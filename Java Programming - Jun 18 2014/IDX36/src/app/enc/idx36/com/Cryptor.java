package app.enc.idx36.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cryptor
{
	protected ArrayList<String> auxChar;
	protected ArrayList<String> keyName;
	public static final char SPACE = '_';
	public static final char LETTERSPACE = '-';
	public static final char AUXIDENTIFIER = '~';
	
	
	public Cryptor()
	{
		loadFile();
		loadAux();
	}
	public static int b36Tob10(String b36)
	{
		int total = 0;
		for(int i = 1, pos = b36.length()-1; pos >= 0; pos--, i*=36)
		{
			int digit = getB10Digit("" + b36.charAt(pos));
			total += digit*i;
		}
		return total;
	}
	public static int getB10Digit(String b36)
	{
		try {
			int temp = Integer.parseInt(b36);
			return temp;
		} catch (NumberFormatException nfe)
		{
			char ch = b36.toCharArray()[0];
			return ((int) ch)-55;
		}
	}
	public static String b10Tob36(int index)
	{
		String output = "";
		for(; index > 0; index /= 36)
		{
			int digit = index % 36;
			output = getB36Digit(digit) + output;
		}
		return output;
	}
	public static String getB36Digit(int decimal)
	{
		if(decimal < 10) {
			return "" + decimal;
		} else {
			decimal -= 10;
		}
		char ch = (char) (65+decimal);
		return "" + ch;
	}
	public static boolean isAlpha(Character c)
	{
		return Character.isLetter(c);
	}
	public static boolean isNumChar(Character c)
	{
		String str = c.toString();
		try {
			Integer.parseInt(str);
			return true;
		} catch(Exception e) {}
		return false;
	}
	public static char numToAlphaEquiv(int n)
	{
		switch(n % 10) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		case 8:
			return 'i';
		default:
			return 'j';
		}
	}
	public static int alphaToNumEquiv(char c)
	{
		switch(c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		default:
			return 9;
		}
	}
	protected void loadFile()
	{
		int counter = 1;
		String line = "";
		keyName = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("pkmnlist.txt"));
			while((line = reader.readLine()) != null) {
				if(line.length() == 0) {
					continue;
				}
				try {
					if(line.charAt(0) == '#') continue;
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Error on line: " + counter);
				}
				keyName.add(line.substring(0, Math.min(line.length(), 36)));
				counter++;
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
	protected void loadAux()
	{
		auxChar = new ArrayList<String>();
		auxChar.add("~01");
		auxChar.add("~02");
		auxChar.add("`~!@#$%^&*()_-+={[}]|\\:;\"\'<,>.?/____");
		auxChar.add("012345678901234567890123456789012345");
	}
}
