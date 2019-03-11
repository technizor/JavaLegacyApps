import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Sol3
{
	final static String prefix = "Farmer John has no ";
	final static String suffix = " cow.";

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"data//7.in")));
		String[] inputStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inputStr[0]);
		int k = Integer.parseInt(inputStr[1]);
		TreeSet<AdjCow> exclusion = new TreeSet<AdjCow>();
		for (int c = 0; c < n; c++) {
			String line = reader.readLine();
			String adjLine = line.substring(prefix.length(), line.length()
					- suffix.length());
			AdjCow cow = new AdjCow(adjLine.split(" "));
			exclusion.add(cow);
		}
		reader.close();

		AdjCow.generateTree(k+n);
		TreeSet<AdjCow> tree = AdjCow.tree;
		tree.removeAll(exclusion);
		AdjCow target = (AdjCow) (AdjCow.tree.toArray()[k - 1]);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"nocow.out")));
		writer.println(target);
		writer.close();
	}
}

class AdjCow implements Comparable<AdjCow>
{
	static TreeSet<AdjCow> tree;
	static int nAdj;
	static ArrayList<String>[] adjectiveList;
	String adjList;
	static int generated;

	static void generateTree(int max)
	{
		tree = new TreeSet<AdjCow>();
		generated = 0;
		generateCow(0, new String[nAdj], max);
		int gen = generated;
		return;
	}

	private static void generateCow(int adj, String[] adjList, int max)
	{
		if (adj == nAdj) {
			tree.add(new AdjCow(adjList));
			generated++;
			return;
		}
		for (String str : adjectiveList[adj]) {
			if(tree.size() >= max)
				return;
			adjList[adj] = str;
			generateCow(adj + 1, adjList, max);
		}
	}

	@SuppressWarnings("unchecked")
	AdjCow(String[] adj)
	{
		if (adjectiveList == null) {
			nAdj = adj.length;
			adjectiveList = new ArrayList[nAdj];
			for (int a = 0; a < nAdj; a++) {
				adjectiveList[a] = new ArrayList<String>();
				adjectiveList[a].add(adj[a]);
			}
		} else {
			for (int a = 0; a < nAdj; a++) {
				int index = Collections.binarySearch(adjectiveList[a], adj[a]);
				if (index < 0)
					adjectiveList[a].add(-index - 1, adj[a]);
			}
		}
		StringBuilder strb = new StringBuilder(adj[0]);
		for (int n = 1; n < nAdj; n++)
			strb.append(" " + adj[n]);
		adjList = strb.toString();
	}

	public String toString()
	{
		return adjList;
	}

	public int compareTo(AdjCow other)
	{
		return adjList.compareTo(other.adjList);
	}
}
