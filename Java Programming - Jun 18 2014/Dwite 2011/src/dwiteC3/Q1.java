package dwiteC3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q1
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
				Scanner scan2 = new Scanner(scan.nextLine());
				int n = scan2.nextInt();
				ArrayList<String> itemList = new ArrayList<String>();
				for(int j = 0; j < 4; j++) {
					itemList.add(scan.nextLine());
				}
				ArrayList<Integer> itemSize = new ArrayList<Integer>();
				for(int j = 0; j < 4; j++) {
					itemSize.add(new Integer(itemList.get(j).substring(itemList.get(j).indexOf(' ')+1)));
				}
				boolean unfound = true;
				int outputItem = -1;
				for(int j = 0; unfound; j++) {
					if(itemSize.contains(new Integer(n+j))) {
						unfound = false;
						outputItem = itemSize.indexOf(n+j);
					} else if(itemSize.contains(new Integer(n-j))) {
						unfound = false;
						outputItem = itemSize.indexOf(n-j);
					}
				}
				writer.println(itemList.get(outputItem).substring(0, itemList.get(outputItem).indexOf(' ')));
				writer.flush();
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
}
