package cca.com;

public class Basic {
	private double diff;
	private double prodDenom;
	private double prodNumer;
	private double sum;
	
	public void add(double a, double b)
	{
		double c = a+b;
		sum = c;
	}
	public void add(double[] a)
	{
		double b = 0;
		for(double c : a)
		{
			b += c;
		}
		sum = b;
	}
	public void div(double a, double b)
	{
		double c = a;
		double d = b;
		if(d == 0) {
			System.out.println("Error: Tried to divide by zero!");
		}
		prodNumer = c;
		prodDenom = d;
	}
	public double getDiff()
	{
		return diff;
	}
	public double getProd()
	{
		if(prodDenom == 1) {
			return prodNumer;
		} else {
			return prodNumer/prodDenom;
		}
	}
	public double getSum()
	{
		return sum;
	}
	public void mult(double a, double b)
	{
		double c = 1;
		double d = 1;
		c = a*b;
		prodNumer = c;
		prodDenom = d;
	}
	public void mult(double[] a)
	{
		double b = 1;
		double c = 1;
		for(double d : a) {
			b *= d;
		}
		prodNumer = b;
		prodDenom = c;
	}
	
	
	public void pow(double a, double b)
	{
		double c = 1;
		double d = 1;
		if(a == 0) {
			c = a;
			d = a;
		} else if(b < 0) {
			for(int e = 0; e > b; e--) {
				d *= a;
			}
		} else if(b > 0) {
			for(int e = 0; e < b; e++) {
				c *= a;
			}
		}
		prodNumer = c;
		prodDenom = d;
	}
	public int round(double a, double b, int c) {
		int d = 0;
		switch(c) {
		case 0:
			if((a % b) >= (b/2)) {
				d = (int)(a/b + 1);
			} else {
				d = (int)(a/b);
			}
			break;
		case 1:
			d = (int)(a/b + 1);
			break;
		case 2:
			d = (int)(a/b);
			break;
		}
		return d;
	}
	public void sub(double a, double b)
	{
		double c = a-b;
		diff = c;
	}
}
