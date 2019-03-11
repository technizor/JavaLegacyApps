import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PhoneSwipe implements Comparable<PhoneSwipe>
{
	private static final int[][][] available = { { { 0 } },
			{ { 2, 3 }, { 6 }, { 5, 9 }, { 8 }, { 4, 7 } },// 1
			{ { 3 }, { 6 }, { 9 }, { 5, 8 }, { 7 }, { 4 }, { 1 } },// 2
			{ { 6, 9 }, { 8 }, { 5, 7 }, { 4 }, { 2, 1 } },// 3
			{ { 1 }, { 2 }, { 3 }, { 5, 6 }, { 9 }, { 8 }, { 7 } }, // 4
			{ { 2 }, { 3 }, { 6 }, { 9 }, { 8 }, { 7 }, { 4 }, { 1 } }, // 5
			{ { 3 }, { 9 }, { 8 }, { 7 }, { 5, 4 }, { 1 }, { 2 } }, // 6
			{ { 4, 1 }, { 2 }, { 5, 3 }, { 6 }, { 8, 9 } },// 7
			{ { 5, 2 }, { 3 }, { 6 }, { 9 }, { 7 }, { 4 }, { 1 } },// 8
			{ { 6, 3 }, { 8, 7 }, { 4 }, { 5, 1 }, { 2 } } };// 9
	private String order;
	private boolean[] visited;

	public PhoneSwipe(String order)
	{
		this.order = order;
		visited = new boolean[10];
		for (int p = 1; p <= 9; p++) {
			if (order.indexOf("" + p) != -1)
				visited[p] = true;
		}
	}

	@Override
	public int compareTo(PhoneSwipe other)
	{
		if (length() == other.length()) {
			for (int n = 0; n < length(); n++) {
				if (position(n) != other.position(n)) {
					return position(n) - other.position(n);
				}
			}
			return 0;
		}
		return length() - other.length();
	}

	public int length()
	{
		return order.length();
	}

	public int position(int n)
	{
		return order.charAt(n) - '0';
	}

	public String toString()
	{
		return order;
	}

	public String toGrid()
	{
		char[][] grid = new char[3][3];
		for (int p = 1; p <= 9; p++) {
			int n = order.indexOf(p);
			if (n == -1)
				grid[(p - 1) / 3][(p - 1) % 3] = ' ';
			else
				grid[(p - 1) / 3][(p - 1) % 3] = (char) (n + '0');
		}
		StringBuilder strb = new StringBuilder();
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				strb.append(grid[r][c]);
			}
			strb.append('\n');
		}
		return grid.toString().trim();
	}

	public boolean canConnect(int pos)
	{
		if (visited[pos])
			return false;
		int lastPos = order.charAt(length() - 1) - '0';
		int[][] paths = available[lastPos];
		for (int[] path : paths) {
			boolean blocked = false;
			for (int p = 0; !blocked && p < path.length; p++) {
				if (path[p] == pos)
					return true;
				if (!visited[path[p]])
					blocked = true;
			}
		}
		return false;
	}

	public static void main(final String[] args) throws IOException
	{
		ArrayList<PhoneSwipe> list = generateAll();
		PrintWriter prw = new PrintWriter(new FileWriter("list.txt"));
		for (PhoneSwipe pattern : list)
			prw.println(pattern);
		prw.close();
	}

	public static ArrayList<PhoneSwipe> generateAll()
	{
		ArrayList<PhoneSwipe> list = new ArrayList<PhoneSwipe>();
		for (int firstPos = 1; firstPos <= 9; firstPos++)
			list.addAll(generate(new PhoneSwipe("" + firstPos)));
		Collections.sort(list);
		return list;
	}

	private static ArrayList<PhoneSwipe> generate(PhoneSwipe orig)
	{
		ArrayList<PhoneSwipe> list = new ArrayList<PhoneSwipe>();
		if (orig.length() >= 4)
			list.add(orig);
		if (orig.length() == 9)
			return list;
		for (int nextPos = 1; nextPos <= 9; nextPos++)
			if (orig.canConnect(nextPos))
				list.addAll(generate(new PhoneSwipe(orig.toString() + nextPos)));
		return list;
	}
}
