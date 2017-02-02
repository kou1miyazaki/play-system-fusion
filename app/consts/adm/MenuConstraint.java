package consts.adm;

import java.util.Arrays;
import java.util.List;

import consts.cmn.CommonConstraint;

/**
 *
 * メニュー・コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class MenuConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Menu";

	/** ラベル. */
	public static final String C_LABEL_NAME = "メニュー";
	/** ラベル:一覧. */
	public static final String C_LABEL_LIST_NAME = C_LABEL_NAME + "一覧";
	/** ラベル:詳細. */
	public static final String C_LABEL_DETAIL_NAME = C_LABEL_NAME + "詳細";
	/** ラベル:編集. */
	public static final String C_LABEL_EDIT_NAME = C_LABEL_NAME + "編集";
	/** ラベル:保存. */
	public static final String C_LABEL_SAVE_NAME = C_LABEL_NAME + "保存";
	/** ラベル:削除. */
	public static final String C_LABEL_DELETE_NAME = C_LABEL_NAME + "削除";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/admin.png";

	/** テーブル名. */
	public static final String C_TABLE = "CMN_TT_MENU";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:親ID. */
	public static final String C_TABLE_COL_PARENT_ID = "PARENT_ID";
	/** カラム:タイプ. */
	public static final String C_TABLE_COL_MENU_TYPE = "MENU_TYPE";
	/** カラム:アクション. */
	public static final String C_TABLE_COL_ACTION_TYPE = "ACTION_TYPE";
	/** カラム:名前. */
	public static final String C_TABLE_COL_NAME = "NAME";
	/** カラム:URL. */
	public static final String C_TABLE_COL_URL = "URL";
	/** カラム:アイコン. */
	public static final String C_TABLE_COL_IMAGE_ICON = "IMAGE_ICON";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("id", "parentId", "menuType", "actionType", "name", "url", "imageIcon");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ID", "親ID", "タイプ", "アクション", "名前", "URL", "アイコン");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "text", "text", "text", "url", "image");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col1", "col2", "col1", "col2", "col1", "col2", "col1");

	/** URL. */
	public static final String C_URL_BASE = "/adm/menu";
	/** URL:インデックス. */
	public static final String C_URL_INDEX = C_URL_BASE + "/index";
	/** URL:検索. */
	public static final String C_URL_SEARCH = C_URL_BASE + "/search";
	/** URL:詳細. */
	public static final String C_URL_DETAIL = C_URL_BASE + "/detail";
	/** URL:一覧. */
	public static final String C_URL_LIST = C_URL_BASE + "/list";
	/** URL:編集. */
	public static final String C_URL_EDIT = C_URL_BASE + "/edit";
	/** URL:保存. */
	public static final String C_URL_SAVE = C_URL_BASE + "/save";
	/** URL:作成. */
	public static final String C_URL_CREATE = C_URL_BASE + "/create";
	/** URL:削除. */
	public static final String C_URL_DELETE = C_URL_BASE + "/delete";

	/** セッション:検索ワード. */
	public static final String C_SESSION_SEARCH_WORD = "session.searchWord";
}
