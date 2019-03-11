package pwrdf.map;

import java.awt.Color;

public class MapConfig
{
	private String mapName;
	private int mapWidth;
	private int mapHeight;
	private int mapMode;
	private Color mapColor;
	
	public MapConfig(String mapName, int mapWidth, int mapHeight, int mapMode, Color mapColor)
	{
		this.setMapName(mapName);
		this.setMapWidth(mapWidth);
		this.setMapHeight(mapHeight);
		this.setMapMode(mapMode);
		this.setMapColor(mapColor);
	}
	public MapConfig(String fileTags)
	{
		String temp = fileTags.trim().substring(2);
		this.setMapName(temp.substring(0, temp.indexOf(")w(")));
		temp = temp.substring(temp.indexOf(")w(") + 3);
		this.setMapWidth(toInt(temp.substring(0, temp.indexOf(")h("))));
		temp = temp.substring(temp.indexOf(")h(") + 3);
		this.setMapHeight(toInt(temp.substring(0, temp.indexOf(")m("))));
		temp = temp.substring(temp.indexOf(")m(") + 3);
		this.setMapMode(toInt(temp.substring(0, temp.indexOf(")c("))));
		temp = temp.substring(temp.indexOf(")c(") + 3);
		this.setMapColor(new Color(toInt(temp.substring(0, temp.length()-1))));
	}

	private int toInt(String number)
	{
		try {
			int n = Integer.parseInt(number);
			return n;
		} catch (NumberFormatException nfe)
		{
			return 0;
		}
	}
	public String getMapName()
	{
		return mapName;
	}

	public void setMapName(String mapName)
	{
		this.mapName = mapName;
	}

	public int getMapWidth()
	{
		return mapWidth;
	}

	public void setMapWidth(int mapWidth)
	{
		this.mapWidth = mapWidth;
	}

	public int getMapHeight()
	{
		return mapHeight;
	}

	public void setMapHeight(int mapHeight)
	{
		this.mapHeight = mapHeight;
	}

	public int getMapMode()
	{
		return mapMode;
	}

	public void setMapMode(int mapMode)
	{
		this.mapMode = mapMode;
	}

	public Color getMapColor()
	{
		return mapColor;
	}

	public void setMapColor(Color mapColor)
	{
		this.mapColor = mapColor;
	}

	public String getString()
	{
		String output = "n(";
		output += mapName + ")w(" + mapWidth + ")h(" + mapHeight + ")m(" + mapMode + ")c(" + mapColor.getRGB() + ")";
		return output;
	}
}
