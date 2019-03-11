package ccg.com;

import java.util.ArrayList;

import ccg.com.chess.Bishop;
import ccg.com.chess.ChessPiece;
import ccg.com.chess.King;
import ccg.com.chess.Knight;
import ccg.com.chess.Pawn;
import ccg.com.chess.Queen;
import ccg.com.chess.Rook;

public class ChessBoard
{
	private ArrayList<ArrayList<GamePiece>> board;
	private int gameMode = GamePiece.CHESSMODE; //0 = Chess, 1 = Checkers
	private int blackScore = 0;
	private int whiteScore = 0;
	private int playerTurn = GamePiece.TEAMWHITE;
	public static final GamePiece nullPiece = new GamePiece()
	{
		public boolean canMoveTo(int x, int y, ChessBoard board) {return false;}
		public  void move(int x, int y, ChessBoard board) {}
		public boolean check(ChessBoard board) {return false;}
	};
	
	public ChessBoard()
	{
		gameMode = GamePiece.CHESSMODE;
		board = new ArrayList<ArrayList<GamePiece>>();
		for(int i = 0; i < 8; i++) {
			board.add(new ArrayList<GamePiece>());
			for(int j = 0; j < 8; j++) {
				board.get(i).add(nullPiece);
			}
		}
		if(gameMode == GamePiece.CHESSMODE) {
			setChessBoard();
		}
	}
	public ChessBoard(ArrayList<ArrayList<GamePiece>> tempBoard)
	{
		gameMode = GamePiece.CHESSMODE;
		board = new ArrayList<ArrayList<GamePiece>>();
		for(int i = 0; i < 8; i++) {
			board.add(new ArrayList<GamePiece>());
			for(int j = 0; j < 8; j++) {
				board.get(i).add(tempBoard.get(i).get(j));
			}
		}
	}
	public void setChessBoard()
	{
		for(int i = 0; i < 8; i++) {
			board.get(1).set(i, new Pawn(GamePiece.TEAMBLACK, 1, i));
			board.get(6).set(i, new Pawn(GamePiece.TEAMWHITE, 6, i));
		}
		board.get(0).set(0, new Rook(GamePiece.TEAMBLACK, 0, 0));
		board.get(0).set(7, new Rook(GamePiece.TEAMBLACK, 0, 7));
		board.get(7).set(0, new Rook(GamePiece.TEAMWHITE, 7, 0));
		board.get(7).set(7, new Rook(GamePiece.TEAMWHITE, 7, 7));
		board.get(0).set(1, new Knight(GamePiece.TEAMBLACK, 0, 1));
		board.get(0).set(6, new Knight(GamePiece.TEAMBLACK, 0, 6));
		board.get(7).set(1, new Knight(GamePiece.TEAMWHITE, 7, 1));
		board.get(7).set(6, new Knight(GamePiece.TEAMWHITE, 7, 6));
		board.get(0).set(2, new Bishop(GamePiece.TEAMBLACK, 0, 2));
		board.get(0).set(5, new Bishop(GamePiece.TEAMBLACK, 0, 5));
		board.get(7).set(2, new Bishop(GamePiece.TEAMWHITE, 7, 2));
		board.get(7).set(5, new Bishop(GamePiece.TEAMWHITE, 7, 5));
		board.get(0).set(3, new Queen(GamePiece.TEAMBLACK, 0, 3));
		board.get(0).set(4, new King(GamePiece.TEAMBLACK, 0, 4));
		board.get(7).set(3, new Queen(GamePiece.TEAMWHITE, 7, 3));
		board.get(7).set(4, new King(GamePiece.TEAMWHITE, 7, 4));
	}
	public GamePiece getPieceAt(int x, int y)
	{
		return board.get(x).get(y);
	}
	public void set(GamePiece piece, int x, int y)
	{
		board.get(x).set(y, nullPiece);
		int temp = board.get(piece.getxPos()).get(piece.getyPos()).getTeamNum();
		int score = board.get(piece.getxPos()).get(piece.getyPos()).getValue();
		if(temp == GamePiece.TEAMWHITE) blackScore += score;
		else whiteScore += score;
		board.get(piece.getxPos()).set(piece.getyPos(), piece);
	}
	private void resetEnPassant(int x, int y)
	{
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i != x && j != y) {
					GamePiece piece = board.get(i).get(j);
					piece.setEnPassant(false);
					board.get(i).set(j, piece);
				}
			}
		}
	}
	public int getBlackScore()
	{
		return blackScore;
	}
	public int getWhiteScore()
	{
		return whiteScore;
	}
	public String printBoard()
	{
		String output = "";
		for(ArrayList<GamePiece> array : board) {
			for(GamePiece piece : array) {
				String temp = "";
				switch(piece.getPieceType()) {
				case ChessPiece.BISHOP:
					temp = "B";
					break;
				case ChessPiece.KNIGHT:
					temp = "N";
					break;
				case ChessPiece.PAWN:
					temp = "P";
					break;
				case ChessPiece.ROOK:
					temp = "R";
					break;
				case ChessPiece.QUEEN:
					temp = "Q";
					break;
				case ChessPiece.KING:
					temp = "K";
					break;
				default:
					temp = " ";
					break;
				}
				if(piece.getTeamNum() == GamePiece.TEAMBLACK) output += temp.toLowerCase();
				else output += temp;
			}
			output += "\n";
		}
		output += "--------";
		return output;
	}
	public void move(int startX, int startY, int targetX, int targetY)
	{
		GamePiece piece = board.get(startX).get(startY);
		int v = piece.getTeamNum() == GamePiece.TEAMBLACK ? -1 : 1;
		if(piece.canMoveTo(targetX, targetY, this)) {
			piece.move(targetX, targetY, this);
			if(piece.isPassing()) {
				board.get(targetX + v).set(targetY, nullPiece);
			}
			resetEnPassant(targetX, targetY);
			if(playerTurn == GamePiece.TEAMWHITE) playerTurn = GamePiece.TEAMBLACK;
			else playerTurn = GamePiece.TEAMWHITE;
		}
	}
}
