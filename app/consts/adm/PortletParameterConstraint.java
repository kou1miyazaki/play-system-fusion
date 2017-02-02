package consts.adm;

import java.util.Arrays;
import java.util.List;

import consts.cmn.CommonConstraint;

/**
 *
 * ポートレット・パラメーター・コンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class PortletParameterConstraint extends CommonConstraint {

	/** 名前. */
	public static final String C_NAME = "Portlet Parameter";
	/** 名前:詳細. */
	public static final String C_NAME_DETAIL = C_NAME + " Detail";
	/** 名前:一覧. */
	public static final String C_NAME_LIST = C_NAME + " List";
	/** 名前:編集. */
	public static final String C_NAME_EDIT = C_NAME + " Edit";

	/** アイコン. */
	public static final String C_ICON = "/images/icon/admin.png";

	/** テーブル名. */
	public static final String C_TABLE = "CMN_TT_PORTLET_PARAMETER";
	/** シーケンス名. */
	public static final String C_TABLE_SEQ = C_TABLE  + "_SEQ";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:ポートレットID. */
	public static final String C_TABLE_COL_PORTLET_ID = "PORTLET_ID";
	/** カラム:名前. */
	public static final String C_TABLE_COL_NAME = "NAME";
	/** カラム:値. */
	public static final String C_TABLE_COL_VALUE = "VALUE";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("id", "portletId", "name", "value");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ID", "ポートレットID", "名前", "値");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "text", "text");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col1", "col2", "col1", "col2");

	/** URL. */
	public static final String C_URL_BASE = "/adm/parameter";
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
	public static final String C_LABEL_NAME = "ポートレット変数";
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