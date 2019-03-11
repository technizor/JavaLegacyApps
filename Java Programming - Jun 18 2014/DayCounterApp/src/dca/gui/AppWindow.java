package dca.gui;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextField;

import org.jetstorm.stormFrame.StormConstraints;
import org.jetstorm.stormFrame.StormFrame;

import dca.com.DayCounterData;
import dca.com.DayCounterEnum;

/**
*	AppWindow Class Extends JFrame Implements ActionListener
*	This class is the main window of the application. This class
*	is created by the AppLaunch class and is then used to handle
*	events created by the interface.

*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

public class AppWindow extends StormFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

/**
*	This is the DayCounter calling sub-class.
**/	private DayCounterData data;

/**
*	An AppInterface JPanel.
**/	private AppInterface panel;

/**
*	Used to calculate the current Date.
**/	private Date date;

/**
*	Stores the names of month 1 and month 2.
**/	private String[] monthNames;

/**
*	Stores the day, month, and year of date 1.
**/	private int[] date1;

/**
*	Stores the day, month, and year of date 2.
**/	private int[] date2;

/**
*	Stores the day, month, and year of the current date.
**/	private int[] currentDate;

/**
*	A String array that stores the month name, the ay of the week, and the date line.
**/	private String [] currentString;

/**
*	Whether the time display is being shown or not.
**/	private Boolean timeShown;

/**
*	This is the String that is set as the text of the result display.
**/	private String responseBuffer;
	
/**
*	Constructor of this object. Calls methods to create and
*	configure the interface object.
**/	public AppWindow()
	{
		super("Day Counter Application", false, false);
		refreshWindow();
	}
	
/**
*	One of the builder methods call by configureDefaultElements(),
*	this generates the item list for all ComboBox components.
**/	private void setDefaultComboBoxList()
	{
		for(DayCounterEnum.monthList z : DayCounterEnum.monthList.values()) {
			panel.monthBox1.addItem(z.getName());
			panel.monthBox2.addItem(z.getName());
			panel.monthBox1.setSelectedItem((Object) monthNames[0]);
			panel.monthBox2.setSelectedItem((Object) monthNames[1]);
		}
		for(DayCounterEnum.dayList z : DayCounterEnum.dayList.values()) {
			panel.dayBox1.addItem(z.getDayNum());
			panel.dayBox2.addItem(z.getDayNum());
			panel.dayBox1.setSelectedItem((Object) "" + DayCounterEnum.sdfD.format(date));
			panel.dayBox2.setSelectedItem((Object) "" + DayCounterEnum.sdfD.format(date));
		}
		for(int i = currentDate[2] - 100; i < currentDate[2] + 101; i++) {
			panel.yearBox1.addItem("" + i);
			panel.yearBox2.addItem("" + i);
			panel.yearBox1.setSelectedItem((Object) "" + DayCounterEnum.sdfY.format(date));
			panel.yearBox2.setSelectedItem((Object) "" + DayCounterEnum.sdfY.format(date));
		}
	}

/**
*	One of the builder methods call by configureDefaultElements(),
*	this sets the current date, allowing for certain elements to
*	display unique information based on this.
**/	private void getDefaultDate()
	{
		currentDate[2] = date1[2] = date2[2] = Integer.parseInt((String) "" + DayCounterEnum.sdfY.format(date));
		currentString[0] = monthNames[0] = monthNames[1] = (String) DayCounterEnum.sdfM.format(date);
		currentDate[1] = date1[1] = date2[1] = Integer.parseInt((String) "" + DayCounterEnum.sdfMn.format(date));
		currentDate[0] = date1[0] = date2[0] = Integer.parseInt((String) "" + DayCounterEnum.sdfD.format(date));
		currentString[2] = getDayName(currentDate[2], currentDate[1], currentDate[0]) + ", the ";
		currentString[2] += getDayNum(currentDate[2], currentDate[1], currentDate[0]) + ".";
		responseBuffer = currentString[2];
	}
	
/**
*	One of two methods called to generate a date line
*	with the proper text format.
*
*	@param a - day
*	@param b - month.
*	@param c - year.
*	@return String buffer - returns the day number, with its 'order' suffix.
**/	private String getDayNum(int a, int b, int c)
	{
		String buffer = "" + c;
		switch(c) {
		case 11:	case 12:	case 13:
			buffer += "th";
			break;
		default:
			switch(currentDate[0] % 10) {
			case 1:
				buffer += "st";
				break;
			case 2:
				buffer += "nd";
				break;
			case 3:
				buffer += "rd";
				break;
			default:
				buffer += "th";
				break;
			}
		}
		return buffer;
	}
	
/**
*	One of two methods called to generate a date line
*	with the proper text format.
*
*	@param a - day
*	@param b - month.
*	@param c - year.
*	@return String buffer - returns the day of the week.
**/	private String getDayName(int a, int b, int c)
	{
		String buffer = "";
		Calendar today = new GregorianCalendar(a, b, c+4);
		switch (today.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			buffer = "Sunday";
			break;
		case Calendar.MONDAY:
			buffer = "Monday";
			break;
		case Calendar.TUESDAY:
			buffer = "Tuesday";
			break;
		case Calendar.WEDNESDAY:
			buffer = "Wednesday";
			break;
		case Calendar.THURSDAY:
			buffer = "Thursday";
			break;
		case Calendar.FRIDAY:
			buffer = "Friday";
			break;
		case Calendar.SATURDAY:
			buffer = "Saturday";
			break;
		}
		return buffer;
	}
	
