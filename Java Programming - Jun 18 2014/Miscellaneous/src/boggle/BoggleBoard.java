package boggle;
/**
 * A grid of letters that can be used to search for specific words
 * @author Sherman Ying
 * @version October 14, 2012
 */
public class BoggleBoard
{
	/**
	 * The grid of letters that make up the board
	 */
	private char[][] boggleBoard;
	
	/**
	 * Creates a Boggle Board that has searching capabilities
	 * @param board a 2D grid of letters to search within
	 */
	public BoggleBoard(char[][] board)
	{
		setBoard(board);
	}
	
	/**
	 * Sets the boggle board letters
	 * @param board the 2D grid of letters to search within
	 */
	public void setBoard(char[][] board)
	{
		boggleBoard = board;
	}
	
	/**
	 * Returns the boggle board of letters
	 * @return the 2D grid of letters
	 */
	public char[][] getBoard()
	{
		return boggleBoard;
	}
	
	/**
	 * Intializes the Boggle Search for a given word
	 * @param word the word to search for in the grid
	 */
	public void boggleSearch(String word)
	{
		word = word.toUpperCase();
		for(int i = 0; i < boggleBoard.length; i ++) {
			for(int j = 0; j <boggleBoard[i].length; j++) {
				if(boggleBoard[i][j] == Character.toUpperCase(word.charAt(0))) {
					boolean foundWord = findNextLetter(word, 1, i, j);
					boggleBoard[i][j] = Character.toUpperCase(boggleBoard[i][j]);
					if(foundWord) {
						System.out.println("Found the word: " + word + ".");
						return;
					}
				}
			}
		}
		System.out.println("Didn't find the word: " + word + ".");
	}
	
	/**
	 * Finds the next letter of the string on the grid until it reaches the end, or cannot find it.
	 * @param word the word to search for in the grid
	 * @param index the letter to look for next
	 * @param xPos horizontal position of the last letter
	 * @param yPos vertical postion of the last letter
	 * @return whether it found the end of the word
	 */
	private boolean findNextLetter(String word, int index, int xPos, int yPos)
	{
		// Found the end of the word
		if(index == word.length()) 
			return true;
		// If not, set this char to lower to prevent letter duplication
		boggleBoard[xPos][yPos] = Character.toLowerCase(boggleBoard[xPos][yPos]);
		// Check the 3x3 box around the position
		for(int i = xPos - 1; i <= xPos + 1; i++) {
			for(int j = yPos - 1; j<= yPos + 1; j++) {
				if(i >= 0 && i < boggleBoard.length && j >= 0 && j < boggleBoard[i].length) {
					if(boggleBoard[i][j] == Character.toUpperCase(word.charAt(index))) {
						//	If successful in finding the end, return true to exit out
						//	and reset the characters to uppercase for use again
						boolean foundEnd = findNextLetter(word, index+1, i, j);
						boggleBoard[i][j]= Character.toUpperCase(boggleBoard[i][j]);
						if(foundEnd)
							return true;
					}
				}
			}
		}
		// Couldn't find the next letter
		return false;
	}
	
	/**
	 * Prints the state of the board for debugging purposes
	 */
	public void printBoard()
	{
		for(int i = 0; i < boggleBoard.length; i++) {
			for(int j = 0; j < boggleBoard[i].length; j++)
			{
				System.out.print(boggleBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----");
	}
}
