package forms.adm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.PortletParameterConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.PortletParameterModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.Session;

/**
 *
 * アカウント・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class PortletParameterForm extends SystemFusionForm {

	/** ID. */
	public String id = "";
	/** ポートレットID. */
	public String portletId = "";
	/** 名前. */
	public String name = "";
	/** 値. */
	public String value = "";

	/** コンストラクタ. */
	public PortletParameterForm() {
		//this.id = PortletParameterConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(PortletParameterConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param id ID<br>
	 *
	 */
	public PortletParameterForm(final String id, final String portletId, final String name, final String value) {
		this.id = id;
		this.portletId = portletId;
		this.name = name;
		this.value = value;
		this.updateUser = Session.get(PortletParameterConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return PortletParameterModel
	 *
	 */
	public final PortletParameterModel getModel() {
		PortletParameterModel model = new PortletParameterModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.portletId = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.portletId).intValue() : null;
		model.name = this.name;
		model.value = this.value;
		model.createUser = this.createUser;
		model.updateUser = this.updateUser;

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
			// ログ出力.
			StackTraceElement ste = new Throwable().getStackTrace()[0];
			LogModel.logging(ste, PortletParameterConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
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
		List<Integer> idx = (List<Integer>) Arrays.asList(2);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(PortletParameterConstraint.C_ITEM_DETAIL.get(i),
					PortletParameterConstraint.C_ITEM_TYPE.get(i),
					"＝" + PortletParameterConstraint.C_ITEM_DISPLAY.get(i),
					PortletParameterConstraint.C_ITEM_LABEL.get(i),
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
		List<ItemForm> item = new ArrayList<ItemForm>();
		IntStream.range(0, PortletParameterConstraint.C_ITEM_DETAIL.size())
			.forEach(i -> item.add(
				new ItemForm(PortletParameterConstraint.C_ITEM_DETAIL.get(i),
					PortletParameterConstraint.C_ITEM_TYPE.get(i),
					PortletParameterConstraint.C_ITEM_DISPLAY.get(i),
					PortletParameterConstraint.C_ITEM_LABEL.get(i))
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
		IntStream.range(0, PortletParameterConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(PortletParameterConstraint.C_ITEM_DETAIL.get(i),
						PortletParameterConstraint.C_ITEM_TYPE.get(i),
						PortletParameterConstraint.C_ITEM_DISPLAY.get(i),
						PortletParameterConstraint.C_ITEM_LABEL.get(i),
						(String) this.getClass().getDeclaredField(PortletParameterConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
