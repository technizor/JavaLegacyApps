package com.rpc;

import java.util.ArrayList;
import java.util.Scanner;

public class RectanglePerimeterCounterClass
{
	private int area = 0;
	private ArrayList<int[]> perimeter = new ArrayList<int[]>();
	
	public RectanglePerimeterCounterClass ()
	{
		System.out.println("Rectangle Perimeter Counter");
		System.out.print("Enter an area: ");
		Scanner reader = new Scanner(System.in);
		try {
			area = reader.nextInt();
		} catch(Exception ioe) {
			System.out.println("Not a number!");
			return;
		}
		getPerimeters();
		System.out.println("Number of Possible Perimeters: " + perimeter.size());
		for(int i = 0; i < perimeter.size(); i++) {
			int[] temp = perimeter.get(i);
			System.out.println((temp[0]*2 + temp[1]*2) + " (" + temp[0] + " * " + temp[1] + " = " + temp[0] + " + " + temp[1] + " + " + temp[0] + " + " + temp[1] + ")");
		}
	}
	private void getPerimeters()
	{
		for(int x = 1; x <= Math.sqrt(area); x++) {
			for(int y = area; y >= x; y--) {
				if(x * y == area) {
					int[] temp = {x, y};
					perimeter.add(temp);
				}
				
			}
		}
	}
}
