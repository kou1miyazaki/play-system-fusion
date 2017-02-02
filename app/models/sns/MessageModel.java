package models.sns;

import java.util.ArrayList;
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
import consts.cmn.CommonConstraint;
import forms.cmn.ItemForm;
import forms.sns.MessageForm;
import models.cmn.SystemFusionModel;
import play.i18n.Messages;

/**
 *
 * @author kou1miyazaki
 *
 */
@Entity(name = "Message")
@Table(name = "SNS_TT_MESSAGE")
public class MessageModel extends SystemFusionModel {

	/** ID */
	@Id
	@Column(name = CommonConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "SNS_TT_MESSAGE_SEQ")
	@SequenceGenerator(name="SNS_TT_MESSAGE_SEQ", allocationSize=25)
	public Integer id;

	/** アカウントコード */
	@NotNull
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_ID)
	public String accountId;

	/** パスワード */
	@Column(name = "MESSAGE")
	public String message;

	/** コンストラクタ */
	public MessageModel() {}

	/** コンストラクタ */
	public MessageModel(Integer id, String accountId, String message) {
		this.id = id;
		this.accountId = accountId;
		this.message = message;
	}

	/**
	 * データベースからObjectModelを取得.
	 *
	 * @param Integer id
	 * @return AccountModel
	 */
	public static MessageModel get(Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Account>
	 */
	public static List<MessageModel> getList() {
		return MessageModel.finder.orderBy(CommonConstraint.C_TABLE_COL_ID + " desc").findList();
	}

	/**
	 *
	 *
	 * @param searchWord
	 * @return List<AccountModel>
	 */
	public static List<MessageModel> getList(String searchWord) {

		if (searchWord == "") {
			return MessageModel.finder.order("id").findList();
		} else {
			return MessageModel.finder.where()
				.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID ,searchWord)
				.orderBy("ID desc")
				.findList();
		}
	}
	/**
	 *
	 */
	public static Find<Long, MessageModel> finder = new Find<Long, MessageModel>(){};

	/**
	 *
	 * @return AccountModel
	 */
	public static MessageModel convertToModel(MessageForm form) {
		MessageModel model = new MessageModel();
		model.id = StringUtils.isNotEmpty(form.id) ? Integer.valueOf(form.id).intValue() : null;
		model.message = form.message;
		return model;
	}

	/**
	 *
	 * @return AccountForm
	 */
	public static MessageForm convertToForm(MessageModel model) {
		MessageForm form = new MessageForm();
		form.id = model.id.toString();
		form.message = model.message;
		return form;
	}

	/**
	 *
	 * @return List<Bookmark>
	 */
	public static List<ItemForm> getSearch(String searchWord) {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		item.add(new ItemForm("accountId", "text", "", Messages.get("account.accountId"), searchWord));

		return item;
	}

	/**
	 *
	 * @return List<ItemForm>
	 */
	public static List<ItemForm> convertToItem() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		item.add(new ItemForm("id", "text", "col1", Messages.get("account.id")));
		item.add(new ItemForm("message", "text", "col1", Messages.get("account.accountId")));

		return item;
	}

	/**
	 *
	 * @return List<Bookmark>
	 */
	public static List<ItemForm> convertToItem(MessageForm form) {

	    // リスト作成.
		List<ItemForm> item = convertToItem();
		item.get(0).value = form.id;
		item.get(1).value = form.message;

		return item;
	}
}