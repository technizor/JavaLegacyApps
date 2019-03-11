package connectFour;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class ConnectFourGame extends JFrame implements MouseListener, Runnable, MouseMotionListener
{
	private static final long serialVersionUID = -6709943497320779458L;
	private boolean running;
	private int[][] board;
	private int turn;
	private int blueScore;
	private int redScore;
	private int tieScore;
	private int mouseX;
	private int mouseY;
	private int lastStart;
	private GameScreen window;
	private ImageIcon gameIcon;
	private final int tileSize = 64;
	
	
	public ConnectFourGame()
	{
		board = new int[7][6];//wide, high
		turn = 0;
		lastStart = 0;
		window = new GameScreen();
		window.setPreferredSize(new Dimension(20+7*tileSize, 20+6*tileSize));
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		window.board = board;
		gameIcon = new ImageIcon(window.tiles.getSubimage(129, 65, 62, 62));
		this.setIconImage(gameIcon.getImage());
		this.setResizable(false);
		this.setTitle("ConnectFour");
		this.add(window);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.run();
		//0 = null, 1 = red, 2 = black
	}
	public int checkWin(int posX, int posY, int player)
	{
		//Check Vertical down
		int count = 1;
		for(int offset = 1; offset < 4 && posY-offset >= 0 && 
			board[posX][posY-offset] == player; offset++, count++);
		if(count == 4) {
			return player;
		}
		count = 0;
		//Horizontal
		for(int offset = -3; offset < 4 && count != 4; offset++) {
			if(posX+offset > -1 && posX+offset < 7) {
				if(board[posX+offset][posY] != player) 
					count = 0;
				else count++;
			}
		}
		if(count == 4) {
			return player;
		}
		count = 0;
		//Diagonal top left to bot right
		for(int offset = -3; offset < 4 && count != 4; offset++) {
				if(posX+offset > -1 && posX+offset < 7 && posY-offset > -1 && posY-offset < 6) {
					if(board[posX+offset][posY-offset] != player) 
						count = 0;
					else count++;
				}
		}
		if(count == 4) {
			return player;
		}
		count = 0;
		//Other diagonal
		for(int offset = -3; offset < 4 && count != 4; offset++) {

			if(posX+offset > -1 && posX+offset < 7 && posY+offset > -1 && posY+offset < 6) {
				if(board[posX+offset][posY+offset] != player) 
					count = 0;
				else count++;
			}
		}
		if(count == 4) {
			return player;
		}
		return 0;
	}
	
	public void mousePressed(MouseEvent arg0) {
		int column = arg0.getX()-10;
		if(column >= 0 && column < 448 && arg0.getY() >= 10 && arg0.getY() < 394) {
			if(board[column/64][5] == 0) {
				add(column/64);
			}
		}
	}
	public void add(int col)
	{
		int player = ((turn + lastStart) % 2) + 1;
		int i = 0;
		for(;i < 6; i++) {
			if(board[col][i] == 0) {
				board[col][i] = player;
				break;
			}
		}
		window.turnPlayer = (window.turnPlayer + 1) % 2;
		window.board = board;
		turn++;
		int winner = checkWin(col, i, player);
		if(winner != 0) 
			window.won = winner;
		else if(turn == 42) {
			window.won = 3;
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {}
	
	public void run() {
		running = true;
		while(running) {
			window.paint(window.getGraphics());
			try {
				if(window.won == 0) 
					Thread.sleep(5);
				else {
					if(window.won == 1) {
						redScore++;
						window.turnPlayer = 1;
					} else if(window.won == 2) {
						blueScore++;
						window.turnPlayer = 0;
					} else 	{
						tieScore++;
						window.turnPlayer = (lastStart+1) % 2;
					}
					lastStart = window.turnPlayer;
					window.blueS = blueScore;
					window.redS = redScore;
					window.tieS = tieScore;
					window.paint(window.getGraphics());
					Thread.sleep(1500);
					board = new int[7][6];
					turn = 0;
					window.reset();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void mouseMoved(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		if(x >= 10 && x < 458 && y >= 10 && y  < 394) {
			mouseX = (x-10)/64;
			mouseY = 5-((y-10)/64);
			window.xPos = mouseX;
			window.yPos = mouseY;
		} else {
			window.xPos = mouseX = -1;
			window.yPos = mouseY = -1;
		}
	}
	public int[][] getBoard()
	{
		return board;
	}
	
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {
		window.xPos = -1;
		window.yPos = -1;
	}
	public void mouseDragged(MouseEvent arg0) {}
}
