package no.orientering.models;

public class User {
	int userId;
	String userName, password;
	Organization team;
	
	Person personalia;
	Person friend = new NullPerson();
	Person emergencyContact = new NullPerson();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Organization getTeam() {
		return team;
	}
	public void setTeam(Organization team) {
		this.team = team;
	}
	public Person getPersonalia() {
		return personalia;
	}
	public void setPersonalia(Person personalia) {
		this.personalia = personalia;
	}
	public Person getFriend() {
		return friend;
	}
	public void setFriend(Person friend) {
		this.friend = friend;
	}
	public Person getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(Person emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public int getUserId() {
		return userId;
	}
}
