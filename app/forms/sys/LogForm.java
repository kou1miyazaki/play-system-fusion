package forms.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import consts.adm.AccountConstraint;
import consts.sys.LogConstraint;
import forms.cmn.ItemForm;
import forms.cmn.SystemFusionForm;
import models.sys.LogModel;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import utils.cmn.DateParser;

/**
 *
 * ログ・フォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class LogForm extends SystemFusionForm {

	public String id = "";
	public String appType = "";
	public String uuid = "";
	public String logTitle = "";
	public String logBody = "";
	public String className = "";
	public String methodName = "";
	/** 作成者. */
	public String createUser;
	/** 作成日. */
	public String createDate;

	/** コンストラクタ. */
	public LogForm() {
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @return LogModel
	 *
	 */
	public final LogModel getModel() {
		LogModel model = new LogModel();
		model.id = StringUtils.isNotEmpty(this.id) ? Integer.valueOf(this.id).intValue() : null;
		model.appType = this.appType;
		model.uuid = this.uuid;
		model.logTitle = this.logTitle;
		model.logBody = this.logBody;
		model.className = this.className;
		model.methodName = this.methodName;
		model.createDate = DateParser.stringToDate(this.createDate);
		model.createUser = this.createUser;
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
		List<Integer> idx = (List<Integer>) Arrays.asList(2, 3, 5);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(LogConstraint.C_ITEM_DETAIL.get(i),
					LogConstraint.C_ITEM_TYPE.get(i),
					"＝" + LogConstraint.C_ITEM_DISPLAY.get(i),
					LogConstraint.C_ITEM_LABEL.get(i),
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
		List<Integer> idx = (List<Integer>) Arrays.asList(0, 1, 2, 3, 4, 7, 5, 6);
		List<ItemForm> item = new ArrayList<ItemForm>();
		idx.stream()
			.forEach(i -> item.add(
				new ItemForm(LogConstraint.C_ITEM_DETAIL.get(i),
					LogConstraint.C_ITEM_TYPE.get(i),
					LogConstraint.C_ITEM_DISPLAY.get(i),
					LogConstraint.C_ITEM_LABEL.get(i))
				)
			);
		return item;
	}

	/**
	 *
	 * 詳細アイテムを取得する.
	 *
	 * @return List
	 *
	 */
	public final List<ItemForm> getItem() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		IntStream.range(0, LogConstraint.C_ITEM_DETAIL.size())
		.forEach(i -> {
			try {
				item.add(
					new ItemForm(LogConstraint.C_ITEM_DETAIL.get(i),
							LogConstraint.C_ITEM_TYPE.get(i),
							LogConstraint.C_ITEM_DISPLAY.get(i),
							LogConstraint.C_ITEM_LABEL.get(i),
							(String) this.getClass().getDeclaredField(LogConstraint.C_ITEM_DETAIL.get(i)).get(this)
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
