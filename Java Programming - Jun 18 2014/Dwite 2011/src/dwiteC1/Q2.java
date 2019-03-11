package dwiteC1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Q2
{
	public static int lowestI(int[] numbers)
	{
		int lowest = 0;
		for (int i = 1; i < numbers.length; i++)
			if (numbers[lowest] > numbers[i])
				lowest = i;
		return lowest;
	}
	
	public static int highestI(int[] numbers)
	{
		int highest = 0;
		for (int i = 1; i < numbers.length; i++)
			if (numbers[highest] < numbers[i])
				highest = i;
		return highest;
	}
	
	public static boolean isEven(int[] numbers)
	{
		int first = numbers[0];
		for (int i = 1; i < numbers.length; i++)
			if (first != numbers[i])
				return false;
		return true;
	}
	
	public static void main(final String[] args)
	{
		BufferedReader inputStr = null;
		BufferedWriter outputStr = null;
		try {
			 inputStr = new BufferedReader(new FileReader("c1/data2.txt"));
			 outputStr = new BufferedWriter(new FileWriter("c1/out2.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for (int curr = 1; curr <= 5; curr++)
			{
				int arraysize = Integer.parseInt(inputStr.readLine());
				int[] numbers = new int[arraysize];
				for (int i = 0; i < arraysize; i++)
					numbers[i] = Integer.parseInt(inputStr.readLine());
				int steps = 0;
				
				while(!isEven(numbers))
				{
					steps++;
					numbers[highestI(numbers)] -= 1;
					numbers[lowestI(numbers)] += 1;
				}
				
				outputStr.write(steps);
				outputStr.newLine();
			}
			outputStr.flush();
		} catch (Exception e) {}
	}
}
