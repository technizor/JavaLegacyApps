package lolutil.data.item;

import java.util.ArrayList;

public class Item
{
	private boolean classic;
	private boolean dominion;
	private String name;
	private int tier;
	private ArrayList<ItemCategory> category;
	private ArrayList<Item> components;
	private int cost;
	public Item(String name, int combineCost, boolean classic, boolean dominion, ItemRecipe itemRecipe, ItemCategoryList itemCategories)
	{
		this.name = name;
		cost = combineCost;
		components = new ArrayList<Item>();
		this.category = new ArrayList<ItemCategory>();
		this.classic = classic;
		this.dominion = dominion;
		ArrayList<Item> temp = itemRecipe.getRecipe();
		if(temp.size() > 0) {
			int highestTier = 0;
			for(int i = 0; i < temp.size(); i++) {
				components.add(temp.get(i));
				highestTier = Math.max(highestTier, temp.get(i).getTier());
			}
			tier = highestTier+1;
		} else {
			tier = 0;
		}
		ArrayList<ItemCategory> temp1 = itemCategories.getCategories();
		if(temp1.size() > 0) {
			for(int i = 0; i < temp1.size(); i++) {
				category.add(temp1.get(i));
				if(temp1.get(i).equals(ItemCategory.consumableItem)) {
					tier = -1;
				}
			}
		}
	}
	private int getTier()
	{
		return tier;
	}
	public String getTierName()
	{
		if(tier == 0) {
			return "Basic";
		}
		if(tier == 1) {
			return "Advanced";
		}
		if(tier == 2) {
			return "Legendary";		
		}
		return "Consumable";
	}
	public int getTotalCost()
	{
		int total = cost;
		for(Item item : components) {
			total += item.getTotalCost();
		}
		return total;
	}
	public int getCombineCost()
	{
		return cost;
	}
	public ArrayList<Item> getRecipe()
	{
		return components;
	}
	public ArrayList<ItemCategory> getCategory()
	{
		return category;
	}
	public boolean classicItem()
	{
		return classic;
	}
	public boolean dominionItem()
	{
		return dominion;
	}
	public boolean coreItem()
	{
		return classic && dominion;
	}
	public boolean removedItem()
	{
		return !classic && !dominion;
	}
	public String getName()
	{
		return name;
	}
}
