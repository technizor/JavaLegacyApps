import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Pageant
{
	// all four possible (r,c) changes
	public static final int[][] DIR = { { -1, 0 }, { 0, -1 }, { 1, 0 },
			{ 0, 1 } };

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"pageant.in")));
		for (int test = 0; test < 10; test++) {
			// read input of n*m characters (save as int for safety in large
			// grids)
			String[] n_m = reader.readLine().split(" ");
			int n = Integer.parseInt(n_m[0]);
			int m = Integer.parseInt(n_m[1]);
			int[][] pattern = new int[n][m];
			int sr = -1, sc = -1;

			for (int r = 0; r < n; r++) {
				String str = reader.readLine();
				char[] ch = str.toCharArray();
				for (int c = 0; c < m; c++)
					pattern[r][c] = ch[c];

				// save the first detected 'X' as the spot to expand from
				if (sr == -1) {
					int index = str.indexOf('X');
					if (index != -1) {
						sr = r;
						sc = index;
					}
				}
			}

			// expand to the closest spot
			boolean[][] mask1 = new boolean[n][m];
			Queue<Point> q1 = selectSpot(pattern, mask1, sr, sc);
			int dist1 = wave(pattern, mask1, q1);

			// expand to the last spot
			boolean[][] mask2 = new boolean[n][m];
			Queue<Point> q2 = selectSpot(pattern, mask2, sr, sc);
			int dist2 = wave(pattern, mask2, q2);

			// display result
			int total = dist1 + dist2;
			System.out.println(total);
		}
		reader.close();
	}

	private static Queue<Point> selectSpot(int[][] pattern, boolean[][] mask,
			int sr, int sc)
	{
		int n = pattern.length;
		int m = pattern[0].length;
		mask[sr][sc] = true;

		Queue<Point> process = new LinkedList<Point>();
		Queue<Point> collection = new LinkedList<Point>();

		// breadth first search flood fill, saving every point that is part of
		// the spot in collection
		process.add(new Point(sr, sc));
		collection.add(new Point(sr, sc));
		while (!process.isEmpty()) {
			Point p = process.remove();
			for (int[] delta : DIR) {
				int r = p.r + delta[0];
				int c = p.c + delta[1];
				if (r >= 0 && r < n && c >= 0 && c < m && !mask[r][c]
						&& pattern[r][c] == 'X') {
					Point p2 = new Point(r, c);
					process.add(p2);
					collection.add(p2);
					mask[r][c] = true;
				}
			}
		}
		return collection;

	}

	private static int wave(int[][] pattern, boolean[][] mask,
			Queue<Point> queue)
	{
		int n = pattern.length;
		int m = pattern[0].length;
		int dist = 0;
		while (!queue.isEmpty()) {
			// flood around, one distance at a time
			int size = queue.size();
			Queue<Point> objective = new LinkedList<Point>();
			boolean gtfo = false;
			for (int q = 0; q < size; q++) {
				Point p = queue.remove();
				for (int[] delta : DIR) {
					int r = p.r + delta[0];
					int c = p.c + delta[1];
					if (r >= 0 && r < n && c >= 0 && c < m && !mask[r][c]) {
						Point p2 = new Point(r, c);
						queue.add(p2);
						mask[r][c] = true;
						// overwrite the '.' characters with distance markers
						if (pattern[r][c] == '.') {
							pattern[r][c] = dist + 'a';
						}
						// if we discover an 'X' not part of the original spot
						else if (pattern[r][c] == 'X') {
							objective.add(p2);
							gtfo = true;
						}
					}
				}
			}
			// when we connect to another spot
			if (gtfo) {
				connectSpots(dist, pattern, objective);
				return dist;
			}
			dist++;

		}
		return 0;
	}

	public static void connectSpots(int dist, int[][] pattern,
			Queue<Point> objective)
	{
		int n = pattern.length;
		int m = pattern[0].length;
		// ensure we are connecting only to one other spot
		Point first = objective.peek();
		Queue<Point> connected = selectSpot(pattern, new boolean[n][m],
				first.r, first.c);
		objective.retainAll(connected);

		// connect all of the possible paths between the spots
		while (!objective.isEmpty() && dist > 0) {
			int s = objective.size();
			for (int q = 0; q < s; q++) {
				Point p = objective.remove();
				for (int[] delta : DIR) {
					int r = p.r + delta[0];
					int c = p.c + delta[1];
					// overwrite the appropriate markers
					if (r >= 0 && r < n && c >= 0 && c < m
							&& pattern[r][c] == 'a' + dist - 1) {
						Point p2 = new Point(r, c);
						objective.add(p2);
						pattern[r][c] = 'X';
					}
				}
			}
			dist--;
		}
	}
}

class Point
{
	int r;
	int c;

	Point(int r, int c)
	{
		this.r = r;
		this.c = c;
	}

	public boolean equals(Object other)
	{
		if (other instanceof Point) {
			Point p = (Point) other;
			return p.r == r && p.c == c;
		}
		return false;
	}

	public String toString()
	{
		return String.format("(%d,%d)", r, c);
	}
}