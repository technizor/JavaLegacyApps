package dsa.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import dsa.com.Constants;
import dsa.com.Global;
import dsa.enums.SortInfo;

abstract class AbstractSorter
{
	private int operation;
	private long beginNano;
	private long beginTime;
	private long timeVals[] = new long[6];
	private long elapsedTime;
	private long endNano;
	private long endTime;
	private String sortName;
	private String timeString;
	private String performance;
	private int errorNum;
	private int maxStrLen;
	private long nanoTime;
	int elements;
	int width;
	long comparisons;
	long moves;
	boolean sortColumnIsNumeric;
	ArrayList<ArrayList<String>> list2D;
		
	@SuppressWarnings("unchecked")
	AbstractSorter(final String b, final int c)
	{
		sortName = b;
		operation = c;
		if(loadCSV() == false) return;
		for(int z = 0; z < elements; z++) {
			for(int e = 0; e < width; e++) {
				if(e == Global.sortByColumn && sortColumnIsNumeric == false) {
					list2D.get(z).set(e, padLeft(list2D.get(z).get(e).toString(), maxStrLen));
				}
			}
		}
		if(operation == 0) sort();
		else rank();
		readOut();
		saveToCSV();
	}
	private void readOut()
	{
		String log = performance;
		if(Constants.systemOut) {
			System.out.print(performance + "\n\t- Elements: ");
			if(width == 2) {
				int b = 0;
				for(final ArrayList<String> a : list2D) b++;
				System.out.print("\t");
				if(b > 4) {
					for(int c = 0; c < b/4; c++) {
						String a = "";
						String d = "";
						String y = "";
						String z = "";
						for(int i = 1000, j = 0; j != 3; i /= 10, j++) {
							if(Double.parseDouble(list2D.get(c).get(1)) < i) a += " ";
							if(Double.parseDouble(list2D.get((elements/4)+c).get(1)) < i) d += " ";
							if(Double.parseDouble(list2D.get((elements/2)+c).get(1)) < i) y += " ";
							if(Double.parseDouble(list2D.get(elements-(elements/4)+c).get(1)) < i) z += " ";
						}
						a += (int)Double.parseDouble(list2D.get(c).get(1));
						d += (int)Double.parseDouble(list2D.get((elements/4)+ c).get(1));
						y += (int)Double.parseDouble(list2D.get((elements/2)+ c).get(1));
						z += (int)Double.parseDouble(list2D.get(elements-(elements/4)+ c).get(1));
						System.out.print(list2D.get(c).get(0) + "\t" + a + "\t!\t" + 
						list2D.get((elements/4)+ c).get(0) + "\t" + d + "\t!\t" + 
						list2D.get((elements/2)+ c).get(0) + "\t" + y + "\t!\t" + 
						list2D.get(elements-(elements/4)+ c).get(0) + "\t" + z + "\n\t\t\t");
					}
					if(b % 4 != 0) {
						String a = "";
						String d = "";
						String y = "";
						for(int i = 1000, j = 0; j != 3; i /= 10, j++) {
							if(Double.parseDouble(list2D.get(elements-4).get(1)) < i) a += " ";
							if(Double.parseDouble(list2D.get(elements-3).get(1)) < i) d += " ";
							if(Double.parseDouble(list2D.get(elements-2).get(1)) < i) y += " ";
						}
						a += (int)Double.parseDouble(list2D.get(elements-4).get(1));
						d += (int)Double.parseDouble(list2D.get(elements-3).get(1));
						y += (int)Double.parseDouble(list2D.get(elements-2).get(1));
						switch(b % 4) {
						case 1:
							System.out.print(list2D.get(elements-2).get(0) + "\t" + y);
							break;
						case 2:
							System.out.print(list2D.get(elements-3).get(0) + "\t" + d + "\t!\t" + 
								list2D.get(elements-2).get(0) + "\t" + y);
							break;
						case 3:
							System.out.print(list2D.get(elements-4).get(0) + "\t" + a + "\t!\t" + 
								list2D.get(elements-3).get(0) + "\t" + d + "\t!\t" + 
								list2D.get(elements-2).get(0) + "\t" + y);
							break;
						}
						System.out.println("");
					}
				} else {
					for(final ArrayList<String> al : list2D) {
						for(final String d : al) {
							System.out.print(d + " ");
						}
					}
				}
			} else {
				for(final ArrayList<String> a : list2D) {
					for(final String b : a) {
						System.out.print(b + "\t");
					}
					System.out.print("\n");
				}
			}
		}
		log += "\n";
		if(Constants.systemOut) System.out.print("\n");
		Global.performanceLog = log;
	}
	private void timerStart()
	{
		beginNano = System.nanoTime();
		beginTime = System.currentTimeMillis();
	}
	private void timerEnd()
	{
		endNano = System.nanoTime();
		endTime = System.currentTimeMillis();
		nanoTime = endNano - beginNano;
		elapsedTime = endTime - beginTime;
		logPerformance();
	}
	private void logPerformance()
	{
		timeVals[0] = (elapsedTime/1000)/3600;
		timeVals[1] = (elapsedTime/1000)/60;
		timeVals[2] = (elapsedTime/1000)%60;
		timeVals[3] = (nanoTime/1000000)%1000;
		timeVals[4] = (nanoTime/1000)%1000;
		timeVals[5] = nanoTime%1000;
		timeString = "";
		timeString += timeVals[0] < 10 ? "0" + timeVals[0] + ":" : timeVals[0] + ":";
		timeString += timeVals[1] < 10 ? "0" + timeVals[1] + ":" : timeVals[1] + ":";
		timeString += timeVals[2] < 10 ? "0" + timeVals[2] + "." : timeVals[2] + ".";
		if(timeVals[3] < 100) timeString += "0";
		timeString += timeVals[3] < 10 ? "0" + timeVals[3] + ":" : timeVals[3] + ":";
		if(timeVals[4] < 100) timeString += "0";
		timeString += timeVals[4] < 10 ? "0" + timeVals[4] + ":" : timeVals[4] + ":";
		if(timeVals[5] < 100) timeString += "0";
		timeString += timeVals[5] < 10 ? "0" + timeVals[5] + "." : timeVals[5];
		performance = sortName + " Algorithm Performance Statistics" + 
		"\n- Total Moves: " + moves + "\t- Total Comparisons: " +
		comparisons + "\n- Elapsed Time(h:m:s:ms:us:ns): (" + timeString + ")";
	}
	@SuppressWarnings("unchecked")
	private void sort()
	{
		ArrayList[] a = new ArrayList[elements];
		ArrayList b = new ArrayList<Integer>();
		for(int i = 0; i < elements; i++) a[i] = new ArrayList<String>();
		int c = 0;
		final int h = width;
		for(ArrayList<String> s : list2D) {
			for(int i = 0; i < width; i++) a[c].add(s.get(i));
			c++;
		}
		list2D = new ArrayList<ArrayList<String>>();
		c = 0;
		for(ArrayList<String> s : a) {
			list2D.add(c, new ArrayList<String>());
			list2D.get(c).add(s.get(Global.sortByColumn));
			list2D.get(c).add("" + (c));
			c++;
		}
		Global.sortByColumn = 0;
		width = 2;
		timerStart();
		sorter();
		timerEnd();
		String[] strs = new String[Global.dataW];
		for(int n = 0; n < Global.dataW; n++) strs[n] = Global.headers[n];
		Global.headers = strs;
		for(ArrayList<String> s : list2D) b.add(s.get(1));
		for(int d = 0; d < elements; d++) list2D.get(d).add("" + (d + 1));
		list2D = new ArrayList<ArrayList<String>>();
		try {
			for(int i = 0; i < elements; i++) {
				list2D.add(i, new ArrayList<String>());
				for(int j = 0; j < h; j++) list2D.get(i).add("" + a[Integer.parseInt(b.get(i).toString())].get(j));
			}
		} catch (OutOfMemoryError oome) {
			setError(-7);
			return;
		}
	}
	@SuppressWarnings("unchecked")
	private void rank()
	{
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < elements; i++) a.add(i, new ArrayList<String>());
		int c = 0;
		final int h = width;
		for(final ArrayList<String> b : list2D) {
			for(final String str : b) a.get(c).add(str);
			c++;
		}
		if(!Global.loadTable) Global.sortByColumn = 0;
		list2D = new ArrayList<ArrayList<String>>();
		c = 0;
		for(ArrayList<String> b : a) {
			list2D.add(c, new ArrayList<String>());
			list2D.get(c).add(b.get(Global.sortByColumn).toString());
			list2D.get(c).add("" + (c + 1));
			c++;
			if(c < a.size() != true) break;
		}
//	sort it by column 0
		Global.sortByColumn = 0;
		width = 2;
//	Start Timer
		timerStart();
		sorter();
		for(int d = 0; d < elements; d++) list2D.get(d).add("" + (d + 1));
//	resort it by column 1
		Global.sortByColumn = 1;
		width = 3;
		sorter();
//	Stop Timer
		comparisons *= 2;
		moves *= 2;
		timerEnd();
//	By now it should be in original order
		for(int d = 0; d < elements; d++) {
			list2D.get(d).set(0, list2D.get(d).get(2));
			list2D.get(d).remove(2);
			list2D.get(d).remove(1);
		}
		for(int d = 0; d < elements; d++) {
			for(int i = 0; i < h; i++) list2D.get(d).add(i+1, a.get(d).get(i));
		}
		Global.listWidth = h+1;
		width = h+1;
		String[] s = new String[Global.dataW];
		s[0] = "Ranking";
		for(int col = 1; col < Global.dataW; col++) {
			if(Global.headers != null) s[col] = Global.headers[col-1];
			else s[col] = "Column " + col;
		}
		Global.headers = new String[Global.dataW];
		for(int col = 0; col < Global.dataW; col++) Global.headers[col] = s[col];
	}
	private void saveToCSV()
	{
		PrintStream out = null;
		BufferedReader bufRdr = null;
		try {
			if(operation == 1) bufRdr = new BufferedReader(new FileReader(new File(Global.loadPath)));
			out = new PrintStream(new FileOutputStream(Global.savePath));
			if(Global.outHeader) {
				if(operation == 0) {
					String u = "";
					if(Global.headers != null) {
						for(String s : Global.headers) {
							if(s != null) u += s + ",";
						}
					} else {
						for(int i = 0; i < width; i++) u += "Column " + (i+1) + ",";
					}
					u = u.substring(0, u.length()-1);
					if(u != null) out.println(u);
				} else if(operation == 1) {
					String line = "";
					String[] fields = null;
					if((line = bufRdr.readLine()) != null) fields = line.split(",");
					String u = "Ranking";
					if(fields != null) {
						int i = 0;
						for(String s : fields) {
							if(s != null && i < Global.dataW) {
								u += "," + s;
								i++;
							} else break;
						}
					} else {
						for(int i = 0; i < Global.dataW; i++) u += ",Column " + (i+1);
					}
					if(u != null) out.println(u);
				}
			}
			if(operation == 0) {
				for(ArrayList<String> s : list2D) {
					if(s == null) {
						setError(-7);
						return;
					}
					String u = "";
					for(String t : s) {
						if(t != null) u += t.trim() + ",";
					}
					u = u.substring(0, u.length()-1);
					if(u != null) out.println(u);
				}
			} else if(operation == 1) {
				for(int i = 0; i < elements; i++) {
					String line = "";
					String[] fields = null;
					if((line = bufRdr.readLine()) != null) fields = line.split(",");
					String u = list2D.get(i).get(0);
					if(fields != null) {
						int j = 0;
						for(String s : fields) {
							if(s != null && j < Global.dataW) {
								u += "," + s;
								j++;
							} else break;
						}
					}
					if(u != null) out.println(u);
				}
			}
			out.close();
		} catch (FileNotFoundException z) {
			if(Constants.systemOut) System.out.println("Could not save to file. File is being used.");
			setError(-1);
			return;
		} catch (IOException ioe) {
			setError(-5);
			return;
		}
	}
	@SuppressWarnings("unchecked")
	private boolean loadCSV()
	{
		BufferedReader bufRdr;
		final File file = new File(Global.loadPath);
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			setError(-4);
			return false;
		}
		ArrayList<ArrayList<String>> field = new ArrayList<ArrayList<String>>();
		if(Global.loadTable || operation == 0) {
			for(int a = 0; a < Global.listHeight; a++) field.add(a, new ArrayList<String>());
			{
				int i = 0;
				int j = 0;
				try {
					String line = null;
					for(; i < Global.listHeight && (line = bufRdr.readLine()) != null; i++, j = 0) {
						if(Constants.systemOut) System.out.print("Loaded Row " + i);
						final String[] tempArray = line.split(",");
						for(String a : tempArray) {
							if(j < Global.listWidth != true) break;
							field.get(i).add(a.toString().substring(0, a.length()));
							j++;
						}
						if(Constants.systemOut) System.out.print(" Sub-element 0 - " + j + "\n");
						Global.dataW = j;
					}
					Global.dataH = (Global.inHeader) ? i-1 : i;
					if(Constants.systemOut) System.out.println("CSV Loaded Successfully.");
					bufRdr.close();
				} catch (IOException e) {
					setError(-5);
					return false;
				} catch (OutOfMemoryError oome) {
					System.out.println("Loaded up to: Row " + i + ", Col " + j + ".");
					setError(-7);
					return false;
				}
			}
		} else {
			for(int a = 0; a < Global.listHeight; a++) field.add(a, new ArrayList<String>());
			{
				int i = 0;
				int j = 0;
				try {
					String line = null;
					for(; i < Global.listHeight && (line = bufRdr.readLine()) != null; i++, j = 0) {
						if(Constants.systemOut) System.out.print("Loaded Row " + i);
						final String[] tempArray = line.split(",");
						for(final String str : tempArray) {
							if(j < Global.listWidth != true) break;
							if(j == Global.sortByColumn) field.get(i).add(str.toString().substring(0, str.length()));
							j++;
						}
						if(Constants.systemOut) System.out.print(" Sub-element 0 - " + j + "\n");
						Global.dataW = j;
					}
					Global.sortByColumn = 0;
					Global.dataH = (Global.inHeader) ? i-1 : i;
					if(Constants.systemOut) System.out.println("CSV Loaded Successfully.");
					bufRdr.close();
				} catch (IOException e) {
					setError(-5);
					return false;
				} catch (OutOfMemoryError oome) {
					System.out.println("Loaded up to: Row " + i + ", Col " + j + ".");
					setError(-7);
					return false;
				}
			}
		}
