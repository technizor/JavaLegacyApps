package boltstorm.gui.screen;

import java.awt.Graphics;

import boltstorm.gui.Game;
import boltstorm.gui.MenuButton;
import boltstorm.gui.MenuScreen;


public class HelpMenu extends MenuScreen
{
	private static final long serialVersionUID = 8841648113254013135L;

	public HelpMenu(Game game) {
		super(game);
	}
	public HelpMenu(Game game, MenuScreen parentScreen) {
		super(game, parentScreen);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public MenuButton[] init()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MenuScreen getNextMenu()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
