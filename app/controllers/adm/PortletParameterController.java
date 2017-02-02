package controllers.adm;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import consts.adm.PortletParameterConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.adm.PortletParameterForm;
import forms.cmn.CommonSearchForm;
import forms.cmn.ItemForm;
import models.adm.PortletParameterModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * ポートレット・パラメーター・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class PortletParameterController extends SystemFusionController {

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
		Session.set(PortletParameterConstraint.C_SESSION_TAB, "adm2");
		Session.set(PortletParameterConstraint.C_SESSION_URL, PortletParameterConstraint.C_URL_INDEX);
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
		//LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_LIST_NAME, "");

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

		// ログ出力.
		//StackTraceElement ste = new Throwable().getStackTrace()[0];
		//LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_LIST_NAME, "");

		// リクエストを取得.
		Form<CommonSearchForm> formData = Form.form(CommonSearchForm.class).bindFromRequest();

		// 検索キーワードをセッションに保存.
		if (!formData.get().searchWord.isEmpty()) {
			Session.set(PortletParameterConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
		}

		// 1ページ目を返す.
		return list(1);
	}


	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param page ページ数<br>
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result list(final Integer page) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(PortletParameterConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(PortletParameterConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<PortletParameterModel> pagedList = PortletParameterModel.getPagedList(page, searchWord);

		// リスト取得.
        List<PortletParameterModel> model = pagedList.getList();
        int maxPage = pagedList.getTotalPageCount();

		return ok(views.html.adm.portletParameterList.render(PortletParameterConstraint.C_LABEL_LIST_NAME, model, page, maxPage));
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
		LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(PortletParameterConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		PortletParameterForm form = PortletParameterModel.finder.byId(id.longValue()).getForm();
		List<ItemForm> formItem = form.getItem();

		// WhoIs項目を取得.
		List<ItemForm> whoisItem = form.getItemRecord();

	    return ok(views.html.adm.portletParameterDetail.render(PortletParameterConstraint.C_LABEL_DETAIL_NAME, formItem, whoisItem));
	}

	/**
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
		LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(PortletParameterConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		PortletParameterForm form;
		if (id == null || id == 0) {
			Session.set(PortletParameterConstraint.C_SESSION_MODE, "insert");
			form = new PortletParameterForm();
			form.id = PortletParameterConstraint.C_SYSTEM_LABEL_AUTOINC;
		} else {
			Session.set(PortletParameterConstraint.C_SESSION_MODE, "update");
			form = PortletParameterModel.finder.byId(id.longValue()).getForm();
		}

		// Formオブジェクト作成.
		Form<PortletParameterForm> formData = Form.form(PortletParameterForm.class).fill(form);

		// Debug.
		logger.info(form.toString());

	    return ok(views.html.adm.portletParameterEdit.render(PortletParameterConstraint.C_LABEL_EDIT_NAME, formData));
	}

	/**
	 *
	 * 値を保存する.
	 *
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result save() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_SAVE_NAME, "");

		// リクエストを取得.
		Form<PortletParameterForm> formData = Form.form(PortletParameterForm.class).bindFromRequest();

		// バリデーションエラー処理.
		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.adm.portletParameterEdit.render(PortletParameterConstraint.C_NAME + " Retry", formData));
		}

		// 更新処理.
		PortletParameterModel model = formData.get().getModel();
		model.updateUser = Session.get(PortletParameterConstraint.C_SESSION_USER_NAME);

		if (Session.get(PortletParameterConstraint.C_SESSION_MODE).equals("insert")) {
			Ebean.execute(() -> {
				model.createUser = Session.get(PortletParameterConstraint.C_SESSION_USER_NAME);
				model.save();
			});
		} else if (Session.get(PortletParameterConstraint.C_SESSION_MODE).equals("update")) {
			Ebean.execute(() -> {
				model.id = Integer.valueOf(Session.get(PortletParameterConstraint.C_SESSION_ID)).intValue();
				model.update();
			});
		}
		flash("success", Messages.get("common.save.success"));

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.PortletParameterController.list(session("pPortletParameterType")));
		return redirect(PortletParameterConstraint.C_URL_LIST + "/" + Session.get(PortletParameterConstraint.C_SESSION_PAGE));
	}

	/**
	 *
	 * 値を削除する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result delete(final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, PortletParameterConstraint.C_LABEL_DELETE_NAME, "");

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
					PortletParameterModel model = PortletParameterModel.finder.byId(id.longValue());
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
		//return redirect(controllers.adm.routes.PortletParameterController.list(
		//	session(PortletParameterConstraint.C_ACCOUNT_SESSION_ACCOUNT_TYPE)));
		return redirect(PortletParameterConstraint.C_URL_LIST + "/" + Session.get(PortletParameterConstraint.C_SESSION_PAGE));
	 }

}

