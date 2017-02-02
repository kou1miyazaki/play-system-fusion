package controllers.dbx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxCallable;

import models.adm.BookmarkModel;
import models.dbx.TableColumnModel;
import models.dbx.TableDataModel;
import models.dbx.TableModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import utils.adm.BookmarkConstraint;
import utils.cmn.CommonConstraint;
import utils.cmn.DateParser;
import utils.cmn.Session;
import utils.dbx.DatabaseConstraint;
import views.form.cmn.ItemForm;
import views.form.dbx.TableDataForm;

/**
 *modelList
 * @param
 * @return
 */
public class TableDataController extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(TableDataController.class);

	/**
	  * 一覧画面を表示する.
	  *
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	  */
	public Result index(String tableName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		// セッションを初期化.
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_TABLE_NAME, tableName);
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_SEARCH_WORD, "");

		// テーブル定義取得.
		List<TableModel> table = TableModel.getList(tableName);
		if (table.isEmpty()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.dbx.dataList.render(DatabaseConstraint.C_DATABASE + " List", tableName, null, null));
		}

		//logger.debug(table.get(0).displayName);

		// セッションを初期化.
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_DISPLAY_NAME, table.get(0).displayName);

		// 項目リスト取得.
		List<TableColumnModel> col = TableColumnModel.getList(tableName);

		// リスト取得.
		List<TableDataModel> model = TableDataModel.getList(tableName);

		// リスト取得.
		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();

		for(int i = 0; i < model.size(); ++i){
			// ObjectをHashMapに変換する.
			data.add(TableDataModel.convertToDataMap(col, model.get(i)));
		}

		return ok(views.html.dbx.dataList.render(DatabaseConstraint.C_DATABASE + " List", tableName, data, col));
	}

	/**
	  * 一覧画面を表示する.
	  *
	  * @param		bookmarkType
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	  */
	public Result list(String tableName, String searchWord) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		// セッションを保存.
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_TABLE_NAME, tableName);
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_SEARCH_WORD, searchWord);

		// 項目リスト取得.
		List<TableColumnModel> col = TableColumnModel.getList(tableName);
		if (col.isEmpty()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.dbx.dataList.render(DatabaseConstraint.C_DATABASE + " List", tableName, null, null));
		}

		// テーブルリスト取得.
		List<TableDataModel> modelList = (searchWord == null || searchWord == "") ?
				TableDataModel.getList(tableName) : TableDataModel.getList(tableName, searchWord);

		// HashMap取得.
		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < modelList.size(); ++i){
			// ObjectをHashMapに変換する.
			data.add(TableDataModel.convertToDataMap(col, modelList.get(i)));
		}

		return ok(views.html.dbx.dataList.render(DatabaseConstraint.C_DATABASE + " List", tableName, data, col));
	}

	/**
	 * 詳細画面を表示する.
	 *
	 * @param id
	 * @return
	 */
	public Result detail(String tableName, Integer id) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		// セッションに保存.
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_ID, id.toString());

		// エラー処理.
		if (tableName == null || tableName == "") {
			return badRequest();
		}
		if (id == null || id == 0) {
			return badRequest();
		}

		// 項目リスト取得.
		List<TableColumnModel> col = TableColumnModel.getForm(tableName);

		// 値を取得.
		TableDataForm form = TableDataModel.convertToForm(TableDataModel.finder.byId(id.longValue()));

	    // リスト作成.
		List<ItemForm> item = TableDataModel.convertToItem(col, form);

	    return ok(views.html.dbx.dataDetail.render(DatabaseConstraint.C_DATABASE + " Detail", tableName, item));
	}

	/**
	 * 編集画面を表示する.
	 *
	 * @param id
	 * @return
	 */
	public Result edit(String tableName, Integer id) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		// セッションに保存.
		Session.set(DatabaseConstraint.C_DATABASE_SESSION_ID, id.toString());

		// 項目リスト取得.
		List<TableColumnModel> col = TableColumnModel.getForm(tableName);

		// モード処理.
		TableDataForm form;
		if (id == null || id == 0) {
			Session.set(DatabaseConstraint.C_DATABASE_SESSION_MODE,"insert");
			form = new TableDataForm();
			form.id = CommonConstraint.C_SYSTEM_LABEL_AUTOINC;
		} else {
			Session.set(DatabaseConstraint.C_DATABASE_SESSION_MODE,"update");
			form = TableDataModel.convertToForm(TableDataModel.finder.byId(id.longValue()));
		}

		// Formオブジェクト作成.
		Form<TableDataForm> formData = Form.form(TableDataForm.class).fill(form);

	    // リスト作成.
		List<ItemForm> item = TableDataModel.convertToItem(col, form);

		// Debug.
		logger.info(form.toString());

	    return ok(views.html.dbx.dataEdit.render(DatabaseConstraint.C_DATABASE + " Edit", tableName, item, formData));
	}

	/**
	 * 値を保存する.
	 *
	 * @param id
	 * @return
	 */
	public Result save(String tableName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		logger.debug("[" + DatabaseConstraint.C_DATABASE_SESSION_MODE + "] " + Session.get(DatabaseConstraint.C_DATABASE_SESSION_MODE));

		// リクエストを取得.
		Form<TableDataForm> formData = Form.form(TableDataForm.class).bindFromRequest();

	    // リスト作成.
		List<ItemForm> item = TableDataModel.convertToItem();

		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			//return badRequest(views.html.dbx.dataEdit.render(DatabaseConstraint.C_DATABASE + " Retry", item, formData));
			return badRequest();
		}

		// 更新処理.
		TableDataModel model;
		try {
			model = TableDataModel.convertToModel(formData.get());
			if (Session.get(DatabaseConstraint.C_DATABASE_SESSION_MODE).equals("insert")) {
				Ebean.execute(()->{
					model.tableName = tableName;
					model.save();
				});
			} else if (Session.get(DatabaseConstraint.C_DATABASE_SESSION_MODE).equals("update")) {
				Ebean.execute(()->{
					logger.info(model.toString());
					model.id= Integer.valueOf(Session.get(DatabaseConstraint.C_DATABASE_SESSION_ID)).intValue();
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
		return redirect("/database/" + tableName + "/list" + "?searchWord=" + Session.get(DatabaseConstraint.C_DATABASE_SESSION_SEARCH_WORD));
	}

	/**
	 * 値を削除する.
	 *
	 * @param id
	 * @return
	 */
	 public Result delete(String tableName, Integer id) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(" [" + tableName + "] " + DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		// エラー処理.
		if (id == null || id == 0) {
			flash("error", Messages.get("common.validation.error"));
			List<BookmarkModel> model = BookmarkModel.finder.all();
			return badRequest(views.html.adm.bookmarkList.render(BookmarkConstraint.C_BOOKMARK + " List", model));
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
		return redirect("/database/" + tableName + "/list" + "?searchWord=" + Session.get(DatabaseConstraint.C_DATABASE_SESSION_SEARCH_WORD));
	 }
}
