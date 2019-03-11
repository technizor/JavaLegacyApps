import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

public class Q3
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"mooomoo.in")));
		String[] n_b = reader.readLine().split(" ");
		int n = Integer.parseInt(n_b[0]);
		int b = Integer.parseInt(n_b[1]);
		TreeSet<Integer> breeds = new TreeSet<Integer>();
		for (int i = 0; i < b; i++)
			breeds.add(Integer.parseInt(reader.readLine()));
		int[] fields = new int[n];
		for (int i = 0; i < n; i++)
			fields[i] = Integer.parseInt(reader.readLine());
		reader.close();
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"mooomoo.out")));
		writer.println(calculate(n, b, breeds, fields));
		writer.close();
	}

	private static int calculate(int n, int b, TreeSet<Integer> breeds,
			int[] fields)
	{
		// remove wind
		int maxVolume = 0;
		int nBreeds = breeds.size();
		for (int i = n - 1; i > 0; i--) {
			if (fields[i - 1] > 0) {
				fields[i] -= fields[i - 1] - 1;
			}
			if (fields[i] > maxVolume)
				maxVolume = fields[i];
		}
		// generate a table for all of the volumes and breeds usable
		ArrayList<Integer> breedList = new ArrayList<Integer>(breeds);
		int[][] table = new int[nBreeds + 1][maxVolume + 1];
		for (int i = 1; i <= maxVolume; i++)
			table[0][i] = -1;
		for (int t = 1; t <= nBreeds; t++) {
			for (int v = 1; v <= maxVolume; v++) {
				int tv = breedList.get(t - 1);
				int rv = v - tv;
				int count = 1;
				table[t][v] = table[t - 1][v];
				while (rv >= 0) {
					int cval = table[t - 1][rv];
					if (cval != -1 && (table[t][v] == -1 || table[t][v] > cval+count))
						table[t][v] = cval+count;
					rv -= tv;
					count++;
				}
			}
		}
		// count cows
		int total = 0;
		for (int v : fields)
			total += table[nBreeds][v];
		return total;
	}
}
