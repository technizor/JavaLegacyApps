package com.dynpro;

import java.util.Scanner;

public class Fibonacci
{
	public Fibonacci()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Fibonacci Sequencer");
		System.out.print("Please enter the number of outputs: ");
		int num = scan.nextInt();
		printSeq(num);
		System.out.println("----------------");
	}
	
	public void printSeq(int num)
	{
		long[] fibonacci = new long[num+1];
		fibonacci[0] = 0;
		fibonacci[1] = 1;
		System.out.println(fibonacci[1]);
		for(int i = 2; i <= num; i++) {
			fibonacci[i] = fibonacci[i-2] + fibonacci[i-1];
			System.out.println(fibonacci[i]);
		}
	}
}
