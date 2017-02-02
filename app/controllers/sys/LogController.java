package controllers.sys;

import java.util.List;

import com.avaje.ebean.PagedList;

import consts.sys.LogConstraint;
import controllers.cmn.AuthenticationAction;
import controllers.cmn.SystemFusionController;
import forms.cmn.CommonSearchForm;
import forms.cmn.ItemForm;
import models.sys.LogModel;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * ログ・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class LogController extends SystemFusionController {

	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void init() {
		// セッションを初期化.
		Session.set(LogConstraint.C_SESSION_ID, "");
		Session.set(LogConstraint.C_SESSION_SEARCH_WORD, "");
		Session.set(LogConstraint.C_SESSION_PAGE, "1");

		// タブ設定.
		Session.set(LogConstraint.C_SESSION_TAB, "sys1");
		Session.set(LogConstraint.C_SESSION_URL, LogConstraint.C_URL_INDEX);
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
			Session.set(LogConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
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
		LogModel.logging(ste, LogConstraint.C_SYSTEM_APPL_SYS, LogConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(LogConstraint.C_SESSION_SEARCH_WORD);

		// ページ数をセッションに保存.
		Session.set(LogConstraint.C_SESSION_PAGE, page.toString());

		// ページネーション取得.
		PagedList<LogModel> pagedList = LogModel.getPagedList(page, searchWord);

		return ok(views.html.sys.logList.render(LogConstraint.C_LABEL_LIST_NAME, pagedList.getList(), page,  pagedList.getTotalPageCount()));
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
		LogModel.logging(ste, LogConstraint.C_SYSTEM_APPL_SYS, LogConstraint.C_LABEL_DETAIL_NAME, "");

		// エラー処理.
		if (id == null || id == 0) {
			return badRequest();
		}

		// セッションに保存.
		Session.set(LogConstraint.C_SESSION_ID, id.toString());

		// List<ItemForm>オブジェクト作成.
		List<ItemForm> formItem = LogModel.finder.byId(id.longValue()).getForm().getItem();

	    return ok(views.html.sys.logDetail.render(LogConstraint.C_LABEL_DETAIL_NAME, formItem));
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
		return null;
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
		return null;
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
		 return null;
	}
}
