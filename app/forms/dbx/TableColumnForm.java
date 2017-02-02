package forms.dbx;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import consts.dbx.TableColumnConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.dbx.TableColumnModel;
import play.data.validation.ValidationError;

/**
 *
 * テーブル・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class TableColumnForm extends SystemFusionForm {

	public String id = "";
	public String tableName = "";
	public String tableColName = "";
	public String columnName = "";
	public String displayType = "";
	public String displayName = "";
	public String columnType = "";
	public String columnLength = "";
	public String listFlg = "";
	public String listOrder = "";
	public String searchFlg = "";
	public String searchOrder = "";

	/** コンストラクタ. */
	public TableColumnForm() {
	}

	/** コンストラクタ. */
	public TableColumnForm(String id, String tableName, String tableColName, String columnName,
			String displayType, String displayName, String columnType, String columnLength, String listFlg,
			String listOrder, String searchFlg, String searchOrder) {
		this.id = id;
		this.tableName = tableName;
		this.tableColName = tableColName;
		this.columnName = columnName;
		this.displayType = displayType;
		this.displayName = displayName;
		this.columnType = columnType;
		this.columnLength = columnLength;
		this.listFlg = listFlg;
		this.listOrder = listOrder;
		this.searchFlg = searchFlg;
		this.searchOrder = searchOrder;
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return TableColumnModel
	 *
	 */
	public final TableColumnModel getModel() {
		TableColumnModel model = new TableColumnModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.tableName = this.tableName;
		model.tableColName = this.tableColName;
		model.columnName = this.columnName;
		model.displayType = this.displayType;
		model.displayName = this.displayName;
		model.columnType = this.columnType;
		model.columnLength = StringUtils.isNotEmpty(this.columnLength) ? Integer.valueOf(this.columnLength).intValue() : null;
		//model.listFlg = this.listFlg;
		model.listOrder = StringUtils.isNotEmpty(this.listOrder) ? Integer.valueOf(this.listOrder).intValue() : null;
		//model.searchFlg = this.searchFlg;
		model.searchOrder = StringUtils.isNotEmpty(this.searchOrder) ? Integer.valueOf(this.searchOrder).intValue() : null;

		return model;
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
			System.out.println("BookmarkForm#validate errors");
			return errors;
		}

		return null;
	}

	/**
	 *
	 * @return List
	 *
	 */
	public final List<ItemForm> getItem() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		item.add(new ItemForm("id", "text", "col1", TableColumnConstraint.C_LABEL_COL_ID));
		item.add(new ItemForm("tableName", "text", "col1", TableColumnConstraint.C_LABEL_COL_TABLE_NAME));
		item.add(new ItemForm("columnName", "text", "col1", TableColumnConstraint.C_LABEL_COL_COLUMN_NAME));
		item.add(new ItemForm("displayName", "text", "col1", TableColumnConstraint.C_LABEL_COL_DISPLAY_NAME));

		return item;
	}
}
