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

import consts.adm.BookmarkConstraint;
import forms.adm.BookmarkForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;

/**
 *
 * ブックマーク・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = BookmarkConstraint.C_NAME)
@Table(name = BookmarkConstraint.C_TABLE)
public class BookmarkModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = BookmarkConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BookmarkConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = BookmarkConstraint.C_TABLE_SEQ, allocationSize = BookmarkConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** 親ID. */
	@NotNull
	@Column(name = BookmarkConstraint.C_TABLE_COL_PARENT_ID)
	public Integer parentId;

	/** タイプ. */
	@NotNull
	@Column(name = BookmarkConstraint.C_TABLE_COL_BOOKMARK_TYPE)
	public String bookmarkType;

	/** 名称. */
	@NotNull
	@Column(name = BookmarkConstraint.C_TABLE_COL_NAME)
	public String name;

	/** URL. */
	@NotNull
	@Column(name = BookmarkConstraint.C_TABLE_COL_URL)
	public String url;

	/** アイコン. */
	@Column(name = BookmarkConstraint.C_TABLE_COL_IMAGE_ICON)
	public String imageIcon;

	/** コンストラクタ. */
	public BookmarkModel() {
	}

	/** コンストラクタ. */
	public BookmarkModel(Integer id, Integer parentId, String bookmarkType, String name, String url, String imageIcon) {
		this.id = id;
		this.parentId = parentId;
		this.bookmarkType = bookmarkType;
		this.name = name;
		this.url = url;
		this.imageIcon = imageIcon;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return BookmarkForm
	 *
	 */
	public final BookmarkForm getForm() {
		BookmarkForm form = new BookmarkForm();
		form.id = this.id.toString();
		form.parentId = this.parentId.toString();
		form.bookmarkType = this.bookmarkType;
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
	public static Find<Long, BookmarkModel> finder = new Find<Long, BookmarkModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID
	 * @return BookmarkModel
	 *
	 */
	public static BookmarkModel get(final Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @param parentId parentId
	 * @param bookmarkType bookmarkType
	 * @return List<BookmarkModel>
	 */
	public static List<BookmarkModel> getListChildren(final Integer parentId, final String bookmarkType) {
		return BookmarkModel.finder.where()
			.eq(BookmarkConstraint.C_TABLE_COL_BOOKMARK_TYPE, bookmarkType)
			.eq(BookmarkConstraint.C_TABLE_COL_PARENT_ID, parentId)
			.order(BookmarkConstraint.C_TABLE_COL_ID)
			.findList();
	}

	/**
	 *
	 *
	 * @return List<Bookmark>
	 */
	public static List<BookmarkModel> getList() {

		return BookmarkModel.finder.order(BookmarkConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param bookmarkType bookmarkType
	 * @return List<BookmarkModel>
	 *
	 */
	public static List<BookmarkModel> getList(final String bookmarkType) {

		if (bookmarkType.isEmpty()) {
			return BookmarkModel.finder.order(BookmarkConstraint.C_TABLE_COL_ID).findList();
		} else {
			return BookmarkModel.finder.where()
				.eq(BookmarkConstraint.C_TABLE_COL_BOOKMARK_TYPE, bookmarkType)
				.eq(BookmarkConstraint.C_TABLE_COL_PARENT_ID, 0)
				.order(BookmarkConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 *
	 * @param page ページ数<br>
	 * @param bookmarkType 検索ワード<br>
	 * @return List<Bookmark>
	 *
	 */
	public static PagedList<BookmarkModel> getPagedList(final Integer page, final String bookmarkType) {

		int pageSize = BookmarkConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (bookmarkType.isEmpty()) {
			return BookmarkModel.finder.order(BookmarkConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return BookmarkModel.finder.where()
				.eq(BookmarkConstraint.C_TABLE_COL_BOOKMARK_TYPE, bookmarkType)
				.eq(BookmarkConstraint.C_TABLE_COL_PARENT_ID, 0)
				.order(BookmarkConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}
}