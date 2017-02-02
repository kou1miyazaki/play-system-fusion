package controllers.adm;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import consts.adm.BookmarkConstraint;
import consts.cmn.CommonConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.adm.BookmarkForm;
import forms.cmn.CommonSearchForm;
import models.adm.BookmarkModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * ブックマーク：コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class BookmarkController extends SystemFusionController {

	/**
	 *
	 * 画面を初期化する.
	 *
	 *
	 */
	public final void init() {
		// セッションを初期化.
		clear();
		Session.set(AccountConstraint.C_SESSION_SEARCH_WORD, "");

		// タブ設定.
		Session.set(BookmarkConstraint.C_SESSION_TAB, "adm1");
		Session.set(BookmarkConstraint.C_SESSION_URL, BookmarkConstraint.C_URL_INDEX);
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
			Session.set(BookmarkConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
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
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, BookmarkConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(BookmarkConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(AccountConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<BookmarkModel> pagedList = BookmarkModel.getPagedList(page, searchWord);

		// リスト取得.
        List<BookmarkModel> model = pagedList.getList();

		return ok(views.html.adm.bookmarkList.render(BookmarkConstraint.C_LABEL_LIST_NAME, model, page, pagedList.getTotalPageCount()));
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
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, BookmarkConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		BookmarkForm form = BookmarkModel.finder.byId(id.longValue()).getForm();

	    return ok(views.html.adm.bookmarkDetail.render(BookmarkConstraint.C_LABEL_DETAIL_NAME, form.getItem(), form.getItemRecord()));
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
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, BookmarkConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		BookmarkForm form;
		if (id == null || id == 0) {
			Session.set(BookmarkConstraint.C_SESSION_MODE, "insert");
			form = new BookmarkForm();
		} else {
			Session.set(BookmarkConstraint.C_SESSION_MODE, "update");
			form = BookmarkModel.finder.byId(id.longValue()).getForm();
		}

		// Formオブジェクト作成.
		Form<BookmarkForm> formData = Form.form(BookmarkForm.class).fill(form);

	    return ok(views.html.adm.bookmarkEdit.render(BookmarkConstraint.C_LABEL_EDIT_NAME, formData));
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
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, BookmarkConstraint.C_LABEL_SAVE_NAME, "");

		// リクエストを取得.
		Form<BookmarkForm> formData = Form.form(BookmarkForm.class).bindFromRequest();

		// エラー処理.
		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.adm.bookmarkEdit.render(BookmarkConstraint.C_NAME + " Retry", formData));
		}

		// 更新処理.
		BookmarkModel model = formData.get().getModel();

		if (Session.get(BookmarkConstraint.C_SESSION_MODE).equals("insert")) {
			Ebean.execute(() -> {
				model.save();
			});
		} else if (Session.get(BookmarkConstraint.C_SESSION_MODE).equals("update")) {
			Ebean.execute(() -> {
				model.id = Integer.valueOf(Session.get(BookmarkConstraint.C_SESSION_ID)).intValue();
				model.update();
			});
		}
		flash("success", Messages.get("common.save.success"));

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.BookmarkController.list(session("pBookmarkType")));
		return redirect(BookmarkConstraint.C_URL_LIST + "/" + Session.get(BookmarkConstraint.C_SESSION_PAGE));
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
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, BookmarkConstraint.C_LABEL_DELETE_NAME, "");

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
					BookmarkModel model = BookmarkModel.finder.byId(id.longValue());
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
		//return redirect(controllers.adm.routes.BookmarkController.list(
		//	session(BookmarkConstraint.C_BOOKMARK_SESSION_BOOKMARK_TYPE)));
		return redirect(BookmarkConstraint.C_URL_LIST + "/" + Session.get(BookmarkConstraint.C_SESSION_PAGE));
	 }


	/**
	 *
	 *
	 * @param url URL<br>
	 * @return Result
	 *
	 */
	public final Result iFrameFull(final String url) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_CMN, BookmarkConstraint.C_LABEL_NAME, url);

		// エラー処理.
		if (url == null || url == "") {
			return badRequest();
		}

	    return ok(views.html.cmn.iFrameFull.render(BookmarkConstraint.C_NAME + " Full", url));
	}

	/**
	 *
	 *
	 *
	 * @param bookmarkType bookmarkType<br>
	 * @param id ID<br>
	 * @return Result
	 */
	public final Result iFrameTab(final String bookmarkType, final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_CMN, BookmarkConstraint.C_LABEL_NAME, "");

		// タブ設定.
		Session.set(CommonConstraint.C_SESSION_TAB, "");

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_ID, id.toString());
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_BOOKMARK_TYPE, bookmarkType);

		// リスト取得.
		List<BookmarkModel> modelList = BookmarkModel.getList(bookmarkType);

		// 先頭行取得.
		BookmarkModel model = BookmarkModel.finder.byId(id.longValue());

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_URL, model.url);

		return ok(views.html.cmn.iFrameTab.render(BookmarkConstraint.C_NAME + " Tab", modelList));
	}

	/**
	 *
	 * @param bookmarkType bookmarkType<br>
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	public final Result iFrameMenu(final String bookmarkType, final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_CMN, BookmarkConstraint.C_LABEL_NAME, bookmarkType);

		// タブ設定.
		Session.set(BookmarkConstraint.C_SESSION_TAB, "");

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_ID, id.toString());
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_BOOKMARK_TYPE, bookmarkType);

		// リスト取得.
		List<BookmarkModel> modelList = BookmarkModel.getList(bookmarkType);

		// 先頭行取得.
		BookmarkModel model = BookmarkModel.finder.byId(id.longValue());

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_URL, model.url);

		return ok(views.html.cmn.iFrameMenu.render(BookmarkConstraint.C_NAME + " Menu", modelList));
	}

	/**
	 *
	 *
	 *
	 * @param bookmarkType bookmarkType
	 * @return Result
	 *
	 */
	public final Result iFrameDial(final String bookmarkType) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_CMN, BookmarkConstraint.C_LABEL_NAME, bookmarkType);

		// タブ設定.
		Session.set(BookmarkConstraint.C_SESSION_TAB, "");

		// セッションに保存.
		Session.set(BookmarkConstraint.C_SESSION_IFRAME_BOOKMARK_TYPE, bookmarkType);

		// リスト取得.
		List<BookmarkModel> modelList = BookmarkModel.getList(bookmarkType);

		return ok(views.html.cmn.iFrameDial.render(BookmarkConstraint.C_NAME + " Dial", modelList));
	}
}
