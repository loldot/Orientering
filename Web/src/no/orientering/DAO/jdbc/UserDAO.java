package no.orientering.DAO.jdbc;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.security.sasl.AuthenticationException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import no.orientering.models.User;
import no.orientering.utils.ByteUtil;

public class UserDAO {
	private String SHA256(String str) {
		String hash = null;

		try {
			MessageDigest pwHash = MessageDigest.getInstance("SHA-256");
			pwHash.update(str.getBytes("UTF-8"));
			hash = ByteUtil.BytesToHexString(pwHash.digest());
		} catch (NoSuchAlgorithmException aEx) {
		} catch (UnsupportedEncodingException encEx) {
		}
		return hash;
	}

	public User LogIn(String userName, String password)
			throws AuthenticationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User loggedIn = null;

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);

			String hash = SHA256(password);

			String sql = "SELECT * FROM `Users` WHERE `userName` = ? AND `passwordHash`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, hash);

			rs = ps.executeQuery();
			if (!rs.first())
				throw new Exception("Feil brukernavn/passord");
			loggedIn = fromResultSet(rs).get(0);

		} catch (Exception feil) {
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

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);

			String sql = "SELECT * FROM `Users` WHERE `ID` = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			user = fromResultSet(rs).get(0);
		} catch (Exception feil) {
			throw new AuthenticationException("Feil ved innlogging!", feil);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return user;
	}

	public List<User> getUsers() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = null;

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			String sqlStr = "Select * from Users";
			ps = conn.prepareStatement(sqlStr);

			rs = ps.executeQuery();

			users = fromResultSet(rs);
		} catch (Exception ex) {

		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return users;
	}

	private List<User> fromResultSet(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<User>();
		// For � v�re sikker p� at vi f�r med alle i resultatsettet
		rs.beforeFirst();

		while (rs.next()) {
			User user = new User();

			user.setUserId(rs.getInt("ID"));
			user.setPassword(rs.getNString("passwordHash"));
			user.setUserName(rs.getNString("userName"));

			PersonDAO persons = new PersonDAO();
			user.setPersonalia(persons.getPerson(rs.getInt("personID")));
			user.setEmergencyContact(persons.getPerson(rs
					.getInt("emergencyContactID")));
			user.setFriend(persons.getPerson(rs.getInt("friendID")));

			users.add(user);
		}
		return users;
	}

	public boolean deletePerson(int id) {
		Connection conn = null;
		PreparedStatement ps = null;

		boolean deleted = true;
		try {
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

	public int insertPerson(User user) {
		int insertedId = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SqlCommands sqlCmd = new SqlCommands();
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);

			String sqlStr = "Insert into Users ("
					+ "userName,passwordHash,personID,emergencyContactID,friendID)"
					+ " values (?,?,?,?,?)";

			// String sqlStr = "{CALL InsertUser(?,?,?,?,?,?)}";
			ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS);
			//ps.setInt(1, user.getUserId());
			ps.setString(1, user.getUserName());
			ps.setString(2, SHA256(user.getPassword()));
			
			PersonDAO pd = new PersonDAO();
			int personID = pd.savePerson(user.getPersonalia());
			if (personID <= 0){
				throw new Exception("Feil v/lagring person");
			}
			ps.setInt(3, personID);
			ps.setInt(4,user.getEmergencyContact().getID());
			ps.setInt(5, user.getFriend().getID());
			
			sqlCmd.ExecuteNonQuery(ps);
			rs = ps.getGeneratedKeys();
			if(rs.next())
				insertedId= rs.getInt(1);
			
	
		} catch (Exception ex) {
			String f = ex.getMessage();
			System.out.println(f);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return insertedId;
	}
	public int updatePerson(User user) {
		int insertedId = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SqlCommands sqlCmd = new SqlCommands();
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);

			String sqlStr = "Update Users Set userName = ? ,passwordHash = ?, " 
					+ "personID = ?,emergencyContactID = ?, friendID = ?";

			ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.setString(2, SHA256(user.getPassword()));
			
			PersonDAO pd = new PersonDAO();
			int personID = pd.savePerson(user.getPersonalia());
			if (personID <= 0){
				throw new Exception("Feil v/lagring person");
			}
			ps.setInt(3, personID);
			ps.setInt(4,user.getEmergencyContact().getID());
			ps.setInt(5, user.getFriend().getID());
			
			ps.executeUpdate();
			insertedId = user.getUserId();
	
		} catch (Exception ex) {
			String f = ex.getMessage();
			System.out.println(f);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return insertedId;
	}
}
