package com.dynpro;

import java.util.Scanner;

public class Golf
{
	public void printMoves(int distance, int ... clubs)
	{
		int[] array = new int[distance+1];
		array[0] = 0;
		for(int d = 1; d <= distance; d++) {
			int[] swings = new int[clubs.length];
			for(int c = 0; c < clubs.length; c++) {
				swings[c] = (d - clubs[c] > -1) ? ((array[d - clubs[c]] != -1) ? array[d-clubs[c]]+1 : -1) : -1;
			}
			int shortest = -1;
			for(int n : swings)
			{
				if(shortest == -1 && n!= -1) {
					shortest = n;
				} else if(n != -1) {
					shortest = Math.min(shortest, n);
				}
			}
			array[d] = shortest;
		}
		System.out.println(array[distance] != -1 ? "Total moves: " + array[distance] : "Cannot finish game!");
	}
	public Golf()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Golf Move Calculator");
		System.out.print("Please enter the distance: ");
		int dist = scan.nextInt();
		System.out.print("Please enter the total number of golf clubs: ");
		int club = scan.nextInt();
		int[] clubArray = new int[club];
		for(int i = 0; i < club; i++)
		{
			System.out.print("Please enter the distance golf club " + (i+1) + " hits: ");
			clubArray[i] = scan.nextInt();
		}
		printMoves(dist, clubArray);
		System.out.println("----------------");
	}
}
