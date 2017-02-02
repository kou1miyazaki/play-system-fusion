package consts.cmn;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 *
 * SystemFusionコンスト.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class CommonConstraint {

	/** 名前. */
	public static final String C_SYSTEM_NAME = "SystemFusion";

	/** CMN. */
	public static final String C_SYSTEM_APPL_CMN = "CMN";
	/** ADM. */
	public static final String C_SYSTEM_APPL_ADM = "ADM";
	/** SNS. */
	public static final String C_SYSTEM_APPL_SNS = "SNS";
	/** DBX. */
	public static final String C_SYSTEM_APPL_DBX = "DBX";
	/** SYS. */
	public static final String C_SYSTEM_APPL_SYS = "SYS";

	/** セッション・タイムアウト(sec). */
	public static final int C_SESSION_TIMEOUT = 60 * 30;

	/** パターン. */
	public static final String C_DATE_PATTERN = "yyyy/MM/dd";
	/** パターン. */
	public static final String C_DATETIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

	/** 自動採番. */
	public static final String C_SYSTEM_LABEL_AUTOINC = "(自動採番)";

	/** サイズ:ページ数. */
	public static final Integer C_SIZE_PAGE = 10;
	/** サイズ:アロケーション. */
	public static final int C_SIZE_ALLOCATION = 25;
	/** サイズ:アイコン中. */
	public static final String C_SIZE_ICON_XS = "16";
	/** サイズ:アイコン小. */
	public static final String C_SIZE_ICON_SS = "25";
	/** サイズ:アイコン小. */
	public static final String C_SIZE_ICON_SM = "32";

	/** カラム:ID. */
	public static final String C_TABLE_COL_ID = "ID";
	/** カラム:ノート. */
	public static final String C_TABLE_COL_NOTE = "NOTE";
	/** カラム:作成者. */
	public static final String C_TABLE_COL_CREATE_USER = "CREATE_USER";
	/** カラム:作成日. */
	public static final String C_TABLE_COL_CREATE_DATE = "CREATE_DATE";
	/** カラム:最終更新者. */
	public static final String C_TABLE_COL_UPDATE_USER = "UPDATE_USER";
	/** カラム:最終更新日. */
	public static final String C_TABLE_COL_UPDATE_DATE = "UPDATE_DATE";

	/** アイテム:ID. */
	public static final List<String> C_ITEM_DETAIL =
			(List<String>) Arrays.asList("note", "createUser", "createDate", "updateUser", "updateDate");
	/** アイテム:ラベル. */
	public static final List<String> C_ITEM_LABEL =
			(List<String>) Arrays.asList("ノート", "作成者", "作成日", "最終更新者", "最終更新日");
	/** アイテム:タイプ. */
	public static final List<String> C_ITEM_TYPE =
			(List<String>) Arrays.asList("text", "text", "text", "text", "text");
	/** アイテム:表示. */
	public static final List<String> C_ITEM_DISPLAY =
			(List<String>) Arrays.asList("col2", "col1", "col1", "col1", "col1");

	/** ラベル:一覧. */
	public static final String C_LABEL_LIST = "一覧";
	/** ラベル:詳細. */
	public static final String C_LABEL_DETAIL = "詳細";
	/** ラベル:編集. */
	public static final String C_LABEL_EDIT = "編集";
	/** ラベル:追加. */
	public static final String C_LABEL_CREATE = "追加";
	/** ラベル:保存. */
	public static final String C_LABEL_SAVE = "保存";
	/** ラベル:削除. */
	public static final String C_LABEL_DELETE = "削除";

	/** テーマ. */
	public static final String C_THEMA_CLASS = "success";

	/** セッション:UUID. */
	public static final String C_SESSION_UUID = "session.uuid";
	/** セッション:ユーザID. */
	public static final String C_SESSION_USER_NAME = "session.accountId";
	/** セッション:URL. */
	public static final String C_SESSION_URL = "session.url";
	/** セッション:タブ. */
	public static final String C_SESSION_TAB = "session.tab";
	/** セッション:ページ数. */
	public static final String C_SESSION_PAGE = "session.page";
	/** セッション:ID. */
	public static final String C_SESSION_ID = "session.id";
	/** セッション:モード. */
	public static final String C_SESSION_MODE = "session.mode";

	/**
	 *
	 * 文字列へ変換.
	 *
	 * @return String
	 *
	 */
	public final List<String> toList() {
		return null;
	}

	/**
	 *
	 * 文字列へ変換.
	 *
	 * @return String
	 *
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				sb.append(field.getName() + " = " + field.get(this) + "\n");
			} catch (IllegalAccessException e) {
				sb.append(field.getName() + " = " + "access denied\n");
			}
		}
		return sb.toString();
	}
}
