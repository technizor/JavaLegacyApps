import java.util.Scanner;

public class UFO
{
	public static void main(final String[] args)
	{
		Scanner s = new Scanner(System.in);
		char[][] radar = new char[8][];
		for (int r = 0; r < 8; r++) {
			System.out.printf("Row %d: ", r + 1);
			radar[r] = s.nextLine().toCharArray();
		}
		s.close();
		int output = 0;
		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++) {
				int a = flood(radar, r, c);
				if (a > output)
					output = a;
			}
		System.out.println("Largest UFO size: " + output);
	}

	public static int flood(char[][] radar, int r, int c)
	{
		if (r < 0 || c < 0 || r >= 8 || c >= 8 || radar[r][c] == '-')
			return 0;
		radar[r][c] = '-';
		return 1 + flood(radar, r - 1, c) + flood(radar, r + 1, c)
				+ flood(radar, r, c - 1) + flood(radar, r, c + 1);
	}
}
