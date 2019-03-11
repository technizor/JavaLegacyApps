package com.pld;

import java.util.Scanner;

public class PalindromeClass
{
	public PalindromeClass()
	{
		int n = 0;
		int p = 0;
		try {
			System.out.print("Enter an integer to calculate the number of palindromes between 0 and 'n': ");
			Scanner keybd = new Scanner(System.in);
			n = keybd.nextInt();
		} catch (Exception e) {
			System.out.println("Input Error. Please enter an integer.");
			return;
		}/*
		for(int i = 2; i <= n; i++) {
			if(i < 10) {
				p++;
				System.out.println(i);
				continue;
			}
			String str = "" + i;
			char[] chars = str.toCharArray();
			boolean isPalindrome = true;
			for(int j = 0; j < (chars.length / 2); j++) {
				if(chars[j] != chars[chars.length-1-j]) {
					isPalindrome = false;
					break;
				}
			}
			if(isPalindrome) {
				p++;
				System.out.println(i);
			}
		}*//*
		for(int i = 1; i < n; i = i * 10 + 1) {
			if(i == 1) {
				p +=
			}
		}/*
		if(n > 8) {
			p += 8;
		} else {
			p += n-1;
		}
		if(n > 98) {
			p += 9;
		} else {
			p += n / 11;
		}
		if(n > 998) {
			p += 90;
		} else {
			p += 2 * (n / 10) - 9;
		}*//*
		for(int i = 1, j = 10; j < 1000000000 && i <= n; i *= 10, j *= 10) {
			int k = ((n/i) % j);
			if(k != 0) k -= 1;
			else k = 10;
			p += k;
		}*/
		if(n % 2 == 0) {
			p = (int)(2 * (Math.pow(10, n/2) - 1));
		} else {
			p = (int)(11 * Math.pow(10, (n-1)/2) - 2);
		}
		p += n/10;
		if(n > 8) {
			p += 8;
		}
		else {
			p += n - 1;
		}
		System.out.println("Number of Palindromes: " + p);
	}
}
