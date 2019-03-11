package boltstorm.map;

import java.util.ArrayList;

import boltstorm.entity.Entity;
import boltstorm.entity.ProjectileEntity;


public class Map
{
	private int width;
	private int height;
	private Tile[][] tileset;
	public static int TILESCALE = 32;
	
	public Map(int width, int height)
	{
		setWidth(width-width%TILESCALE);
		setHeight(height-height%TILESCALE);
		entityList = new ArrayList<Entity>();
		tileset = new Tile[width/TILESCALE][height/TILESCALE];
		for(int i = 0; i < width/TILESCALE; i++)
		{
			for(int j = 0; j < height/TILESCALE; j++)
			{
				tileset[i][j] = new Tile(0, 1, 1, false);
			}
		}
	}
	private ArrayList<Entity> entityList;

	public ArrayList<Entity> getEntityList()
	{
		return entityList;
	}

	public void setEntityList(ArrayList<Entity> entityList)
	{
		this.entityList = entityList;
	}
	public void addEntity(Entity entity)
	{
		this.entityList.add(entity);
	}
	public void tick()
	{
		for(Entity e : entityList)
		{
			e.tick(this);
		}
		int j = entityList.size();
		for(int i = 0; i < j; i++)
		{
			Entity entity = entityList.get(i);
			if(entity instanceof ProjectileEntity) {
				if(entity.getEntityX() < 0 || entity.getEntityX() > getWidth() ||
						entity.getEntityY() < 0 || entity.getEntityY() > getHeight() ||
						entity.getSpeed() < 1 || getTileAt((int)entity.getEntityY(), (int)entity.getEntityY()).isSolid())
				{
					entityList.remove(entityList.get(i));
					i--;
					j--;
				}
			}
		}
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public Tile[][] getTileset()
	{
		return tileset;
	}

	public void setTileset(Tile[][] tileset)
	{
		this.tileset = tileset;
	}
	public ArrayList<Entity> getEntityAt(int tileX, int tileY)
	{
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for(Entity entity : entityList)
		{
			if(((int)entity.getEntityX()/32) == tileX &&
					((int)entity.getEntityY()/32) == tileY)
					{
						entities.add(entity);
					}
		}
		return entities;
	}
	public Tile getTileAt(double entityX, double entityY)
	{
		if((int)entityX < this.getWidth() && (int)entityY < this.getHeight() && (int)entityX > -1 && (int)entityY > -1)
		return tileset[(int)entityX/TILESCALE][(int)entityY/TILESCALE];
		return null;
	}
}
