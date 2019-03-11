package boltstorm.gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JPanel;

import boltstorm.com.MenuController;
import boltstorm.resource.Resource;


public abstract class Screen extends JPanel
{
	private static final long serialVersionUID = -6917605977905763451L;
	private int xOffset;
	private int yOffset;
	private int xSize;
	private int ySize;
	private Game game;
	private Screen parentScreen;
	protected MenuController controls;
	protected Image logo;
	
	public Screen(int xSize, int ySize, Game game)
	{
		this.setGame(game);
		this.addKeyListener(controls = game.getMenuControls());
		setxSize(xSize);
		setySize(ySize);
		setParentScreen(null);
		this.setBackground(Color.BLACK);
		logo = Resource.getImage(Resource.boltstormLogo);
	}
	public Screen(int xSize, int ySize, Game game, Screen parentScreen)
	{
		this.setGame(game);
		this.addKeyListener(controls = game.getMenuControls());
		setxSize(xSize);
		setySize(ySize);
		setParentScreen(parentScreen);
		this.setBackground(Color.BLACK);
		logo = Resource.getImage(Resource.boltstormLogo);
	}
	
	public int getxOffset()
	{
		return xOffset;
	}
	public void setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
	}
	public int getyOffset()
	{
		return yOffset;
	}
	public void setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
	}
	public int getxSize()
	{
		return xSize;
	}
	public void setxSize(int xSize)
	{
		this.xSize = xSize;
	}
	public int getySize()
	{
		return ySize;
	}
	public void setySize(int ySize)
	{
		this.ySize = ySize;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	public Screen getParentScreen()
	{
		return parentScreen;
	}

	public void setParentScreen(Screen parentScreen)
	{
		this.parentScreen = parentScreen;
	}
}
