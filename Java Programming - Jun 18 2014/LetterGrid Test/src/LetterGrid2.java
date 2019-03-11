import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Holds a grid of letters
 * @author Austin Tripp
 * @version March 19th 2013
 */
public class LetterGrid2
{

	// Holds the grid of letters
	private char [][] grid;
	private int noOfLetters;
	private int longestRowLength;

	/**
	 * Constructs a LetterGrid using the data in the given file
	 * @param fileName the name of the file with the LetterGrid data
	 */
	public LetterGrid2 (String fileName)
	{
		try
		{
			Scanner inFile = new Scanner(new File(fileName));

			// Read in all the lines in the file
			ArrayList<String> linesInFile = new ArrayList<String>();
			while (inFile.hasNextLine())
				linesInFile.add(inFile.nextLine());
			inFile.close();

			//Ensure that the file was not empty
			if (linesInFile.size() == 0){
				grid = new char [] [] {{'E', 'M', 'P', 'T', 'Y'}};
				noOfLetters = 5;
				longestRowLength = 5;
			}

			// Transfer the contents of these lines to the character grid
			grid = new char[linesInFile.size()][];
			noOfLetters = 0;
			longestRowLength = 0;
			for (int lineNo = 0; lineNo < grid.length; lineNo++)
			{
				grid[lineNo] = linesInFile.get(lineNo).toCharArray();
				noOfLetters += grid[lineNo].length;
				if (longestRowLength < grid[lineNo].length)
					longestRowLength = grid[lineNo].length;
			}
		}
		catch (IOException exp)
		{
			grid = new char[][] { { 'B', 'A', 'D' }, { 'F', 'I', 'L', 'E' },
					{ 'N', 'A', 'M', 'E' } };
			noOfLetters = 11;
			longestRowLength = 4;
		}
	}

	/**Checks to see if a single letter is on a grid
	 * @param letter the letter to search for
	 * @return true if that letter is on the grid, otherwise false
	 */
	private boolean isLetterOnGrid(char letter)
	{
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
				if (grid[row][col] == letter)
					return true;
		return false;
	}

	/**Checks to see if a given word is on the grid
	 * @param word the word to search for
	 * @return true if the word is on the grid, false if it is not
	 */
	public boolean wordSearch(String word)
	{
		// Ensure the word has letters
		if (word.length() == 0)
			return false;

		//Check to see if it is a one letter word
		if (word.length() == 1)
			return isLetterOnGrid(word.charAt(0));

		// Return false if the word cannot fit on the grid
		if (word.length() > grid.length && word.length() > longestRowLength)
			return false;

		// Keep track of the first letter in the word
		char first = word.charAt(0);

		// Store an array holding the change in position for the 8 directions
		int [][] posChanges = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },
				{ 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		// Search for that letter in the grid
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
				if (grid[row][col] == first)
				{

					// Search in all 8 directions for the word
					for (int dir = 0; dir < posChanges.length; dir++)
					{
						int searchRow = row + posChanges[dir][0];
						int searchCol = col + posChanges[dir][1];
						int currentLetter = 1;// Start searching for the second
						// letter
						while (searchRow >= 0 && searchRow < grid.length
								&& searchCol >= 0
								&& searchCol < grid[searchRow].length
								&& currentLetter < word.length())
						{

							// Check to see if it is the current letter
							if (grid[searchRow][searchCol] == 
									word.charAt(currentLetter))
							{
								currentLetter++;

								// Return true if the whole word has been found
								if (currentLetter == word.length())
									return true;

								// Increment to look at the last letter
								else
								{
									searchRow += posChanges[dir][0];
									searchCol += posChanges[dir][1];
								}
							}
							else
							{
								// Make it quit the loop by giving the positions
								// a high value
								searchRow = Integer.MAX_VALUE;
								searchCol = Integer.MAX_VALUE;
							}
						}
					}
				}

		// If not found then return false
		return false;
	}

	/**Checks to see if a word is on a grid using boggle rules
	 * @param word the word to search for on the grid
	 * @return true if the word is on the grid, false if it is not
	 */
	public boolean boggleSearch(String word){

		//Check if the word is blank
		if (word.length() == 0)
			return false;

		//Check to make sure this word could fit in the grid
		if (word.length() > noOfLetters)
			return false;

		//Search recursively for each letter
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
			{
				if (searchAround(word, 0, row, col))
					return true;
			}

		//Otherwise return false
		return false;
	}

	/**Searches recursively for a word in boggle
	 * @param word the word to search for
	 * @param pos the position in the word of the letter one is currently searching for
	 * @param row the row to search from
	 * @param col the column to search from
	 * @return true if the word is found, false if it is not
	 */
	private boolean searchAround(String word, int pos, int row, int col)
	{

		//Check to see if all the letters have been found, 
		//in which case the word is on the grid
		if (pos == word.length())
			return true;

		//Check to make sure these coordinates are within the bounds of the array
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length)
			return false;

		//If the letters do not match return false
		if (word.charAt(pos) != grid[row][col])
			return false;

		//Store all 8 directions
		int [][] posChanges = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },
				{ 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		//Mark this space as visited
		char originalChar = grid[row][col];
		grid[row][col] = Character.MAX_VALUE;

		//Check to see if the rest of the word lies in one of the 8 directions
		for (int dir = 0; dir < posChanges.length; dir++)
		{
			if (searchAround(word, pos+1, row + posChanges[dir][0], col + posChanges[dir][1]))
			{
				grid[row][col] = originalChar;
				return true;
			}
		}

		//If the word is not found mark this space as unvisited and return false
		grid[row][col] = originalChar;
		return false;
	}

	/**
	 * Finds all of the words in a list that are on this grid
	 * @param wordList the list of words to search for on the grid
	 * @return a list containing all of the given words that
	 *         are on the grid
	 */
	public List<String> wordsOnGrid(List<String> wordList)
	{
		//Store the words that are on the grid
		List<String> wordsOnGrid = new ArrayList<String>();

		//Go through each word and see if it is on the grid
		for (String word : wordList)
		{
			if (wordSearch(word))
				wordsOnGrid.add(word);
		}
		return wordsOnGrid;
	}

	/**Finds the boggle score of this LetterGrid
	 * @param wordList the list of possible words
	 * @return the sum of the score of every word on this grid
	 */
	public int boggleScore(List <String> wordList)
	{

		//Keep track of the score
		int score = 0;

		//Set up the score lookup table
		int [] scoreTable = new int [noOfLetters];
		for (int i = 0; i < noOfLetters; i++)
			scoreTable[i] = 11;
		int [] nonMaxScores = {0,0,0,1,1,2,3,5};
		System.arraycopy(nonMaxScores, 0, scoreTable, 0, noOfLetters > nonMaxScores.length ? 
				nonMaxScores.length : noOfLetters);
		if (longestRowLength > 4)
			scoreTable[3] = 0;

		//Search for every word
		for (String word : wordList)
		{

			//Check to see whether to bother searching for the word
			if (word.length() < noOfLetters)
				if (scoreTable[word.length()] > 0)	
					if (boggleSearch(word))
						score += scoreTable[word.length()];
		}
		return score;
	}

	// ----------------------------------------------------
	// Overloaded methods from the object class

	/**
	 * Creates a String representation of this grid
	 * @return a String containing the grid
	 */
	public String toString()
	{
		// Create a builder to create the String
		StringBuilder str = new StringBuilder();

		// Add each line to the String
		for (int line = 0; line < grid.length; line++)
		{
			for (int pos = 0; pos < grid[line].length; pos++)
				str.append(grid[line][pos]);
			str.append('\n');
		}
		return str.toString();
	}
}
