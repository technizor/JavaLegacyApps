package numbers;
import java.util.ArrayList;

public class Biorhythms
{
	
	public static int findFirstMaxBiorhythmDay(int physCyclePeriod,
			int emotCyclePeriod, int inteCyclePeriod)
	{
		// Find the first instance of max within the same day
		// Where the cycles are sin functions
		int FIRST_PHYS_MAX = physCyclePeriod / 4;
		int FIRST_EMOT_MAX = emotCyclePeriod / 4;
		int FIRST_INTE_MAX = inteCyclePeriod / 4;
		int TOTAL_PERIOD = physCyclePeriod*emotCyclePeriod*inteCyclePeriod;

		// Make three lists of all max days until the first period of all three
		// cycles
		ArrayList<Integer> physicalMax = new ArrayList<Integer>();
		for (int d = FIRST_PHYS_MAX; d < TOTAL_PERIOD; d += physCyclePeriod)
			physicalMax.add(d);
		ArrayList<Integer> emotionalMax = new ArrayList<Integer>();
		for (int d = FIRST_EMOT_MAX; d < TOTAL_PERIOD; d += emotCyclePeriod)
			emotionalMax.add(d);
		ArrayList<Integer> intellectualMax = new ArrayList<Integer>();
		for (int d = FIRST_INTE_MAX; d < TOTAL_PERIOD; d += inteCyclePeriod)
			intellectualMax.add(d);

		// Find the common days
		physicalMax.retainAll(emotionalMax);
		physicalMax.retainAll(intellectualMax);
		if (physicalMax.isEmpty())
			return 0;
		return physicalMax.get(0);
	}

	public static void main(final String[] args)
	{
		int physPeriod = 23;
		int emotPeriod = 28;
		int intePeriod = 33;
		int commonDay = findFirstMaxBiorhythmDay(physPeriod, emotPeriod,
				intePeriod);
		int year = (int) (commonDay / 365.25);
		int day = (int) (commonDay % 365.25);

		double physK = 2 * Math.PI / physPeriod;
		double emotK = 2 * Math.PI / emotPeriod;
		double inteK = 2 * Math.PI / intePeriod;
		double exactPhysMax = commonDay + (physPeriod % 4)/4.0;
		double exactEmotMax = commonDay + (emotPeriod % 4)/4.0;
		double exactInteMax = commonDay + (intePeriod % 4)/4.0;
		
		// Display results
		System.out.printf(
				"The %dth day is the first common max day.%nThis is %d years and %d "
						+ "days after the person's date of birth.%n",
				commonDay, year, day);
		System.out.printf("Physical Cycle Value on the %.2fth day is: %.2f%n",
				exactPhysMax, Math.sin(physK * exactPhysMax));
		System.out.printf("Emotional Cycle Value on the %.2fth day is: %.2f%n",
				exactEmotMax, Math.sin(emotK * exactEmotMax));
		System.out.printf(
				"Intellectual Cycle Value on the %.2fth day is: %.2f%n",
				exactInteMax, Math.sin(inteK * exactInteMax));
	}
}
