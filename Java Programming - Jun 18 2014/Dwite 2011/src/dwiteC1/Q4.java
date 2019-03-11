package dwiteC1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Q4
{
	public static void main(final String[] args)
	{
		BufferedReader inputStr = null;
		BufferedWriter outputStr = null;
		try {
			 inputStr = new BufferedReader(new FileReader("c1/data4.txt"));
			 outputStr = new BufferedWriter(new FileWriter("c1/out4.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(int i = 0; i < 5; i++) {
				int n = Integer.parseInt(inputStr.readLine());
				int coolness = 0;
				for(int j = 0; j <= n; j++) {
					String str = "" + j;
					for(char c : str.toCharArray()) {
						if(c == '0') coolness++;
					}
				}
				outputStr.write("" + coolness);
				outputStr.newLine();
			}
			outputStr.flush();
		} catch (Exception e) {}
	}
}
