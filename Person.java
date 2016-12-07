
public class Person {

    	private int scoreGryf, scoreSlyth, scoreHuffle, scoreRaven;
    	private String name;
    	
    	Person(String n, int score1, int score2, int score3, int score4) {
    		name = n;
    		scoreGryf = score2;
    		scoreSlyth = score3;
    		scoreHuffle = score1;
    		scoreRaven = score4;
    	}
    	
    	public int getScoreGryf() {
    		return scoreGryf;
    	}
    	
    	public int getScoreSlyth() {
    		return scoreSlyth;
    	}
    	
    	public int getScoreHuffle() {
    		return scoreHuffle;
    	}
    	
    	public int getScoreRaven() {
    		return scoreRaven;
    	}
    	
    	public String getName() {
    		return name;
    	}

}
