import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q3
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"wormhole.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<int[]> list = new ArrayList<int[]>(n);
		for (int w = 0; w < n; w++) {
			String[] line = reader.readLine().split(" ");
			int[] coord = { Integer.parseInt(line[0]),
					Integer.parseInt(line[1]) };
			list.add(coord);
		}
		reader.close();

		WormHoleGrid grid = new WormHoleGrid(list);
		int total = grid.getNumberOfLoops();
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"wormhole.out")));
		writer.println(total);
		writer.close();
		// System.out.println(grid.pairsPossible);
	}
}

class WormHoleGrid
{
	int pairsPossible = 0;
	ArrayList<WormHole> holes;
	int n;

	WormHoleGrid(ArrayList<int[]> coordinates)
	{
		n = coordinates.size();
		holes = new ArrayList<WormHole>();
		for (int[] point : coordinates) {
			WormHole hole = new WormHole(point);
			holes.add(hole);
		}
		Collections.sort(holes);
	}

	public int getNumberOfLoops()
	{
		// Set up wormhole pairs
		int[] pairs = new int[n];
		for (int i = 0; i < n; i++)
			pairs[i] = -1;
		return createPairs(0, pairs);
	}

	private int createPairs(int pairNum, int[] pairs)
	{
		if (pairNum == n / 2) {
			pairsPossible++;
			return isLoopable(pairs) ? 1 : 0;
		}
		int firstIndex = 0;
		boolean foundFirstIndex = false;
		while (!foundFirstIndex) {
			if (pairs[firstIndex] == -1)
				foundFirstIndex = true;
			else
				firstIndex++;
		}
		int total = 0;
		for (int secondIndex = firstIndex + 1; secondIndex < n; secondIndex++) {
			if (pairs[secondIndex] == -1) {
				pairs[firstIndex] = secondIndex;
				pairs[secondIndex] = firstIndex;
				// Calculate with this chosen pair then undo the pairing
				total += createPairs(pairNum + 1, pairs);
				pairs[firstIndex] = pairs[secondIndex] = -1;
			}
		}
		return total;
	}

	private boolean isLoopable(int[] pairs)
	{
		for (int start = 0; start < n; start++) {
			boolean[] visited = new boolean[n];
			int currentPos = start;
			boolean running = true;
			while (running) {
				if (visited[currentPos])
					return true;
				visited[currentPos] = true;
				int pairedPos = pairs[currentPos];
				visited[pairedPos] = true;
				int nextPos = pairedPos + 1;
				if (nextPos >= n
						|| holes.get(nextPos).y != holes.get(pairedPos).y)
					running = false;
				currentPos = nextPos;
			}
		}
		return false;
	}

	public String toString()
	{
		StringBuilder strb = new StringBuilder(holes.get(0).toString());
		int currentRow = holes.get(0).y;

		for (int i = 1; i < n; i++) {
			WormHole hole = holes.get(i);
			if (hole.y != currentRow) {
				currentRow = hole.y;
				strb.append("\n");
			}
			strb.append(hole.toString());
		}
		return strb.toString();
	}
}

class WormHole implements Comparable<WormHole>
{
	int x;
	int y;

	WormHole(int[] coords)
	{
		x = coords[0];
		y = coords[1];
	}

	public int compareTo(WormHole otherHole)
	{
		if (y != otherHole.y)
			return y - otherHole.y;
		return x - otherHole.x;
	}

	public String toString()
	{
		return String.format("[%d,%d]", x, y);
	}
}