/**
*	One of two date line generating methods that calls
*	two other methods to help generate special portions
*	of the String.
*
*	@param s - a selector variable to choose which date line is required.
**/	private void getFullDateLine(int s)
	{
		int month;
		int year;
		int day;
		String monthName;
		String buffer = "Date " + s + " is: ";
		data.submitInfo(date1[0], date1[1], date1[2], date2[0], date2[1], date2[2]);
		if((data.valid(1) == false) && (s == 1)) {
			responseBuffer = "Date " + s + " does not exist!";
		} else if((data.valid(2) == false) && (s == 2)) {
			responseBuffer = "Date " + s + " does not exist!";
		} else {
			if(s == 1) {
				month = date1[1];
				monthName = monthNames[0];
				year = date1[2];
				day = date1[0];
			} else {
				month = date2[1];
				monthName = monthNames[1];
				year = date2[2];
				day = date2[0];
			}
			buffer += getDayName(year, month, day) + ", " + monthName  + " the ";
			buffer += getDayNum(year, month, day) + ", " + year + ".";
			responseBuffer = buffer;
		}
	}

	@Override
	public void actionHandler(Object source)
	{
		Boolean gotResult = false;
		int m0 = 0;
	//Get source of event, then execute method
		if((source != panel.currentTimeLabel) || timeShown == true) {
			panel.result.setVisible(true);
			panel.currentTime.setVisible(false);
			timeShown = false;
			if(source == panel.submit) {
				responseBuffer = data.submitInfo(date1[0], date1[1], date1[2], date2[0], date2[1], date2[2]);
				gotResult = true;
			} else if(source == panel.fromLabel) {
				getFullDateLine(1);
				gotResult = true;
			} else if(source == panel.toLabel) {
				getFullDateLine(2);
				gotResult = true;
			} else if(source == panel.resultLabel) {
				responseBuffer = null;
				gotResult = true;
			} else if(source == panel.monthLabel) {
				responseBuffer = "Today's month is: " + currentString[0] + ".";
				gotResult = true;
			} else if(source == panel.dayLabel) {
				responseBuffer = "Today's day is: " + currentString[2];
				gotResult = true;
			} else if(source == panel.yearLabel) {
				responseBuffer = "Today's year is: " + currentDate[2] + ".";
				gotResult = true;
			} else {
				monthNames[0] = (String) panel.monthBox1.getSelectedItem();
				date1[1] = data.getMonthN(monthNames[0]);
				monthNames[1] = (String) panel.monthBox2.getSelectedItem();
				date2[1] = data.getMonthN(monthNames[1]);
				date1[0] = Integer.parseInt((String) panel.dayBox1.getSelectedItem());
				date2[0] = Integer.parseInt((String) panel.dayBox2.getSelectedItem());
				date1[2] = Integer.parseInt((String) panel.yearBox1.getSelectedItem());
				date2[2] = Integer.parseInt((String) panel.yearBox2.getSelectedItem());
				if(source == panel.monthBox1) {
					m0 = data.checkMonth(monthNames[0], date1[2]);
				} else if(source == panel.monthBox2) {
					m0 = data.checkMonth(monthNames[0], date1[2]);
				} else if(source == panel.yearBox1) {
					m0 = data.checkMonth(monthNames[1], date2[2]);
				} else if(source == panel.yearBox2) {
					m0 = data.checkMonth(monthNames[1], date2[2]);
				} else if(source == panel.dayBox1) {
					
				} else if(source == panel.dayBox2) {
					
				}
			}
		} else {
			panel.result.setVisible(false);
			panel.currentTime.setVisible(true);
			timeShown = true;
		}
		if(gotResult == true) {
			gotResult = false;
			panel.result.setText(responseBuffer);
		}
		if(m0 != 0){
			m0 = 0;
		}
	
	}

	@Override
	public void addActionListeners()
	{
		panel.currentTimeLabel.addActionListener(this);
		panel.toLabel.addActionListener(this);
		panel.fromLabel.addActionListener(this);
		panel.resultLabel.addActionListener(this);
		panel.submit.addActionListener(this);
		panel.dayBox1.addActionListener(this);
		panel.monthBox1.addActionListener(this);
		panel.yearBox1.addActionListener(this);
		panel.dayBox2.addActionListener(this);
		panel.monthBox2.addActionListener(this);
		panel.yearBox2.addActionListener(this);
		panel.dayLabel.addActionListener(this);
		panel.monthLabel.addActionListener(this);
		panel.yearLabel.addActionListener(this);
	}
	
	@Override
	public void buildDefaultElements()
	{
		buildElement(panel, new StormConstraints(1,1,0,0,0,0,0,0));
	}
	
	@Override
	public void configureElements()
	{
		date = new Date();
		monthNames = new String[2];
		date1 = new int[3];
		date2 = new int[3];
		currentDate = new int[3];
		currentString = new String[3];
		timeShown = false;
		responseBuffer = null;
		currentDate = new int[3];
		data = new DayCounterData();
		panel = new AppInterface();
		panel.result.setEditable(false);
		panel.result.setHorizontalAlignment(JTextField.CENTER);
		panel.currentTime.setVisible(false);
		responseBuffer = DayCounterEnum.startUpMessage;
		panel.result.setText(responseBuffer);
		getDefaultDate();
		setDefaultComboBoxList();
	}
}