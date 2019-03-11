package dsa.algorithm;

import java.util.ArrayList;

import dsa.com.Global;
import dsa.enums.SortInfo;


public class ShakerSort extends AbstractSorter
{	public ShakerSort(final int b)
	{
		super(SortInfo.Shaker.getName(), b);
	}
	public void sorter()
	{
		int p = 0;
		int q = 0;
		final int n = elements-1;
//	the for loop shifts larger elements to the bottom of the array and smaller one to the top
//	and passes in both directions to eliminate "turtles", small elements at the bottom of an array
//	as well as reducing the number of elements checked per pass
		for(int b = 0; b < n-b; b++) {
			if(sortColumnIsNumeric) {
				for(int c = b; c < n-b; c++, q += 2) {
					if(Double.parseDouble(list2D.get(c).get(Global.sortByColumn)) > Double.parseDouble(list2D.get(c+1).get(Global.sortByColumn))) {
						swap(c, c+1);
						p += width;
					}
					if(Double.parseDouble(list2D.get((n-b)-c).get(Global.sortByColumn)) < Double.parseDouble(list2D.get((n-b)-(c+1)).get(Global.sortByColumn))) {
						swap((n-b)-c, (n-b)-(c+1));
						p += width;
					}
				}
			} else {
				for(int c = b; c < n-b; c++, q += 2) {
					if(list2D.get(c).get(Global.sortByColumn).compareToIgnoreCase(list2D.get(c+1).get(Global.sortByColumn)) > 0) {
						swap(c, c+1);
						p += width;
					}
					if(list2D.get((n-b)-c).get(Global.sortByColumn).compareToIgnoreCase(list2D.get((n-b)-(c+1)).get(Global.sortByColumn)) < 0) {
						swap((n-b)-c, (n-b)-(c+1));
						p += width;
					}
				}
			}
		}
		moves = p;
		comparisons = q;
	}
	
	
	
	
	
	
	
	@Deprecated
	public ShakerSort(ArrayList<Double> a, boolean output)
	{
		super(a, "Shaker Sort", output);
	}
	@Deprecated
	public void sort1D()
	{
		int p = 0;
		int q = 0;
		int n = elements;
		for(int b = n-1, c = 0; b > c; b--, c++, p++) {
			for(int d = c; d < b; d++, q++) {
				if(list.get(d) > list.get(d+1)) swap(d, d+1);
			}
			for(int e = b; e > c; e--, q++) {
				if(list.get(e) < list.get(e-1)) swap(e, e-1);
			}
		}
		moves = p;
		comparisons = q;
	}
}
