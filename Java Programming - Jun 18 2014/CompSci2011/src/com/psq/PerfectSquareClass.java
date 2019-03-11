package com.psq;

import java.util.Scanner;

public class PerfectSquareClass
{
	private int n = 0;
	private int output = 0;
	
	public PerfectSquareClass()
	{
		System.out.println("Perfect Square Calculator");
		System.out.print("Enter a number: ");
		Scanner reader = new Scanner(System.in);
		try {
			n = reader.nextInt();
		} catch(Exception ioe) {
			System.out.println("Not a number!");
			return;
		}
		output = getMultiplier();
		System.out.println("The output number is " + output);
	}
	private int getMultiplier()
	{
		int maxOutNum = n;
		int minOutNum = (int) Math.sqrt(n);
		if(minOutNum * minOutNum == n) {
			return minOutNum;
		}
		for(int i = 0; i < n; i++) {
			int product = i * i;
			if(product < n) continue;
			if(product == n) return 1;
			if(product > n) {
				if(product % n == 0) {
					return product/n;
				}
			}
		}
		return maxOutNum;
	}
}
