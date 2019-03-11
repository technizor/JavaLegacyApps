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
	private static final int ATTEMPT = 0;

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(
				String.format("data%d%d.txt", PROB, ATTEMPT)));
		PrintWriter writer = new PrintWriter(new FileWriter(String.format(
				"out%d%d.txt", PROB, ATTEMPT)));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			int n = Integer.parseInt(reader.readLine());
			int[][] grid = new int[n + 1][n + 1];
			while (grid[n][0] == 0) {
				String[] x_y = reader.readLine().split(" ");
				int x = Integer.parseInt(x_y[0]);
				int y = Integer.parseInt(x_y[1]);
				int i = 1;
				while (grid[i][0] != x && grid[i][0] != 0 && i <= n)
					i++;
				grid[i][0] = x;
				grid[0][i] = x;
				int j = 1;
				while (grid[j][0] != y && grid[j][0] != 0 && j <= n)
					j++;
				grid[j][0] = y;
				grid[0][j] = y;
				grid[i][j] = 1;
				grid[j][i] = 1;
			}
			int min = Integer.MAX_VALUE;
			for (int root = 1; root <= n; root++) {
				int val = calc(grid, root);
				if (val < min)
					min = val;
			}
			String output = "" + min;

			writer.printf("Case #%d: %s%n", testCase, output);
		}
		writer.close();
		reader.close();
	}

	private static int calc(int[][] grid, int root)
	{
		boolean[] visited = new boolean[grid.length];
		Queue<Integer> remaining = new LinkedList<Integer>();
		Node[] nodes = new Node[grid.length];
		nodes[root] = new Node(grid[root][0]);
		visited[root] = true;
		for (int i = 1; i < grid.length; i++) {
			if (grid[root][i] == 1 && !visited[i]) {
				nodes[i] = new Node(grid[i][0]);
				nodes[root].add(nodes[i]);
				remaining.add(i);
			}
		}
		while (!remaining.isEmpty()) {
			int next = remaining.remove();
			visited[next] = true;
			for (int i = 1; i < grid.length; i++) {
				if (grid[next][i] == 1 && !visited[i]) {
					nodes[i] = new Node(grid[i][0]);
					nodes[next].add(nodes[i]);
				}
			}
		}
		Node n = nodes[root];
		int val = fix(n);
		return val;
	}

	private static int fix(Node n)
	{
		if (n.isBinary())
			return 0;
		Collections.sort(n.children);
		int count = 0;
		if (n.size() > 2) {
			for (int i = n.size() - 1; i >= 2; i--) {
				count += n.children.get(i).getCount();
				n.children.remove(i);
			}
		}
		if (count == 2) {
			count += fix(n.children.get(0));
			count += fix(n.children.get(1));
			return count;
		}
		count += n.children.get(0).getCount();
		n.children.remove(0);
		return count;
	}

	static class Node implements Comparable<Node>
	{
		Node parent;
		ArrayList<Node> children;
		int id;

		boolean isBinary()
		{
			if (children.isEmpty())
				return true;
			if (size() == 2) {
				boolean isValid = true;
				for (Node c : children)
					if (!c.isBinary())
						isValid = false;
				return isValid;
			}
			return false;
		}

		Node(int id)
		{
			this.id = id;
			children = new ArrayList<Node>();
		}

		void add(Node o)
		{
			int index = Collections.binarySearch(children, o);
			if (index < 0)
				index = -index - 1;
			children.add(index, o);
			o.parent = this;
		}

		boolean canRemove()
		{
			return children.isEmpty();
		}

		void remove()
		{
			if (canRemove() && parent != null) {
				int index = Collections.binarySearch(parent.children, this);
				parent.children.remove(index);
			}
		}

		int size()
		{
			return children.size();
		}

		public String toString()
		{
			StringBuilder strb = new StringBuilder();
			strb.append(id);
			if (children.size() > 0) {
				strb.append(":{");
				for (Node c : children)
					strb.append(c.id).append("/");
				strb.deleteCharAt(strb.length() - 1);
				strb.append("}");
			}
			return strb.toString();
		}

		@Override
		public int compareTo(Node o)
		{
			if (isBinary() == o.isBinary())
				return getCount() - o.getCount();
			if (isBinary())
				return -1;
			return 1;
		}

		public int getCount()
		{
			int count = 1;
			if (!children.isEmpty()) {
				for (Node c : children)
					count += c.getCount();
			}
			return count;
		}
	}
}
