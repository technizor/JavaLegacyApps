package tttduel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jetstorm.stormFrame.StormConstraints;
import org.jetstorm.stormFrame.StormFrame;

public class Game extends StormFrame
{
	private static final long serialVersionUID = -6860223347335211187L;
	private Board currentBoard;
	private HashSet<Board> usedBoards;
	private BufferedImage gridIcon;
	private JPanel content;
	private JPanel gridPane;
	private JButton[][] gridButtons;
	private JButton newGameButton;
	private JLabel notification;
	private boolean playingGame;

	private static final byte[] gridIconByte = { -119, 80, 78, 71, 13, 10, 26,
			10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, -64, 0, 0, 0, 64, 8, 6,
			0, 0, 0, 76, 108, 120, -33, 0, 0, 2, -81, 73, 68, 65, 84, 120, -38,
			-19, -35, 97, 106, -29, 48, 16, -122, -31, 64, -113, 18, -40, -69,
			-20, -83, 122, -105, 66, -113, -78, -80, 119, -39, 59, -72, -91,
			-76, -117, 9, -111, 45, -39, 30, 73, -74, 30, -61, -5, -89, 36,
			-115, 18, -66, -73, -75, -99, -103, -47, 109, -102, -90, 27, 48,
			42, 62, 4, 16, 0, 32, 0, 64, 0, -128, 0, 0, 1, 0, 2, 0, 4, 0, 8,
			48, 52, -1, -18, -9, 41, -59, -56, 107, -69, -1, -2, 51, 45, 81,
			-21, 117, 110, -119, -125, 0, 7, -122, -20, -17, -25, 71, -11, -56,
			-5, 55, -83, -61, -97, 90, 91, -115, -16, -33, 126, -67, 61, -25,
			-27, 117, 90, 11, 110, 9, 75, -81, -13, 5, 1, -22, 11, -48, 82,
			-126, -91, -16, 71, 11, -80, 26, -54, -103, 0, -117, -113, -55, 33,
			-25, -9, 36, 36, 32, 64, 5, 1, 90, 72, -80, 22, -2, 72, 1, -78,
			-62, 95, 91, -128, -124, 4, 4, -88, 36, 64, 77, 9, 114, -62, 31,
			37, 64, 118, -8, 91, 8, -16, 68, 2, 2, 84, 20, -96, -122, 4, -71,
			-31, -113, 16, -96, 40, -4, -83, 4, 120, -112, -128, 0, -115, -126,
			23, 33, 65, -53, 53, 20, -121, -65, -91, 0, 51, 9, 8, 112, -111, 0,
			-74, 22, 112, 83, -112, 91, 10, -16, -3, 60, 2, 92, 64, -126, 30,
			-2, -5, -20, 18, -32, -25, -108, 100, 7, 4, 24, 84, -126, 30, -62,
			-65, 73, -128, -126, 83, -112, -56, 83, 48, 2, -100, 88, -126, 94,
			-62, 95, 44, -64, -63, -31, -1, 10, -23, -25, -79, -27, 34, -100,
			0, 39, -107, -96, -89, -16, 23, 9, 16, 16, -2, 31, 1, -118, 37, 32,
			-64, 57, 37, -24, 45, -4, -39, 2, 4, -123, 127, 46, 64, -111, 4, 4,
			56, -97, 4, 61, -122, -1, -65, 0, 25, 23, -85, 97, 21, -101, 15,
			-57, -111, -21, 17, -30, -114, -66, -96, -22, 49, -4, -75, 43, 63,
			107, -81, 71, -128, 27, -108, 40, 44, -107, 48, -9, 24, -2, -43,
			-45, -96, -64, -65, -2, -47, -21, 17, -34, 6, 69, 106, -49, 30,
			-109, -6, 121, 47, -27, -41, 4, -64, 97, 101, -54, 91, 4, 104, -35,
			123, 64, 0, 28, -42, -88, 82, 42, 64, -21, -16, 19, 0, -101, 36,
			120, -97, -123, 119, -50, -110, 0, -87, -25, -76, 126, 63, 4, -64,
			-95, 61, -69, -71, 23, -57, -67, -68, 15, 2, 32, -76, -105, -96,
			69, 95, 47, 1, 8, 64, 0, 2, 8, 41, 1, 8, 0, 2, 16, 0, -57, -109,
			-70, -85, -109, -70, -43, -39, -37, -35, 31, 2, -32, -16, -119,
			109, 37, 119, -127, 122, -71, 27, 68, 0, -8, 34, -116, 0, 80, 10,
			65, 0, 40, -122, 35, 0, -10, -107, 67, -25, -106, 72, 40, -121, 38,
			-128, -122, -104, 78, 26, 98, 90, 116, -125, 69, -81, 71, -128,
			-75, 68, -18, -18, -66, 74, -51, -23, -113, 108, -119, -52, -127,
			0, -102, -30, -85, 55, -59, 71, 11, -96, 41, -34, 88, -108, -18,
			-57, -94, 68, 9, 96, 44, -118, -63, 88, -89, 25, -116, 117, -76, 0,
			6, 99, 25, -115, 104, 52, -94, -47, -120, -29, -124, -65, 39, 9,
			-10, 12, -57, 13, -33, 35, -116, 0, -41, 13, 127, 47, 18, 24, -113,
			46, -4, 54, -56, -80, 65, -122, -102, -2, 81, -73, 72, -38, 122,
			17, 106, -117, -92, -127, 4, -72, -14, 38, 121, 91, 111, 67, -38,
			36, 111, 16, 1, -82, -66, 77, -22, -42, 47, -94, 108, -109, 58,
			-128, 0, 35, 108, -108, -99, 42, 69, -80, 81, -10, -32, 2, -76,
			-82, -47, 95, 107, -68, -119, 18, 32, 75, -126, 90, 123, -124, 37,
			-62, 79, -128, 74, 83, -34, 70, 88, 91, -22, -88, 85, 33, -70, 42,
			82, -30, 32, 0, 64, 0, -128, 0, 0, 1, 0, 2, 0, 4, 0, 8, 0, 16, 0,
			4, 0, -122, -27, 3, -48, -5, 9, -32, -20, 79, 10, -87, 0, 0, 0, 0,
			73, 69, 78, 68, -82, 66, 96, -126 };

