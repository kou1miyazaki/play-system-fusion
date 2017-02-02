package samples.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.avaje.ebean.PagedList;

import consts.adm.AccountConstraint;
import forms.adm.AccountForm;
import lombok.Getter;
import lombok.Setter;
import models.cmn.SystemFusionModel;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import utils.cmn.DateParser;

/**
 *
 * アカウント・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@Entity(name = AccountConstraint.C_NAME)
@Table(name = AccountConstraint.C_TABLE)
public class AccountModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = AccountConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AccountConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = AccountConstraint.C_TABLE_SEQ, allocationSize = AccountConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** アカウントコード. */
	@NotNull
	@Required(message = "必須入力です")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "半角英数字のみで入力してください。")
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_ID)
	@Getter
	@Setter
	public String accountId;

	/** パスワード. */
	@Column(name = AccountConstraint.C_TABLE_COL_PASSWORD)
	public String password;

	/** アカウント名. */
	@NotNull
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_NAME)
	public String accountName;

	/** メールアドレス. */
	@Email(message = "Emailの形式で入力して下さい。")
	@Column(name = AccountConstraint.C_TABLE_COL_EMAIL_ADDRESS)
	public String emailAddress;

	/** コンストラクタ. */
	public AccountModel() {
	}

	/** コンストラクタ. */
	public AccountModel(Integer id, String accountId, String password, String accountName, String emailAddress) {
		this.id = id;
		this.accountId = accountId;
		this.password = password;
		this.accountName = accountName;
		this.emailAddress = emailAddress;
	}

	/**
	 *
	 * フォームObjectを取得.
	 *
	 * @return AccountForm
	 */
	public final AccountForm getForm() {
		AccountForm form = new AccountForm();
		form.id = this.id.toString();
		form.accountId = this.accountId;
		form.password = this.password;
		form.accountName = this.accountName;
		form.emailAddress = this.emailAddress;
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
	public static Find<Long, AccountModel> finder = new Find<Long, AccountModel>() {
	};

	/**
	 *
	 * データベースからObjectModelを取得.
	 *
	 * @param id ID<br>
	 * @return AccountModel
	 */
	public static AccountModel get(final Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Account>
	 */
	public static List<AccountModel> getList() {
		return AccountModel.finder.order(AccountConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param accountId アカウントID<br>
	 * @return List<AccountModel>
	 */
	public static List<AccountModel> getList(final String accountId) {

		if (accountId.isEmpty()) {
			return AccountModel.finder.order(AccountConstraint.C_TABLE_COL_ID).findList();
		} else {
			return AccountModel.finder.where()
				.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID, accountId)
				.order(AccountConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 * ページリストを取得する.
	 *
	 * @param page ページ数<br>
	 * @param accountId 検索ワード<br>
	 * @return List<AccountModel>
	 *
	 */
	public static PagedList<AccountModel> getPagedList(final Integer page, final String accountId) {

		int pageSize = AccountConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (accountId.isEmpty()) {
			return AccountModel.finder.order(AccountConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		} else {
			return AccountModel.finder.where()
				.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID, accountId)
				.order(AccountConstraint.C_TABLE_COL_ID).findPagedList(pageIndex, pageSize);
		}
	}

	/**
	 *
	 *
	 */
	public static boolean isLogin(final String accountId, final String password) {

		Integer cnt = AccountModel.finder.where()
			.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID, accountId)
			.eq(AccountConstraint.C_TABLE_COL_PASSWORD, password)
			.findRowCount();

		System.out.println(cnt.toString());
		return (cnt > 0) ? true : false;
	}
}
