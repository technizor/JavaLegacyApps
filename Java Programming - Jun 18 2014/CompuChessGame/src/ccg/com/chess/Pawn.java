package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class Pawn extends ChessPiece
{
	public Pawn(int team, int x, int y)
	{
		super(team, ChessPiece.PAWN, ChessPiece.PAWNVAL, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "pawn.png");
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		int relativeX = getxPos() - x;
		int relativeY = getyPos() - y;
		int v = getTeamNum() == GamePiece.TEAMBLACK ? -1 : 1;
		if(relativeX == 0 && relativeY == 0) return false;
		if(relativeX < 0 && getTeamNum() == GamePiece.TEAMWHITE) return false;
		if(relativeX > 0 && getTeamNum() == GamePiece.TEAMBLACK) return false;
		if(!isVerticalOf(x, y) && !isDiagonalOf(x, y)) {
			return false; //Cannot move straight sideways
		}
		if((v == -1 && relativeX < -2) || (v == 1 && relativeX > 2)) return false;
		if(relativeY > 1 || relativeY < -1) {
			return false; 
		}
			//Pawns are unable to move more than 1 block towards either side and no more than 2 blocks forward
		if(relativeX == 2 * v) {
			if(getTeamNum() == GamePiece.TEAMWHITE && getxPos() != 6) return false;
			if(getTeamNum() == GamePiece.TEAMBLACK && getxPos() != 1) return false;
			if ((getxPos() == 1 && v < 0) || (getxPos() == 6 && v > 0) && isVerticalOf(x, y)) {
				//First move for the pawn can be a double move
				if(board.getPieceAt(x, y).equals(ChessBoard.nullPiece) && board.getPieceAt(x + v, y).equals(ChessBoard.nullPiece)) {
					setEnPassant(true);
					return true;
				}
				return false;
			}
		}
		if(relativeX == 1 * v && isDiagonalOf(x, y)) {
			if(!board.getPieceAt(x, y).equals(ChessBoard.nullPiece) && board.getPieceAt(x, y).getTeamNum() != this.getTeamNum()) {
				//Enemy piece in diagonal forward position
				return true;
			}
			GamePiece piece = board.getPieceAt(x + v, y);
			if(piece.getClass() == Pawn.class && piece.canBeEnPassant() && piece.getTeamNum() != getTeamNum()) {
				//En Passant in progress
				setPassing(true);
				return true;
			}
			return false;
		}
		if(relativeX == 1 * v && isVerticalOf(x, y)) {
			if(board.getPieceAt(x, y).equals(ChessBoard.nullPiece)) {
				//Forward position is unoccupied
				setEnPassant(false);
				return true;
			}
			return false;
		}
		return true;
	}

}
