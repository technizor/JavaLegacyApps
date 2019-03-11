import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5
{
	public static void main(final String[] args) throws IOException
	{
		boolean debug = true;
		BufferedReader reader = new BufferedReader(debug ? new FileReader(
				new File("data5.txt")) : new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			String[] line = reader.readLine().split(" ");
			x[i] = Integer.parseInt(line[0]);
			y[i] = Integer.parseInt(line[1]);
		}
		reader.close();

		int[][] dist = new int[n][n];
		for (int p = 0; p < n - 1; p++)
			for (int q = p + 1; q < n; q++) {
				if (p != q) {
					int dx = x[p] - x[q];
					int dy = y[p] - y[q];
					dist[p][q] = dist[q][p] = dx * dx + dy * dy;
				}
			}

		int[][] candy = new int[n][n];
		int max = 0;
		for (int s = 0; s < n; s++) {
			int val = generate(dist, candy, s, x[s] * x[s] + y[s] * y[s]);
			if (val > max)
				max = val;
		}
		System.out.println(max);
	}

	public static int generate(int[][] dist, int[][] candy, int s, int m)
	{
		int max = 0;
		for (int t = dist.length - 1; t >= 0; t--) {
			if (dist[s][t] < m) {
				if (candy[s][t] == 0 && s != t) {
					candy[s][t] = generate(dist, candy, t, dist[s][t]);
				}
				if (candy[s][t] > max)
					max = candy[s][t];
			}
		}
		return max + 1;
	}
}
