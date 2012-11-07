package no.orientering.utils;

import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class BtFeedReader {
	
	private static final String URL = "http://www.bt.no/rss/";
	private static final String XPATH = "/channel/item[1]/guid";
	
	public static String getLatestNews(){
		return getLinkFrom(URL, XPATH);
	}
	
	private static String getLinkFrom(String url, String xpath){
		try {
		
			SyndFeedInput input = new SyndFeedInput();
	        SyndFeed feed = input.build(new XmlReader(new URL(url)));

			SyndEntry latest = (SyndEntry)feed.getEntries().get(0);
			
			return latest.getLink();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "http://bt.no/";
	}

}
