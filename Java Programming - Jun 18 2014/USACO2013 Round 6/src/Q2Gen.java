import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q2Gen
{
	public static void main(final String[] args) throws IOException
	{
		for (int test = 0; test < 50; test++) {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(
					"lazy.in")));
			String str = String.format("%d %d", 400,
					(int) (Math.random() * 801));
			writer.println(str);
			for (int r = 0; r < 400; r++) {
				StringBuilder strb = new StringBuilder();
				for (int c = 0; c < 400; c++)
					strb.append(String.format("%d ",
							(int) (Math.random() * 1001)));
				writer.println(strb.toString());
			}
			writer.close();
			System.out.printf(str + ": ");

			long start = System.nanoTime();
			Q1.main(null);
			long end = System.nanoTime();
			BufferedReader reader = new BufferedReader(new FileReader(new File("lazy.out")));
			System.out.printf("%.3fms %s%n", (end - start) / 1000000.0,reader.readLine());
			reader.close();
		}
	}
}
