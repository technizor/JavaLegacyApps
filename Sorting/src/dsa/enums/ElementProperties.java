package dsa.enums;

public enum ElementProperties
{
	inField("", "Path of the *.csv file this application will load from." +
		" Note that this field is required."),
	outField("", "Path of the *.csv file this application will save to. Note " +
			"that this does not need to be manually filled, as it will be prepopulated " +
			"when you select an input file."),
	inButton("Browse", "Open a file chooser to find the input file."),
	outButton("Browse", "Open a file chooser to find the output file."),
	inLabel("Input File: ", "Path of the *.csv file this application will load from."),
	outLabel("Output File: ", "Path of the *.csv file this application will save to."),
	submit("Sort the data.", "Click to sort the records."),
	sortSelect("", "Select the type of sort."),
	columnSize("", "Number of Columns in the data."),
	rowSize("", "Number of rows in the data."),
	sortColumn("", "The column of data to sort by."),
	headerCheck("Headers in Original File", "Check this box if your data has a header row."),
	addHeaders("Headers in Output File", "Check this box if you want headers in the output file."),
	enableTable("Enable Data Table Render", "Check this box if you wish to have a preview of the sorted data."),
	sortLabel("Sorting Algorithm: ", "Select the type of sort."),
	sortByLabel("Column to sort by: ", "Select the column of data to sort by."),
	rowLabel("Maximum Rows: ", "Number of rows in the data."),
	columnLabel("Maximum Columns: ", "Number of columns in the data."),
	rank("Rank", "Click to generate a ranked file."),
	title("Data Sorting Application", "Click to drag this window.");
	private String labelText;
	private String toolTip;
	ElementProperties(String l, String t)
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
