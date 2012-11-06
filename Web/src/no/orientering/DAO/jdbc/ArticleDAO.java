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

		String sqlStr = "SELECT * FROM articles ORDER BY publishDate desc";

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
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "Select * from articles where ID = ?";
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sqlStr);
			ps.setInt(1, ID);
			rs = ps.executeQuery();
			if (!rs.first())
				throw new SQLException("No match");

			UserDAO users = new UserDAO();			
			art = new Article();
			art.setID(rs.getInt("ID"));
			art.setContent(rs.getString("Content"));
			art.setPublishedDate(rs.getDate("publishDate"));
			art.setTitle(rs.getString("title"));
			art.setAuthor(users.GetBy(rs.getInt("authorId")));

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
			DatabaseHelper.close(conn);
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

	public int saveNew(Article art) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		int saved = -1;

		String sqlStr = "Insert into Articles (Title,Content,"
				+ "publishDate,AuthorID) Values" + "(?,?,?,?)";
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, art.getTitle());
			ps.setString(2, art.getContent());
			ps.setDate(3, new java.sql.Date(art.getPublishedDate().getTime()));
			ps.setInt(4, art.getAuthor().getUserId());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DatabaseHelper.close(ps);
			DatabaseHelper.close(rs);
			DatabaseHelper.close(conn);
		}
		return saved;

	}

	public int updateArticle(Article art) {
		PreparedStatement ps = null;
		Connection conn = null;
		
		int updated = -1;
		String sqlStr = "Update Articles set " + "Title = ?," + "Content = ?,"
				+ "AuthorID = ?" + " WHERE ID = ?";
		try {
			conn = DatabaseHelper.getConnection("java:comp/env/jdbc/noeheftig");
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, art.getTitle());
			ps.setString(2, art.getContent());
			ps.setInt(3, art.getAuthor().getUserId());
			ps.setInt(4, art.getID());

			ps.executeUpdate();
			return art.getID();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}
}
