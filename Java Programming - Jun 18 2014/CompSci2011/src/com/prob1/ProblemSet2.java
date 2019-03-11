package com.prob1;

import java.util.Scanner;

public class ProblemSet2
{
	public ProblemSet2()
	{
		int n = 0;
		try {
			System.out.print("Enter an integer to calculate the number of steps to reach 1: ");
			Scanner keybd = new Scanner(System.in);
			n = keybd.nextInt();
		} catch (Exception e) {
			System.out.println("Input Error. Please enter an integer.");
			return;
		}
		System.out.println("Steps: " + recurse(n, 1));
	}
	private int recurse(int n, int s)
	{
		if(n > 1) {
			if(n % 3 == 0) {
				return recurse(n/3, s+1);
			}
			if(n % 2 == 0 && (n-1) % 3 != 0) {
				return recurse(n/2, s+1);
			}
			return recurse(n-1, s+1);
		}
		return s;
	}
}
