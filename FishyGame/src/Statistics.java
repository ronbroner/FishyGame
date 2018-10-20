
public class Statistics {
	int score; 
	int highScore;
	protected boolean won = false;
	
	public int getScore()
	{
		return score;
	}
	
	public int getHighScore()
	{
		return highScore;
	}
	
	public void setWin()  // will tell us when game is won and (stop growing given a given size)
	{
		won = true;
	}
	
	public void addPoints(int mySize, int otherFishSize)
	{
		int ratio = mySize/otherFishSize;
		if (ratio == 0 )
		{
			score+=30;
		}
		else if (ratio == 1)
		{
			score+=20;
		}
		else if (ratio <=5)
		{
			score+=10;
		}
		else
			score+=1;
		
		
		if (score>=highScore)  // update high score 
		{
			highScore = score;
		}
	}
	
	public void reset()
	{
		score = 0;
	}
}
