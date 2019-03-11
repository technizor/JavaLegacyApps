package pca.com;

import java.util.Scanner;

public class MainClass
{
	public static void main(final String[] args)
	{
		final int calcLimit = 1000000;
		PrimeData primeList = new PrimeData(calcLimit);
		System.out.println("Prime Calculator: " + calcLimit +" Maximum");
		for(;;) {
			System.out.println("-------------------------------------------");
			int n = 0;
			try {
				System.out.print("Enter the number you wish to check: ");
				Scanner keybd = new Scanner(System.in);
				n = keybd.nextInt();
			} catch (Exception e) {
				System.out.println("Input Error. Please enter an integer.");
				continue;
			}
			String outputStr = "The integer " + n
					+ (n > 0 && n <= calcLimit ? (primeList.getPrime(n) ? " is " : " is not ") + "a prime number."
							: " is not within the calculator limits.");
			System.out.println(outputStr);
		}
	}
}