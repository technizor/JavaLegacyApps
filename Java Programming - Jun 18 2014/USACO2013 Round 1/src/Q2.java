import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q2
{
	final static String name = "milktemp";

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(name
				+ ".in")));
		String[] inputStr = reader.readLine().split(" ");
		short n = Short.parseShort(inputStr[0]);
		Range.x = Short.parseShort(inputStr[1]);
		Range.y = Short.parseShort(inputStr[2]);
		Range.z = Short.parseShort(inputStr[3]);
		Range[] cows = new Range[n];
		int maxTemp = 0;

		for (int c = 0; c < n; c++) {
			String[] tempStr = reader.readLine().split(" ");
			int min = Integer.parseInt(tempStr[0]);
			int max = Integer.parseInt(tempStr[1]);
			cows[c] = new Range(min, max);
			if (max > maxTemp)
				maxTemp = max;
		}
		reader.close();

		int max = 0;
		for (int r = 0; r < maxTemp; r++) {
			int total = 0;
			for (int c = 0; c < n; c++)
				total += cows[c].getValue(r);
			if (total > max)
				max = total;
		}
		PrintWriter writer = new PrintWriter(new FileWriter(new File(name
				+ ".out")));
		writer.println(max);
		writer.close();
	}

	static class Range
	{
		public static short x = 0;
		public static short y = 0;
		public static short z = 0;
		int minTemp;
		int maxTemp;

		Range(int min, int max)
		{
			minTemp = min;
			maxTemp = max;
		}

		short getValue(int temp)
		{
			if (temp < minTemp)
				return x;
			if (temp > maxTemp)
				return z;
			return y;
		}
	}
}
