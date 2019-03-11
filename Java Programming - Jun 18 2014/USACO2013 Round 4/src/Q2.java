import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"rblock.in")));
		String[] nLine = reader.readLine().split(" ");
		int n = Integer.parseInt(nLine[0]);
		int m = Integer.parseInt(nLine[1]);
		int[][] paths = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			String[] pLine = reader.readLine().split(" ");
			int a = Integer.parseInt(pLine[0]);
			int b = Integer.parseInt(pLine[1]);
			int l = Integer.parseInt(pLine[2]);
			paths[a][b] = paths[b][a] = l;
		}
		reader.close();

		Path path = djikstra(paths, n);
		ArrayList<Integer> possibleChanges = path.nodesUsed;
		int dist = path.dist;
		int bestChange = 0;
		for (int i = 1; i < possibleChanges.size(); i++) {
			int a = possibleChanges.get(i - 1);
			int b = possibleChanges.get(i);
			paths[a][b] *= 2;
			paths[b][a] *= 2;
			Path newPath = djikstra(paths, n);
			paths[a][b] /= 2;
			paths[b][a] /= 2;
			int change = newPath.dist-dist;
			if(change > 0 && change > bestChange)
				bestChange = change;
		}

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"rblock.out")));
		writer.println(bestChange);
		writer.close();

	}

	public static Path djikstra(int[][] paths, int n)
	{
		PriorityQueue<Path> routes = new PriorityQueue<Path>();
		for (int i = 1; i <= n; i++) {
			if (paths[1][i] != 0) {
				routes.add(new Path(1, i, paths[1][i]));
			}
		}
		boolean[] visited = new boolean[n + 1];
		visited[1] = true;
		Path current = null;
		while (!visited[n]) {
			current = routes.poll();
			int a = current.nodesUsed.get(current.nodesUsed.size() - 1);
			if (!visited[a]) {
				visited[a] = true;
				for (int i = 1; i <= n; i++) {
					if (!visited[i] && paths[a][i] != 0) {
						routes.add(new Path(current, i, paths[a][i]));
					}
				}
			}
		}
		return current;
	}
}

class Path implements Comparable<Path>
{
	int dist;
	ArrayList<Integer> nodesUsed;

	Path(int a, int b, int l)
	{
		nodesUsed = new ArrayList<Integer>(250);
		nodesUsed.add(a);
		nodesUsed.add(b);
		dist = l;
	}

	Path(Path old, int b, int l)
	{
		nodesUsed = new ArrayList<Integer>(old.nodesUsed);
		nodesUsed.add(b);
		dist = old.dist + l;
	}

	public int compareTo(Path other)
	{
		return dist - other.dist;
	}
}