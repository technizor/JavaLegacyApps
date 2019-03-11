package scrollscreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CameraScreen extends JPanel
{
	private final int gridSize = 25;
	private BufferedImage wall;
	private static final long serialVersionUID = -424885338402465798L;
	public CameraScreen(int i, int j) {
		java.net.URL url = this.getClass().getResource("wall.png");
		if(url != null) {
			try {
				String str = url.getPath();
				String temp = "";
				while(str.indexOf("%20") != -1) {
					temp += str.substring(0, str.indexOf("%20")) + " ";
					str = str.substring(str.indexOf("%20") + 3);
				}
				temp += str;
				wall = ImageIO.read(new File(temp));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setMinimumSize(new Dimension(i, j));
		this.setMaximumSize(new Dimension(i, j));
		this.setPreferredSize(new Dimension(i, j));
		this.setSize(200, 200);
	}
	public void paint(Graphics g, CameraRoom room)
	{
		Image image = createImage(room.getViewW(), room.getViewH());
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.clearRect(0, 0, image.getWidth(this), image.getHeight(this));
		if(wall == null) {
			g2D.setColor(room.getBgCol());
			g2D.fillRect(0, 0, image.getWidth(this), image.getHeight(this));
			g2D.setColor(Color.GREEN);
			for(int x = room.getViewX() % gridSize; x < image.getWidth(this); x += gridSize)
			{
				g2D.drawLine(image.getHeight(this)-x, 0, image.getHeight(this)-x, image.getWidth(this));
			}
			for(int y = room.getViewY() % gridSize; y < image.getHeight(this); y += gridSize)
			{
				g2D.drawLine(0, image.getWidth(this)-y, image.getHeight(this), image.getWidth(this)-y);
			}
			g2D.setColor(Color.RED);
			g2D.fillRect(room.getXPos() - room.getViewX()-10, room.getYPos() - room.getViewY()-10, 20, 20);
			g2D.setColor(Color.ORANGE);
			g2D.drawRect(room.getXPos() - room.getViewX()-10, room.getYPos() - room.getViewY()-10, 20, 20);
		} else {
			try {
				g2D.drawImage(wall.getSubimage(room.getViewX(), room.getViewY(), room.getViewW(), room.getViewH()), 0, 0, this);
			} catch(RasterFormatException rfe)
			{
				System.out.println(room.getViewX() + ", " + room.getViewY());
			}
			g2D.setColor(Color.GREEN);
			g2D.drawRect(room.getXPos() - room.getViewX()-10, room.getYPos() - room.getViewY()-10, 20, 20);
		}
		g.drawImage(image, 0, 0, this);
	}
}
