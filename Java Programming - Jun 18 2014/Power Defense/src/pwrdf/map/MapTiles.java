package pwrdf.map;

public class MapTiles
{
	private Tile[][] tiles;
	private int mapWidth;
	private int mapHeight;
	public MapTiles(MapConfig mapData)
	{
		mapWidth = mapData.getMapWidth();
		mapHeight = mapData.getMapHeight();
		Tile[][] tiles = new Tile[mapWidth][mapHeight];
		for(int j = 0; j < mapHeight; j++) {
			for(int i = 0; i < mapWidth; i++) {
				tiles[i][j] = new Tile();
			}
		}
		setTiles(tiles);
	}
	public MapTiles(MapConfig mapData, String fileGrid)
	{
		mapWidth = mapData.getMapWidth();
		mapHeight = mapData.getMapHeight();
		Tile[][] tiles = new Tile[mapWidth][mapHeight];
		for(int j = 0; j < mapHeight; j++) {
			for(int i = 0; i < mapWidth; i++) {
				tiles[i][j] = new Tile(toInt(fileGrid.trim().charAt(i+j*mapWidth+2) + ""));
			}
		}
		setTiles(tiles);
	}
	
	public int toInt(String number)
	{
		try {
			int n = Integer.parseInt(number);
			return n;
		} catch (NumberFormatException nfe)	{
			return 0;
		}
	}
	
	public String getString()
	{
		String output = "t(";
		for(int j = 0; j < mapHeight; j++) {
			for(int i = 0; i < mapWidth; i++) {
				output += "" + tiles[i][j].getTileId();
			}
		}
		output += ")";
		return output;
	}
	public Tile[][] getTiles()
	{
		return tiles;
	}
	public void setTiles(Tile[][] tiles)
	{
		this.tiles = tiles;
	}
	public Tile getTileAt(int xPos, int yPos)
	{
		int x = (xPos + mapWidth)%mapWidth;
		int y = (yPos + mapHeight)%mapHeight;
		return tiles[x][y];
	}
	public void setTileAt(int xPos, int yPos, int tileId)
	{
		if(xPos < mapWidth && xPos >= 0 && yPos >= 0 && yPos < mapHeight && tileId < Tile.tileSet.length && tileId >= 0) {
			tiles[xPos][yPos] = Tile.tileSet[tileId];
		}
	}
}
