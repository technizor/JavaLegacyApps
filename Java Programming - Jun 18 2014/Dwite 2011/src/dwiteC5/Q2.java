package dwiteC5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q2
{
	public static void main(final String[] args)
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("c5/DATA2.TXT"));
			writer = new PrintWriter(new FileWriter("c5/OUT2.TXT"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(reader != null && writer != null) {
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				int num = Integer.parseInt(scan.nextLine());
				int count = 0;
				for(int x = 1; x <= num; x++) {
					for(int y = x; x*y <= num; y++) {
						count++;
					}
				}
				writer.println(count);
				writer.flush();
			}
		}
	}
}
