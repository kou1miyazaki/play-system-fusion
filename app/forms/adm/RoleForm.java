package forms.adm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.RoleConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.RoleModel;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.Session;

/**
*
* @author kou1miyazaki
*
*/
public class RoleForm extends SystemFusionForm {

	/** ID. */
	public String id = "";
	/** 名前. */
	public String name = "";
	/** 説明. */
	public String roleDesc = "";

	/** コンストラクタ. */
	public RoleForm() {
		//this.id = RoleConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(RoleConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param id ID<br>
	 * @param name 名前<br>
	 * @param roleDesc 説明<br>
	 *
	 */
	public RoleForm(final String id, final String name, final String roleDesc) {
		this.id = id;
		this.name = name;
		this.roleDesc = roleDesc;
		this.updateUser = Session.get(RoleConstraint.C_SESSION_USER_NAME);

	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return RoleModel
	 *
	 */
	public final RoleModel getModel() {
		RoleModel model = new RoleModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.name = this.name;
		model.roleDesc = this.roleDesc;
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
			LogModel.logging(ste, RoleConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
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
		List<Integer> idx = (List<Integer>) Arrays.asList(1);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(RoleConstraint.C_ITEM_DETAIL.get(i),
					RoleConstraint.C_ITEM_TYPE.get(i),
					"＝" + RoleConstraint.C_ITEM_DISPLAY.get(i),
					RoleConstraint.C_ITEM_LABEL.get(i),
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
		IntStream.range(0, RoleConstraint.C_ITEM_DETAIL.size())
			.forEach(i -> item.add(
				new ItemForm(RoleConstraint.C_ITEM_DETAIL.get(i),
					RoleConstraint.C_ITEM_TYPE.get(i),
					RoleConstraint.C_ITEM_DISPLAY.get(i),
					RoleConstraint.C_ITEM_LABEL.get(i))
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
		IntStream.range(0, RoleConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(RoleConstraint.C_ITEM_DETAIL.get(i),
						RoleConstraint.C_ITEM_TYPE.get(i),
						RoleConstraint.C_ITEM_DISPLAY.get(i),
						RoleConstraint.C_ITEM_LABEL.get(i),
						(String) this.getClass().getDeclaredField(RoleConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
