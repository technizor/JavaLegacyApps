import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class S2
{
	public static void main(final String[] args) throws IOException
	{
		boolean debug = true;
		BufferedReader reader = new BufferedReader(debug ? new FileReader(
				new File("data2.txt")) : new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] a = reader.readLine().split(" ");
		String[] b = reader.readLine().split(" ");
		reader.close();
		if (valid(n, a, b))
			System.out.println("good");
		else
			System.out.println("bad");
	}

	public static boolean valid(int n, String[] a, String[] b)
	{
		if (n % 2 == 1)
			return false;
		ArrayList<String> pairs = new ArrayList<String>(n);
		for (int i = 0; i < n; i++) {
			String p = a[i].compareTo(b[i]) < 0 ? a[i] + b[i] : b[i] + a[i];
			int pi = Collections.binarySearch(pairs, p);
			if (pi < 0)
				pairs.add(-pi - 1, p);
		}
		if (pairs.size() == n / 2)
			return true;
		return false;
	}

}
