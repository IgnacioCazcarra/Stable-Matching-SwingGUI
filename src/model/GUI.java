package model;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	private Solution solution;

	public GUI() {
	}

	public List<JTextField> enter_names(int cantidadNombres) throws Exception {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 8, 8));
		
		List<JTextField> listaNombres = names(panel, cantidadNombres);

		int result = JOptionPane.showConfirmDialog(null, panel, "Stable Matching", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result != JOptionPane.OK_OPTION)
			throw new Exception("Operacion cancelada");

		return listaNombres;
	}

	public void pick_options(List<Person> personas) throws Exception {

		String[] nchicas = new String[personas.size() / 2];
		for (int i = 0; i < personas.size() / 2; i++) {
			nchicas[i] = personas.get(i).getName();
		}

		String[] nchicos = new String[personas.size() / 2];
		for (int j = 0, i = personas.size() / 2; i < personas.size(); i++, j++) {
			nchicos[j] = personas.get(i).getName();
		}

		JPanel panel = new JPanel(new GridLayout(personas.size(), 2, 8, 8));

		List<JComboBox> elecciones = getElectionsFromPeople(panel, personas, nchicas, nchicos);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Stable Matching", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result != JOptionPane.OK_OPTION)
			throw new Exception("Operacion cancelada");

		
		List<Person> eleccionesAux = adjustElections(elecciones, personas);
		
		personas = electionsToPeopleList(personas, eleccionesAux);

		List<Boy> chicos = new ArrayList<Boy>();
		List<Girl> chicas = new ArrayList<Girl>();

		for (Person person : personas) {
			if (person instanceof Boy) {
				chicos.add((Boy) person);

			} else if (person instanceof Girl) {
				chicas.add((Girl) person);
			}
		}

		printResults(solution.stable_matching(chicos, chicas, personas));

	}
	
	/*Other methods*/
	public List<JTextField> names(JPanel panel, int cantidadNombres){
		
		List<JTextField> listaNombres = new ArrayList<JTextField>();

		for (int i = 0; i < 2 * cantidadNombres; i++) {

			JTextField chico = new JTextField("");

			if (i < cantidadNombres) {
				JTextField chica = new JTextField("");
				panel.add(new JLabel("Nombre de la chica N°" + (i + 1) + ": "));
				panel.add(chica);
				listaNombres.add(chica);
			} else {
				panel.add(new JLabel("Nombre del chico N°" + (i - cantidadNombres + 1) + ": "));
				panel.add(chico);
				listaNombres.add(chico);
			}
		}
		
		return listaNombres;
	}
	
	public List<Person> electionsToPeopleList(List<Person> personas, List<Person> eleccionesAux){
		
		for (int i = 0; i < Math.sqrt(eleccionesAux.size() / 2); i++) {

			for (int j = 0; j < personas.size() / 2; j++) {

				if (personas.get(j) instanceof Girl && eleccionesAux.get(j + (i * personas.size() / 2)) instanceof Boy) {
					((Girl) personas.get(j)).getBoysList().add((Boy) eleccionesAux.get(j + (i * personas.size() / 2)));
				}
			}

			for (int k = personas.size() / 2; k < personas.size(); k++) {

				if (personas.get(k) instanceof Boy
						&& eleccionesAux.get((int) (k + Math.pow(personas.size()/2, 2)-(personas.size()/2) + (i * personas.size() / 2))) instanceof Girl) {
					((Boy) personas.get(k)).getGirlsList()
							.add((Girl) eleccionesAux.get((int) (k + Math.pow(personas.size()/2, 2)-(personas.size()/2) + (i * personas.size() / 2))));

				}
			}

		}
		
		return personas;
	}
	
	public List<Person> adjustElections(List<JComboBox> elecciones, List<Person> personas){
		List<Person> personasAux = new ArrayList<Person>();

		for (JComboBox eleccion : elecciones) {
			for (Person p : personas) {
				if (p.getName().equals(eleccion.getSelectedItem().toString())) {
					personasAux.add(p);
				}
			}
		}

		Collections.reverse(personasAux);
		
		List<Person> aux1 = personasAux.subList(0, personasAux.size()/2);
		Collections.reverse(aux1);

		List<Person> aux2 = personasAux.subList(personasAux.size()/2, personasAux.size());
		Collections.reverse(aux2);

		List<Person> eleccionesAux = new ArrayList<Person>();
		
		eleccionesAux.addAll(aux1);
		eleccionesAux.addAll(aux2);
		
		return eleccionesAux;
	}
	
	public List<JComboBox> getElectionsFromPeople(JPanel panel, List<Person> personas, String[] nchicas, String[] nchicos){
		
		List<JComboBox> elecciones = new ArrayList<JComboBox>();

		int option = 0;

		for (int i = 0; i < personas.size(); i++) {
			option++;
			if (option == (1 + personas.size() / 2)) {
				option = 1;
			}
			if (i < personas.size() / 2) {
				for (int j = personas.size() / 2; j < personas.size(); j++) {
					panel.add(new JLabel("Ingrese la " + option + "° opcion de " + personas.get(j).getName() + ":"));
					JComboBox comboChicas = new JComboBox(nchicas);
					panel.add(comboChicas);
					elecciones.add(comboChicas);
				}
			} else {
				for (int j = 0; j < personas.size() / 2; j++) {
					panel.add(new JLabel("Ingrese la " + option + "° opcion de " + personas.get(j).getName() + ":"));
					JComboBox comboChicos = new JComboBox(nchicos);
					panel.add(comboChicos);
					elecciones.add(comboChicos);
				}
			}

		}
		return elecciones;
	}
	
	public List<Person> createPeople(List<JTextField> nombres) {

		List<Person> personas = new ArrayList<Person>();

		for (int i = 0; i < nombres.size(); i++) {
			if (i < (nombres.size() / 2)) {
				String text = nombres.get(i).getText();
				Girl girl = new Girl(text, null);
				personas.add(girl);
			} else {
				String text = nombres.get(i).getText();
				Boy boy = new Boy(text, null);
				personas.add(boy);
			}
		}

		return personas;
	}

	public void printResults(List<Boy> chicos) {
		
		JPanel panel = new JPanel(new GridLayout(1,0, 8, 8));
		
		for(Boy b : chicos) {
			panel.add(new JLabel("Novia de "+b.getName()+": "+b.getNovia().getName()));
		}
	
		int result = JOptionPane.showConfirmDialog(null, panel, "Stable Matching",JOptionPane.PLAIN_MESSAGE,
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

}
