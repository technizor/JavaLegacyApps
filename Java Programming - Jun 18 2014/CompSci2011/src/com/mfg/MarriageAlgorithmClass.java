package com.mfg;

public class MarriageAlgorithmClass
{
	private int personNum = 4;
	private int[][] guysPref = {
		{3,2,/*5,*/1,4},
		{1,2,/*5,*/3,4},
		{4,3,2,/*5,*/1},
		{1,3,4,/*5,*/2},
		//{1,2,4,5,3}
		};
	private int[][] galsPref = {
		{3,/*5,*/2,1,4},
		{/*5,*/2,1,4,3},
		{4,3,/*5,*/1,2},
		{1,2,3,4,/*5*/},
		//{2,3,4,1,5}
		};
	
	public MarriageAlgorithmClass ()
	{
		int num = personNum;
		int[] pairs = new int[num];
		boolean[] guyPaired = new boolean[num];
		boolean[] galPaired = new boolean[num];
		int[][] temp = new int [num][2];
		try {
			for(int i = 0; i < num; i++) {
				for(int j = 0; j < num; j++) {
					if(guyPaired[j] != true) {
						temp[j][0] = j;
						temp[j][1] = guysPref[j][i] -1;
					}
				}
				for(int j = 0; j < num; j++) {
					if(galPaired[j] != true) {
						boolean[] temp2 = new boolean[num];
						for(int k = 0; k < num; k++) {
							if(temp[k][1] == j)
								temp2[k] = true;
						}
						for(int k = 0; k < num; k++) {
							if(temp2[galsPref[j][k]-1]) {
								pairs[galsPref[j][k]-1] = j;
								guyPaired[galsPref[j][k]-1] = true;
								galPaired[j] = true;
								break;
							}
						}
					}
				}
			}
			String outputString = "Couples: ";
			for(int i = 0; i < num; i++) {
				outputString += numToAlpha(i+1) + (pairs[i]+1);
				if(i != num-1) outputString += ", ";
			}
			outputString += ".";
			System.out.println(outputString);
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("Array Data Error. Please verify your input data.");
		}
		
	}
	private String numToAlpha(int num)
	{
		char letter = (char) (num + 64);
		String alpha = "" + letter;
		return alpha;
	}
}
