package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public Solution() {
	}

	public List<Boy> stable_matching(List<Boy> chicos, List<Girl> chicas, List<Person> personas) {

		boolean flag = false;
		int i = 0;

		while (!flag) {
			//while at least one girl doesnt have a boyfriend
			flag = checkFlag(chicas);

			if (chicos.get(i).getNovia() == null) {
				getGirlfriendForBoy(chicos.get(i));
			}

			if (i < chicos.size() - 1) {
				i++;
			} 
			else {
				i = 0;
			}

		}

		return chicos;
	}

	public boolean checkFlag(List<Girl> chicas) {
		boolean flag = true;
		for (Girl g : chicas) {
			if (g.getNovio() == null) {
				flag = false;
			}
		}
		return flag;
	}

	public void getGirlfriendForBoy(Boy currentBoy) {
		int pos = 0;

		while (currentBoy.getNovia() == null && pos < currentBoy.getGirlsList().size()) {
			
			if (currentBoy.getGirlsList().get(pos).getNovio() == null) {
				currentBoy.setNovia(currentBoy.getGirlsList().get(pos));
				currentBoy.getGirlsList().get(pos).setNovio(currentBoy);
			} 
			else {
				checkIfBestOption(currentBoy, pos);
			}
			pos++;
		}

	}
	
	public Map<String,Integer> mapCurrentGirlElections(Boy currentBoy, int pos){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Boy> listaMujerActual = currentBoy.getGirlsList().get(pos).getBoysList();

		for (int posAux = 0; posAux < listaMujerActual.size(); posAux++) {
			Boy aux = currentBoy.getGirlsList().get(pos).getBoysList().get(posAux);
			map.put(aux.getName(), posAux + 1);
		}
		
		return map;
	}

	public void checkIfBestOption(Boy currentBoy, int pos) {
		
		Map<String, Integer> map = mapCurrentGirlElections(currentBoy, pos);

		if (map.get(currentBoy.getName()) < map.get(currentBoy.getGirlsList().get(pos).getNovio().getName())) {
			for (Boy b : currentBoy.getGirlsList().get(pos).getBoysList()) {
				if (b.getNovia() != null) {
					if (b.getNovia().equals(currentBoy.getGirlsList().get(pos))) {
						b.setNovia(null);
					}
				}
			}
			currentBoy.getGirlsList().get(pos).setNovio(currentBoy);
			currentBoy.setNovia(currentBoy.getGirlsList().get(pos));
		}
	}
	
}
