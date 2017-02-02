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
 * アイテム・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = "Item")
public class ItemForm extends SystemFusionForm {

	/** ID. */
	public String id;

	/** 項目タイプ. */
	public String itemType;

	/** 表示タイプ. */
	public String displayType;

	/** 名称. */
	public String name;

	/** 値. */
	public String value;

	/** コンストラクタ. */
	public ItemForm() {
	}

	/** コンストラクタ. */
	public ItemForm(String id, String itemType, String displayType, String name) {
		this.id = id;
		this.itemType = itemType;
		this.displayType = displayType;
		this.name = name;
	}

	/** コンストラクタ. */
	public ItemForm(String id, String itemType, String displayType, String name, String value) {
		this.id = id;
		this.itemType = itemType;
		this.displayType = displayType;
		this.name = name;
		this.value = value;
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