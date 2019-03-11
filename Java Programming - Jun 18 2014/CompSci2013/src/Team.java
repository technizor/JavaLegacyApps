import java.util.Scanner;

public class Team
{
	public static void main(final String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Input: ");
		int n = input.nextInt();
		input.close();
		if (n < 4 || n > 99)
			System.out.println("Invalid Input! Must be between 4 and 99!");
		else {
			int c = 0;
			for (int a = 1; a < n - 2; a++)
				for (int b = a + 1; b < n - 1; b++)
					c += n - b - 1;
			System.out.println(c);
		}
	}
}
