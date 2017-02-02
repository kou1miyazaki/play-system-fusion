package controllers.sns;

import java.util.List;

import consts.adm.AccountConstraint;
import consts.cmn.CommonConstraint;
import models.sns.TimelineModel;
import models.sys.LogModel;
import play.mvc.Controller;
import play.mvc.Result;
import utils.cmn.Session;

public class TimelineController extends Controller {

	/**
	 *
	 *
	 * @param
	 * @return
	 */
	public Result index() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "タイムライン" , "");

		// リスト取得.
		List<TimelineModel> model = TimelineModel.getList();

		// ホーム表示.
		return ok(views.html.sns.timelineList.render("Message List", model));
	}

	/**
	 * 値を保存する.
	 *
	 * @param id
	 * @return
	 */
	public Result send() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, AccountConstraint.C_LABEL_SAVE_NAME, "");

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.AccountController.list(session("pAccountType")));
		return redirect(AccountConstraint.C_URL_LIST + "?searchWord=" + Session.get(AccountConstraint.C_SESSION_SEARCH_WORD));
	}
}