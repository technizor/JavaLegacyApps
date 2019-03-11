package boltstorm.map;

import java.awt.geom.Point2D;

import boltstorm.entity.ItemEntity;
import boltstorm.item.Item;

public class ItemSpawnTile extends Tile
{
	private int itemTimer;
	private int timer;
	private Item spawnItem;
	public ItemSpawnTile(int dataVal, Item item, int itemTimer) {
		super(dataVal, 1, 1, false);
		setItemTimer(itemTimer);
		setSpawnItem(item);
	}
	public void tick(Map map)
	{
		if(timer > 0) {
			if(!map.getEntityAt(getPosX(), getPosY()).contains(spawnItem)) {
				timer--;
			}
		}
		else {
			timer = itemTimer;
			map.addEntity(new ItemEntity(spawnItem, new Point2D.Double(this.getPosX()+Map.TILESCALE/2,
					this.getPosY()+Map.TILESCALE/2), itemTimer));
		}
	}
	public int getItemTimer()
	{
		return itemTimer;
	}
	public void setItemTimer(int itemTimer)
	{
		this.itemTimer = itemTimer;
	}
	public Item getSpawnItem()
	{
		return spawnItem;
	}
	public void setSpawnItem(Item spawnItem)
	{
		this.spawnItem = spawnItem;
	}
}
