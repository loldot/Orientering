package no.orientering.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.orientering.DAO.jdbc.PersonDAO;
import no.orientering.DAO.jdbc.UserDAO;
import no.orientering.models.Person;
import no.orientering.models.User;
import no.orientering.utils.NetHelp;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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

		String URL = "WEB-INF/userlist.jsp";
		String id = request.getParameter("userID");
		PersonDAO pd = new PersonDAO();
		List<Person> pList = pd.getPersons();
		UserDAO ud = new UserDAO();

		request.setAttribute("persons", pList);

		if (!NetHelp.isNullOrEmpty(id)) {
			int uid = Integer.parseInt(id);

			if (uid > 0) {
				// edit
				User u = ud.GetBy(uid);
				if (u != null)
					request.setAttribute("user", u);

				URL = "WEB-INF/registration.jsp";

			}
		} else {
			List<User> users = ud.getUsers();
			URL ="WEB-INF/userlist.jsp";
			request.setAttribute("users",users);
			
			
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
