package dsa.com;

public class Global
{
	public static String loadPath = "";
	public static String savePath = "";
	public static String performanceLog = "";
	public static int dataH = 0;
	public static int dataW = 0;
	public static int listHeight = 256;
	public static int listWidth = 1;
	public static int sortByColumn = 0;
	public static int sortType = 5;
	public static boolean outHeader = true;
	public static boolean inHeader = true;
	public static boolean loadTable = true;
	public static String[] headers = null;
	
	public static void setGlobalValues(final String[] filePaths, final int[] dataSettings)
	{
		loadPath = filePaths[0];
		savePath = filePaths[1];
		listHeight = dataSettings[0];
		listWidth = dataSettings[1];
		sortByColumn = dataSettings[2];
		sortType = dataSettings[3];
	}
}
