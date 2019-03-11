import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class S4
{
	public static void main(final String[] args) throws IOException
	{
		boolean debug = true;
		BufferedReader reader = new BufferedReader(debug ? new FileReader(
				new File("data4.txt")) : new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int t = Integer.parseInt(reader.readLine());
		// {x1,x2} OR {y1,y2}
		int[] x1 = new int[n];
		int[] x2 = new int[n];
		int[] y1 = new int[n];
		int[] y2 = new int[n];
		int[] tint = new int[n];
		
		ArrayList<Integer> xP = new ArrayList<Integer>(n * 2);
		ArrayList<Integer> yP = new ArrayList<Integer>(n * 2);

		for (int i = 0; i < n; i++) {
			String[] line = reader.readLine().split(" ");
			int x1c = Integer.parseInt(line[0]);
			int y1c = Integer.parseInt(line[1]);
			int x2c = Integer.parseInt(line[2]);
			int y2c = Integer.parseInt(line[3]);
			int tc = Integer.parseInt(line[4]);

			x1[i] = x1c;
			x2[i] = x2c;
			y1[i] = y1c;
			y2[i] = y2c;
			tint[i] = tc;
			
			int xi1 = Collections.binarySearch(xP, x1c);
			if (xi1 < 0)	xP.add(-xi1 - 1, x1c);
			int xi2 = Collections.binarySearch(xP, x2c);
			if (xi2 < 0)	xP.add(-xi2 - 1, x2c);
			int yi1 = Collections.binarySearch(yP, y1c);
			if (yi1 < 0)	yP.add(-yi1 - 1, y1c);
			int yi2 = Collections.binarySearch(yP, y2c);
			if (yi2 < 0)	yP.add(-yi2 - 1, y2c);
		}
		reader.close();

		int w = xP.size();
		int h = yP.size();
		int[][] grid = new int[w][h];

		for (int x = 0; x < w; x++) {
			int xCur = xP.get(x);
			for (int y = 0; y < h; y++) {
				int yCur = yP.get(y);
				for (int z = 0; z < n; z++) {
					if (xCur >= x1[z] && xCur < x2[z]
							&& yCur >= y1[z] && yCur < y2[z])
						grid[x][y] += tint[z];
				}
			}
		}

		long area = 0;
		for (int x = 0; x < w - 1; x++) {
			for (int y = 0; y < h - 1; y++) {
				if (grid[x][y] >= t) {
					area += (long) (xP.get(x + 1) - xP.get(x))
							* (long) (yP.get(y + 1) - yP.get(y));
				}
			}
		}
		System.out.println(area);
	}
}