import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class B3
{
	public static Cow[] cowList;
	public static int[] assignedCows;
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "assign.in";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN)));
		String[] header = reader.readLine().split(" ");
		int cowN = Integer.parseInt(header[0]);
		int relationN = Integer.parseInt(header[1]);
		cowList = new Cow[cowN];
		assignedCows = new int[cowN];
		for(int i = 0; i < cowN; i++)
		{
			cowList[i] = new Cow(cowN, i);
		}
		for(int i = 0; i < relationN; i++)
		{
			String[] line = reader.readLine().split(" ");
			boolean relation = line[0].indexOf('S') != -1;
			int cow1 = Integer.parseInt(line[1])-1;
			int cow2 = Integer.parseInt(line[2])-1;
			if(relation)
			{
				cowList[cow1].same.add(cowList[cow2]);
				cowList[cow2].same.add(cowList[cow1]);
			}
			else
			{
				cowList[cow1].diff.add(cowList[cow2]);
				cowList[cow2].diff.add(cowList[cow1]);
			}
		}
		reader.close();
		int combos = process(0);
		
		PrintWriter writer = new PrintWriter(new FileWriter(new File("assign.out")));
		writer.print(combos);
		writer.flush();
		writer.close();
	}

	private static int process(int cowN)
	{
		if(cowN == assignedCows.length)
			return 1;
		int combos = 0;
		assignedCows[cowN] = 1;
		if(possible(cowN, cowList, assignedCows))
			combos += process(cowN+1);
		assignedCows[cowN] = 2;
		if(possible(cowN, cowList, assignedCows))
			combos += process(cowN+1);
		assignedCows[cowN] = 3;
		if(possible(cowN, cowList, assignedCows))
			combos += process(cowN+1);
		assignedCows[cowN] = 0;
		return combos;
	}

	private static boolean possible(int cowN, Cow[] cowList, int[] assignedCows)
	{
		Cow curCow = cowList[cowN];
		ArrayList<Cow> used = new ArrayList<Cow>();
		PriorityQueue<Cow> queue = new PriorityQueue<Cow>();
		// Same
		for(Cow cow : curCow.same)
		{
			int bin = java.util.Collections.binarySearch(used, cow);
			if(bin < 0 && cow.id < curCow.id)
			{
				queue.add(cow);
				used.add(-bin-1, cow);
			}
		}
		while(!queue.isEmpty())
		{
			Cow next = queue.poll();
			if(assignedCows[next.id] != assignedCows[curCow.id])
				return false;
			for(Cow cow : next.same)
			{
				int bin = java.util.Collections.binarySearch(used, cow);
				if(bin < 0 && cow.id < curCow.id)
				{
					queue.add(cow);
					used.add(-bin-1, cow);
				}
			}
		}

		// Diff
		used = new ArrayList<Cow>();
		queue = new PriorityQueue<Cow>();
		for(Cow cow : curCow.diff)
		{
			int bin = java.util.Collections.binarySearch(used, cow);
			if(bin < 0 && cow.id < curCow.id)
			{
				queue.add(cow);
				used.add(-bin-1, cow);
			}
		}
		while(!queue.isEmpty())
		{
			Cow next = queue.poll();
			if(assignedCows[next.id] == assignedCows[curCow.id])
				return false;
			/*for(Cow cow : next.diff)
			{
				int bin = java.util.Collections.binarySearch(used, cow);
				if(bin < 0 && cow.id < curCow.id)
				{
					queue.add(cow);
					used.add(-bin-1, cow);
				}
			}*/
		}
		return true;
	}
	
}
class Cow implements Comparable<Cow>
{
	int id;
	ArrayList<Cow> same;
	ArrayList<Cow> diff;
	public Cow(int cowN, int id)
	{
		this.id = id;
		same = new ArrayList<Cow>(cowN);
		diff = new ArrayList<Cow>(cowN);
	}
	public boolean equals(Object obj)
	{
		if(obj.getClass() != this.getClass())
			return false;
		Cow cow = (Cow) obj;
		return cow.id == this.id;
	}
	public int compareTo(Cow other)
	{
		return this.id - other.id;
	}
	public String toString()
	{
		return "" + id;
	}
}