package dsa.enums;

public enum SortInfo
{
	Bubble("BubbleSort", "Comparison", "Exchanging", "Yes", "O(n)",
			"O(n^2)", "O(n^2)", "O(1)", "Yes"),
	Shaker("ShakerSort", "Comparison", "Exchanging", "Yes", "O(n)",
			"O(n^2)", "O(n^2)", "O(1)", "Yes"),
	Merge("MergeSort", "Comparison", "Merging", "Yes", "O(n log n)",
			"O(n log n)", "O(n log n)", "Depends", "Yes"),	
	MinMax("Min-MaxSort", "Comparison", "Selection", "Yes", "N/A",
			"N/A", "N/A", "Depends", "Yes"),
	LSDRadix("LSD RadixSort", "Non-Comparison", "Distribution", "Yes", "N/A",
			"O(n*(k/d))", "O(n*(k/d))", "O(n)", "No");
	private String name;
	private String type;
	private String method;
	private String stability;
	private String best;
	private String average;
	private String worst;
	private String memory;
	private String notes;
	private String infoString;
	SortInfo(String a, String b, String c, String d, String ea, 
			String eb, String ec, String f, String g)
	{
		name = a;
		type = b;
		method = c;
		stability = d;
		best = ea;
		average = eb;
		worst = ec;
		memory = f;
		notes = g;
		infoString = name + "\n" + type + "\nMethod - " + method + "\nStable? - " + 
			stability + "\nBest - " + best + "\nAverage - " + average + 
			"\nWorst - " + worst + "\nMemory - " + memory + "\nStrings? - " + notes;
	}
	public String getInfo() {
		return infoString;
	}
	public String getName() {
		return name;
	}
}
