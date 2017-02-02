package models.adm;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.avaje.ebean.Expr;
import com.avaje.ebean.PagedList;

import consts.adm.PortletConstraint;
import forms.adm.PortletForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * ポートレット・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name =  PortletConstraint.C_NAME)
@Table(name = PortletConstraint.C_TABLE)
public class PortletModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = PortletConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PortletConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = PortletConstraint.C_TABLE_SEQ, allocationSize = PortletConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** ポータルタイプ. */
	@Column(name = PortletConstraint.C_TABLE_COL_PORTAL_TYPE)
	public String portalType;

	/** ポータルタブタイプ. */
	@Column(name = PortletConstraint.C_TABLE_COL_PORTAL_TAB_TYPE)
	public String portalTabType;

	/** 表示場所. */
	@Column(name = PortletConstraint.C_TABLE_COL_DISPLAY_TYPE)
	public String displayType;

	/** テーブルカラム名. */
	@Column(name = PortletConstraint.C_TABLE_COL_PORTLET_NAME)
	public String portletName;

	/** カラム名. */
	@Column(name = PortletConstraint.C_TABLE_COL_DISPLAY_NAME)
	public String displayName;

	/** カラム名. */
	@Column(name = PortletConstraint.C_TABLE_COL_ACTIVE_FLG)
	public Boolean activeFlg;

	/** パラメーター. */
	@Transient
	public HashMap<String, String> parameter;

	/** パラメーター. */
    //@OneToMany(mappedBy = "portlet")
    //public List<PortletParameterModel> portletParameter = new ArrayList<PortletParameterModel>();

	/** コンストラクタ. */
	public PortletModel() {
	}

	/** コンストラクタ. */
	public PortletModel(Integer id, String portalType, String portalTabType, String displayType,
		String portletName, String displayName, Boolean activeFlg) {

		this.id = id;
		this.portalType = portalType;
		this.portalTabType = portalTabType;
		this.displayType = displayType;
		this.portletName = portletName;
		this.displayName = displayName;
		this.activeFlg = activeFlg;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return AccountForm
	 *
	 */
	public PortletForm getForm() {
		PortletForm form = new PortletForm();
		form.id = this.id.toString();
		form.portalType = this.portalType;
		form.portalTabType = this.portalTabType;
		form.displayType = this.displayType;
		form.portletName = this.portletName;
		form.displayName = this.displayName;
		form.activeFlg = (this.activeFlg) ? "TRUE" : "FALSE";
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
	public static Find<Long, PortletModel> finder = new Find<Long, PortletModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID<br>
	 * @return PortalModel
	 *
	 */
	public static PortletModel get(final Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List
	 *
	 */
	public static List<PortletModel> getList() {
		return PortletModel.finder.where()
				.eq(PortletConstraint.C_TABLE_COL_PORTAL_TYPE, "index")
				.or(Expr.eq(PortletConstraint.C_TABLE_COL_PORTAL_TAB_TYPE, "common"),
					Expr.eq(PortletConstraint.C_TABLE_COL_PORTAL_TAB_TYPE, "home"))
				.order(PortletConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 * @param portalType portalType
	 * @param portalTabType portalTabType
	 * @return List<PortletModel>
	 *
	 */
	public static List<PortletModel> getList(final String portalType, final String portalTabType) {

		List<PortletModel> model = PortletModel.finder.where()
				.eq(PortletConstraint.C_TABLE_COL_PORTAL_TYPE, portalType)
				.or(Expr.eq(PortletConstraint.C_TABLE_COL_PORTAL_TAB_TYPE, "common"),
					Expr.eq(PortletConstraint.C_TABLE_COL_PORTAL_TAB_TYPE, portalTabType))
			.order(PortletConstraint.C_TABLE_COL_ID).findList();

		return model;
	}

	/**
	 *
	 * @param page page
	 * @param portalType portalType
	 * @return List<PortletModel>
	 *
	 */
	public static PagedList<PortletModel> getPagedList(final Integer page, final String portalType) {

		int pageSize = PortletConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (portalType.isEmpty()) {
			return PortletModel.finder.order(PortletConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return PortletModel.finder.where()
				.eq(PortletConstraint.C_TABLE_COL_PORTAL_TYPE, portalType)
				.order(PortletConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}
}