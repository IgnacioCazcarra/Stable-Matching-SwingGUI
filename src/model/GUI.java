package model;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		

	
	public void pick_options(List<Person> personas) {
		
		
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
		
		Collections.reverse(personasAux);
		
		for(int i = 0 ; i < personasAux.size() ; i++) {
			if(i%(personas.size()/2)==0 && i!=0) {
				current++;
			}
			
			if(personas.get(current) instanceof Boy && personasAux.get(i) instanceof Girl) {
				((Boy) personas.get(current)).getGirlsList().add((Girl) personasAux.get(i));
			}
			else if(personas.get(current) instanceof Girl && personasAux.get(i) instanceof Boy) {
				((Girl) personas.get(current)).getBoysList().add((Boy) personasAux.get(i));
			}
		}
	   
	   List<Boy> chicos = new ArrayList<Boy>();
	   List<Girl> chicas = new ArrayList<Girl>();
	
	   for(Person person : personas) {
		   if(person instanceof Boy) {
			   chicos.add((Boy)person);
		   }
		   else if(person instanceof Girl) {
			   chicas.add((Girl)person);
		   }
		}
	
		this.stable_matching(chicos, chicas, personas);
		
	}
	
	public void stable_matching(List<Boy> chicos, List<Girl> chicas, List<Person> personas) {
		/*Stable matching*/
		boolean mientras = false;
		int i=0;
		
		while(!mientras) {
			
			mientras = true;
			for(Girl g : chicas) {
				if(g.getNovio()==null) {
					mientras = false;
				}
			}
			
			Boy currentBoy = null;
			
			if(chicos.get(i).getNovia()==null) {
				currentBoy = chicos.get(i);	
				
				int pos = 0;
				
				while(currentBoy.getNovia()==null && pos<currentBoy.getGirlsList().size()) {
					
					if(currentBoy.getGirlsList().get(pos).getNovio()==null) {
						currentBoy.setNovia(currentBoy.getGirlsList().get(pos));
						currentBoy.getGirlsList().get(pos).setNovio(currentBoy);
					}
					else {
						Map<String,Integer> map = new HashMap<String, Integer>();
						List<Boy> listaMujerActual = currentBoy.getGirlsList().get(pos).getBoysList();
						
						for(int posAux = 0; posAux < listaMujerActual.size(); posAux++) {
							Boy aux = currentBoy.getGirlsList().get(pos).getBoysList().get(posAux);
							map.put(aux.getName(), posAux+1);
						}
						
						if(map.get(currentBoy.getName())<map.get(currentBoy.getGirlsList().get(pos).getNovio().getName())) {
							for(Boy b : currentBoy.getGirlsList().get(pos).getBoysList()) {
								if(b.getNovia()!=null) {
									if(b.getNovia().equals(currentBoy.getGirlsList().get(pos))) {
										b.setNovia(null);
									}
								}
							}
							currentBoy.getGirlsList().get(pos).setNovio(currentBoy);
							currentBoy.setNovia(currentBoy.getGirlsList().get(pos));
						}
					}
					pos++;
				}
	
				
			}
			
			if(i<chicos.size()-1) {
				i++;
			}
			else {
				i=0;
			}
			
		}
		/**/
		/*Result*/
		for(Person pe : personas) {
			System.out.println();
			if(pe instanceof Boy) {
				System.out.println("Novio de "+pe.getName()+": "+((Boy) pe).getNovia().getName());
			}
			else if(pe instanceof Girl) {
				System.out.println("Novio de "+pe.getName()+": "+((Girl) pe).getNovio().getName());
			}
		}
		/**/
	}
	
	
}
