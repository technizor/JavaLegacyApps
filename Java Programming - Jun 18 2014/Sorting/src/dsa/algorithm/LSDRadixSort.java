package dsa.algorithm;

import java.util.ArrayList;

import dsa.com.Global;
import dsa.enums.SortInfo;

public class LSDRadixSort extends AbstractSorter
{
	public LSDRadixSort(final int b)
	{
		super(SortInfo.LSDRadix.getName(), b);
	}
@SuppressWarnings("unchecked")
	protected void sorter()
	{
		if(sortColumnIsNumeric) {
			double max = 0;
			int maxTen = 1;
			int maxDeci = 0;
			for(final ArrayList<String> a : list2D) {
				String strVal = "";
				if(max < Double.parseDouble(a.get(Global.sortByColumn))) max = Double.parseDouble(a.get(Global.sortByColumn));
				if((strVal = a.get(Global.sortByColumn).toString()).contains(".")) {
					final int deciPlace = strVal.length() - (strVal.lastIndexOf(".")+1);
					maxDeci = (deciPlace > maxDeci) ? deciPlace : maxDeci;
				}
			}
//	Find the largest value in the array and use it to find the greatest power of ten required
//	to sort all  the elements
			for(int powTen = 1; powTen <= max; powTen *= 10, maxTen++);
/*
*	this 2D array is basically 10 arrays that can each hold up to the max number of elements
*	the elements are moved to here and then moved back as part of the algorithm
*/
			ArrayList<ArrayList<ArrayList<String>>> radix = new ArrayList<ArrayList<ArrayList<String>>>();
			for(int i = 0; i < 20; i++) {
				radix.add(i, new ArrayList<ArrayList<String>>());
				for(int c = 0; c < width; c++) radix.get(i).add(c, new ArrayList<String>());
			}
			int startPos = 1;
			for(int i = 0; i < maxDeci; i++) startPos--;
/*		
*	Ensure that all values will be sorted by setting the maximum 10^x
*	this will allow this method to be adaptable to the varying sizes of the values,
*	allowing for flexibility as well as saving some time in lower value arrays
*/	
			for(int d = 0, e = startPos; e < maxTen; e++, d = 0) {
//	this iterative for loop allows you to sort the values with every existing digit
//	this copies the elements into their respective digit arrays
				for(int a = 0; a < elements; a++) {
					final int b = getDigit(Double.parseDouble(list2D.get(a).get(Global.sortByColumn)), e);
					if(Double.parseDouble(list2D.get(a).get(Global.sortByColumn)) >= 0) {
						for(int c = 0; c < width; c++) radix.get(b+10).get(c).add(list2D.get(a).get(c));
//	Positive numbers
					} else {
						for(int c = 0; c < width; c++) radix.get(9-b).get(c).add(list2D.get(a).get(c));
//	Negative numbers
					}
				}
//	and then they are copied back in ascending order, -9 to 0 to +9
				for(int i = 0; i < 20; i++) {
					for(int c = 0; c < radix.get(i).get(0).size(); c++, d++) {
						for(int f = 0; f < width; f++) list2D.get(d).set(f, radix.get(i).get(f).get(c).toString());
					}
					for(int c = 0; c < width; c++) radix.get(i).get(c).clear();
				}
				moves = elements*width*(maxTen+maxDeci-1)*2;
			}
		} else {
			setError(-3);
			return;
			/*int maxStrLen = 0;
			for(final ArrayList<String> a : list2D) {
				int strLen = a.get(Global.sortByColumn).length();
				if(maxStrLen < strLen) maxStrLen = strLen;
			}
			int n = 0;
			for(final ArrayList<String> a : list2D) {
				list2D.get(n).set(Global.sortByColumn, padRight(a.get(Global.sortByColumn).trim(), maxStrLen));
				n++;
			}
			ArrayList<ArrayList<ArrayList<String>>> radix = new ArrayList<ArrayList<ArrayList<String>>>();
			for(int i = 0; i < 128; i++) {
				radix.add(i, new ArrayList<ArrayList<String>>());
				for(int c = 0; c < width; c++) radix.get(i).add(c, new ArrayList<String>());
			}
			for(int e = 0; e < maxStrLen; e++) {
				for(int a = 0; a < elements; a++) {
					final int b = (e >= list2D.get(a).get(Global.sortByColumn).length())? 0 
							: list2D.get(a).get(Global.sortByColumn).toLowerCase().charAt(e);
					for(int c = 0; c < width; c++) radix.get(b).get(c).add(list2D.get(a).get(c));
				}
				if(list2D != null);
				for(int i = 0; i < 128; i++) {
					if(radix.get(i).get(0).size() == 0) continue;
					if(radix.get(i).get(0).size() > 1) radix.set(i, msdRadixSort(radix.get(i), 1, maxStrLen));
					for(int c = 0; c < radix.get(i).get(0).size(); c++) {
						for(int f = 0; f < width; f++) list2D.get(c).set(f, radix.get(i).get(f).get(c).toString());
					}
					for(int c = 0; c < width; c++) radix.get(i).get(c).clear();
				}
			}*/
		}
	}
	/*@SuppressWarnings("unchecked")
	private ArrayList<ArrayList<String>> msdRadixSort(final ArrayList<ArrayList<String>> arrayList, final int pos, final int maxStrLen)
	{
		ArrayList<ArrayList<String>> list = arrayList;
		ArrayList<ArrayList<ArrayList<String>>> radix = new ArrayList<ArrayList<ArrayList<String>>>();
		for(int i = 0; i < 128; i++) {
			radix.add(i, new ArrayList<ArrayList<String>>());
			for(int c = 0; c < width; c++) radix.get(i).add(c, new ArrayList<String>());
		}
		for(int a = 0; a < list.size(); a++) {
			final int b = (pos >= list.get(a).get(Global.sortByColumn).length()) ? 0 
					: list.get(a).get(Global.sortByColumn).toLowerCase().charAt(pos); 
			for(int c = 0; c < width; c++) radix.get(b).get(c).add(list.get(a).get(c));
		}
		for(int i = 0; i < 128; i++) {
			if(radix.get(i).get(0).size() == 0) continue;
			if(radix.get(i).get(0).size() > 1) radix.set(i, msdRadixSort(radix.get(i), pos+1, maxStrLen));
			for(int c = 0; c < radix.get(i).get(0).size(); c++) {
				for(int f = 0; f < width; f++) list.get(c).set(f, radix.get(i).get(f).get(c).toString());
			}
			for(int c = 0; c < width; c++) radix.get(i).get(c).clear();
		}
		return list;
	}*/





