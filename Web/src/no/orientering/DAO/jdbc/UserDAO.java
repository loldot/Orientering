package no.orientering.DAO.jdbc;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			conn.setAutoCommit(false);
			
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
			//loggedIn.set
			
		} catch(Exception feil){
			throw new AuthenticationException("Feil ved innlogging!", feil);
		} finally {
			DatabaseHelper.close(conn);
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return loggedIn;
	}
}
