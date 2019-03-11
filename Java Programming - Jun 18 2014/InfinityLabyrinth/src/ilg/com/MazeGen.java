package ilg.com;

public abstract class MazeGen
{
	private String genName;
	
	public MazeGen(String name)
	{
		genName = name;
	}
	public abstract void generateMaze(Maze maze);
	public String getName()
	{
		return genName;
	}
}
