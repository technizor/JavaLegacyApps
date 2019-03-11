package ilg.com;

public class MazeCell
{
	private boolean topWall;
	private boolean botWall;
	private boolean leftWall;
	private boolean rightWall;
	
	public MazeCell()
	{
		topWall = botWall = leftWall = rightWall = true;
	}
	public MazeCell(boolean top, boolean bot, boolean left, boolean right)
	{
		topWall = top;
		botWall = bot;
		leftWall = left;
		rightWall = right;
	}
	public boolean hasTopWall()
	{
		return topWall;
	}
	public boolean hasBotWall()
	{
		return botWall;
	}
	public boolean hasLeftWall()
	{
		return leftWall;
	}
	public boolean hasRightWall()
	{
		return rightWall;
	}
	public void setTop(boolean wall)
	{
		topWall = wall;
	}
	public void setBot(boolean wall)
	{
		botWall = wall;
	}
	public void setLeft(boolean wall)
	{
		leftWall = wall;
	}
	public void setRight(boolean wall)
	{
		rightWall = wall;
	}
	public boolean isConnected()
	{
		return topWall && botWall && leftWall && rightWall;
	}
}
