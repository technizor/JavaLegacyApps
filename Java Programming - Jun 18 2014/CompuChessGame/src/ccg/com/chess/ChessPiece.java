package ccg.com.chess;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;

public abstract class ChessPiece extends GamePiece
{
	public static final int PAWN = 1001;
	public static final int KNIGHT = 1002;
	public static final int BISHOP = 1003;
	public static final int ROOK = 1004;
	public static final int QUEEN = 1005;
	public static final int KING = 1006;
	public static final int PAWNVAL = 1;
	public static final int KNIGHTVAL = 3;
	public static final int BISHOPVAL = 3;
	public static final int ROOKVAL = 5;
	public static final int QUEENVAL = 9;
	public static final int KINGVAL = 0;
	
	public ChessPiece()
	{
		super();
		setGameMode(GamePiece.CHESSMODE);
	}
	public ChessPiece(int team, int piece, int pieceVal, int x, int y, String iconPath)
	{
		super(team, piece, x, y, iconPath);
		setValue(pieceVal);
		setGameMode(GamePiece.CHESSMODE);
	}
	public void move(int x, int y, ChessBoard board)
	{
		int tempX = getxPos();
		int tempY = getyPos();
		setxPos(x);
		setyPos(y);
		if(isCastling()) {
			if(tempY > y) {
				//Castle to left
				GamePiece piece = board.getPieceAt(tempX, tempY - 4);
				piece.castle(true);
				board.set(piece, tempX, 0);
			} else {
				//Castle to right
				GamePiece piece = board.getPieceAt(tempX, tempY + 3);
				piece.castle(false);
				board.set(piece, tempX, 7);
			}
		}
		setCanCastle(false);
		board.set(this, tempX, tempY);
	}
	public boolean check(ChessBoard board)
	{
		int kingX = -1;
		int kingY = -1;
		OUT:
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board.getPieceAt(i, j).getTeamNum() == getTeamNum() && board.getPieceAt(i, j).getPieceType() == KING) {
					kingX = i;
					kingY = j;
					break OUT;
				}
			}
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board.getPieceAt(i, j).getTeamNum() != getTeamNum()) {
					if(board.getPieceAt(i, j).canMoveTo(kingX, kingY, board)) return true;
				}
			}
		}
		return false;
	}
}
