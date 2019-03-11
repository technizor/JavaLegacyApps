import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RandoChess
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"data41.txt")));
		ArrayList<Arrangement> valid = generateValid();
		for (int test = 0; test < 10; test++) {
			char[] format = reader.readLine().toCharArray();
			Arrangement part = new Arrangement(format);
			int n = 0;
			for(Arrangement arr : valid)
				if(part.equals(arr))
					n++;
			System.out.println(n);
		}
		reader.close();
	}

	private static ArrayList<Arrangement> generateValid()
	{
		ArrayList<Arrangement> valid = new ArrayList<Arrangement>();
		// place bishops, then rooks + king, then queen, then knights
		char[] format = { '_', '_', '_', '_', '_', '_', '_', '_' };
		for (int b1 = 0; b1 < 8; b1 += 2) {
			format[b1] = 'B';
			for (int b2 = 1; b2 < 8; b2 += 2) {
				format[b2] = 'B';
				for (int r1 = 0; r1 < 4; r1++) {
					for (int k = r1+1; k < 5; k++) {
						for (int r2 = k+1; r2 < 6; r2++) {
							for(int p = 0, a = 0; p < 8; p++)
							{
								if(format[p] == '_')
								{
									if(a == r1 || a==r2)
										format[p] = 'R';
									else if(a == k)
										format[p] = 'K';
									a++;
								}
							}
							for (int q = 0; q < 3; q++) {
								for(int p = 0, a = 0; p < 8; p++)
								{
									if(format[p] == '_')
									{
										if(a == q)
											format[p] = 'Q';
										else
											format[p] = 'N';
										a++;
									}
								}
								valid.add(new Arrangement(format));
								for(int p = 0; p < 8; p++)
								{
									if(format[p] == 'Q' || format[p] == 'N')
										format[p] = '_';
								}
							}
							for(int p = 0; p < 8; p++)
							{
								if(format[p] == 'R' || format[p] == 'K')
									format[p] = '_';
							}
						}
					}
				}
				format[b2] = '_';
			}
			format[b1] = '_';
		}
		return valid;
	}
}

class Arrangement
{
	char[] format;

	Arrangement(char[] format) throws IllegalArgumentException
	{/*
		if (format.length != 8)
			throw new IllegalArgumentException("Invalid size!");
		int n = 0;
		int k = 0;
		int q = 0;
		int r = 0;
		int b = 0;
		int bPos = -1;
		int rCount = 0;
		for (int pos = 0; pos < 8; pos++) {
			char ch = format[pos];
			if (ch == 'N') {
				n++;
				if (n > 2)
					throw new IllegalArgumentException("Too many Knights!");
			} else if (ch == 'K') {
				k++;
				rCount = 0;
				if (k > 1)
					throw new IllegalArgumentException("Too many Kings!");
			} else if (ch == 'Q') {
				q++;
				if (q > 1)
					throw new IllegalArgumentException("Too many Queens!");
			} else if (ch == 'R') {
				r++;
				rCount++;
				if (r > 2)
					throw new IllegalArgumentException("Too many Rooks!");
				if (rCount > 1)
					throw new IllegalArgumentException(
							"King must be between Rooks!");
			} else if (ch == 'B') {
				b++;
				if (b > 2)
					throw new IllegalArgumentException("Too many Bishops!");
				if (b == 1)
					bPos = pos;
				else if (bPos % 2 == pos % 2)
					throw new IllegalArgumentException(
							"Bishops must be on different colour tiles!");
			} else if (ch != '_')
				throw new IllegalArgumentException("Invalid piece!");
		}*/
		this.format = new char[8];
		for(int i = 0; i < 8; i++)
			this.format[i] = format[i];
	}

	public boolean equals(Object other)
	{
		if (other instanceof Arrangement) {
			Arrangement arr = (Arrangement) other;
			for (int pos = 0; pos < 8; pos++) {
				if (format[pos] != arr.format[pos] && format[pos] != '_'
						&& arr.format[pos] != '_')
					return false;
			}
			return true;
		}
		return false;
	}
	public String toString()
	{
		return new String(format);
	}
}