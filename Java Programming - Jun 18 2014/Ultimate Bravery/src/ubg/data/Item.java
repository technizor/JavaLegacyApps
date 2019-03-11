package ubg.data;

import java.awt.Image;

import ubg.res.ImageRes;

public class Item
{
	private boolean classic;
	private boolean dominion;
	private boolean aram;
	private String name;
	private String fileName;
	private Image icon;
	public Image getIcon()
	{
		return icon;
	}
	public String getFileName()
	{
		return fileName;
	}
	public String getName()
	{
		return name;
	}
	public Item(String name, boolean classic, boolean dominion, boolean aram, String fileName)
	{
		this.name = name;
		this.setClassic(classic);
		this.setDominion(dominion);
		this.setAram(aram);
		this.fileName = fileName;
		this.icon = ImageRes.loadImage(fileName);
	}
	public boolean isClassic()
	{
		return classic;
	}
	private void setClassic(boolean classic)
	{
		this.classic = classic;
	}
	public boolean isDominion()
	{
		return dominion;
	}
	private void setDominion(boolean dominion)
	{
		this.dominion = dominion;
	}
	public boolean isAram()
	{
		return aram;
	}
	private void setAram(boolean aram)
	{
		this.aram = aram;
	}
	public boolean isMode(int n)
	{
		switch(n%3) {
		case 0:
			return isClassic();
		case 1:
			return isDominion();
		case 2:
			return isAram();
		}
		return false;
	}
}
