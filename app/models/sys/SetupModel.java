package models.sys;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.Transaction;

import models.adm.AccountModel;
import models.adm.BookmarkModel;
import models.adm.MenuModel;
import models.adm.PortalModel;
import models.adm.PortletModel;
import models.adm.PortletParameterModel;
import models.cmn.SystemFusionModel;
import models.dbx.TableColumnModel;
import models.dbx.TableDataModel;
import models.dbx.TableModel;
import models.sns.TimelineModel;

/**
 *
 * @author kou1miyazaki
 *
 */
public class SetupModel extends SystemFusionModel {

	private static final Logger logger = LoggerFactory.getLogger(SetupModel.class);

	/** コンストラクタ */
	public SetupModel() {}

	public static void init() {

		// ログ出力.
		StackTraceElement ste = new Throwable().getStackTrace()[0];
		LogModel.logging(ste, "SYS", "セットアップ", "");

		try (Transaction txn = Ebean.beginTransaction()) {

		txn.setBatchMode(true);
		txn.setBatchSize(100000);
		txn.setBatchGetGeneratedKeys(false);

		SqlUpdate deleteSql = null;

		logger.info("Insert Portal..");

		deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_PORTAL");
		deleteSql.execute();
		deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_PORTLET");
		deleteSql.execute();
		deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_PORTLET_PARAMETER");
		deleteSql.execute();

		try {
			new PortalModel( 1, "index", "home", "ホーム", "/portal/index/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel( 2, "index", "private", "プライベート", "/portal/index/private", "/assets/images/icon/home.png", "2aLayout").save();
			new PortalModel( 3, "index", "wall", "ウォール", "/portal/index/wall", "/assets/images/icon/home.png", "default").save();
			new PortalModel( 4, "index", "dashbord", "ダッシュボード", "/portal/index/dashbord", "/assets/images/icon/home.png", "default").save();
			new PortalModel( 5, "bsc", "home", "ホーム", "/portal/bsc/home", "/assets/images/icon/bsc.png", "default").save();
			new PortalModel( 6, "bsc", "01", "顧客満足", "/portal/bsc/01", "/assets/images/icon/bsc.png", "default").save();
			new PortalModel( 7, "bsc", "02", "管理会計", "/portal/bsc/02", "/assets/images/icon/bsc.png", "default").save();
			new PortalModel( 8, "bsc", "03", "業務プロセス", "/portal/bsc/03", "/assets/images/icon/bsc.png", "default").save();
			new PortalModel( 9, "bsc", "04", "学習・成長", "/portal/bsc/04", "/assets/images/icon/bsc.png", "default").save();
			new PortalModel(10, "crm", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel(11, "app", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel(12, "col", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel(13, "dev", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel(14, "dbx", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();
			new PortalModel(15, "sec", "home", "ホーム", "/portal/security/home", "/assets/images/icon/home.png", "default").save();

			txn.commit();
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		logger.info("Insert Portlet..");

		try {
			new PortletModel( 1, "index", "home", "col1", "IFrame", "テスト1", true).save();
			new PortletModel( 2, "index", "home", "col1", "EmbedContent", "Youtube", true).save();
			new PortletModel( 3, "index", "home", "col1", "EmbedContent", "SlideShare", true).save();
			new PortletModel( 4, "index", "home", "col2", "BijinClock", "美人時計", true).save();
			new PortletModel( 5, "index", "home", "col2", "NewsWeather", "天気", true).save();
			new PortletModel( 6, "index", "common", "head1", "GoogleSearch", "Google Search", false).save();
			new PortletModel( 7, "index", "home", "col2", "GoogleTranslate", "Google 翻訳", true).save();
			new PortletModel( 8, "index", "home", "col2", "Bookmark", "テスト1", true).save();
			new PortletModel( 9, "index", "home", "col3", "RSSFeed", "テスト1", true).save();
			new PortletModel(10, "index", "private", "col1", "Carousel", "テスト1", true).save();
			new PortletModel(11, "index", "private", "col2", "Facebook", "テスト1", true).save();
			new PortletModel(12, "index", "private", "col2", "Twitter", "テスト1", true).save();
			//new PortletModel(13, "index", "home", "col2", "RSSFeed", "テスト1", true).save();
			new PortletModel(15, "sec", "home", "head1", "Alart", "テスト1", true).save();
			new PortletModel(16, "sec", "home", "head2", "Alart", "テスト1", true).save();
			new PortletModel(17, "sec", "home", "head3", "Alart", "テスト1", true).save();
			new PortletModel(18, "sec", "home", "head4", "Alart", "テスト1", true).save();
			new PortletModel(19, "sec", "home", "col4", "IFrame", "テスト1", true).save();
			//new PortletModel(20, "index", "private", "col3", "RSSFeed", "テスト1", true).save();
			new PortletModel(21, "sec", "home", "col1", "RSSFeed", "テスト1", true).save();
			new PortletModel(22, "sec", "home", "col2", "RSSFeed", "テスト1", true).save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert Portlet Parameter..");

		try {
			new PortletParameterModel( 1, 1, "url", "http://52.196.214.8/news/").save();
			new PortletParameterModel( 2, 2, "type", "youtube").save();
			new PortletParameterModel( 3, 2, "key", "DDJut50TzmQ").save();
			new PortletParameterModel( 4, 3, "type", "slideshare").save();
			new PortletParameterModel( 5, 3, "key", "iSPnhOuHuEnnQo").save();
			new PortletParameterModel( 6, 8, "bookmarkType", "partner").save();
			new PortletParameterModel( 7, 9, "url", "http://52.196.214.8/news/index.php/feed/").save();
			//new PortletParameterModel( 8,13, "url", "https://news.google.com/news?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss&topic=irhttp://52.196.214.8/2ch/?feed=rss2").save();
			new PortletParameterModel( 9,15, "alartType", "warning").save();
			new PortletParameterModel(10,15, "message", "Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, <a href='#' class='alert-link'>vel scelerisque nisl consectetur et</a>.").save();
			new PortletParameterModel(11,16, "alartType", "danger").save();
			new PortletParameterModel(12,16, "message", "<strong>Oh snap!</strong> <a href='#' class='alert-link'>Change a few things up</a> and try submitting again.").save();
			new PortletParameterModel(13,17, "alartType", "success").save();
			new PortletParameterModel(14,17, "message", "<strong>Well done!</strong> You successfully read <a href='#' class='alert-link'>this important alert message</a>.").save();
			new PortletParameterModel(15,18, "alartType", "info").save();
			new PortletParameterModel(16,18, "message", "<strong>Heads up!</strong> This <a href='#' class='alert-link'>alert needs your attention</a>, but it's not super important.").save();
			new PortletParameterModel(17,19, "url", "http://www.security-next.com/feed/").save();
			new PortletParameterModel(18,20, "url", "http://www.security-next.com/feed").save();
			new PortletParameterModel(19,21, "url", "http://jvndb.jvn.jp/ja/rss/jvndb_new.rdf").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert Portlet..done");

		logger.info("Insert Account..");

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_ACCOUNT");
			deleteSql.execute();

			new AccountModel( 1,  "miyazaki", "miyazaki", "宮崎　晃一", "miyazaki@city.co.jp").save();
			new AccountModel( 2,  "test", "test", "テスト", "miyazaki@city.co.jp").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert Account..done");

		logger.info("Insert Menu..");

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_MENU");
			deleteSql.execute();

			new MenuModel( 1, 0, "MENU1", "url", "ホーム"				, "", "/assets/images/icon/home.png").save();
			new MenuModel( 2, 1, "MENU1", "url", "ポ－タル"				, "/portal/index/home", "/assets/images/icon/portal.png").save();
			new MenuModel( 3, 1, "MENU1", "url", "連携アプリケーション"	, "/tab/cmn", "/assets/images/icon/tab.png").save();
			new MenuModel( 4, 1, "MENU1", "url", "リンク"				, "/bookmark/dial/cmn", "/assets/images/icon/link.png").save();
			new MenuModel( 5, 1, "MENU1", "url", "社内システム"			, "/bookmark/tab/ca/27", "/assets/images/icon/home.png").save();
			new MenuModel( 6, 1, "MENU1", "url", "パートナー"			, "/bookmark/tab/partner/1", "/assets/images/icon/home.png").save();
			new MenuModel( 7, 1, "MENU1", "url", "動画"					, "/tab/bookmark", "/assets/images/icon/home.png").save();
			new MenuModel( 8, 1, "MENU1", "url", "Google"				, "/bookmark/dial/google", "/assets/images/icon/google.png").save();
			new MenuModel( 9, 1, "MENU1", "url", "ブックマーク：タブ"			, "/bookmark/tab/A/50", "/assets/images/icon/home.png").save();
			new MenuModel(10, 1, "MENU1", "url", "ブックマーク：メニュー"		, "/bookmark/menu/B/5", "/assets/images/icon/home.png").save();
			new MenuModel(11, 1, "MENU1", "url", "ブックマーク：ダイアル"		, "/bookmark/dial/partner", "/assets/images/icon/home.png").save();
			new MenuModel(12, 1, "MENU1", "iframe", "全文検索(FESS)"	, "http://search.n2sm.co.jp/", "/assets/images/icon/home.png").save();
			new MenuModel(13, 0, "MENU1", "url", "経営(AI)"				, "", "/assets/images/icon/bsc.png").save();
			new MenuModel(14,13, "MENU1", "url", "ポ－タル"				, "/portal/bsc/home", "/assets/images/icon/portal.png").save();
			new MenuModel(15,13, "MENU1", "url", "連携アプリケーション"	, "/tab/bsc", "/assets/images/icon/tab.png").save();
			new MenuModel(16,13, "MENU1", "url", "リンク"				, "/bookmark/dial/bsc", "/assets/images/icon/link.png").save();
			new MenuModel(17, 0, "MENU1", "url", "顧客"					, "", "/assets/favicon.png").save();
			new MenuModel(18,17, "MENU1", "url", "ポ－タル"				, "/portal/crm/home", "/assets/images/icon/portal.png").save();
			new MenuModel(19,17, "MENU1", "url", "連携アプリケーション"	, "/tab/crm", "/assets/images/icon/tab.png").save();
			new MenuModel(20,17, "MENU1", "url", "リンク"				, "/bookmark/dial/crm", "/assets/images/icon/link.png").save();
			new MenuModel(21, 0, "MENU1", "url", "業務"					, "", "/assets/favicon.png").save();
			new MenuModel(22,21, "MENU1", "url", "ポ－タル"				, "/portal/app/home", "/assets/images/icon/portal.png").save();
			new MenuModel(23,21, "MENU1", "url", "連携アプリケーション"	, "/tab/app", "/assets/images/icon/tab.png").save();
			new MenuModel(24,21, "MENU1", "url", "リンク"				, "/bookmark/dial/app", "/assets/images/icon/link.png").save();
			new MenuModel(25, 0, "MENU1", "url", "協業"				, "", "/assets/favicon.png").save();
			new MenuModel(26,25, "MENU1", "url", "ポ－タル"				, "/portal/col/home", "/assets/images/icon/portal.png").save();
			new MenuModel(27,25, "MENU1", "url", "連携アプリケーション"	, "/tab/col", "/assets/images/icon/tab.png").save();
			new MenuModel(28,25, "MENU1", "url", "リンク"				, "/bookmark/dial/col", "/assets/images/icon/link.png").save();
			new MenuModel(29,25, "MENU1", "div"	, ""					, "", "").save();
			new MenuModel(30,25, "MENU1", "url", "タイムライン"			, "/timeline/index", "/assets/favicon.png").save();
			new MenuModel(31,25, "MENU1", "url", "LINE風メッセンジャー"			, "/message/index", "/assets/images/icon/line.jpg").save();
			new MenuModel(32, 0, "MENU1", "url", "開発"					, "", "/assets/favicon.png").save();
			new MenuModel(33,32, "MENU1", "url", "ポ－タル"				, "/portal/dev/home", "/assets/images/icon/portal.png").save();
			new MenuModel(34,32, "MENU1", "url", "連携アプリケーション"	, "/tab/dev", "/assets/images/icon/tab.png").save();
			new MenuModel(35,32, "MENU1", "url", "リンク"				, "/bookmark/dial/dev", "/assets/images/icon/link.png").save();
			new MenuModel(36,32, "MENU1", "iframe", "テストデータ作成"	, "http://www.databasetestdata.com/", "/assets/favicon.png").save();
			new MenuModel(37, 0, "MENU1", "url", "情報"					, "", "/assets/images/icon/data.jpg").save();
			new MenuModel(38,37, "MENU1", "url", "ポ－タル"				, "/portal/dbx/home", "/assets/images/icon/portal.png").save();
			new MenuModel(39,37, "MENU1", "url", "連携アプリケーション"	, "/tab/dbx", "/assets/images/icon/tab.png").save();
			new MenuModel(40,37, "MENU1", "url", "リンク"				, "/bookmark/dial/dbx", "/assets/images/icon/link.png").save();
			new MenuModel(41,37, "MENU1", "div"	, ""					, "", "").save();
			new MenuModel(42,37, "MENU1", "url", "データベース管理"		, "/tab/dbx1", "/assets/images/icon/data.jpg").save();
			new MenuModel(43,37, "MENU1", "url", "データベース"			, "/database/TEST/index", "/assets/images/icon/data.jpg").save();
			new MenuModel(44,37, "MENU1", "url", "データベース"			, "/database/SAMPLE/index", "/assets/images/icon/data.jpg").save();
			new MenuModel(45, 0, "MENU1", "url", "安全"					, "", "/assets/images/icon/sec.png").save();
			new MenuModel(46,45, "MENU1", "url", "ポ－タル"				, "/portal/sec/home", "/assets/images/icon/portal.png").save();
			new MenuModel(47,45, "MENU1", "url", "連携アプリケーション"	, "/tab/sec", "/assets/images/icon/tab.png").save();
			new MenuModel(48,45, "MENU1", "url", "リンク"				, "/bookmark/dial/sec", "/assets/images/icon/link.png").save();
			new MenuModel(49, 0, "MENU2", "url", "管理"					, "", "/assets/images/icon/admin.png").save();
			new MenuModel(50,49, "MENU2", "url", "ポ－タル"				, "/portal/adm/home", "/assets/images/icon/portal.png").save();
			new MenuModel(51,49, "MENU2", "url", "連携アプリケーション"	, "/tab/adm", "/assets/images/icon/tab.png").save();
			new MenuModel(52,49, "MENU2", "url", "リンク"				, "/bookmark/dial/adm", "/assets/images/icon/link.png").save();
			new MenuModel(53,49, "MENU2", "div"	, ""					, "", "").save();
			new MenuModel(54,49, "MENU2", "url", "システム管理"			, "/tab/adm1", "/assets/images/icon/admin.png").save();
			new MenuModel(55,49, "MENU2", "url", "ポータル管理"			, "/tab/adm2", "/assets/images/icon/admin.png").save();
			new MenuModel(56,49, "MENU2", "url", "共有管理"				, "/tab/adm3", "/assets/images/icon/admin.png").save();
			new MenuModel(57,49, "MENU2", "url", "システム情報"			, "/tab/sys1", "/assets/images/icon/admin.png").save();
			new MenuModel(58, 0, "MENU2", "url", "個人"					, "", "/assets/images/icon/person.png").save();
			new MenuModel(59,58, "MENU2", "url", "プロファイル"			, "/profile", "/assets/images/icon/person.png").save();
			new MenuModel(60,58, "MENU2", "url", "メッセージ"			, "/logout", "/assets/images/icon/person.png").save();
			new MenuModel(61,58, "MENU2", "url", "個人設定"				, "/logout", "/assets/images/icon/person.png").save();
			new MenuModel(62,58, "MENU2", "div"	, ""					, "", "").save();
			new MenuModel(63,58, "MENU2", "url", "ログアウト"			, "/logout", "/assets/images/icon/person.png").save();
			new MenuModel(101,  0, "cmn", "iframe", "WordPress"			, "http://52.196.214.8/news/", "/assets/images/icon/home.png").save();
			new MenuModel(102,  0, "cmn", "iframe", "infoscoop"			, "http://demo.infoscoop.org/infoscoop/index.jsp", "/assets/images/icon/home.png").save();
			new MenuModel(103,  0, "crm", "iframe", "F-RevoCRM"			, "http://onion.vtest01.info/frevocrm62-demo/", "/assets/favicon.png").save();
			new MenuModel(104,  0, "app", "iframe", "oddo"				, "https://demo9.cloud1-oscg.biz/web", "/assets/favicon.png").save();
			new MenuModel(105,  0, "app", "iframe", "iDempiere"			, "http://jpiere.net/webui/&tabType=app", "/assets/favicon.png").save();
			new MenuModel(106,  0, "app", "iframe", "MosP"				, "http://demo.mosp.jp/human4/srv/", "/assets/favicon.png").save();
			new MenuModel(107,  0, "app", "iframe", "EC-CUBE"			, "http://site3.ec-cube.net/admin/", "/assets/favicon.png").save();
			new MenuModel(108,  0, "app", "iframe", "RacNote"			, "http://rn.cuore-cloud.net/overview/overview/", "/assets/favicon.png").save();
			new MenuModel(109,  0, "col", "iframe", "Aipo5"				, "http://cpaneldemo.cpanel-plesk.net/aipo/", "/assets/images/icon/aipo.png").save();
			new MenuModel(110,  0, "col", "iframe", "GroupSession"		, "http://groupsession.jp/gsession/common/cmn001.do", "/assets/favicon.png").save();
			new MenuModel(111,  0, "col", "iframe", "抹茶SNS"			, "http://oss.icz.co.jp/demo/sns/homes", "/assets/favicon.png").save();
			new MenuModel(112,  0, "col", "iframe", "Limesurvey"		, "http://demo.limesurvey.org/index.php?r=admin", "/assets/images/icon/limesurvey.jpg").save();
			new MenuModel(113,  0, "col", "iframe", "piwigo"			, "http://piwigo.org/demo/", "/assets/images/icon/piwigo.png").save();
			new MenuModel(114,  0, "col", "iframe", "OpenUpload"		, "http://demo5.d-ip.biz/openupdemo/index.php", "/assets/favicon.png").save();
			new MenuModel(115,  0, "col", "iframe", "OpenMeetings"		, "https://om.alteametasoft.com/openmeetings/signin", "/assets/images/icon/openmeetings.png").save();
			new MenuModel(116,  0, "dev", "iframe", "Trac"				, "http://trac.hln.co.jp/demo/wiki", "/assets/favicon.png").save();
			new MenuModel(117,  0, "dev", "iframe", "TestLink"			, "https://www.softaculous.com/demos/TestLink", "/assets/favicon.png").save();
			new MenuModel(118,  0, "dev", "iframe", "Jenkins"			, "https://builds.apache.org/", "/assets/favicon.png").save();
			new MenuModel(119,  0, "dbx", "iframe", "Yellowfin"			, "http://demo.jp.yellowfin.bi/YFEntry.i4?key=LGfP0Iz7%2FRWQ9rE3htntOy1cIM2%2BPoxu", "/assets/favicon.png").save();
			new MenuModel(120,  0, "dbx", "iframe", "Pentaho"			, "http://pentaho.octaba.org/pentaho/Home", "/assets/images/icon/pentaho.png").save();
			new MenuModel(121,  0, "dbx", "iframe", "Jasper BI"			, "http://demo.youranalysis.net:18080/jasperserver-pro/flow.html?_flowId=homeFlow", "/assets/images/icon/jasper.png").save();
			new MenuModel(122,  0, "dbx", "iframe", "phpLDAPadmin"		, "http://demo.phpldapadmin.org/RELEASE/htdocs/index.php", "/assets/images/icon/phpLDAPadmin.jpg").save();
			new MenuModel(123,  0, "adm", "iframe", "Norse Attack Map"	, "http://map.norsecorp.com/", "/assets/favicon.png").save();
			new MenuModel(124,  0, "adm", "iframe", "Zabbix"			, "http://demo.zabbix.jp/zabbix/charts.php", "/assets/images/icon/zabbix.png").save();
			new MenuModel(125,  0, "dbx1", "url", "テーブル"			, "/adm/account/index", "/assets/images/icon/admin.png").save();
			new MenuModel(126,  0, "dbx1", "url", "カラム"				, "/adm/bookmark/index", "/assets/images/icon/admin.png").save();
			new MenuModel(127,  0, "dbx1", "url", "リスト"				, "/adm/menu/index", "/assets/images/icon/admin.png").save();
			new MenuModel(128,  0, "adm1", "url", "アカウント"			, "/adm/account/index", "/assets/images/icon/admin.png").save();
			new MenuModel(129,  0, "adm1", "url", "ブックマーク"		, "/adm/bookmark/index", "/assets/images/icon/admin.png").save();
			new MenuModel(130,  0, "adm1", "url", "メニュー"			, "/adm/menu/index", "/assets/images/icon/admin.png").save();
			new MenuModel(131,  0, "adm1", "url", "ロール"				, "/adm/role/index", "/assets/images/icon/admin.png").save();
			new MenuModel(132,  0, "adm2", "url", "ポータル"			, "/adm/portal/index", "/assets/images/icon/admin.png").save();
			new MenuModel(133,  0, "adm2", "url", "ポートレット"		, "/adm/portlet/index", "/assets/images/icon/admin.png").save();
			new MenuModel(134,  0, "adm2", "url", "パラメータ"			, "/adm/parameter/index", "/assets/images/icon/admin.png").save();
			new MenuModel(135,  0, "adm3", "url", "従業員"				, "/adm/person/index", "/assets/images/icon/admin.png").save();
			new MenuModel(136,  0, "adm3", "url", "会社"				, "/adm/person/index", "/assets/images/icon/admin.png").save();
			new MenuModel(137,  0, "adm3", "url", "所属"				, "/adm/person/index", "/assets/images/icon/admin.png").save();
			new MenuModel(138,  0, "adm3", "url", "職位"				, "/adm/person/index", "/assets/images/icon/admin.png").save();
			new MenuModel(139,  0, "sys1", "url", "セッション"			, "/session", "/assets/images/icon/admin.png").save();
			new MenuModel(140,  0, "sys1", "url", "操作ログ	"			, "/log/index", "/assets/images/icon/admin.png").save();
			new MenuModel(141,  0, "sys1", "url", "セットアップ	"		, "/setup", "/assets/images/icon/admin.png").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert Menu..done");

		logger.info("Insert Bookmark..");

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM CMN_TT_BOOKMARK");
			deleteSql.execute();

			new BookmarkModel( 1, 0, "partner","Oracle"		, "http://www.oracle.com/jp/index.html", "/assets/images/icon/oracle.png").save();
			new BookmarkModel( 2, 1, "partner","Oracle"		, "http://www.oracle.com/jp/index.html", "/assets/images/icon/oracle.png").save();
			new BookmarkModel( 3, 1, "partner","Oracle Technology Network"		, "https://www.oracle.com/technetwork/jp/", "/assets/images/icon/oracle.png").save();
			new BookmarkModel( 4, 1, "partner","Oracle Partner Network"			, "http://www.oracle.com/partners/ja/index.html", "/assets/images/icon/oracle.png").save();
			new BookmarkModel( 5, 1, "partner","My Oracle Support"				, "https://support.oracle.com/", "/assets/images/icon/oracle.png").save();
			new BookmarkModel( 6, 0, "partner","Microsoft"	, "https://www.microsoft.com/ja-jp/", "/assets/favicon.png").save();
			new BookmarkModel( 7, 0, "partner","intra-mart"	, "http://www.intra-mart.jp/", "/assets/favicon.png").save();
			new BookmarkModel( 8, 0, "partner","IBM"		, "http://www.ibm.com/jp-ja/", "/assets/favicon.png").save();
			new BookmarkModel( 9, 0, "partner","VMware"		, "http://www.vmware.com/jp.html", "/assets/favicon.png").save();
			new BookmarkModel(10, 0, "social","Amazon"		, "https://www.amazon.co.jp/", "/assets/images/social/amazon.png").save();
			new BookmarkModel(11, 0, "social","Dropbox"		, "https://www.dropbox.com/ja/", "/assets/images/social/dropbox.png").save();
			new BookmarkModel(12, 0, "social","Facebook"	, "https://ja-jp.facebook.com/", "/assets/images/social/fb.png").save();
			new BookmarkModel(13, 0, "social","LINE"		, "https://line.me/ja/", "/assets/images/social/line.png").save();
			new BookmarkModel(14, 0, "social","twitter"		, "https://twitter.com/?lang=ja", "/assets/images/social/twitter.png").save();
			new BookmarkModel(15, 0, "social","Yahoo!"		, "http://www.yahoo.co.jp/", "/assets/images/social/yahoo.png").save();
			new BookmarkModel(16, 0, "social","Google"		, "http://www.google.co.jp/", "/assets/images/social/google.png").save();
			new BookmarkModel(17, 0, "social","Evernote"	, "https://evernote.com/intl/jp/", "/assets/images/social/evernote.png").save();
			new BookmarkModel(18, 0, "social","youtube"		, "https://www.youtube.com/?gl=JP&hl=ja", "/assets/images/social/youtube.png").save();
			new BookmarkModel(19, 0, "social","Google Map"	, "https://maps.google.co.jp/", "/assets/images/social/maps.png").save();
			new BookmarkModel(20, 0, "social","Instagram"	, "https://www.instagram.com/?hl=ja", "/assets/images/social/instagram.png").save();
			new BookmarkModel(21, 0, "social","Linkedin"	, "https://jp.linkedin.com/", "/assets/images/social/linkedin.png").save();
			new BookmarkModel(22, 0, "tools","マニュアル"	, "http://www.yahoo.co.jp/", "/assets/images/tools/help.png").save();
			new BookmarkModel(23, 0, "tools","FAQ"			, "http://www.yahoo.co.jp/", "/assets/images/tools/wordpress.png").save();
			new BookmarkModel(24, 0, "tools","問合せ"		, "http://www.yahoo.co.jp/", "/assets/images/tools/contact.png").save();
			new BookmarkModel(25, 0, "tools","開発"			, "http://www.yahoo.co.jp/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(26, 0, "tools","Play Framework", "https://www.playframework.com/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(27, 0, "google","Google Search", "https://www.google.co.jp/", "/assets/images/icon/google.png").save();
			new BookmarkModel(28, 0, "google","Google Analytics", "https://www.google.com/intl/ja_jp/analytics/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(29, 0, "google","Google Site", "https://sites.google.com/?pli=1", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(30, 0, "ca","シティアスコム"	, "http://www.city.co.jp/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(31, 0, "ca","新卒採用サイト"	, "http://recruit.city.co.jp/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(32, 0, "ca","TOMASユーザサイト", "http://recruit.city.co.jp/", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(33, 0, "ca","シティキャリアサービス", "http://www.city-ccs.co.jp/index.php", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(34, 0, "cmn","SlideShare"		, "http://www.slideshare.net/", "/assets/images/icon/slideshare.png").save();
			new BookmarkModel(35, 0, "cmn","OpenData"		, "http://www.open-governmentdata.org/", "/assets/images/icon/cybozu.png").save();
			new BookmarkModel(36, 0, "cmn","Liferay"		, "https://www.liferay.com/ja/home", "/assets/images/icon/liferay.png").save();
			new BookmarkModel(37, 0, "cmn","Free Logo Maker", "https://logomakr.com/", "/assets/images/icon/cybozu.png").save();
			new BookmarkModel(38, 0, "app","moodel"			, "http://cloud.l-edge.jp/moodle/", "/assets/images/icon/moodel.jpg").save();
			new BookmarkModel(39, 0, "app","OrangeHRM"		, "http://611.demo.orangehrmlive.com/dashboard", "/assets/images/icon/orangehrm.png").save();
			new BookmarkModel(40, 0, "col","Cybozu Live"	, "https://cybozulive.com/", "/assets/images/icon/cybozu.png").save();
			new BookmarkModel(41, 0, "col","ownCloud"		, "https://demo.owncloud.org/index.php/login", "/assets/images/icon/owncloud.png").save();
			new BookmarkModel(42, 0, "col","dokuwiki"		, "https://www.dokuwiki.org/ja:dokuwiki", "/assets/images/icon/dokuwiki.jpg").save();
			new BookmarkModel(43, 0, "col","Joruri"			, "http://demo.joruri.org/", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(44,43, "col","Joruri CMS"		, "http://demo.joruri.org/_admin/login", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(45,43, "col","Joruri Mail"	, "http://demo.joruri.org/", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(46,43, "col","Joruri GW"		, "http://demo.gw.joruri.org/_admin/login", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(47,43, "col","Joruri Plus+"	, "http://demo.plus.joruri.org/_admin/login", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(48,43, "col","Joruri Video"	, "http://demo.video.joruri.org/_admin", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(49,43, "col","Joruri Map"		, "http://demo.maps.joruri.org/", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(50,43, "col","Joruri DMS"		, "http://demo.joruri.org/", "/assets/images/icon/joruri.jpg").save();
			new BookmarkModel(51, 0, "col","Alfresco"		, "https://www.alfresco.com/jp", "/assets/images/icon/alfresco.png").save();
			new BookmarkModel(52, 0, "dev","Mantis"			, "http://www.mantisbt.org/bugs/my_view_page.php", "/assets/images/tools/eclipse.png").save();
			new BookmarkModel(53, 0, "dev","redmine"		, "https://my.redmine.jp/demo/my/page", "/assets/images/icon/redmine.png").save();
			new BookmarkModel(54, 0, "dev","github"			, "https://github.com/", "/assets/images/icon/github.png").save();
			new BookmarkModel(55, 0, "dbx","phpMyAdmin"		, "https://demo.phpmyadmin.net/master-config/", "/assets/images/icon/phpmyadmin.jpg").save();
			new BookmarkModel(56, 0, "dbx","phpPgAdmin"		, "http://phppgadmin.kattare.com/phppgadmin/", "/assets/images/icon/phppgadmin.png").save();
			new BookmarkModel(57, 0, "sec","OpenVAS"		, "http://www.openvas.org/index.html", "/assets/images/icon/openvas.jpg").save();
			new BookmarkModel(58, 0, "sec","Splunk"			, "https://www.splunk.com/en_us/homepage.html", "/assets/images/icon/splunk.png").save();
			new BookmarkModel(59, 0, "adm","AWS"			, "https://ap-northeast-1.console.aws.amazon.com/console/home?region=ap-northeast-1", "/assets/images/icon/aws.png").save();
			new BookmarkModel(60, 0, "adm","Microsoft Azure", "https://portal.azure.com/", "/assets/images/icon/azure.png").save();
			new BookmarkModel(61, 0, "adm","GMOクラウド"	, "https://pf.gmocloud.com/login", "/assets/images/icon/gmo.png").save();
			new BookmarkModel(62, 0, "adm","@PAGES"			, "http://atpages.jp/admin/login.php", "/assets/images/icon/atpages.jpg").save();
			new BookmarkModel(63, 0, "adm","Nagios"			, "http://nagioscore.demos.nagios.com/nagios/", "/assets/images/icon/nagios.png").save();
			new BookmarkModel(64, 0, "adm","Piwik"			, "http://demo.piwik.org/index.php", "/assets/images/icon/piwik.png").save();
			new BookmarkModel(65, 0, "A","ITニュース"		, "http://52.196.214.8/news/", "/assets/favicon.png").save();
			new BookmarkModel(66, 0, "A","Game"				, "http://52.196.214.8/game/", "/assets/favicon.png").save();
			new BookmarkModel(67, 0, "A","Redmine"			, "https://my.redmine.jp/demo/", "/assets/favicon.png").save();
			new BookmarkModel(68, 0, "B","GMOクラウド"		, "https://pf.gmocloud.com/login", "/assets/favicon.png").save();
			new BookmarkModel(69, 0, "B","GitHub"			, "https://github.com/", "/assets/favicon.png").save();
			new BookmarkModel(70, 0, "B","テスト"	, "http://demo.infoscoop.org/infoscoop/index.jsp", "/assets/favicon.png").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert Bookmark..done");

		logger.info("Insert TableData..");

		Date datNow = new Date(System.currentTimeMillis());

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM DBX_TM_TABLE");
			deleteSql.execute();

			new TableModel( 1,  "TEST", "テーブル名").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM DBX_TM_TABLE_COLUMN");
			deleteSql.execute();

			new TableColumnModel( 1,  "TEST", "ID", "id", "COL1", "ID", true, 0, false, 0).save();
			new TableColumnModel( 2,  "TEST", "STR_COL01", "strCol01", "col1", "文字列01", true, 2, true, 1).save();
			new TableColumnModel( 3,  "TEST", "STR_COL02", "strCol02", "col1", "文字列02", true, 3, true, 2).save();
			new TableColumnModel( 4,  "TEST", "NUM_COL01", "numCol01", "col2", "数値01", false, 0, false, 0).save();
			new TableColumnModel( 5,  "TEST", "NUM_COL02", "numCol02", "col2", "数値02", false, 0, false, 0).save();
			new TableColumnModel( 6,  "TEST", "DAT_COL01", "datCol01", "tab1", "日付01", false, 0, false, 0).save();
			new TableColumnModel( 7,  "TEST", "DAT_COL02", "datCol02", "tab1", "日付02", true, 1, false, 0).save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM DBX_TT_TABLE_DATA");
			deleteSql.execute();

			new TableDataModel( 1,  "TEST", "ああああ", "いいいいいいい", 1, -1, datNow, datNow).save();
			new TableDataModel( 2,  "TEST", "うううううう", "ええええええ", 999, 999, datNow, datNow).save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		logger.info("Insert TableData..done");

		try {
			//deleteSql = Ebean.createSqlUpdate("DELETE FROM SNS_TT_MESSAGE");
			//deleteSql.execute();

			//new MessageModel( 1,  "TEST", "今ひま？").save();
			//new MessageModel( 2,  "miyazaki", "ちょっと手伝って欲しいんだけど、いいかな？").save();
			//new MessageModel( 3,  "miyazaki", "ああああああああああああああああああ").save();

			//txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			deleteSql = Ebean.createSqlUpdate("DELETE FROM SNS_TT_TIMELINE");
			deleteSql.execute();

			new TimelineModel( 1,  "TEST", 0, "今ひま？").save();
			new TimelineModel( 2,  "TEST", 1, "今ひま？").save();
			new TimelineModel( 3,  "TEST", 1, "今ひま？").save();
			new TimelineModel( 4,  "miyazaki", 0, "ちょっと手伝って欲しいんだけど、いいかな？").save();
			new TimelineModel( 5,  "miyazaki", 0, "あああああああああああああああああああああああああああああああ").save();

			txn.commit();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}