import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q3Gen
{
	public static void main(final String[] args) throws IOException
	{
		for (int test = 0; test < 50; test++) {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(
					"mooomoo.in")));
			String str = String.format("%d %d", 100, 20);
			writer.println(str);
			for (int b = 0; b < 20; b++) {
				writer.println(String.format("%d", (int) (Math.random() * 101)));
			}
			int last = 0;
			for (int r = 0; r < 100; r++) {
				int limit = last == 0?100001 : 100001-last;
				last = (int) (Math.random() * limit)+last;
				writer.println(String.format("%d", last));
			}
			writer.close();
			System.out.printf(str + ": ");

			long start = System.nanoTime();
			Q3.main(null);
			long end = System.nanoTime();
			BufferedReader reader = new BufferedReader(new FileReader(new File("mooomoo.out")));
			System.out.printf("%.3fms %s%n", (end - start) / 1000000.0, reader.readLine());
			reader.close();
		}
	}
}
