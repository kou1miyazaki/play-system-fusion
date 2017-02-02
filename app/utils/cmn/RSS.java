package utils.cmn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSS {

	public void main () {
		getChannel("http://www.security-next.com/feed");
	}

	private static Element getRSSElment(String url) throws ParserConfigurationException, SAXException, IOException{

		// XMLを読むためのDom APIをインスタンス化
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(url);

		// ドキュメントのrootを取得
		return  document.getDocumentElement();
	}

	public static HashMap<String, String> getChannel(String url) {

		HashMap<String, String> channelMap = new HashMap<String, String>();

		try {
			// ドキュメントのrootを取得
			Element root = getRSSElment(url);
			NodeList channel = root.getElementsByTagName("channel");
			// チャンネル取得.
			channelMap.put("title", ((Element) channel.item(0)).getElementsByTagName("title").item(0).getFirstChild().getNodeValue());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channelMap;
	}

    public static List<HashMap<String, String>> getItem(String url){

    	// リスト作成.
    	List<HashMap<String, String>> item = new ArrayList<HashMap<String, String>>();

        try {
            // ドキュメントのrootを取得
            Element root = getRSSElment(url);

            // 各"item"内のノードリストを取得
            NodeList itemlist = root.getElementsByTagName("item");

            // itemの数だけループさせて全てのitemを取得
            for (int i = 0; i < itemlist.getLength(); i++) {
                Element element = (Element) itemlist.item(i);
                // "title","link","pubDate"を取得
                HashMap<String, String> itemMap = new HashMap<String, String>();

                itemMap.put("title", element.getElementsByTagName("title").item(0).getFirstChild().getNodeValue());
                //itemMap.put("creator",element.getElementsByTagName("dc:creator").item(0).getFirstChild().getNodeValue());
                itemMap.put("link", element.getElementsByTagName("link").item(0).getFirstChild().getNodeValue());
                //itemMap.put("pubDate",element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue());
                itemMap.put("description", element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue());
                //itemMap.put("content",element.getElementsByTagName("content:encoded").item(0).getFirstChild().getNodeValue());

                item.add(itemMap);

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return item;
    }
}