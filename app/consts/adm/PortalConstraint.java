package consts.adm;

import java.util.Arrays;
import java.util.List;

import consts.cmn.CommonConstraint;

/**
 *
 * ポータル・コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class PortalConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Portal";
	/** 名前:詳細. */
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	/** 名前:一覧. */
	public static final String C_NAME_LIST = C_NAME + " List";
	/** 名前:編集. */
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/admin.png";

	/** テーブル名. */
	public static final String C_TABLE = "CMN_TT_PORTAL";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:タイプ. */
	public static final String C_TABLE_COL_PORTAL_TYPE = "PORTAL_TYPE";
	/** カラム:タブ. */
	public static final String C_TABLE_COL_PORTAL_TAB_TYPE = "PORTAL_TAB_TYPE";
	/** カラム:ポータル名. */
	public static final String C_TABLE_COL_PORTAL_NAME = "PORTAL_NAME";
	/** カラム:URL. */
	public static final String C_TABLE_COL_URL = "URL";
	/** カラム:アイコン. */
	public static final String C_TABLE_COL_IMAGE_ICON = "IMAGE_ICON";
	/** カラム:レイアウト. */
	public static final String C_TABLE_COL_LAYOUT_TYPE = "LAYOUT_TYPE";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("id", "portalType", "portalTabType", "portalName", "url", "imageIcon", "layoutType");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ID", "タイプ", "タブ", "ポータル名", "URL", "アイコン", "レイアウト");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "text", "text", "url", "image", "text");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col1", "col2", "col1", "col2", "col1", "col2", "col1");

	/** URL. */
	public static final String C_URL_BASE = "/adm/portal";
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

	/** ラベル. */
	public static final String C_LABEL_NAME = "ポータル";
	/** ラベル:一覧. */
	public static final String C_LABEL_LIST_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_LIST;
	/** ラベル:詳細. */
	public static final String C_LABEL_DETAIL_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_DETAIL;
	/** ラベル:編集. */
	public static final String C_LABEL_EDIT_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_EDIT;
	/** ラベル:作成. */
	public static final String C_LABEL_CREATE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_CREATE;
	/** ラベル:保存. */
	public static final String C_LABEL_SAVE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_SAVE;
	/** ラベル:削除. */
	public static final String C_LABEL_DELETE_NAME = C_LABEL_NAME + CommonConstraint.C_LABEL_DELETE;

	/** セッション:検索ワード. */
	public static final String C_SESSION_SEARCH_WORD = "session.searchWord";
}