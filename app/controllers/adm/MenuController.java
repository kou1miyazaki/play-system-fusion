package controllers.adm;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import consts.adm.MenuConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.adm.MenuForm;
import forms.cmn.CommonSearchForm;
import models.adm.MenuModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * メニュー・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class MenuController extends SystemFusionController {

	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void init() {
		// セッションを初期化.
		clear();
		Session.set(AccountConstraint.C_SESSION_SEARCH_WORD, "");

		// タブ設定.
		Session.set(MenuConstraint.C_SESSION_TAB, "adm1");
		Session.set(MenuConstraint.C_SESSION_URL, MenuConstraint.C_URL_INDEX);
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result index() {

		// ログ出力.
		//StackTraceElement ste = new Throwable().getStackTrace()[0];
		//LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_LIST_NAME, "");

		// セッションを初期化.
		init();

		// 1ページ目を返す.
		return list(1);
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result search() {

		// リクエストを取得.
		Form<CommonSearchForm> formData = Form.form(CommonSearchForm.class).bindFromRequest();

		// 検索キーワードをセッションに保存.
		if (!formData.get().searchWord.isEmpty()) {
			Session.set(MenuConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
		}

		// 1ページ目を返す.
		return list(1);
	}


	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param		page ベージ番号<br>
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result list(final Integer page) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(MenuConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(MenuConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<MenuModel> pagedList = MenuModel.getPagedList(page, searchWord);

		// リスト取得.
        List<MenuModel> model = pagedList.getList();

		return ok(views.html.adm.menuList.render(MenuConstraint.C_LABEL_LIST_NAME, model, page, pagedList.getTotalPageCount()));
	}

	/**
	 *
	 * 詳細画面を表示する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result detail(final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(MenuConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		MenuForm form = MenuModel.finder.byId(id.longValue()).getForm();

	    return ok(views.html.adm.menuDetail.render(MenuConstraint.C_LABEL_DETAIL_NAME, form.getItem(), form.getItemRecord()));
	}

	/**
	 *
	 * 編集画面を表示する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result edit(final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(MenuConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		MenuForm form;
		if (id == null || id == 0) {
			Session.set(MenuConstraint.C_SESSION_MODE, "insert");
			form = new MenuForm();
		} else {
			Session.set(MenuConstraint.C_SESSION_MODE, "update");
			form = MenuModel.finder.byId(id.longValue()).getForm();
		}

		// Formオブジェクト作成.
		Form<MenuForm> formData = Form.form(MenuForm.class).fill(form);

		// Debug.
		logger.info(form.toString());

	    return ok(views.html.adm.menuEdit.render(MenuConstraint.C_LABEL_EDIT_NAME, formData));
	}

	/**
	 *
	 * 値を保存する.
	 *
	 * @return Result
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result save() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_SAVE_NAME, "");

		logger.debug("[" + MenuConstraint.C_SESSION_MODE + "] " + Session.get(MenuConstraint.C_SESSION_MODE));

		// Debug.
		//logger.info(Form.form(BookmarkForm.class).bindFromRequest().get().toString());

		// リクエストを取得.
		Form<MenuForm> formData = Form.form(MenuForm.class).bindFromRequest();

		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.adm.menuEdit.render(MenuConstraint.C_NAME + " Retry", formData));

		}

		// 更新処理.
		MenuModel model = formData.get().getModel();

		if (Session.get(MenuConstraint.C_SESSION_MODE).equals("insert")) {
			Ebean.execute(() -> {
				model.save();
			});
		} else if (Session.get(MenuConstraint.C_SESSION_MODE).equals("update")) {
			Ebean.execute(() -> {
				model.id = Integer.valueOf(Session.get(MenuConstraint.C_SESSION_ID)).intValue();
				model.update();
			});
		}
		flash("success", Messages.get("common.save.success"));

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.MenuController.list(session("pMenuType")));
		return redirect(MenuConstraint.C_URL_LIST + "/" + Session.get(MenuConstraint.C_SESSION_PAGE));
	}

	/**
	 *
	 * 値を削除する.
	 *
	 * @return Result
	 * @param id ID<br>
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result delete(final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, MenuConstraint.C_SYSTEM_APPL_ADM, MenuConstraint.C_LABEL_DELETE_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest();
		}

		// 削除処理.
		Boolean result = Ebean.execute(
			new TxCallable<Boolean>() {
				@Override
				public Boolean call() {
					MenuModel model = MenuModel.finder.byId(id.longValue());
					model.delete();
					return Boolean.TRUE;
				}
			}
		);

		// エラー処理.
		if (result) {
			flash("success", Messages.get("common.delete.success"));
		} else {
			flash("error", Messages.get("common.delete.error"));
		}

		// 一覧表示画面を表示する.(リダイレクト)
		return redirect(MenuConstraint.C_URL_LIST + "/" + Session.get(MenuConstraint.C_SESSION_PAGE));
	 }

}

