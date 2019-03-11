package lolutil.data.item;

import java.util.ArrayList;

public class ItemRecipe
{
	ArrayList<Item> recipe;
	public ItemRecipe(Item ... components)
	{
		recipe = new ArrayList<Item>();
		if(components.length > 0) {
			for(Item item : components) {
				recipe.add(item);
			}
		}
	}
	public ItemRecipe(ItemEnum ... components) {
		recipe = new ArrayList<Item>();
		if(components.length > 0) {
			for(ItemEnum item : components) {
				recipe.add(item.getItem());
			}
		}
	}
	public ArrayList<Item> getRecipe()
	{
		return recipe;
	}
}
