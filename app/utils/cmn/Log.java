package utils.cmn;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 一覧画面を表示する.
 *
 * @since		2016/11/21
 * @author	kou1miyazaki
 *
 */
public class Log extends SystemFusionUtils {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(Log.class);

	/** コンストラクタ. */
	public Log() {
	};

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 */
	public final void info() {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(DateParser.dateToString(new Date()) + " : " + throwableStackTraceElement.getMethodName());

	}
}