	private static final byte[] winIcon = { -119, 80, 78, 71, 13, 10, 26, 10,
			0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 64, 0, 0, 0, 64, 8, 6, 0, 0,
			0, -86, 105, 113, -34, 0, 0, 1, -77, 73, 68, 65, 84, 120, -38, -19,
			-102, -37, 109, -61, 48, 12, 69, 13, 116, 20, 3, -39, -91, 91, 101,
			-105, 0, 25, -91, 64, 118, -55, 14, -119, -13, -95, 66, 21, 68,
			-15, 82, -42, -77, -70, 23, -32, 79, 16, -37, -28, -79, -11, 32,
			-59, 109, -93, 40, -118, -94, 40, -118, -94, 100, 61, -9, -3, 21,
			-77, -3, -5, 39, 105, -91, -98, -81, 61, 71, -14, -81, 40, -128,
			-57, -74, -3, -79, -33, 32, 47, -73, -72, 125, 93, 85, 64, 22, 75,
			61, -25, 99, -95, 127, -9, -61, -86, 1, 80, -99, -14, 0, 36, -1,
			-125, 24, 114, -97, 8, -124, 106, 0, -96, -32, 91, 3, -120, 64,
			-88, 2, 0, 14, -66, 7, -128, 0, 66, 113, 0, -90, -32, 123, 1, -16,
			32, 20, 5, 96, 14, -66, 39, 0, 15, 66, 81, 0, 77, 28, 47, 121, -97,
			97, 0, -72, -73, 113, -62, -26, 3, -32, -115, -61, 51, 99, 49, -36,
			112, -51, 1, 32, -78, 28, -27, 64, 112, -63, -101, -105, -33, -82,
			0, 18, -69, 50, 11, -124, 48, -8, 44, 8, -51, 1, 8, -63, 91, 33,
			72, -63, -25, 108, -60, -54, 2, 0, 38, 43, -55, 105, 20, -126, 22,
			-68, 15, 1, -102, 64, 75, 10, -55, -56, 52, -57, 31, 0, 0, -12, 30,
			-83, 50, 81, 108, 24, 28, -76, -47, -73, -9, 113, 94, 74, 95, -47,
			-21, 29, -128, 38, -97, 63, 10, 0, -3, -124, 29, 0, -12, 119, 105,
			8, 13, 9, 0, -127, -112, 11, 32, -100, 63, -122, 5, -96, 65, -56,
			1, 16, -101, 60, -121, 6, -32, 32, -36, 61, -25, 125, 75, 1, -112,
			-82, 57, -21, 79, 115, 0, 90, 61, 17, -99, 28, 75, -5, -45, 5, 0,
			-78, -76, 89, -105, 76, 2, 32, 0, 2, -104, 3, -128, 52, -85, 75,
			75, -99, 54, -5, 79, -75, 12, 90, -74, -68, -38, 22, 121, 42, 0,
			75, 111, -124, -106, -34, 10, 47, -99, 12, 89, -45, 97, 116, -117,
			60, 86, 58, -100, -88, -66, -12, 40, -120, 12, 83, 13, 114, -42,
			-78, 36, 6, 29, -87, -77, 40, -54, -78, 56, 15, 70, 120, 52, -42,
			-5, 112, -76, 122, -113, -48, -16, -89, -61, -1, -27, 120, 124,
			-23, 6, -119, -27, 91, 100, -40, 36, -59, 54, 57, 54, 74, 102, -73,
			-54, 86, -17, 17, 106, -43, 42, 27, 75, 95, 91, 101, 100, -38, 115,
			-84, -11, 68, -118, -94, 40, -118, -94, 40, -22, -48, 27, -14, 78,
			-20, 53, 4, 56, -65, 14, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96,
			-126 };

