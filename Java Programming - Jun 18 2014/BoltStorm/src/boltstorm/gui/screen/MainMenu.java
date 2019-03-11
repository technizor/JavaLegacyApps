package boltstorm.gui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import boltstorm.gui.Game;
import boltstorm.gui.MenuButton;
import boltstorm.gui.MenuScreen;
import boltstorm.resource.Resource;


public class MainMenu extends MenuScreen
{
	public MainMenu(Game game, MenuScreen parentScreen) {
		super(game, parentScreen);
	}
	public MainMenu(Game game) {
		super(game);
	}
	private static final long serialVersionUID = -297987467253130405L;
	public MenuButton[] init()
	{
		MenuButton[] buttons = new MenuButton[6];
		buttons[0] = new MenuButton("Single Player", Resource.singleIcon);
		buttons[1] = new MenuButton("Multiplayer", Resource.multiIcon);
		buttons[2] = new MenuButton("Map Editor", Resource.editorIcon);
		buttons[3] = new MenuButton("Options", Resource.optionIcon);
		buttons[4] = new MenuButton("Help", Resource.helpIcon);
		buttons[5] = new MenuButton("Exit", Resource.exitIcon);
		return buttons;
	}
	public MenuScreen getNextMenu()
	{
		MenuScreen menu = null;
		switch(getSelected())
		{
		case 0:
			//menu =  new SingleMenu(getGame(), this);
			break;
		case 1:
			//menu =  new MultiMenu(getGame(), this);
			break;
		case 2:
			//menu =  new EditorMenu(getGame(), this);
			break;
		case 3:
			menu =  new OptionMenu(getGame(), this);
			break;
		case 4:
			//menu =  new HelpMenu(getGame(), this);
			break;
		default:
			menu = new ExitMenu(getGame(), this);
		}
		return menu;
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.BLACK);
		g2D.drawImage(logo, Game.X/2-logo.getWidth(this)/4, getTitleY()-logo.getHeight(this)/4, 
				logo.getWidth(this)/2, logo.getHeight(this)/2, this);
		
	}
}
