package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public class King extends ChessPiece
{
	public King(int team, int x, int y)
	{
		super(team, ChessPiece.KING, ChessPiece.KINGVAL, x, y,
				"icon/" + (team == GamePiece.TEAMBLACK ? "black" : "white") + "king.png");
		setCanCastle(true);
	}
	@Override
	public boolean canMoveTo(int x, int y, ChessBoard board)
	{
		int relativeX = getxPos() - x;
		int relativeY = getyPos() - y;
		if(relativeX == 0 && relativeY == 0) return false;
		if(isCanCastle() && relativeX == 0 && relativeY == 2) {
			//Go to the left
			if(board.getPieceAt(getxPos(), getyPos() - 4).getClass() == Rook.class && board.getPieceAt(getxPos(), getyPos() - 4).isCanCastle()) {
				for(int i = 0; i < 3; i++) {
					if(!board.getPieceAt(getxPos(), getyPos()- 3 + i).equals(ChessBoard.nullPiece)) {
						return false;
					}
				}
			} else {
				return false;
			}
			setCastling(true);
			return true;
		}
		if(isCanCastle() && relativeX == 0 && relativeY == -2) {
			//Go to the right
			if(board.getPieceAt(getxPos(), getyPos() + 3).getClass() == Rook.class && board.getPieceAt(getxPos(), getyPos() + 3).isCanCastle()) {
				for(int i = 0; i < 2; i++) {
					if(!board.getPieceAt(getxPos(), getyPos() + 1 + i).equals(ChessBoard.nullPiece)) {
						return false;
					}
				}
			} else {
				return false;
			}
			setCastling(true);
			return true;
		}
		if(relativeX > 1 || relativeX < -1 || relativeY > 1 || relativeY < -1) return false;
		return true;
	}

}
