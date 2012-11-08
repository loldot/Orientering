package no.orientering.models;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;


@XmlRootElement(name = "article")
@XmlType(propOrder = { "ID", "title", "content", "author", "publishedDate" })
public class Article {
	private int ID;
	private String title;
	private String content;
	private User author;
	private Date publishedDate;
	
	public Article(){
		ID = 0;
		publishedDate = Calendar.getInstance().getTime();
	}
	public Article(int ID){
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public SyndEntry asRSSEntry(){
		String permUrl = URI.create(String.format("/HomeController?articleID=%d", ID)).toString();
		SyndEntry entry = new SyndEntryImpl();
		
		entry.setTitle(title);
		entry.setAuthor(author.getUserName());
		entry.setUri(permUrl);
		entry.setLink(permUrl);
		entry.setPublishedDate(publishedDate);
		
		SyndContent rssContent = new SyndContentImpl();
		rssContent.setType("text/html");
		rssContent.setValue((content.length() <= 100) ? content + "..." : content.substring(0, 100));
		
		entry.setDescription(rssContent);
		
		return entry;
	}
}
