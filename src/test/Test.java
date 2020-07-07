package test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

import model.Boy;
import model.GUI;
import model.Girl;
import model.Pair;
import model.Person;

public abstract class Test {

	public static void main(String[] args) {
		
		GUI gui = new GUI();
		
		List<JTextField> nombres = gui.enter_names(3);		
		List<Person> personas = new ArrayList<Person>();
		
		for(int i = 0 ; i < nombres.size() ; i++) {
			if(i<(nombres.size()/2)) {
				String text = nombres.get(i).getText();
				Girl girl = new Girl(text, null);
				personas.add(girl);
			}
			else {
				String text = nombres.get(i).getText();
				Boy boy = new Boy(text, null);
				personas.add(boy);
			}
		}
		Pair pair = gui.pick_options(personas);
		List<Person> p = pair.getPersonas();
		System.out.println(pair.getEleccionesChicas().size()+ " "+pair.getEleccionesChicas().get(0).size());;
		int j = 0;
		for(int i = 0 ; i < personas.size() ; i++) {
			if(i%2==1 && i!=5) {
				j++;
			}
			if(personas.get(i) instanceof Boy) {
				((Boy) personas.get(i)).setGirlsList(pair.getEleccionesChicos().get(j));
			}
			else if(personas.get(i) instanceof Girl) {
				((Girl) personas.get(i)).setBoyList(pair.getEleccionesChicas().get(j));
			}
		}
		
		List<Boy> chicos = new ArrayList<Boy>();
		List<Girl> chicas = new ArrayList<Girl>();

		for(Person person : p) {
			if(person instanceof Boy) {
				chicos.add((Boy)person);
			}
			else if(person instanceof Girl) {
				chicas.add((Girl)person);
			}
		}

		
		boolean mientras = false;
		int i=0;
		
		while(!mientras) {
			
			/*Chequeamos que haya alguien sin novia*/
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
						System.out.println("ahora tiene novia");
						currentBoy.setNovia(currentBoy.getGirlsList().get(pos));
						currentBoy.getGirlsList().get(pos).setNovio(currentBoy);
					}
					else {
						//Mapeamos a los hombres segun la preferencia de la mujer que est)amos evaluando
						Map<String,Integer> map = new HashMap<String, Integer>();
						List<Boy> listaMujerActual = currentBoy.getGirlsList().get(pos).getBoyList();
						
						for(int posAux = 0; posAux < listaMujerActual.size(); posAux++) {
							Boy aux = currentBoy.getGirlsList().get(pos).getBoyList().get(posAux);
							map.put(aux.getName(), posAux+1);
						}
						
						if(map.get(currentBoy.getName())<map.get(currentBoy.getGirlsList().get(pos).getNovio().getName())) {
							for(Boy b : currentBoy.getGirlsList().get(pos).getBoyList()) {
								
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

		for(Person pe : p) {
			System.out.println(pe.getName());
			if(pe instanceof Boy) {
				System.out.println("boy gf "+((Boy) pe).getNovia());
			}
			else if(pe instanceof Girl) {
				System.out.println("girl bf "+((Girl) pe).getNovio());
			}
		}
		
	}
		
}
