package models.adm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.PagedList;

import consts.adm.MenuConstraint;
import forms.adm.MenuForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * メニュー・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = MenuConstraint.C_NAME)
@Table(name = MenuConstraint.C_TABLE)
public class MenuModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = MenuConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = MenuConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = MenuConstraint.C_TABLE_SEQ, allocationSize = MenuConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** 親ID. */
	@NotNull
	@Column(name = MenuConstraint.C_TABLE_COL_PARENT_ID)
	public Integer parentId;

	/** タイプ. */
	@NotNull
	@Column(name = MenuConstraint.C_TABLE_COL_MENU_TYPE)
	public String menuType;

	/** タイプ. */
	@NotNull
	@Column(name = MenuConstraint.C_TABLE_COL_ACTION_TYPE)
	public String actionType;

	/** 名称. */
	@Column(name = MenuConstraint.C_TABLE_COL_NAME)
	public String name;

	/** URL. */
	@Column(name = MenuConstraint.C_TABLE_COL_URL)
	public String url;

	/** アイコン. */
	@Column(name = MenuConstraint.C_TABLE_COL_IMAGE_ICON)
	public String imageIcon;

	@Transient
	public Integer cnt;

	/** コンストラクタ. */
	public MenuModel() {
	}

	/** コンストラクタ. */
	public MenuModel(Integer id, Integer parentId, String menuType, String actionType, String name, String url, String imageIcon) {
		this.id = id;
		this.parentId = parentId;
		this.menuType = menuType;
		this.actionType = actionType;
		this.name = name;
		this.url = url;
		this.imageIcon = imageIcon;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return MenuForm
	 *
	 */
	public final MenuForm getForm() {
		MenuForm form = new MenuForm();
		form.id = this.id.toString();
		form.parentId = this.parentId.toString();
		form.menuType = this.menuType;
		form.actionType = this.actionType;
		form.name = this.name;
		form.url = this.url;
		form.imageIcon = this.imageIcon;
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
	public static Find<Long, MenuModel> finder = new Find<Long, MenuModel>() {};

	/**
	 * データベースからObjectModelを取得.
	 *
	 * @param Integer id
	 * @return MenuModel
	 */
	public static MenuModel get(Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 * @return List<Menu>
	 */
	public static List<MenuModel> getList() {
		return MenuModel.finder.order(MenuConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 * @param menuType
	 * @return List<Bookmark>
	 */
	public static List<MenuModel> getList(String searchWord) {
		return MenuModel.finder.where()
			.eq(MenuConstraint.C_TABLE_COL_MENU_TYPE, searchWord)
			.eq(MenuConstraint.C_TABLE_COL_PARENT_ID, 0)
			.order(MenuConstraint.C_TABLE_COL_ID)
			.findList();
	}

	/**
	 *
	 * @param menuType
	 * @return List<Bookmark>
	 */
	public static List<MenuModel> getListChildren(Integer pParentId, String pMenuType) {
		return MenuModel.finder.where()
			.eq(MenuConstraint.C_TABLE_COL_MENU_TYPE, pMenuType)
			.eq(MenuConstraint.C_TABLE_COL_PARENT_ID, pParentId)
			.order(MenuConstraint.C_TABLE_COL_ID)
			.findList();
	}

	/**
	 *
	 *
	 * @return List<Bookmark>
	 */
	public static PagedList<MenuModel> getPagedList(Integer page, String menuType) {

		int pageSize = MenuConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (menuType.isEmpty()) {
			return MenuModel.finder.order(MenuConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return MenuModel.finder.where()
				.eq(MenuConstraint.C_TABLE_COL_MENU_TYPE, menuType)
				.order(MenuConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}
}