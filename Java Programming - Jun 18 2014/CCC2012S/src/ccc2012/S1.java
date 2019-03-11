package ccc2012;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class S1
{
	private int jersey;
	public static void main(final String[] args) throws IOException
	{
		new S1();
	}
	public S1() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("s1.in")));
		String line = reader.readLine();
		while(line != null) {
			jersey = Integer.parseInt(line);
			long combos =  iterate();
			System.out.println("Total Combinations (" + jersey + "): " + combos);
			line = reader.readLine();
		}
	}
	private long iterate()
	{
		long count = 0;
		for(int i = 1; i < jersey-2; i++) {
			for(int j = i+1; j < jersey-1; j++) {
				count += jersey-(j+1);
			}
		}
		return count;
	}
}
