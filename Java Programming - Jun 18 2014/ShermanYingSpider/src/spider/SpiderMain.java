package spider;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * The main window of the Spider Solitaire program.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class SpiderMain extends JFrame
{
	private static final long serialVersionUID = -4481089242794867941L;

	/**
	 * Initializes the program and runs the window.
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args)
	{
		SpiderMain frame = new SpiderMain();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Player currentPlayer;

	private String playerName;

	private TablePanel tableArea;

	private GameStatistics gameStats;

	/**
	 * Constructs the main window of the Spider Solitaire program.
	 */
	public SpiderMain()
	{
		super("Spider Solitaire");
		setResizable(false);

		// Add in an Icon - Ace of Spades
		setIconImage(new ImageIcon("images\\s1.png").getImage());

		// Add the TablePanel to the centre of the Frame
		setLayout(new BorderLayout());
		tableArea = new TablePanel(this);
		add(tableArea, BorderLayout.CENTER);

		gameStats = new GameStatistics("statistics.dat");
		// Add in the menus
		addMenus();
		addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent evt)
			{
				int keyId = evt.getKeyCode();
				if (keyId == KeyEvent.VK_H) {
					tableArea.useHint();
				} else if (keyId == KeyEvent.VK_Z) {
					if (tableArea.canUndo())
						tableArea.undoMove();
				} else if (keyId == KeyEvent.VK_R) {
					if (tableArea.canReset()) {
						gameStats.gameStarted();
						tableArea.resetGame();
					}
				} else if (keyId == KeyEvent.VK_SLASH) {
					displayHelp();
				}
			}

			@Override
			public void keyReleased(KeyEvent evt)
			{
			}

			@Override
			public void keyTyped(KeyEvent evt)
			{
			}
		});
	}

	/**
	 * Adds the menus to the main frame and action listeners to respond to
	 * selections.
	 */
	private void addMenus()
	{
		JMenuBar menuBar = new JMenuBar();

		// Game submenu
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');

		JMenu newOption = new JMenu("New Game");
		JMenuItem oneSuit = new JMenuItem("One Suit");
		JMenuItem twoSuit = new JMenuItem("Two Suits");
		JMenuItem fourSuit = new JMenuItem("Four Suits");
		JMenuItem resetGame = new JMenuItem("Restart Game");
		JMenuItem seedGame = new JMenuItem("Game by Seed");
		newOption.setMnemonic('G');
		resetGame.setMnemonic('R');
		seedGame.setMnemonic('S');
		oneSuit.setMnemonic('1');
		twoSuit.setMnemonic('2');
		fourSuit.setMnemonic('4');

		oneSuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				startGame(1);
			}
		});
		twoSuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				startGame(2);
			}
		});
		fourSuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				startGame(4);
			}
		});
		resetGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (tableArea.canReset()) {
					gameStats.gameStarted();
					tableArea.resetGame();
				}
			}
		});
		seedGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				seededGame();
			}
		});

		// Score submenu
		JMenu scoreMenu = new JMenu("Statistics");
		JMenuItem hiscore = new JMenuItem("Show High Scores");
		JMenuItem scoreGuide = new JMenuItem("Scoring Guide");
		JMenuItem gameStats = new JMenuItem("Game Statistics");
		JMenuItem renamePlayer = new JMenuItem("Rename Player");
		scoreMenu.setMnemonic('S');
		hiscore.setMnemonic('H');
		scoreGuide.setMnemonic('G');
		gameStats.setMnemonic('T');
		renamePlayer.setMnemonic('R');

		hiscore.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				showHighScores();
			}
		});
		scoreGuide.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				displayScoreGuide();
			}
		});
		gameStats.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				displayGameStats();
			}
		});
		renamePlayer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				choosePlayerName();
			}
		});

		// Info submenu
		JMenu infoMenu = new JMenu("Info");
		JMenuItem help = new JMenuItem("Instructions");
		JMenuItem about = new JMenuItem("About");
		JMenuItem keyShort = new JMenuItem("Keyboard Shortcuts");
		infoMenu.setMnemonic('I');
		help.setMnemonic('H');
		about.setMnemonic('A');
		keyShort.setMnemonic('K');

		help.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				displayHelp();
			}
		});
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				displayInfo();
			}
		});
		keyShort.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				showShortcuts();
			}
		});

		// Help menu// Undo and hint options
		JMenuItem helpMenu = new JMenu("Help");
		JMenuItem undoMove = new JMenuItem("Undo Move");
		JMenuItem hintMove = new JMenuItem("Hint");
		helpMenu.setMnemonic('H');
		undoMove.setMnemonic('U');
		hintMove.setMnemonic('H');

		undoMove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if (tableArea.canUndo())
					tableArea.undoMove();
			}
		});
		hintMove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				tableArea.useHint();
			}
		});

		gameMenu.add(newOption);
		gameMenu.add(resetGame);
		newOption.add(oneSuit);
		newOption.add(twoSuit);
		newOption.add(fourSuit);
		newOption.add(seedGame);
		scoreMenu.add(hiscore);
		scoreMenu.add(scoreGuide);
		scoreMenu.add(gameStats);
		scoreMenu.add(renamePlayer);
		infoMenu.add(help);
		infoMenu.add(about);
		infoMenu.add(keyShort);
		helpMenu.add(undoMove);
		helpMenu.add(hintMove);
		menuBar.add(gameMenu);
		menuBar.add(scoreMenu);
		menuBar.add(infoMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
	}

	/**
	 * Selects a player name from the user input.
	 */
	private void choosePlayerName()
	{
		String initialValue = "";
		if (playerName != null)
			initialValue = playerName;

		String chosenName = (String) JOptionPane.showInputDialog(this,
				"Enter your name:", "Choose a name (Effective Next Game)",
				JOptionPane.QUESTION_MESSAGE, null, null, initialValue);

		// Assign a default name if no selection is made
		if (chosenName == null || chosenName.length() == 0) {
			JOptionPane
					.showMessageDialog(
							this,
							"You have not selected a name!\nYour name will be set to Guest.",
							"Error", JOptionPane.ERROR_MESSAGE);
			playerName = "Guest";
		} else
			playerName = chosenName;
	}

	/**
	 * Displays program information.
	 */
	private void displayInfo()
	{
		JOptionPane.showMessageDialog(this,
				"Spider Solitaire by Sherman Ying.\nMay 16, 2013", "About",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Displays a score guide dialog.
	 */
	private void displayScoreGuide()
	{
		JOptionPane.showMessageDialog(this, Player.scoreGuide(),
				"Scoring Guide", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Adds the current player if in the top ten players, then saves the list if
	 * updated.
	 */
	public void savePlayer()
	{
		gameStats.addScore(currentPlayer);
		JOptionPane.showMessageDialog(this, currentPlayer.getStatistics(),
				"Results", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Displays the top ten scores.
	 */
	private void showHighScores()
	{
		ArrayList<Player> topScores = gameStats.getTopPlayers();
		if (topScores.isEmpty()) {
			JOptionPane.showMessageDialog(this, "There are no top players.",
					"High Scores", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int playerRank = 1;
		for (Player topPlayer : topScores) {
			JOptionPane.showMessageDialog(this, topPlayer.getStatistics(),
					"Rank #" + playerRank, JOptionPane.INFORMATION_MESSAGE);
			playerRank++;
		}
	}

	/**
	 * Starts a new game with the specified number of suits.
	 * 
	 * @param noOfSuits
	 */
	private void startGame(int noOfSuits)
	{
		// Ask for player name if first game
		if (playerName == null)
			choosePlayerName();

		int seed = (int) (Math.random() * 32000);
		currentPlayer = new Player(playerName, noOfSuits, seed);
		gameStats.gameStarted();
		tableArea.newGame(currentPlayer, noOfSuits, seed);
	}

	/**
	 * Displays instructions on playing Spider Solitaire.
	 */
	private void displayHelp()
	{
		String helpStr = "Drag and drop cards to move them between stacks.\n"
				+ "Click on the deck in the bottom right corner to deal a set of 10 card, 1 on each stack.\n"
				+ "Cards are removed if they form a sequence from King at the top to Ace in the same suit.\n"
				+ "Cards can be placed on any suit card with one rank greater, but only sequences with\n"
				+ "ascending rank and the same suit can be picked up at the same time.\n"
				+ "See Score > Scoring Guide for detailed scoring information.";
		JOptionPane.showMessageDialog(this, helpStr, "Spider Solitaire Help",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Displays game win statistics.
	 */
	private void displayGameStats()
	{
		JOptionPane.showMessageDialog(this, gameStats.toString(),
				"Game Statistics", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Prompts the user for a new game seed.
	 */
	private void seededGame()
	{
		String suitStr = (String) JOptionPane.showInputDialog(this,
				"Number of Suits:", "Seeded Game",
				JOptionPane.QUESTION_MESSAGE, null, null, "1");
		while (suitStr == null
				|| suitStr.length() > 1
				|| (suitStr.charAt(0) != '1' && suitStr.charAt(0) != '2' && suitStr
						.charAt(0) != '4'))
			suitStr = (String) JOptionPane.showInputDialog(this,
					"Number of Suits:", "Enter a valid number of suits!",
					JOptionPane.QUESTION_MESSAGE, null, null, "1");

		String seedStr = (String) JOptionPane.showInputDialog(this,
				"Game Seed:", "Seeded Game", JOptionPane.QUESTION_MESSAGE,
				null, null, "");
		boolean invalidSeed = false;
		try {
			Integer.parseInt(seedStr);
		} catch (NumberFormatException nfe) {
			invalidSeed = true;
		}
		while (invalidSeed) {
			seedStr = (String) JOptionPane.showInputDialog(this, "Game Seed:",
					"Invalid Seed!", JOptionPane.QUESTION_MESSAGE, null, null,
					"");
			try {
				Integer.parseInt(seedStr);
				invalidSeed = false;
			} catch (NumberFormatException nfe) {
			}
		}

		// Ask for player name if first game
		if (playerName == null)
			choosePlayerName();

		int seed = Integer.parseInt(seedStr);
		int noOfSuits = suitStr.charAt(0) - '0';
		currentPlayer = new Player(playerName, noOfSuits, seed);
		gameStats.gameStarted();
		tableArea.newGame(currentPlayer, noOfSuits, seed);
	}
	
	/**
	 * Displays keyboard shortcuts.
	 */
	private void showShortcuts()
	{
		String str = "Z to Undo a Move.\n" +
				"H to Use a Hint.\n" +
				"R to Restart the Game.";
		JOptionPane.showMessageDialog(this, str, "Keyboard Shortcuts", JOptionPane.INFORMATION_MESSAGE);
	}
}

// http://unintentional-irony.blogspot.ca/2007/12/spider-solitaire.html
// http://www.solitairecity.com/Help/Spider_Scoring.shtml