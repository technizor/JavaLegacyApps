package ccc2012;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class S2
{
	private String arar;
	public static void main(final String[] args) throws IOException
	{
		new S2();
	}
	public S2() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("s2.in")));
		String line = reader.readLine();
		while(line != null) {
			arar = line;
			long value =  iterate();
			System.out.println("Value (" + arar + "): " + value);
			line = reader.readLine();
		}
	}
	private long iterate()
	{
		long value = 0;
		for(int i = 0; i < arar.length()/2; i++) {
			int arab = Integer.parseInt("" + arar.charAt(i*2));
			char roman = arar.charAt(i*2+1);
			long digitVal = arab*getRomanValue(roman);
			if((i*2+3) < arar.length()) {
				char nextRoman = arar.charAt(i*2+3);
				if(getRomanValue(roman) < getRomanValue(nextRoman)) {
					value -= digitVal;
				} else {
					value += digitVal;
				}
			} else {
				value += digitVal;
			}
		}
		return value;
	}
	private int getRomanValue(char ch)
	{
		switch(ch) {
		case 'I': 
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		}
		return 0;
	}
}
