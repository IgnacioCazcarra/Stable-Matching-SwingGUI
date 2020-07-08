package model;

import java.util.ArrayList;
import java.util.List;

public class Girl extends Person{

	private List<Boy> boysList = new ArrayList<Boy>();
	private Boy novio;
	
	public Girl(String name, Boy novio) {
		super(name);
		this.novio = novio;
	}

	public List<Boy> getBoysList() {
		return boysList;
	}

	public void setBoysList(List<Boy> boyList) {
		this.boysList = boyList;
	}

	public Boy getNovio() {
		return novio;
	}

	public void setNovio(Boy novio) {
		this.novio = novio;
	}

}
