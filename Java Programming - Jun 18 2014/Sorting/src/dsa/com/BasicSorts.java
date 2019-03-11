package dsa.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dsa.algorithm.BubbleSort;
import dsa.algorithm.LSDRadixSort;
import dsa.algorithm.MergeSort;
import dsa.algorithm.MinMaxSort;
import dsa.algorithm.ShakerSort;

public class BasicSorts
{	
	private BubbleSort bubble;
	private MergeSort merger;
	private MinMaxSort minMax;
	private LSDRadixSort radix;
	private ShakerSort shaker;
	private int errorN = 0;
	private int operation = 0;
	
	public int initiate(final int a)
	{
		operation = a;
		callSorter();
		return errorN;
	}
	@SuppressWarnings("unchecked")
	private boolean callSorter()
	{
		switch(Global.sortType) {
		case 1:
			bubble = new BubbleSort(operation);
			errorN = bubble.getError();
			break;
		case 2:
			shaker = new ShakerSort(operation);
			errorN = shaker.getError();
			break;
		case 3:
			merger = new MergeSort(operation);
			errorN = merger.getError();
			break;
		case 4:
			minMax = new MinMaxSort(operation);
			errorN = minMax.getError();
			break;
		case 5:
			radix = new LSDRadixSort(operation);
			errorN = radix.getError();
			break;
		default:
			break;
		}
		if(errorN != 0) return false;
		return true;
	}
	@SuppressWarnings("unchecked")
	public ArrayList getSorted()
	{
		switch(Global.sortType) {
		case 1:
			return bubble.getData();
		case 2:
			return shaker.getData();
		case 3:
			return merger.getData();
		case 4:
			return minMax.getData();
		case 5:
			return radix.getData();
		}
		return null;
	}

	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	@Deprecated
	private ArrayList[] list;
	@Deprecated
	@SuppressWarnings("unchecked")
	public ArrayList[] getUnsorted()
	{
		int a = 0;
		for(final ArrayList<String> b : list) {
			if(b.size() != 0) a++;
		}
		ArrayList[] arrayList = new ArrayList[a];
		int c = 0;
		for(final ArrayList<String> b : list) {
			if(b.size() != 0 && c < a) {
				arrayList[c] = b;
				c++;
			}
		}
		return arrayList;
	}
	@Deprecated
	@SuppressWarnings("unchecked")
	boolean loadCSV()
	{
		BufferedReader bufRdr;
		final File file = new File(Global.loadPath);
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			errorN = -4;
			return false;
		}
		ArrayList[] field = new ArrayList[Global.listHeight];
		for(int a = 0; a < Global.listHeight; a++) field[a] = new ArrayList<String>();
		try {
			int i = 0;
			String line = null;
			for(int j = 0; i < Global.listHeight && (line = bufRdr.readLine()) != null; i++, j = 0) {
				System.out.print("Loaded Row " + i);
				final String[] tempArray = line.split(",");
				for(String a : tempArray) {
					if(j < Global.listWidth != true) break;
					field[i].add(a.toString().substring(0, a.length()));
					j++;
				}
				System.out.print(" Sub-element 0 - " + j + "\n");
				Global.listWidth = j;
			}
			Global.listHeight = (Global.inHeader) ? i-1 : i;
			if(Constants.systemOut) System.out.println("CSV Loaded Successfully.");
		} catch (IOException e) {
			errorN = -5;
			return false;
		} catch (OutOfMemoryError oome) {
			errorN = -7;
			return false;
		}
//Optional: print out the extracted data
		if(Constants.systemOut) {
			System.out.println("Now displaying CSV data.");
			for(final ArrayList<String> s : field) {
				for(final String t : s) System.out.print(t + " ");
				System.out.print("\n");
			}
		}
//Copy the required fields into the list
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
					list[d].add(t);
					c++;
				}
				d++;
			}
		}
		if(Constants.systemOut) System.out.println("Closing file.");
		return true;
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	void generate()
	{
		Random generator;
		switch(Constants.situation) {
		case 0:
			if(Constants.randSeed != 0) {
				generator = new Random(Constants.randSeed);
			} else {
				generator = new Random();
			}
			if(Global.listWidth > 1) {
				for(int i = 0; i < Global.listHeight; i++) {
					double c = generator.nextInt(Constants.randRange) + 1;
					list[i].add(c);
					list[i].add(i);
				}
			}
			break;
		case 1:
			if(Global.listWidth > 1) {
				for(int i = 0; i < Global.listHeight; i++) {
					list[i].add(i);
					list[i].add(i);
				}
			}
			break;
		case 2:
			if(Global.listWidth > 1) {
				for(int i = 0; i < Global.listHeight; i++) {
					if(i % 8 == 7) {
						list[i].add(i-6);
						list[i].add(i);
					} else if(i % 8 == 1) {
						list[i].add(i+6);
						list[i].add(i);
					} else {
						list[i].add(i);
						list[i].add(i);
					}
				}
			}
			break;
		case 3:
			if(Global.listWidth > 1) {
				for(int i = 0, j = Global.listHeight -1; i < Global.listHeight; i++) {
					list[i].add(j-i);
					list[i].add(i);
				}
			}
			break;
		}
	}
}
