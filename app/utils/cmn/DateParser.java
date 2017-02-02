package utils.cmn;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import consts.cmn.CommonConstraint;

/**
 *
 * 一覧画面を表示する.
 *
 * @since		2016/11/21
 * @author	kou1miyazaki
 */
public class DateParser extends SystemFusionUtils {

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @return	Date
	 *
	 */
	public static Date stringToDate(final String str) {
		LocalDate ld = LocalDate.parse(str, DateTimeFormatter.ofPattern(CommonConstraint.C_DATE_PATTERN));
		Instant i = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(i);
	}

	/**
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 * @since		2016/11/21
	 * @author	kou1miyazaki
	 */
	public static Date stringToDatetime(final String str) {
		LocalDate ld = LocalDate.parse(str, DateTimeFormatter.ofPattern(CommonConstraint.C_DATETIME_PATTERN));
		Instant i = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(i);
	}

	/**
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	public static String dateToString(final Date date) {
		if (date == null) {
			return "---";
		}
		Instant i = date.toInstant();
		LocalDate ld = LocalDateTime.ofInstant(i, ZoneId.systemDefault()).toLocalDate();
		return ld.format(DateTimeFormatter.ofPattern(CommonConstraint.C_DATE_PATTERN));
	}

	/**
	 * 一覧画面を表示する.
	 *
	 * @return	String
	 *
	 */
	public static String datetimeToString(final Date date) {
		if (date == null) {
			return "---";
		}
		//Instant i = date.toInstant();
		//LocalDate ld = LocalDateTime.ofInstant(i, ZoneId.systemDefault()).toLocalDate();
		//return ld.format(DateTimeFormatter.ofPattern(CommonConstraint.C_DATETIME_PATTERN));
		return new SimpleDateFormat(CommonConstraint.C_DATETIME_PATTERN).format(date);
	}
}

