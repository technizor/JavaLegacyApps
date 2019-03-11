package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class Rook extends ChessPiece
{
	public Rook(int team, int x, int y)
	{
		super(team, ChessPiece.ROOK, ChessPiece.ROOKVAL, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "rook.png");
		setCanCastle(true);
	}
	public Rook(int team, int x, int y, int value) // Pawn
	{
		super(team, ChessPiece.ROOK, value, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "rook.png");
		setCanCastle(false);
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		if(x - getxPos() == 0 && y - getyPos() == 0) return false;
		if(!isHorizontalVerticalOf(x, y)) {
			return false;
		}
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
	}

}
