import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"ccski.in")));
		String[] mnStr = reader.readLine().split(" ");
		int m = Integer.parseInt(mnStr[0]);
		int n = Integer.parseInt(mnStr[1]);
		int[][] elevationMap = new int[m][n];
		for (int row = 0; row < m; row++) {
			String[] elevLine = reader.readLine().split(" ");
			for (int col = 0; col < n; col++)
				elevationMap[row][col] = Integer.parseInt(elevLine[col]);
		}
		ArrayList<Point> waypoints = new ArrayList<Point>();
		for (int row = 0; row < m; row++) {
			StringBuilder line = new StringBuilder(reader.readLine());
			int index = line.indexOf("1");
			while (index != -1) {
				waypoints.add(new Point(row, index / 2));
				line.setCharAt(index, '0');
				index = line.indexOf("1");
			}
		}
		reader.close();
		int difficulty = calculateDifficulty(elevationMap, waypoints);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"ccski.out")));
		writer.println(difficulty);
		writer.close();

	}

	public static int calculateDifficulty(final int[][] elevMap,
			final ArrayList<Point> points)
	{
		Point start = points.get(0);
		int difficulty = 0;
		while (difficulty < 1000000000) {
			boolean[][] accessMap = new boolean[elevMap.length][elevMap[0].length];
			int nextDiff = iterate(elevMap, accessMap, difficulty, start.x,
					start.y);
			boolean success = true;
			for (Point p : points) {
				if (!accessMap[p.x][p.y])
					success = false;
			}
			if (success)
				return difficulty;
			difficulty = nextDiff;
		}
		return 1000000000;
	}

	public static int iterate(final int[][] elevMap, boolean[][] accessMap,
			int difficulty, int r, int c)
	{
		if (r < 0 || r == elevMap.length || c < 0 || c == elevMap[r].length
				|| accessMap[r][c])
			return Integer.MAX_VALUE;
		accessMap[r][c] = true;
		int current = elevMap[r][c];
		int smallestChange = Integer.MAX_VALUE;
		if (r > 0) {
			int a = current - elevMap[r - 1][c];
			if (a < 0)
				a *= -1;
			if (difficulty >= a) {
				int b = iterate(elevMap, accessMap, difficulty, r - 1, c);
				if (b < smallestChange)
					smallestChange = b;
			} else if (a < smallestChange)
				smallestChange = a;
		}
		if (c > 0) {
			int a = current - elevMap[r][c - 1];
			if (a < 0)
				a *= -1;
			if (difficulty >= a) {
				int b = iterate(elevMap, accessMap, difficulty, r, c - 1);
				if (b < smallestChange)
					smallestChange = b;
			} else if (a < smallestChange)
				smallestChange = a;
		}
		if (r < elevMap.length - 1) {
			int a = current - elevMap[r + 1][c];
			if (a < 0)
				a *= -1;
			if (difficulty >= a) {
				int b = iterate(elevMap, accessMap, difficulty, r + 1, c);
				if (b < smallestChange)
					smallestChange = b;
			} else if (a < smallestChange)
				smallestChange = a;
		}
		if (c < elevMap[r].length - 1) {
			int a = current - elevMap[r][c + 1];
			if (a < 0)
				a *= -1;
			if (difficulty >= a) {
				int b = iterate(elevMap, accessMap, difficulty, r, c + 1);
				if (b < smallestChange)
					smallestChange = b;
			} else if (a < smallestChange)
				smallestChange = a;
		}
		return smallestChange;
	}
}