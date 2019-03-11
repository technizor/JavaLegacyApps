package com.prob1;

import java.util.ArrayList;
import java.util.Scanner;

public class ProblemSet3
{
	private final boolean ORDEREDPRINT = true;
	private int[] SET;
	private ArrayList<Subset> subsets = new ArrayList<Subset>();
	public ProblemSet3()
	{
		int len = 0;
		int[] integers;
		try {
			System.out.print("Enter the size of the set: ");
			Scanner keybd = new Scanner(System.in);
			len = keybd.nextInt();
			integers = new int[len];
			for(int i = 0; i < len; i++)
			{
				System.out.print("Set Number " + i + ": ");
				integers[i] = keybd.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Input Error. Please enter an integer.");
			return;
		}
		SET = integers;
		recurse(0, new boolean[SET.length]);
		if(ORDEREDPRINT) printSubsets();
		System.out.println("Total Number of sets (2^" + len +"): " + (int)Math.pow(2, len));
	}
	private void recurse(int index, boolean[] components)
	{
		if(index >= SET.length) {
			if(!ORDEREDPRINT) printSet(components);
			else addSubset(components);
			return;
		}
		components[index] = false;
		recurse(index+1, components);
		components[index] = true;
		recurse(index+1, components);
	}
	private void addSubset(boolean[] components)
	{
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < components.length; i++) {
			if(components[i]) {
				ints.add(SET[i]);
			}
		}
		int i = 0;
		for(; i < subsets.size() && subsets.get(i).getSetSize() < ints.size(); i++);
		subsets.add(i, new Subset(ints.size(), ints));
	}
	private void printSet(boolean[] components)
	{
		String set = "{";
		for(int i = 0; i < components.length; i++) {
			if(components[i]) {
				if(set.length() > 1) {
					set += ", ";
				}
				set += SET[i];
			}
		}
		set += "}";
		System.out.println(set);
	}
	private void printSubsets()
	{
		for(Subset subset : subsets)
		{
			String set = "{";
			for(int i = 0; i < subset.getSetNums().length; i++) {
				if(set.length() > 1) {
					set += ", ";
				}
				set += subset.getSetNums()[i];
			}
			set += "}";
			System.out.println(set);
		}
	}
}

class Subset
{
	private int setSize;
	private int[] setNums;
	
	public Subset(int size, int[] set)
	{
		setSetSize(Math.min(size, set.length));
		setNums = new int[getSetSize()];
		for(int i = 0; i < getSetSize(); i++)
		{
			setNums[i] = set[i];
		}
	}

	public Subset(int size, ArrayList<Integer> set)
	{
		setSetSize(Math.min(size, set.size()));
		setNums = new int[getSetSize()];
		for(int i = 0; i < getSetSize(); i++)
		{
			setNums[i] = set.get(i);
		}
	}

	public int getSetSize()
	{
		return setSize;
	}

	public void setSetSize(int setSize)
	{
		this.setSize = setSize;
	}

	public int[] getSetNums()
	{
		return setNums;
	}

	public void setSetNums(int[] setNums)
	{
		this.setNums = setNums;
	}
}
