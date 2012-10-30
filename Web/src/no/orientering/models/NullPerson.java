package no.orientering.models;

public class NullPerson extends Person {
	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public String getFirstName() {
		return "Kontakt ikke oppgitt";
	}

	@Override
	public int getBirthYear() {
		return 0;
	}

	@Override
	public String getPhone() {
		return "Kontakt ikke opggitt";
	}

	@Override
	public void setBirthYear(int birthYear) {
	}

	@Override
	public void setFirstName(String name) {
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setID(int iD) {

	}

	@Override
	public String getLastName() {
		return "";
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		super.setLastName(lastName);
	}

	@Override
	public void setPhone(String phone) {
	}
}
