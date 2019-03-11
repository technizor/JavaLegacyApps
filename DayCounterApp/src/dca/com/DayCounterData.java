package dca.com;

/**
*	DayCounterData Class extends DayCounter
*	This subclass handles any data requests for its superclass,
*	combining method calls used together into various calling
*	methods.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

public class DayCounterData extends DayCounter
{
/**
*	This method will return the number of days in any
*	given month.
*
*	@param month - name of the month.
*	@param year - the year.
*	@return ans - the number of days in that month.
**/	public int checkMonth(String month, int year)
	{
		int ans = -1;
		int m = super.getMonthNum(month);
		ans = super.countDays(m, year);
		return ans;
	}
	
/**
*	This method will return the number of days in any
*	given month. Functionally identical to the other method,
*	only this uses an integer to represent the month.
*
*	@param month - the month number
*	@param year - the year.
*	@return ans - the number of days in that month.
**/	public int checkMonth(int month, int year)
	{
		int ans = -1;
		ans = super.countDays(month, year);
		return ans;
	}
	
/**
*	This method converts a month name into its corresponding number.
*
*	@param month - name of month.
*	@return m - the number corresponding to the month name.
**/	public int getMonthN(String month)
	{
	
		int m = super.getMonthNum(month);
		return m;
	}
	
/**
*	The main calling method used, this submits information to the
*	superclass, then return an information buffer that is to be
*	displayed through the GUI.
*
*	@param d1 - the day for date 1.
*	@param m1 - the month for date 1.
*	@param y1 - the year for date 1.
*	@param d2 - the day for date 2.
*	@param m2 - the month for date 2.
*	@param y2 - the ear for date 2.
*	@return buffer - the message to be displayed.
**/	public String submitInfo(int d1, int m1, int y1, int d2, int m2, int y2)
	{
	
		String buffer = null;
		super.setDateA(d1, m1, y1);
		super.setDateB(d2, m2, y2);
		super.processInfo();
		buffer = super.getResult();
		return buffer;
	}
	
/**
*	This method submits information to its superclass, then
*	returns a String containing date information.
*
*	@param d1 - the day for date 1.
*	@param m1 - the month for date 1.
*	@param y1 - the year for date 1.
*	@param d2 - the day for date 2.
*	@param m2 - the month for date 2.
*	@param y2 - the ear for date 2.
*	@param a - selects which date to return.
*	@return buffer - the message to be displayed.
**/	public String getDateInfo(int d1, int m1, int y1, int d2, int m2, int y2, int a)
	{
	
		String buffer = null;
		switch (a) {
			case 1:
				super.setDateA(d1, m1, y1);
				super.processInfo();
				buffer = super.getDateA();
				break;
			case 2:
				super.setDateB(d2, m2, y2);
				super.processInfo();
				buffer = super.getDateB();
				break;
		}
		return buffer;
	}
	
/**
*	This method is a calling method to verify whether a date
*	in question exists.
*
*	@param j - Selects which date to validate.
*	@return Boolean z - tells the calling method whether the date is valid or not.
**/	public Boolean valid(int j)
	{
		Boolean z = super.validate(j);
		return z;
	}
}
