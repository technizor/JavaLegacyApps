import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class B1
{
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "cowrace.in";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN)));
		String[] segments = reader.readLine().split(" ");
		int m = Integer.parseInt(segments[0]);
		int n = Integer.parseInt(segments[1]);
		int[][] mSegments = new int[m][2];
		int[][] nSegments = new int[n][2];
		
		int totalTime = 0;
		for(int segmentM = 0; segmentM < m; segmentM++)
		{
			String[] data = reader.readLine().split(" ");
			mSegments[segmentM][0] = Integer.parseInt(data[0]);
			mSegments[segmentM][1] = Integer.parseInt(data[1]);
			totalTime += mSegments[segmentM][1];
		}
		for(int segmentN = 0; segmentN < n; segmentN++)
		{
			String[] data = reader.readLine().split(" ");
			nSegments[segmentN][0] = Integer.parseInt(data[0]);
			nSegments[segmentN][1] = Integer.parseInt(data[1]);
		}
		reader.close();
		int[][] timeGraph = new int[totalTime+1][2];
		int pos = 1;
		for(int ms = 0; ms < m; ms++)
		{
			int speed = mSegments[ms][0];
			int time = mSegments[ms][1];
			for(int mp = 0; mp < time; mp++, pos++)
				timeGraph[pos][0] = timeGraph[pos-1][0]+speed;
		}
		
		pos = 1;
		int lastAhead = -1;
		int lastBehind = -1;
		int changes = 0;
		for(int ns = 0; ns < n; ns++)
		{
			int speed = nSegments[ns][0];
			int time = nSegments[ns][1];
			for(int np = 0; np < time; np++, pos++)
			{
				timeGraph[pos][1] = timeGraph[pos-1][1]+speed;
				if(lastAhead == -1 && timeGraph[pos][0] != timeGraph[pos][1])
				{
					lastAhead = timeGraph[pos][0] > timeGraph[pos][1] ? 0 : 1;
					lastBehind = (lastAhead+1)%2;
				} else if(timeGraph[pos][lastAhead] < timeGraph[pos][lastBehind])
				{
					lastAhead = (lastAhead+1)%2;
					lastBehind = (lastBehind+1)%2;
					changes++;
				}
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter(new File("cowrace.out")));
		writer.print(changes);
		writer.flush();
		writer.close();
	}
}
