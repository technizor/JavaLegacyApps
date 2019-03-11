import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class S5Gen
{
	public static void main(final String[] args) throws IOException
	{
		HashSet<String> points = new HashSet<String>(2500, 0.9F);
		while (points.size() < 2000) {
			int x = (int) (Math.random() * 20001) - 10000;
			int y = (int) (Math.random() * 20001) - 10000;
			points.add(String.format("%d %d", x, y));
		}
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"data5gen.txt")));
		writer.println("2000");
		for(String p : points)
			writer.println(p);
		writer.close();
	}
}
