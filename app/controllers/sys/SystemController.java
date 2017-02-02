package controllers.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consts.cmn.CommonConstraint;
import consts.sys.LogConstraint;
import models.cmn.SessionModel;
import models.sys.LogModel;
import models.sys.SetupModel;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.cmn.Session;

public class SystemController extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	/**
	  * 画面を初期化する.
	  *
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	  */
	public void init() {
		// セッションを初期化.
		Session.set(LogConstraint.C_SESSION_ID,"");
		Session.set(LogConstraint.C_SESSION_SEARCH_WORD,"");

		// タブ設定.
		Session.set(CommonConstraint.C_SESSION_TAB, "sys1");
		Session.set(CommonConstraint.C_SESSION_URL, "");

		SetupModel.init();
	}

	/**
	 *
	 *
	 * @param
	 * @return
	 */
	public Result sessionList() {

		// ログ出力.
		LogModel.logging(new Throwable().getStackTrace()[0], CommonConstraint.C_SYSTEM_APPL_CMN, "セッション" , "");

		// セッション取得
		List<SessionModel> cache = SessionModel.getList();

		String session = Http.Context.current().session().toString();

		return ok(views.html.sys.sessionList.render("セッション一覧", cache, session));

	}

	/**
	 *
	 *
	 * @param
	 * @return
	 */
	public Result setup() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "セットアップ" , "");

		SetupModel.init();

		return redirect(controllers.cmn.routes.HomeController.index("index", "home"));
	}
}