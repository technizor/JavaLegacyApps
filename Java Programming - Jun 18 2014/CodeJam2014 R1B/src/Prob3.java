import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Prob3
{
	private static final int PROB = 3;
	private static final int ATTEMPT = 0;

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(
				String.format("data%d%d.txt", PROB, ATTEMPT)));
		PrintWriter writer = new PrintWriter(new FileWriter(String.format(
				"out%d%d.txt", PROB, ATTEMPT)));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			String[] n_m = reader.readLine().split(" ");
			int n = Integer.parseInt(n_m[0]);
			int m = Integer.parseInt(n_m[1]);
			ArrayList<Zip> zips = new ArrayList<Zip>();
			for (int i = 1; i <= n; i++) {
				zips.add(new Zip(i, Integer.parseInt(reader.readLine())));
			}
			ArrayList<Zip> order = new ArrayList<Zip>(zips);
			Collections.sort(zips);
			int[][] grid = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				grid[0][i] = Collections.binarySearch(zips, order.get(i - 1)) + 1;
			}
			for (int i = 0; i < m; i++) {
				String[] x_y = reader.readLine().split(" ");
				int x = Integer.parseInt(x_y[0]);
				int y = Integer.parseInt(x_y[1]);
				int xc = Collections.binarySearch(zips, order.get(x - 1));
				int yc = Collections.binarySearch(zips, order.get(x - 1));
				grid[x][y] = yc;
				grid[y][x] = xc;
			}
			boolean[] visited = new boolean[n + 1];
			Path best = null;
			for (int start = 1; start <= n; start++) {
				Path path = calculate(new Path(""), grid, visited,
						zips.get(0).n, 1);
				if(path != null && (best == null || best.compareTo(path)>0))
					best= path;
			}
			StringBuilder strb = new StringBuilder();
			for (int i = 0; i < best.path.length(); i++) {
				int index = best.path.charAt(i) - 'A' - 1;
				strb.append(order.get(index).zip);
			}
			String output = "" + strb;

			writer.printf("Case #%d: %s%n", testCase, output);
		}
		writer.close();
		reader.close();
	}

	private static Path calculate(Path path, int[][] grid, boolean[] visited,
			int pos, int steps)
	{
		Path npath = visited[pos] ? new Path(path.path) : new Path(path.path
				+ (char) (pos + 'A'));
		if (npath.path.length() == visited.length - 1)
			return npath;
		visited[pos] = true;
		Path best = null;
		if (steps > 1)
			best = calculate(npath, grid, visited,
					npath.path.charAt(npath.path.length() - 2) - 'A', steps - 1);
		for (int a = 1; a < visited.length; a++) {
			if (!visited[a] && grid[pos][a] != 0) {
				Path next = calculate(npath, grid, visited, a, steps + 1);
				if ((best == null && next != null)
						|| (best != null && next != null && best
								.compareTo(next) > 0))
					best = next;
			}
		}
		visited[pos] = false;
		return best;
	}

	static class Zip implements Comparable<Zip>
	{
		int n;
		int zip;

		Zip(int n, int zip)
		{
			this.n = n;
			this.zip = zip;
		}

		public int compareTo(Zip other)
		{
			return zip - other.zip;
		}
	}

	static class Path implements Comparable<Path>
	{
		String path;

		Path(String path)
		{
			this.path = path;
		}

		@Override
		public int compareTo(Path o)
		{
			if (path != null && o.path != null)
				for (int i = 0; i < path.length(); i++) {
					if (path.charAt(i) != o.path.charAt(i))
						return path.charAt(i) - o.path.charAt(i);
				}
			return 0;
		}

		public String toString()
		{
			return path;
		}

	}
}
