package ubg.data;

import java.awt.Image;

import ubg.res.ImageRes;

public class SummonerSpell
{
	public static final SummonerSpell[] spellList = {
		new SummonerSpell("Barrier", 0, false, false, true, "Barrier.png"),
		new SummonerSpell("Exhaust", 0, true, true, true, "Exhaust.png"),
		new SummonerSpell("Garrison", 0, false, true, false, "Garrison.png"),
		new SummonerSpell("Ghost", 0, true, true, true, "Ghost.png"),
		new SummonerSpell("Heal", 0, true, true, true, "Heal.png"),
		new SummonerSpell("Revive", 0, true, true, false, "Revive.png"),
		new SummonerSpell("Smite", 0, true, true, false, "Smite.png"),
		new SummonerSpell("Surge", 0, true, true, true, "Surge.png"),
		new SummonerSpell("Teleport", 0, true, false, false, "Teleport.png"),
		new SummonerSpell("Cleanse", 1, true, true, true, "Cleanse.png"),
		new SummonerSpell("Clarity", 5, true, true, true, "Clarity.png"),
		new SummonerSpell("Ignite", 6, true, true, true, "Ignite.png"),
		new SummonerSpell("Promote", 7, true, true, true, "Promote.png"),
		new SummonerSpell("Clairvoyance", 9, true, true, false, "Clairvoyance.png"),
		new SummonerSpell("Flash", 11, true, true, true, "Flash.png")
	};
	private int summonerLevel;
	private boolean classic;
	private boolean dominion;
	private boolean aram;
	private String name;
	private String fileName;
	private Image icon;
	
	public String getName()
	{
		return name;
	}
	public String getFileName()
	{
		return fileName;
	}
	public Image getIcon()
	{
		return icon;
	}
	public SummonerSpell(String name, int level, boolean classic, boolean dominion, boolean aram, String fileName)
	{
		this.name = name;
		this.setSummonerLevel(level);
		this.setClassic(classic);
		this.setDominion(dominion);
		this.setAram(aram);
		this.fileName = fileName;
		icon = ImageRes.loadImage(fileName);
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
	public int getSummonerLevel()
	{
		return summonerLevel;
	}
	public void setSummonerLevel(int summonerLevel)
	{
		this.summonerLevel = summonerLevel;
	}
}
