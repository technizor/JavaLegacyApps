import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player
{
	public static final int WIDTH = 256;
	public static final int HEIGHT = 64;
	public static final Color BLANK = new Color(89, 89, 89);
	private byte id;
	private int score;
	private byte selection;
	private ArrayList<Connector> hand;

	public static final Color[] PLAYER_COLOUR = { new Color(191, 191, 191),
			new Color(223, 63, 63), new Color(63, 223, 63),
			new Color(63, 63, 223), new Color(191, 159, 63),
			new Color(63, 191, 191) };

	public Player(int id) throws IllegalArgumentException
	{
		if (id <= 0 || id >= PLAYER_COLOUR.length)
			throw new IllegalArgumentException();
		this.id = (byte) id;
		score = 0;
		selection = 0;
		hand = new ArrayList<Connector>(5);
	}

	public Color getColour()
	{
		return PLAYER_COLOUR[id];
	}

	public void update(ArrayList<Connector> connectorBag, ConnectorGrid grid)
	{
		score = 0;
		for (byte[] row : grid.getOwnership())
			for (byte id : row)
				if (this.id == id)
					score++;
		while (hand.size() < 5)
			hand.add(connectorBag.remove(connectorBag.size() - 1));
	}

	public String toString()
	{
		return String.format("Player %d Score: %d", id + 1, score);
	}

	public boolean equals(Object other)
	{
		if (other instanceof Player && ((Player) other).id == id
				&& ((Player) other).score == score)
			return true;
		return false;
	}

	public int hashCode()
	{
		return score * 10 + id;
	}

	public byte id()
	{
		return id;
	}

	public BufferedImage getImage()
	{
		BufferedImage buf = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buf.getGraphics();
		g2D.setColor(PLAYER_COLOUR[id]);
		g2D.fillRect(0, 0, WIDTH, HEIGHT);
		for (int c = 0; c < hand.size(); c++) {
			g2D.drawImage(hand.get(c).getImage(),
					16 + Connector.IMAGE_SIZE * c, 16, null);
		}
		return buf;
	}

	public Connector removeSelected()
	{
		return hand.remove(selection);
	}
}
