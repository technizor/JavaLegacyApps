import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;


public class B2
{
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "blink";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN+ ".in")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File(fileN +".out")));
		String[] nums = reader.readLine().split(" ");
		int bulbs = Integer.parseInt(nums[0]);
		long times = Long.parseLong(nums[1]);
		boolean[] startConfig = new boolean[bulbs];
		for(int b = 0; b < bulbs; b++)
		{
			if(reader.readLine().charAt(0) == '1')
				startConfig[b] = true;
		}
		BulbString lights = new BulbString(startConfig);
		while(times > 0 && !lights.nextMove())
			times--;
		int loopStart = lights.used.indexOf(lights.toString());
		int loopLength = lights.used.size()-loopStart;
		int finalLoopOffset = (int) (times % loopLength);
		int finalPos = loopStart + finalLoopOffset;
		String finalConfig = lights.used.get(finalPos);
		for(char c : finalConfig.toCharArray())
			writer.println(c);
		writer.flush();
		writer.close();
	}
}

class BulbString
{
	int length;
	boolean[] bulbs;
	ArrayList<String> used;
	BulbString(boolean[] startingConfig)
	{
		bulbs = startingConfig;
		length = bulbs.length;
		used = new ArrayList<String> (100);
		used.add(this.toString());
	}
	public String toString()
	{
		StringBuilder builder = new StringBuilder(bulbs.length);
		for(boolean b : bulbs)
			builder.append(b ? '1' : '0');
		return builder.toString();
	}
	// Return true if looping
	public boolean nextMove()
	{
		boolean[] newB = new boolean[length];
		for(int n = 0; n < length; n++)
		{
			if(bulbs[(n+length-1)%length])
				newB[n] = !bulbs[n];
			else
				newB[n] = bulbs[n];
		}
		bulbs = newB;
		String currentConfig = toString();
		if(used.contains(currentConfig))
			return true;
		used.add(currentConfig);
		return false;
	}
}
