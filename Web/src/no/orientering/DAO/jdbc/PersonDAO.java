package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import no.orientering.models.Person;

public class PersonDAO {

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
			if(!rs.first())
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
	public List<Person> getPersons(){
		
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
			if(!rs.first())
				throw new Exception("Feil i getPerson");
			
			Person p = null;
			persons = new ArrayList<Person>();
			
			while(rs.next()){
				p = new Person();
				p.setID(rs.getInt("ID"));
				p.setBirthYear(rs.getInt("BirthYear"));
				p.setFirstName(rs.getString("FirstName"));
				p.setLastName(rs.getString("LastName"));
				p.setPhone(rs.getString("Phone"));
				persons.add(p);
			}
			
			
		} catch (Exception feil) {

		}

		return persons;
		
	}
}
