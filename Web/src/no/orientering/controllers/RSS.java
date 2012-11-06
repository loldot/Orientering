package no.orientering.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;

/**
 * Servlet implementation class RSS
 */
@WebServlet("/RSS")
public class RSS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RSS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SyndFeed rss = createFeed(request);
			response.setContentType("application/xml; charset=UTF-8");
			SyndFeedOutput output = new SyndFeedOutput();
			output.output(rss, response.getWriter());
		} catch (FeedException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Could not generate feed");
        }
		
	}
	
	private SyndFeed createFeed(final HttpServletRequest req) {
		ArticleDAO articles = new ArticleDAO();
		
		SyndFeed myRSS = new SyndFeedImpl();
		
		myRSS.setTitle("O-feed!");
		myRSS.setLink(URI.create("/").toString());
		myRSS.setDescription("De heteste nyhetene fra o-løpsverden");
		myRSS.setFeedType("atom_1.0");
		
		List<Article> recentArticles = articles.getArticles();
		List<SyndEntry> feeds = new ArrayList<>();
		for(int i = 0; i <3; i++){
			feeds.add(recentArticles.get(i).asRSSEntry());
		}
		
		myRSS.setEntries(feeds);
		
		return myRSS;
	}
}
