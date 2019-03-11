package tttduel;
public class Board implements Comparable<Board>
{
	private byte[][] grid;
	private byte[] moves;
	private int turn;
	private boolean p1started;

	public Board(int round)
	{
		grid = new byte[3][3];
		moves = new byte[9];
		turn = 0;
		p1started = round % 2 == 0;
	}

	public boolean makeMove(int pos) throws IllegalArgumentException
	{
		if (pos < 0 || pos >= 9)
			throw new IllegalArgumentException();
		if (grid[pos / 3][pos % 3] != 0)
			return false;

		grid[pos / 3][pos % 3] = (byte) (turn % 2 == 0 ? 1 : 2);
		moves[turn] = (byte) pos;
		turn++;
		return true;
	}

	public String toString()
	{
		String text = p1started ? " XO" : " OX";
		return String.format("%s%s%s%s%s%s%s%s%s", text.charAt(grid[0][0]),
				text.charAt(grid[0][1]), text.charAt(grid[0][2]),
				text.charAt(grid[1][0]), text.charAt(grid[1][1]),
				text.charAt(grid[1][2]), text.charAt(grid[2][0]),
				text.charAt(grid[2][1]), text.charAt(grid[2][2]));
	}

	public String getMoves()
	{
		return String.format("%d%d%d%d%d%d%d%d", moves[0], moves[1], moves[2],
				moves[3], moves[4], moves[5], moves[6], moves[7], moves[8]);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof Board))
			return false;
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				if (grid[r][c] != ((Board) other).grid[r][c])
					return false;
		return true;
	}

	public int hashCode()
	{
		int hashCode = 0;
		for (int exp = 0, mult = 1; exp < 9; exp++, mult *= 3)
			hashCode += mult * grid[(8 - exp) / 3][(8 - exp) % 3];
		return hashCode;
	}

	public int compareTo(Board other)
	{
		for (int r = 2; r >= 0; r--)
			for (int c = 2; c >= 0; c--)
				if (grid[r][c] != other.grid[r][c])
					return grid[r][c] - other.grid[r][c];
		return 0;
	}

	public int currentPlayer()
	{
		return ((p1started == (turn % 2 == 0)) ? 1 : 2);
	}

	public int turnsPassed()
	{
		return turn;
	}

	public boolean isWinState()
	{
		// Row/Column
		for (int i = 0; i < 3; i++) {
			if (grid[0][i] != 0 && grid[0][i] == grid[1][i]
					&& grid[0][i] == grid[2][i])
				return true;
			if (grid[i][0] != 0 && grid[i][0] == grid[i][1]
					&& grid[i][0] == grid[i][2])
				return true;
		}
		// Diagonals
		if (grid[1][1] != 0 && grid[1][1] == grid[0][0]
				&& grid[1][1] == grid[2][2])
			return true;
		if (grid[1][1] != 0 && grid[1][1] == grid[2][0]
				&& grid[1][1] == grid[0][2])
			return true;
		return false;
	}
}
