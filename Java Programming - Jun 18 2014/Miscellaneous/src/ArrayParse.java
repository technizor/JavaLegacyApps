import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArrayParse
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String line[] = reader.readLine().split(", ");
		reader.close();
		
		StringBuilder strb = new StringBuilder();
		for(String str : line)
		{
			while(str.indexOf('[') != -1)
				str= str.substring(str.lastIndexOf('[')+1);
			if(str.indexOf(']') != -1)
			{
				str = str.substring(0,str.indexOf(']'));
				strb.append(str);
				strb.append('\n');
			} else
			{
				strb.append(str);
				strb.append('\t');
			}
		}
		System.out.println(strb);
	}
}
// [[0, -1, -1, -1, -1, -1, -1], [0, 4, 0, 0, 1, 0, 0], [0, 0, 8, 0, 0, 5, 0], [0, 0, 0, 8, 0, 0, 9]]