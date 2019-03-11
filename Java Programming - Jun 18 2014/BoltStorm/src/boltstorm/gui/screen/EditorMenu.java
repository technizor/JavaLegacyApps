package boltstorm.gui.screen;

import java.awt.Graphics;

import boltstorm.gui.Game;
import boltstorm.gui.MenuButton;
import boltstorm.gui.MenuScreen;


public class EditorMenu extends MenuScreen
{
	private static final long serialVersionUID = -2394453786469476553L;

	public EditorMenu(Game game) {
		super(game);
	}
	public EditorMenu(Game game, MenuScreen parentScreen) {
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
