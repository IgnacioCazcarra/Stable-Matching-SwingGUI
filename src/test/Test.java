package test;



import model.GUI;
import model.Solution;

public abstract class Test {

	public static void main(String[] args) {
		
		GUI gui = new GUI();
		Solution solution = new Solution();
		gui.setSolution(solution);
		
		try {
			gui.pick_options(gui.createPeople(gui.enter_names(3)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
