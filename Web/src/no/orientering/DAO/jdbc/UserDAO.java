package no.orientering.DAO.jdbc;

import java.security.MessageDigest;
import javax.security.sasl.AuthenticationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.orientering.models.User;
import no.orientering.utils.ByteUtil;

public class UserDAO 
{
	public User LogIn(String userName, String password) throws AuthenticationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User loggedIn = null;
		
		try
		{
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			
			MessageDigest pwHash = MessageDigest.getInstance("SHA-256");
			pwHash.update(password.getBytes("UTF-8"));
			
			String hash = ByteUtil.BytesToHexString(pwHash.digest());
			
			String sql = "SELECT * FROM `Users` WHERE `userName` = ? AND `passwordHash`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(0, userName);
			ps.setString(1, hash);
			
			rs = ps.executeQuery();
			if(!rs.first())
				throw new Exception("Feil brukernavn/passord");
			loggedIn = fromResultSet(rs).get(0);
			
			
		} catch(Exception feil){
			throw new AuthenticationException("Feil ved innlogging!", feil);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return loggedIn;
	}
	
	public User GetBy(int id) throws AuthenticationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try
		{
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			
			String sql = "SELECT * FROM `Users` WHERE `userID` = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(0, id);
			
			rs = ps.executeQuery();
			
			user = fromResultSet(rs).get(0);
		} catch(Exception feil){
			throw new AuthenticationException("Feil ved innlogging!", feil);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return user;
	}
	
	private List<User> fromResultSet(ResultSet rs) throws SQLException{
		List<User> users = new ArrayList<>();
		//For å være sikker på at vi får med alle i resultatsettet
		rs.beforeFirst();
		
		while(rs.next()){
			User user = new User();
			
			user.setUserId(rs.getInt("userID"));
			user.setPassword(rs.getNString("passwordHash"));
			user.setUserName(rs.getNString("userName"));
			
			PersonDAO persons = new PersonDAO();
			user.setPersonalia(persons.getPerson(rs.getInt("personID")));
			user.setEmergencyContact(persons.getPerson(rs.getInt("emergencyContactID")));
			user.setFriend(persons.getPerson(rs.getInt("friendID")));
			
			users.add(user);
		}
		return users;
	}
	
	public boolean deletePerson(int id) {
		Connection conn = null;
		PreparedStatement ps = null;

		boolean deleted = true;
		try
		{
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(false);

			String sqlStr = "DELETE FROM `User` WHERE ID = ?";

			ps = conn.prepareStatement(sqlStr);
			ps.setInt(0, id);

			deleted = ps.execute();
		} catch (Exception ex) {
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
		}
		return deleted;
	}
	
	public boolean insertPerson(int id) {
		Connection conn = null;
		PreparedStatement ps = null;

		boolean insert = true;
		try
		{
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(false);

			String sqlStr = "DELETE FROM `User` WHERE ID = ?";

			ps = conn.prepareStatement(sqlStr);
			ps.setInt(0, id);

			insert = ps.execute();
		} catch (Exception ex) {
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
		}
		return insert;
	}

}
