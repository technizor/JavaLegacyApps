package com.pbc;

import java.util.Scanner;

public class PerimeterBoxCounterClass
{
	private int perimeter = 0;
	private String output = "";

	public PerimeterBoxCounterClass ()
	{
		System.out.println("Perimeter Box Counter");
		System.out.print("Enter a perimeter: ");
		Scanner reader = new Scanner(System.in);
		try {
			perimeter = reader.nextInt();
		} catch(Exception ioe) {
			System.out.println("Not a number!");
			return;
		}
		if(perimeter % 2 != 0) {
			System.out.println("Invalid!");
			return;
		}
		int temp = perimeter/2;
		if(temp % 2 != 0) {
			output = (temp+1/2) + ", " + (temp-1/2);
		} else {
			output = (temp/2) + ", " + (temp/2);
		}
		System.out.println("Maximum Size: " + output);
	}
}
