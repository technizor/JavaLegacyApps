package dwiteC4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q2
{
	
	protected static boolean[] primeCheck;
	
	protected static double fact (int num)
	{
		if (num == 1)
			return 1;
		else
			return num*fact(num - 1);
	}
	public static void main(final String[] args) throws Exception
	{
		long time = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader(new File("c4/DATA2.TXT")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("c4/OUT2.TXT")));
		Scanner scan = new Scanner(reader);/*
		primeCheck = new boolean[10001];
		for (int i = 2; i < 10001; i++)
			primeCheck[i] = true;*/
		/*for (int current = 2; current < 10001; current++)
		{/*
			if (primeCheck[current])
			{
				for (int mul = current*2; mul < 10001; mul += current)
					primeCheck[mul] = false;
			}
		}*/
		for(int i = 0; i < 5; i++)
		{
			double number = fact(scan.nextInt());
			
			boolean isFirst = true;
			int curr = 2, exp = 0;
			
			String output = "";
			
			while (curr < 10001)
			{
				exp = 0;/*
				if(primeCheck[curr])
				{*/
					while (number % curr == 0)
					{
						number /= curr;
						exp++;
						
					}
					if (exp > 0)
					{
						if (!isFirst)
							output += " * ";
						output += curr + "^" + exp;
						isFirst = false;
					}
				//}
				curr++;
			}
			writer.println(output);
			writer.flush();
		}
		long timeDur = System.currentTimeMillis()-time;
		System.out.println(timeDur);
		System.exit((int) timeDur);
	}
}
