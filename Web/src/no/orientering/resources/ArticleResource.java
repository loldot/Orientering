package no.orientering.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import no.orientering.DAO.jdbc.ArticleDAO;
import no.orientering.models.Article;

@Path("/articles")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class ArticleResource {
	private final ArticleDAO db = new ArticleDAO();
	
	@GET
	public final List<Article> getAllArticles(){
		return db.getArticles();
	}
	
	@GET
	@Path("{id}")
	public final Article getSpecificArticle(@PathParam("id") final int id){
		return db.getArticle(id);
	}
	
	@POST
	public final Response makeArticle(final Article article){
		final int id = db.saveArticle(article);
		return Response.created(URI.create("/" + id)).build();
	}
	
	@PUT
	public final Response putArticle(@PathParam("id") final int id, final Article article){
		if(db.getArticle(id) == null)
	}

}
