package controllers.cmn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consts.adm.BookmarkConstraint;
import consts.cmn.CommonConstraint;
import models.adm.MenuModel;
import models.adm.PortalModel;
import models.adm.PortletModel;
import models.adm.PortletParameterModel;
import models.cmn.SessionModel;
import models.sys.LogModel;
import play.cache.Cache;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import utils.cmn.Session;

/**
 *
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class HomeController extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	/**
	 *
	 * 画面を初期化する.
	 *
	 */
	public final void init() {

		Session.set(CommonConstraint.C_SESSION_TAB, "");
		Session.set(CommonConstraint.C_SESSION_URL, "");

		// セッション処理.

		//userName = Session.get(CommonConstraint.C_SESSION_USER_NAME);
		//if(userName ==null ) {
		//	Session.set(CommonConstraint.C_SESSION_USER_NAME, "miyazaki");
		//}

		// メニュー処理.
		Cache.set("session.menu1", MenuModel.getList("MENU1"));
		Cache.set("session.menu2", MenuModel.getList("MENU2"));

		//List<MenuModel> aaa = (List<MenuModel>) Cache.get("session.menu1");

	}

	/**
	 *
	 *
	 * @return Result
	 *
	 */
	public final Result index() {
		return index("home", "index");
	}

	/**
	 *
	 *
	 * @param portalType portalType
	 * @param portalTabType portalTabType
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result index(final String portalType, final String portalTabType) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "ホーム", "");

		// 初期化.
		init();

		// リスト取得.
		PortalModel model = (portalTabType == null || portalTabType == null)
				? PortalModel.getPortal("index", "home") : PortalModel.getPortal(portalType, portalTabType);

		// リスト取得.
		List<PortalModel> portal = (portalType == null || portalType == "")
				? PortalModel.getList() : PortalModel.getList(portalType);

		// リスト取得.
		List<PortletModel> portlet = (portalTabType == null || portalTabType == "")
				? PortletModel.getList() : PortletModel.getList(portalType, portalTabType);

		for (int i = 0; i < portlet.size(); ++i) {
			if (portlet.size() != 0) {
				portlet.get(i).parameter = PortletParameterModel.getMap(portlet.get(i).id);
			}
		}

		// ホーム表示.
		switch (model.layoutType) {
			case "default":
				return ok(views.html.portalDefault.render("", portalType, portalTabType, portal, portlet));
			case "2aLayout":
				return ok(views.html.portal2aLayout.render("", portalType, portalTabType, portal, portlet));
			case "2bLayout":
				return ok(views.html.portalDefault.render("", portalType, portalTabType, portal, portlet));
			default:
				return ok(views.html.portalDefault.render("", portalType, portalTabType, portal, portlet));
		}
	}



	/**
	 *
	 * @param url URL<br>
	 * @return Result
	 *
	 */
	public final Result url(final String url) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "ブックマーク", url);

		// タブ設定.
		Session.set(CommonConstraint.C_SESSION_URL, "");
		Session.set(CommonConstraint.C_SESSION_TAB, "");

	    return ok(views.html.cmn.iFrameFull.render(BookmarkConstraint.C_NAME + " Display", url));
	}

	/**
	 *
	 *
	 * @param tabType タブタイプ<br>
	 * @return Result
	 *
	 */
	public final Result tab(final String tabType) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "サブメニュー", "");

		// リスト取得.
		List<MenuModel> menu = MenuModel.getList(tabType);

		if (menu.size() == 0) {
			flash("error", Messages.get("common.validation.error"));
			return badRequest(views.html.error.render());
		}

		// タブ設定.
		Session.set(CommonConstraint.C_SESSION_TAB, tabType);
		Session.set(CommonConstraint.C_SESSION_URL, menu.get(0).url);

		if (menu.get(0).actionType.equals("iframe")) {
			return redirect("/urltab?url=" + menu.get(0).url + "&tabType=" + tabType);
		}
		return redirect(menu.get(0).url);
	}

	/**
	 *
	 * @param url URL<br>
	 * @param tabType タブタイプ<br>
	 * @return Result
	 *
	 */
	public final Result tabUrl(final String url, final String tabType) {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "ブックマーク", url);

		// タブ設定.
		if (url.isEmpty()) {
			Session.set(CommonConstraint.C_SESSION_URL, "");
		} else {
			Session.set(CommonConstraint.C_SESSION_URL, url);
		}
		if (tabType.isEmpty()) {
			Session.set(CommonConstraint.C_SESSION_TAB, "");
		} else {
			Session.set(CommonConstraint.C_SESSION_TAB, tabType);
		}

		// エラー処理.
		if (url == null || url == "") {
			return badRequest();
		}

	    return ok(views.html.cmn.iFrameFull.render(BookmarkConstraint.C_NAME + " Display", url));
	}

	/**
	 *
	 *
	 * @return Result
	 *
	 */
	@Authenticated(AuthenticationAction.class)
	public final Result profile() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "プロファイル", "");

		List<SessionModel> model = SessionModel.getList();

		return ok(views.html.cmn.profileDetail.render("Session List", model));

	}
}