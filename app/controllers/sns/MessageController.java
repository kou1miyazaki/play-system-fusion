package controllers.sns;

import java.util.List;

import com.avaje.ebean.Ebean;

import consts.adm.AccountConstraint;
import consts.cmn.CommonConstraint;
import forms.sns.MessageForm;
import models.sns.MessageModel;
import models.sys.LogModel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.cmn.Session;

public class MessageController extends Controller {

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
		List<MessageModel> model = MessageModel.getList();

		for(int i = 0; i < model.size(); ++i){
			model.get(i).message.replaceAll("\n", "<BR>");
		}
		// ホーム表示.
		return ok(views.html.sns.messageList.render("Message List", model));
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
		LogModel.logging(ste, CommonConstraint.C_SYSTEM_APPL_ADM, AccountConstraint.C_LABEL_SAVE_NAME, "");

		// リクエストを取得.
		Form<MessageForm> formData = Form.form(MessageForm.class).bindFromRequest();

		// 更新処理.
		MessageModel model = MessageModel.convertToModel(formData.get());

		Ebean.execute(()->{
			model.accountId = Session.get(CommonConstraint.C_SESSION_USER_NAME);
			model.message = model.message.replaceAll("\n", "<BR>");
			model.save();
		});

		// 一覧表示画面を表示する.(リダイレクト)
		//return redirect(controllers.adm.routes.AccountController.list(session("pAccountType")));
		return redirect("/message/index");
	}

}