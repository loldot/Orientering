package no.orientering.DAO.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseHelper {
	public static Connection getConnection(String source) throws SQLException, NamingException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(source);
		return ds.getConnection();
	}

	public static void r(Connection c) {
		try {
			if (c != null) {
				c.rollback();
			}
		} catch (SQLException e) {}
	}

	public static void close(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {}
	}
	
	public static void close(PreparedStatement s) {
		try {
			if (s != null) s.close();
		} catch (SQLException e) {}
	}

	public static void close(Statement s) {
		try {
			if (s != null) s.close();
		} catch (SQLException e) {}
	}

	public static void close(ResultSet r) {
		try {
			if (r != null) r.close();
		} catch (SQLException e) {}
	}
}
