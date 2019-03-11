package boltstorm.entity;

import java.awt.geom.Point2D;

import boltstorm.item.Item;
import boltstorm.map.Map;


public class ItemEntity extends Entity
{
	private Item item;
	public ItemEntity(Item item, Point2D.Double itemPos, int entityId)
	{
		super(itemPos, entityId);
		setItem(item);
	}
	public ItemEntity(Item item, int itemX, int itemY, int entityId)
	{
		super(new Point2D.Double(itemX, itemY), entityId);
		setItem(item);
	}
	public static void dropItem(Map map, Item item, Point2D.Double itemPos, int entityId)
	{
		map.addEntity(new ItemEntity(item, itemPos, entityId));
	}
	public static void dropItem(Map map, Item item, int itemX, int itemY, int entityId)
	{
		map.addEntity(new ItemEntity(item, itemX, itemY, entityId));
	}
	public void takeItem(PlayerEntity player)
	{
		player.getPlayerInv().add(item);
	}
	public Item getItem()
	{
		return item;
	}
	public void setItem(Item item)
	{
		this.item = item;
	}
}
