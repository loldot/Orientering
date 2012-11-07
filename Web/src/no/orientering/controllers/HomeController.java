package no.orientering.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;
import no.orientering.utils.NetHelp;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
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
		String URL = "WEB-INF/default.jsp";

		String action = request.getParameter("action");
		if (!NetHelp.isNullOrEmpty(action) && action.equalsIgnoreCase("new")) {
			URL="WEB-INF/newarticle.jsp";
		} else {
			ArticleDAO ad = new ArticleDAO();
			List<Article> artlist = null;
			String id = request.getParameter("articleID");
			if (!NetHelp.isNullOrEmpty(id)) {
				int aid = Integer.parseInt(id);
				if (aid > 0) {
					artlist = new ArrayList<Article>();
					artlist.add(ad.getArticle(aid));
				}
			} else {
				artlist = ad.getArticles();
			}
			request.setAttribute("articles", artlist);
		}

	
		RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
