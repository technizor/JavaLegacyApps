import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q1Gen
{
	public static void main(final String[] args) throws IOException
	{
		for (int test = 0; test < 50; test++) {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(
					"irrigation.in")));
			String str = String.format("%d %d", 2000,
					(int) (Math.random() * 1000)+1);
			writer.println(str);
			for (int r = 0; r < 2000; r++) {
				String strl = String.format("%d %d", (int)(Math.random()*1001), (int)(Math.random()*1001));
				writer.println(strl);
			}
			writer.close();
			System.out.printf(str + ": ");

			long start = System.nanoTime();
			Q1.main(null);
			long end = System.nanoTime();
			BufferedReader reader = new BufferedReader(new FileReader(new File("irrigation.out")));
			System.out.printf("%.3fms %s%n", (end - start) / 1000000.0, reader.readLine());
			reader.close();
		}
	}
}
