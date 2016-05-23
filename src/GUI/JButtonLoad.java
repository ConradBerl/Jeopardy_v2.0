package GUI;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class JButtonLoad extends JButton{


	private static final long serialVersionUID = 1L;
	
	JFileChooser chooser = new JFileChooser();

	
	
	public JButtonLoad (ActionListener listener){
		this.addActionListener(listener);
		this.setText("Load Game");
	}
	
	
	public String fileChooser(){
		String newGame = null;

		chooser.setDialogTitle("Game Loader");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		
		int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    newGame = chooser.getSelectedFile().getAbsolutePath();
		    
	    }else if(returnVal == JFileChooser.CANCEL_OPTION){
	    	 System.out.println("Cancel was selected");
	    }
		

		
		return newGame;
	}
}
