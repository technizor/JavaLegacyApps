package dwiteC4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class Q4
{
	public static void main(final String[] args) throws Exception
	{/*
		BufferedReader reader = new BufferedReader(new FileReader(new File("c4/DATA4.TXT")));*/
		PrintWriter writer = new PrintWriter(new FileWriter(new File("c4/OUT4.TXT")));
		//Scanner scan = new Scanner(reader);
		Random rand = new Random();
		for(int i = 0; i < 5; i++)
		{
			String str = "";
			for(int j = 0; j < 3; j++)
			{
				str += rand.nextBoolean() ? "A" : "B";
			}
			writer.println(str);
			writer.flush();
		}
	}
}
