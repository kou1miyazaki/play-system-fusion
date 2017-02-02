package forms.dbx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import consts.adm.AccountConstraint;
import consts.dbx.TableDataConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.dbx.TableColumnModel;
import models.dbx.TableDataModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;

public class TableDataForm extends SystemFusionForm {

	public String id = "";
	public String tableName = "";
	public String strCol01 = "";
	public String strCol02 = "";
	public String numCol01 = "";
	public String numCol02 = "";
	public String datCol01 = "";
	public String datCol02 = "";

	/** コンストラクタ. */
	public TableDataForm() {
	}

	/** コンストラクタ. */
	public TableDataForm(String id, String tableName, String strCol01, String strCol02, String numCol01, String numCol02, String datCol01, String datCol02) {
		this.id = id;
		this.tableName = tableName;
		this.strCol01 = strCol01;
		this.strCol02 = strCol02;
		this.strCol01 = numCol01;
		this.strCol02 = numCol02;
		this.strCol01 = datCol01;
		this.strCol02 = datCol02;
	}

	/**
	 *
	 * @throws ParseException
	 *
	 */
	public TableDataModel getModel() throws ParseException {
		TableDataModel model = new TableDataModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.tableName = this.tableName;
		model.strCol01 = this.strCol01;
		model.strCol02 = this.strCol02;
		model.numCol01 = Integer.parseInt(this.numCol01);
		model.numCol02 = Integer.parseInt(this.numCol02);
		model.datCol01 = new SimpleDateFormat(TableDataConstraint.C_DATE_PATTERN).parse(this.datCol01);
		model.datCol02 = new SimpleDateFormat(TableDataConstraint.C_DATE_PATTERN).parse(this.datCol02);
		return model;
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

		// タイプチェック.
		if (StringUtils.isEmpty(strCol01)) {
			errors.add(new ValidationError("strCol01", Messages.get("bookmark.bookmarkType.required")));
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

	/**
	 *
	 * @return List<Bookmark>
	 */
	public static List<ItemForm> getItemSearch(String tableName, String searchWord) {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		item.add(new ItemForm("searchWord", "text", "", "文字列01",searchWord));
		item.add(new ItemForm("searchWord", "text", "", "文字列02",""));

		return item;
	}

	/**
	 *
	 * @return List<Bookmark>
	 */
	public static List<ItemForm> getItemHead() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		item.add(new ItemForm("id", "text", "col1", "ID"));
		item.add(new ItemForm("tableName", "text", "col1", "テーブル名"));
		item.add(new ItemForm("strCol01", "text", "col1", "文字列01"));
		item.add(new ItemForm("strCol02", "text", "col1", "文字列02"));
		item.add(new ItemForm("numCol01", "text", "col1", "数値01"));
		item.add(new ItemForm("numCol02", "text", "col1", "数値02"));
		item.add(new ItemForm("datCol01", "text", "col1", "日付01"));
		item.add(new ItemForm("datCol02", "text", "col1", "日付02"));

		return item;
	}

	/**
	 *
	 * @return List<Bookmark>
	 */
	public List<ItemForm> getItem(List<TableColumnModel> col) {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		for(int i = 0; i < col.size(); ++i){
			switch (col.get(i).columnName) {
				case "id"			: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.id)); break;
				case "tableName"	: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.tableName)); break;
				case "strCol01"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.strCol01)); break;
				case "strCol02"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.strCol01)); break;
				case "numCol01"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.numCol01)); break;
				case "numCol02"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.numCol02)); break;
				case "datCol01"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.datCol01)); break;
				case "datCol02"		: item.add(new ItemForm(col.get(i).columnName, "text", col.get(i).displayType, col.get(i).displayName, this.datCol02)); break;

			}
		}

		return item;
	}
}
