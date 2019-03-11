package com;

public class MainClass
{
	private static int[] funcList = {14, 14, 14};
	public static void main (final String[] args)
	{
		for(int n : funcList) {
			funcCall(n);
		}
	}
	private static void funcCall(final int FUNCTION)
	{
		Object obj = null;
		switch(FUNCTION) {
		case 0:
			obj = new com.fzc.FactorialZeroCounterClass();
			break;
		case 1:
			obj = new com.mfg.MarriageAlgorithmClass();
			break;
		case 2:
			obj = new com.sqc.SquareCounterClass();
			break;
		case 3:
			obj = new com.pfb.PrimeFactorCalculationsClass();
			break;
		case 4:
			obj = new com.psq.PerfectSquareClass();
			break;
		case 5:
			obj = new com.rpc.RectanglePerimeterCounterClass();
			break;
		case 6:
			obj = new com.pbc.PerimeterBoxCounterClass();
			break;
		case 7:
			obj = new com.pld.PalindromeClass();
			break;
		case 8:
			obj = new com.sqc.SquareCounterClass();
			break;
		case 9:
			obj = new com.prob1.ProblemSet1();
			break;
		case 10:
			obj = new com.prob1.ProblemSet2();
			break;
		case 11:
			obj = new com.prob1.ProblemSet3();
			break;
		case 12:
			obj = new com.dynpro.Golf();
			break;
		case 13:
			obj = new com.dynpro.Fibonacci();
			break;
		case 14:
			obj = new com.dynpro.Backpack();
			break;
		default:
			obj = null;
		}
		if(obj == null) {
			System.out.println("Invalid function command number: " + FUNCTION);
		}
	}
}