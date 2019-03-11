import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ConnectorGrid
{
	private Connector[][] grid;
	private byte[][] ownership;
	private byte width;
	private byte height;

	public ConnectorGrid()
	{
		width = 11;
		height = 11;
		grid = new Connector[height][width];
		ownership = new byte[height][width];
		for (int r = 0; r < height; r++)
			for (int c = 0; c < width; c++) {
				grid[r][c] = new Connector(0);
				ownership[r][c] = 0;
			}

		grid[0][0] = new Connector(9);
		grid[0][3] = new Connector(8);
		grid[0][7] = new Connector(8);
		grid[0][10] = new Connector(12);
		grid[1][5] = new Connector(7);
		grid[2][2] = new Connector(15);
		grid[2][8] = new Connector(15);
		grid[3][0] = new Connector(1);
		grid[3][10] = new Connector(4);
		grid[5][1] = new Connector(14);
		grid[5][5] = new Connector(15);
		grid[5][9] = new Connector(11);
		grid[7][0] = new Connector(1);
		grid[7][10] = new Connector(4);
		grid[8][2] = new Connector(15);
		grid[8][8] = new Connector(15);
		grid[5][9] = new Connector(13);
		grid[10][0] = new Connector(3);
		grid[10][3] = new Connector(2);
		grid[10][7] = new Connector(2);
		grid[10][10] = new Connector(6);
	}

	public boolean placeConnector(Player player,
			ArrayList<Connector> connectorBag, int r, int c)
			throws IllegalArgumentException
	{
		if (r < 0 || c < 0 || r >= height || c >= width)
			throw new IllegalArgumentException();
		if (grid[r][c].getId() != 0)
			return false;
		grid[r][c] = player.removeSelected();
		flood(player, new boolean[height][width], r, c);
		player.update(connectorBag, this);

		return true;
	}

	private void flood(Player player, boolean[][] checked, int r, int c)
	{
		checked[r][c] = true;
		ownership[r][c] = player.id();

		if (c + 1 < width && grid[r][c].connectRight()
				&& grid[r][c + 1].connectLeft() && !checked[r][c + 1])
			flood(player, checked, r, c + 1);
		if (r - 1 >= 0 && grid[r][c].connectUp()
				&& grid[r - 1][c].connectDown() && !checked[r - 1][c])
			flood(player, checked, r - 1, c);
		if (c - 1 >= 0 && grid[r][c].connectLeft()
				&& grid[r][c - 1].connectRight() && !checked[r][c - 1])
			flood(player, checked, r, c - 1);
		if (r + 1 < height && grid[r][c].connectDown()
				&& grid[r + 1][c].connectUp() && !checked[r + 1][c])
			flood(player, checked, r + 1, c);
	}

	public BufferedImage getImage()
	{
		BufferedImage buf = new BufferedImage(Connector.IMAGE_SIZE * width,
				Connector.IMAGE_SIZE * height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buf.getGraphics();
		for (int r = 0; r < 11; r++)
			for (int c = 0; c < 11; c++) {
				g2D.setColor(Player.PLAYER_COLOUR[ownership[r][c]]);
				g2D.fillRect(c * Connector.IMAGE_SIZE,
						r * Connector.IMAGE_SIZE, Connector.IMAGE_SIZE,
						Connector.IMAGE_SIZE);
				g2D.drawImage(grid[r][c].getImage(), c * Connector.IMAGE_SIZE,
						r * Connector.IMAGE_SIZE, null);
			}
		return buf;
	}

	public byte[][] getOwnership()
	{
		return ownership;
	}
}
