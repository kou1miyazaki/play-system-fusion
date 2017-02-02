package consts.dbx;

import java.util.Arrays;
import java.util.List;

import consts.cmn.CommonConstraint;

/**
 *
 * テーブル・コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class TableConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Table";
	/** 名前:詳細. */
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	/** 名前:一覧. */
	public static final String C_NAME_LIST = C_NAME + " List";
	/** 名前:編集. */
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/data.jpg";

	/** テーブル名. */
	public static final String C_TABLE = "DBX_TM_TABLE";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:テーブル名. */
	public static final String C_TABLE_COL_TABLE_NAME = "TABLE_NAME";
	/** カラム:表示名. */
	public static final String C_TABLE_COL_DISPLAY_NAME = "DISPLAY_NAME";
	/** カラム:テーブル説明. */
	public static final String C_TABLE_COL_TABLE_DESC = "TABLE_DESC";
	/** カラム:アイコン. */
	public static final String C_TABLE_COL_IMAGE_ICON = "IMAGE_ICON";

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
	public static final String C_DATABASE_BASE = "/database";
	//public static final String C_DATABASE_INDEX = "/database/index";
	//public static final String C_DATABASE_LIST = "/database/list";
	//public static final String C_DATABASE_EDIT = "/database/edit";
	//public static final String C_DATABASE_CREATE = "/database/create";
	//public static final String C_DATABASE_DELETE = "/database/delete";
}