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
 *
 * @author kou1miyazaki
 * @since 2017//01/07
 * @version 1.0
 *
 */
public class TimelineForm extends SystemFusionForm {

	public String id = "";
	public String accountId = "";
	public Integer parentId = 0;
	public String message = "";

	/**
	 *
	 * @author kou1miyazaki
	 *
	 */
	public TimelineForm() {
	}

	/**
	 *
	 * @author kou1miyazaki
	 *
	 */
	public TimelineForm(String id, String accountId, Integer parentId, String message) {
		this.id = id;
		this.accountId = accountId;
		this.parentId = parentId;
		this.message = message;
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
	 * @return List
	 *
	 */
	public final List<ItemForm> getItem() {
		return null;
	}
}
