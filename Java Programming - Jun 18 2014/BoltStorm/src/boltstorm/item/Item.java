package boltstorm.item;

public class Item
{
	private String itemName;
	public Item(String itemName)
	{
		setItemName(itemName);
	}
	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
}
