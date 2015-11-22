package Database;
// Author: Conrad Berlinguette
// Student Number: 7005055
// Course: ITI 1121-B
// Assignment: 2

import java.io.*;
import java.util.Scanner;

public class Database {

	//number of categories
	private int m;
	//number of questions per category
	private int n;
	
	//number of questions in total (m * n)
	
	//The game categories
	private static String [] category;
	
	//the game questions
	private static Question [][] questionDatabase;
	

	private Database(int m, int n){
		this.m = m;
		this.n = n;
	}
	
	
	/*File format:
	 * Number of categories (m)
	 * Number of questions per categories (n)
	 * Categories (one per line)
	 * Questions and answers (m×n pairs of questions and answers).
	 */
	
	public static Database readQuestions(String name) {

		//Use java.util.Scanner
        Scanner file;
		
        try {
			file = new Scanner(new File (name));
		} catch (FileNotFoundException e) {
			file = null;
			e.printStackTrace();
		}
       
        //Number of categories
        int m = file.nextInt();    
        file.nextLine();
        
        //Number of questions per categories (n)
        int n = file.nextInt();    
        file.nextLine();
        
        //store categories
        setCategory(new String [m]);
        for (int i=0; i < m; i++){
        	getCategory()[i] = file.nextLine();
        }
        
        //array of questions (both response and questions)
        questionDatabase = new Question [m][n];
        
        /*
         * populate the array of questions
         * two lines equals a 'question' type object
         */
        for (int i=0; i < m; i++){
        	 for (int j = 0; j < (n); j++){
             	questionDatabase [i][j] = new Question (file.nextLine(), file.nextLine());
             } 
        }
              
        
        file.close();
        
        return new Database(m, n);
	}
	
	
	
	//returns the category found at position index of this Database.
	public String getCategory(int index){
		return category [index];
	}
	
	
	//sets the category at position index to category.
	public void setCategory(int index, String category){
		Database.category [index] = category;
	}
	
	
	//returns the question at position index for the given category.
	public Question getQuestion(int category, int index){
		return questionDatabase [category] [index];
	}
	
	
	//sets the question for a given category and index.
	public void setQuestion(int category, int index, Question question){
		questionDatabase [category][index] = question;
	}
	
	
	//returns the number of categories for this game.
	public int getNumCategories(){
		return m;
	}
	
	
	//returns the number of questions per category.
	public int getNumQuestions(){
		return n;
	}


	public static String [] getCategory() {
		return category;
	}


	public static void setCategory(String [] category) {
		Database.category = category;
	}
	
	
}
