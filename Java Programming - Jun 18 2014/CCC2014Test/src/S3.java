import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S3
{
	public static void main(final String[] args) throws IOException
	{
		boolean debug = true;
		BufferedReader reader = new BufferedReader(debug ? new FileReader(
				new File("data3.txt")) : new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int c = 0; c < t; c++) {
			int n = Integer.parseInt(reader.readLine());
			Stack<Integer> mountain = new Stack<Integer>();
			for (int i = 0; i < n; i++)
				mountain.push(Integer.parseInt(reader.readLine()));
			if (possible(mountain))
				System.out.println("Y");
			else
				System.out.println("N");
		}
		reader.close();
	}

	public static boolean possible(Stack<Integer> mountain)
	{
		int n = mountain.size();
		Stack<Integer> branch = new Stack<Integer>();
		int cur = 1;
		while (cur <= n) {
			if (!mountain.isEmpty() && mountain.peek().equals(cur)) {
				mountain.pop();
				cur++;
			} else if (!branch.isEmpty() && branch.peek().equals(cur)) {
				branch.pop();
				cur++;
			} else if (!mountain.isEmpty())
				branch.push(mountain.pop());
			else
				return false;
		}
		return true;
	}
}
