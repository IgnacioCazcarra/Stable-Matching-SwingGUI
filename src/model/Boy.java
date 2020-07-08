package model;

import java.util.ArrayList;
import java.util.List;

public class Boy extends Person{

	private List<Girl> girlsList = new ArrayList<Girl>();
	private Girl novia;
	
	public Boy(String name, Girl novia) {
		super(name);
		this.novia = novia;
	}

	public List<Girl> getGirlsList() {
		return girlsList;
	}

	public void setGirlsList(List<Girl> girlsList) {
		this.girlsList = girlsList;
	}

	public Girl getNovia() {
		return novia;
	}

	public void setNovia(Girl novia) {
		this.novia = novia;
	}
}
