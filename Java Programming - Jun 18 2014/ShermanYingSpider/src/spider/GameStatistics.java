package spider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tracks information such as the total number of games played and won, and the
 * top ten players.
 * 
 * @author Sherman Ying
 * @version May 19, 2013
 * 
 */
public class GameStatistics implements Serializable
{
	private static final long serialVersionUID = -1311095228839131463L;
	private ArrayList<Player> topPlayers;
	private int gamesWon;
	private int gamesPlayed;
	private String fileName;

	/**
	 * Creates a game statistic object from the specified file.
	 * 
	 * @param fileName
	 */
	public GameStatistics(String fileName)
	{
		this.fileName = fileName;
		loadStats();
	}

	/**
	 * Reloads the statistics from a file.
	 */
	@SuppressWarnings("unchecked")
	public void loadStats()
	{
		try {
			ObjectInputStream fileIn = new ObjectInputStream(
					new FileInputStream(fileName));
			topPlayers = (ArrayList<Player>) fileIn.readObject();
			gamesWon = (Integer) fileIn.readObject();
			gamesPlayed = (Integer) fileIn.readObject();
			fileIn.close();
		} catch (IOException ioe) {
			topPlayers = new ArrayList<Player>();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Adds the given player to the top players list.
	 * 
	 * @param currentPlayer the player to add to the top ten players.
	 */
	public void addScore(Player currentPlayer)
	{
		gamesWon++;
		int index = -Collections.binarySearch(topPlayers, currentPlayer) - 1;
		if (index < 10) {
			if (topPlayers.size() > 9)
				topPlayers.remove(9);
			topPlayers.add(index, currentPlayer);

			saveStats();
		}
	}

	/**
	 * Writes the statistics to a file.
	 */
	public void saveStats()
	{
		try {
			ObjectOutputStream fileOut = new ObjectOutputStream(
					new FileOutputStream(new File(fileName)));
			fileOut.writeObject(topPlayers);
			fileOut.writeObject(gamesWon);
			fileOut.writeObject(gamesPlayed);
			fileOut.close();
		} catch (IOException ioe) {
			System.out.println("Unable to save top players.");
		}
	}

	/**
	 * Gives a list of the top ten players.
	 * 
	 * @return the top ten player list.
	 */
	public ArrayList<Player> getTopPlayers()
	{
		return topPlayers;
	}

	/**
	 * Increments the number of games played.
	 */
	public void gameStarted()
	{
		gamesPlayed++;
		saveStats();
	}

	/**
	 * Gives a short summary of the player's overall performance.
	 */
	public String toString()
	{
		double winPerc = gamesPlayed > 0 ? ((double) gamesWon / gamesPlayed)*100 : 0;
		return String.format(
				"Games Played: %d%nGames Won: %d%nWin Percentage: %.2f%%",
				gamesPlayed, gamesWon, winPerc);
	}
}
