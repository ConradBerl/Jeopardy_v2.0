package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Database.Database;
import GUI.JButtonLoad;

public class Initializer extends JFrame implements ActionListener{

	
	private static final long serialVersionUID = 1L;

	final Font defaultFont = new Font("Helvetica", Font.BOLD, 26);
	
	//the main frame used by the game, accessible for the creation of a new game while in the game
	static JFrame f;

	//have some features available in the menu bar
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem menuItem = new JMenuItem("Load", KeyEvent.VK_N);
	JButtonLoad load = new JButtonLoad(null);
	
	JPanel panel = new JPanel();
	JLabel welcome = new JLabel();


	
	//==================================================================
	//******************************************************************
	// 			BOARD LAYOUT
	//******************************************************************
	//==================================================================
	
	public Initializer (String title){
		super(title);
		
		setLayout(new BorderLayout());
		
		//the text
		welcome.setText("Welcome to Jeopardy");
		welcome.setFont(defaultFont);
		panel.add(welcome, BorderLayout.CENTER);
		
		
		//The Menu
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		//the MenuItem
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//adding the menu bar to the fame
		setJMenuBar(menuBar);
		add(panel);
		
		//JFrame size and alignment
		setSize(400, 120);
		setLocationRelativeTo(null);

		setVisible( true );
	}


	//=========================================
	// ON-CLICK actions
	//=========================================

	//what happens on button click
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * Loading a different game
		 */
		if (e.getSource() == menuItem){
			//open a dialog box asking for the file the player wants to load (i.e. the database text file)
		    String path = load.fileChooser();
		    
		    /*
		     * If a file is chosen, then proceed
		     * otherwise do nothing
		     */
		    
		    if (path != null){
			    System.out.println(path);
	

				//hide and close the previous game
				setVisible(false);
				dispose();
				
				//create and open a new game base on a new database
				Jeopardy.database = Database.readQuestions(path);
				f = new Jeopardy(path);
		    }
		}
	}
		

}
