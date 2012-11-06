package no.orientering.DAO.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import no.orientering.models.Article;


public class ArticleDAO {

	private Connection oldConn;

	private SqlCommands sqlCmd = new SqlCommands();

	public ArticleDAO() {
		try {
			//oldConn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Article> getArticles() {
		List<Article> artList = new ArrayList<Article>();
		
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM articles ORDER BY publishDate";

		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			s = conn.createStatement();
			rs = s.executeQuery(sqlStr);
			Article art = null;
			UserDAO users = new UserDAO();
			while (rs.next()) {
				art = new Article();
				art.setID(rs.getInt("ID"));
				art.setContent(rs.getString("content"));
				art.setPublishedDate(rs.getDate("publishDate"));
				art.setTitle(rs.getString("title"));
				art.setAuthor(users.GetBy(rs.getInt("authorId")));
				artList.add(art);
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseHelper.close(s);
			DatabaseHelper.close(rs);
			DatabaseHelper.close(conn);
		}

		return artList;
	}

	public Article getArticle(int ID) {
		Article art = null;

		String sqlStr = "Select * from articles where ID = ?";
		try {
			PreparedStatement ps = oldConn.prepareStatement(sqlStr);
			ps.setInt(1, ID);
			ResultSet rs = sqlCmd.makeResultSet(ps);
			if (!rs.next())
				throw new SQLException("No match");

			UserDAO users = new UserDAO();			
			art = new Article();
			art.setID(rs.getInt("ID"));
			art.setContent(rs.getString("Content"));
			art.setPublishedDate(rs.getDate("PublishedDate"));
			art.setTitle(rs.getString("Title"));
			art.setAuthor(users.GetBy(rs.getInt("authorId")));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return art;
	}

	public int saveArticle(Article art) {
		int saved = -1;
		if (art.getID() < 1) {
			saved = saveNew(art);
		} else {
			saved = updateArticle(art);
		}

		return saved;
	}

	private int saveNew(Article art) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int saved = -1;

		String sqlStr = "Insert into `Articles` set (`Title`,`Content`"
				+ "`PublishedDate`,`AuthorID`) Values" + "(?,?,?,?)";
		try {
			ps = oldConn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
			ps.setString(0, art.getTitle());
			ps.setString(1, art.getContent());
			ps.setDate(2, new java.sql.Date(art.getPublishedDate().getTime()));
			ps.setInt(3, art.getAuthor().getUserId());
			
			sqlCmd.ExecuteNonQuery(ps);
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
		}
		return saved;

	}

	private int updateArticle(Article art) {

		int updated = -1;
		String sqlStr = "Update Articles set " + "Title = ?," + "Content = ?,"
				+ "AuthorID = ?" + " WHERE ID = ?";
		try {
			PreparedStatement ps = oldConn.prepareStatement(sqlStr);
			ps.setString(0, art.getTitle());
			ps.setString(1, art.getContent());
			ps.setInt(2, art.getAuthor().getUserId());
			ps.setInt(3, art.getID());

			sqlCmd.ExecuteNonQuery(ps);
			return art.getID();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updated;
	}
}
