package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import no.orientering.models.NullPerson;
import no.orientering.models.Person;

public class PersonDAO {

	private Connection oldConn;

	private SqlCommands sqlCmd;

	public PersonDAO() {
		try {

			// oldConn =
			// DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			sqlCmd = new SqlCommands();
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
			conn.setAutoCommit(true);

			String sqlStr = "Select * from `Persons` where ID = ?";

			ps = conn.prepareStatement(sqlStr);
			ps.setInt(1, personID);

			rs = ps.executeQuery();
			if (!rs.first())
				return new NullPerson();

			p = new Person();
			p.setID(rs.getInt("ID"));
			p.setBirthYear(rs.getInt("BirthYear"));
			p.setFirstName(rs.getString("FirstName"));
			p.setLastName(rs.getString("LastName"));
			p.setPhone(rs.getString("Phone"));
			p.setAddress(rs.getString("Address"));
			conn.close();
		} catch (Exception feil) {
			String f = feil.getMessage();
			System.out.println(f);
		} finally {
			DatabaseHelper.close(rs);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(conn);
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
			/*
			 * if (!rs.first()) throw new Exception("Feil i getPerson");
			 */

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

			conn.close();
		} catch (Exception feil) {
		} finally {
			DatabaseHelper.close(rs);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(conn);
		}

		return persons;

	}

	public boolean deletePerson(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean deleted = true;
		try {

			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);

			String sqlStr = "Delete from `Person` where ID = ?";

			ps = oldConn.prepareStatement(sqlStr);
			ps.setInt(0, id);

			rs = ps.executeQuery();

			if (!rs.first())
				deleted = false;

		} catch (Exception ex) {

		} finally {
			DatabaseHelper.close(rs);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(conn);
		}
		return deleted;
	}

	public int savePerson(Person p) {
		int saved = -1;
		if (p.getID() > 0) {
			saved = updatePerson(p);
		} else
			saved = saveNewPerson(p);

		return saved;
	}

	private int saveNewPerson(Person p) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int saved = -1;
		String sqlStr = "Insert into `Persons` "
				+ "(`FirstName`,`LastName`,`Phone`,`BirthYear`,`Address`) "
				+ "Values (?,?,?,?,?)";

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			 ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getPhone());
			ps.setInt(4, p.getBirthYear());
			ps.setString(5, p.getAddress());

			sqlCmd.ExecuteNonQuery(ps);
			rs = ps.getGeneratedKeys();
			if(rs.next())
				saved= rs.getInt(1);
			
		} catch (Exception e) {
			String f = e.getMessage();
			System.out.println(f);
		} finally {
			
			DatabaseHelper.close(ps);
			DatabaseHelper.close(conn);
		}

		return saved;
	}

	private int updatePerson(Person p) {
		Connection conn = null;
		PreparedStatement ps = null;
		int updated = -1;
		String sqlStr = "Update `Persons`set " + "FirstName = ?,"
				+ "LastName = ?," + "Phone = ?," + "BirthYear = ?,"
				+ "Address = ?" + " WHERE `ID`= ?";

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			 ps = conn.prepareStatement(sqlStr);
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getPhone());
			ps.setInt(4, p.getBirthYear());
			ps.setString(5, p.getAddress());
			ps.setInt(6, p.getID());

			updated = sqlCmd.ExecuteNonQuery(ps);

		} catch (Exception ex) {

		} finally {
		
			DatabaseHelper.close(ps);
			DatabaseHelper.close(conn);
		}

		return updated;
	}
}
