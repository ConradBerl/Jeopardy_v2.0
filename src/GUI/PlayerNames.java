package GUI;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Main.Main;

public class PlayerNames {
	

	
	private static String typedText0 = null;
	private static JTextField textField0;
	private static String typedText1 = null;
	private static JTextField textField1;
	private static String typedText2 = null;
	private static JTextField textField2;

	//Create the frame
	public static void PNames(){
	    //Create and set up the window.
	    JFrame frame = new JFrame("Player Names");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500, 230);

	    
	    //Stuff going into the window
		textField0 = new JTextField(10);
		textField1 = new JTextField(10);
		textField2 = new JTextField(10);
	    Object[] array = {"Player 0", textField0, "Player 1", textField1, "Player 2", textField2};

	    //get the 'value' to see if OK was pressed
	    JOptionPane.showMessageDialog(frame, array);
	    //action resulting
			typedText0 = textField0.getText();
			Main.labelArrayName[0].setText(typedText0);
			typedText1 = textField1.getText();
			Main.labelArrayName[1].setText(typedText1);
			typedText2 = textField2.getText();
			Main.labelArrayName[2].setText(typedText2);
	}
}