package dsa.algorithm;

import java.util.ArrayList;

import dsa.com.Global;
import dsa.enums.SortInfo;

public class BubbleSort extends AbstractSorter
{	
	public BubbleSort(final int b)
	{
		super(SortInfo.Bubble.getName(), b);
	}
	public void sorter()
	{
		int p = 0;
		int q = 0;
		int n = elements;
//	loops through the array, decreasing subloop size per pass, shifting smaller elements to the top
//	and larger elements to the bottom
		if(sortColumnIsNumeric) {
			for(int b = n-1; b > 0; b--) {
				for(int c = 0; c < b; c++, q++) {
//	call the swap element function
					if(Double.parseDouble(list2D.get(c).get(Global.sortByColumn)) > Double.parseDouble(list2D.get(c+1).get(Global.sortByColumn))) {
						swap(c, c+1);
						p += width;
					}
				}
			}
		} else {
			for(int b = n-1; b > 0; b--) {
				for(int c = 0; c < b; c++, q++) {
					if(list2D.get(c).get(Global.sortByColumn).compareToIgnoreCase(list2D.get(c+1).get(Global.sortByColumn)) > 0) {
						swap(c, c+1);
						p += width;
					}
				}
			}
		}
		moves = p;
		comparisons = q;
	}
	
	
	
	
	
	
	
	
	
	
	@Deprecated
	public BubbleSort(ArrayList<Double> a, boolean output)
	{
		super(a, "Bubble Sort", output);
	}
	@Deprecated
	public void sort1D()
	{
		int p = 0;
		int q = 0;
		int n = elements;
		for(int b = n-1; b > 0; b--, p++) {
			for(int c = 0; c < b; c++, q++) {
				if(list.get(c) > list.get(c+1)) swap(c, c+1);
			}
		}
		moves = p;
		comparisons = q;
	}
}
