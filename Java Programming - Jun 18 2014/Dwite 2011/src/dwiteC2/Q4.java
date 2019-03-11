package dwiteC2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q4 {
	public static ArrayList<ArrayList<Integer>> caveConnect;
	
	public static void main(String[] args) {
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader("c2/DATA4.TXT"));
			writer = new PrintWriter(new FileWriter("c2/OUT4.TXT"));
			for(int i = 0; i < 5; i++) {
				int n = 0;
				String line = reader.readLine().trim();
				n = Integer.parseInt(line);
				caveConnect = new ArrayList<ArrayList<Integer>>();
				for(int j = 0; j < n; j++) {
					caveConnect.add(new ArrayList<Integer>());
				}
				for(int j = 0; j < n-1; j++) {
					String line2 = reader.readLine().trim();
					Scanner scan = new Scanner(line2);
					int a = scan.nextInt();
					int b = scan.nextInt();
					caveConnect.get(a).add(b);
				}
				int output = getMaxConnections(0);
				writer.println(output);
				writer.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getMaxConnections(int caveNum)
	{
		ArrayList<Integer> cave = caveConnect.get(caveNum);
		if(cave.size() > 0) {
			if(cave.size() == 1) {
				return getMaxConnections(cave.get(0));
			}
			int[] sizes = new int[cave.size()];
			int max = 0;
			int min = 0;
			for(int i = 0; i < cave.size(); i++) {
				sizes[i] = getMaxConnections(cave.get(i));
			}
			for(int i = 0; i < cave.size(); i++) {
				max = sizes[max] >= sizes[i] ? max : i;
				max = sizes[min] <= sizes[i] ? min : i;
			}
			return Math.max(sizes[max], sizes[min] + cave.size() - 1);
		}
		return 0;
	}
}
