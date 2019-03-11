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
				"auto.in")));
		String[] nLine = reader.readLine().split(" ");
		int w = Integer.parseInt(nLine[0]);
		int n = Integer.parseInt(nLine[1]);
		PrintWriter writer = new PrintWriter(new FileWriter(
				new File("auto.out")));
		ArrayList<Entry> dictionary = new ArrayList<Entry>(w);
		for (int i = 1; i <= w; i++) {
			String word = reader.readLine();
			dictionary.add(new Entry(word, i));
		}
		Collections.sort(dictionary);

		for (int i = 1; i <= n; i++) {
			String[] data = reader.readLine().split(" ");
			Entry autocomp = new Entry(data[1], 0);
			int index = -Collections.binarySearch(dictionary, autocomp) - 1
					+ Integer.parseInt(data[0]) - 1;
			if (index >= dictionary.size())
				writer.println("-1");
			else {
				Entry entry = dictionary.get(index);
				if (entry.word.indexOf(data[1]) != 0)
					writer.println("-1");
				else
					writer.println(entry.index);
			}

		}
		reader.close();
		writer.close();

	}
}

class Entry implements Comparable<Entry>
{
	String word;
	int index;

	Entry(String word, int index)
	{
		this.word = word;
		this.index = index;
	}

	public int compareTo(Entry other)
	{
		return word.compareTo(other.word);
	}

	public String toString()
	{
		return word;
	}
}