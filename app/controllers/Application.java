package controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consts.cmn.CommonConstraint;
import controllers.adm.AccountController;
import models.sys.SetupModel;
import play.mvc.Controller;
import play.mvc.Result;
import utils.cmn.DateParser;
import utils.cmn.Session;

public class Application extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	  * 一覧画面を表示する.
	  *
	  * @return	Result
	  * @since		2016/11/21
	  * @author	kou1miyazaki
	  */
	public Result index() {

		// ログ出力.
		StackTraceElement throwableStackTraceElement = new Throwable().getStackTrace()[0];
		logger.info(DateParser.datetimeToString(new Date()) +" : " + throwableStackTraceElement.getMethodName());

		init();

		logger.info("Application#index");
		return ok();
	}

	public void init() {

		// セッション処理.
		String uuid= Session.get("uuid");
		//if(uuid ==null ) {
			Session.set("uuid", java.util.UUID.randomUUID().toString());
		//}
		logger.debug("UserID:" + uuid);

		String userName= Session.get(CommonConstraint.C_SESSION_USER_NAME);
		//if(userName ==null ) {
			Session.set(CommonConstraint.C_SESSION_USER_NAME, "miyazaki");
		//}

		SetupModel.init();
	}

	public Result error500(Integer f) {
		if (f == 1) {
			throw new RuntimeException("Internal Server Error");
		}
		return ok();
	}
}
