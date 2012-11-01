package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.orientering.models.Article;


public class ArticleDAO {

	private Connection conn;

	private SqlCommands sqlCmd = new SqlCommands();

	public ArticleDAO() {
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Article> getArticles() {
		List<Article> artList = new ArrayList<Article>();

		String sqlStr = "SELECT * FROM articles ORDER BY PublishedDate";

		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ResultSet rs = sqlCmd.makeResultSet(ps);
			Article art = null;

			while (rs.next()) {
				art = new Article();
				art.setID(rs.getInt("ID"));
				art.setContent(rs.getString("Content"));
				art.setPublishedDate(rs.getDate("PublishedDate"));
				art.setTitle(rs.getString("Title"));
				// art.setAuthor
				artList.add(art);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return artList;
	}

	public Article getArticle(int ID) {
		Article art = null;

		String sqlStr = "Select * from articles where ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setInt(0, ID);
			ResultSet rs = sqlCmd.makeResultSet(ps);
			if (!rs.next())
				throw new SQLException("No match");

			art = new Article();
			art.setID(rs.getInt("ID"));
			art.setContent(rs.getString("Content"));
			art.setPublishedDate(rs.getDate("PublishedDate"));
			art.setTitle(rs.getString("Title"));
			// art.setAuthor

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return art;
	}

	public boolean saveArticle(Article art) {
		boolean saved = false;
		if (art.getID() < 1) {
			saved = saveNew(art);
		} else {
			saved = updateArticle(art);
		}

		return saved;
	}

	private boolean saveNew(Article art) {
		boolean saved = false;

		String sqlStr = "Insert into `Articles` set (`Title`,`Content`"
				+ "`PublishedDate`,`AuthorID`) Values" + "(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(0, art.getTitle());
			ps.setString(1, art.getContent());
			ps.setDate(2, new java.sql.Date(art.getPublishedDate().getTime()));
			ps.setInt(3, art.getAuthor().getUserId());

			saved = sqlCmd.ExecuteNonQuery(ps) > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saved;

	}

	private boolean updateArticle(Article art) {

		boolean updated = false;
		String sqlStr = "Update Articles set " + "Title = ?," + "Content = ?,"
				+ "AuthorID = ?" + " WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(0, art.getTitle());
			ps.setString(1, art.getContent());
			ps.setInt(2, art.getAuthor().getUserId());
			ps.setInt(3, art.getID());

			updated = sqlCmd.ExecuteNonQuery(ps) > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
}
