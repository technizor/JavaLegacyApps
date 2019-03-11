
public class GravityBoard implements Comparable<GravityBoard>
{
	private static final int UP = 90;
	private static final int DOWN = 270;
	private static final int LEFT = 180;
	private static final int RIGHT = 0;
	private int gridSize;
	private int[][] grid;
	private int turn;
	
	public GravityBoard(int size)
	{
		gridSize = size*2;
		grid = new int[gridSize][gridSize];
		grid[size][size] = 1;
		grid[size-1][size-1] = 1;
		grid[size-1][size] = 2;
		grid[size][size-1] = 2;
	}
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder(gridSize*(gridSize+1));
		for(int[] row : grid)
		{
			for(int p : row)
				strBuild.append(p);
			strBuild.append("\n");
		}
		return strBuild.toString();
	}
	
	public int compareTo(GravityBoard other)
	{
		return 0;
	}
	public static void main(final String[] args)
	{
		GravityBoard board = new GravityBoard(5);
		System.out.println(board);
	}
	public void move(int row, int direction)
	{
		if(direction == UP)
		{
			
		} else if(direction == DOWN)
		{
			
		} else if(direction == LEFT)
		{
			
		} else if(direction == RIGHT)
		{
			
		}
	}
}
