import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A grid of letters that can look for a given word within the grid of letters
 * for either a word search or boggle search.
 * 
 * @author Sherman Ying
 * @version March 19, 2013
 */
public class LetterGrid
{
	private char[][] grid;

	/**
	 * Constructs a new LetterGrid using data from the given file.
	 * 
	 * @param fileName the file to read data from
	 */
	public LetterGrid(String fileName) {
		// Attempt to read the given file to set the grid of letters
		try {
			Scanner input = new Scanner(new File(fileName));
			ArrayList<String> rowList = new ArrayList<String>();
			while (input.hasNextLine())
				rowList.add(input.nextLine());
			input.close();

			// Set the number of rows in the grid, and add the rows
			int totalRows = rowList.size();
			grid = new char[totalRows][];
			for (int row = 0; row < totalRows; row++)
				grid[row] = rowList.get(row).toCharArray();
		}

		// When encountering a problem with the file input,
		// set the word grid to an error message
		catch (IOException ioe) {
			grid = new char[][] { { 'B', 'A', 'D' }, { 'F', 'I', 'L', 'E' },
					{ 'N', 'A', 'M', 'E' } };
		}
	}

	/**
	 * Helps the wordSearch method find a word in the given direction.
	 * 
	 * @param word the original word to search for
	 * @param row the row of the first letter
	 * @param col the column of the first letter
	 * @param deltaR the change in row after every letter check
	 * @param deltaC the change in column after every letter check
	 * @return whether the word is found at this location
	 */
	private boolean searchWordAt(String word, int row, int col, int deltaR,
			int deltaC)
	{
		// Store the grid and word size for reference
		int wordLength = word.length();
		int gridHeight = grid.length;
		int gridWidth = grid[0].length;

		// Store the letter position in the word and the grid positions
		int wordPos = 1;
		int curR = row + deltaR;
		int curC = col + deltaC;

		// Keep checking letters while you are still on the grid
		while (curR < gridHeight && curR >= 0 && curC < gridWidth
				&& wordPos < wordLength && curC >= 0
				&& grid[curR][curC] == word.charAt(wordPos)) {
			wordPos++;
			curR += deltaR;
			curC += deltaC;
		}

		// Return true if all letters were found, and false if not
		return wordPos == wordLength;
	}

