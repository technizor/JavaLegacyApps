package spider;

import java.io.Serializable;

/**
 * A Player tracks the current score and other data such as number of moves,
 * undos, and hints.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class Player implements Comparable<Player>, Serializable
{
	public static final int FLIP_SCORE = 150;

	public static final int[] FOUNDATION_SCORE = { 0, 520, 780, 0, 1040 };

	public static final int[] HINT_PENALTY = { 0, -50, -100, 0, -200 };
	public static final int MOVE_BONUS_MODIFIER = 25;

	public static final int MOVE_PAR = 300;
	public static final int NO_HINT_BONUS = 1000;
	public static final int NO_UNDO_BONUS = 1000;
	public static final int SCORE_BONUS_MODIFIER = 4;
	private static final long serialVersionUID = 7410681793000517568L;
	public static final int UNDO_PENALTY = -100;

	/**
	 * Gives the scoring guide in place for Spider Solitaire.
	 * 
	 * @return the scoring guide.
	 */
	public static String scoreGuide()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Points:\n");
		builder.append(String.format("Transfer to Foundation: %d/%d/%d%n",
				FOUNDATION_SCORE[1], FOUNDATION_SCORE[2], FOUNDATION_SCORE[4]));
		builder.append(String
				.format("Flip a facedown card: %d%n%n", FLIP_SCORE));
		builder.append("Bonuses:\n");
		builder.append("Completion Bonus: Score/4\n");
		builder.append(String.format("Move Bonus: (%d-Moves)x%d%n", MOVE_PAR,
				MOVE_BONUS_MODIFIER));
		builder.append(String.format("No Undo Bonus: %d%n", NO_UNDO_BONUS));
		builder.append(String.format("No Hint Bonus: %d%n%n", NO_HINT_BONUS));
		builder.append("Penalties:\n");
		builder.append(String.format("Undo Move: %d%n", UNDO_PENALTY));
		builder.append(String.format("Use Hint: %d/%d/%d%n%n", HINT_PENALTY[1],
				HINT_PENALTY[2], HINT_PENALTY[4]));
		return builder.toString();
	}

	private int cardFlips;

	private int foundationTransfers;

	private int hints;

	private int moves;

	private int noOfSuits;

	private String playerName;
	private int seed;

	private int undos;

	/**
	 * Creates a player with the given name, number of suits, and game seed.
	 * 
	 * @param name the player's name.
	 * @param noOfSuits the number of suits in the game.
	 * @param seed the seed of the game being played.
	 */
	public Player(String name, int noOfSuits, int seed)
	{
		this.playerName = name;
		this.noOfSuits = noOfSuits;
		this.seed = seed;
		resetScore();
	}

	/**
	 * Adds the score for flipping a card.
	 */
	public void addFlipScore()
	{
		if (cardFlips < 54)
			cardFlips++;
	}

	/**
	 * Adds the score for transferring a straight to the foundation.
	 */
	public void addFoundationScore()
	{
		if (foundationTransfers < 8)
			foundationTransfers++;
	}

	/**
	 * Compares this player to the other player using the score. If the players
	 * have the same score, the first player to get the score will be first.
	 * 
	 * @param player the player to compare to.
	 * @return less than 0 if the other player has a better score, and greater
	 *         than 0 if the other player has the less score. If the other
	 *         player has the same score, less than 0 if the other player has a
	 *         higher number of suits, greater than 0 if the other player has a
	 *         lower number of suits, and the other seed - this seed if none of
	 *         the other conditions are met.
	 */
	@Override
	public int compareTo(Player other)
	{
		int diff = other.getFinalScore() - getFinalScore();
		if (diff != 0)
			return diff;
		int suitDiff = other.noOfSuits - noOfSuits;
		if (suitDiff != 0)
			return suitDiff;
		return other.seed - seed;
	}

	/**
	 * Calculates the score from transferring and flipping cards minus any
	 * penalties accrued.
	 * 
	 * @return the score without any bonuses.
	 */
	public int getBaseScore()
	{
		return foundationTransfers * FOUNDATION_SCORE[noOfSuits] + cardFlips
				* FLIP_SCORE;
	}

	/**
	 * Calculates the total score including all bonuses and penalties.
	 * 
	 * @return the final score.
	 */
	public int getFinalScore()
	{
		return getBaseScore() + getTotalBonus() + getTotalPenalty();
	}

	/**
	 * Gives the game seed of this player.
	 * 
	 * @return the game seed.
	 */
	public int getGameSeed()
	{
		return seed;
	}

	/**
	 * Calculates the score lost from using the hint system.
	 * 
	 * @return the score lost for using hints.
	 */
	public int getHintPenalty()
	{
		return hints * HINT_PENALTY[noOfSuits];
	}

	/**
	 * Calculates the bonus for using fewer moves.
	 * 
	 * @return the score for using less moves.
	 */
	public int getMoveBonus()
	{
		if (moves < MOVE_PAR)
			return (MOVE_PAR - moves) * MOVE_BONUS_MODIFIER;
		return 0;
	}

	/**
	 * Calculates the bonus score for not using any hints.
	 * 
	 * @return the bonus for not using hints.
	 */
	public int getNoHintBonus()
	{
		if (hints > 0)
			return 0;
		return NO_HINT_BONUS;
	}

	/**
	 * Calculates the bonus score for not using any undos.
	 * 
	 * @return the bonus for not undoing.
	 */
	public int getNoUndoBonus()
	{
		if (undos > 0)
			return 0;
		return NO_UNDO_BONUS;
	}

	/**
	 * Gives the name of the player of this game.
	 * 
	 * @return the name of the current player.
	 */
	public String getPlayerName()
	{
		return playerName;
	}

	/**
	 * Calculates the score bonus for completing the game.
	 * 
	 * @return the bonus score for completing the game.
	 */
	public int getScoreBonus()
	{
		if (foundationTransfers == 8)
			return (getBaseScore() + getTotalPenalty()) / 4;
		return 0;
	}

	/**
	 * Gives detailed statistics about the game.
	 * 
	 * @return the current game statistics.
	 */
	public String getStatistics()
	{
		StringBuilder builder = new StringBuilder();

		// General info
		builder.append(String.format("Player: %s%n", playerName));
		builder.append(String.format("Suits: %d%n", noOfSuits));
		builder.append(String.format("Game #%s%n%n", seed));
		builder.append(String.format("Base Score: %16d%n", getBaseScore()));
		builder.append(String.format("Total Moves: %15d%n", moves));
		builder.append(String.format("Total Undos: %15d%n", undos));
		builder.append(String.format("Total Hints: %15d%n%n", hints));

		// Bonuses
		builder.append(String.format("Bonuses: %n"));
		builder.append(String.format("Completion Bonus: %10d%n",
				getScoreBonus()));
		builder.append(String.format("Move Bonus: %16d%n", getMoveBonus()));
		builder.append(String.format("No Undo Bonus: %13d%n", getNoUndoBonus()));
		builder.append(String.format("No Hint Bonus: %13d%n", getNoHintBonus()));
		builder.append(String.format("Total Bonus: %15d%n%n", getTotalBonus()));

		// Penalties
		builder.append(String.format("Penalties: %n"));
		builder.append(String.format("Hints (x%4d): %10d%n", hints,
				getHintPenalty()));
		builder.append(String.format("Undos (x%4d): %10d%n", undos,
				getUndoPenalty()));
		builder.append(String.format("Total Penalty: %15d%n%n",
				getHintPenalty() + getUndoPenalty()));

		// Total
		builder.append(String.format("Total Score: %15d", getFinalScore()));
		return builder.toString();
	}

	/**
	 * Calculates the total bonus score.
	 * 
	 * @return the total score gained from bonuses.
	 */
	public int getTotalBonus()
	{
		return getNoHintBonus() + getNoUndoBonus() + getScoreBonus()
				+ getMoveBonus();
	}

	/**
	 * Calculates the total amount of points lost from penalties.
	 * 
	 * @return the total score lost from penalties.
	 */
	public int getTotalPenalty()
	{
		return getHintPenalty() + getUndoPenalty();
	}

	/**
	 * Calculates the penalty for undoing.
	 * 
	 * @return the score lost for undoing.
	 */
	private int getUndoPenalty()
	{
		return undos * UNDO_PENALTY;
	}

	/**
	 * Increments the number of moves.
	 */
	public void incrementMoves()
	{
		moves++;
	}

	/**
	 * Renames the player.
	 * 
	 * @param newName the new name of the player.
	 */
	public void rename(String newName)
	{
		playerName = newName;
	}

	/**
	 * Resets the score.
	 */
	public void resetScore()
	{
		moves = 0;
		undos = 0;
		foundationTransfers = 0;
		cardFlips = 0;
		hints = 0;
	}

	/**
	 * Gives a String representation of this player.
	 * 
	 * @return the player's name and score.
	 */
	@Override
	public String toString()
	{
		return playerName + ": " + getFinalScore();
	}

	/**
	 * Undoes a move.
	 */
	public void undo()
	{
		if (moves > 0) {
			undos++;
			moves--;
		}
	}

	/**
	 * Undoes a flip.
	 */
	public void undoFlip()
	{
		if (cardFlips > 0)
			cardFlips--;
	}

	/**
	 * Undoes a foundation transfer.
	 */
	public void undoFoundation()
	{
		if (foundationTransfers > 0)
			foundationTransfers--;
	}

	/**
	 * Checks whether the game state has changed from the start.
	 * 
	 * @return true if any moves/undoes/hints have been made, and false if no
	 *         actions have taken place.
	 */
	public boolean canReset()
	{
		return !(moves == 0 && undos == 0 && hints == 0);
	}

	/**
	 * Increments the number of hints used.
	 */
	public void useHint()
	{
		hints++;
	}
}
