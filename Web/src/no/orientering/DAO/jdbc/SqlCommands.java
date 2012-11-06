package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlCommands {

	// private Connection conn;

	public SqlCommands() {

	}

	/**
	 * Metode som utfører sql "select"
	 * 
	 * @param sqlStr
	 * @return resultatet av spørringen
	 */
	public ResultSet makeResultSet(String sqlStr) {
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			PreparedStatement ps = conn.prepareStatement(sqlStr);

			rs = ps.executeQuery();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return rs;
	}

	public ResultSet makeResultSet(PreparedStatement ps) {
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return rs;
	}

	/**
	 * Metode som utfører UPDATE, DELETE og INSERT
	 * 
	 * @param sqlStr
	 * @return Integer: antall "rows affected" eller 0
	 */
	public int ExecuteNonQuery(String sqlStr) {
		int rowsAff = 0;
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			PreparedStatement ps;

			ps = conn.prepareStatement(sqlStr);
			rowsAff = ps.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return rowsAff;
	}

	public int ExecuteNonQuery(PreparedStatement ps) {
		int rowsAff = 0;
		Connection conn = null;
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			rowsAff = ps.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return rowsAff;
	}
}
