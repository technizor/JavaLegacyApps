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


public class ExitMenu extends MenuScreen
{
	private static final long serialVersionUID = -5868629668891447313L;

	public ExitMenu(Game game) {
		super(game);
	}
	public ExitMenu(Game game, MenuScreen parentScreen) {
		super(game, parentScreen);
	}

	
	public MenuButton[] init()
	{
		MenuButton[] options = new MenuButton[2];
		options[0] = new MenuButton("Quit", Resource.exitIcon);
		options[1] = new MenuButton("Cancel", Resource.backIcon);
		return options;
	}
	
	public MenuScreen getNextMenu()
	{
		if(getSelected() == 0) {
			System.exit(1);
		}
		return getParentScreen();
	}
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		super.paint(g2D);
		g2D.drawImage(logo, 0, 0, logo.getWidth(this)/4, logo.getHeight(this)/4, this);
		g2D.setColor(Color.LIGHT_GRAY);
		Font font = new Font(g2D.getFont().getName(), 32, 32);
		g2D.setFont(font);
		String str = "Do you really want to quit?";
		Rectangle2D stringLoc = g2D.getFontMetrics(font).getStringBounds(str, g2D);
		g2D.drawString(str, this.getWidth()/2-((int)stringLoc.getWidth())/2, getTitleY()-((int)stringLoc.getHeight())/2);
	}
	public void escapeAction()
	{
		System.exit(1);
	}
}
