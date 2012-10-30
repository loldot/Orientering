package no.orientering.models;

public class NullPerson extends Person
{
	@Override
	public boolean isNull()
	{
		return true;
	}
	
	@Override
	public String getName() {
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
	public void setBirthYear(int birthYear) {	}
	
	@Override
	public void setName(String name) { }
	
	@Override
	public void setPhone(String phone) { }
}
