package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlCommands {

	private Connection conn;

	public SqlCommands() {

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	/**
	 * Metode som utf�rer sql "select"
	 * 
	 * @param sqlStr
	 * @return resultatet av sp�rringen
	 */
	public ResultSet makeResultSet(String sqlStr) {
		ResultSet rs = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);

			rs = ps.executeQuery();

		} catch (Exception ex) {
			System.out.println(ex);
		} 
		return rs;
	}
	public ResultSet makeResultSet(PreparedStatement ps) {
		ResultSet rs = null;
		try {
			
			rs = ps.executeQuery();

		} catch (Exception ex) {
			System.out.println(ex);
		} 
		return rs;
	}

	/**
	 * Metode som utf�rer UPDATE, DELETE og INSERT
	 * 
	 * @param sqlStr
	 * @return Integer: antall "rows affected" eller 0
	 */
	public int ExecuteNonQuery(String sqlStr) {
		int rowsAff = 0;
		try {

			PreparedStatement ps;

			ps = conn.prepareStatement(sqlStr);
			rowsAff = ps.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return rowsAff;
	}
	public int ExecuteNonQuery(PreparedStatement ps) {
		int rowsAff = 0;
		try {
					
			rowsAff = ps.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return rowsAff;
	}
}
