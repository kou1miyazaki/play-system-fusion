package forms.adm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.BookmarkConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.BookmarkModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.Session;

/**
 *
 * ブックマーク・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class BookmarkForm extends SystemFusionForm {

	public String id = "";
	public String parentId = "";
	public String bookmarkType = "";
	public String name = "";
	public String url = "";
	public String imageIcon = "";

	/** コンストラクタ. */
	public BookmarkForm() {
		//this.id = BookmarkConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(BookmarkConstraint.C_SESSION_USER_NAME);
	}

	/** コンストラクタ. */
	public BookmarkForm(String id, String parentId, String bookmarkType, String name, String url, String imageIcon) {
		this.id = id;
		this.parentId = parentId;
		this.bookmarkType = bookmarkType;
		this.name = name;
		this.url = url;
		this.imageIcon = imageIcon;
		this.updateUser = Session.get(BookmarkConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return BookmarkModel
	 *
	 */
	public final BookmarkModel getModel() {
		BookmarkModel model = new BookmarkModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.parentId = StringUtils.isNotEmpty(this.parentId) ? Integer.valueOf(this.parentId).intValue() : null;
		model.bookmarkType = this.bookmarkType;
		model.name = this.name;
		model.url = this.url;
		model.imageIcon = this.imageIcon;
		model.createUser = this.createUser;
		model.updateUser = this.updateUser;

		return model;
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List
	 *
	 */
	public final List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		// タイプチェック.
		if (StringUtils.isEmpty(bookmarkType)) {
			errors.add(new ValidationError("bookmarkType", Messages.get("bookmark.bookmarkType.required")));
		}
		// 名前チェック.
		if (StringUtils.isEmpty(name)) {
			errors.add(new ValidationError("name", Messages.get("bookmark.name.required")));
		}
		// URLチェック.
		if (StringUtils.isEmpty(url)) {
			errors.add(new ValidationError("url", Messages.get("bookmark.url.required")));
		}

		// エラー表示.
		if (errors.size() > 0) {
			// ログ出力.
			StackTraceElement ste = new Throwable().getStackTrace()[0];
			LogModel.logging(ste, BookmarkConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
			return errors;
		}

		return null;
	}

	/**
	 *
	 * 検索アイテムを取得する.
	 *
	 * @param searchWord searchWord<br>
	 * @return List
	 *
	 */
	public static List<ItemForm> getItemSearch(final String searchWord) {

	    // リスト作成.
		int[] idx = {2};
		List<ItemForm> item = new ArrayList<ItemForm>();
		for (int i: idx) {
			try {
				item.add(new ItemForm(
						BookmarkConstraint.C_ITEM_DETAIL.get(i),
						BookmarkConstraint.C_ITEM_TYPE.get(i),
						"＝" + BookmarkConstraint.C_ITEM_DISPLAY.get(i),
						BookmarkConstraint.C_ITEM_LABEL.get(i),
						searchWord
					)
				);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	/**
	 *
	 * 一覧タイトルアイテムを取得する.
	 *
	 * @return List
	 *
	 */
	public static List<ItemForm> getItemHead() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		for (int i = 0; i < BookmarkConstraint.C_ITEM_DETAIL.size(); ++i) {
			try {
				item.add(new ItemForm(
						BookmarkConstraint.C_ITEM_DETAIL.get(i),
						BookmarkConstraint.C_ITEM_TYPE.get(i),
						BookmarkConstraint.C_ITEM_DISPLAY.get(i),
						BookmarkConstraint.C_ITEM_LABEL.get(i)
					)
				);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	/**
	 *
	 * 詳細アイテムを取得する.
	 *
	 * @return List<ItemForm>
	 *
	 */
	public final List<ItemForm> getItem() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		IntStream.range(0, BookmarkConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(BookmarkConstraint.C_ITEM_DETAIL.get(i),
							BookmarkConstraint.C_ITEM_TYPE.get(i),
							BookmarkConstraint.C_ITEM_DISPLAY.get(i),
							BookmarkConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(BookmarkConstraint.C_ITEM_DETAIL.get(i)).get(this)
						)
					);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		);
		return item;
	}
}
