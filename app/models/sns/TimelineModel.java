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
import consts.adm.MenuConstraint;
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
@Entity(name = "Timeline")
@Table(name = "SNS_TT_TIMELINE")
public class TimelineModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = CommonConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= AccountConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name=AccountConstraint.C_TABLE_SEQ, allocationSize=25)
	public Integer id;

	/** アカウントコード. */
	@NotNull
	@Column(name = AccountConstraint.C_TABLE_COL_ACCOUNT_ID)
	public String accountId;

	/** パスワード. */
	@Column(name = "PARENT_ID")
	public Integer parentId;

	/** パスワード. */
	@Column(name = "MESSAGE")
	public String message;

	/** コンストラクタ. */
	public TimelineModel() {}

	/** コンストラクタ. */
	public TimelineModel(Integer id, String accountId, Integer parentId, String message) {
		this.id = id;
		this.accountId = accountId;
		this.parentId = parentId;
		this.message = message;
	}

	/**
	 * データベースからObjectModelを取得.
	 *
	 * @param Integer id
	 * @return AccountModel
	 */
	public static TimelineModel get(Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Account>
	 */
	public static List<TimelineModel> getList() {
		return TimelineModel.finder.where()
				.eq("PARENT_ID" ,0)
				.order(CommonConstraint.C_TABLE_COL_ID)
				.findList();
	}

	/**
	 *
	 *
	 * @param searchWord
	 * @return List<AccountModel>
	 */
	public static List<TimelineModel> getList(String searchWord) {

		if (searchWord == "") {
			return TimelineModel.finder.order("id").findList();
		} else {
			return TimelineModel.finder.where()
				.eq("PARENT_ID" ,0)
				.eq(AccountConstraint.C_TABLE_COL_ACCOUNT_ID ,searchWord)
				.order(CommonConstraint.C_TABLE_COL_ID).findList();
		}
	}

	/**
	 *
	 * @param menuType
	 * @return List<Bookmark>
	 */
	public static List<TimelineModel> getListChildren(Integer pParentId) {
		return TimelineModel.finder.where()
			.eq(MenuConstraint.C_TABLE_COL_PARENT_ID, pParentId)
			.order(CommonConstraint.C_TABLE_COL_ID)
			.findList();
	}

	/**
	 *
	 */
	public static Find<Long, TimelineModel> finder = new Find<Long, TimelineModel>(){};

	/**
	 *
	 * @return AccountModel
	 */
	public static TimelineModel convertToModel(MessageForm form) {
		TimelineModel model = new TimelineModel();
		model.id = StringUtils.isNotEmpty(form.id) ? Integer.valueOf(form.id).intValue() : null;
		model.message = form.message;
		return model;
	}

	/**
	 *
	 * @return AccountForm
	 */
	public static MessageForm convertToForm(TimelineModel model) {
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