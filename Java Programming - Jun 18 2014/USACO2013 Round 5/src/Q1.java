import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class Q1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"irrigation.in")));
		String[] n_c = reader.readLine().split(" ");
		int n = Integer.parseInt(n_c[0]);
		int c = Integer.parseInt(n_c[1]);
		int[] xPos = new int[n];
		int[] yPos = new int[n];
		for (int f = 0; f < n; f++) {
			String[] x_y = reader.readLine().split(" ");
			xPos[f] = Integer.parseInt(x_y[0]);
			yPos[f] = Integer.parseInt(x_y[1]);
		}
		reader.close();

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"irrigation.out")));
		writer.println(calculate(n, c, xPos, yPos));
		writer.close();
	}

	private static int calculate(int n, int c, int[] xPos, int[] yPos)
	{
		int[][] cost = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				cost[i][j] = cost[j][i] = (xPos[i] - xPos[j])
						* (xPos[i] - xPos[j]) + (yPos[i] - yPos[j])
						* (yPos[i] - yPos[j]);
			}
		}
		TreeSet<Integer> connected = new TreeSet<Integer>();
		int tCost = 0;
		connected.add(0);
		int last = 0;
		while (connected.size() < n) {
			int lowestCost = Integer.MAX_VALUE;
			int lowestIndex = -1;
			for (int i = 0; i < n; i++) {
				int cCost = cost[last][i];
				if (!connected.contains(i) && cCost >= c && cCost < lowestCost) {
					lowestCost = cCost;
					lowestIndex = i;
				}
			}
			if (lowestIndex == -1)
				return -1;
			connected.add(lowestIndex);
			tCost += lowestCost;
		}

		return tCost;
	}
}