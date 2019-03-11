import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q31
{
	public static void main(final String[] args) throws IOException
	{
		long start = System.nanoTime();
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"odometer.in")));
		String[] x_y = reader.readLine().split(" ");
		reader.close();
		
		Value x = new Value(x_y[0]);
		Value y = new Value(x_y[1]);
		ArrayList<Value> intValues = new ArrayList<Value>();
		for(int d = 0; d < 100; d++)
			intValues.add(new Value("" + d));
		for(int digits = 3, first = 0, last = intValues.size(); digits <= y.length(); digits++, first = last, last = intValues.size())
		{
			for(int n = first; n < last; n++)
			{
				String numBase = intValues.get(n).toString();
				for(int p = numBase.length(); p >= 0; p--)
				{
					for(int d = 0; d < 10; d++)
					{
						Value val = new Value(numBase.substring(0,p)+ d + numBase.substring(p));
						if(!val.isInteresting())
						{
							int index = Collections.binarySearch(intValues, val);
							if(index < 0)
								intValues.add(-index-1, val);
						}
					}
				}
			}
		}
		int index1 = Collections.binarySearch(intValues, x);
		int index2 = Collections.binarySearch(intValues, y);
		if(index1 < 0)
			index1 *=-1;
		if(index2 < 0)
			index2*=-1;
		else
			index2++;
		int answer = index2-index1;
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"odometer.out")));
		writer.println(answer);
		writer.close();
		long end = System.nanoTime();
		System.out.printf("%.2fms", (end-start)/1000000.0);
	}
}