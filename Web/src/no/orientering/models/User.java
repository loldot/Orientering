package no.orientering.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="author")
@XmlAccessorType(XmlAccessType.NONE)
public class User {
	@XmlElement
	private int userId;
	@XmlElement
	private String userName;
	
	private String password;
	private String team;
	
	private Person personalia;
	private Person friend;
	private Person emergencyContact;

	
	public User(){
		friend = new NullPerson();
		emergencyContact = new NullPerson();
	}
	
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
	public String getTeam() {
		return team;
	}
	public void setTeam(Organization team) {
		this.team = team.getName();
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
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
