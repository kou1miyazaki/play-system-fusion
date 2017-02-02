package forms.adm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import consts.adm.AccountConstraint;
import forms.cmn.SystemFusionForm;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

public class PersonForm extends SystemFusionForm {

	public String id = "";
	public String personId = "";
	public String password = "";
	public String personName = "";
	public String emailAddress = "";

	/** コンストラクタ. */
	public PersonForm() {
	}

	/** コンストラクタ. */
	public PersonForm(String id, String personId, String password, String personName, String emailAddress) {
		this.id = id;
		this.personId = personId;
		this.password = password;
		this.personName = personName;
		this.emailAddress = emailAddress;
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List<ValidationError>
	 *
	 */
	public List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		// タイプチェック.
		if (StringUtils.isEmpty(personId)) {
			errors.add(new ValidationError("personId", Messages.get("bookmark.bookmarkType.required")));
		}

		// エラー表示.
		if (errors.size() > 0) {
			// ログ出力.
			StackTraceElement ste = new Throwable().getStackTrace()[0];
			LogModel.logging(ste, AccountConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
			return errors;
		}

		return null;
	}
}
