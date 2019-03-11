import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;


public class B4
{
	static int[][] friends;
	static int lowest;
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "haywire";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN+ ".in")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File(fileN +".out")));
		int cowNum = Integer.parseInt(reader.readLine());
		friends = new int[cowNum+1][3];
		for(int n = 1; n <= cowNum; n++)
		{
			String[] nums = reader.readLine().split(" ");
			friends[n][0] = Integer.parseInt(nums[0]);
			friends[n][1] = Integer.parseInt(nums[1]);
			friends[n][2] = Integer.parseInt(nums[2]);
		}
		reader.close();
		lowest = Integer.MAX_VALUE;
		attempt(new int[cowNum], new boolean[cowNum+1], 0);
		writer.println(lowest);
		writer.flush();
		writer.close();
	}

	private static void attempt(int[] order, boolean[] used, int pos)
	{
		if(pos == order.length)
		{
			int total = 0;
			for(int p = 0; p < order.length-1; p++)
			{
				int cowN = order[p];
				for(int q = p+1; q < order.length; q++)
					if(order[q] == friends[cowN][0] || order[q] == friends[cowN][1] || order[q] == friends[cowN][2])
						total+= q-p;
			}
			if(lowest > total)
			{
				/*System.out.print("New Low: ");
				for(int n : order)
					System.out.print(n + " ");
				System.out.println();*/
				lowest = total;
			}
			return;
		}
		for(int next = 1; next < used.length; next++)
		{
			if(!used[next])
			{
				used[next] = true;
				order[pos] = next;
				attempt(order, used, pos+1);
				used[next] = false;
			}
		}
	}
}