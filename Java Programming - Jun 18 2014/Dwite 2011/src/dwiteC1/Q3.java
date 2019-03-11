package dwiteC1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Q3
{
	public static void main(final String[] args)
	{
		BufferedReader inputStr = null;
		BufferedWriter outputStr = null;
		try {
			 inputStr = new BufferedReader(new FileReader("c1/data5.txt"));
			 outputStr = new BufferedWriter(new FileWriter("c1/out5.txt"));
			 for(int i = 0; i < 5; i++) {
				 int n = Integer.parseInt(inputStr.readLine());
				 ArrayList<int[]> stringList = new ArrayList<int[]>();
				 int[] point = new int[2];
				 for(int j = 0; j <= n; j++) {
					 if(n != j) {
						String[] temp = inputStr.readLine().split(" ");
						int[] temp2 = new int[2];
						temp2[0] = Integer.parseInt(temp[0]);
						temp2[1] = Integer.parseInt(temp[1]);
						stringList.add(temp2);
					 } else {
						String[] temp = inputStr.readLine().split(" ");
						point[0] = Integer.parseInt(temp[0]);
						point[1] = Integer.parseInt(temp[1]);
					 }
				 }
				 
			 }
			 outputStr.flush();
		} catch (Exception e) {}
	}
}
