package forms.adm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.PortalConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.PortalModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.Session;

/**
 *
 * ポータル・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class PortalForm extends SystemFusionForm {

	public String id = "";
	public String portalType = "";
	public String portalTabType = "";
	public String portalName = "";
	public String url = "";
	public String imageIcon = "";
	public String layoutType = "default";

	/** コンストラクタ. */
	public PortalForm() {
		//this.id = PortalConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(PortalConstraint.C_SESSION_USER_NAME);
	}

	/** コンストラクタ. */
	public PortalForm(String id, String portalType, String portalTabType, String portalName, String url, String imageIcon, String layoutType) {
		this.id = id;
		this.portalType = portalType;
		this.portalTabType = portalTabType;
		this.portalName = portalName;
		this.url = url;
		this.imageIcon = imageIcon;
		this.layoutType = layoutType;
		this.updateUser = Session.get(PortalConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return PortalModel
	 *
	 */
	public final PortalModel getModel() {
		PortalModel model = new PortalModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.portalType = this.portalType;
		model.portalTabType = this.portalTabType;
		model.portalName = this.portalName;
		model.url = this.url;
		model.imageIcon = this.imageIcon;
		model.layoutType = this.layoutType;
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
			LogModel.logging(ste, PortalConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
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
				new ItemForm(PortalConstraint.C_ITEM_DETAIL.get(i),
					PortalConstraint.C_ITEM_TYPE.get(i),
					"＝" + PortalConstraint.C_ITEM_DISPLAY.get(i),
					PortalConstraint.C_ITEM_LABEL.get(i),
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
		IntStream.range(0, PortalConstraint.C_ITEM_DETAIL.size())
			.forEach(i -> item.add(
				new ItemForm(PortalConstraint.C_ITEM_DETAIL.get(i),
					PortalConstraint.C_ITEM_TYPE.get(i),
					PortalConstraint.C_ITEM_DISPLAY.get(i),
					PortalConstraint.C_ITEM_LABEL.get(i))
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
		IntStream.range(0, PortalConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(PortalConstraint.C_ITEM_DETAIL.get(i),
							PortalConstraint.C_ITEM_TYPE.get(i),
							PortalConstraint.C_ITEM_DISPLAY.get(i),
							PortalConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(PortalConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
