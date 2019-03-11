package boltstorm.map;

public class Tile
{
	private int ID;
	private int W;
	private int H;
	private boolean SOLID;
	private int posX;
	private int posY;
	
	public Tile(int dataVal, int width, int height, boolean solid)
	{
		setId(dataVal);
		setW(width);
		setH(height);
		setSolid(solid);
	}
	public boolean isSolid()
	{
		return SOLID;
	}
	public void setSolid(boolean solid)
	{
		SOLID = solid;
	}
	public int getH()
	{
		return H;
	}
	public void setH(int h)
	{
		H = h;
	}
	public int getW()
	{
		return W;
	}
	public void setW(int w)
	{
		W = w;
	}
	public int getId()
	{
		return ID;
	}
	public void setId(int id)
	{
		ID = id;
	}
	public void tick(Map map)
	{
		
	}
	public int getPosX()
	{
		return posX;
	}
	public void setPosX(int posX)
	{
		this.posX = posX;
	}
	public int getPosY()
	{
		return posY;
	}
	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	public void addTile(Map map, int posX, int posY)
	{
		for(int i = 0; i < this.getW(); i++)
		{
			for(int j = 0; j < this.getH(); j++) {
				Tile tile = this;
				tile.setH(1);
				tile.setW(1);
				tile.setPosX(posX+i);
				tile.setPosY(posY+j);
				map.getTileset()[posX+i][posY+j] = tile;
			}
		}
	}
}