	/**
	 * Searches for the given word in this LetterGrid
	 * 
	 * @param word the word or phrase to search the grid for
	 * @return true if the word or phrase was found, and false if not found
	 */
	public boolean wordSearch(String word)
	{
		// Store the word and grid size for reference
		int gridHeight = grid.length;
		int gridWidth = grid[0].length;
		int wordLength = word.length();

		// Ensure that the word will fit on the grid and is valid
		if (wordLength == 0)
			return false;
		if (wordLength > gridHeight && wordLength > gridWidth)
			return false;

		// Store the change values for the 8 directions to check
		int[][] deltaValues = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 },
				{ -1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 } };

		// Store the first letter for reference and search
		char firstCh = word.charAt(0);
		for (int row = 0; row < gridHeight; row++) {
			for (int col = 0; col < gridWidth; col++) {
				// If the first letter is found, check for the
				// word at this location
				if (grid[row][col] == firstCh)
					for (int[] dirValues : deltaValues)
						if (searchWordAt(word, row, col, dirValues[0],
								dirValues[1]))
							return true;
			}
		}

		// If the word has not been found on the grid
		return false;
	}

	/**
	 * Gives the grid of letters arranged in rows.
	 * 
	 * @return a visual representation of the grid of letters
	 */
	public String toString()
	{
		// Build the String output that is the size of the grid
		StringBuilder output = new StringBuilder(grid.length
				* (grid[0].length + 1));

		// Append every letter in the grid in row format
		for (char[] row : grid) {
			for (char ch : row)
				output.append(ch);
			output.append('\n');
		}
		return output.toString();
	}

	/**
	 * Searches the letter grid with a given list of words, and gives a list of
	 * words found
	 * 
	 * @param wordList the full list of words to search for
	 * @return a list of words that were found in the grid
	 */
	public ArrayList<String> wordsOnGrid(ArrayList<String> wordList)
	{
		ArrayList<String> wordsFound = new ArrayList<String>(wordList.size());

		// Search for all the words in the list and add the ones found
		for (String word : wordList)
			if (wordSearch(word))
				wordsFound.add(word);
		return wordsFound;
	}

	/**
	 * Searches for the given word in the letter grid using Boggle search rules.
	 * 
	 * @param word the word to search for in the grid
	 * @return true if the word is contained in the grid, and false if not
	 */
	public boolean boggleSearch(String word)
	{
		// Start searching for the word at each letter, until it has been found,
		// or the end of the grid is reached
		char first = word.charAt(0);

		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[0].length; col++)
				if (first == grid[row][col] && boggleSearchAt(row, col, word))
					return true;
		return false;
	}

	/**
	 * Helps the boggleSearch method find the given word.
	 * 
	 * @param row the row of the letter being checked
	 * @param col the column of the letter being checked
	 * @param word the word being searched for
	 * @return true if all of the letters after this letter were found, false if
	 *         not found
	 */
	private boolean boggleSearchAt(int row, int col, String word)
	{
		// Check for being out of bounds and letter equality
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != word.charAt(0))
			return false;

		// Check whether the search is finished
		if (word.length() == 1)
			return true;

		// Change the case of the current letter to prevent looping, then search
		// for the remaining word in all directions
		grid[row][col] = Character.toLowerCase(grid[row][col]);
		String remainingWord = word.substring(1);

		// If the word is found by branching off in any direction, return true
		boolean foundWord = false;

		// Determine which columns to check
		boolean canGoLeft = col > 0;
		boolean canGoRight = col < grid[0].length - 1;
		
		// Check the rows above
		if (row > 0) {
			foundWord |= boggleSearchAt(row - 1, col, remainingWord);
			foundWord |= canGoLeft
					&& boggleSearchAt(row - 1, col - 1, remainingWord);
			foundWord |= canGoRight
					&& boggleSearchAt(row - 1, col + 1, remainingWord);
		}
		
		// Check left and right
		foundWord |= canGoLeft && boggleSearchAt(row, col - 1, remainingWord);
		foundWord |= canGoRight && boggleSearchAt(row, col + 1, remainingWord);

		// Check the row below
		if (row < grid.length - 1) {
			foundWord |= boggleSearchAt(row + 1, col, remainingWord);
			foundWord |= col > 0
					&& boggleSearchAt(row + 1, col - 1, remainingWord);
			foundWord |= canGoRight
					&& boggleSearchAt(row + 1, col + 1, remainingWord);
		}

		// Reset the case of the current letter for future searching
		grid[row][col] = Character.toUpperCase(grid[row][col]);
		return foundWord;
	}

	/**
	 * Calculates the Boggle score for the word in the given list that are on
	 * this grid.
	 * 
	 * @param wordList the list of words to total the boggle score of
	 * @return the total boggle score for the list of words
	 */
	public int boggleScore(List<String> wordList)
	{
		int totalScore = 0;
		int boggleSize = grid.length;
		
		// Boggle scores are based on length of the found word
		// 4x4 boards are 3,4=1pt, 5 = 2pts, 6 = 3pts, 7 = 5pts, 8+ =11pts
		// 5x5 omits 3 = 1pt
		int[] fourByFourScores = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };
		int[] fiveByFiveScores = { 0, 0, 0, 0, 1, 2, 3, 5, 11 };

		for (String word : wordList) {
			if (boggleSearch(word)) {
				// Calculate the scoring position for the word's length
				// Cap the length at 8 because the scoring is the same above 8
				int scorePos = word.length() < 8 ? word.length() : 8;

				// Add the correct score for the board size
				if (boggleSize == 4)
					totalScore += fourByFourScores[scorePos];
				else
					totalScore += fiveByFiveScores[scorePos];
			}
		}
		return totalScore;
	}
}
