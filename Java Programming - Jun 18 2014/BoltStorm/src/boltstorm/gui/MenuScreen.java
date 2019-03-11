package boltstorm.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import boltstorm.com.MenuController;
import boltstorm.gui.screen.ExitMenu;
import boltstorm.resource.Resource;


public abstract class MenuScreen extends JPanel
{
	private MenuScreen parentScreen;
	private Game game;
	private static final long serialVersionUID = -2966170244875636476L;
	private MenuButton[] buttons;
	private int selected;
	private int buttonSize;
	private MenuController controller;
	private int optionSwitchTimer;
	private int buttonTimer;
	protected Image logo;
	
	public MenuScreen(Game game)
	{
		setGame(game);
		setParentScreen(null);
		buttons = init();
		selected = 0;
		setController(game.getMenuControls());
		buttonSize = buttons.length;
		optionSwitchTimer = 20;
		buttonTimer = 20;
		logo = Resource.getImage(Resource.boltstormLogo);
	}
	public MenuScreen(Game game, MenuScreen parentScreen)
	{
		setGame(game);
		setParentScreen(parentScreen);
		buttons = init();
		selected = 0;
		setController(game.getMenuControls());
		buttonSize = buttons.length;
		optionSwitchTimer = 20;
		buttonTimer = 20;
		logo = Resource.getImage(Resource.boltstormLogo);
	}
	public MenuButton[] getButtons()
	{
		return buttons;
	}
	public void setButtons(MenuButton[] buttons)
	{
		this.buttons = buttons;
	}
	public MenuScreen getParentScreen()
	{
		return parentScreen;
	}
	public void setParentScreen(MenuScreen parentScreen)
	{
		this.parentScreen = parentScreen;
	}
	public int getSelected()
	{
		return selected;
	}
	public void setSelected(int selected)
	{
		this.selected = selected;
	}
	public void selectedPlus()
	{
		setSelected((getButtonSize()+getSelected()+1) % getButtonSize());
	}
	public void selectedMinus()
	{
		setSelected((getButtonSize()+getSelected()-1) % getButtonSize());
	}
	public int getButtonSize()
	{
		return buttonSize;
	}
	public void setButtonSize(int buttonSize)
	{
		this.buttonSize = buttonSize;
	}
	public void paint(Graphics g)
	{
		if(getButtons() != null) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
			int hInt = getButtonY();
			int offset = 60;
			int prev = (getSelected() + getButtonSize() - 1) % getButtonSize();
			int next = (getSelected() + getButtonSize() + 1) % getButtonSize();
			g2D.setColor(Color.LIGHT_GRAY);
			Font font = new Font(g2D.getFont().getName(), 32, 32);
			g2D.setFont(font);
			Image icon = null;
			if(getButtons()[getSelected()].getButtonIcon() != null) {
				icon = getButtons()[getSelected()].getButtonIcon().getImage();
			}
			String str = getButtons()[getSelected()].getButtonName();
			Rectangle2D stringLoc = g2D.getFontMetrics(font).getStringBounds(str, g2D);
			if(icon != null) {
				g2D.drawImage(icon, this.getWidth()/2-128, hInt, 256, 256, this);
				g2D.drawString(str, this.getWidth()/2-((int)stringLoc.getWidth())/2, hInt + 272);
			} else {
				g2D.drawString(str, this.getWidth()/2-((int)stringLoc.getWidth())/2, hInt + 128);
			}
			if(prev != getSelected() && next != getSelected()) {
				font = new Font(g2D.getFont().getName(), 16, 16);
				g2D.setFont(font);
				String str1 = getButtons()[prev].getButtonName();
				Rectangle2D stringLoc1 = g2D.getFontMetrics(font).getStringBounds(str1, g2D);
				String str2 = getButtons()[next].getButtonName();
				Rectangle2D stringLoc2 = g2D.getFontMetrics(font).getStringBounds(str2, g2D);
				Image icon1 = null;
				Image icon2 = null;
				if(getButtons()[prev].getButtonIcon() != null) {
					icon1 = getButtons()[prev].getButtonIcon().getImage();
				}
				if((getButtons()[next].getButtonIcon() != null)) {
					icon2 = getButtons()[next].getButtonIcon().getImage();
				}
				if(prev == next) {
					if(getSelected() > prev) {
						if(icon1 != null) {
							g2D.drawImage(icon1, this.getWidth()/4-offset-64, hInt+64, 128, 128, this);
							g2D.drawString(str1, this.getWidth()/4-offset-((int)stringLoc1.getWidth())/2, hInt + 200);
						} else {
							g2D.drawString(str1, this.getWidth()/4-offset-((int)stringLoc1.getWidth())/2, hInt + 128);
						}
					} else {
						if(icon2 != null) {
							g2D.drawImage(icon2, this.getWidth()/4*3+offset-64, hInt+64, 128, 128, this);
							g2D.drawString(str2, this.getWidth()/4*3+offset-((int)stringLoc2.getWidth())/2, hInt + 200);
						} else {
							g2D.drawString(str2, this.getWidth()/4*3+offset-((int)stringLoc2.getWidth())/2, hInt + 128);
						}
					}
				} else {
					if(icon1 != null) {
						g2D.drawImage(icon1, this.getWidth()/4-offset-64, hInt+64, 128, 128, this);
						g2D.drawString(str1, this.getWidth()/4-offset-((int)stringLoc1.getWidth())/2, hInt + 200);
					} else {
						g2D.drawString(str1, this.getWidth()/4-offset-((int)stringLoc1.getWidth())/2, hInt + 128);
					}
					if(icon2 != null) {
						g2D.drawImage(icon2, this.getWidth()/4*3+offset-64, hInt+64, 128, 128, this);
						g2D.drawString(str2, this.getWidth()/4*3+offset-((int)stringLoc2.getWidth())/2, hInt + 200);
					} else {
						g2D.drawString(str2, this.getWidth()/4*3+offset-((int)stringLoc2.getWidth())/2, hInt + 128);
					}
				}
			}
		}
	}
	public abstract MenuButton[] init();
	public abstract MenuScreen getNextMenu();
	
	
	public void upDownAction(boolean up)
	{
		
	}
	
	public MenuController getController()
	{
		return controller;
	}
	public void setController(MenuController controller)
	{
		this.controller = controller;
	}
	public Game getGame()
	{
		return game;
	}
	public void setGame(Game game)
	{
		this.game = game;
	}
	public void tick()
	{
		if(optionSwitchTimer == 0)
		{
			if(controller.right.clicked)
			{
				selectedPlus();
				optionSwitchTimer = 15;
			}
			if(controller.left.clicked)
			{
				selectedMinus();
				optionSwitchTimer = 15;
			}
			if(controller.up.clicked)
			{
				upDownAction(true);
			}
			if(controller.down.clicked)
			{
				upDownAction(false);
			}
		} else {
			optionSwitchTimer--;
		}
		if (buttonTimer == 0) {
			if(controller.confirm.clicked)
			{
				if(getNextMenu() != null) {
					((Menu)game.getScreen()).setMenu(getNextMenu());
					buttonTimer = 15;
				}
			}
			if(controller.deconfirm.clicked)
			{
				if(getParentScreen() != null)
				{
					((Menu)game.getScreen()).setMenu(getParentScreen());
				} else {
					((Menu)game.getScreen()).setMenu(new ExitMenu(game, this));
				}
				buttonTimer = 15;
			}
			if(controller.pause.clicked)
			{
				escapeAction();
				buttonTimer = 15;
			}
		} else {
			buttonTimer--;
		}
	}
	public void escapeAction()
	{
		if(getParentScreen() != null)
		{
			((Menu)game.getScreen()).setMenu(getParentScreen());
		} else {
			((Menu)game.getScreen()).setMenu(new ExitMenu(game, this));
		}
	}
	public int getTitleY()
	{
		return getHeight()/4;
	}
	public int getButtonY()
	{
		return getHeight()-336;
	}
}
