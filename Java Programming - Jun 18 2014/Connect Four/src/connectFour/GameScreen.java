package connectFour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameScreen extends JPanel {
	private static final long serialVersionUID = 5008607685134919937L;
	public int[][] board;
	public int won;
	public BufferedImage tiles;
	public BufferedImage gray;
	/*private static Image loadImage(String path)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = GameScreen.class.getResource(path);
		Image img = tk.createImage(url);
		tk.checkImage(img, -1, -1, null);
		return img;
	}
	private static BufferedImage toBufferedImage(Image img)
	{
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = buff.createGraphics();
		g2D.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
		g2D.dispose();
		return buff;
	}*/
	private final byte[] TILEPNG = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 
			0, 0, 13, 73, 72, 68, 82, 0, 
			0, 0, -64, 0, 0, 0, -128, 8, 
			2, 0, 0, 0, -46, -26, -15, 60, 
			0, 0, 2, -97, 73, 68, 65, 84, 
			120, -38, -19, -35, -47, -119, -62, 64, 
			20, 5, -48, 1, 75, 9, 88, -118, 
			-80, 69, -92, -125, 45, 34, -67, 4, 
			44, 69, -80, -108, -123, -19, 97, 23, 
			-4, 82, 19, 113, -52, 58, -55, -68, 
			-39, 115, 121, -33, 122, -61, 59, -28, 
			35, -60, 49, 37, -111, 63, 102, 12, 
			27, -3, 107, -24, 31, -11, 2, -6, 
			-66, -41, -65, -122, -2, 41, 104, -5, 
			-48, 11, 104, -87, 127, 10, -38, 62, 
			-18, 2, 26, -21, 127, 115, 1, 67, 
			-11, -23, -81, -94, 127, 13, -3, 45, 
			64, 127, -128, -12, 7, 8, 32, -128, 
			26, 89, -64, 119, -41, -51, -50, -54, 
			-3, -69, -113, 83, 37, 3, -48, -53, 
			-128, -50, 41, -35, -51, 49, -91, -11, 
			1, -91, -3, -72, -3, -20, -36, -127, 
			0, 2, 8, 32, -128, 0, 2, 8, 
			32, -128, 0, 2, 8, -96, 127, 12, 
			104, 55, -108, 29, -128, -102, 7, 84, 
			46, 57, -33, 8, 16, 64, 0, 1, 
			4, 16, 64, 0, 1, 4, 16, 64, 
			0, 1, 4, 16, 64, 0, 1, 4, 
			16, 64, 0, 1, 4, 16, 64, 0, 
			-67, 105, 1, -113, -34, 76, -51, -103, 
			89, 64, -117, 63, 13, -96, -88, -128, 
			-90, 14, -42, -97, -69, 91, 23, 64, 
			0, 1, 4, 16, 64, 0, 1, 4, 
			16, 64, 0, 1, 4, 80, -61, -128, 
			-114, -105, -3, 45, -104, 89, 7, -117, 
			7, 32, 15, 18, 61, 72, 4, 8, 
			32, -128, 0, 2, 8, 32, -128, 0, 
			2, 8, 32, -128, 0, 2, 8, 32, 
			-128, 0, 2, 8, 32, -128, 0, 2, 
			8, 32, -128, 0, -54, 89, 103, -47, 
			3, 89, 1, 106, 29, -112, 3, -90, 
			0, 2, 8, 32, -128, 0, 2, 8, 
			32, -128, 0, 2, 8, -96, 22, 1, 
			61, 125, 51, 117, 37, 64, -91, -49, 
			100, -51, 27, -128, -12, 47, -40, -33, 
			2, -12, 7, 72, 127, -128, 0, 106, 
			1, 80, 31, 42, -6, -41, -48, 63, 
			5, 109, 63, 93, -128, -2, 91, 2, 
			-22, 3, 70, -1, 90, 0, -11, 49, 
			-93, 127, 21, -128, 68, 68, -74, -53, 
			41, 108, -12, -81, -95, 127, -44, 11, 
			24, -122, 65, -1, 26, -6, -89, -96, 
			-19, 67, 47, -96, -91, -2, 41, 104, 
			-5, -72, 11, 104, -84, -1, -51, 5, 
			-116, -43, -25, -6, -79, -70, -2, 53, 
			-12, -73, 0, -3, 1, -46, 31, 32, 
			-128, 0, 106, 100, 1, 63, -121, -61, 
			-20, -84, -36, -1, -16, -7, 85, -55, 
			0, -12, 50, -96, -103, -1, -17, -23, 
			-70, -11, 1, 21, -3, 21, 115, -18, 
			-20, -35, -127, 0, 2, 8, 32, -128, 
			0, 2, 8, 32, -128, 0, 2, 8, 
			-96, 127, 12, 104, 63, -106, 29, -128, 
			-102, 7, 84, 46, 57, -33, 8, 16, 
			64, 0, 1, 4, 16, 64, 0, 1, 
			4, 16, 64, 0, 1, 4, 16, 64, 
			0, 1, 4, 16, 64, 0, 1, 4, 
			16, 64, 0, -67, 105, 1, -113, -34, 
			76, -51, -103, 89, 64, -117, 63, 13, 
			-96, -88, -128, -90, 14, -42, -97, -69, 
			91, 23, 64, 0, 1, 4, 16, 64, 
			0, 1, 4, 16, 64, 0, 1, 4, 
			80, -61, -128, -50, -105, -3, 45, -104, 
			89, 7, -117, 7, 32, 15, 18, 61, 
			72, 4, 8, 32, -128, 0, 2, 8, 
			32, -128, 0, 2, 8, 32, -128, 0, 
			2, 8, 32, -128, 0, 2, 8, 32, 
			-128, 0, 2, 8, 32, -128, 0, -54, 
			89, 103, -47, 3, 89, 1, 106, 29, 
			-112, 3, -90, 0, 2, 8, 32, -128, 
			0, 2, 8, 32, -128, 0, 2, 8, 
			-96, 22, 1, 61, 125, 51, 117, 37, 
			64, -91, -49, 100, -51, 27, -128, -12, 
			47, -40, -33, 2, -12, 7, 72, 127, 
			-128, 0, 106, 1, -48, 16, 42, -6, 
			-41, -48, 63, 5, 109, 63, 93, -128, 
			-2, 91, 2, 26, 2, 70, -1, 90, 
			0, 13, 49, -93, 127, 21, -128, 68, 
			22, -25, 23, 98, 60, -124, -18, -104, 
			108, 98, -19, 0, 0, 0, 0, 73, 
			69, 78, 68, -82, 66, 96, -126};
	private final byte[] GRAYPNG = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 
			0, 0, 13, 73, 72, 68, 82, 0, 
			0, 0, 1, 0, 0, 0, 1, 8, 
			6, 0, 0, 0, 31, 21, -60, -119, 
			0, 0, 0, 13, 73, 68, 65, 84, 
			120, -38, 99, 96, 96, 96, 120, 2, 
			0, 0, -23, 0, -27, 100, -50, 43, 
			87, 0, 0, 0, 0, 73, 69, 78, 
			68, -82, 66, 96, -126};
	public int blueS;
	public int redS;
	public int tieS;
	public int xPos;
	public int yPos;
	public int turnPlayer;
	public GameScreen()
	{
		try {
			InputStream in0 = new ByteArrayInputStream(TILEPNG);
			InputStream in1 = new ByteArrayInputStream(GRAYPNG);
			tiles = ImageIO.read(in0);
			gray = ImageIO.read(in1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//tiles = toBufferedImage(loadImage("tileset.png"));
		//gray = toBufferedImage(loadImage("gray.png"));
		reset();
	}
	public void reset()
	{
		won = 0;
		xPos = -1;
		yPos = -1;
		board = new int[7][6];
		won = 0;
	}
	public void paint(Graphics g)
	{
		Image image = createImage(getWidth(), getHeight());
		Image score = createImage(getWidth(), 10);
		Graphics2D g2D = ((Graphics2D) image.getGraphics());
		g2D.clearRect(0, 0, getWidth(), getHeight());
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, getWidth(), getHeight());
		if(board == null || tiles == null) return;
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 6; y++) {
				int piece = board[x][y];
				int highlighted = 0;
				if(xPos == x && board[x][y] == 0) {
					highlighted = 1;
				}
				Image image1 = (Image) tiles.getSubimage(piece*64, highlighted*64, 64, 64);
				g2D.drawImage(image1, x*64+10, (5-y)*64+10, this);
			}
		}
		Graphics2D g2D1 = (Graphics2D) score.getGraphics();
		String rs = "RED " + redS;
		String bs = blueS + " BLUE";
		String ts = "" + tieS;
		//int rw = (int) g2D1.getFont().getStringBounds(rs, g2D1.getFontRenderContext()).getWidth();
		int bw =(int) g2D1.getFont().getStringBounds(bs, g2D1.getFontRenderContext()).getWidth();
		int tw = (int) g2D1.getFont().getStringBounds(ts, g2D1.getFontRenderContext()).getWidth();
		g2D1.setColor(Color.BLACK);
		g2D1.fillRect(0, 0, score.getWidth(this), score.getHeight(this));
		g2D1.setColor(Color.RED);
		g2D1.drawString(rs,10, 10);
		g2D1.setColor(Color.GRAY);
		g2D1.drawString(ts, getWidth()/2-tw/2, 10);
		g2D1.setColor(Color.BLUE);
		g2D1.drawString(bs, getWidth()-10-bw, 10);
		g2D.drawImage(score, 0, 0, this);
		if(turnPlayer == 0) g2D.setColor(Color.RED);
		else g2D.setColor(Color.BLUE);
		g2D.fillRect(10, 394, 448, 10);
		if(won != 0) {
			g2D.drawImage(gray, 10, 10, getWidth()-20, getHeight()-20, this);
			g2D.setColor(Color.WHITE);
			String output = "";
			if(won == 1) {
				output = "Red Player Wins!";
			}
			if(won == 2) {
				output = "Blue Player Wins!";
			}
			if(won == 3) {
				output = "It's a tie!";
			}
			Rectangle2D stringBounds = g2D.getFont().getStringBounds(output, g2D.getFontRenderContext());
			g2D.drawString(output, (int) (getWidth()/2-stringBounds.getWidth()/2), (int) (getHeight()/2-stringBounds.getHeight()/2));
		}
		g.drawImage(image, 0, 0, this);
	}
}
