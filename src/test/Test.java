package test;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import model.Boy;
import model.GUI;
import model.Girl;
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
		
		gui.pick_options(personas);
	}
		
}
