package test;



import model.GUI;
import model.Solution;

public abstract class Test {

	public static void main(String[] args) {
		
		GUI gui = new GUI();
		Solution solution = new Solution();
		gui.setSolution(solution);
		gui.pick_options(gui.createPeople(gui.enter_names(3)));
		
	}
		
}
