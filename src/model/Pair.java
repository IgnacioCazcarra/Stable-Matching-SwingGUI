package model;

import java.util.List;

public class Pair {
	private List<Person> personas;
	private List<List<Girl>> eleccionesChicos;
	private List<List<Boy>> eleccionesChicas;
	
	
	public Pair(List<Person> personas, List<List<Girl>> eleccionesChicos, List<List<Boy>> eleccionesChicas) {
		super();
		this.personas = personas;
		this.eleccionesChicos = eleccionesChicos;
		this.eleccionesChicas = eleccionesChicas;
	}
	public List<Person> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Person> personas) {
		this.personas = personas;
	}
	public List<List<Girl>> getEleccionesChicos() {
		return eleccionesChicos;
	}
	public void setEleccionesChicos(List<List<Girl>> eleccionesChicos) {
		this.eleccionesChicos = eleccionesChicos;
	}
	public List<List<Boy>> getEleccionesChicas() {
		return eleccionesChicas;
	}
	public void setEleccionesChicas(List<List<Boy>> eleccionesChicas) {
		this.eleccionesChicas = eleccionesChicas;
	}
	

	
}
