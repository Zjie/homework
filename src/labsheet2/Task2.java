package labsheet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Task2 {
	public static void main(String[] args) throws IOException, ParserException {
		Parser parser = new Parser(fetchUrl("http://www.baidu.com", "utf-8"));
		NodeFilter filter = new TagNameFilter("a");
		NodeList nodes = parser.extractAllNodesThatMatch(filter);
		for (int i = 0; i < nodes.size() ; i++) {
			LinkTag node = (LinkTag) nodes.elementAt(i);
			System.out.println(node.getLink());
		}
	}
	private static String fetchUrl(String url, String charSet) throws IOException {
		URL	locator	=	new	URL(url);
		StringBuilder html = new StringBuilder();
		InputStream instream = locator.openStream();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(instream, charSet));
			String line = null;
			while ((line = reader.readLine()) != null) {
				html.append(line);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			instream.close();
		}
		
		return html.toString();
	}
}
