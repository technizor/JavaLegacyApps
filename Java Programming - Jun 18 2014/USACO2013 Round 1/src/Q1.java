import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q1
{
	final static String name = "combo";

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(name
				+ ".in")));
		String sizeStr = reader.readLine();
		String[] johnComboStr = reader.readLine().split(" ");
		String[] masterComboStr = reader.readLine().split(" ");
		reader.close();

		int n = Integer.parseInt(sizeStr);
		int[] johnCombo = new int[3];
		int[] masterCombo = new int[3];
		johnCombo[0] = Integer.parseInt(johnComboStr[0]);
		johnCombo[1] = Integer.parseInt(johnComboStr[1]);
		johnCombo[2] = Integer.parseInt(johnComboStr[2]);
		masterCombo[0] = Integer.parseInt(masterComboStr[0]);
		masterCombo[1] = Integer.parseInt(masterComboStr[1]);
		masterCombo[2] = Integer.parseInt(masterComboStr[2]);

		// 0 = invalid, 1 = johnCombo, 2 = masterCombo, 3 = both
		int[] firstN = new int[n];
		for (int d = -2; d <= 2; d++) {
			firstN[(n + johnCombo[0] + d) % n] += 1;
			firstN[(n + masterCombo[0] + d) % n] += 2;
		}

		int[] secondN = new int[n];
		for (int d = -2; d <= 2; d++) {
			secondN[(n + johnCombo[0] + d) % n] += 1;
			secondN[(n + masterCombo[0] + d) % n] += 2;
		}

		int[] thirdN = new int[n];
		for (int d = -2; d <= 2; d++) {
			thirdN[(n + johnCombo[0] + d) % n] += 1;
			thirdN[(n + masterCombo[0] + d) % n] += 2;
		}

		int combos = 0;
		for (int d1 = 0; d1 < n; d1++) {
			// johncombo
			if (firstN[d1] == 1) {
				for (int d2 = 0; d2 < n; d2++) {
					if (secondN[d2] % 2 == 1) {
						for (int d3 = 0; d3 < n; d3++) {
							if (thirdN[d3] % 2 == 1)
								combos++;
						}
					}

				}
			}
			// mastercombo
			if (firstN[d1] == 2) {
				for (int d2 = 0; d2 < n; d2++) {
					if (secondN[d2] > 1) {
						for (int d3 = 0; d3 < n; d3++) {
							if (thirdN[d3] > 1)
								combos++;
						}
					}

				}
			}
			// both
			if (firstN[d1] == 3) {
				for (int d2 = 0; d2 < n; d2++) {
					// johncombo
					if (secondN[d2] == 1) {
						for (int d3 = 0; d3 < n; d3++) {
							if (thirdN[d3] % 2 == 1)
								combos++;
						}
					}
					// mastercombo
					if (secondN[d2] == 2) {
						for (int d3 = 0; d3 < n; d3++) {
							if (thirdN[d3] > 1)
								combos++;
						}
					}
					// both
					if (secondN[d2] == 3) {
						for (int d3 = 0; d3 < n; d3++) {
							if (thirdN[d3] != 0)
								combos++;
						}
					}
				}
			}
		}

		PrintWriter writer = new PrintWriter(new FileWriter(new File(name
				+ ".out")));
		writer.println(combos);
		writer.close();
	}
}
