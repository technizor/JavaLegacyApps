package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class Queen extends ChessPiece
{
	public Queen(int team, int x, int y)
	{
		super(team, ChessPiece.QUEEN, ChessPiece.QUEENVAL, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "queen.png");
	}
	public Queen(int team, int x, int y, int value)
	{
		super(team, ChessPiece.QUEEN, value, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "queen.png");
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		if(x - getxPos() == 0 && y - getyPos() == 0) return false;
		if(isHorizontalVerticalOf(x, y)) {
			if(isVerticalOf(x, y)) {
				int dir = getxPos() > x ? -1 : 1;
				int i = getxPos() + dir;
				for(;i < 8 && i > -1 && i != x; i += dir) {
					if((!board.getPieceAt(i, y).equals(ChessBoard.nullPiece) && i != x + dir) || board.getPieceAt(i, y).getTeamNum() == getTeamNum()) {
						return false;
					}
				}
			} else if (isHorizontalOf(x, y)) {
				int dir = getyPos() > y ? -1 : 1;
				int i = getyPos() + dir;
				for(;i < 8 && i > -1 && i != y; i += dir) {
					if((!board.getPieceAt(x, i).equals(ChessBoard.nullPiece) && i != y + dir) || board.getPieceAt(x, i).getTeamNum() == getTeamNum()) {
						return false;
					}
				}
			}
			return true;
		} else if(isDiagonalOf(x, y)){
			int xDir = getxPos() > x ? -1 : 1;
			int yDir = getyPos() > y ? -1 : 1;
			int i = getxPos() + xDir;
			int j = getyPos() + yDir;
			for(;i < 8 && i > -1 && j < 8 && j > -1 && i != x; i += xDir, j += yDir) {
				if((!board.getPieceAt(i, j).equals(ChessBoard.nullPiece) && i != x + xDir) || board.getPieceAt(i, j).getTeamNum() == getTeamNum()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
