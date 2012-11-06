package no.orientering.DAO.Web;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import no.orientering.models.Organization;

public class OrganizationWebDAO {
	private static final String URL = "http://o-bergen.no/idrett/ranking/klubbranking.cgi";
	
	public List<Organization> getAllOrganizations(){
		List<Organization> officialRanking = new ArrayList<>();
		
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode root = cleaner.clean(new URL(URL));
			List<TagNode> tags = root.getElementListByName("table", true);
			
			//Skip idiotisk table for layout
			TagNode table = tags.get(1);
			
			for(Object child : table.getChildren()){
				TagNode tr = (TagNode)child;
				List ths = tr.getChildren();
				Organization o = new Organization(
						Integer.parseInt(((TagNode)ths.get(0)).getText().toString()), 
						((TagNode)ths.get(1)).getText().toString(), 
						Integer.parseInt(((TagNode)ths.get(2)).getText().toString()));
				officialRanking.add(o);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return officialRanking;
	}
}
