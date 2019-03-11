import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Sol2
{

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"milktemp.in")));
		String[] line = reader.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		Cow.x = Integer.parseInt(line[1]);
		Cow.y = Integer.parseInt(line[2]);
		Cow.z = Integer.parseInt(line[3]);

		Cow[] list = new Cow[n];
		ArrayList<Integer> sigTemp = new ArrayList<Integer>();

		for (int c = 0; c < n; c++) {
			String[] str = reader.readLine().split(" ");
			int l = Integer.parseInt(str[0]);
			int h = Integer.parseInt(str[1]);
			list[c] = new Cow(l, h);
			int p1 = Collections.binarySearch(sigTemp, l);
			if (p1 < 0)
				sigTemp.add(-p1 - 1, l);
			int p2 = Collections.binarySearch(sigTemp, h);
			if (p2 < 0)
				sigTemp.add(-p2 - 1, h);
		}
		reader.close();

		int m = 0;
		for (Integer t : sigTemp) {
			int s = 0;
			for (Cow c : list)
				s += c.yield(t);
			if (s > m)
				m = s;
		}
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"milktemp.out")));
		writer.println(m);
		writer.close();
	}
}

class Cow
{
	static int x = 0;
	static int y = 1;
	static int z = 0;

	int l;
	int h;

	public Cow(int lo, int hi)
	{
		l = lo;
		h = hi;
	}

	int yield(int t)
	{
		if (t < l)
			return x;
		if (t > h)
			return z;
		return y;
	}
}