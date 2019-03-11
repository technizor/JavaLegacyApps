import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Prob3
{
	private static final int PROB = 3;
	private static final int ATTEMPT = 0;
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(String.format("data%d%d.txt", PROB, ATTEMPT)));
		PrintWriter writer = new PrintWriter(new FileWriter(String.format("out%d%d.txt", PROB, ATTEMPT)));
		int noCases = Integer.parseInt(reader.readLine());
		for (int testCase = 1; testCase <= noCases; testCase++) {
			String output = "";
			writer.printf("Case #%d: %s%n", testCase, output);
		}
		writer.close();
		reader.close();
	}
}
