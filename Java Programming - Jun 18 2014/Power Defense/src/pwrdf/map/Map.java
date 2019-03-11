package pwrdf.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pwrdf.res.ImageRes;

public class Map
{
	public static final String mapFileExtension = "PRDM";
	public static final int LINEAR = 214516;
	public static final int NONLINEAR = 6390135;
	public static final int PVP = 1530935;
	private int counter;
	private MapConfig mapData;
	private MapTiles mapTiles;
	private TowerTiles towerTiles;
	
	public MapConfig getMapData()
	{
		return mapData;
	}
	public TowerTiles towerTiles()
	{
		return towerTiles;
	}
	public String getName()
	{
		return mapData.getMapName();
	}
	public static void saveMap(Map map)
	{
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(map.getName().toLowerCase() + "." + mapFileExtension)));
			writer.println(map.getMapData().getString());
			writer.flush();
		} catch (IOException ioe) {
			System.out.println("Cannot save map: " + map.getName());
		}
	}
	public static Map loadMap(String mapFileName)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(mapFileName)));
			String mapD = reader.readLine();
			String mapT = reader.readLine();
			return new Map(mapD, mapT);
		} catch(IOException ioe)
		{
			System.out.println("Cannot load map: " + mapFileName);
			return null;
		}
	}
	public Map (String fileTags, String fileGrid)
	{
		mapData = new MapConfig(fileTags);
		mapTiles = new MapTiles(mapData, fileGrid);
		counter = 0;
	}
	public Map(String name, int width, int height, int mapMode)
	{
		mapData = new MapConfig(name, width, height, mapMode, Color.black);
		setColor();
		mapTiles = new MapTiles(mapData);
		counter = 0;
	}
	public Map(String name, int width, int height, int mapMode, Color mapColor)
	{
		mapData = new MapConfig(name, width, height, mapMode, mapColor);
		mapTiles = new MapTiles(mapData);
		counter = 0;
	}
	private void setColor()
	{
		int red = (int)(Math.random()*256%256);
		int gre = (int)(Math.random()*256%256);
		int blu = (int)(Math.random()*256%256);
		mapData.setMapColor(new Color(red, gre, blu));
		/*int colNum = (int) ((Math.random()*13)%13);
		switch(colNum) {
		case 0:
			mapData.setMapColor(Color.BLACK);
			break;
		case 1:
			mapData.setMapColor(Color.BLUE);
			break;
		case 2:
			mapData.setMapColor(Color.cyan);
			break;
		case 3:
			mapData.setMapColor(Color.DARK_GRAY);
			break;
		case 4:
			mapData.setMapColor(Color.gray);
			break;
		case 5:
			mapData.setMapColor(Color.green);
			break;
		case 6:
			mapData.setMapColor(Color.LIGHT_GRAY);
			break;
		case 7:
			mapData.setMapColor(Color.magenta);
			break;
		case 8:
			mapData.setMapColor(Color.ORANGE);
			break;
		case 9:
			mapData.setMapColor(Color.PINK);
			break;
		case 10:
			mapData.setMapColor(Color.RED);
			break;
		case 11:
			mapData.setMapColor(Color.WHITE);
			break;
		case 12:
			mapData.setMapColor(Color.yellow);
			break;
		}*/
	}
	public Tile getTileAt(int xPos, int yPos)
	{
		return mapTiles.getTileAt(xPos, yPos);
	}
	public void setTileAt(int xPos, int yPos, int tileId) {
		mapTiles.setTileAt(xPos, yPos, tileId);
	}
	public Tile[][] getMapTiles()
	{
		return mapTiles.getTiles();
	}
	public void setMapTiles(Tile[][] mapTiles)
	{
		this.mapTiles.setTiles(mapTiles);
	}
	public int getMapHeight()
	{
		return mapData.getMapHeight();
	}
	public void setMapHeight(int mapHeight)
	{
		mapData.setMapHeight(mapHeight);
	}
	public int getMapWidth()
	{
		return mapData.getMapWidth();
	}
	public void setMapWidth(int mapWidth)
	{
		mapData.setMapWidth(mapWidth);
	}
	public int getMapMode()
	{
		return mapData.getMapMode();
	}
	public void setMapMode(int mapMode)
	{
		mapData.setMapMode(mapMode);
	}
	public BufferedImage render(int viewX, int viewY, int viewW, int viewH, int selectedX, int selectedY, boolean paintCursor)
	{
		BufferedImage image = new BufferedImage(viewW+2*Tile.TILESIZE, viewH+2*Tile.TILESIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		int xOffset = Tile.TILESIZE- (viewX % Tile.TILESIZE);
		int yOffset = Tile.TILESIZE- (viewY % Tile.TILESIZE);
		if(xOffset == 0) xOffset += Tile.TILESIZE;
		if(yOffset == 0) yOffset += Tile.TILESIZE;
		int xSelect = selectedX/Tile.TILESIZE;
		int ySelect = selectedY/Tile.TILESIZE;
		int xPos = viewX / Tile.TILESIZE;
		int yPos = viewY / Tile.TILESIZE;
		selectedX += Tile.TILESIZE;
		selectedY += Tile.TILESIZE;
		for(int j = 0; j <= viewH/Tile.TILESIZE; j++) {
			for(int i = 0; i <= viewW/Tile.TILESIZE; i++) {
				Tile t = mapTiles.getTileAt(xPos+i,yPos+j);
				BufferedImage buf = t.drawImage(mapData.getMapColor(), toSquare(xPos+i, xPos+j));
				g2D.drawImage(buf, xOffset + i*Tile.TILESIZE, yOffset + j*Tile.TILESIZE, null);
				if(paintCursor && i == xSelect && j == ySelect) {
					//Highlight
					g2D.drawImage(ImageRes.getHighlighter(), xOffset + i*Tile.TILESIZE, yOffset + j*Tile.TILESIZE, null);
				}
			}
		}
		if(paintCursor) {
			BufferedImage cursor = ImageRes.getCursor(counter/3);
			g2D.drawImage(cursor, selectedX-16, selectedY-16, null);
			counter = (counter + 1) % 12;
		}
		return image.getSubimage(Tile.TILESIZE, Tile.TILESIZE, viewW, viewH);
	}
	private Tile[][] toSquare(int x, int y)
	{
		Tile[][] square = new Tile[3][3];
		for(int i = -1; i <= 1; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				square[i+1][j+1] = mapTiles.getTileAt(x+i, y+j);
			}
		}
		return square;
	}
}
