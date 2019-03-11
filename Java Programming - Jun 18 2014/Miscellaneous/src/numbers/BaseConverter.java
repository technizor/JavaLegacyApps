package numbers;

import java.math.BigInteger;
import java.util.Scanner;

public class BaseConverter {
	public static String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(final String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Base Converter (B2 - B16)");
		for(;;)
		{
			System.out.println("-------------");
			try {
				System.out.print("Please enter the starting base: ");
				int b1 = Integer.parseInt(scan.next());
				System.out.print("Please enter the integer you wish to convert: ");
				String n1 = scan.next().trim();
				System.out.print("Please enter the resulting base: ");
				int b2 = Integer.parseInt(scan.next());
				String n2 = convert(n1, b1, b2);
				System.out.println(n2);
			} catch(NumberFormatException nfe) {
				System.out.println("Input Error!");
			}
		}
	}
	public static String convert(String number, int base1, int base2)
	{
		//Convert to base 10
		if(base1 > 16 || base2 > 16 || base1 < 2 || base2 < 2)
			return "Cannot compute!";
		double n = 0;
		for(double i = number.length()-1, b = 1; i > -1; i--, b *= base1)
		{
			char ch = number.toUpperCase().charAt((int)i);
			n += DIGITS.indexOf(ch) * b;
		}
		String n1 = "";
		while(n != 0) {
			n1 = DIGITS.charAt((int)n%base2) + n1;
			n = Math.floor(n/base2);
		}
		return n1;
	}
}
