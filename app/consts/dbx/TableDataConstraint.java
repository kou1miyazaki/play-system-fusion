package consts.dbx;

import consts.cmn.CommonConstraint;

public class TableDataConstraint extends CommonConstraint {

	public static final String C_NAME = "Database Data";
	public static final String C_NAME_INDEX = C_NAME + " Index";
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	public static final String C_NAME_LIST = C_NAME + " List";
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	public static final String C_ICON = "/images/icon/data.jpg";

	public static final String C_TABLE = "DBX_TT_TABLE_DATA";
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	public static final String C_TABLE_COL_ID = "ID";
	public static final String C_TABLE_COL_TABLE_NAME = "TABLE_NAME";
	public static final String C_TABLE_COL_STR_COL01 = "STR_COL01";
	public static final String C_TABLE_COL_STR_COL02 = "STR_COL02";
	public static final String C_TABLE_COL_NUM_COL01 = "NUM_COL01";
	public static final String C_TABLE_COL_NUM_COL02 = "NUM_COL02";
	public static final String C_TABLE_COL_DAT_COL01 = "DAT_COL01";
	public static final String C_TABLE_COL_DAT_COL02 = "DAT_COL02";

	public static final String C_DATABASE_BASE = "/database";
	//public static final String C_DATABASE_INDEX = "/database/index";
	//public static final String C_DATABASE_LIST = "/database/list";
	//public static final String C_DATABASE_EDIT = "/database/edit";
	//public static final String C_DATABASE_CREATE = "/database/create";
	//public static final String C_DATABASE_DELETE = "/database/delete";

	public static final String C_LABEL_NAME = "データベース";
	public static final String C_LABEL_LIST_NAME= C_LABEL_NAME + CommonConstraint.C_LABEL_LIST;
	public static final String C_LABEL_DETAIL_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_DETAIL;
	public static final String C_LABEL_EDIT_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_EDIT;
	public static final String C_LABEL_CREATE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_CREATE;
	public static final String C_LABEL_SAVE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_SAVE;
	public static final String C_LABEL_DELETE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_DELETE;

	public static final String C_SESSION_TABLE_NAME = "session.tableName";
	public static final String C_SESSION_SEARCH_WORD = "session.searchWord";
	public static final String C_SESSION_DISPLAY_NAME = "session.displayName";
}