package forms.dbx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.dbx.TableConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.dbx.TableColumnModel;
import play.data.validation.ValidationError;

/**
 *
 * テーブル・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class TableForm extends SystemFusionForm {

	/** ID. */
	public String id = "";
	/** テーブル名. */
	public String tableName = "";
	/** 表示名. */
	public String displayName = "";
	/** テーブル説明. */
	public String tableDesc = "";
	/** アイコン. */
	public String imageIcon = "";

	/** コンストラクタ. */
	public TableForm() {
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param id ID<br>
	 * @param tableName テーブル名<br>
	 * @param displayName 表示名<br>
	 * @param tableDesc テーブル説明<br>
	 * @param imageIcon アイコン<br>
	 *
	 */
	public TableForm(final String id, final String tableName, final String displayName, final String tableDesc, final String imageIcon) {
		this.id = id;
		this.tableName = tableName;
		this.displayName = displayName;
		this.tableDesc = tableDesc;
		this.imageIcon = imageIcon;
		this.displayName = displayName;
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return TableColumnModel
	 *
	 */
	public final TableColumnModel getModel() {
		TableColumnModel model = new TableColumnModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.tableName = this.tableName;
		model.displayName = this.displayName;
		//model.tableDesc = this.tableDesc;
		//model.imageIcon = this.imageIcon;
		model.displayName = this.displayName;
		return model;
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List<ValidationError>
	 *
	 */
	public final List<ValidationError> validate() {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		// エラー表示.
		if (errors.size() > 0) {
			System.out.println("BookmarkForm#validate errors");
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
		List<Integer> idx = (List<Integer>) Arrays.asList(3);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(TableConstraint.C_ITEM_DETAIL.get(i),
					TableConstraint.C_ITEM_TYPE.get(i),
					"＝" + TableConstraint.C_ITEM_DISPLAY.get(i),
					TableConstraint.C_ITEM_LABEL.get(i),
					searchWord)
				)
			);

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
		List<Integer> idx = (List<Integer>) Arrays.asList(0, 1, 3, 4);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(TableConstraint.C_ITEM_DETAIL.get(i),
					TableConstraint.C_ITEM_TYPE.get(i),
					TableConstraint.C_ITEM_DISPLAY.get(i),
					TableConstraint.C_ITEM_LABEL.get(i))
				)
			);
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
		IntStream.range(0, TableConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(TableConstraint.C_ITEM_DETAIL.get(i),
							TableConstraint.C_ITEM_TYPE.get(i),
							TableConstraint.C_ITEM_DISPLAY.get(i),
							TableConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(TableConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
