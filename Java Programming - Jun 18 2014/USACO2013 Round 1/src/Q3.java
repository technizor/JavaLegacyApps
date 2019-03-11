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
	final static String name = "nocow";
	final static String prefix = "Farmer John has no ";
	final static String suffix = " cow.";

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(name
				+ ".in")));
		String[] inputStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inputStr[0]);
		int k = Integer.parseInt(inputStr[1]);
		String[][] list = new String[n][];
		for (int c = 0; c < n; c++) {
			String line = reader.readLine();
			list[c] = line.substring(prefix.length(),
					line.length() - suffix.length()).split(" ");
		}
		reader.close();

		int nAdj = list[0].length;
		@SuppressWarnings("unchecked")
		ArrayList<String>[] adjectiveList = new ArrayList[nAdj+1];
		adjectiveList[nAdj] = new ArrayList<String>();
		adjectiveList[nAdj].add("fill");
		for (int a = 0; a < list[0].length; a++) {
			adjectiveList[a] = new ArrayList<String>();
			for (String[] adj : list) {
				String str = adj[a];
				int index = Collections.binarySearch(adjectiveList[a], str);
				if (index < 0)
					adjectiveList[a].add(-index - 1, str);
			}
		}
		ArrayList<Integer> exclusions = new ArrayList<Integer>(n);
		for (int c = 0; c < n; c++) {
			int id = 0;
			for (int a = nAdj - 1; a >= 0; a--)
				id += Collections.binarySearch(adjectiveList[a], list[c][a])
						* adjectiveList[a + 1].size();
			exclusions.add(-Collections.binarySearch(exclusions, id) - 1, id);
		}

		int targetId = k;
		while (exclusions.remove(0) <= k)
			targetId++;

		String target = "";
		for (int a = nAdj-1; a > 0; a--) {
			target = adjectiveList[a].get(targetId % adjectiveList[a-1].size())
					+ " " + target;
			targetId = targetId/adjectiveList[a-1].size();
		}
		target = adjectiveList[0].get(targetId) + " " + target.trim();

		PrintWriter writer = new PrintWriter(new FileWriter(new File(name
				+ ".out")));
		writer.println(target);
		writer.close();
	}
}
