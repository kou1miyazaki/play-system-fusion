package models.cmn;

import java.util.HashMap;
import java.util.List;

import utils.cmn.RSS;

/**
 *
 * RSS・モデル.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public class RSSModel extends SystemFusionModel {

	/** URL. */
	public String url;

	/** タイトル. */
	public String title;

	/** アイテム. */
	public List<HashMap<String, String>> item;

	/** コンストラクタ. */
	public RSSModel() {
	}

	/**
	 *
	 * コンストラクタ.
	 *
	 * @param url URL<br>
	 *
	 */
	public RSSModel(final String url) {
		this.url = url;
		this.title = RSS.getChannel(url).get("title");
		this.item = RSS.getItem(url);
	}

	/**
	 *
	 * @return List<SessionModel>
	 */
	public static List<RSSModel> getList() {
		return null;
	}
}