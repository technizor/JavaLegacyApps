package boltstorm.gui.screen;

import java.awt.Graphics;

import boltstorm.gui.Game;
import boltstorm.gui.MenuButton;
import boltstorm.gui.MenuScreen;


public class SingleMenu extends MenuScreen
{
	private static final long serialVersionUID = -4545948117846549501L;

	public SingleMenu(Game game) {
		super(game);
	}
	public SingleMenu(Game game, MenuScreen parentScreen) {
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
