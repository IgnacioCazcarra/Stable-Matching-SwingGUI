package model;

import java.util.ArrayList;
import java.util.List;

public class Girl extends Person{

	private List<Boy> boyList = new ArrayList<Boy>();
	private Boy novio;
	
	public Girl(String name, Boy novio) {
		super(name);
		this.novio = novio;
	}

	public List<Boy> getBoyList() {
		return boyList;
	}

	public void setBoyList(List<Boy> boyList) {
		this.boyList = boyList;
	}

	public Boy getNovio() {
		return novio;
	}

	public void setNovio(Boy novio) {
		this.novio = novio;
	}

	public List<Boy> convertirLista(List<Person> personas){
		 List<Boy> boys = new ArrayList<Boy>();
		for(Person p : personas) {
			if(p instanceof Boy) {
				boys.add((Boy) p);
			}
		}
		return boys;
	}
}
