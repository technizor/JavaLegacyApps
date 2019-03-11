package toh.com;

public class TowerConstants {
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
}
