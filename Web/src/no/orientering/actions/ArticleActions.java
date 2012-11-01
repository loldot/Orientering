package no.orientering.actions;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ArticleActions {
	private Gson gson = new GsonBuilder().create();
	
	public void postArticle(String document){
		ArticleDAO articles = new ArticleDAO();		
		Article newArt = gson.fromJson(document, Article.class);
		articles.saveArticle(newArt);
	}
	public void putArticle(String document){
		ArticleDAO articles = new ArticleDAO();
		Article newArt = gson.fromJson(document, Article.class);
		articles.saveArticle(newArt);
	}
	public String getArticle(){
		ArticleDAO articles = new ArticleDAO();
		return gson.toJson(articles.getArticles());
	}
	public String getArticle(int id){
		ArticleDAO articles = new ArticleDAO();
		return gson.toJson(articles.getArticle(id));
	}
}
