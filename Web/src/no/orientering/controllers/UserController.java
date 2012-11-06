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
import no.orientering.models.NullPerson;
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
			URL = "WEB-INF/registration.jsp";
			if (uid > 0) {
				// edit
				User u = ud.GetBy(uid);
				if (u != null)
					request.setAttribute("user", u);

			}
		} else {
			List<User> users = ud.getUsers();
			URL = "WEB-INF/userlist.jsp";
			request.setAttribute("users", users);

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
		User u = null;
		String id = request.getParameter("userID");
		UserDAO ud = new UserDAO();

		if (!NetHelp.isNullOrEmpty(id)) {
			int uid = Integer.parseInt(id);
			if (uid > 0) {
				// edit
				u = editUser(uid, request);
				if (u != null) {

				}
			}
		} else {
			// ny kis
			u = makeUser(request);
			if (u != null) {
				if (ud.insertPerson(u) > -1) {
					// success
				}

			}

		}

	}

	private User editUser(int uid, HttpServletRequest request) {
		Person personalia = null;
		User u = new User();
		Person friend = null;
		Person ec = null;
		PersonDAO pd = new PersonDAO();

		try {

			if (!NetHelp.isNullOrEmpty(request.getParameter("personalia")))
				personalia = pd.getPerson(Integer.parseInt(request
						.getParameter("personalia")));
			if (personalia == null)
				throw new Exception("Feil ved lagring");

			String ecID = request.getParameter("ec");
			String fID = request.getParameter("friend");

			u.setUserId(uid);

			if (!NetHelp.isNullOrEmpty(ecID))
				ec = pd.getPerson(Integer.parseInt(ecID));

			if (!NetHelp.isNullOrEmpty(fID))
				friend = pd.getPerson(Integer.parseInt(fID));

			u.setPersonalia(personalia);

			if (ec != null) {
				u.setEmergencyContact(ec);
			} else {
				u.setEmergencyContact(new NullPerson());
			}

			if (friend != null) {
				u.setFriend(friend);
			} else {
				u.setFriend(new NullPerson());
			}

		} catch (Exception ex) {

		}

		return u;
	}

	private User makeUser(HttpServletRequest request) {
		Person personalia = null;
		User u = null;
		Person friend = null;
		Person ec = null;

		PersonDAO pd = new PersonDAO();
		try {
			if (!NetHelp.isNullOrEmpty(request.getParameter("personalia")))
				personalia = pd.getPerson(Integer.parseInt(request
						.getParameter("personalia")));
			if (personalia == null)
				throw new Exception("Feil ved lagring");

			String ecID = request.getParameter("ec");
			String fID = request.getParameter("friend");

			if (!NetHelp.isNullOrEmpty(ecID))
				ec = pd.getPerson(Integer.parseInt(ecID));

			if (!NetHelp.isNullOrEmpty(fID))
				friend = pd.getPerson(Integer.parseInt(fID));

			u.setPersonalia(personalia);

			if (ec != null) {
				u.setEmergencyContact(ec);
			} else {
				u.setEmergencyContact(new NullPerson());
			}
			if (friend != null) {
				u.setFriend(friend);
			} else {
				u.setFriend(new NullPerson());
			}

			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}

}
