package utils.cmn;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * セッション値を取得する.
 *
 * @since		2016/11/21
 * @author	kou1miyazaki
 *
 */
public class Cookie extends SystemFusionUtils {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(Cookie.class);

	/**
	 * セッション値を取得する.
	 *
	 * @return	String
	 *
	 */
	public static String get(final String cookieName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		return (String) Session.get(cookieName);
	}

	/**
	 * セッション値を保存する.
	 *
	 */
	public static void set(final String cookieName, final Object cookienObject) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Session.set(cookieName, cookienObject);
	}

	/**
	 * セッション値を保存する.
	 *
	 * @return	Result
	 * @since		2016/11/21
	 * @author	kou1miyazaki
	 * @return
	 */
	public static void set(final String cookieName, final String cookieValue) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Session.set(cookieName, cookieValue);
	}


	/**
	 *
	 * セッション値を削除する.
	 *
	 *
	 */
	public static void remove(final String cookieName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Session.remove(cookieName);
	}


}
