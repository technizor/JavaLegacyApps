package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class Bishop extends ChessPiece
{
	public Bishop(int team, int x, int y)
	{
		super(team, ChessPiece.BISHOP, ChessPiece.BISHOPVAL, x, y, 
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "bishop.png");
	}
	public Bishop(int team, int x, int y, int value)
	{
		super(team, ChessPiece.BISHOP, value, x, y, 
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "bishop.png");
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		int relativeX = getxPos() - x;
		int relativeY = getyPos() - y;
		if(relativeX == 0 && relativeY == 0) {
			return false;
		}
		if(!isDiagonalOf(x, y)) {
			return false;
		}
		int xDir = relativeX > 0 ? -1 : 1;
		int yDir = relativeY > 0 ? -1 : 1;
		int i = getxPos() + xDir;
		int j = getyPos() + yDir;
		for(;i < 8 && i > -1 && j < 8 && j > -1 && i != x; i += xDir, j += yDir) {
			if((!board.getPieceAt(i, j).equals(ChessBoard.nullPiece) && i != x + xDir) || board.getPieceAt(i, j).getTeamNum() == getTeamNum()) {
				return false;
			}
		}
		return true;
	}
}
