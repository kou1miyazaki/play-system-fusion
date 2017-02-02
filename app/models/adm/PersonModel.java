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

import org.apache.commons.lang3.StringUtils;

import consts.adm.AccountConstraint;
import forms.adm.AccountForm;
import models.cmn.SystemFusionModel;

/** エンティティ. */
@Entity(name = AccountConstraint.C_NAME)
@Table(name = AccountConstraint.C_TABLE)
/**
 *
 * @author kou1miyazaki
 *
 */
public class PersonModel extends SystemFusionModel {

	/** ID */
	@Id
	@Column(name = AccountConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= AccountConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name=AccountConstraint.C_TABLE_SEQ, allocationSize=25)
	public Integer id;

	/** アカウントコード */
	@NotNull
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_ID)
	public String accountId;

	/** パスワード */
	@Column(name = AccountConstraint.C_TABLE_COL_PASSWORD)
	public String password;

	/** アカウント名 */
	@NotNull
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_NAME)
	public String accountName;

	/** メールアドレス */
	@Column(name = AccountConstraint.C_TABLE_COL_EMAIL_ADDRESS)
	public String emailAddress;

	/** コンストラクタ */
	public PersonModel() {}

	/** コンストラクタ */
	public PersonModel(Integer id, String accountId, String password, String accountName, String emailAddress) {
		this.id = id;
		this.accountId = accountId;
		this.password = password;
		this.accountName = accountName;
		this.emailAddress = emailAddress;
	}

	/**
	 *
	 */
	public static Find<Long, PersonModel> finder = new Find<Long, PersonModel>(){};

	/**
	 * データベースからObjectModelを取得.
	 *
	 * @param Integer id
	 * @return AccountModel
	 */
	public static PersonModel get(Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Account>
	 */
	public static List<PersonModel> getList() {
		return PersonModel.finder.order(AccountConstraint.C_TABLE_COL_ID).findList();
	}

	/**
	 *
	 *
	 * @param searchWord
	 * @return List<AccountModel>
	 */
	public static List<PersonModel> getList(String searchWord) {

		if (searchWord == "") {
			return PersonModel.finder.order("id").findList();
		} else {
			return PersonModel.finder.where()
				.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID ,searchWord)
				.order(AccountConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 * @return AccountModel
	 */
	public static PersonModel convertToModel(AccountForm form) {
		PersonModel model = new PersonModel();
		model.id = StringUtils.isNotEmpty(form.id) ? Integer.valueOf(form.id).intValue() : null;
		model.accountId = form.accountId;
		model.password = form.password;
		model.accountName = form.accountName;
		model.emailAddress = form.emailAddress;
		return model;
	}

	/**
	 *
	 * @return AccountForm
	 */
	public static AccountForm convertToForm(PersonModel model) {
		AccountForm form = new AccountForm();
		form.id = model.id.toString();
		form.accountId = model.accountId;
		form.password = model.password;
		form.accountName = model.accountName;
		form.emailAddress = model.emailAddress;
		return form;
	}
}