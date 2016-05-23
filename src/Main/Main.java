package Main;

import javax.swing.JFrame;

public class Main {

	static JFrame f;
	

	public static void main (String args[]){

		//Start by bringing up a game selection menu
		f = new Initializer("Jeopardy");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
}
