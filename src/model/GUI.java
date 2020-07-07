package model;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	public GUI() {}
	
	public List<JTextField> enter_names(int cantidadNombres) {
		List<JTextField> listaNombres = new ArrayList<JTextField>();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2,8,8));


	    for(int i = 0 ; i < 2*cantidadNombres ; i++) {
	    	
	        JTextField chico = new JTextField("");
	        
	        if(i<cantidadNombres) {
	        	JTextField chica= new JTextField("");
	        	panel.add(new JLabel("Nombre de la chica N°"+(i+1)+": "));
	        	panel.add(chica);
			    listaNombres.add(chica);
	        }
	        else {
		        panel.add(new JLabel("Nombre del chico N°"+(i-cantidadNombres+1)+": "));
			    panel.add(chico);
			    listaNombres.add(chico);
	        }
	    }
	    
		
	    int result = JOptionPane.showConfirmDialog(null, panel, "Stable Matching",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
	    return listaNombres;
	}
		

	
	public Pair pick_options(List<Person> personas) {
		
		
		String[] nchicas = new String[personas.size()/2];
		for(int i = 0 ; i < personas.size()/2; i++) {
			nchicas[i] = personas.get(i).getName();
		}
		
		String[] nchicos = new String[personas.size()/2];
		for(int j=0, i = personas.size()/2 ; i < personas.size(); i++,j++) {
			nchicos[j] = personas.get(i).getName();
		}
		
		List<JComboBox> elecciones = new ArrayList<JComboBox>();
		
	    JPanel panel = new JPanel(new GridLayout(0,2,8,8));
	    int option=0;
		for(int i = 0 ; i < personas.size(); i++) {
			option++;
			if(option==(1+personas.size()/2)) {
				option=1;
			}
			if(i<personas.size()/2) {
				for(int j = personas.size()/2 ; j < personas.size(); j++) {
					panel.add(new JLabel("Ingrese la "+ option +"° opcion de "+personas.get(j).getName()+":" ));
					JComboBox comboChicas = new JComboBox(nchicas);
					panel.add(comboChicas);
					elecciones.add(comboChicas);
				}
			}
			else {
				for(int j = 0 ; j < personas.size()/2 ; j++) {
					panel.add(new JLabel("Ingrese la "+ option +"° opcion de "+personas.get(j).getName()+":" ));
					JComboBox comboChicos = new JComboBox(nchicos);
					panel.add(comboChicos);
					elecciones.add(comboChicos);
				}
			}
			
		}
		int result = JOptionPane.showConfirmDialog(null, panel, "Stable Matching",
		        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		int current = 0;
		
		List<Person> personasAux = new ArrayList<Person>();
		
		for(JComboBox eleccion : elecciones) {
			for(Person p : personas) {
				if(p.getName().equals(eleccion.getSelectedItem().toString())) {
					personasAux.add(p);
				}
			}	
		}
		
		for(int i = 0 ; i < personasAux.size() ; i++) {
			
			if(i%(personas.size()/2)==0 && i!=0) {
				current++;
			}
			if(personas.get(current) instanceof Boy && personasAux.get(i) instanceof Girl) {
				((Boy) personas.get(current)).getGirlsList().add((Girl) personasAux.get(i));
			}
			else if(personas.get(current) instanceof Girl && personasAux.get(i) instanceof Boy) {
				((Girl) personas.get(current)).getBoyList().add((Boy) personasAux.get(i));
			}
		}
	   
		List<List<Girl>> eleccionesChicos = new ArrayList<List<Girl>>();
		List<List<Boy>> eleccionesChicas = new ArrayList<List<Boy>>();
		
		for(Person p : personas) {
			if(p instanceof Boy) {
				eleccionesChicos.add(((Boy) p).getGirlsList());
			}
			else if(p instanceof Girl) {
				eleccionesChicas.add(((Girl) p).getBoyList());
			}
		}
		
		System.out.println("tamaño eleccioneschicos "+eleccionesChicos.size());
		System.out.println("tamaño eleccioneschicas "+eleccionesChicas.size());
		Pair pair = new Pair(personas,eleccionesChicos,eleccionesChicas);
		
	   return pair;
	}
	
	
}
