/*
ID: idiotio1
LANG: JAVA
TASK: gift1
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class gift1
{
	public static void main (final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader (new FileReader(new File("gift1.in")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("gift1.out")));
		int numPeople = Integer.parseInt(reader.readLine());
		String[] names = new String[numPeople];
		int[] netChange = new int[numPeople];
		for(int person = 0; person < numPeople; person++)
		{
			names[person] = reader.readLine();
		}
		for(int c = 0; c < numPeople; c++)
		{
			String curName = reader.readLine();
			StringTokenizer nums = new StringTokenizer(reader.readLine());
			int curNum = -1;
			for(int id = 0; id < numPeople && curNum == -1; id++)
				if(curName.equals(names[id]))
					curNum = id;
			int origM = Integer.parseInt(nums.nextToken());
			int splitN = Integer.parseInt(nums.nextToken());
			if(splitN != 0)
			{
				int splitM = origM/splitN;
				for(int id = 0; id < splitN; id++)
				{
					String gName = reader.readLine();
					int gNum = -1;
					for(int g = 0; g < numPeople && gNum == -1; g++)
						if(gName.equals(names[g]))
							gNum = g;
					netChange[curNum] -= splitM;
					netChange[gNum] += splitM;
				}
			}
		}
		reader.close();
		for(int person = 0; person < numPeople; person++)
		{
			writer.println(names[person] + " " + netChange[person]);
		}
		writer.flush();
		writer.close();
	}
}
