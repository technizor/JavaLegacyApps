package dwiteC1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Q5
{
	public static boolean isPal (String s)
	{
		for (int i = 0; i < s.length()/2; i++)
		{
			if (s.charAt(i) != s.charAt(s.length()-1-i))
				return false;
		}
		return true;
	}
	
	public static boolean isIndexPal (int i, String s)
	{
		if (s.charAt(i) == s.charAt(s.length()-1-i))
			return true;
		else if (i == s.length()-1)
			return false;
		else if (s.charAt(i+1) == s.charAt(i)) {
				return true;
		}
		return false;
	}
	
	public static String removeChar (String s, int index)
	{
		String newstring = "";
		for (int i = 0; i < s.length(); i++)
		{
			if (i < index)
				newstring += Character.toString(s.charAt(i));
			else if (i > index) {
				newstring += s.substring(index + 1);
				break;
			}
			
		}
		return newstring;
	}
	
	public static int palNum(String str) {
		if(isPal(str)) return str.length();
		for(int i = 0; i < str.length(); i++) {
			
		}
		
		
		
		return 0;
	}
	
	public static void main(final String[] args)
	{
		BufferedReader inputStr = null;
		BufferedWriter outputStr = null;
		try {
			 inputStr = new BufferedReader(new FileReader("c1/data5.txt"));
			 outputStr = new BufferedWriter(new FileWriter("c1/out5.txt"));
			 for (int curr = 1; curr <= 5; curr++)
			 {
				 String line = inputStr.readLine();
				 
				 int biggestl = line.length();
				 while (!isPal(line)){
					 if(biggestl <= 0) break;
					 for (int i = 0; i < line.length(); i++)
					 {
						 if (!isIndexPal(i, line)) {
							 if(i < line.length()) {
								 line = removeChar(line, i);
							 }
						 }
						 else {
							 int n = 0;
						 }
					 }
					 biggestl = line.length();
				 }
				 outputStr.write("" + biggestl);
				 outputStr.newLine();
			 }
			 outputStr.flush();
		} catch (Exception e) {}
	}
}
