package forms.dbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.TableColumnModel;

import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import play.data.validation.ValidationError;

/**
 *
 * @author kou1miyazaki
 *
 */
public class DatabaseForm extends SystemFusionForm {

	/** 列. */
	public List<TableColumnModel> rowHead;

	/** 列. */
	public List<HashMap<String, String>> rowData;

	/** 列. */
	public List<ItemForm> listItem;

	/** コンストラクタ. */
	public DatabaseForm(List<TableColumnModel> rowHead, List<HashMap<String,String>> rowData, List<ItemForm> listItem) {
		this.rowHead = rowHead;
		this.rowData = rowData;
		this.listItem = listItem;
	}

	/** コンストラクタ. */
	public DatabaseForm(List<TableColumnModel> rowHead, List<HashMap<String,String>> rowData) {
		this.rowHead = rowHead;
		this.rowData = rowData;
	}

	/** コンストラクタ */
//	public DatabaseForm(List<ItemForm> listItem) {
//		this.listItem = listItem;
//	}

	/** コンストラクタ. */
	public DatabaseForm() {
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
			System.out.println("BookmarkForm#validate errors");
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