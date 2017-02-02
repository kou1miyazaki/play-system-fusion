package consts.dbx;

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
public class TableColumnConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Table Column";
	/** 名前:詳細. */
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	/** 名前:一覧. */
	public static final String C_NAME_LIST = C_NAME + " List";
	/** 名前:編集. */
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/data.jpg";

	/** テーブル名. */
	public static final String C_TABLE = "DBX_TM_TABLE_COLUMN";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:ID. */
	public static final String C_TABLE_COL_TABLE_NAME = "TABLE_NAME";
	/** カラム:ID. */
	public static final String C_TABLE_COL_TABLE_COL_NAME = "TABLE_COL_NAME";
	/** カラム:ID. */
	public static final String C_TABLE_COL_COLUMN_NAME = "COLUMN_NAME";
	/** カラム:ID. */
	public static final String C_TABLE_COL_DISPLAY_TYPE = "DISPLAY_TYPE";
	/** カラム:ID. */
	public static final String C_TABLE_COL_DISPLAY_NAME = "DISPLAY_NAME";
	/** カラム:ID. */
	public static final String C_TABLE_COL_LIST_FLG = "LIST_FLG";
	/** カラム:ID. */
	public static final String C_TABLE_COL_LIST_ORDER = "LIST_ORDER";
	/** カラム:ID. */
	public static final String C_TABLE_COL_SEARCH_FLG = "SEARCH_FLG";
	/** カラム:ID. */
	public static final String C_TABLE_COL_SEARCH_ORDER = "SEARCH_ORDER";

	public static final String C_LABEL_COL_ID = "ID";
	public static final String C_LABEL_COL_TABLE_NAME = "TABLE_NAME";
	public static final String C_LABEL_COL_TABLE_COL_NAME = "TABLE_COL_NAME";
	public static final String C_LABEL_COL_COLUMN_NAME = "COLUMN_NAME";
	public static final String C_LABEL_COL_DISPLAY_TYPE = "DISPLAY_TYPE";
	public static final String C_LABEL_COL_DISPLAY_NAME = "DISPLAY_NAME";
	public static final String C_LABEL_COL_LIST_FLG = "LIST_FLG";
	public static final String C_LABEL_COL_LIST_ORDER = "LIST_ORDER";
	public static final String C_LABEL_COL_SEARCH_FLG = "SEARCH_FLG";
	public static final String C_LABEL_COL_SEARCH_ORDER = "SEARCH_ORDER";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("id", "tableName", "displayName", "tableDesc", "imageIcon");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ID", "テーブル名", "表示名", "テーブル説明", "アイコン");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "text", "text", "image");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col1", "col2", "col1", "col2", "col1");

	/** URL. */
	public static final String C_URL_BASE = "/database";
	//public static final String C_DATABASE_INDEX = "/database/index";
	//public static final String C_DATABASE_LIST = "/database/list";
	//public static final String C_DATABASE_EDIT = "/database/edit";
	//public static final String C_DATABASE_CREATE = "/database/create";
	//public static final String C_DATABASE_DELETE = "/database/delete";
}