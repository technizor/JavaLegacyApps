package com.dynpro;

import java.util.Scanner;

public class Backpack
{
	public void printMulti(int distance, BackpackItem ... items)
	{
		int[] array = new int[distance+1];
		array[0] = 0;
		for(int d = 1; d <= distance; d++) {
			int[] item = new int[items.length];
			for(int c = 0; c < items.length; c++) {
				item[c] = (d - items[c].getWeight() > -1) ? 
						((array[d - items[c].getWeight()] != -1) ? 
								array[d-items[c].getWeight()] + items[c].getValue(): -1) : -1;
			}
			int mostVal = -1;
			for(int n : item) {
				if(n != -1) {
					mostVal = Math.max(mostVal, n);
				}
			}
			array[d] = mostVal;
		}
		int mostValue = -1;
		for(int n : array) {
			mostValue = Math.max(mostValue, n);
		}
		System.out.println(mostValue > 0 ? "Stole $" + mostValue + " worth of items!" : "Cannot steal anything!");
	}
	public Backpack()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Thief's Guide to Selective Theft");
		System.out.print("Please enter the size of the backpack: ");
		int size = scan.nextInt();
		System.out.print("Please enter the total number of items: ");
		int items = scan.nextInt();
		BackpackItem[] itemArray = new BackpackItem[items];
		for(int i = 0; i < items; i++) {
			System.out.print("\tPlease enter the name of item " + (i+1) + ": ");
			scan = new Scanner(System.in);
			String itemN = scan.nextLine();
			System.out.print("\tPlease enter the size of " + itemN.trim() + ": ");
			int itemS = scan.nextInt();
			System.out.print("\tPlease enter the value of " + itemN.trim() + ": ");
			int itemV = scan.nextInt();
			itemArray[i] = new BackpackItem(itemN.trim(), itemS, itemV);
		}
		printSingle(size, itemArray);
		System.out.println("----------------");
	}
	
	public void printSingle(int distance, BackpackItem ... items)
	{
		int[][] array = new int[distance+1][items.length+1];
		for(int i = 0; i <= items.length; i++) {
			array[0][i] = 0;
		}
		for(int i = 0; i <= distance; i++) {
			array[i][0] = 0;
		}
		for(int i = 0; i < items.length; i++) {
			for(int d = 1; d <= distance; d++) {
				if(items[i].getWeight() <= d) {
					array[d][i+1] = Math.max(array[d-items[i].getWeight()][i] + items[i].getValue(), array[d][i]);
				}
			}
		}
		int mostValue = array[array.length-1][array[0].length-1];
		System.out.println(mostValue > 0 ? "Stole $" + mostValue + " worth of items!" : "Cannot steal anything!");
	}

}

class BackpackItem
{
	private String name;
	private int weight;
	private int value;
	
	BackpackItem(String name, int weight, int value)
	{
		setName(name);
		setWeight(weight);
		setValue(value);
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}