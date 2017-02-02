package models.adm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.avaje.ebean.PagedList;

import consts.adm.PortalConstraint;
import forms.adm.PortalForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * ポータル・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = PortalConstraint.C_NAME)
@Table(name = PortalConstraint.C_TABLE)
public class PortalModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = PortalConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PortalConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = PortalConstraint.C_TABLE_SEQ, allocationSize = PortalConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** ポータルタイプ. */
	@Column(name = PortalConstraint.C_TABLE_COL_PORTAL_TYPE)
	public String portalType;

	/** ポータルタブタイプ. */
	@Column(name = PortalConstraint.C_TABLE_COL_PORTAL_TAB_TYPE)
	public String portalTabType;

	/** ポータル名. */
	@Column(name = PortalConstraint.C_TABLE_COL_PORTAL_NAME)
	public String portalName;

	/** URL. */
	@Column(name = PortalConstraint.C_TABLE_COL_URL)
	public String url;

	/** イメージアイコン. */
	@Column(name = PortalConstraint.C_TABLE_COL_IMAGE_ICON)
	public String imageIcon;

	/** レイアウトタイプ. */
	@Column(name = PortalConstraint.C_TABLE_COL_LAYOUT_TYPE)
	public String layoutType;

	/** コンストラクタ. */
	public PortalModel() {
	}

	/** コンストラクタ. */
	public PortalModel(Integer id, String portalType, String portalTabType, String portalName, String url, String imageIcon, String layoutType) {
		this.id = id;
		this.portalType = portalType;
		this.portalTabType = portalTabType;
		this.portalName = portalName;
		this.url = url;
		this.imageIcon = imageIcon;
		this.layoutType = layoutType;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return PortalForm
	 *
	 */
	public final PortalForm getForm() {
		PortalForm form = new PortalForm();
		form.id = this.id.toString();
		form.portalType = this.portalType;
		form.portalTabType = this.portalTabType;
		form.portalName = this.portalName;
		form.url = this.url;
		form.imageIcon = this.imageIcon;
		form.layoutType = this.layoutType;
		form.note = this.note;
		form.createDate = DateParser.datetimeToString(this.createDate);
		form.createUser = this.createUser;
		form.updateDate = DateParser.datetimeToString(this.updateDate);
		form.updateUser = this.updateUser;
		return form;
	}

	/**
	 *
	 *
	 */
	public static Find<Long, PortalModel> finder = new Find<Long, PortalModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID<br>
	 * @return PortalModel
	 *
	 */
	public static PortalModel get(final Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @param portalType portalType
	 * @param portalTabType portalTabType
	 * @return PortalModel
	 *
	 */
	public static PortalModel getPortal(final String portalType, final String portalTabType) {

		List<PortalModel> model = PortalModel.finder.where()
				.eq(PortalConstraint.C_TABLE_COL_PORTAL_TYPE, portalType)
				.eq(PortalConstraint.C_TABLE_COL_PORTAL_TAB_TYPE, portalTabType).findList();

		if (model.size() > 0) {
			return model.get(0);
		} else {
			return new PortalModel();
		}
	}

	/**
	 *
	 *
	 * @return List
	 *
	 */
	public static List<PortalModel> getList() {
		return PortalModel.finder.where()
				.order(PortalConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @return List
	 *
	 */
	public static List<PortalModel> getListIndex() {
		return PortalModel.finder.where()
				.eq(PortalConstraint.C_TABLE_COL_PORTAL_TYPE, "index")
				.order(PortalConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param portalType portalType
	 * @return List
	 *
	 */
	public static List<PortalModel> getList(final String portalType) {

		List<PortalModel> model = PortalModel.finder.where()
				.eq(PortalConstraint.C_TABLE_COL_PORTAL_TYPE, portalType)
				.order(PortalConstraint.C_TABLE_COL_ID).findList();

		return model;
	}

	/**
	 *
	 *
	 * @param page ページ数<br>
	 * @param bookmarkType 検索ワード<br>
	 * @return List
	 *
	 */
	public static PagedList<PortalModel> getPagedList(final Integer page, final String bookmarkType) {

		int pageSize = PortalConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (bookmarkType.isEmpty()) {
			return PortalModel.finder.order(PortalConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return PortalModel.finder.where()
				.eq(PortalConstraint.C_TABLE_COL_PORTAL_TYPE, bookmarkType)
				.order(PortalConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}
}
