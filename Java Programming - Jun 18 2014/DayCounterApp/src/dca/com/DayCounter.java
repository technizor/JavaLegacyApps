package dca.com;

/**
*	DayCounter Class subclass DayCounterData
*	The purpose of this class is to provide the calculation backbone
*	of any GUI interface required to display the difference of dates.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

class DayCounter
{
/**
*	This array holds the day, month, and year of date 1.
**/	private int[] date1 = {0, 0, 0};

/**
*	This array holds the day, month, and year of date 2.
**/	private int[] date2 = {0, 0, 0};

/**
*	This saves the relationship between both dates. 
*	0 = same, 1 = Date A > Date B, 2 = Date B > Date A.
**/	private int order = 0;

/**
*	This array stores whether either date is a leap year.
**/	private Boolean[] leap = {false, false};

/**
*	This array holds the names of the months for both dates.
**/	private String[] monthNames = {null, null};

/**
*	This is the result buffer that is passed to the display.
**/	private String buffer = null;

/**
*	This is the count variable used to save the calculation.
**/	private double count = -1;

/**
*	This String array holds the numeric dates in the format MM/DD/YYYY.
**/	private String[] dates = {null, null};
	
/**
*	This is a calling method that begins the process of calculation,
*	as well as any other calculations required.
**/	void processInfo()
	{
		compareDates();
		MonthName();
		if((validate(1) == false) || (validate(2) == false)) {
			count = -2;
		} else {
	//Calculate the day count according to relationship between the two dates
			switch (order) {
				case 1:
					counter();
					break;
				case 2:
					counter();
					break;
				case 0:
					count = 0;
					break;
				default:
					count = -1;
					break;
			}
		}
	//Output the answer, unless returned 0.1
		dates[0] = date1[1] + "/" + date1[0] + "/" + date1[2];
		dates[1] = date2[1] + "/" + date2[0] + "/" + date2[2];
			if(count == 0) {
				buffer = "Attention! The two dates are identical.";
			} else if(count == -1) {
				buffer = "Program Error.";
			} else if(count == -2) {
				buffer = "That day does not exist!";
			} else {
				buffer = dates[0] + " is ";
				if(count*count == 1) {
					buffer += "1 day ";
				} else {
					buffer += (int) count + " days ";
				}
				if(count > 0) {
					buffer += "before ";
				} else {
					buffer += "after ";
				}
				buffer += dates[1] + ".";
			}
	}
	
/**
*	This method is used to find a matching name of the
*	month in the enum MONTHS to get the month number.
*
*	@param name - the name of the month to return.
*	@return int month - the corresponding month number.
**/	int getMonthNum(String name)
	{
		int month = 1;
	//
		for(DayCounterEnum.monthList m : DayCounterEnum.monthList.values()) {
			if(m.getName() == name) {
				break;
			}
				month++;
		}
		return month;
	}

/**
*	Accessor method. Returns the result string to the GUI for display.
*
*	@return String buffer - the result string that will be displayed.
**/	String getResult()
	{
	
		return buffer;
	}
	
/**
*	Accessor method. Returns the counting calculation, for
*	whatever purpose not covered in this class.
*
*	@return double count - the variable that stores the number of days last calculated.
**/	double getCount()
	{
		return count;
	}
	
/**
*	Accessor method. Returns the name of either month.
*
*	@param x - selects which month name to return.u
*	@return String - Returns null if it cannot return the correct String.
**/	String getNames(int x)
	{
	
		switch(x) {
			case 1:
				return monthNames[0];
			case 2: 
				return monthNames[1];
		}
		return null;
	}
	
/**
*	Accessor method. Returns Date 1.
*
*	@return String - date 1 in the format: MM/DD/YYYY.
**/	String getDateA()
	{
	
		return dates[0];
	}
	
/**
*	Accessor method. Returns Date 2.
*
*	@return String - date 2 in the format: MM/DD/YYYY.
**/	String getDateB()
	{
	
		return dates[1];
	}
	
/**
*	Accessor method. Sets Date 1.
*
*	@param x - day of date 1.
*	@param y - month of date 1.
*	@param z - year of date 1.
**/	void setDateA(int x, int y, int z)
	{
	
		date1[0] = x;
		date1[1] = y;
		date1[2] = z;
	}
	
/**
*	Accessor method. Sets Date 2.
*
*	@param x - day of date 2.
*	@param y - month of date 2.
*	@param z - year of date 2.
**/	void setDateB(int x, int y, int z)
	{
	
		date2[0] = x;
		date2[1] = y;
		date2[2] = z;
	}
	
