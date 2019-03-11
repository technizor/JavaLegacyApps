import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Prob1
{
	private static final int PROB = 1;
	private static final int ATTEMPT = 2;

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(
				String.format("data%d%d.txt", PROB, ATTEMPT)));
		PrintWriter writer = new PrintWriter(new FileWriter(String.format(
				"out%d%d.txt", PROB, ATTEMPT)));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			String[] n_l = reader.readLine().split(" ");
			int n = Integer.parseInt(n_l[0]);
			int l = Integer.parseInt(n_l[1]);
			String[] a = reader.readLine().split(" ");
			String[] b = reader.readLine().split(" ");
			ArrayList<String> start = new ArrayList<String>();
			ArrayList<String> end = new ArrayList<String>();
			for (int i = 0; i < n; i++) {
				start.add(a[i]);
				end.add(b[i]);
			}
			Collections.sort(start);
			Collections.sort(end);
			Wires orig = new Wires(start);
			Wires endg = new Wires(end);
			int val = calc(orig, endg, n, l);

			String output = val == -1 ? "NOT POSSIBLE" : ("" + val);
			writer.printf("Case #%d: %s%n", testCase, output);
		}
		writer.close();
		reader.close();
	}

	private static int calc(Wires start, Wires end, int n, int l)
	{
		PriorityQueue<Wires> q = new PriorityQueue<Wires>();
		q.add(start);
		while (!q.isEmpty()) {
			Wires s = q.remove();
			if (s.equals(end))
				return s.length;
			for (int i = 0; i < l; i++) {
				if (s.canFlip(i)) {
					q.add(new Wires(s, i));
				}
			}
		}
		return -1;
	}

	static class Wires implements Comparable<Wires>
	{
		int flip;
		ArrayList<String> wire;
		boolean[] flips;
		int length;

		Wires(ArrayList<String> wires)
		{
			length = wires.get(0).length();
			flip = 0;
			wire = wires;
			flips = new boolean[length];
		}

		Wires(Wires old, int pos)
		{
			length = old.length;
			wire = new ArrayList<String>(old.wire);
			for (int i = 0; i < wire.size(); i++) {
				StringBuilder strb = new StringBuilder(wire.get(i));
				strb.setCharAt(pos, strb.charAt(pos) == '1' ? '0' : '1');
				wire.set(i, strb.toString());
			}
			flips = new boolean[length];
			System.arraycopy(old.flips, 0, flips, 0, length);
			flips[pos] = true;
			flip = old.flip + 1;
		}

		@Override
		public int compareTo(Wires arg0)
		{
			return flip - arg0.flip;
		}

		public boolean canFlip(int pos)
		{
			return !flips[pos];
		}

		public boolean equals(Wires other)
		{
			ArrayList<String> list = new ArrayList<String>(wire);
			list.retainAll(other.wire);
			return list.size() == other.wire.size();
		}
	}
}
