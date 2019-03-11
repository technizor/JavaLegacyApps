import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q2
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"lazy.in")));
		String[] n_k = reader.readLine().split(" ");
		int n = Integer.parseInt(n_k[0]);
		int k = Integer.parseInt(n_k[1]);
		int[][] gField = new int[n][n];

		for (int r = 0; r < n; r++) {
			String[] gl = reader.readLine().split(" ");
			for (int c = 0; c < n; c++)
				gField[r][c] = Integer.parseInt(gl[c]);
		}
		reader.close();
		PrintWriter writer = new PrintWriter(new FileWriter(
				new File("lazy.out")));
		int total = (((double)k)/n >= 0.9375F) ? calculate(n, k, gField) : calc(n,k,gField);
		writer.println(total);
		writer.close();
	}

	private static int calc(int n, int k, int[][] grass)
	{
		if(k == n*2)
		{
			int total = 0;
			for(int[] r : grass)
				for(int c : r)
					total +=c;
			return total;
		}
		int best = 0;
		int[][] total = new int[n][n];
		for (int i = 0; i <= k; i++)
			for (int j = 0; i + j <= k; j++)
				if (i < n && j < n)
					total[0][0] += grass[i][j];
		best = total[0][0];
		for (int c = 1; c < n; c++) {
			total[0][c] = total[0][c - 1];
			for (int m = k; m >= 0; m--) {
				int i = 0 - m;
				int j1 = c - 1 - k + m;
				int j2 = c + k - m;
				if (i >= 0) {
					if (j1 >= 0)
						total[0][c] -= grass[i][j1];
					if (j2 < n)
						total[0][c] += grass[i][j2];
				}
			}
			for (int m = 1; m <= k; m++) {
				int i = 0 + m;
				int j1 = c - 1 - k + m;
				int j2 = c + k - m;
				if (i < n) {
					if (j1 >= 0)
						total[0][c] -= grass[i][j1];
					if (j2 < n)
						total[0][c] += grass[i][j2];
				}
			}
			if (total[0][c] > best)
				best = total[0][c];
		}
		for (int r = 1; r < n; r++) {
			for (int c = 0; c < n; c++) {
				total[r][c] = total[r - 1][c];
				for (int m = k; m >= 0; m--) {
					int j = c - m;
					int i1 = r - 1 - k + m;
					int i2 = r + k - m;
					if (j >= 0) {
						if (i1 >= 0)
							total[r][c] -= grass[i1][j];
						if (i2 < n)
							total[r][c] += grass[i2][j];
					}
				}
				for (int m = 1; m <= k; m++) {
					int j = c + m;
					int i1 = r - 1 - k + m;
					int i2 = r + k - m;
					if (j < n) {
						if (i1 >= 0)
							total[r][c] -= grass[i1][j];
						if (i2 < n)
							total[r][c] += grass[i2][j];
					}
				}
				if (total[r][c] > best)
					best = total[r][c];
			}
		}
		return best;
	}

	private static int calculate(int n, int k, int[][] gField)
	{
		int best = 0;
		int[][] totalF = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!inCorner(n, k, r, c)) {
					int total = 0;
					for (int i = k; i >= 0; i--)
						for (int j = k - i; j >= 0; j--) {
							if (i != 0) {
								if (j != 0) {
									if (r - i >= 0 && c - j >= 0)
										total += gField[r - i][c - j];
									if (r - i >= 0 && c + j < n)
										total += gField[r - i][c + j];
									if (r + i < n && c - j >= 0)
										total += gField[r + i][c - j];
									if (r + i < n && c + j < n)
										total += gField[r + i][c + j];
								} else {
									if (r - i >= 0)
										total += gField[r - i][c];
									if (r + i < n)
										total += gField[r + i][c];
								}
							} else {
								if (j != 0) {
									if (c - j >= 0)
										total += gField[r][c - j];
									if (c + j < n)
										total += gField[r][c + j];
								} else {
									total += gField[r][c];
								}
							}

						}
					totalF[r][c] = total;
					if (total > best)
						best = total;
				}
			}

		}
		if (best == 0)// can get to every grid point no matter what
		{
			for (int r = 0; r < n; r++)
				for (int c = 0; c < n; c++)
					best += gField[r][c];
		}
		return best;
	}

	private static boolean inCorner(int n, int k, int r, int c)
	{
		int[][] corners = { { 0, 0 }, { n - 1, 0 }, { 0, n - 1 },
				{ n - 1, n - 1 } };
		for (int[] cPos : corners) {
			int rdist = cPos[0] - r;
			int cdist = cPos[1] - c;
			if (rdist < 0)
				rdist = -rdist;
			if (cdist < 0)
				cdist = -cdist;
			if (rdist + cdist < k)
				return true;
		}
		return false;
	}
}
