import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"fairphoto.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<FairCow> cows = new ArrayList<FairCow>(n);
		for (int c = 0; c < n; c++) {
			String[] line = reader.readLine().split(" ");
			FairCow cow = new FairCow(Integer.parseInt(line[0]),
					line[1].equals("S"));
			int index = Collections.binarySearch(cows, cow);
			cows.add(-index - 1, cow);
		}
		reader.close();

		int[][] types = new int[n + 1][2];
		for (int c = 0; c < n; c++) {
			types[c + 1][0] = types[c][0];
			types[c + 1][1] = types[c][1];
			if (cows.get(c).isSpotted())
				types[c + 1][1]++;
			else
				types[c + 1][0]++;
		}
		int answer = getDist(cows, types);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"fairphoto.out")));
		writer.println(answer);
		writer.close();
	}

	private static int getDist(ArrayList<FairCow> cows, int[][] types)
	{
		int n = types.length - 1;
		int best = 0;
		for (int c1 = 0; c1 < n - 1; c1++) {
			int cBest = measure(cows, types, c1);
			if (cBest > best)
				best = cBest;
		}
		return best;
	}

	private static int measure(ArrayList<FairCow> cows, int[][] types, int c1)
	{
		int n = types.length - 1;
		for (int c2 = n - 1; c2 > c1; c2--) {
			int whiteCows = types[c2+1][0] - types[c1][0];
			int spotCows = types[c2+1][1] - types[c1][1];
			if (whiteCows == spotCows
					|| (whiteCows > spotCows && (whiteCows + spotCows) % 2 == 0))
				return cows.get(c2).compareTo(cows.get(c1));
		}
		return 0;
	}
}

class FairCow implements Comparable<FairCow>
{
	int pos;
	boolean isSpotted;

	boolean isSpotted()
	{
		return isSpotted;
	}

	FairCow(int pos, boolean isSpotted)
	{
		this.pos = pos;
		this.isSpotted = isSpotted;
	}

	public int compareTo(FairCow other)
	{
		return pos - other.pos;
	}
}