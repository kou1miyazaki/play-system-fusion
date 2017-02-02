package forms.sns;

import java.util.ArrayList;
import java.util.List;

import consts.adm.AccountConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

/**
*
* @author kou1miyazaki
*
*/
public class MessageForm extends SystemFusionForm {

	public String id = "";
	public String accountId = "";
	public String message = "";

	/**
	 *
	 * @author kou1miyazaki
	 *
	 */
	public MessageForm() {
	}

	/**
	 *
	 * @author kou1miyazaki
	 *
	 */
	public MessageForm(String id, String accountId, String message) {
		this.id = id;
		this.accountId = accountId;
		this.message = message;
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List<ValidationError>
	 *
	 */
	public final List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		// エラー表示.
		if (errors.size() > 0) {
			// ログ出力.
			StackTraceElement ste = new Throwable().getStackTrace()[0];
			LogModel.logging(ste, AccountConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
			return errors;
		}

		return null;
	}

	/**
	 * 詳細タイトルを取得する.
	 *
	 * @return List<ItemForm>
	 */
	public List<ItemForm> getItem() {
		return null;
	}
}
