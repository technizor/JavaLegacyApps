package dwiteC5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q5
{
	
	public static void main(final String[] args)
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("c5/DATA5.TXT"));
			writer = new PrintWriter(new FileWriter("c5/OUT5.TXT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(reader != null && writer != null) {
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				String temp_1 = scan.nextLine();
				String[] temp = temp_1.split(" ");
				int l = Integer.parseInt(temp[0]);
				int w = Integer.parseInt(temp[1]);
				int[][] world = new int[l][w];
				for(int j = 0; j < l; j++) {
					String temp2 = scan.nextLine();
					String[] temp1 = temp2.split(" ");
					for(int k = 0; k < w; k++) {
						world[j][k] = Integer.parseInt(temp1[k]);
					}
				}
				writer.println(getVolume(world));
				writer.flush();
			}
		}
	}
	public static int getVolume(int[][] world) {
		int volume = 0;
		for(int i = 1; i < world.length-1; i++) {
			for(int j = 1; j < world[0].length-1; j++) {
				{
					int heightHere = world[i][j];
					int minx = getSurroundingMinHeight(i, j, world);
					if(heightHere < minx) {
						volume += minx - heightHere;
						world[i][j] = minx;
					} else {
						int miny = Math.min(Math.min(getSurroundingMinHeight(i-1, j, world), getSurroundingMinHeight(i+1, j, world)),
								Math.min(getSurroundingMinHeight(i, j-1, world), getSurroundingMinHeight(i, j+1, world)));
						if(miny	> heightHere) {
							volume += miny*5 - heightHere - world[i-1][j] - world[i+1][j] - world[i][j-1] - world[i][j+1];
							world[i][j] = world[i+1][j] = world[i][j+1] = world[i][j-1] = world[i-1][j] = miny;
						}
					}
				}
			}
		}
		return volume;
	}
	public static int getSurroundingMinHeight(int x, int y, int[][] world)
	{
		if(x == 0 || y == 0 || x == world.length-1 || y == world[0].length-1) return 0;
		int[] surrounding = {world[x-1][y], world[x+1][y], world[x][y-1], world[x][y+1]};
		int[] mins = {Math.min(surrounding[0], surrounding[1]), Math.min(surrounding[2], surrounding[3])};
		int minx = Math.min(mins[0], mins[1]);
		return minx;
	}
}
