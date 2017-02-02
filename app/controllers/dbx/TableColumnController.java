package controllers.dbx;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.adm.AccountForm;
import forms.cmn.CommonSearchForm;
import models.adm.AccountModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * アカウント・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class TableColumnController extends SystemFusionController {

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
		Session.set(AccountConstraint.C_SESSION_TAB, "adm1");
		Session.set(AccountConstraint.C_SESSION_URL, AccountConstraint.C_URL_INDEX);
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
			Session.set(AccountConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
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
		LogModel.logging(new Throwable().getStackTrace()[0], AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(AccountConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(AccountConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<AccountModel> pagedList = AccountModel.getPagedList(page, searchWord);

		return ok(views.html.adm.accountList.render(AccountConstraint.C_LABEL_LIST_NAME, pagedList.getList(), page, pagedList.getTotalPageCount()));
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
		LogModel.logging(new Throwable().getStackTrace()[0], AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(AccountConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		AccountForm form = AccountModel.finder.byId(id.longValue()).getForm();

	    return ok(views.html.adm.accountDetail.render(AccountConstraint.C_LABEL_DETAIL_NAME, form.getItem(), form.getItemRecord()));
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
		LogModel.logging(new Throwable().getStackTrace()[0], AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(AccountConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		AccountForm form;
		if (id == null || id == 0) {
			Session.set(AccountConstraint.C_SESSION_MODE, "insert");
			form = new AccountForm();
		} else {
			Session.set(AccountConstraint.C_SESSION_MODE, "update");
			form = AccountModel.finder.byId(id.longValue()).getForm();
		}

		// Formオブジェクト作成.
		Form<AccountForm> formData = Form.form(AccountForm.class).fill(form);

	    return ok(views.html.adm.accountEdit.render(AccountConstraint.C_LABEL_EDIT_NAME, formData));
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
		LogModel.logging(new Throwable().getStackTrace()[0], AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_SAVE_NAME, "");

		// リクエストを取得.
		Form<AccountForm> formData = Form.form(AccountForm.class).bindFromRequest();

		// バリデーションエラー処理.
		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.adm.accountEdit.render(AccountConstraint.C_NAME + " Retry", formData));
		}

		// 更新処理.
		AccountModel model = formData.get().getModel();
		if (Session.get(AccountConstraint.C_SESSION_MODE).equals("insert")) {
			Ebean.execute(() -> {
				model.save();
			});
		} else if (Session.get(AccountConstraint.C_SESSION_MODE).equals("update")) {
			Ebean.execute(() -> {
				model.id = Integer.valueOf(Session.get(AccountConstraint.C_SESSION_ID)).intValue();
				model.update();
			});
		}
		flash("success", Messages.get("common.save.success"));

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.AccountController.list(session("pAccountType")));
		return redirect(AccountConstraint.C_URL_LIST + "/" + Session.get(AccountConstraint.C_SESSION_PAGE));
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
		LogModel.logging(new Throwable().getStackTrace()[0], AccountConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_DELETE_NAME, "");

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
					AccountModel model = AccountModel.finder.byId(id.longValue());
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
		return redirect(AccountConstraint.C_URL_LIST + "/" + Session.get(AccountConstraint.C_SESSION_PAGE));
	 }
}
