package dwiteC5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q1
{
	public static void main(final String[] args)
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("c5/DATA1.TXT"));
			writer = new PrintWriter(new FileWriter("c5/OUT1.TXT"));
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
				String line = scan.nextLine();
				String[] temp = line.split(" ");
				int[] temp1 = {Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])};
				if(temp1[2] < 1997) {
					writer.println("old enough");
				} else if(temp1[2] > 1997) {
					writer.println("too young");
				} else {
					if(temp1[1] < 10) {
						writer.println("old enough");
					} else if(temp1[1] > 10) {
						writer.println("too young");
					} else {
						if(temp1[0] <= 27) {
							writer.println("old enough");
						} else {
							writer.println("too young");
						} 
					}
				}
				writer.flush();
			}
		}
	}
}
