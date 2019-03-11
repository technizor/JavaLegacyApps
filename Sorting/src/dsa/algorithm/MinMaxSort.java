package dsa.algorithm;

import java.util.ArrayList;

import dsa.com.Global;
import dsa.enums.SortInfo;

public class MinMaxSort extends AbstractSorter
{
	public MinMaxSort(final int b)
	{
		super(SortInfo.MinMax.getName(), b);
	}
	@SuppressWarnings("unchecked")
	public void sorter()
	{
/*
*	This method sorts the elements in the array by
*	recursively extracting the highest and lowest values
*	and copying them into a temporary array
*	low values go at the beginning, high ones go at the end
*	you should only implement this sort for randomized sets
*	or ones that are reversed
*	because this algorithm wastes time on nearly sorted and sorted sets of data
*	start the performance timer that tracks the speed of the algorithm
*/
		int p = 0;
		int q = 0;
/*
*	this version requires an array of temporary values
*	temporary array stores the sorted values for writing later on
*	ordered boolean array tracks which elements in the original array
*	have already been copied into their proper position
*/
		ArrayList[] temp = new ArrayList[elements];
		for(int i = 0; i < elements; i++) temp[i] = new ArrayList<String>(width);
		boolean[] ordered = new boolean[elements];
		for(int i = 0; i < elements; i++) ordered[i] = false;
		for(int orders = 0, minLoc = -1, maxLoc = -1, low = 0, high = elements;
			(orders*2) < elements; orders++, ordered[minLoc] = ordered[maxLoc] = true,
			minLoc = maxLoc = -1) {	
/*
*	Until you have taken care of every single unsorted element,
*	The for loop will reset values needed to be reset every time
*/
			for(int a = low; a < high; a++, p++) {
				if(ordered[a] != true) {
/*
*	minLocation and maxLocation are unset when at value -1. 
*	this if statement prevents ArrayOutOfBoundsException by setting it no matter what
*	as long as that element has not been set yet.
*/
					if(minLoc == -1 && maxLoc == -1) minLoc = maxLoc = a;
/*
*	if this particular element is the new lowest value, and
*	if this particular element is the new highest value
*	note that the comparison is equal to or greater:
*	this is so that equal values farther up the array
*	are placed at the back, ensuring a stable sort.
*	the lowest value comparison does not need the equal to portion,
*	because you are incrementing from the start of the array
*/
					else {
						if(sortColumnIsNumeric) {
							if(Double.parseDouble(list2D.get(a).get(Global.sortByColumn)) < Double.parseDouble(list2D.get(minLoc).get(Global.sortByColumn))) minLoc = a;
							if(Double.parseDouble(list2D.get(a).get(Global.sortByColumn)) >= Double.parseDouble(list2D.get(maxLoc).get(Global.sortByColumn))) maxLoc = a;
						} else {
							if(list2D.get(a).get(Global.sortByColumn).compareToIgnoreCase(list2D.get(minLoc).get(Global.sortByColumn)) < 0) minLoc = a;
							if(list2D.get(a).get(Global.sortByColumn).compareToIgnoreCase(list2D.get(maxLoc).get(Global.sortByColumn)) >= 0) maxLoc = a;
						}
					}
					q += 2;
				}
			}
/*
*	statements here set the temporary array values(on both ends)
*	as the highest and lowest elements that have not been put into order yet
*	then increments the counters and resets the in and max locations.
*/
			for(int c = 0; c < width; c++) {
				if(minLoc != maxLoc) {
					temp[orders].add(list2D.get(minLoc).get(c));
					temp[elements-(orders+1)].add(list2D.get(maxLoc).get(c));
					p += 2;
				} else {
					temp[orders].add(list2D.get(minLoc).get(c));
					p++;
				}
			}
		}
//	overwrites the original array with the stable sorted values
		for(int i = 0; i < elements; i++) {
			for(int c = 0; c < width; c++, p++) list2D.get(i).set(c, temp[i].get(c).toString());
		}
//	stops the performance timer
		moves = p;
		comparisons = q;
	}
	
	
	
	
	
	
	
	

	@Deprecated
	public MinMaxSort(ArrayList<Double> a, boolean output)
	{
		super(a, "Min-Max Sort", output);
	}
	@Deprecated
	public void sort1D()
	{
		int p = 0;
		int q = 0;
		double[] temp = new double[elements];
		boolean[] ordered = new boolean[elements];
		for(int orders = 0, minLoc = -1, maxLoc = -1,low = 0, high = elements;
			(orders*2) < elements; orders++, ordered[minLoc] = ordered[maxLoc] = true,
			minLoc = maxLoc = -1) {
			for(int a = low; a < high; a++, p++) {
				if(ordered[a] != true) {
					if(minLoc == -1 && maxLoc == -1) {
						minLoc = a;
						maxLoc = a;
					} else {
						if(list.get(a) < list.get(minLoc)) minLoc = a;
						if(list.get(a) >= list.get(maxLoc)) maxLoc = a;
					}
					q += 2;
				}
			}
			temp[orders] = list.get(minLoc);
			temp[elements-(orders+1)] = list.get(maxLoc);
		}
		for(int i = 0; i < elements; i++) list.set(i, temp[i]);
		moves = p;
		comparisons = q;
	}
}
