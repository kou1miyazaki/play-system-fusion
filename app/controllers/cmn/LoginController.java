package controllers.cmn;

import consts.adm.AccountConstraint;
import consts.cmn.CommonConstraint;
import forms.cmn.LoginForm;
import models.adm.AccountModel;
import models.sys.LogModel;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import utils.cmn.Session;

/**
 *
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class LoginController extends Controller {

	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void init(String accountId) {

		String uuid = java.util.UUID.randomUUID().toString();

		// セッション保存.
		Session.set(CommonConstraint.C_SESSION_UUID, uuid);
		Session.set(CommonConstraint.C_SESSION_USER_NAME, accountId);

		Context ctx = Context.current();
		ctx.session().put(CommonConstraint.C_SESSION_UUID, uuid);
		ctx.session().put(CommonConstraint.C_SESSION_USER_NAME, accountId);
	}

	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void clear() {

		// セッション保存.
		Session.remove(CommonConstraint.C_SESSION_UUID);
		Session.remove(CommonConstraint.C_SESSION_USER_NAME);

		Context ctx = Context.current();
		ctx.session().remove(CommonConstraint.C_SESSION_UUID);
		ctx.session().remove(CommonConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 *
	 * @return Reult
	 *
	 */
	public final Result login() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "ログイン", "");

		// セッションクリア.
		clear();

		System.out.println(Session.get(CommonConstraint.C_SESSION_URL));

		return ok(views.html.cmn.login.render("ログイン", null));
	}

	/**
	 *
	 *
	 * @return Result
	 *
	 */
	public final Result auth() {

		// リクエストを取得.
		Form<LoginForm> formData = Form.form(LoginForm.class).bindFromRequest();

		// バリデーションエラー処理.
		if (formData.hasErrors()) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.cmn.login.render(AccountConstraint.C_NAME + " Retry", formData));
		}

		String accountId = formData.get().accountId;

		// ログイン処理.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		if (AccountModel.isLogin(accountId, formData.get().password)) {
			// セッション保存.
			init(accountId);
			System.out.println(Session.get(CommonConstraint.C_SESSION_UUID));
			// ログ出力.
			LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_ADM, "認証:成功", formData.toString());

			return redirect(Session.get(CommonConstraint.C_SESSION_URL));
		} else {
			// ログ出力.
			LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_ADM, "認証:失敗", formData.toString());

			return badRequest(views.html.cmn.login.render(AccountConstraint.C_NAME + " Retry", formData));
		}
	}

	/**
	 *
	 *
	 * @return Result
	 *
	 */
	public final Result logout() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "ログアウト", "");

		// セッション削除.
		clear();
		flash("success", "You've been logged out");

		Session.set(CommonConstraint.C_SESSION_URL, "/portal/index/home");

		return ok(views.html.cmn.login.render("", null));
	}
}