package utils.cmn;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * SystemFusionユーティリティ.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public abstract class SystemFusionUtils {

	/** ログ出力. */
	public static final Logger logger = LoggerFactory.getLogger(SystemFusionUtils.class);

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
		sb.append("Class: " + this.getClass().getCanonicalName() + "\n");
		sb.append("Settings:\n");
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
