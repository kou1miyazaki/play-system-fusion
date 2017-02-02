package forms.adm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.PortletConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.PortletModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.Session;

/**
 *
 * ポートレット・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class PortletForm extends SystemFusionForm {

	public String id = "";
	public String portalType = "";
	public String portalTabType = "";
	public String displayType = "";
	public String portletName = "";
	public String displayName = "";
	public String activeFlg = "";

	/** コンストラクタ. */
	public PortletForm() {
		//this.id = PortletConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(PortletConstraint.C_SESSION_USER_NAME);
	}

	/** コンストラクタ. */
	public PortletForm(String id, String portalType, String portalTabType, String displayType, String portletName, String displayName, String activeFlg) {
		this.id = id;
		this.portalType = portalType;
		this.portalTabType = portalTabType;
		this.displayType = displayType;
		this.portletName = portletName;
		this.displayName = displayName;
		this.activeFlg = activeFlg;
		this.updateUser = Session.get(PortletConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return PortalModel
	 *
	 */
	public final PortletModel getModel() {
		PortletModel model = new PortletModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.portalType = this.portalType;
		model.portalTabType = this.portalTabType;
		model.displayType = this.displayType;
		model.portletName = this.portletName;
		model.displayName = this.displayName;
		model.activeFlg = Boolean.valueOf(this.activeFlg);
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
			LogModel.logging(ste, PortletConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
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
				new ItemForm(PortletConstraint.C_ITEM_DETAIL.get(i),
					PortletConstraint.C_ITEM_TYPE.get(i),
					"＝" + PortletConstraint.C_ITEM_DISPLAY.get(i),
					PortletConstraint.C_ITEM_LABEL.get(i),
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
		IntStream.range(0, PortletConstraint.C_ITEM_DETAIL.size())
			.forEach(i -> item.add(
				new ItemForm(PortletConstraint.C_ITEM_DETAIL.get(i),
					PortletConstraint.C_ITEM_TYPE.get(i),
					PortletConstraint.C_ITEM_DISPLAY.get(i),
					PortletConstraint.C_ITEM_LABEL.get(i))
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
		IntStream.range(0, PortletConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(PortletConstraint.C_ITEM_DETAIL.get(i),
							PortletConstraint.C_ITEM_TYPE.get(i),
							PortletConstraint.C_ITEM_DISPLAY.get(i),
							PortletConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(PortletConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
