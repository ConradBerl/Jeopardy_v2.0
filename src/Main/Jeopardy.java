package Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import Database.Database;
import Database.Player;
import GUI.JButtonLoad;
import GUI.JeopardyButton;

//specialized JFrame
//controller of this application
public class Jeopardy extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	final Font defaultFont = new Font("Helvetica", Font.BOLD, 18);
	
	//declare static database, so it can be used through the application
	static private Database database;
	
	
	private int currentPointValue = 0;

	//the main frame used by the game, accessible for the creation of a new game while in the game
	static JFrame f;
	
	//declare array of buttons
	JeopardyButton [] buttonArray = new JeopardyButton [database.getNumCategories()*database.getNumQuestions()];
	
	//declare specialized panel
	JLabel answer = new JLabel();
	JLabel question = new JLabel();
	
	//array of players
	Player[] pArray = new Player[3];
	
	//array of labels for name AND score
	JLabel[] labelArrayName = new JLabel[(pArray.length)];
	JLabel[] labelArrayScore = new JLabel[(pArray.length)];
	JButton[] buttonAdd = new JButton[pArray.length];
	JButton[] buttonSubtract = new JButton[pArray.length];


	//have some features available in the menu bar
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem menuItem = new JMenuItem("Load", KeyEvent.VK_N);
	JButtonLoad load = new JButtonLoad(null);
	
	//declare variables for actions performed
	int catGet;
	int queGet;
	boolean questionSelected;
	

	
	
	//==================================================================
	//******************************************************************
	// 			BOARD LAYOUT
	//******************************************************************
	//==================================================================
	
	public Jeopardy (String title){
		super(title);
		
		setLayout(new BorderLayout());
		//board size should be full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//The Menu
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		//the MenuItem
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//adding the menu bar to the fame
		setJMenuBar(menuBar);
		
		/*
		 * Within this main frame, split the board into 3 distinct areas
		 * 1- Categories at the TOP
		 * 2- Question buttons in the CENTER
		 * 3- Player information at the SIDE
		 */
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new GridBagLayout ());
		GridBagConstraints gbc = new GridBagConstraints();

		//Set the CATEGORIES
		JPanel cat = new JPanel();
		cat = categoryFrame();
		
		
		//=========================================
		//ESTHETICS of CATEGORY
		//=========================================
		cat.setBackground(Color.BLUE);
		
		cat.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//Specify the row and column at the upper left of the component.
		gbc.gridx = gbc.gridy = 0;
		
		// These constraints specify the number of cells the component uses
        gbc.gridwidth = gbc.gridheight = 1;
        
        // Used when the component's display area is larger than the component's requested size to determine whether and how to resize the component.
        gbc.fill = GridBagConstraints.BOTH;
        
        //Used when the component is smaller than its display area to determine where (within the area) to place the component.
        gbc.anchor = GridBagConstraints.NORTHWEST;
       
        //weights are used to determine how to distribute space among columns (weightx) and among rows (weighty)
        gbc.weightx = 90;
        gbc.weighty = 10;
        
        
		mainContainer.add(cat, gbc);
		
		
		
		//=========================================
		//ESTHETICS of QUESTION
		//=========================================

		JPanel que = new JPanel();
		que = questionFrame();
		
		//Specify the row (x) and column (y) at the upper left of the component.
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		// These constraints specify the number of cells the component uses
        gbc.gridwidth = gbc.gridheight = 1;
        
        // Used when the component's display area is larger than the component's requested size to determine whether and how to resize the component.
        gbc.fill = GridBagConstraints.BOTH;
        
        //Used when the component is smaller than its display area to determine where (within the area) to place the component.
        gbc.anchor = GridBagConstraints.SOUTHWEST;
       
        //weights are used to determine how to distribute space among columns (weightx) and among rows (weighty)
        gbc.weightx = 90;
        gbc.weighty = 90;
        
		mainContainer.add(que, gbc);
		
		

		//=========================================
		//ESTHETICS of PLAYER STATS
		//=========================================
		
		JPanel player = new JPanel();
		player = playerFrame();
		

		
		//Specify the row (x) and column (y) at the upper left of the component.
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		// These constraints specify the number of cells the component uses
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        
        // Used when the component's display area is larger than the component's requested size to determine whether and how to resize the component.
        gbc.fill = GridBagConstraints.BOTH;
        
        //Used when the component is smaller than its display area to determine where (within the area) to place the component.
        gbc.anchor = GridBagConstraints.WEST;
       
        //weights are used to determine how to distribute space among columns (weightx) and among rows (weighty)
        gbc.weightx = 10;
        //gbc.weighty = 100;
        
        //add to the board
		mainContainer.add(player, gbc);

	
		
		//=========================================
		//Main Jeopardy Frame
		//=========================================
		
		//add the main JPanel to the frame
		add(mainContainer);
				
		setVisible( true );
		
	}
	
	
	
	
	
	//==================================================================
	//******************************************************************
	// 			BOARD INFORMATION
	//******************************************************************
	//==================================================================
	
	
	//=========================================
	//Categories
	//=========================================
	
	public JPanel categoryFrame(){
		
		//Database
		int m = database.getNumCategories();
		
		//the categories
		JPanel cat = new JPanel();
		cat.setLayout(new GridLayout (1, m));
		for (int i = 0; i < m; i ++){
			JLabel categoryDisplayed = new JLabel(database.getCategory(i), JLabel.CENTER);
			categoryDisplayed.setFont(defaultFont);
			categoryDisplayed.setForeground(Color.WHITE);
			cat.add(categoryDisplayed);
		}
		
		return cat;
	}
	
		
	//=========================================
	//Questions
	//=========================================
	public JPanel questionFrame() {
	
		//Database
		int m = database.getNumCategories();
		int n = database.getNumQuestions();
		
		//variables for grid layout
		int amount = 200;
		int count=0;
		
		//the question buttons
		JPanel que = new JPanel();
		
		//Grid (#rows, #columns)
		que.setLayout(new GridLayout (n, m));
		
		for (int j = 0; j < (n); j ++){
			for (int k = 0; k < (m); k ++){
				buttonArray[count] = new JeopardyButton(this, k, j, amount);
				
				//Button Look
				buttonArray[count].setBackground(Color.BLUE);
				buttonArray[count].setForeground(Color.WHITE);
				buttonArray[count].setOpaque(true);
				buttonArray[count].setBorderPainted(true);

				
				que.add(buttonArray[count]);
				count++;
			}
			amount = amount + 200;
		}
		
		return que;
	}
	
	
	//=========================================
	//Player Statistics
	//=========================================
	
	public JPanel playerFrame(){
		JPanel player = new JPanel();
		player.setLayout(new GridLayout (6, 2));
				
		//create the players
		for (int i=0; i<pArray.length; i++){
			//create player
			pArray[i] = new Player("Player " + i);
		}
		
		//create the labels for players and their scores 
		//group as (name-score, name-score, ...)
		for (int i=0; i<labelArrayName.length; i++){
			//create name label
			labelArrayName[i] = new JLabel(pArray[i].getName(), JLabel.CENTER);
			labelArrayName[i].setFont(defaultFont);
			
			//create the score label
			labelArrayScore[i] = new JLabel(pArray[i].getScoreString(), JLabel.CENTER);
			labelArrayScore[i].setFont(defaultFont);
			
			//buttons for score keeping
			buttonAdd[i] = new JButton("Add");
			buttonAdd[i].setFont(defaultFont);
			buttonAdd[i].setBackground(Color.WHITE);
			buttonAdd[i].setForeground(Color.BLACK);
			buttonAdd[i].addActionListener(this);
			
			buttonSubtract[i] = new JButton("Subtract");
			buttonSubtract[i].setFont(defaultFont);
			buttonSubtract[i].setBackground(Color.WHITE);
			buttonSubtract[i].setForeground(Color.BLACK);
			buttonSubtract[i].addActionListener(this);
			
			//add to the panel
			player.add(labelArrayName[i]);
			player.add(labelArrayScore[i]);
			player.add(buttonAdd[i]);
			player.add(buttonSubtract[i]);
		}
		
		return player;
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
		    String newQuestionFile = load.fileChooser();
		    
		    /*
		     * If a file is chosen, then proceed
		     * otherwise do nothing
		     */
		    
		    if (newQuestionFile != null){
			    System.out.println(newQuestionFile);
	
				String path = "src/QuestionFiles/".concat(newQuestionFile);
				System.out.println(path);
				
				//hide and close the previous game
				setVisible(false);
				dispose();
				
				//create and open a new game base on a new database
				database = Database.readQuestions(path);
				f = new Jeopardy(path);
				questionSelected = false;
		    }
		}
		
		
		/*
		 * Questions selections
		 */
		for (int p = 0; p < (database.getNumCategories() * database.getNumQuestions()); p++){
			
			if (e.getSource() == buttonArray[p]){
				
				//get the score value of the button
				currentPointValue = Integer.parseInt(buttonArray[p].getText());
				
				//remove text label
				buttonArray[p].setText("-");
				
				//disclose that a question has indeed been selected
				questionSelected = true;
				//indicate WHICH question was selected, and keep information of latest selection
				catGet = buttonArray[p].getCategory();
				queGet = buttonArray[p].getQuestion();
				
				//revel the answer, to which the player  must guess the question
				String answerDisplay = database.getQuestion(catGet, queGet).getResponse();
				String questionDisplay = database.getQuestion(catGet, queGet).getQuestion();

				
				//create a new full-screen frame showing the questions
				buttonArray[p].selectedQuestionDisplay(answerDisplay, questionDisplay);
			}
		}
			
		/*
		 * Score Keeping buttons
		 */
		for (int i = 0; i < buttonAdd.length ; i++){
			
			if (e.getSource() == buttonAdd[i]){
				pArray[i].addPoints(currentPointValue);
				
				//change the color according to the score
				if (pArray[i].getScore()<0){
					labelArrayScore[i].setForeground(Color.RED);
				}else{
					labelArrayScore[i].setForeground(Color.BLACK);
				}
				
				//update the score on the board
				labelArrayScore[i].setText(pArray[i].getScoreString());
			}
			
			else if (e.getSource() == buttonSubtract[i]){
				pArray[i].subtractPoints(currentPointValue);
				
				//change the color according to the score
				if (pArray[i].getScore()<0){
					labelArrayScore[i].setForeground(Color.RED);
				}else{
					labelArrayScore[i].setForeground(Color.BLACK);
				}
				
				//update the score on the board
				labelArrayScore[i].setText(pArray[i].getScoreString());
			}
			
		}
		
	}
	
	
	
	public static void main (String args[]){
		
		
		database = Database.readQuestions("src/QuestionFiles/10_Finance.txt");
		f = new Jeopardy("Jeopardy - Conrad Edition");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
}
