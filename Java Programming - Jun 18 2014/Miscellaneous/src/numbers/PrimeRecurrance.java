package numbers;

public class PrimeRecurrance {
	private long max;
	public PrimeRecurrance(int maxVal) {
		max = maxVal;
		recursion(0);
	}
	private long recursion(long i) {
		long a = 0;
		for(int j = 1; j < 10; j += 2) {
			if(isPrime(i*10+j) && (i*10+j) <= max)
				a = Math.max(a, recursion(i*10+j));
		}
		return a;
	}
	private boolean isPrime(long l) {
		double sqrt = Math.sqrt(l);
		if(l == 1) return false;
		if(l%2 == 0 && l != 2) return false;
		for(long x = 3; x < sqrt; x+=2) {
			if(l%x == 0)return false;
		}
		return true;
	}
}