	@Deprecated
	public LSDRadixSort(ArrayList<Double> a, boolean output)
	{
		super(a, "LSD RadixSort", output);
	}
	@Deprecated
	@SuppressWarnings("unchecked")
	public void sort1D()
	{
	//start the timer
		int p = 0;
		double max = 0;
		int maxTen = 1;
	/*
	*	element count array keeps track of the number of array values stored
	*	this is important so you do not copy zero values back in
	*/
		int[] elementCount = new int[10];
	/*
	*	this 2D array is basically 10 arrays that can each hold up to the max number of elements
	*	the elements are moved to here and then moved back as part of the algorithm
	*/
		ArrayList[] radix = new ArrayList[10];
		for(int i = 0; i < 10; i++) radix[i] = new ArrayList<Double>();
	//Find the largest value in the array
		for(double a : list) {
			if(max < a) max = a;
		}
	/*		
	*	Ensure that all values will be sorted by setting the maximum 10^x
	*	this will allow this method to be adaptable to the varying sizes of the values,
	*	allowing for flexibility as well as saving some time in lower value arrays	
	*/		
		for(int powTen = 1; powTen <= max; powTen *= 10, maxTen++);
	//this iterative for loop allows you to sort the values with every existing digit
		for(int d = 0, e = 1; e < maxTen; e++, d = 0, elementCount = new int[10], p++) {
	//this copies the elements into the correct digit array
			for(int a = 0; a < elements; a++, p++) {
				int b = getDigit(list.get(a), e);
				radix[b].add(elementCount[b], list.get(a));
				elementCount[b]++;
			}
	//and then they are copied back in ascending order, 0 - 9
			for(int i = 0; i < 10; i++, p++) {
				for(int c = 0; c < elementCount[i]; c++, d++) list.set(d, (Double) radix[i].get(c));
				radix[i].clear();
			}
		}
	//stop the timer
		moves = p;
	}
}
