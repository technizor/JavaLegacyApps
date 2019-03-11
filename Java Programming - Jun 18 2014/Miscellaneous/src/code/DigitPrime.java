package code;

public class DigitPrime
{
	public static boolean isPrime(long number)
	{
		// Initial checks
		if (number <= 1 || (number != 2 && number % 2 == 0))
			return false;
		// Determine the upper bounds of the checking loop
		int sqrt = (int) Math.sqrt(number);
		for (int factor = 3; factor <= sqrt; factor += 2) {
			if (number % factor == 0)
				return false;
		}
		return true;
	}

	public static boolean isDigitPrime(int number)
	{
		while (isPrime(number)) {
			number /= 10;
		}
		if (number == 0)
			return true;
		return false;
	}

	public static int findMaxPrime()
	{
		for(int digit1 = 9; digit1 >= 0; digit1 -= 1) {
			if(isDigitPrime(digit1))
			for(int digit2 = digit1*10 + 9; digit2 >= digit1*10; digit2 -= 1) {
				if(isDigitPrime(digit2))
					for(int digit3 = digit2*10 + 9; digit3 >= digit2*10; digit3 -= 1) {
					if(isDigitPrime(digit3))
					for(int digit4 = digit3*10 + 9; digit4 >= digit3*10; digit4 -= 1) {
						if(isDigitPrime(digit4))
						for(int digit5 = digit4*10 + 9; digit5 >= digit4*10; digit5 -= 1) {
							if(isDigitPrime(digit5))
							for(int digit6 = digit5*10 + 9; digit6 >= digit5*10; digit6 -= 1) {
								if(isDigitPrime(digit6))
								for(int digit7 = digit6*10 + 9; digit7 >= digit6*10; digit7 -= 1) {
									if(isDigitPrime(digit7))
									for(int digit8 = digit7*10 + 9; digit8 >= digit7*10; digit8 -= 1) {
										if(isDigitPrime(digit8))
										for(int digit9 = digit8*10 + 9; digit9 >= digit8*10; digit9 -= 2) {
											if(isDigitPrime(digit9))
												return digit9;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args)
	{
		System.out.println(findMaxPrime());
	}
}
