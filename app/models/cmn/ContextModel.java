package models.cmn;

import java.util.List;

/**
 *
 * RSS・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class ContextModel extends SystemFusionModel {

	/** URL. */
	public String userName;

	/** タイトル. */
	public String remoteAddress;

	/** タイトル. */
	public String userAgent;

	/** コンストラクタ. */
	public ContextModel() {
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param url URL<br>
	 *
	 */
	public ContextModel(final String url) {
	}

	/**
	 *
	 * @return List<SessionModel>
	 */
	public static List<ContextModel> getList() {
		return null;
	}
}