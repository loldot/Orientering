package no.orientering.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String articleID = request.getParameter("articleID");
		ArticleDAO adao = new ArticleDAO();
		if (!NetHelp.isNullOrEmpty(articleID)){
			int id = Integer.parseInt(articleID);
			if (id > 0){
				
				Article art = adao.getArticle(id);
				request.setAttribute("article",art);
				// fetch 1
			}else {
				//liste
				List<Article> artList = adao.getArticles();
				
			}
			
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
