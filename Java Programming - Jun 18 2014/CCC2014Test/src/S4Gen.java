import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class S4Gen
{
	public static void main(final String[] args) throws IOException
	{
		int t1 = 1000000;
		HashSet<String> points = new HashSet<String>(1250, 0.9F);
		while (points.size() < 1000) {
			int x1 = (int)(Math.random()*1000000000);
			int y1 = (int)(Math.random()*1000000000);
			int x2 = (int)(Math.random()*(1000000000-x1))+x1+1;
			int y2 = (int)(Math.random()*(1000000000-y1))+y1+1;
			int t = (int)(Math.random()*1000000);
			points.add(String.format("%d %d %d %d %d", x1, y1, x2, y2, t));
		}
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"data4gen.txt")));
		writer.println("1000");
		writer.println(t1);
		for(String p : points)
			writer.println(p);
		writer.close();
	}
}
