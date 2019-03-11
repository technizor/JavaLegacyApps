package boltstorm.gui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import boltstorm.gui.Game;
import boltstorm.gui.Screen;
import boltstorm.resource.Resource;

public class Splash extends Screen
{
	private static final long serialVersionUID = -4626857969643923369L;
	private Image splash;
	public Splash(int xSize, int ySize, Game game)
	{
		super(xSize, ySize, game);
		splash = Resource.getImage(Resource.splashScreen);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.BLACK);
		g2D.drawImage(splash, 0, 0, this);
	}
}
