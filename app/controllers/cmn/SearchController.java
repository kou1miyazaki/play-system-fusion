package controllers.cmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consts.cmn.CommonConstraint;
import forms.cmn.GoogleSearchForm;
import models.sys.LogModel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 *
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class SearchController extends Controller {

	/** ログ出力. */
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	/**
	 *
	 *
	 * @return Result
	 *
	 */
	public final Result index() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "Google検索", "");

		// ホーム表示.
		return ok(views.html.cmn.search.render("Search"));
	}

	/**
	 *
	 * 値を保存する.
	 *
	 * @return Result
	 *
	 */
	public final Result search() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_CMN, "Google検索", "");

		// リクエストを取得.
		Form<GoogleSearchForm> formData = Form.form(GoogleSearchForm.class).bindFromRequest();

		String url = "https://www.google.co.jp/";
		String query = formData.get().q;

		if (formData.get().googleType.equals("search")) {
			if (!formData.get().pdf.isEmpty()) {
				query = "filetype:pdf+" + query;
			}
			url += "?#q=" + query;
			if (!formData.get().ja.isEmpty()) {
				url += "&tbs=lr:lang_1ja&lr=lang_ja";
			}
		} else if (formData.get().googleType.equals("news")) {
			url += "?#q=" + query + "&tbm=nws";
		} else if (formData.get().googleType.equals("picture")) {
			url += "?#q=" + query + "&tbm=isch";
		} else if (formData.get().googleType.equals("video")) {
			url += "?#q=" + query + "&tbm=vid";
		} else if (formData.get().googleType.equals("book")) {
			url += "?#q=" + query + "&tbm=bks";
		} else if (formData.get().googleType.equals("slideshare")) {
			url = "http://www.slideshare.net/search/slideshow?searchfrom=header&q=" + query;
		}

		// 一覧表示画面を表示する.(リダイレクト)
		return redirect(url);
	}
}