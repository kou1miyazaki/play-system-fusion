package models.adm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.PagedList;

import consts.adm.RoleConstraint;
import forms.adm.RoleForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * ロール・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = RoleConstraint.C_NAME)
@Table(name = RoleConstraint.C_TABLE)
public class RoleModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = RoleConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = RoleConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = RoleConstraint.C_TABLE_SEQ, allocationSize = RoleConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** 名称. */
	@NotNull
	@Column(name = RoleConstraint.C_TABLE_COL_NAME)
	public String name;

	/** URL. */
	@Column(name = "ROLE_DESC")
	public String roleDesc;

	/** コンストラクタ. */
	public RoleModel() {}

	/** コンストラクタ. */
	public RoleModel(Integer id, String name, String roleDesc) {
		this.id = id;
		this.name = name;
		this.roleDesc = roleDesc;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return RoleForm
	 *
	 */
	public final RoleForm getForm() {
		RoleForm form = new RoleForm();
		form.id = this.id.toString();
		form.name = this.name;
		form.roleDesc = this.roleDesc;
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
	public static Find<Long, RoleModel> finder = new Find<Long, RoleModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID<br>
	 * @return RoleModel
	 *
	 */
	public static RoleModel get(final Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Role>
	 */
	public static List<RoleModel> getList() {

		return RoleModel.finder.order(RoleConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param bookmarkType bookmarkType
	 * @return List
	 *
	 */
	public static List<RoleModel> getList(final String bookmarkType) {

		if (bookmarkType.isEmpty()) {
			return RoleModel.finder.order(RoleConstraint.C_TABLE_COL_ID).findList();
		} else {
			return RoleModel.finder.where()
				.eq(RoleConstraint.C_TABLE_COL_NAME, bookmarkType)
				.order(RoleConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 *
	 *
	 * @param page page
	 * @param bookmarkType bookmarkType
	 * @return List
	 *
	 */
	public static PagedList<RoleModel> getPagedList(final Integer page, final String bookmarkType) {

		int pageSize = RoleConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (bookmarkType.isEmpty()) {
			return RoleModel.finder.order(RoleConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return RoleModel.finder.where()
				.eq(RoleConstraint.C_TABLE_COL_NAME, bookmarkType)
				.order(RoleConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}
}

