package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import no.orientering.models.Person;

public class PersonDAO {

	private Connection conn;

	private SqlCommands sqlCmd = new SqlCommands();

	public PersonDAO() {
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Person getPerson(int personID) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Person p = null;
		try {

			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(false);

			String sqlStr = "Select * from `Persons` where ID = ?";

			ps = conn.prepareStatement(sqlStr);
			ps.setInt(0, personID);

			rs = ps.executeQuery();
			if (!rs.first())
				throw new Exception("Feil i getPerson");

			p = new Person();
			p.setID(rs.getInt("ID"));
			p.setBirthYear(rs.getInt("BirthYear"));
			p.setFirstName(rs.getString("FirstName"));
			p.setLastName(rs.getString("LastName"));
			p.setPhone(rs.getString("Phone"));

		} catch (Exception feil) {

		}

		return p;
	}

	public List<Person> getPersons() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Person> persons = null;
		try {

			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(false);

			String sqlStr = "Select * from `Persons`";

			ps = conn.prepareStatement(sqlStr);

			rs = ps.executeQuery();
			/*if (!rs.first())
				throw new Exception("Feil i getPerson");*/

			Person p = null;
			persons = new ArrayList<Person>();

			while (rs.next()) {
				p = new Person();
				p.setID(rs.getInt("ID"));
				p.setBirthYear(rs.getInt("BirthYear"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setPhone(rs.getString("Phone"));
				p.setAddress(rs.getString("Address"));
				persons.add(p);
			}

		} catch (Exception feil) {
			String f = feil.getMessage();
			System.out.println(f);
		}

		return persons;

	}

	public boolean deletePerson(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean deleted = true;
		try {
			conn.setAutoCommit(false);

			String sqlStr = "Delete from `Person` where ID = ?";

			ps = conn.prepareStatement(sqlStr);
			ps.setInt(0, id);

			rs = ps.executeQuery();

			if (!rs.first())
				deleted = false;

		} catch (Exception ex) {

		}
		return deleted;
	}

	public boolean savePerson(Person p) {
		boolean saved = false;
		if (p.getID() > 0) {
			saved = updatePerson(p);
		} else
			saved = saveNewPerson(p);

		return saved;
	}

	private boolean saveNewPerson(Person p) {

		boolean saved = false;
		String sqlStr = "Insert into `Persons` "
				+ "(`FirstName`,`LastName`,`Phone`,`BirthYear`,`Address`) "
				+ "Values (?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getPhone());
			ps.setInt(4, p.getBirthYear());
			ps.setString(5, p.getAddress());

			saved = sqlCmd.ExecuteNonQuery(ps) > 0;
		} catch (Exception e) {
			String f = e.getMessage();
			System.out.println(f);
		}

		return saved;
	}

	private boolean updatePerson(Person p) {

		boolean updated = false;
		String sqlStr = "Update `Persons`set " + "FirstName = ?,"
				+ "LastName = ?," + "Phone = ?," + "BirthYear = ?,"
				+ "Address = ?" + " WHERE `ID`= ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(0, p.getFirstName());
			ps.setString(1, p.getLastName());
			ps.setString(2, p.getPhone());
			ps.setInt(3, p.getBirthYear());
			ps.setString(4, p.getAddress());
			ps.setInt(5, p.getID());

			updated = sqlCmd.ExecuteNonQuery(ps) > 0;

		} catch (Exception ex) {

		}

		return updated;
	}
}
