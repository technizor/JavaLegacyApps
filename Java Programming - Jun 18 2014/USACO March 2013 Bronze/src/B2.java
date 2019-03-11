import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class B2
{
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "proximity.in";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN)));
		String[] inLine = reader.readLine().split(" ");
		int cowN = Integer.parseInt(inLine[0]);
		int prox = Integer.parseInt(inLine[1]);
		int[] cows = new int[cowN+prox];
		boolean[] crowds = new boolean[cowN];
		for(int lineP = 0; lineP < cowN; lineP++)
		{
			cows[lineP] = Integer.parseInt(reader.readLine());
		}
		reader.close();
		for(int cowP = 0; cowP < cowN; cowP++)
		{
			for(int relPos = 1; relPos <= prox; relPos++)
			{
				if(cows[cowP] == cows[cowP+relPos])
				{
					crowds[cowP] = crowds[cowP+relPos] = true;
				}
			}
		}
		int crowdedCows = 0;
		for(boolean crowded : crowds)
			if(crowded)
				crowdedCows++;
		
		PrintWriter writer = new PrintWriter(new FileWriter(new File("proximity.out")));
		writer.print(crowdedCows);
		writer.flush();
		writer.close();
	}
}
