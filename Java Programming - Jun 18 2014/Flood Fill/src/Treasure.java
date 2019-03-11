import java.util.Scanner;

public class Treasure
{
	public static void main(final String[] args)
	{
		Scanner s = new Scanner(System.in);
		char[][] map = new char[10][];
		for (int r = 0; r < 10; r++) {
			System.out.printf("Row %2d: ", r + 1);
			map[r] = s.nextLine().toCharArray();
		}
		s.close();
		int output = 0;
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++) {
				int a = hoard(map, r, c);
				if (a > output)
					output = a;
			}
		System.out.println("Largest Treasure hoard: " + output);
	}

	public static int hoard(char[][] map, int r, int c)
	{
		if (r < 0 || c < 0 || r >= 10 || c >= 10 || map[r][c] == 'W')
			return 0;
		int v = map[r][c] - '0';
		if (v < 0 || v > 9)
			v = 0;
		map[r][c] = 'W';
		return v + hoard(map, r - 1, c) + hoard(map, r + 1, c)
				+ hoard(map, r, c - 1) + hoard(map, r, c + 1);
	}
}