	public Game()
	{
		super("TicTacToe: Duel", false, false);
	}

	@Override
	public void actionHandler(Object source)
	{
		if (source.equals(newGameButton)) {
			newGame();
		} else {
			for (int r = 0; r < 3; r++)
				for (int c = 0; c < 3; c++)
					if (source.equals(gridButtons[r][c]))
						makeMove(r * 3 + c);
		}
	}

	public void setGridEnabled(boolean enabled)
	{
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				gridButtons[r][c].setEnabled(enabled);
	}

	public boolean makeMove(int pos)
	{
		if (currentBoard == null)
			return false;
		if (!currentBoard.makeMove(pos))
			return false;
		updateGridButtons();
		if (currentBoard.isWinState()) {
			setGridEnabled(false);
			notification.setText(String.format("Player %d Wins",
					currentBoard.currentPlayer() == 1 ? 2 : 1));
			newGameButton.setText("New Game");
			playingGame = false;
		} else if (currentBoard.turnsPassed() == 9) {
			setGridEnabled(false);
			if (!usedBoards.add(currentBoard)) {
				notification.setText(String.format("Player %d Wins by repeated end state.",
						currentBoard.currentPlayer()));
				newGameButton.setText("New Game");
				playingGame = false;
			} else {
				notification.setText("Stalemate reached. Round end.");
				newGameButton.setText("Next Round");
			}
		} else {
			notification.setText(String.format("Player %d's Turn",
					currentBoard.currentPlayer()));
		}
		return true;
	}

	public void updateGridButtons()
	{
		if (currentBoard == null) {
			for (int r = 0; r < 3; r++)
				for (int c = 0; c < 3; c++)
					gridButtons[r][c].setIcon(new ImageIcon(gridIcon
							.getSubimage(0, 0, 64, 64)));
			return;
		}
		String boardState = currentBoard.toString();
		String vals = " XO";
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++) {
				int n = vals.indexOf(boardState.charAt(r * 3 + c));
				gridButtons[r][c].setIcon(new ImageIcon(gridIcon.getSubimage(
						n * 64, 0, 64, 64)));
			}
	}

	public void newGame()
	{
		if (!playingGame) {
			currentBoard = new Board(0);
			updateGridButtons();
			playingGame = true;
			notification.setText(String.format(
					"New Duel Started. Player %d's Turn",
					currentBoard.currentPlayer()));
			newGameButton.setText("Forfeit");
			setGridEnabled(true);
		} else if (currentBoard.turnsPassed() == 9) {
			currentBoard = new Board(usedBoards.size());
			updateGridButtons();
			notification.setText(String.format(
					"Round %d started. Player %d's Turn",
					usedBoards.size() + 1, currentBoard.currentPlayer()));
			newGameButton.setText("Forfeit");
			setGridEnabled(true);
		} else {
			notification.setText(String.format("Player %d has forfeited.",
					currentBoard.currentPlayer()));
			newGameButton.setText("New Game");
			currentBoard = null;
			usedBoards.clear();
			setGridEnabled(false);
			playingGame = false;
		}
	}

	@Override
	public void addActionListeners()
	{
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				gridButtons[i][j].addActionListener(this);
		newGameButton.addActionListener(this);
	}

	@Override
	public void buildDefaultElements()
	{
		buildElement(content, new StormConstraints(1, 1, 0, 0, 0, 0, 0, 0));
	}

	@Override
	public void configureElements()
	{
		usedBoards = new HashSet<Board>();
		currentBoard = null;
		playingGame = false;

		InputStream in = new ByteArrayInputStream(gridIconByte);
		try {
			gridIcon = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream in2 = new ByteArrayInputStream(winIcon);
		try {
			BufferedImage buf = ImageIO.read(in2);
			setIconImage(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}

		content = new JPanel();
		content.setLayout(new BorderLayout());
		gridPane = new JPanel();
		gridPane.setLayout(new GridLayout(3, 3));
		gridButtons = new JButton[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				gridButtons[i][j] = new JButton();
				gridButtons[i][j].setIcon(new ImageIcon(gridIcon.getSubimage(0,
						0, 64, 64)));
				gridPane.add(gridButtons[i][j]);
				gridButtons[i][j].setEnabled(false);
			}
		content.add(gridPane, BorderLayout.CENTER);
		newGameButton = new JButton("New Game");
		notification = new JLabel("Press New Game to Start");
		content.add(newGameButton, BorderLayout.SOUTH);
		content.add(notification, BorderLayout.NORTH);
	}

	public static void main(final String[] args)
	{
		Game game = new Game();
		game.setVisible(true);
	}
}
