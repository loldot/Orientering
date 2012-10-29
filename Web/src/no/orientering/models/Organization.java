package no.orientering.models;

public class Organization {
	private int ranking;
	private String name;
	private int points;
		
	public Organization(int ranking, String name, int points) {
		this.ranking = ranking;
		this.name = name;
		this.points = points;
	}
	
	public int getRanking() {
		return ranking;
	}
	public String getName() {
		return name;
	}
	public int getPoints() {
		return points;
	}
}
