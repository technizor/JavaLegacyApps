package dsa.algorithm;

import java.util.ArrayList;

import dsa.com.Global;
import dsa.enums.SortInfo;

public class MergeSort extends AbstractSorter
{
	public MergeSort(final int b)
	{
		super(SortInfo.Merge.getName(), b);
	}

	public void sorter()
	{
//	Begin the recursion
		spliter(0, elements-1);
	}
	@SuppressWarnings("unchecked")
	private void merger(final int low, final int middle, final int high)
	{
//	temporary array
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>((high+1)-low);
//	Copy both parts into the temporary array
		for(int i = 0; i < low; i++) temp.add(new ArrayList<String>());
		for (int i = low; i <= high; i++) {
			temp.add(i, new ArrayList<String>());
			for(int c = 0; c < width; c++) temp.get(i).add(list2D.get(i).get(c));
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		int q = 0;
//	Copy the smallest values from either the left or the right side back to the original array
		if(sortColumnIsNumeric) {
			while (i <= middle && j <= high) {
				if (Double.parseDouble(temp.get(i).get(Global.sortByColumn).toString()) <= Double.parseDouble(temp.get(j).get(Global.sortByColumn).toString())) {
					for(int c = 0; c < width; c++) list2D.get(k).set(c, temp.get(i).get(c).toString());
					i++;
				} else {
					for(int c = 0; c < width; c++) list2D.get(k).set(c, temp.get(j).get(c).toString());
					j++;
				}
				k++;
				q++;
			} 
		} else {
			while (i <= middle && j <= high) {
				if (temp.get(i).get(Global.sortByColumn).toString().compareToIgnoreCase(temp.get(j).get(Global.sortByColumn).toString()) <= 0) {
					for(int c = 0; c < width; c++) list2D.get(k).set(c, temp.get(i).get(c).toString());
					i++;
				} else {
					for(int c = 0; c < width; c++) list2D.get(k).set(c, temp.get(j).get(c).toString());
					j++;
				}
				k++;
				q++;
			}
		}
//	Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			for(int c = 0; c < width; c++) list2D.get(k).set(c, temp.get(i).get(c).toString());
			k++;
			i++;
			q++;
		}
		temp = null;
		moves += high - low;
		comparisons += q;
	}
	private void spliter(final int low, final int high) {
//	Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
//	Get the index of the element which is in the middle
			final int middle = (low + high) / 2;
//	Sort the both sides of the array by calling itself
			spliter(low, middle);
			spliter(middle + 1, high);
//	and recombine them both
			merger(low, middle, high);
		}
	}
	
	
	
	
	
	@Deprecated
	public MergeSort(ArrayList<Double> a, boolean output)
	{
		super(a, "Merge Sort", output);
	}
	@Deprecated
	private void merge(int low, int middle, int high)
	{
		double[] temp = new double[elements];
		for (int i = low; i <= high; i++) {
			temp[i] = list.get(i);
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		int p = 0;
		int q = 0;
		while (i <= middle && j <= high) {
			p++;
			q++;
			if (temp[i] <= temp[j]) {
				list.set(k, temp[i]);
				i++;
			} else {
				list.set(k, temp[j]);
				j++;
			}
			k++;
		}
		while (i <= middle) {
			list.set(k, temp[i]);
			k++;
			i++;
			p++;
			q++;
		}
		temp = null;
		moves += p;
		comparisons += q;
	}
	@Deprecated
	public void sort1D()
	{
		//Sort
		split(0, elements-1);
	}
	@Deprecated
	private void split(int low, int high) {
		moves++;
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = (low + high) / 2;
			// Sort the left side of the array
			split(low, middle);
			// Sort the right side of the array
			split(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}
}
