package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class Knight extends ChessPiece
{
	public Knight(int team, int x, int y)
	{
		super(team, ChessPiece.KNIGHT, ChessPiece.KNIGHTVAL, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "knight.png");
	}
	public Knight(int team, int x, int y, int value)
	{
		super(team, ChessPiece.KNIGHT, value, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "knight.png");
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		int relativeX = getxPos() - x;
		int relativeY = getyPos() - y;
		if(relativeX == 0 && relativeY == 0) return false;
		if((relativeX == 1 || relativeX == -1) && (relativeY == 2 || relativeY == -2)) return true;
		if((relativeX == 2 || relativeX == -2) && (relativeY == 1 || relativeY == -1)) return true;
		return false;
	}

}
