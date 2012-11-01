package no.orientering.models;

public class Person {
	private int ID;
	private String firstName;
	private String lastName;
	private String phone;
	private int birthYear;
	private String address;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isNull()
	{
		return false;
	}
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	@Override
	public String toString() {
		return "[id=" + ID + ", firstName=" + firstName + ", lastName=" +
				lastName + ", phone=" + phone + ", birthYear=" + birthYear + 
				", address="+ address + "]";
	}
}
