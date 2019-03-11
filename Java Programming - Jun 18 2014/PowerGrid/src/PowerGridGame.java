import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PowerGridGame extends JPanel implements MouseListener
{
	public static final Color INFO_COLOUR = new Color(128, 128, 128);
	private static final long serialVersionUID = -8665324685159912884L;

	private ConnectorGrid powerGrid;
	private LinkedList<Player> playerList;
	private ArrayList<Connector> connectorBag;

	public PowerGridGame(int nPlayers) throws IllegalArgumentException
	{
		if (nPlayers <= 0)
			throw new IllegalArgumentException();
		powerGrid = new ConnectorGrid();
		Random rand = new Random();
		connectorBag = new ArrayList<Connector>();
		for (int c = 0; c < 100; c++)
			connectorBag.add(new Connector(rand.nextInt(15) + 1));

		playerList = new LinkedList<Player>();
		if (nPlayers > Player.PLAYER_COLOUR.length)
			nPlayers = Player.PLAYER_COLOUR.length;
		for (int p = 1; p <= nPlayers; p++) {
			Player player = new Player(p);
			player.update(connectorBag, powerGrid);
			playerList.add(player);
		}

	}

	private BufferedImage drawPlayerList()
	{
		BufferedImage buf = new BufferedImage(Player.WIDTH, 5 * Player.HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buf.getGraphics();
		g2D.setColor(Player.BLANK);
		g2D.fillRect(0, 0, buf.getWidth(), buf.getHeight());
		int p = 0;
		for (Player player : playerList) {
			g2D.drawImage(player.getImage(), 0, p * Player.HEIGHT, null);
			p++;
		}
		return buf;
	}

	private BufferedImage drawInfo()
	{
		BufferedImage buf = new BufferedImage(Player.WIDTH,
				Connector.IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buf.getGraphics();
		g2D.setColor(INFO_COLOUR);
		g2D.fillRect(0, 0, buf.getWidth(), buf.getHeight());
		return buf;
	}

	public BufferedImage getImage()
	{
		BufferedImage gridImg = powerGrid.getImage();
		BufferedImage playerImg = drawPlayerList();
		BufferedImage infoImg = drawInfo();

		BufferedImage buf = new BufferedImage(gridImg.getWidth()
				+ playerImg.getWidth(), Math.max(gridImg.getHeight(),
				playerImg.getHeight()), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buf.getGraphics();

		g2D.drawImage(gridImg, 0, 0, null);
		g2D.drawImage(playerImg, gridImg.getWidth(), 0, null);
		g2D.drawImage(infoImg, gridImg.getWidth(), playerImg.getHeight(), null);

		return buf;
	}

	public static void main(final String[] args) throws IOException
	{
		BufferedImage img = new PowerGridGame(5).getImage();
		File f = new File("grid.png");
		ImageIO.write(img, "PNG", f);
	}

	public void mouseClicked(MouseEvent evt)
	{

	}

	public void mouseEntered(MouseEvent evt)
	{
	}

	public void mouseExited(MouseEvent evt)
	{
	}

	public void mousePressed(MouseEvent evt)
	{
	}

	public void mouseReleased(MouseEvent evt)
	{
	}
}
