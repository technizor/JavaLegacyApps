package lolutil.data.champion;

import java.util.Scanner;

public class Date
{
	private int month;
	private int day;
	private int year;
	public Date(String date)
	{
		Scanner scan = new Scanner(date);
		this.month = scan.nextInt();
		this.day = scan.nextInt();
		this.year = scan.nextInt();
	}
	public Date(int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public int getYear()
	{
		return year;
	}
	public int getDayNum()
	{
		return day;
	}
	public String getDay()
	{
		if(day < 10) {
			return "0" + day;
		}
		return "" + day;
	}
	public String getMonthName()
	{
		switch(month) {
		case 1:
		return "January";
		case 2:
		return "February";
		case 3:
		return "March";
		case 4:
		return "April";
		case 5:
		return "May";
		case 6:
		return "June";
		case 7:
		return "July";
		case 8:
		return "August";
		case 9:
		return "September";
		case 10:
		return "October";
		case 11:
		return "November";
		case 12:
		return "December";
		default:
		return "";
		}
	}
	public int getMonthNum()
	{
		return month;
	}
	public String getMonth()
	{
		if(month < 10) {
			return "0" + month;
		}
		return "" + month;
	}
	public String getDate()
	{
		return getMonthName() + " " + getDayNum() + ", " + getYear();
	}
	public String getDateNum()
	{
		return getMonth() + "/" + getDay() + "/" + getYear();
	}
}
