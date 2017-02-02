package controllers.adm;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import consts.adm.PortalConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.adm.PortalForm;
import forms.cmn.CommonSearchForm;
import models.adm.PortalModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * ポータル・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class PortalController extends SystemFusionController {

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
		Session.set(PortalConstraint.C_SESSION_TAB, "adm2");
		Session.set(PortalConstraint.C_SESSION_URL, PortalConstraint.C_URL_INDEX);
	}


	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	public final Result index() {

		// ログ出力.
		//StackTraceElement ste = new Throwable().getStackTrace()[0];
		//LogModel.logging(ste, AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_LIST_NAME, "");

		// セッションを初期化.
		init();

		// 1ページ目を返す.
		return list(1);
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param		bookmarkType
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result search() {

		// ログ出力.
		//StackTraceElement ste = new Throwable().getStackTrace()[0];
		//LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_LIST_NAME, "");

		// リクエストを取得.
		Form<CommonSearchForm> formData = Form.form(CommonSearchForm.class).bindFromRequest();

		// 検索キーワードをセッションに保存.
		if (!formData.get().searchWord.isEmpty()) {
			Session.set(PortalConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
		}

		// 1ページ目を返す.
		return list(1);
	}


	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param page ページ番号<br>
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result list(final Integer page) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(PortalConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(PortalConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<PortalModel> pagedList = PortalModel.getPagedList(page, searchWord);

		// リスト取得.
        List<PortalModel> model = pagedList.getList();

		return ok(views.html.adm.portalList.render(PortalConstraint.C_LABEL_LIST_NAME, model, page, pagedList.getTotalPageCount()));
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
		LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(PortalConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		PortalForm form = PortalModel.finder.byId(id.longValue()).getForm();

	    return ok(views.html.adm.portalDetail.render(PortalConstraint.C_LABEL_DETAIL_NAME, form.getItem(), form.getItemRecord()));
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
		LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(PortalConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		PortalForm form;
		if (id == null || id == 0) {
			Session.set(PortalConstraint.C_SESSION_MODE, "insert");
			form = new PortalForm();
			form.id = PortalConstraint.C_SYSTEM_LABEL_AUTOINC;
		} else {
			Session.set(PortalConstraint.C_SESSION_MODE, "update");
			form = PortalModel.finder.byId(id.longValue()).getForm();
		}

		// Formオブジェクト作成.
		Form<PortalForm> formData = Form.form(PortalForm.class).fill(form);

		// Debug.
		logger.info(form.toString());

	    return ok(views.html.adm.portalEdit.render(PortalConstraint.C_LABEL_EDIT_NAME, formData));
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
		LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_SAVE_NAME, "");

		// リクエストを取得.
		Form<PortalForm> formData = Form.form(PortalForm.class).bindFromRequest();

		// バリデーションエラー処理.
		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest();
		}

		// 更新処理.
		PortalModel model = formData.get().getModel();
		model.updateDate = new Date();
		model.updateUser = Session.get(PortalConstraint.C_SESSION_USER_NAME);

		if (Session.get(PortalConstraint.C_SESSION_MODE).equals("insert")) {
			Ebean.execute(() -> {
				logger.debug(model.toString());
				model.createDate = new Date();
				model.createUser = Session.get(PortalConstraint.C_SESSION_USER_NAME);
				model.save();
			});
		} else if (Session.get(PortalConstraint.C_SESSION_MODE).equals("update")) {
			Ebean.execute(() -> {
				logger.debug(model.toString());
				model.id = Integer.valueOf(Session.get(PortalConstraint.C_SESSION_ID)).intValue();
				model.update();
			});
		}
		flash("success", Messages.get("common.save.success"));

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.PortalController.list(session("pPortalType")));
		return redirect(PortalConstraint.C_URL_LIST + "/" + Session.get(PortalConstraint.C_SESSION_PAGE));
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
		LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, PortalConstraint.C_LABEL_DELETE_NAME, "");

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
					PortalModel model = PortalModel.finder.byId(id.longValue());
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
		//return redirect(controllers.adm.routes.PortalController.list(
		//	session(PortalConstraint.C_Portal_SESSION_Portal_TYPE)));
		return redirect(PortalConstraint.C_URL_LIST + "/" + Session.get(PortalConstraint.C_SESSION_PAGE));
	 }

}
