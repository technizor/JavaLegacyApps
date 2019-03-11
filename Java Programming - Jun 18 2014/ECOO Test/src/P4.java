import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


public class P4
{
	private final static boolean DEBUG = false;
	public Pos[] positions;
	public int[][] grid;
	public HashSet<Pos> posList;
	public int integer;
	public static void main(final String[] args) throws IOException
	{
		new P4();
	}
	public P4() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("p4.in")));
		for(int i = 0; i < 5; i++) {
			String dimensions = reader.readLine();
			Scanner scan1 = new Scanner(dimensions);
			int height = scan1.nextInt();
			int width = scan1.nextInt();
			positions = new Pos[4];
			int maxX = 0;
			int maxY = 0;
			for(int j = 0; j < 4; j++) {
				Scanner scan2 = new Scanner(reader.readLine());
				positions[j] = new Pos(scan2.nextInt()-1, scan2.nextInt()-1);
				maxX = Math.max(maxX, positions[j].x);
				maxY = Math.max(positions[j].y, maxY);
			}
			height = Math.min(maxX+2, height);
			width = Math.min(maxY+2, width);
			int a = pathFind(0, height, width);
			int b = pathFind(1, height, width);
			if(a > 0) {
				if(b > 0) {
					System.out.println(Math.min(a, b));
				} else {
					System.out.println(a);
				}
			} else {
				if(b > 0) {
					System.out.println(b);
				} else {
					System.out.println("-1 \tImpossibru!");
				}
			}
		}
	}

	public int pathFind(int i, int height, int width)
	{
		integer = i;
		grid = new int[height][width];
		for(int p = 0; p < height; p++) {
			for(int q = 0; q < width; q++) {
				grid[p][q] = Integer.MAX_VALUE;
			}
		}
		if(i == 0) {
			grid[positions[0].x][positions[0].y] = 0;
			grid[positions[2].x][positions[2].y] = -1;
			grid[positions[3].x][positions[3].y] = -1;
		} else {
			grid[positions[0].x][positions[0].y] = -1;
			grid[positions[1].x][positions[1].y] = -1;
			grid[positions[2].x][positions[2].y] = 0;
		}
		posList = new HashSet<Pos>();
		path(positions[i*2].x, positions[i*2].y, new Pos(positions[i*2].x, positions[i*2].y));
		if(DEBUG) printGrid();
		int total = grid[positions[i*2+1].x][positions[i*2+1].y] + 1;
		backPath(i, positions[i*2+1].x, positions[i*2+1].y, grid[positions[i*2+1].x][positions[i*2+1].y]);
		for(int p = 0; p< grid.length; p++) {
			for(int q = 0; q < grid[0].length; q++) {
				if(grid[p][q] != -1) {
					grid[p][q] = Integer.MAX_VALUE;
				}
			}
		}
		if(i == 1) {
			grid[positions[0].x][positions[0].y] = 0;
			grid[positions[2].x][positions[2].y] = -1;
			grid[positions[3].x][positions[3].y] = -1;
			grid[positions[1].x][positions[1].y] = Integer.MAX_VALUE;
		} else {
			grid[positions[0].x][positions[0].y] = -1;
			grid[positions[1].x][positions[1].y] = -1;
			grid[positions[2].x][positions[2].y] = 0;
			grid[positions[3].x][positions[3].y] = Integer.MAX_VALUE;
		}
		if(DEBUG) printGrid();
		posList = new HashSet<Pos>();
		int x = positions[((i+1)%2)*2+1].x;
		int y = positions[((i+1)%2)*2+1].y;
		path(positions[((i+1)%2)*2].x, positions[((i+1)%2)*2].y, new Pos(positions[((i+1)%2)*2].x, positions[((i+1)%2)*2].y));
		if(DEBUG) printGrid();
		total += 1 + grid[x][y];
		if(grid[positions[(i*2+2)%4].x][positions[(i*2+2)%4].y] == Integer.MAX_VALUE) {
			return -1;
		}
		return total;
	}
	private void printGrid()
	{
		System.out.println("---");
		for(int[] ints : grid) {
			for(int n : ints) {
				System.out.print(n + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("---");
	}
	public void backPath(int i, int x, int y, int num) {
		if(num == 0) {
			return;
		}
		if(x > 0) {
			if(grid[x-1][y] == num-1) {
				grid[x-1][y] = -1;
				backPath(i, x-1, y, num-1);
				return;
			}
		}
		if(x < grid.length-1) {
			if(grid[x+1][y] == Integer.MAX_VALUE) {
				grid[x+1][y] = -1;
				backPath(i, x+1, y, num-1);
				return;
			}
		}
		if(y > 0) {
			if(grid[x][y-1] == num-1) {
				grid[x][y-1] = -1;
				backPath(i, x, y-1, num-1);
				return;
			}
		}
		if(y < grid[0].length-1) {
			if(grid[x][y+1] == num-1) {
				grid[x][y+1] = -1;
				backPath(i, x, y+1, num-1);
				return;
			}
		}
	}
	public void path(int x, int y, Pos index) {
		if(x == positions[integer*2+1].x && y == positions[integer*2+1].y) {
			return;
		}
		if(grid[x][y] != -1) {
			if(x > 0) {
				if(grid[x-1][y] == Integer.MAX_VALUE) {
					int temp = Math.min(grid[x-1][y], grid[x][y] + 1);
					if(temp != grid[x-1][y]) {
						if(!posList.contains(new Pos(x-1, y)))
							posList.add(new Pos(x-1, y));
						grid[x-1][y] = temp;
					}
				} else if(grid[x-1][y] != -1) {
					grid[x-1][y] = Math.min(grid[x-1][y], grid[x][y] + 1);
				}
			}
			if(x < grid.length-1) {
				if(grid[x+1][y] == Integer.MAX_VALUE) {
					int temp = Math.min(grid[x+1][y], grid[x][y] + 1);
					if(temp != grid[x+1][y]) {
						if(!posList.contains(new Pos(x+1, y)))
							posList.add(new Pos(x+1, y));
						grid[x+1][y] = temp;
					}
				} else if(grid[x+1][y] != -1) {
					grid[x+1][y] = Math.min(grid[x+1][y], grid[x][y] + 1);
				}
			}
			if(y > 0) {
				if(grid[x][y-1] == Integer.MAX_VALUE) {
					int temp = Math.min(grid[x][y-1], grid[x][y] + 1);
					if(temp != grid[x][y-1]) {
						if(!posList.contains(new Pos(x, y-1)))
							posList.add(new Pos(x, y-1));
						grid[x][y-1] = temp;
					}
				} else if(grid[x][y-1] != -1) {
					grid[x][y-1] = Math.min(grid[x][y-1], grid[x][y] + 1);
				}
			}
			if(y < grid[0].length-1) {
				if(grid[x][y+1] == Integer.MAX_VALUE) {
					int temp = Math.min(grid[x][y+1], grid[x][y] + 1);
					if(temp != grid[x][y+1]) {
						if(!posList.contains(new Pos(x, y+1)))
							posList.add(new Pos(x, y+1));
						grid[x][y+1] = temp;
					}
				} else if(grid[x][y+1] != -1) {
					grid[x][y+1] = Math.min(grid[x][y+1], grid[x][y] + 1);
				}
			}
		} else if(DEBUG){
			System.out.println("Invalid!");
		}
		if(posList.size() != 0) {
			try {
				Pos mIndex = null;
				posList.remove(index);
				for(Pos pos : posList) {
					if(mIndex == null) {
						mIndex = pos;
					} else {
						mIndex = grid[pos.x][pos.y] > grid[mIndex.x][mIndex.y] ? mIndex : pos;
					}
				}
				if(posList.size() != 0) {
					path(mIndex.x, mIndex.y, mIndex);
				}
			} catch(StackOverflowError soe) {
				System.out.println(posList.size());
				System.exit(-1);
			}
		}
		return;
	}
}
class Pos {
	int x;
	int y;
	Pos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}