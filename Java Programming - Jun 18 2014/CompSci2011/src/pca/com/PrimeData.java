package pca.com;

import java.util.ArrayList;

public class PrimeData
{
	private ArrayList<Boolean> primeArray = new ArrayList<Boolean>();
	private int maximum;	
	
	public PrimeData(int max)
	{
		maximum = max;
		for(int i = 0; i < maximum; i++) {
			primeArray.add(true);
		}
		for(int i = 0; i < maximum; i++) {
			if(i == 0) {
				primeArray.set(0, false);
			} else if(primeArray.get(i)) {
				for(int c = i*2 + 1; c < maximum; c += i + 1) {
					primeArray.set(c,  false);
				}
			}
		}
	}
	public int getMax()
	{
		return maximum;
	}
	public boolean getPrime(int integer)
	{
		if(integer > 0 && integer <= maximum) {
			return primeArray.get(integer - 1);
		}
		return false;
	}
}
