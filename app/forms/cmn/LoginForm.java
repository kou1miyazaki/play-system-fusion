package forms.cmn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import consts.adm.AccountConstraint;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

/**
 *
 * アカウント・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = "Login")
public class LoginForm extends SystemFusionForm {

	/** アカウントコード. */
	public String accountId;

	/** パスワード. */
	public String password;

	/** コンストラクタ. */
	public LoginForm() {}

	/** コンストラクタ. */
	public LoginForm(String accountId, String password) {
		this.accountId = accountId;
		this.password = password;
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List
	 *
	 */
	public final List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		//if (accountId == null || accountId.length() == 0) {
		//	errors.add(new ValidationError("accountId", Messages.get("actor.name.required")));
		//}

		//if (password == null || password.length() == 0) {
		//	errors.add(new ValidationError("password", Messages.get("actor.name.required")));
		//}

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
	 *
	 * 詳細タイトルを取得する.
	 *
	 * @return List<ItemForm>
	 *
	 */
	public final List<ItemForm> getItem() {
		return null;
	}
}