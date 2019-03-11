package toh.com;

import java.util.Stack;

public class Towers
{
	private int arraySize;
	private int[] layers;
	private int level;
	
	public Towers(int height, Boolean full)
	{
		arraySize = height;
		layers = new int[arraySize];
		level = -1;
		if(full) {
			fillStack();
		} else {
			emptyStack();
		}
	}
	private void emptyStack()
	{
		for(int i = 0; i < arraySize; i++) {
			layers[i] = 0;
		}
		level = -1;
	}
	private void fillStack()
	{
		for(int i = 0; i < arraySize; i++) {
			layers[i] = arraySize - i;
		}
		level = arraySize - 1;
	}
	private Boolean isEmpty()
	{
		Boolean a = level == -1 ? true : false;
		return a;
	}
	private Boolean isFull()
	{
		Boolean a = level == arraySize - 1 ? true : false;
		return a;
	}
	public int takeItem()
	{
		int a = 0;
		if(isEmpty() == false) {
			int i = 0;
			for(i = arraySize - 1; (i > -1 && layers[i] == 0); i--) {
			}
			a = layers[i];
			layers[i] = 0;
			level--;
		}
		return a;
	}
	public int getTop()
	{
		int a = 0;
		if(isEmpty() == false) {
			int i = 0;
			for(i = arraySize - 1; (i > -1 && layers[i] == 0); i--) {
			}
			a = layers[i];
		}
		return a;
	}
	public Boolean putItem(int a)
	{
		if(isFull() == true) {
			return false;
		} else if(isEmpty() == false) {
			Integer b = getTop();
			if(a >= b) {
				return false;
			} else {
				layers[level+1] = a;
				level++;
			}
		} else {
			layers[level+1] = a;
			level++;
		}
		return true;
	}
	public int getHeight()
	{
		return level+1;
	}
	public int getSize()
	{
		if(isEmpty()) {
			return 0;
		} else {
			return layers[level];
		}
	}
}
