package controllers.cmn;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consts.cmn.CommonConstraint;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 * SystemFusionコントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public abstract class SystemFusionController extends Controller {

	/** ログ出力. */
	public static final Logger logger = LoggerFactory.getLogger(SystemFusionController.class);


	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void clear() {
		// セッションを初期化.
		Session.set(CommonConstraint.C_SESSION_ID, "");
		Session.set(CommonConstraint.C_SESSION_MODE, "");
		Session.set(CommonConstraint.C_SESSION_PAGE, "1");
	}

	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public abstract void init();

	/**
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result index();

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result search();

	/**
	 *
	 * 一覧画面を表示する.
	 *
	 * @param page ページ番号<br>
	 * @return	Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result list(Integer page);

	/**
	 *
	 * 詳細画面を表示する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result detail(Integer id);

	/**
	 *
	 * 編集画面を表示する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result edit(Integer id);

	/**
	 *
	 * 値を保存する.
	 *
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result save();

	/**
	 *
	 * 値を削除する.
	 *
	 * @param id ID<br>
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public abstract Result delete(Integer id);

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

