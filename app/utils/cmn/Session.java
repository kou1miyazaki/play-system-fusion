package utils.cmn;

import java.util.Date;

import play.cache.Cache;

/**
 * セッション値を取得する.
 *
 * @since		2016/11/21
 * @author	kou1miyazaki
 */
public class Session extends SystemFusionUtils {

	/** コンストラクタ. */
	public Session() {
	};

	/**
	 *
	 * セッション値を取得する.
	 *
	 * @param sessionName sessionName
	 * @return	String
	 *
	 */
	public static String get(final String sessionName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		return (String) Cache.get(sessionName);
	}

	/**
	 *
	 * セッション値を保存する.
	 *
	 *
	 */
	public static void set(final String sessionName,final Object sessionObject) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Cache.set(sessionName, sessionObject, 60 * 30);
	}

	/**
	  * セッション値を保存する.
	  *
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	  * @return
	  */
	public static void set(String sessionName,String sessionValue) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Cache.set(sessionName ,sessionValue, 60 * 30);
	}


	/**
	  * セッション値を削除する.
	  *
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	 * @return
	 * @return
	  */
	public static void remove(String sessionName) {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(new Date().toString() + " : " + throwableStackTraceElement.getMethodName());

		Cache.remove(sessionName);
	}
}
