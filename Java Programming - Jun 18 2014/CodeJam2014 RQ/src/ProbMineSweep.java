import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProbMineSweep
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"data32.txt")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"out32.txt")));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			String[] r_c_m = reader.readLine().split(" ");
			int r = Integer.parseInt(r_c_m[0]);
			int c = Integer.parseInt(r_c_m[1]);
			int m = Integer.parseInt(r_c_m[2]);
			String output = calculate(r, c, m);
			writer.printf("Case #%d:%n%s", testCase, output);
		}
		writer.close();
		reader.close();
	}

	private static String calculate(int r, int c, int m)
	{
		int a = r * c;
		int n = a - m;
		if (m == 0) {
			char[][] grid = new char[r][c];
			for (int x = 0; x < r; x++)
				for (int y = 0; y < c; y++)
					grid[x][y] = '.';
			grid[0][0] = 'c';
			return gridPrint(grid);
		}
		if(n == 1)
		{
			char[][] grid = new char[r][c];
			for (int x = 0; x < r; x++)
				for (int y = 0; y < c; y++)
					grid[x][y] = '*';
			grid[0][0] = 'c';
			return gridPrint(grid);
		}

		char[][] grid = new char[r][c];
		for (int x = 0; x < r; x++)
			for (int y = 0; y < c; y++)
				grid[x][y] = '*';
		if (c == 1) {
			if (n > 0) {
				for (int x = 0; x < n; x++)
					grid[x][0] = '.';
				grid[0][0] = 'c';
				return gridPrint(grid);
			} else
				return "Impossible\n";
		} else if (r == 1) {
			if (n > 0) {
				for (int y = 0; y < n; y++)
					grid[0][y] = '.';
				grid[0][0] = 'c';
				return gridPrint(grid);
			} else
				return "Impossible\n";
		} else {
			for (int width = 2; width <= c; width++) {
				int height = n / width;
				int remainder = n % width;
				if (height > 1 && height <= r) {
					if (remainder == 0 || remainder != 1) {
						if(width < c && remainder < height)
						{
							for (int x = 0; x < height; x++)
								for (int y = 0; y < width; y++)
									grid[x][y] = '.';
							for (int x = 0; x < remainder; x++)
								grid[x][width] = '.';
							grid[0][0] = 'c';
							return gridPrint(grid);
						} else if(height < r && remainder < width)
						{
							for (int x = 0; x < height; x++)
								for (int y = 0; y < width; y++)
									grid[x][y] = '.';
							for (int y = 0; y < remainder; y++)
								grid[height][y] = '.';
							grid[0][0] = 'c';
							return gridPrint(grid);
						}
					} else if (height > 2 && width > 2) {
						if(width < c)
						{
							for (int x = 0; x < height; x++)
								for (int y = 0; y < width; y++)
									grid[x][y] = '.';
							grid[height - 1][width - 1] = '*';
							for (int x = 0; x < 2; x++)
								grid[x][width] = '.';
							grid[0][0] = 'c';
							return gridPrint(grid);
						} else if(height < r)
						{
							for (int x = 0; x < height; x++)
								for (int y = 0; y < width; y++)
									grid[x][y] = '.';
							grid[height - 1][width - 1] = '*';
							for (int y = 0; y < 2; y++)
								grid[height][y] = '.';
							grid[0][0] = 'c';
							return gridPrint(grid);
						}
					}
				}
			}
			return "Impossible\n";
		}
	}

	private static String gridPrint(char[][] grid)
	{
		int r = grid.length;
		int c = grid[0].length;
		StringBuilder strb = new StringBuilder();
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				strb.append(grid[x][y]);
			}
			strb.append("\n");
		}
		return strb.toString();
	}
}
