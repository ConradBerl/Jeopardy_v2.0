package Database;

public class Player {

	private String name;
	private int score;
	
	public Player(String name){
		this.name = name;
		this.score = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getScoreString(){
		return String.valueOf(score);
	}
	
	public void addPoints(int points){
		score = score + points;
		return;
	}
	
	public void subtractPoints(int points){
		score = score - points;
		return;
	}
	
	
}
