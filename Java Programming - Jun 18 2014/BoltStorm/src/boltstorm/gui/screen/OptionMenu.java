package boltstorm.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import boltstorm.gui.Game;
import boltstorm.gui.MenuButton;
import boltstorm.gui.MenuScreen;
import boltstorm.resource.Resource;


public class OptionMenu extends MenuScreen
{
	private static final long serialVersionUID = 6858942787956544582L;

	public OptionMenu(Game game) {
		super(game);
	}
	public OptionMenu(Game game, MenuScreen parentScreen) {
		super(game, parentScreen);
	}
	@Override
	public MenuButton[] init()
	{
		MenuButton[] menu = new MenuButton[5];
		menu[0] = new MenuButton("Video Options", Resource.videoIcon);
		menu[1] = new MenuButton("Sound Options", Resource.soundIcon);
		menu[2] = new MenuButton("Profiles", Resource.profileIcon);
		menu[3] = new MenuButton("Controls", Resource.controlsIcon);
		menu[4] = new MenuButton("Back", Resource.backIcon);
		return menu;
	}
	@Override
	public MenuScreen getNextMenu()
	{
		MenuScreen menu = null;
		switch(getSelected())
		{
		case 0:
		case 1:
		case 2:
		case 3:
		default:
			menu = getParentScreen();
			break;
		}
		return menu;
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(logo, 0, 0, logo.getWidth(this)/4, logo.getHeight(this)/4, this);
		g2D.setColor(Color.LIGHT_GRAY);
		Font font = new Font(g2D.getFont().getName(), 32, 32);
		g2D.setFont(font);
		String str = "Options Menu";
		Rectangle2D stringLoc = g2D.getFontMetrics(font).getStringBounds(str, g2D);
		g2D.drawString(str, this.getWidth()/2-((int)stringLoc.getWidth())/2, getTitleY()-((int)stringLoc.getHeight())/2);
	}

}
