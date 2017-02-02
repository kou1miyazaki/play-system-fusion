package models.dbx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Expr;
import com.avaje.ebean.PagedList;

import consts.dbx.TableDataConstraint;
import forms.dbx.TableDataForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * @author kou1miyazaki
 *
 */
@Entity(name =  TableDataConstraint.C_NAME)
@Table(name = TableDataConstraint.C_TABLE)
public class TableDataModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = TableDataConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TableDataConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = TableDataConstraint.C_TABLE_SEQ, allocationSize = TableDataConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** テーブル名. */
	@NotNull
	@Column(name = TableDataConstraint.C_TABLE_COL_TABLE_NAME)
	public String tableName;

	/** 文字列01. */
	@Column(name = TableDataConstraint.C_TABLE_COL_STR_COL01)
	public String strCol01;

	/** 文字列02. */
	@Column(name = TableDataConstraint.C_TABLE_COL_STR_COL02)
	public String strCol02;

	/** 数値01. */
	@Column(name = TableDataConstraint.C_TABLE_COL_NUM_COL01)
	public Integer numCol01;

	/** 数値02. */
	@Column(name = TableDataConstraint.C_TABLE_COL_NUM_COL02)
	public Integer numCol02;

	/** 日付01. */
	@Column(name = TableDataConstraint.C_TABLE_COL_DAT_COL01)
	public Date datCol01;

	/** 日付02. */
	@Column(name = TableDataConstraint.C_TABLE_COL_DAT_COL02)
	public Date datCol02;

	/** コンストラクタ. */
	public TableDataModel() {}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param id ID
	 * @param tableName tableName
	 * @param strCol01 strCol01
	 * @param strCol02 strCol02
	 * @param numCol01 numCol01
	 * @param numCol02 numCol02
	 * @param datCol01 datCol01
	 * @param datCol02 datCol02
	 *
	 */
	public TableDataModel(
		Integer id, String tableName, String strCol01, String strCol02,
		Integer numCol01, Integer numCol02, Date datCol01, Date datCol02) {

		this.id = id;
		this.tableName = tableName;
		this.strCol01 = strCol01;
		this.strCol02 = strCol02;
		this.numCol01 = numCol01;
		this.numCol02 = numCol02;
		this.datCol01 = datCol01;
		this.datCol02 = datCol02;
	}

	/**
	 *
	 * @return TableDataForm
	 *
	 */
	public final TableDataForm getForm() {
		TableDataForm form = new TableDataForm();
		form.id = this.id.toString();
		form.tableName = this.tableName;
		form.strCol01 = this.strCol01;
		form.strCol02 = this.strCol02;
		form.numCol01 = this.numCol01.toString();
		form.numCol02 = this.numCol02.toString();
		form.datCol01 = (new SimpleDateFormat(TableDataConstraint.C_DATE_PATTERN)).format(this.datCol01);
		form.datCol02 = (new SimpleDateFormat(TableDataConstraint.C_DATE_PATTERN)).format(this.datCol02);
		form.note = this.note;
		form.createDate = DateParser.datetimeToString(this.createDate);
		form.createUser = this.createUser;
		form.updateDate = DateParser.datetimeToString(this.updateDate);
		form.updateUser = this.updateUser;
		return form;
	}

	/**
	 *
	 */
	public static Find<Long, TableDataModel> finder = new Find<Long, TableDataModel>() {
	};

	/**
	 *
	 * @param tableName tableName
	 * @return List
	 *
	 */
	public static List<TableDataModel> getList(final String tableName) {
		return TableDataModel.finder.where()
				.eq(TableDataConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.order(TableDataConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param tableName tableName
	 * @param searchWord searchWord
	 * @return List
	 *
	 */
	public static List<TableDataModel> getList(String tableName, String searchWord) {

		if (searchWord == "") {
			return TableDataModel.finder.order(TableDataConstraint.C_TABLE_COL_ID).findList();
		} else {
			return TableDataModel.finder.where()
				.eq(TableDataConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.or(Expr.like(TableDataConstraint.C_TABLE_COL_STR_COL01, "%" + searchWord + "%"),
					Expr.like(TableDataConstraint.C_TABLE_COL_STR_COL02, "%" + searchWord + "%"))
				.order(TableDataConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 * @param tableName tableName
	 * @param page page
	 * @param searchWord searchWord
	 * @return PagedList
	 *
	 */
	public static PagedList<TableDataModel> getPagedList(String tableName, Integer page, String searchWord) {

		int pageSize = TableDataConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (searchWord.isEmpty()) {
			return TableDataModel.finder.order(TableDataConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return TableDataModel.finder.where()
				.or(Expr.like(TableDataConstraint.C_TABLE_COL_STR_COL01, "%" + searchWord + "%"),
					Expr.like(TableDataConstraint.C_TABLE_COL_STR_COL02, "%" + searchWord + "%"))
				.order(TableDataConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}

	/**
	 *
	 *
	 * @param col col
	 * @return HashMap
	 *
	 */
	public final HashMap<String, String> getMap(final List<TableColumnModel> col) {

		HashMap<String, String> dataMap = new HashMap<String, String>();

		for (int i = 0; i < col.size(); ++i) {
			switch (col.get(i).columnName) {
				case "id"			: dataMap.put(col.get(i).columnName, this.id.toString()); break;
				case "strCol01"		: dataMap.put(col.get(i).columnName, this.strCol01); break;
				case "strCol02"		: dataMap.put(col.get(i).columnName, this.strCol02); break;
				case "numCol01"		: dataMap.put(col.get(i).columnName, this.numCol01.toString()); break;
				case "numCol02"		: dataMap.put(col.get(i).columnName, this.numCol02.toString()); break;
				case "datCol01"		: dataMap.put(col.get(i).columnName, DateParser.dateToString(this.datCol01)); break;
				case "datCol02"		: dataMap.put(col.get(i).columnName, DateParser.dateToString(this.datCol02)); break;
			}
		}

		return dataMap;
	}
}