package dwiteC5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q3
{
	public static void main(final String[] args)
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("c5/DATA3.TXT"));
			writer = new PrintWriter(new FileWriter("c5/OUT3.TXT"));
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
				int weight = getBinWeight(num);
				for(int x = num +1;; x++) {
					if(getBinWeight(x) == weight) {
						writer.println(x);
						break;
					}
				}
				writer.flush();
			}
		}
	}
	public static int getBinWeight(int num)
	{
		int weight = 0;
		while(num > 0) {
			weight += num % 2;
			num = num>>1;
		}
		return weight;
	}
}
