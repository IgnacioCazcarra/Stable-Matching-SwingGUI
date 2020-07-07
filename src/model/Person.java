package model;

public class Person {
	private int idPerson;
	private String name;
	
	public Person(int idPerson, String name) {
		super();
		this.idPerson = idPerson;
		this.name = name;
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