/**
*	This method very simply calculates the days within a month,
*	allowing for quick addition/subtraction when calculating the
*	difference of days.
*
*	@param x - month.
*	@param y - year.
*	@return z - the number of days in that particular month.
**/	int countDays(int x, int y)
	{
		int z = -1;
		switch (x) {
	//January, March, May, July, August, October, December
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			z = 31;
			break;
	//April, June, September, November
		case 4: case 6: case 9: case 11:
			z = 30;
			break;
	//February
		case 2:
	//Leap year
			if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
				z = 29;
			} else {
				z = 28;
			}
		default:
			break;
		}
		return z;
	}
	
/**
*	This method will determine the direct relationship between the dates,
*	whether it be the first date being after or before, or even being
*	identical to the second.
**/	private void compareDates()
	{
		if (date1[2] == date2[2]) {					//If same year, check month
			if (date1[1] == date2[1]) {				//If same month, check day
				if (date1[0] == date2[0]) {
					order = 0;				//Dates are the same
				} else if (date1[0] > date2[0]) {
					order = 1;
				} else{
					order = 2;
				}
			} else if (date1[1] > date2[1]) {
				order = 1;
			} else{
				order = 2;
			}
		} else if (date1[2] > date2[2]) {
			order = 1;
		} else{
			order = 2;
		}
	//Check whether date 1 and 2 are leap years
		if(((date1[2] % 4 == 0) && (date1[2] % 100 != 0)) || (date1[2] % 400 == 0)) {
			leap[0] = true;
		} else {
			leap[0] = false;
		}
		if(((date2[2] % 4 == 0) && (date2[2] % 100 != 0)) || (date2[2] % 400 == 0)) {
			leap[1] = true;
		} else {
			leap[1] = false;
		}
	}
	
/**
*	Reverse of getMonth(), using the month number to match to the name.
*	Uses the enum MONTHS to match.
**/	private void MonthName()
	{
		for(DayCounterEnum.monthList m : DayCounterEnum.monthList.values()) {
			if(date1[1] == m.getNum()) {
				monthNames[0] = m.getName();
			}
			if(date2[1] == m.getNum()) {
				monthNames[1] = m.getName();
			}
		}
	}
	
/**
*	This method actually calculates the number of days between any two
*	given dates, using a simple algorithm to add/subtract dates until
*	the dates "match".
**/	private void counter()
	{
		int x = -1;
		int y = -1;
		double z = 0;
	//Temporary variables so it does not alter the main copies
		int a, b, c, d, e, f;
		a = b = c = d = e = f = 0;
		if(order == 1) {
	//Date 1 is after date 2
			a = date1[0];
			b = date1[1];
			c = date1[2];
			d = date2[0];
			e = date2[1];
			f = date2[2];
		} else if(order == 2) {
	//Vice versa
			a = date2[0];
			b = date2[1];
			c = date2[2];
			d = date1[0];
			e = date1[1];
			f = date1[2];
		}
	//Add days based on the number of years between the two dates
		if (c > f) {
			for(; c > f; f++) {
				y = f+1;
				if ((e > 2)) {
					if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
						x = 366;
					} else {
						x = 365;
					}
				} else if ((e <= 2)) {
					if (((f % 4 == 0) && (f % 100 != 0)) || (f % 400 == 0)) {
						x = 366;
					} else {
						x = 365;
					}
				} else {
					x = 365;
				}
				z += x;
			}
		}
	//Add/Subtract days based on the months between the two dates
		if (b != e) {
			if (b < e) {
				for (; b < e; b++) {
					z -= countDays(b, c);
				}
		 	} else if (b > e) {
			 	for(; b > e; e++) {
				 	z += countDays(e, f);
				}
			}
		}
	//Add/Subtract days based on the difference between the two dates
		if (a != d) {
			z += a-d;
		}
		count = z;
	}
	
/**
*	This method will check to see whether any of the dates are
*	non-existent or invalid, circumventing any possible calculation
*	errors.
*
*	@param g - Selector variable
*	@return Boolean - true if the date is a real date, false if not.
**/	Boolean validate(int g)
	{
		switch(g) {
		case 1:
			switch(date1[1]) {
				case 2:
					if(((leap[0] == true) && (date1[0] > 29)) || ((leap[0] == false) && (date1[0] > 28))){
						return false;
					}
				case 4:	case 6:	case 9:	case 11:
					if(date1[0] > 30) {
						return false;
					}
				default:
					if((date1[0] > 31) || (date1[0] < 0)) {
						return false;
					}
				}
			break;
		case 2:
			switch(date2[1]) {
			case 2:
				if(((leap[1] == true) && (date2[0] > 29)) || ((leap[0] == false) && (date2[0] > 28))){
					return false;
				}
			case 4:	case 6:	case 9:	case 11:
				if(date2[0] > 30) {
					return false;
				}
			default:
				if((date2[0] > 31) || (date2[0] < 0)) {
					return false;
				}
			}
			break;
		}
		return true;
	}
}