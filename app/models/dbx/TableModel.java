package models.dbx;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import consts.dbx.TableConstraint;
import models.cmn.SystemFusionModel;

/**
 *
 * @author kou1miyazaki
 *
 */
@Entity(name =  TableConstraint.C_NAME)
@Table(name = TableConstraint.C_TABLE)
public class TableModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = TableConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TableConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = TableConstraint.C_TABLE_SEQ, allocationSize = TableConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** テーブル名. */
	@Column(name = TableConstraint.C_TABLE_COL_TABLE_NAME)
	public String tableName;

	/** 表示名. */
	@Column(name = TableConstraint.C_TABLE_COL_DISPLAY_NAME)
	public String displayName;

	public String tableDesc;
	public String imageIcon;

	/** コンストラクタ. */
	public TableModel() {
	}

	/** コンストラクタ. */
	public TableModel(Integer id, String tableName, String displayName) {

		this.id = id;
		this.tableName = tableName;
		this.displayName = displayName;
	}

	/**
	 *
	 */
	public static Find<Long, TableModel> finder = new Find<Long, TableModel>(){};

	/**
	 *
	 *
	 * @return List<Bookmark>
	 */
	public static List<TableModel> getList() {
		return TableModel.finder.order(TableConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param tableName テーブル名<br>
	 * @return List<TableModel>
	 */
	public static List<TableModel> getList(String tableName) {

		if (tableName.isEmpty()) {
			return TableModel.finder.order(TableConstraint.C_TABLE_COL_ID).findList();
		} else {
			return TableModel.finder.where()
				.eq(TableConstraint.C_TABLE_COL_TABLE_NAME, tableName)
				.order(TableConstraint.C_TABLE_COL_ID).findList();
		}
	}
}