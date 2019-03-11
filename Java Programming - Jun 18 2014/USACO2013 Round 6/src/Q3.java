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
	public static void main(final String[] args) throws IOException
	{
		long start = System.nanoTime();
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"odometer.in")));
		String[] x_y = reader.readLine().split(" ");
		reader.close();

		Value x = new Value(x_y[0]);
		Value y = new Value(x_y[1]);
		ArrayList<Value> intValues = new ArrayList<Value>();
		for (int d = 0; d < 100; d++)
			intValues.add(new Value("" + d));
		for (int digits = 3, first = 10, last = 100; digits <= y.length(); digits++, first = last, last = intValues
				.size()) {
			for (int n = first; n < last; n++) {
				String numBase = intValues.get(n).toString();
				for (int p = numBase.length(); p >= 0; p--) {
					for (int d = p > 0 ? 0 : 1; d < 10; d++) {
						Value val = new Value(numBase.substring(0, p) + d
								+ numBase.substring(p));
						if (val.isInteresting()) {
							int index = Collections
									.binarySearch(intValues, val);
							if (index < 0)
								intValues.add(-index - 1, val);
						}
					}
				}
			}
		}
		int index1 = Collections.binarySearch(intValues, x);
		int index2 = Collections.binarySearch(intValues, y);
		if (index1 < 0)
			index1 *= -1;
		if (index2 < 0)
			index2 *= -1;
		else
			index2++;
		int answer = index2 - index1;
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"odometer.out")));
		writer.println(answer);
		writer.close();
		long end = System.nanoTime();
		System.out.printf("%.2fms", (end - start) / 1000000.0);
	}

	/*
	 * public static void main(final String[] args) throws IOException { long
	 * start = System.nanoTime(); BufferedReader reader = new BufferedReader(new
	 * FileReader(new File( "odometer.in"))); String[] x_y =
	 * reader.readLine().split(" "); reader.close();
	 * 
	 * Value x = new Value(x_y[0]); Value y = new Value(x_y[1]);
	 * ArrayList<Value> intValues = new ArrayList<Value>(); for (int digits =
	 * x.length(); digits <= y.length(); digits++) { if (digits == 19) {
	 * intValues.add(new Value("1000000000000000000")); } else {
	 * ArrayList<char[]> mask = generateMask(digits); for (char[] m : mask) {
	 * for (int digit = 0; digit < 10; digit++) { generateValues(intValues, m,
	 * digit, 0); } } } } int index1 = Collections.binarySearch(intValues, x);
	 * int index2 = Collections.binarySearch(intValues, y); if (index1 < 0)
	 * index1 *= -1; if (index2 < 0) index2 *= -1; else index2++; int answer =
	 * index2 - index1; PrintWriter writer = new PrintWriter(new FileWriter(new
	 * File( "odometer.out"))); writer.println(answer); writer.close(); long end
	 * = System.nanoTime(); System.out.printf("%.2fms", (end - start) /
	 * 1000000.0); }
	 * 
	 * private static void generateValues(ArrayList<Value> intValues, char[] m,
	 * int digit, int pos) { if (pos == m.length) { Value val = new Value(new
	 * String(m)); if (val.isInteresting() && val.length() == m.length) { int
	 * index = Collections.binarySearch(intValues, val); if (index < 0)
	 * intValues.add(-index - 1, val); } return; } if (m[pos] == 'd') { m[pos] =
	 * (char) ('0' + digit); generateValues(intValues, m, digit, pos + 1); }
	 * else { for (int i = 0; i < 10; i++) { m[pos] = (char) ('0' + i);
	 * generateValues(intValues, m, digit, pos + 1); } }
	 * 
	 * }
	 * 
	 * private static ArrayList<char[]> generateMask(int digits) {
	 * ArrayList<char[]> list = new ArrayList<char[]>(); char[] cs = new
	 * char[digits]; for (int i = 0; i < digits; i++) cs[i] = ' ';
	 * generateMasks(list, cs, (digits + 1) / 2, 0); return list; }
	 * 
	 * private static void generateMasks(ArrayList<char[]> list, char[] cs, int
	 * remaining, int pos) { if (remaining == 0) { char[] ch = new
	 * char[cs.length]; System.arraycopy(cs, 0, ch, 0, cs.length); list.add(ch);
	 * return; } for (int p = pos; p < cs.length - remaining + 1; p++) { cs[p] =
	 * 'd'; generateMasks(list, cs, remaining - 1, p + 1); cs[p] = ' '; } }
	 */
}

class Value implements Comparable<Value>
{
	private final int beginning;
	private final int ending;
	private int[] digits;
	private int length;

	Value(String str)
	{
		digits = new int[10];
		if (str.length() > 9) {
			beginning = Integer.parseInt(str.substring(0, str.length() - 9));
			ending = Integer.parseInt(str.substring(str.length() - 9));
			for (int a = beginning; a > 0; digits[a % 10]++, a /= 10, length++)
				;
		} else {
			beginning = 0;
			ending = Integer.parseInt(str);
		}
		for (int a = ending; a > 0; digits[a % 10]++, a /= 10, length++)
			;
	}

	public int compareTo(Value other)
	{
		if (beginning == other.beginning)
			return ending - other.ending;
		return beginning - other.beginning;
	}

	public boolean isInteresting()
	{
		for (int d = 0; d < 10; d++)
			if (digits[d] >= (length + 1) / 2)
				return true;
		return false;
	}

	public String toString()
	{
		if (beginning != 0)
			return String.format("%d%09d", beginning, ending);
		return String.format("%d", ending);
	}

	public int length()
	{
		return length;
	}
}
