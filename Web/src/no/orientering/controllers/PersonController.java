package no.orientering.controllers;

import java.io.IOException;

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
		String URL = null;
		String id = request.getParameter("personID");
		if (!NetHelp.isNullOrEmpty(id)) {
			
			Person p = pd.getPerson(Integer.parseInt(id));
			if(p != null){
				
				//fill edits
				
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(URL);

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

			p = (Person) session.getAttribute("selectedperson");
			if (p == null) {
				// ny dude
				p = makePerson(request);

			} else {
				p = editPerson(p, request);
			}

			if (!validatePerson(p)) {
				throw new Exception("Fyll inn alt");
			}
			if (pd.savePerson(p)) {
				// G� til liste..
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

	private Person editPerson(Person p, HttpServletRequest request) {

		if (p.getID() == 0) {
			try {
				throw new Exception("Ugyldig person");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				p = null;
				e.printStackTrace();
			}
		} else {
			p.setFirstName(request.getParameter("firstName"));
			p.setLastName(request.getParameter("lastName"));
			p.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
			p.setPhone(request.getParameter("phone"));
			p.setAddress(request.getParameter("address"));
		}

		return p;

	}

}
