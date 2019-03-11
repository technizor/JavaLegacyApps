/*
ID: idiotio1
LANG: JAVA
TASK: ride
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ride
{
	public static void main (final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader (new FileReader(new File("ride.in")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("ride.out")));
		String ufo = reader.readLine();
		String comet = reader.readLine();
		reader.close();
		int ufoNum = 1;
		for(int pos = 0; pos < ufo.length(); pos++)
		{
			ufoNum*= ufo.charAt(pos)-64;
		}
		int cometNum = 1;
		for(int pos = 0; pos < comet.length(); pos++)
		{
			cometNum*= comet.charAt(pos)-64;
		}
		if(ufoNum%47 == cometNum % 47)
			writer.println("GO");
		else
			writer.println("STAY");
		writer.flush();
		writer.close();
		System.exit(0);
		
	}
}
