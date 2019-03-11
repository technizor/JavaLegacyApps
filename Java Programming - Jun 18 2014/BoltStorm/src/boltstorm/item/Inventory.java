package boltstorm.item;

import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Item> itemList;
	
	public Inventory()
	{
		itemList = new ArrayList<Item>();
	}
	public ArrayList<Item> getItemList()
	{
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList)
	{
		this.itemList = itemList;
	}
	public void clearItems()
	{
		itemList.clear();
	}
	public void add(Item item)
	{
		itemList.remove(item);
	}
	public void add(Item item, int index)
	{
		itemList.add(index, item);
	}
	public void remove(int index)
	{
		itemList.remove(index);
	}
}
