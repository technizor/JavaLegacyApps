package dwiteC4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q1
{
	public static void main(final String[] args) throws Exception
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("c4/DATA1.TXT")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("c4/OUT1.TXT")));
		Scanner scan = new Scanner(reader);
		for(int i = 0; i < 5; i++)
		{
			String line1 = scan.nextLine();
			Scanner temp = new Scanner(line1);
			int h = temp.nextInt();
			int w = temp.nextInt();
			int across = 0;
			int down = 0;
			boolean[][] crossword = new boolean[h][w];//# = 1, . = 0
			for(int j = 0; j < h; j++)
			{
				char[] chars = scan.nextLine().toCharArray();
				for(int k = 0; k < w; k++) crossword[j][k] = (chars[k] == '#');
			}
			for(int q = 0; q < h; q++)
			{
				int length = 0;
				for(int p = 0; p < w; p++)
				{
					if(crossword[q][p]) {
						if(length > 1) across++;
						length = 0;
					} else {
						length++;
					}
				}
				if(length > 1) across++;
			}
			for(int q = 0; q < w; q++)
			{
				int length = 0;
				for(int p = 0; p < h; p++)
				{
					if(crossword[p][q]) {
						if(length > 1) down++;
						length = 0;
					} else {
						length++;
					}
				}
				if(length > 1) down++;
			}
			writer.println(across + " " + down);
			writer.flush();
		}
	}
}
