package dca.com;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

/**
*	DayCounterEnum Class
*	This class is a holder for most of the generic constants
*	used by this program. Such includes enumerations, and finals.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

public class DayCounterEnum {
/**
*	This enumeration holds a list of valid month names and their numbers.
**/	public enum monthList {
		a("January", 1), b("February", 2), c("March", 3), d("April", 4), e("May", 5), f("June", 6),
		g("July", 7), h("August", 8), i("September", 9), j("October", 10), k("November", 11), l("December", 12);
		private int number;
		private String name;
		monthList(String m, int n)
		{
			name = m;
			number = n;
		}
		public int getNum()
		{
			return number;
		}
		public String getName()
		{
			return name;
		}
	};
	
/**
*	This enumeration holds a list of valid day numbers.
**/	public enum dayList {
		a("1"), b("2"), c("3"), d("4"), e("5"), f("6"), g("7"), h("8"), i("9"), j("10"), k("11"), l("12"),
		m("13"), n("14"), o("15"), p("16"), q("17"), r("18"), s("19"), t("20"), u("21"), v("22"), w("23"),
		x("24"), y("25"), z("26"), aa("27"), ab("28"), ac("29"), ad("30"), ae("31");
		private String num;
		dayList(String n)
		{
			num = n;
		}
		public String getDayNum()
		{
			return num;
		}
	}
	
/**
*	This enumeration stores the label and tool tip information for
*	every component in the AppInterface class.
**/	public enum elementProperties {
		currentTime("", "Displays the current time."),
		currentTimeLabel("Current Time", "Displays/Hides the current time."),
		toLabel("To:", "Displays Date 2."),
		fromLabel("From:", "Displays Date 1."),
		resultLabel("Result:", "Clears result Display."),
		submit("Submit","Enter the inputted dates."),
		result("","Displays the result message."),
		monthBox1("","Selects the month of Date 1."),
		monthBox2("","Selects the month of Date 2."),
		yearBox1("","Selects the year of Date 1."),
		yearBox2("","Selects the year of Date 2."),
		dayBox1("","Selects the day of Date 1."),
		dayBox2("","Selects the day of Date 2."),
		dayLabel("Day", "Days."),
		monthLabel("Month", "Months."),
		yearLabel("Year", "Years."),
		title("Day Counter Application", "Click to drag this window.");
		private String labelText;
		private String toolTip;
		elementProperties(String l, String t)
		{
			labelText = l;
			toolTip = t;			
		}
		public String getLabel()
		{
			return labelText;
		}
		public String getToolTip()
		{
			return toolTip;
		}
	}

/**
*	This enumeration stores the label and tool tip information for
*	every button in the AppWindowControl class.
**/	public enum buttonProperties {
		close("X","Close this application."),
		minimize("_","Minimize this application.");
		private String labelText;
		private String toolTip;
		buttonProperties(String l, String t)
		{
			labelText = l;
			toolTip = t;			
		}
		public String getLabel()
		{
			return labelText;
		}
		public String getToolTip()
		{
			return toolTip;
		}
	}

/**
*	This is the default size for most of the components.
**/	final static public String compSize = "normal";

/**
*	This is a string required in all statements that would set the size of a component.
**/	final static public String sizeVari = "JComponent.sizeVariant";

/**
*	This is the default size for the window control components.
**/	final static public String controlSize = "small";

/**
*	This is one of four simple date formats required to
*	convert the dates into text.
**/	final static public SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");

/**
*	This is one of four simple date formats required to
*	convert the dates into text.
**/	final static public SimpleDateFormat sdfM = new SimpleDateFormat("MMMMM");

/**
*	This is one of four simple date formats required to
*	convert the dates into text.
**/	final static public SimpleDateFormat sdfMn = new SimpleDateFormat("MM");

/**
*	This is one of four simple date formats required to
*	convert the dates into text.
**/	final static public SimpleDateFormat sdfD = new SimpleDateFormat("d");
	
/**
*	This is the preferred size of the window.
**/	final static public Dimension panelSize = new Dimension(375, 140);
	
/**
*	This is the string displayed in the result display on startup.
**/	final static public String startUpMessage = " ~ Day Counter Application ~ ";
}
