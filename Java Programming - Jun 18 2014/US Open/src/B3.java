import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class B3
{
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "photo";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN+ ".in")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File(fileN +".out")));
		String[] info = reader.readLine().split(" ");
		int cowNum = Integer.parseInt(info[0]);
		int breakNum = Integer.parseInt(info[1]);
		List<BreakPoint> breaks = new ArrayList<BreakPoint>(breakNum);
		for(int b = 0; b < breakNum; b++)
		{
			String[] nums = reader.readLine().split(" ");
			BreakPoint br = new BreakPoint(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
			breaks.add(-Collections.binarySearch(breaks, br)-1, br);
		}
		
		int photos = 1 + separate(breaks);
		writer.println(photos);
		writer.flush();
		writer.close();
	}

	private static int separate(List<BreakPoint> breaks)
	{
		int maxStart = 0;
		int minEnd = Integer.MAX_VALUE;
		for(BreakPoint bp : breaks)
		{
			if(maxStart < bp.start)
				maxStart = bp.start;
			if(minEnd > bp.end)
				minEnd = bp.end;
		}
		if(maxStart < minEnd)
			return 1;
		BreakPoint bs = breaks.get(0);
		BreakPoint endPoint = new BreakPoint(bs.end, bs.end);
		int index = -Collections.binarySearch(breaks, endPoint)-1;
		return separate(breaks.subList(0, index)) + separate(breaks.subList(index, breaks.size()));
	}
}

class BreakPoint implements Comparable<BreakPoint>
{
	int start;
	int end;
	BreakPoint(int first, int second)
	{
		start = Math.min(first,second);
		end = Math.max(first, second);
	}
	public int compareTo(BreakPoint other)
	{
		if(other.start != start)
			return start - other.start;
		return end- other.end;
	}
	
}