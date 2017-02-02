package forms.cmn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import consts.adm.AccountConstraint;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

/** エンティティ. */
@Entity(name = "Item")

/**
 *
 * 画面を初期化する.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 *
 */
public class CommonSearchForm extends SystemFusionForm {

	/** ID. */
	public String searchWord;

	/** ID. */
	public String accountName;

	/** コンストラクタ. */
	public CommonSearchForm() {
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param searchWord searchWord
	 *
	 * */
	public CommonSearchForm(final String searchWord) {
		this.searchWord = searchWord;
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
	 *
	 */
	public final List<ItemForm> getItem() {
		return null;
	}
}