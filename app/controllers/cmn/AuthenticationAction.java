package controllers.cmn;

import consts.cmn.CommonConstraint;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

/**
 *
 * 認証・コントローラー.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class AuthenticationAction extends Authenticator {
	@Override
	public final String getUsername(final Context ctx) {
		return ctx.session().get(CommonConstraint.C_SESSION_USER_NAME);
	}

	@Override
	public final Result onUnauthorized(final Context ctx) {
		String returnUrl = ctx.request().uri();
		if (returnUrl == null) {
			returnUrl = "/portal/index/home";
		}
		ctx.session().put(CommonConstraint.C_SESSION_URL, returnUrl);

		System.out.println("事前処理を行います。");
		return redirect(controllers.cmn.routes.LoginController.login());
	}

}