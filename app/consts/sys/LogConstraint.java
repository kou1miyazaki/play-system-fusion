package consts.sys;

import java.util.Arrays;
import java.util.List;

import consts.cmn.CommonConstraint;

/**
 *
 * 共通コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class LogConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Log";
	/** 名前:詳細. */
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	/** 名前:一覧. */
	public static final String C_NAME_LIST = C_NAME + " List";
	/** 名前:編集. */
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/admin.png";

	/** テーブル名. */
	public static final String C_TABLE = "SYS_TT_LOG";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:アプリ. */
	public static final String C_TABLE_COL_APP_TYPE = "APP_TYPE";
	/** カラム:UUID. */
	public static final String C_TABLE_COL_UUID = "UUID";
	/** カラム:ID. */
	public static final String C_TABLE_COL_LOG_TITLE = "LOG_TITLE";
	/** カラム:タイトル. */
	public static final String C_TABLE_COL_LOG_BODY = "LOG_BODY";
	/** カラム:クラス名. */
	public static final String C_TABLE_COL_CLASS_NAME = "CLASS_NAME";
	/** カラム:メソッド名. */
	public static final String C_TABLE_COL_METHOD_NAME = "METHOD_NAME";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("id", "createDate", "createUser", "appType", "uuid", "className", "methodName", "logTitle", "logBody");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ID", "操作日", "操作者", "アプリ", "UUID", "クラス名", "メソッド名", "タイトル", "説明");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "test", "text", "text", "text", "text", "text", "text");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col1", "col1", "col1", "col1", "col1", "col1", "col1", "col2", "col2");

	public static final String C_URL_BASE = "/log";
	public static final String C_URL_INDEX = C_URL_BASE + "/index";
	public static final String C_URL_SEARCH = C_URL_BASE + "/search";
	public static final String C_URL_DETAIL = C_URL_BASE + "/detail";
	public static final String C_URL_LIST = C_URL_BASE + "/list";

	public static final String C_LABEL_NAME = "ログ";
	public static final String C_LABEL_LIST_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_LIST;
	public static final String C_LABEL_DETAIL_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_DETAIL;

	public static final String C_SESSION_SEARCH_WORD = "session.searchWord";
}
