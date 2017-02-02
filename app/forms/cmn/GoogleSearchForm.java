package forms.cmn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import consts.adm.AccountConstraint;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

@Entity(name = "GoogleSearch")
/**
 *
 * @author kou1miyazaki
 *
 */
public class GoogleSearchForm extends SystemFusionForm {

	/** アカウントコード */
	public String q;

	/** アカウントコード */
	public String googleType;

	/** アカウントコード */
	public String pdf;

	/** アカウントコード */
	public String ja;

	/** コンストラクタ. */
	public GoogleSearchForm() {}

	/** コンストラクタ. */
	public GoogleSearchForm(String q, String googleType) {
		this.q = q;
		this.googleType = googleType;
	}

	/** コンストラクタ */
	public GoogleSearchForm(String q, String googleType, String pdf, String ja) {
		this.q = q;
		this.googleType = googleType;
		this.pdf = pdf;
		this.ja = ja;
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