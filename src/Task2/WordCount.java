package Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
	public static void main(String[] args) {
		try {
			String html = fetchUrl("http://csvision.swan.ac.uk", "utf-8");
			Pattern pattern = Pattern.compile("[Vv]ision");
			Matcher matcher = pattern.matcher(html);
			int count = 0;
			while (matcher.find()) {
				count++;
			}
			System.out.println("Number	of	occurrence:" + count);
		} catch (IOException e) {
			e.printStackTrace();
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
