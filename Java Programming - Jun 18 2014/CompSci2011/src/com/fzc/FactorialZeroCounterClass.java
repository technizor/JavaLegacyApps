package com.fzc;

import java.util.Scanner;


public class FactorialZeroCounterClass
{
	public FactorialZeroCounterClass ()
	{
		int n = 0;
		int z = 0;
		try {
			System.out.print("Enter an integer to calculate the number of zeros in \'n!\': ");
			Scanner keybd = new Scanner(System.in);
			n = keybd.nextInt();
		} catch (Exception e) {
			System.out.println("Input Error. Please enter an integer.");
			return;
		}
		for (int i = 5; i <= n; i++) {
			for (int p = i; p % 5 == 0; z++, p /= 5);
		}
		System.out.println("Number of trailing zeros in \'" + n + "!\': " + z);
	}
}
