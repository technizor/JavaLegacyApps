package ubg.data;

public abstract class ItemCategory
{
	private String name;
	public Item[] items;
	void setName(String name)
	{
		this.name = name;
	}
	void setList(Item[] list)
	{
		items = list;
	}
	public abstract void setItems();
	public Item getItem(int n)
	{
		if(items == null) return null;
		return items[n%items.length];
	}
	public String getName()
	{
		return name;
	}
	public int getSize()
	{
		if(items == null) return 0;
		return items.length;
	}
}
