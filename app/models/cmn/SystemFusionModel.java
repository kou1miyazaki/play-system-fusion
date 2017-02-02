package models.cmn;

import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import consts.adm.AccountConstraint;
import controllers.sys.LogController;

/**
 *
 * SystemFusionモデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
@MappedSuperclass
public abstract class SystemFusionModel extends Model {

	/** ログ出力. */
	public static final Logger logger = LoggerFactory.getLogger(LogController.class);

	/** ノート. */
	@Column(name = AccountConstraint.C_TABLE_COL_NOTE)
	public String note;

	/** 作成者. */
	@Column(name = AccountConstraint.C_TABLE_COL_CREATE_USER)
	public String createUser;

	/** 作成日. */
	@Column(name = AccountConstraint.C_TABLE_COL_CREATE_DATE)
	@CreatedTimestamp
	public Date createDate;

	/** 最終更新者. */
	@Column(name = AccountConstraint.C_TABLE_COL_UPDATE_USER)
	public String updateUser;

	/** 最終更新日. */
	@Column(name = AccountConstraint.C_TABLE_COL_UPDATE_DATE)
	@UpdatedTimestamp
	public Date updateDate;

	/**
	 * 文字列へ変換.
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Class: " + this.getClass().getCanonicalName() + "\n");
		sb.append("Settings:\n");
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				sb.append(field.getName() + " = " + field.get(this) + "\n");
			} catch (IllegalAccessException e) {
				sb.append(field.getName() + " = " + "access denied\n");
			}
		}
		return sb.toString();
	}

}