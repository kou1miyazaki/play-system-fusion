package samples.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.AccountConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.adm.AccountModel;
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
public class AccountForm extends SystemFusionForm {

	/** ID. */
	public String id = "";
	/** アカウントID. */
	public String accountId = "";
	/** パスワード. */
	public String password = "";
	/** アカウント名. */
	public String accountName = "";
	/** Eメールアドレス. */
	public String emailAddress = "";

	/** コンストラクタ. */
	public AccountForm() {
		//this.id = AccountConstraint.C_SYSTEM_LABEL_AUTOINC;
		this.createUser = Session.get(AccountConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param id ID<br>
	 * @param accountId アカウントID<br>
	 * @param password パスワード<br>
	 * @param accountName アカウント名<br>
	 * @param emailAddress Eメールアドレス<br>
	 *
	 */
	public AccountForm(final String id, final String accountId, final String password, final String accountName, final String emailAddress) {
		this.id = id;
		this.accountId = accountId;
		this.password = password;
		this.accountName = accountName;
		this.emailAddress = emailAddress;
		this.updateUser = Session.get(AccountConstraint.C_SESSION_USER_NAME);
	}

	/**
	 *
	 * モデルObjectを取得する.
	 *
	 * @return List
	 *
	 */
	public final AccountModel getModel() {
		AccountModel model = new AccountModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.accountId = this.accountId;
		model.password = this.password;
		model.accountName = this.accountName;
		model.emailAddress = this.emailAddress;
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
		if (StringUtils.isEmpty(accountId)) {
			errors.add(new ValidationError("accountId", Messages.get("account.bookmarkType.required")));
		}
		// 名前チェック.
		if (StringUtils.isEmpty(password)) {
			errors.add(new ValidationError("password", Messages.get("account.name.required")));
		}
		// URLチェック.
		if (StringUtils.isEmpty(accountName)) {
			errors.add(new ValidationError("accountName", Messages.get("account.url.required")));
		}

		// エラー表示.
		if (errors.size() > 0) {
			// ログ出力.
			StackTraceElement ste = new Throwable().getStackTrace()[0];
			LogModel.logging(ste, AccountConstraint.C_SYSTEM_APPL_ADM, Messages.get("common.validation"), this.toString());
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
		List<ItemForm> item = new ArrayList<ItemForm>();
		List<Integer> idx = (List<Integer>) Arrays.asList(3);
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(AccountConstraint.C_ITEM_DETAIL.get(i),
					AccountConstraint.C_ITEM_TYPE.get(i),
					"＝" + AccountConstraint.C_ITEM_DISPLAY.get(i),
					AccountConstraint.C_ITEM_LABEL.get(i),
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
		List<Integer> idx = (List<Integer>) Arrays.asList(0, 1, 3, 4);
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(AccountConstraint.C_ITEM_DETAIL.get(i),
					AccountConstraint.C_ITEM_TYPE.get(i),
					AccountConstraint.C_ITEM_DISPLAY.get(i),
					AccountConstraint.C_ITEM_LABEL.get(i))
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
		IntStream.range(0, AccountConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(AccountConstraint.C_ITEM_DETAIL.get(i),
							AccountConstraint.C_ITEM_TYPE.get(i),
							AccountConstraint.C_ITEM_DISPLAY.get(i),
							AccountConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(AccountConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
