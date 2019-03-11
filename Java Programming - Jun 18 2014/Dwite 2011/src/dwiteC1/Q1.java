package dwiteC1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Q1
{
	public static void main(final String[] args)
	{
		BufferedReader inputStr = null;
		BufferedWriter outputStr = null;
		ArrayList<String>[] stringList = new ArrayList[5];
		String[] outputStrings = new String[5];
		try {
			inputStr = new BufferedReader(new FileReader("c1/data1.txt"));
			outputStr = new BufferedWriter(new FileWriter("c1/out1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inputStr != null && outputStr != null) {
			String line = "";
			try {
				for(int i = 0; i < 5; i++) {
					stringList[i] = new ArrayList<String>();
					outputStrings[i] = new String();
					 line = inputStr.readLine();
					 String[] list = line.split(" ");
					 for(String s : list) {
						 stringList[i].add(0, s);
					 }
					 for(int j = 0; j < stringList[i].size(); j++) {
						 String str = stringList[i].get(j);
						 try {
							 int n = Integer.parseInt(str.toString());
							 outputStrings[i] += str.toString();
						 } catch (NumberFormatException nfe) {
							 char[] chars = str.toString().toCharArray();
							 for(int k = chars.length-1; k >= 0; k--) {
								 outputStrings[i] += chars[k];
							 }
						 }
						 outputStrings[i] += " ";
					 }
					 outputStrings[i] = outputStrings[i].substring(0, outputStrings[i].length()-1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String output = "";
			for(int i = 0; i < 5; i++){
				output += outputStrings[i] + "\n";
			}
			System.out.println(output);
			try {
				for(int i = 0; i < 5; i++){
					outputStr.write(outputStrings[i], 0, outputStrings[i].length());
					outputStr.newLine();
				}
				outputStr.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Failed!");
			}
		}
	}
}
