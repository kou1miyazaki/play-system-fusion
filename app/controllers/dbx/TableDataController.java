package controllers.dbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.TxCallable;

import consts.adm.AccountConstraint;
import consts.dbx.TableDataConstraint;
import controllers.cmn.AuthenticationAction;
import forms.cmn.ItemForm;
import forms.dbx.TableDataForm;
import forms.dbx.TableDataSearchForm;
import models.dbx.TableColumnModel;
import models.dbx.TableDataModel;
import models.dbx.TableModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
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
public class TableDataController extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(TableDataController.class);

	/**
	 *
	 * 画面を初期化する.
	 *
	 * @param tableName テーブル名<br>
	 *
	 */
	public final void init(final String tableName) {
		// セッションを初期化.
		Session.set(TableDataConstraint.C_SESSION_TABLE_NAME, tableName);
		Session.set(TableDataConstraint.C_SESSION_SEARCH_WORD, "");
		Session.set(AccountConstraint.C_SESSION_PAGE, "1");
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param tableName テーブル名<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result index(final String tableName) {

		// セッションを初期化.
		init(tableName);

		// 1ページ目を返す.
		return list(tableName, 1);
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param		tableName tableName
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result search(final String tableName) {

		// リクエストを取得.
		Form<TableDataSearchForm> formData = Form.form(TableDataSearchForm.class).bindFromRequest();

		// 検索キーワードをセッションに保存.
		if (!formData.get().searchWord.isEmpty()) {
			Session.set(TableDataConstraint.C_SESSION_SEARCH_WORD, formData.get().searchWord);
		}

		// 1ページ目を返す.
		return list(tableName, 1);
	}

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param tableName tableName
	 * @param page page
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result list(final String tableName, final Integer page) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, TableDataConstraint.C_SYSTEM_APPL_SYS, TableDataConstraint.C_LABEL_LIST_NAME, "");

		// 検索キーワードをセッションから取得.
		String searchWord = Session.get(TableDataConstraint.C_SESSION_SEARCH_WORD);

		// テーブル名をセッション保存.
		Session.set("session.displayName", TableModel.getList(tableName).get(0).displayName);

		// ページ数をセッションに保存.
		Session.set(TableDataConstraint.C_SESSION_PAGE, page.toString());

		// 項目リスト取得.
		List<TableColumnModel> col = TableColumnModel.getList(tableName);
		if (col.isEmpty()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest();
		}

		// ページネーション取得.
		PagedList<TableDataModel> pagedList = TableDataModel.getPagedList(tableName, page, searchWord);

		// テーブルリスト取得.
        List<TableDataModel> model = pagedList.getList();
        int maxPage = pagedList.getTotalPageCount();

		// HashMap取得.
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < model.size(); ++i) {
			// ObjectをHashMapに変換する.
			data.add(model.get(i).getMap(col));
		}

		// DatabaseFormObject作成.

		return ok(views.html.dbx.tableDataList.render(TableDataConstraint.C_LABEL_LIST_NAME, tableName, data, col, page, maxPage));
	}

	/**
	 *
	 * 詳細画面を表示する.
	 *
	 * @param tableName テーブル名<br>
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result detail(final String tableName, final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, TableDataConstraint.C_SYSTEM_APPL_SYS, TableDataConstraint.C_LABEL_DETAIL_NAME, "");

		// セッションに保存.
		Session.set(TableDataConstraint.C_SESSION_ID, id.toString());

		// エラー処理.
		if (tableName == null || tableName == "") {
			return badRequest();
		}
		if (id == null || id == 0) {
			return badRequest();
		}

		// List<ItemForm>オブジェクト作成.
		TableDataForm form = TableDataModel.finder.byId(id.longValue()).getForm();
		List<ItemForm> item = form.getItem(TableColumnModel.getListForm(tableName));

		// WhoIs項目を取得.
		List<ItemForm> whoisItem = form.getItemRecord();

		// DatabaseFormObject作成.
		//DatabaseForm dbForm = new DatabaseForm(col, data, search);

	    return ok(views.html.dbx.tableDataDetail.render(TableDataConstraint.C_LABEL_LIST_NAME, tableName, item, whoisItem));
	}

	/**
	 *
	 * 編集画面を表示する.
	 *
	 * @param tableName テーブル名<br>
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result edit(final String tableName, final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, TableDataConstraint.C_SYSTEM_APPL_SYS, TableDataConstraint.C_LABEL_EDIT_NAME, "");

		// セッションに保存.
		Session.set(TableDataConstraint.C_SESSION_ID, id.toString());

		// モード処理.
		TableDataForm form;
		if (id == null || id == 0) {
			Session.set(TableDataConstraint.C_SESSION_MODE, "insert");
			form = new TableDataForm();
			form.id = TableDataConstraint.C_SYSTEM_LABEL_AUTOINC;
		} else {
			Session.set(TableDataConstraint.C_SESSION_MODE, "update");
			form = TableDataModel.finder.byId(id.longValue()).getForm();
		}

	    // リスト作成.
		List<ItemForm> item = form.getItem(TableColumnModel.getListForm(tableName));

		// Formオブジェクト作成.
		Form<TableDataForm> formData = Form.form(TableDataForm.class).fill(form);

		// Debug.
		logger.info(form.toString());

	    return ok(views.html.dbx.tableDataEdit.render(TableDataConstraint.C_LABEL_EDIT_NAME, tableName, item, formData));
	}

	/**
	 *
	 * 値を保存する.
	 *
	 * @param tableName tableName
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result save(final String tableName) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, TableDataConstraint.C_SYSTEM_APPL_SYS, TableDataConstraint.C_LABEL_SAVE_NAME, "");

		logger.debug("[" + TableDataConstraint.C_SESSION_MODE + "] " + Session.get(TableDataConstraint.C_SESSION_MODE));

		// リクエストを取得.
		Form<TableDataForm> formData = Form.form(TableDataForm.class).bindFromRequest();

	    // リスト作成.
		//List<ItemForm> item = TableDataForm.getItem();

		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			//return badRequest(views.html.dbx.tableDataEdit.render(DatabaseConstraint.C_DATABASE + " Retry", item, formData));
			return badRequest();
		}

		// 更新処理.
		TableDataModel model;
		try {
			model = formData.get().getModel();
			model.updateUser = Session.get(TableDataConstraint.C_SESSION_USER_NAME);

			if (Session.get(TableDataConstraint.C_SESSION_MODE).equals("insert")) {
				Ebean.execute(() -> {
					model.tableName = tableName;
					model.createUser = Session.get(TableDataConstraint.C_SESSION_USER_NAME);
					model.save();
				});
			} else if (Session.get(TableDataConstraint.C_SESSION_MODE).equals("update")) {
				Ebean.execute(() -> {
					model.id = Integer.valueOf(Session.get(TableDataConstraint.C_SESSION_ID)).intValue();
					model.tableName = tableName;
					model.update();
				});
			}
			flash("success", Messages.get("common.save.success"));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.BookmarkController.list(session("pBookmarkType")));
		return redirect("/database/" + tableName + "/list" + "/" + Session.get(TableDataConstraint.C_SESSION_PAGE));
	}

	/**
	 *
	 * 値を削除する.
	 *
	 * @param tableName tableName<br>
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result delete(final String tableName, final Integer id) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, TableDataConstraint.C_SYSTEM_APPL_SYS, TableDataConstraint.C_LABEL_DELETE_NAME, "");

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
					TableDataModel model = TableDataModel.finder.byId(id.longValue());
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
		return redirect("/database/" + tableName + "/list" + "/" + Session.get(TableDataConstraint.C_SESSION_PAGE));
	 }
}
