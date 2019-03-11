package imageview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import javax.swing.JPanel;

public class CameraScreen extends JPanel
{
	private BufferedImage wall;
	private static final long serialVersionUID = -424885338402465798L;
	public CameraScreen(int i, int j) {
		resizeScreen(i, j);
	}
	public void paint(Graphics g, CameraRoom room)
	{
		if(getWidth() <= 0 || getHeight() <= 0) return;
		Image image = createImage(getWidth(), getHeight());
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.clearRect(0, 0, image.getWidth(this), image.getHeight(this));
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, image.getWidth(this), image.getHeight(this));
		if(wall != null) {
			try {
				int maxX = Math.min(room.getViewW(), wall.getWidth());
				int maxY = Math.min(room.getViewH(), wall.getHeight());
				if(maxX == room.getViewW() && maxY == room.getViewH() && maxX != wall.getWidth() && maxY != wall.getHeight()) {
					g2D.drawImage(wall.getSubimage(room.getViewX(), room.getViewY(), room.getViewW(), room.getViewH()), 0, 0, this);
				} else {
					g2D.drawImage(wall, room.getViewW()/2-wall.getWidth()/2, room.getViewH()/2-wall.getHeight()/2, this);
				}
			} catch(RasterFormatException rfe)
			{
				System.out.println(room.getViewX() + ", " + room.getViewY());
			}
		}
		g.drawImage(image, 0, 0, this);
	}
	public void setImage(BufferedImage image)
	{
		wall = image;
	}
	public void resizeScreen(int width, int height)
	{
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
	}
}
