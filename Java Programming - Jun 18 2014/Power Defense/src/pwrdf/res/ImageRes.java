package pwrdf.res;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageRes
{
	public static final String tileSetFile = "powerDefenseTile.png";
	public static final String menuTileSetFile = "powerDefenseMenu.png";
	public static final String hudTileSetFile = "powerDefenseHud.png";

	public static final BufferedImage tileSet = getImage(tileSetFile);
	public static final BufferedImage hudSet = getImage(hudTileSetFile);
	
	public static final int tileScale = 32;
	
	
	public static Image loadImage(String fileName)
	{
		Image img = Toolkit.getDefaultToolkit().getImage(ImageRes.class.getResource(fileName));
		Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, null);
		return img;
	}
	public static BufferedImage getImage(String fileName)
	{
		BufferedImage buf = null;
		try {
			buf = ImageIO.read(ImageRes.class.getResource(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf;
	}
	public static BufferedImage getTile(int posX, int posY, int width, int height)
	{
		BufferedImage buf = ((BufferedImage)tileSet).getSubimage(posX*tileScale, posY*tileScale, width*tileScale, height*tileScale);
		return buf;
	}
	public static BufferedImage getCursor(int frameNum)
	{
		BufferedImage buf = ((BufferedImage)hudSet).getSubimage(32*(frameNum%4), 0, 32, 32);
		return buf;
	}
	public static BufferedImage getHighlighter()
	{
		BufferedImage buf = ((BufferedImage)hudSet).getSubimage(128, 0, 32, 32);
		return buf;
	}
	public static BufferedImage colorImage(BufferedImage loadImg, int red, int green, int blue) {
	    BufferedImage img = new BufferedImage(loadImg.getWidth(), loadImg.getHeight(),
	            BufferedImage.TRANSLUCENT);
	        Graphics2D graphics = img.createGraphics(); 
	        Color newColor = new Color(red, green, blue, 0 /* alpha needs to be zero */);
	        graphics.setXORMode(newColor);
	        graphics.drawImage(loadImg, null, 0, 0);
	        graphics.dispose();
	        return img;
	    }
}
