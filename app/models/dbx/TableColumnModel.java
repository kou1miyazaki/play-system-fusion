package models.dbx;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import consts.dbx.TableColumnConstraint;
import models.cmn.SystemFusionModel;

/**
 *
 * @author kou1miyazaki
 *
 */
@Entity(name =  TableColumnConstraint.C_NAME)
@Table(name = TableColumnConstraint.C_TABLE)
public class TableColumnModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = TableColumnConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TableColumnConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = TableColumnConstraint.C_TABLE_SEQ, allocationSize = TableColumnConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** テーブル名. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_TABLE_NAME)
	public String tableName;

	/** テーブルカラム名. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_TABLE_COL_NAME)
	public String tableColName;

	/** カラム名. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_COLUMN_NAME)
	public String columnName;

	/** 表示場所. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_DISPLAY_TYPE)
	public String displayType;

	/** 表示名. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_DISPLAY_NAME)
	public String displayName;


	public String columnType;
	public Integer columnLength;

	/** リストフラグ. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_LIST_FLG)
	public Boolean listFlg;

	/** リスト表示順. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_LIST_ORDER)
	public Integer listOrder;

	/** 検索フラグ. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_SEARCH_FLG)
	public Boolean searchFlg;

	/** 検索表示順. */
	@Column(name = TableColumnConstraint.C_TABLE_COL_SEARCH_ORDER)
	public Integer searchOrder;

	/** コンストラクタ. */
	public TableColumnModel() {}

	/** コンストラクタ. */
	public TableColumnModel(Integer pId, String tableName, String tableColName,
		String columnName, String displayType, String displayName,
		Boolean listFlg, Integer listOrder, Boolean searchFlg, Integer searchOrder) {

		this.id = pId;
		this.tableName = tableName;
		//this.tableColName = tableColName;
		this.columnName = columnName;
		this.displayType = displayType;
		this.displayName = displayName;
		this.listFlg = listFlg;
		this.listOrder = listOrder;
		this.searchFlg = searchFlg;
		this.searchOrder = searchOrder;
	}

	/**
	 *
	 */
	public static Find<Long, TableColumnModel> finder = new Find<Long, TableColumnModel>() {
	};

	/**
	 *
	 *
	 * @return List<Bookmark>
	 */
	public static List<TableColumnModel> getList() {
		return TableColumnModel.finder.order(TableColumnConstraint.C_LABEL_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param tableName テーブル名<br>
	 * @return List<TableColumnModel>
	 *
	 */
	public static List<TableColumnModel> getList(final String tableName) {

		if (tableName.isEmpty()) {
			return TableColumnModel.finder.order(TableColumnConstraint.C_TABLE_COL_ID).findList();
		} else {
			return TableColumnModel.finder.where()
				.eq(TableColumnConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.eq(TableColumnConstraint.C_TABLE_COL_LIST_FLG, true)
				.order(TableColumnConstraint.C_TABLE_COL_LIST_ORDER).findList();
		}
	}

	/**
	 *
	 *
	 * @param tableName テーブル名<br>
	 * @return List<TableColumnModel>
	 *
	 */
	public static List<TableColumnModel> getListSearch(final String tableName) {

		if (tableName.isEmpty()) {
			return TableColumnModel.finder.order(TableColumnConstraint.C_TABLE_COL_ID).findList();
		} else {
			return TableColumnModel.finder.where()
				.eq(TableColumnConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.eq(TableColumnConstraint.C_TABLE_COL_SEARCH_FLG, true)
				.order(TableColumnConstraint.C_TABLE_COL_SEARCH_ORDER).findList();
		}
	}

	/**
	 *
	 *
	 * @param tableName テーブル名<br>
	 * @return List<TableColumnModel>
	 *
	 */
	public static List<TableColumnModel> getListForm(final String tableName) {

		if (tableName.isEmpty()) {
			return TableColumnModel.finder.order(TableColumnConstraint.C_TABLE_COL_ID).findList();
		} else {
			return TableColumnModel.finder.where()
				.eq(TableColumnConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.order(TableColumnConstraint.C_TABLE_COL_ID).findList();
		}
	}
}
