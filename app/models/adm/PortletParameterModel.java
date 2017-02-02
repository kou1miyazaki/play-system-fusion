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

import com.avaje.ebean.PagedList;

import consts.adm.PortletParameterConstraint;
import forms.adm.PortletParameterForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * ポートレット・パラメーター・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name =  PortletParameterConstraint.C_NAME)
@Table(name = PortletParameterConstraint.C_TABLE)
public class PortletParameterModel extends SystemFusionModel {
	/** ID. */
	@Id
	@Column(name = PortletParameterConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PortletParameterConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = PortletParameterConstraint.C_TABLE_SEQ, allocationSize = PortletParameterConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** ポートレットID. */
	@Column(name = PortletParameterConstraint.C_TABLE_COL_PORTLET_ID)
	public Integer portletId;

	/** 名前. */
	@Column(name = PortletParameterConstraint.C_TABLE_COL_NAME)
	public String name;

	/** 値. */
	@Column(name = PortletParameterConstraint.C_TABLE_COL_VALUE)
	public String value;

	/** ポートレット. */
    //@ManyToOne
    //@JoinColumn(name = "PORTLET_ID")
    //public PortletModel portlet;

	/** コンストラクタ. */
	public PortletParameterModel() {
	}

	/**
	 *
	 *  コンストラクタ.
	 *
	 * @param id ID
	 * @param portletId portletId
	 * @param name name
	 * @param value value
	 *
	 */
	public PortletParameterModel(final Integer id, final Integer portletId, final String name, final String value) {

		this.id = id;
		this.portletId = portletId;
		this.name = name;
		this.value = value;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return PortletParameterForm
	 *
	 */
	public final PortletParameterForm getForm() {
		PortletParameterForm form = new PortletParameterForm();
		form.id = this.id.toString();
		form.portletId = this.portletId.toString();
		form.name = this.name;
		form.value = this.value;
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
	public static Find<Long, PortletParameterModel> finder = new Find<Long, PortletParameterModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID<br>
	 * @return PortalModel
	 *
	 */
	public static PortletParameterModel get(final Integer id) {
		return PortletParameterModel.finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Bookmark>
	 */
	public static List<PortletParameterModel> getList() {
		return PortletParameterModel.finder.where()
				.order(PortletParameterConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param portletId portletId
	 * @return List
	 *
	 */
	public static List<PortletParameterModel> getList(final Integer portletId) {

		List<PortletParameterModel> model = PortletParameterModel.finder.where()
			.eq(PortletParameterConstraint.C_TABLE_COL_PORTLET_ID, portletId)
			.order(PortletParameterConstraint.C_TABLE_COL_ID).findList();

		return model;
	}

	/**
	 *
	 *
	 * @param page page
	 * @param name name
	 * @return List<PortletParameterModel>
	 *
	 */
	public static PagedList<PortletParameterModel> getPagedList(final Integer page, final String name) {

		int pageSize = PortletParameterConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (name.isEmpty()) {
			return PortletParameterModel.finder.order(PortletParameterConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return PortletParameterModel.finder.where()
				.eq(PortletParameterConstraint.C_TABLE_COL_NAME, name)
				.order(PortletParameterConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}

	/**
	 *
	 *
	 * @param id ID<br>
	 * @return HashMap<String, String>
	 *
	 */
	public static HashMap<String, String> getMap(final Integer id) {

		List<PortletParameterModel> model = getList(id);

		HashMap<String, String> dataMap = new HashMap<String, String>();

		for (int i = 0; i < model.size(); ++i) {
			dataMap.put(model.get(i).name, model.get(i).value);
		}

		return dataMap;
	}
}