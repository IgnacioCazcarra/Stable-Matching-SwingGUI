package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	
	
	public Solution() {}
	
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

		for(Person pe : personas) {
			System.out.println();
			if(pe instanceof Boy) {
				System.out.println("Novio de "+pe.getName()+": "+((Boy) pe).getNovia().getName());
			}
			else if(pe instanceof Girl) {
				System.out.println("Novia de "+pe.getName()+": "+((Girl) pe).getNovio().getName());
			}
		}
	}
	
	
	
}
