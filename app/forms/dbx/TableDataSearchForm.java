package forms.dbx;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import play.data.validation.ValidationError;

@Entity(name = "Ittem")
/**
 *
 * @author kou1miyazaki
 *
 */
public class TableDataSearchForm extends SystemFusionForm {

	/** ID. */
	public String searchWord;

	/** コンストラクタ. */
	public TableDataSearchForm() {}

	/** コンストラクタ. */
	public TableDataSearchForm(String searchWord) {
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
			System.out.println("AccountForm#validate errors");
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