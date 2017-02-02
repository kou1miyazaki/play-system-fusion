package models.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.Transaction;

import consts.cmn.CommonConstraint;
import consts.sys.LogConstraint;
import forms.sys.LogForm;
import models.cmn.SystemFusionModel;
import utils.cmn.DateParser;
import utils.cmn.Session;


/**
 *
 * @author kou1miyazaki
 *
 */
@Entity(name = LogConstraint.C_NAME)
@Table(name = LogConstraint.C_TABLE)
public class LogModel extends SystemFusionModel {

	/** ID. */
	@Id
	@Column(name = LogConstraint.C_TABLE_COL_ID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = LogConstraint.C_TABLE_SEQ)
	@SequenceGenerator(name = LogConstraint.C_TABLE_SEQ, allocationSize = LogConstraint.C_SIZE_ALLOCATION)
	public Integer id;

	/** アプリケションタイプ. */
	@Column(name = LogConstraint.C_TABLE_COL_APP_TYPE)
	public String appType;

	/** UUID. */
	@Column(name = LogConstraint.C_TABLE_COL_UUID)
	public String uuid;

	/** タイトル. */
	@Column(name = LogConstraint.C_TABLE_COL_LOG_TITLE)
	public String logTitle;

	/** 本文. */
	@Column(name = LogConstraint.C_TABLE_COL_LOG_BODY)
	public String logBody;

	/** クラス名. */
	@Column(name = LogConstraint.C_TABLE_COL_CLASS_NAME)
	public String className;

	/** メソッド名. */
	@Column(name = LogConstraint.C_TABLE_COL_METHOD_NAME)
	public String methodName;

	/** コンストラクタ. */
	public LogModel() {
	}

	/** コンストラクタ. */
	public LogModel(StackTraceElement ste, String appType, String logTitle, String logBody) {

		Date datNow = new Date(System.currentTimeMillis());

		this.appType = appType;
		if (!Session.get(CommonConstraint.C_SESSION_UUID).isEmpty()) {
			this.uuid = Session.get(CommonConstraint.C_SESSION_UUID);
		}
		this.logTitle = logTitle;
		this.logBody = logBody;
		this.className = ste.getClassName();
		this.methodName = ste.getMethodName();
		this.createDate = datNow;
		this.updateDate = datNow;
		if (!Session.get(LogConstraint.C_SESSION_USER_NAME).isEmpty()) {
			this.createUser = Session.get(LogConstraint.C_SESSION_USER_NAME);
			this.updateUser = Session.get(LogConstraint.C_SESSION_USER_NAME);
		}
	}

	/**
	 *
	 */
	public LogForm getForm() {
		LogForm form = new LogForm();
		form.id = this.id.toString();
		form.appType = this.appType;
		form.uuid = this.uuid;
		form.logTitle = this.logTitle;
		form.logBody = this.logBody;
		form.className = this.className;
		form.methodName = this.methodName;
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
	public static Find<Long, LogModel> finder = new Find<Long, LogModel>() {
	};

	/**
	 * データベースからObjectModelを取得.
	 *
	 * @param Integer id
	 * @return AccountModel
	 */
	public static LogModel get(Integer id) {
		return finder.byId(id.longValue());
	}

	/**
	 *
	 *
	 * @return List<Account>
	 */
	public static List<LogModel> getList() {
		return LogModel.finder.orderBy(LogConstraint.C_TABLE_COL_ID + " desc").findList();
	}

	/**
	 *
	 *
	 * @param searchWor
	 * @return List<AccountModel>
	 */
	public static List<LogModel> getList(String searchWord) {

		if (searchWord == "") {
			return LogModel.finder.order(LogConstraint.C_TABLE_COL_ID).findList();
		} else {
			return LogModel.finder.where()
				.eq(LogConstraint.C_TABLE_COL_APP_TYPE ,searchWord)
				.orderBy(LogConstraint.C_TABLE_COL_ID + " desc").findList();
		}
	}

	/**
	 *
	 *
	 * @return PagedList
	 *
	 */
	public static PagedList<LogModel> getPagedList(Integer page, String logTitle) {

		int pageSize = LogConstraint.C_SIZE_PAGE;
		int pageIndex = page - 1;

		if (logTitle.isEmpty()) {
			return LogModel.finder
				.orderBy(LogConstraint.C_TABLE_COL_ID + " desc").findPagedList(pageIndex, pageSize);
		} else {
			return LogModel.finder.where()
				.orderBy(LogConstraint.C_TABLE_COL_ID + " desc").findPagedList(pageIndex, pageSize);
		}
	}

	public static void logging(StackTraceElement ste, String appType, String logTitle, String logBody) {

		// ログ出力（ファイル）.
		logger.info(DateParser.datetimeToString(new Date()) + " : " + ste.getClassName() + "." + ste.getMethodName());

		// ログ出力（データベース）.
		try (Transaction txn = Ebean.beginTransaction()) {
			txn.setBatchMode(true);
			new LogModel(ste, appType, logTitle, logBody).save();
			txn.commit();
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}
}