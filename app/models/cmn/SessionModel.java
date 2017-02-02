package models.cmn;

import java.util.ArrayList;
import java.util.List;

import consts.cmn.CommonConstraint;
import utils.cmn.Session;

/**
 *
 * セッション・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class SessionModel extends SystemFusionModel {

	/** 名前. */
	public String name;

	/** 値. */
	public String value;

	/** コンストラクタ. */
	public SessionModel() {
	}

	/** コンストラクタ. */
	public SessionModel(String pName, String pValue) {
		this.name = pName;
		this.value = pValue;
	}

	/**
	 *
	 * @return List<SessionModel>
	 */
	public static List<SessionModel> getList() {

		// セッションリスト作成.
		String data[] = {
			CommonConstraint.C_SESSION_UUID,
			CommonConstraint.C_SESSION_USER_NAME,
			"pMode",
			CommonConstraint.C_SESSION_TAB,
			CommonConstraint.C_SESSION_URL

		};

		// リスト作成.
		List<SessionModel> item = new ArrayList<SessionModel>();

		for (String name: data) {
			item.add(new SessionModel(name,Session.get(name)));
		}

		return item;
	}
}