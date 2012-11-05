package no.orientering.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.orientering.DAO.jdbc.PersonDAO;
import no.orientering.models.Person;
import no.orientering.utils.NetHelp;

/**
 * Servlet implementation class PersonController
 */
@WebServlet("/PersonController")
public class PersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonController() {
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
		PersonDAO pd = new PersonDAO();
		String URL = "WEB-INF/personlist.jsp";
		String id = request.getParameter("personID");
		if (!NetHelp.isNullOrEmpty(id)) {

			int pID = Integer.parseInt(id);
			URL = "WEB-INF/personview.jsp";
			if (pID > 0) {
				Person p = pd.getPerson(Integer.parseInt(id));
				if (p != null) {

					request.setAttribute("person", p);
				}
			} else {
				URL = "WEB-INF/personview.jsp";
			}

		} else {
			List<Person> plist = pd.getPersons();
			URL = "WEB-INF/personlist.jsp";
			request.setAttribute("persons", plist);
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

		Person p = null;
		PersonDAO pd = new PersonDAO();
		HttpSession session = null;

		try {

			session = request.getSession();

			String id = request.getParameter("personID");

			if (NetHelp.isNullOrEmpty(id)) {
				// ny dude
				p = makePerson(request);

			} else {

				p = editPerson(Integer.parseInt(id), request);
			}

			if (!validatePerson(p)) {
				throw new Exception("Fyll inn alt");
			}
			if (pd.savePerson(p)) {
				// Gå til liste..
				response.sendRedirect("PersonController");
			}

		} catch (Exception ex) {

		}
	}

	private Person makePerson(HttpServletRequest request) {
		Person p = new Person();

		p.setFirstName(request.getParameter("firstName"));
		p.setLastName(request.getParameter("lastName"));
		p.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
		p.setPhone(request.getParameter("phone"));
		p.setAddress(request.getParameter("address"));

		return p;

	}

	private boolean validatePerson(Person p) {
		if (p != null) {
			return (!NetHelp.isNullOrEmpty(p.getFirstName())
					&& !NetHelp.isNullOrEmpty(p.getLastName()) && !NetHelp
						.isNullOrEmpty(p.getPhone()));

		} else {
			return false;
		}
	}

	private Person editPerson(int id, HttpServletRequest request) {
		Person p = null;
		if (id > 0) {
			p = new Person();
			p.setID(id);
			p.setFirstName(request.getParameter("firstName"));
			p.setLastName(request.getParameter("lastName"));
			p.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
			p.setPhone(request.getParameter("phone"));
			p.setAddress(request.getParameter("address"));
		}

		return p;

	}

}