//	Optional: print out the extracted data
		if(Constants.systemOut) {
			System.out.println("Now displaying CSV data.");
			for(final ArrayList<String> s : field) {
				for(final String t : s) System.out.print(t + " ");
				System.out.print("\n");
			}
		}
//	Copy the required fields into the list
		{
			int rows = 0;
			int cols = 0;
			int maxStringLength = 0;
			int tempInt = 0;
			sortColumnIsNumeric = true;
			for(ArrayList<String> d : field) {
				if(Global.inHeader && tempInt == 0) {
					tempInt = 1;
					continue;
				}
				if(d == null || d.isEmpty() || d.get(Global.sortByColumn).equals(null) || d.size() == 0) break;
				else {
					rows++;
					cols = 0;
					for(@SuppressWarnings("unused")String temp : d) cols++;
					try {
						Double.parseDouble(d.get(Global.sortByColumn));
						if(maxStringLength < d.get(Global.sortByColumn).length()) maxStringLength = d.get(Global.sortByColumn).length();
					} catch(NumberFormatException nfe) {
						sortColumnIsNumeric = false;
					}
				}
			}
			if(sortColumnIsNumeric == false && sortName == SortInfo.LSDRadix.getName()) {
				setError(-3);
				return false;
			}
			width = (cols < Global.listWidth) ? cols : Global.listWidth;
			elements = (rows < Global.listHeight) ? rows : Global.listHeight;
			width = width < 1 ? 1 : width;
			elements = elements < 1 ? 1 : elements;
			list2D = new ArrayList<ArrayList<String>>();
			maxStrLen = maxStringLength;
		}
		try {
			for(int z = 0; z < elements; z++) list2D.add(z, new ArrayList<String>());
			int d = -1;
			Global.headers = null;
			for(final ArrayList<String> s : field) {
				if(Global.inHeader && d == -1) {
					int i = 0;
					Global.headers = new String[Global.listWidth];
					for(final String t : s) {
						if(i >= Global.listWidth || Global.listWidth == 0) break;
						if(t == null) break;
						Global.headers[i] = t;
						i++;
						d = 0;
					}
				} else {
					if(d == -1) d = 0;
					if(s.size() == 0) break;
					int c = 0;
					for(String t : s) {
						if(c >= Global.listWidth || Global.listWidth == 0) break;
						if(t == null) break;
						list2D.get(d).add(t);
						c++;
					}
					d++;
				}
			}
		} catch (OutOfMemoryError oome) {
			setError(-7);
			return false;
		}
		if(Constants.systemOut) System.out.println("Closing file.");
		return true;
	}
	
	protected String padLeft(final String a, final int b)
	{
		String d = "";
		for(int c = a.length(); c < b; c++) {
			d += " ";
		}
		d += a;
		return d;
	}
	protected String padRight(final String a, final int b)
	{
		String d = a;
		for(int c = a.length(); c < b; c++) {
			d += " ";
		}
		return d;
	}
	protected int getDigit(double n, int d)
	{
		double i = 1;
		int e = 0;
		if(d < 1) {
			try {
				final String str = (new Double(n)).toString();
				final int startVal = str.indexOf(".");
				final String digit = "" + str.charAt(startVal+(1-d));
				e = Integer.parseInt(digit);
			} catch(Exception exc) {
				return 0;
			}
		} else {
			for(int a = 0; a < d; a++, i *= 10);
			e = (int)((n%(i))/(i/10));
		}
		if(e < 0) e *= -1;
		return e;
	}
	protected boolean inOrder()
	{
		if(width != 1) {
			for(int i = 0; i < elements -1; i++) {
				if(Double.parseDouble(list2D.get(i).get(Global.sortByColumn)) > Double.parseDouble(list2D.get(i+1).get(Global.sortByColumn))) return false;
			}
			return true;
		}
		return false;
	}
	protected void swap(final int a, final int b)
	{
		ArrayList<String> c = new ArrayList<String>();
		for(int i = 0; i < width; i++) {
			c.add(list2D.get(a).get(i));
			list2D.get(a).set(i, list2D.get(b).get(i));
			list2D.get(b).set(i, c.get(i));
		}
	}
	protected void setError(final int a)
	{
		errorNum = a;
	}
	protected abstract void sorter();
	public int getError()
	{
//	0 = no error
//	-1 = file save error
//	-2 = unknown error
//	-3 = cannot sort error
		return errorNum;
	}
	@SuppressWarnings("unchecked")
	public ArrayList getData()
	{
		return list2D;
	}

	
	
	
	
	
	
	
	@Deprecated
	ArrayList<Double> list;
	@Deprecated
	AbstractSorter(ArrayList<Double> a, String d, boolean output)
	{
		elements = Global.listHeight;
		list = new ArrayList<Double>();
		for(int i = 0; i < elements; i++) list.add(i, a.get(i));
		sortName = d;
	}
	@Deprecated
	public abstract void sort1D();
}