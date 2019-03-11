import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class P2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("p2.in")));
		for(int i = 0; i < 5; i++) {
			String info = reader.readLine();
			Scanner scan1 = new Scanner(info);
			int n = scan1.nextInt();
			int c = scan1.nextInt();
			int p = scan1.nextInt();
			int[] stops = new int[n];
			for(int j = 0; j < p; j++) {
				int stopN = Integer.parseInt(reader.readLine())-1;
				stops[stopN]++;
			}
			int time = n-1;
			int busC = 0;
			for(int j = 0; j < n; j++) {
				int stopP = stops[j];
				int stopC = (stopP+busC > c) ? c - busC : stopP;
				busC += stopC;
				double stopT = (double)stopC/5;
				time += Math.ceil(stopT);
			}
			System.out.println(time);
		}
	}
}