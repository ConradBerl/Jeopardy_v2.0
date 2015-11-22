package Database;
// Author: Conrad Berlinguette
// Student Number: 7005055
// Course: ITI 1121-B
// Assignment: 2

public class Question {

	String question, response;
	
	Question(String response, String question){
		
		//if score value is in text, remove it
		if (response.contains(":")){
			//using beginIndex
			int index = response.indexOf(":");
			response = response.substring(index+1);
		}
		this.response = response;
		
	

		this.question = question;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getResponse(){
		return response;
	}
}
