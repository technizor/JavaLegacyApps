package com.pfb;

import java.util.ArrayList;
import java.util.Scanner;


public class PrimeFactorCalculationsClass
{
	private int n = 0;
	private int a = 0;
	private int b = 0;
	
	private ArrayList<Integer> aBase = new ArrayList<Integer>();
	private ArrayList<Integer> bBase = new ArrayList<Integer>();
	private int aSum = 0;
	private int bSum = 0;
	
	private int GCF = 0;
	private int toPrimeFac = 0;
	private ArrayList<Integer> primeFac = new ArrayList<Integer>();
	
	public PrimeFactorCalculationsClass ()
	{
		getInputs();
		createBaseData();
		calculateGCF();
		getPrimeFac();
		outputPrimeFac();
	}
	private void getInputs()
	{
		try {
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a number.");
			n = reader.nextInt();
			System.out.println("Enter a base between 2 and 16.");
			a = reader.nextInt();
			System.out.println("Enter another base between 2 and 16.");
			b = reader.nextInt();
		} catch (Exception e) {}
	}
	private void createBaseData()
	{
		for(int x = n; x > 0; x /= a) {
			aBase.add(x % a);
		}
		for(int x = n; x > 0; x /= b) {
			bBase.add(x % b);
		}
		for(int x : aBase) {
			aSum += x;
		}
		for(int y : bBase) {
			bSum += y;
		}
	}
	private void calculateGCF()
	{
		int min = Math.min(aSum, bSum);
		int max = Math.max(aSum, bSum);
		int val = 1;
		if(max % min == 0) {
			GCF = min;
			toPrimeFac = GCF + aSum + bSum;
			return;
		}
		for(int x = 2; x < min;) {
			if(min % x != 0 || max % x != 0) {
				x++;
			} else {
				val *= x;
			}
		}
		GCF = val;
		toPrimeFac = GCF + aSum + bSum;
	}
	private void getPrimeFac()
	{
		int num = toPrimeFac;
		for(int p = 2; p <= num;) {
			if(num % p == 0) {
				primeFac.add(p);
				num /= p;
			} else {
				p++;
			}
		}
	}
	private void outputPrimeFac()
	{
		int output = 0;
		for(int x : primeFac) {
			output += x;
		}
		System.out.println(output);
	}
}
