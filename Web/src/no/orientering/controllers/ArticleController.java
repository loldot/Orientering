package no.orientering.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;
import no.orientering.models.User;
import no.orientering.utils.NetHelp;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String articleID = request.getParameter("articleID");
		ArticleDAO adao = new ArticleDAO();
		if (!NetHelp.isNullOrEmpty(articleID)) {
			int id = Integer.parseInt(articleID);
			if (id > 0) {

				Article art = adao.getArticle(id);
				request.setAttribute("article", art);
				// fetch 1
			}

		} else {
			List<Article> artList = adao.getArticles();
			request.setAttribute("articles", artList);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/articles.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticleDAO ad = new ArticleDAO();
		String title = request.getParameter("title");
		String content = request.getParameter("msgpost");
		HttpSession session = request.getSession();
		
		User u = (User) session.getAttribute("access");
		
		Article article = null;
		if(!NetHelp.isNullOrEmpty(title) && !NetHelp.isNullOrEmpty(content) && u!=null){
			article = new Article();
			article.setAuthor(u);
			article.setContent(content);
			article.setTitle(title);
			article.setPublishedDate(new Date());
			
		}
	}

}
