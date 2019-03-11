import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Prob2
{
	private static final int PROB = 2;
	private static final int ATTEMPT = 2;

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(
				String.format("data%d%d.txt", PROB, ATTEMPT)));
		PrintWriter writer = new PrintWriter(new FileWriter(String.format(
				"out%d%d.txt", PROB, ATTEMPT)));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			String[] a_b_k = reader.readLine().split(" ");
			int a = Integer.parseInt(a_b_k[0]);
			int b = Integer.parseInt(a_b_k[1]);
			int k = Integer.parseInt(a_b_k[2]);
			int firstBase = 30;
			while (firstBase >= 0 && a / pow(firstBase) == 0
					&& b / pow(firstBase) == 0)
				firstBase--;
			int total = 0;
			for (int i = 0; i < k; i++) {
				Binary both = new Binary(i);
				total += generateCount(both, a, b, both.num, both.num,
						30 - firstBase);
			}
			String output = "" + total;

			writer.printf("Case #%d: %s%n", testCase, output);
		}
		writer.close();
		reader.close();
	}

	private static int generateCount(Binary both, int limA, int limB, int a,
			int b, int base)
	{
		if (base == 31) {
			if((a&b) == both.num)
				return 1;
			return 0;
		}
		int sum = 0;
		if (both.binArr[base] != 1) {
			int an = a + pow(30 - base);
			int bn = b + pow(30 - base);
			if (an < limA && b < limB)
				sum += generateCount(both, limA, limB, an, b, base + 1);
			if (a < limA && bn < limB)
				sum += generateCount(both, limA, limB, a, bn, base + 1);
			if (a < limA && b < limB)
				sum += generateCount(both, limA, limB, a, b, base + 1);
		}
		return sum;
	}

	static int pow(int exp)
	{
		return (int) Math.pow(2, exp);
	}

	static class Binary implements Comparable<Binary>
	{
		int num;
		int[] binArr;
		String bin;

		Binary(int n)
		{
			num = n;
			binArr = new int[31];
			StringBuilder strb = new StringBuilder();
			for (int base = (int) Math.pow(2, 30), i = 0; base >= 1; base /= 2) {
				if (n >= base) {
					strb.append('1');
					n -= base;
					binArr[i] = 1;
				} else {
					strb.append('0');
					binArr[i] = 0;
				}
			}
			bin = strb.toString();
		}

		public String toString()
		{
			return bin;
		}

		public int compareTo(Binary other)
		{
			return num - other.num;
		}
	}
}
