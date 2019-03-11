import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class Sol1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"combo.in")));
		String sizeStr = reader.readLine();
		String[] johnComboStr = reader.readLine().split(" ");
		String[] masterComboStr = reader.readLine().split(" ");
		reader.close();

		int n = Integer.parseInt(sizeStr);
		int[] jCom = new int[3];
		int[] mCom = new int[3];
		jCom[0] = Integer.parseInt(johnComboStr[0]);
		jCom[1] = Integer.parseInt(johnComboStr[1]);
		jCom[2] = Integer.parseInt(johnComboStr[2]);
		mCom[0] = Integer.parseInt(masterComboStr[0]);
		mCom[1] = Integer.parseInt(masterComboStr[1]);
		mCom[2] = Integer.parseInt(masterComboStr[2]);

		HashSet<Combination> combos = new HashSet<Combination>(251, 1.00F);
		for (int a = -2; a <= 2; a++)
			for (int b = -2; b <= 2; b++)
				for (int c = -2; c <= 2; c++) {
					int x = (jCom[0] + a + n) % n;
					int y = (jCom[1] + b + n) % n;
					int z = (jCom[2] + c + n) % n;
					combos.add(new Combination(x, y, z));

					int d = (mCom[0] + a + n) % n;
					int e = (mCom[1] + b + n) % n;
					int f = (mCom[2] + c + n) % n;
					combos.add(new Combination(d, e, f));
				}

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"combo.out")));
		writer.println(combos.size());
		writer.close();
	}
}

class Combination
{
	int a;
	int b;
	int c;

	Combination(int first, int second, int third)
	{
		a = first;
		b = second;
		c = third;
	}

	public int hashCode()
	{
		return a * 1000000 + b * 1000 + c;
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof Combination) {
			Combination other = (Combination) obj;
			if (a == other.a && b == other.b && c == other.c)
				return true;
		}
		return false;
	}

	public String toString()
	{
		return String.format("[%d,%d,%d]", a, b, c);
	}
}