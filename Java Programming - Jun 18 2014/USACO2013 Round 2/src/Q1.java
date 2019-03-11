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
				"records.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<CowGroup> groups = new ArrayList<CowGroup>(1000);
		for (int g = 0; g < n; g++) {
			CowGroup line = new CowGroup(reader.readLine());
			if (!groups.contains(line))
				groups.add(line);
		}
		reader.close();
		Collections.sort(groups);
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"records.out")));
		writer.print(groups.get(0).getOccurence());
		writer.close();

	}

}

class CowGroup implements Comparable<CowGroup>
{
	int occurence;
	ArrayList<String> list;

	CowGroup(String line)
	{
		occurence = 1;
		String[] cows = line.split(" ");
		list = new ArrayList<String>();
		list.add(cows[0]);
		list.add(cows[1]);
		list.add(cows[2]);
	}

	public boolean equals(Object other)
	{
		if (other instanceof CowGroup) {
			CowGroup otherGroup = (CowGroup) other;
			if (list.containsAll(otherGroup.list)) {
				otherGroup.occurence++;
				return true;
			}
		}
		return false;
	}

	public int compareTo(CowGroup other)
	{
		return other.occurence - occurence;
	}

	public String toString()
	{
		return String.format("%s %s %s", list.get(0), list.get(1), list.get(2));
	}

	public int getOccurence()
	{
		return occurence;
	}
}