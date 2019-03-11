package dwiteC3;
/*import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q2
{
	public static void main(String[] args)
	{
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader(new File("c3/DATA1.TXT")));
			writer = new PrintWriter(new FileWriter(new File("c3/OUT1.TXT")));
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				int n = new Scanner(scan.nextLine()).nextInt();
				ArrayList<Integer> multipliers = new ArrayList<Integer>();
				String multiLine = scan.nextLine();
				String[] nums = multiLine.split(" ");
				for(String str : nums) multipliers.add(Integer.parseInt(str));
				if(n == 1) {
					writer.println(1);
					writer.flush();
					continue;
				}
				
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
	public static int getStart(ArrayList<Integer> multipliers) {
		
	}
	public static int calc(ArrayList<Integer> multipliers, int index, int remaining) {
		if()
		return calc(multipliers, index-1, remaining);
	}
}
a = 5 b = 2 c 4 d 6
(((n*a-m)*b-o)*c-p)*d
x = ((((y*a)*b)*c)*d)*/