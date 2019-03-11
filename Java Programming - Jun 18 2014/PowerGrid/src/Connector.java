import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Connector
{
	public static final byte IMAGE_SIZE = 32;
	public static BufferedImage connectorImage;
	private byte connector;// RULD

	public Connector(int id) throws IllegalArgumentException
	{
		if (id < 0 || id >= 16)
			throw new IllegalArgumentException();
		connector = (byte) id;
	}

	public boolean connectRight()
	{
		return connector % 2 == 1;
	}

	public boolean connectUp()
	{
		return connector / 2 % 2 == 1;
	}

	public boolean connectLeft()
	{
		return connector / 4 % 2 == 1;
	}

	public boolean connectDown()
	{
		return connector / 8 == 1;
	}

	public BufferedImage getImage()
	{
		return getImage(connector);
	}

	public int getId()
	{
		return connector;
	}

	public String toString()
	{
		return String.format("[%d]", connector);
	}

	public boolean equals(Object other)
	{
		if (other instanceof Connector
				&& connector == ((Connector) other).connector)
			return true;
		return false;
	}

	public int hashCode()
	{
		return connector;
	}

	public static BufferedImage getImage(int id)
	{
		if (connectorImage == null) {
			try {
				loadImage();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		int x = id % 4;
		int y = id / 4;
		return connectorImage.getSubimage(x * IMAGE_SIZE, y * IMAGE_SIZE,
				IMAGE_SIZE, IMAGE_SIZE);
	}

	public static void loadImage() throws IOException
	{
		BufferedImage buf = ImageIO.read(new File("connector.png"));
		connectorImage = buf;
	}
}
