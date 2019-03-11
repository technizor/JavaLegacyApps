import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"baseball.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<Integer> pos = new ArrayList<Integer>(n);
		for (int c = 0; c < n; c++)
			pos.add(Integer.parseInt(reader.readLine()));
		reader.close();
		Collections.sort(pos);
		int combos = 0;
		for (int first = 0; first < n - 2; first++) {
			for (int second = first + 1; second < n - 1; second++) {
				int dist = pos.get(second) - pos.get(first);
				int closest = Collections.binarySearch(pos, pos.get(second)
						+ dist);
				int furthest = Collections.binarySearch(pos, pos.get(second)
						+ dist * 2);
				if(closest < 0) // if not exist, then this index is the first one with the min dist
					closest = -closest-1;
				// if exist, then it is the first one with min dist
				if(furthest < 0)// if not exist, then anything before this has less than max dist
					furthest = -furthest-1;
				else //if exist, then we include this one was within max dist
					furthest++;
				// return the difference in indexes
				combos += furthest-closest;
			}
		}

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"baseball.out")));
		writer.println(combos);
		writer.close();

	}
}
