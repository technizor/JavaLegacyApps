package com.sqc;

import java.util.Scanner;

public class SquareCounterClass
{
	public SquareCounterClass()
	{
		System.out.println("Squares in a Rectangle Calculator");
		for(;;) {
			System.out.println("-------------------------------------------");
			int x = 0, y = 0;
			int squareNum = 0;
			int[] squareSizes;
			try {
				System.out.print("Enter the x dimension of the rectangle: ");
				Scanner keybd = new Scanner(System.in);
				x = keybd.nextInt();
				System.out.print("Enter the y dimension of the rectangle: ");
				keybd = new Scanner(System.in);
				y = keybd.nextInt();
			} catch (Exception e) {
				System.out.println("Input Error. Please enter an integer.");
				continue;
			}
			int minDimension = Math.min(x, y);
			squareSizes = new int[minDimension];
			for(int i = 0; i < minDimension; i++) {
				squareSizes[i] = (x-i)*(y-i);
				squareNum += squareSizes[i];
			}
			String outputStr = "In a rectangle with the dimensions " + x
					+ "*" + y + (squareNum != 1 ? (", there are "
					+ squareNum + " squares.") : ", there is 1 square.\nSizes of Squares: ");
			System.out.println(outputStr);
			for(int i = 0; i < minDimension; i++) {
				System.out.println("Number of [" + (i+1) + "*" + (i+1) + "] squares: " + squareSizes[i]);
			}
			
		}
	}
}
