package no.orientering.DAO.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import no.orientering.models.User;
import no.orientering.utils.NetHelp;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(
		urlPatterns = { "/LoginController" }, 
		initParams = { 
				@WebInitParam(name = "LoginController", value = "LoginController")
		})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equalsIgnoreCase("logout")){
			session.removeAttribute("access");
			
		}
		response.sendRedirect("HomeController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		UserDAO ud = new UserDAO();
		if(!NetHelp.isNullOrEmpty(username) && !NetHelp.isNullOrEmpty(password)){
			User u = ud.LogIn(username, password);
			if(u != null)
				session.setAttribute("access", u);
				
		}
		response.sendRedirect("HomeController");
	}

}
