import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Q2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"gpsduel.in")));
		String[] n_m = reader.readLine().split(" ");
		int n = Integer.parseInt(n_m[0]);
		int m = Integer.parseInt(n_m[1]);
		Intersection[] map = new Intersection[n + 1];
		for (int i = 0; i <= n; i++)
			map[i] = new Intersection();
		for (int r = 0; r < m; r++) {
			String[] a_b_p_q = reader.readLine().split(" ");
			int a = Integer.parseInt(a_b_p_q[0]);
			int b = Integer.parseInt(a_b_p_q[1]);
			int p = Integer.parseInt(a_b_p_q[2]);
			int q = Integer.parseInt(a_b_p_q[3]);
			map[a].addRoad(b, p, q);
		}
		reader.close();
		int answer = calculate(map);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"gpsduel.out")));
		writer.println(answer);
		writer.close();
	}

	private static int calculate(Intersection[] map)
	{
		int n = map.length - 1;
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<Route> buffer = new PriorityQueue<Route>();
		visited[1] = true;
		ArrayList<Road> initial = map[1].getRoads();
		for(Road road : initial)
		{
			buffer.add(new Route(road.b, road.getComplaints(map[1].minTime)));
		}
		while(!buffer.isEmpty())
		{
			Route route = buffer.remove();
			int target = route.target;
			if(target == n)
				return route.getComplaints();
			if(!visited[target])
			{
				visited[target] = true;
				ArrayList<Road> additions = map[target].getRoads();
				for(Road road : additions)
				{
					buffer.add(new Route(road.b, road.getComplaints(map[target].minTime)+route.getComplaints()));
				}
			}
		}
		return -1;
	}
}
class Route implements Comparable<Route>
{
	int totalComplaints;
	int target;
	Route(int target, int totalComplaints)
	{
		this.target = target;
		this.totalComplaints = totalComplaints;
	}
	@Override
	public int compareTo(Route o)
	{
		return totalComplaints-o.totalComplaints;
	}
	public int getComplaints()
	{
		return totalComplaints;
	}
	public int getTarget()
	{
		return target;
	}
	
}

class Intersection
{
	ArrayList<Road> roads;
	int[] minTime;

	Intersection()
	{
		roads = new ArrayList<Road>();
		minTime = new int[2];
		minTime[0] = Integer.MAX_VALUE;
		minTime[1] = Integer.MAX_VALUE;
	}

	void addRoad(int b, int p, int q)
	{
		Road road = new Road(b, p, q);
		int index = Collections.binarySearch(roads, road);
		roads.add(-index - 1, road);
		if (minTime[0] > p)
			minTime[0] = p;
		if (minTime[1] > q)
			minTime[1] = q;
	}

	ArrayList<Road> getRoads()
	{
		return roads;
	}
}

class Road implements Comparable<Road>
{
	int b;
	int p;
	int q;

	Road(int b, int p, int q)
	{
		this.b = b;
		this.p = p;
		this.q = q;
	}

	public int compareTo(Road other)
	{
		return b - other.b;
	}
	public int getComplaints(int[] shortest)
	{
		int i = p > shortest[0] ? 1 : 0;
		i += q > shortest[1] ? 1:0;
		return i;
	}
}