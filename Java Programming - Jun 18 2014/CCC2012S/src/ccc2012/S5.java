package ccc2012;
//summer18.316 cccying
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementation for The 5th CCC2012 Senior division. Achieves a linear computation time through iteration and adding the
 * number of combinations for the cage above and to the left.
 */
public class S5
{
	private static final boolean showSolution = true;
	private int cageWidth;
	private int cageHeight;
	private long[][] cages;
	public static void main(final String[] args) throws IOException
	{
		new S5();
	}
	public S5() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("s5.in")));
		for(String size = reader.readLine(); size != null; size = reader.readLine()) {
			//	Loop until there are no more test cases.
			cageWidth = Integer.parseInt(size.split(" ")[0]);
			cageHeight = Integer.parseInt(size.split(" ")[1]);
			cages = new long[cageWidth][cageHeight];	//	Create the cage array.
			int z = Integer.parseInt(reader.readLine());
			for(int i = 0; i < z; i++) {
				//	Mark the cat cages.
				String[] catPos = reader.readLine().split(" ");
				int catX = Integer.parseInt(catPos[0])-1, catY = Integer.parseInt(catPos[1])-1;
				cages[catX][catY] = -1;
			}
			long paths =  iterate();
			if(paths == 0) {
				System.out.println("Impossible!");
			} else {
				System.out.println("There are " + paths + "possible paths.");
			}
			if(showSolution) {
				//	If you want to display the solution. Most people will understand it if they see the output of this.
				for(long lArr[] : cages) {
					String str = "";
					for(long l : lArr) {
						str += l + " ~ ";
					}
					System.out.println(str.substring(0, str.length()-2));
				}
				System.out.println("________________________________________\n");
			}
		}
	}
	
	@SuppressWarnings("unused")	@ Deprecated
	private long recurse(int x, int y)
	{
		if(cages[x][y] == 0) {
			if(x >= cageWidth-1 && y >= cageHeight-1) {
				cages[x][y] = 1;
				return 1;
			}
			if(x >= cageWidth-1) {
				if(y >= cageHeight-1) {
					cages[x][y] = -1;
					return -1;
				} else if(cages[x][y+1] == -1) {
					cages[x][y] = -1;
					return -1;
				}
			} else if(cages[x+1][y] == -1) {
				if(y >= cageHeight-1) {
					cages[x][y] = -1;
					return -1;
				} else if(cages[x][y+1] == -1) {
					cages[x][y] = -1;
					return -1;
				}
			}
			if(x < cageWidth-1) {
				if(recurse(x+1, y) > 0) {
					cages[x][y] += cages[x+1][y];
				}
			}
			if(y < cageWidth-1) {
				if(recurse(x, y+1) > 0) {
					cages[x][y] += cages[x][y+1];
				}
			}
		}
		return cages[x][y];
	}
	private long iterate()
	{
		cages[0][0] = 1;	//	A single way to stay in the same spot!
		for(int i = 0; i < cageWidth; i++) {
			for(int j = 0; j < cageHeight; j++) {
				if(cages[i][j] == 0) {
					//	So you don't re-evaluate the top left corner!
					long val = 0;
					if(i != 0) {
						//	Make sure you are not going to get an ArrayIndexOutOfBoundsException
						val += cages[i-1][j];	//	Add number of pathing combinations of the cage above
					}
					if(j != 0) {
						//	Make sure you are not going to get an ArrayIndexOutOfBoundsException
						val += cages[i][j-1];	//	Add number of pathing combinations of the cage to the left
					}
					cages[i][j] = val;
					//	Number of pathing combinations for a cage is the sum of the pathing combinations of all cages that can access it
				}
				if(cages[i][j] == -1) {
					//	If cat cage, zero ways to get to it
					cages[i][j] = 0;
				}
			}
		}
		return cages[cageWidth-1][cageHeight-1];	//	Returns the final value in the bottom right corner
	}
}
