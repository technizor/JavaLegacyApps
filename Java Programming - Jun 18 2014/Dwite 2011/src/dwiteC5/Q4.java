package dwiteC5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q4
{
	private static String[] map = new String[10];
	
	public static void main(final String[] args)
	{
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("c5/DATA4.TXT"));
			writer = new PrintWriter(new FileWriter("c5/OUT4.TXT"));
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
				map = new String[10];
				for(int j = 0; j < 10; j++) {
					map[j] = scan.nextLine();
				}
				writer.println(getTime(0));
				writer.flush();
				scan.nextLine();
			}
		}
	}
	public static int getTime(int steps)
	{
		boolean goOn = false;
		String[] mapClone = new String[10];
		for(int i = 0; i < 10; i++) {
			mapClone[i] = "" + map[i];
			if(map[i].indexOf('T') != -1) goOn = true;
		}
		if(goOn != true) {
			return steps;
		}
		boolean changed = false;
		for(int i = 0; i < 10; i++) {
			if(map[i].indexOf('F') == -1) continue;
			for(int j = 0; j < 10; j++) {
				if(map[i].charAt(j) != 'F') continue;
				int index = j;
				if(i > 0) {
					if(map[i-1].charAt(index) == 'T') {
						char[] temp1 = mapClone[i-1].toCharArray();
						temp1[index] = 'F';
						mapClone[i-1] =  new String(temp1);
						changed = true;
					}
				}
				if(index > 0) {
					if(map[i].charAt(index-1) == 'T') {
						char[] temp1 = mapClone[i].toCharArray();
						temp1[index-1] = 'F';
						mapClone[i] =  new String(temp1);
						changed = true;
					}
				}
				if(i < 9) {
					if(map[i+1].charAt(index) == 'T') {
						char[] temp1 = mapClone[i+1].toCharArray();
						temp1[index] = 'F';
						mapClone[i+1] =  new String(temp1);
						changed = true;
					}
				}
				if(index < 9) {
					if(map[i].charAt(index+1) == 'T') {
						char[] temp1 = mapClone[i].toCharArray();
						temp1[index+1] = 'F';
						mapClone[i] =  new String(temp1);
						changed = true;
					}
				}
			}
		}
		if(changed) {
			map = mapClone;
			return getTime(steps+1);
		} else {
			return -1;
		}
	}
}
