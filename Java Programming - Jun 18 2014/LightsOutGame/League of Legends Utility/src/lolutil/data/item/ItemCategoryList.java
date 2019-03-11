package lolutil.data.item;

import java.util.ArrayList;

public class ItemCategoryList
{
	private ArrayList<ItemCategory> categories;
	public ItemCategoryList(ItemCategory ... category)
	{
		categories = new ArrayList<ItemCategory>();
		if(category.length > 0) {
			for(ItemCategory type : category) {
				categories.add(type);
			}
		}
	}
	public ArrayList<ItemCategory> getCategories()
	{
		return categories;
	}
}
