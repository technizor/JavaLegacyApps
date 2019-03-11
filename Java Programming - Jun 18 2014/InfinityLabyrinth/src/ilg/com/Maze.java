package ilg.com;

public class Maze
{
	private MazeCell[][] grid;
	private int width;
	private int height;
	public Maze(int x, int y, MazeGen generator)
	{
		width = x;
		height = y;
		grid = new MazeCell[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				grid[i][j] = new MazeCell();
			}
		}
		generator.generateMaze(this);
	}
	public int width()
	{
		return width;
	}
	public int height()
	{
		return height;
	}
	public MazeCell getCell(int x, int y)
	{
		if(x < width && y < height) {
			return grid[x][y];
		}
		return null;
	}
	public void setCell(int x, int y, MazeCell cell)
	{
		if(x < width && y < height) {
			return;
		}
		grid[x][y] = cell;
	}
}
