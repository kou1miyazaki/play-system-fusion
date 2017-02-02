package utils.adm;

import utils.cmn.SystemFusionUtils;

public class BookmarkConstraint extends SystemFusionUtils {

	final public static String C_BOOKMARK = "Bookmark";

	final public static String C_BOOKMARK_TABLE = "CMN_TT_BOOKMARK";

	final public static String C_BOOKMARK_COL_ID = "ID";
	final public static String C_BOOKMARK_COL_PARENT_ID = "PARENT_ID";
	final public static String C_BOOKMARK_COL_BOOKMARK_TYPE = "BOOKMARK_TYPE";
	final public static String C_BOOKMARK_COL_NAME = "NAME";
	final public static String C_BOOKMARK_COL_URL = "URL";
	final public static String C_BOOKMARK_COL_IMAGE_ICON = "IMAGE_ICON";

	final public static String C_BOOKMARK_INDEX = "/bookmark/index";
	final public static String C_BOOKMARK_LIST = "/bookmark/list";
	final public static String C_BOOKMARK_EDIT = "/bookmark/edit";
	final public static String C_BOOKMARK_CREATE = "/bookmark/create";
	final public static String C_BOOKMARK_DELETE = "/bookmark/delete";

	final public static String C_BOOKMARK_SESSION_ID = "pId";
	final public static String C_BOOKMARK_SESSION_BOOKMARK_TYPE = "pBookmarkType";
	final public static String C_BOOKMARK_SESSION_URL = "pUrl";
	final public static String C_BOOKMARK_SESSION_MODE = "pMode";
}