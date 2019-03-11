import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class P1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("p1.in")));
		for(int iter = 0; iter < 5; iter++) {
			String line = reader.readLine();
			Scanner scanLine1 = new Scanner(line);
			Point pipeStart = new Point(scanLine1.nextInt(), scanLine1.nextInt());
			Point pipeEnd = new Point(scanLine1.nextInt(), scanLine1.nextInt());
		}
	}
}
