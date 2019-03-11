import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TileChng
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"tilechng.in")));
		for (int test = 0; test < 12; test++) {
			String[] n_m = reader.readLine().split(" ");
			int n = Integer.parseInt(n_m[0]);
			int m = Integer.parseInt(n_m[1]);

			int[] length = new int[n];
			for (int i = 0; i < n; i++)
				length[i] = Integer.parseInt(reader.readLine());

			// row = piece to process, col = total area of pieces processed,
			// value = cost to get that area
			int[][] cost = new int[n + 1][m + 1];
			for (int p = 0; p <= n; p++)
				for (int a = 1; a <= m; a++)
					cost[p][a] = Integer.MAX_VALUE;

			// use a list of valid starting positions to avoid looping over m
			// positions for each possible piece length
			ArrayList<Integer> positions = new ArrayList<Integer>();
			positions.add(0);
			for (int piece = 0; piece < n; piece++) {
				ArrayList<Integer> nextPositions = new ArrayList<Integer>();
				// iterate over the possible starting positions
				for (int pos : positions) {
					// generate every possible area for this piece
					for (int len = 1; len < 100 && pos + len * len <= m; len++) {
						// calculate the cost to get the area this way
						int newCost = cost[piece][pos] + (length[piece] - len)
								* (length[piece] - len);
						// update the table if necessary
						if (newCost < cost[piece + 1][pos + len * len])
							cost[piece + 1][pos + len * len] = newCost;
						// insert the position to the next list if not there
						int index = Collections.binarySearch(nextPositions, pos
								+ len * len);
						if (index < 0)
							nextPositions.add(-index - 1, pos + len * len);
					}
				}
				positions = nextPositions;
			}
			// print -1 if unable to generate the area, or the min cost
			System.out.println(cost[n][m] == Integer.MAX_VALUE ? -1
					: cost[n][m]);
		}
		reader.close();

	}
}
