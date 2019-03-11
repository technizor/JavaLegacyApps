package ccg.gui;

import org.jetstorm.stormFrame.StormConstraints;
import org.jetstorm.stormFrame.StormFrame;

import ccg.com.ChessBoard;
import ccg.com.GamePiece;
import ccg.com.chess.Bishop;
import ccg.com.chess.Knight;
import ccg.com.chess.Pawn;
import ccg.com.chess.Queen;
import ccg.com.chess.Rook;

public class AppWindow extends StormFrame
{
	private AppInterface panel;
	private CoOrdinate piecePos;
	private ChessBoard dataBoard;
	private int playerTurn;
	private PawnToPiece pawnDialog;
	
	private static final long serialVersionUID = 7762334487849921518L;

	public AppWindow()
	{
		super("CompuChess Game", false, false);
		refreshButtons();
		setEnabledButtons(ChessBoard.nullPiece);
		setContainerTitle("White Player's Turn - W: " + dataBoard.getWhiteScore() + " B: " + dataBoard.getBlackScore());
		refreshWindow();
	}

	@Override
	public void actionHandler(Object source)
	{
		boolean gridButtonAction = false;
		int x = 0;
		int y = 0;
		OUTER: for (int i = 0; i < 8; i++) {
			for (int c = 0; c < 8; c++) {
				if (source.equals(panel.boardButtons[i][c])) {
					gridButtonAction = true;
					x = i;
					y = c;
					break OUTER;
				}
			}
		}
		if(gridButtonAction) {
			if(piecePos == null) {
				if(dataBoard.getPieceAt(x, y) != ChessBoard.nullPiece && 
						dataBoard.getPieceAt(x, y) != ChessBoard.nullPiece && 
						dataBoard.getPieceAt(x, y).getTeamNum() == playerTurn) {
					piecePos = new CoOrdinate(x, y);
					setEnabledButtons(dataBoard.getPieceAt(x, y));
				}
			} else if(x == piecePos.getX() && y == piecePos.getY()) {
				piecePos = null;
				setEnabledButtons(ChessBoard.nullPiece);
			} else {
				if(dataBoard.getPieceAt(x, y).getTeamNum() == dataBoard.getPieceAt(piecePos.getX(), piecePos.getY()).getTeamNum()) {
					piecePos = new CoOrdinate(x, y);
					setEnabledButtons(dataBoard.getPieceAt(x, y));
				} else if(dataBoard.getPieceAt(piecePos.getX(), piecePos.getY()).canMoveTo(x, y, dataBoard)) {
					dataBoard.move(piecePos.getX(), piecePos.getY(), x, y);
					if(dataBoard.getPieceAt(x, y).getClass() == Pawn.class && 
							(x == 0 && dataBoard.getPieceAt(x, y).getTeamNum() == GamePiece.TEAMWHITE) ||
							(x == 7 && dataBoard.getPieceAt(x, y).getTeamNum() == GamePiece.TEAMBLACK)) {
						pawnDialog = new PawnToPiece(dataBoard.getPieceAt(x, y).getTeamNum(), this, x, y);
						this.setEnabled(false);
					}
					piecePos = null;
					if(playerTurn == GamePiece.TEAMWHITE) {
						playerTurn = GamePiece.TEAMBLACK;
						setContainerTitle("Black Player's Turn - W: " + dataBoard.getWhiteScore() + 
								" B: " + dataBoard.getBlackScore());
					} else {
						playerTurn = GamePiece.TEAMWHITE;
						setContainerTitle("White Player's Turn - W: " + dataBoard.getWhiteScore() + 
								" B: " + dataBoard.getBlackScore());
					}
					setEnabledButtons(ChessBoard.nullPiece);
				} else {
					piecePos = null;
					setEnabledButtons(ChessBoard.nullPiece);
				}
			}
			refreshButtons();
		} else {
			if(source.equals(pawnDialog.buttons[0])) {
				dataBoard.set(new Queen(pawnDialog.getTeam(), pawnDialog.getxGrid(), pawnDialog.getyGrid(), 1), 
						pawnDialog.getxGrid(), pawnDialog.getyGrid());
			} else if(source.equals(pawnDialog.buttons[1])) {
				dataBoard.set(new Bishop(pawnDialog.getTeam(), pawnDialog.getxGrid(), pawnDialog.getyGrid(), 1), 
						pawnDialog.getxGrid(), pawnDialog.getyGrid());
			} else if(source.equals(pawnDialog.buttons[2])) {
				dataBoard.set(new Knight(pawnDialog.getTeam(), pawnDialog.getxGrid(), pawnDialog.getyGrid(), 1), 
						pawnDialog.getxGrid(), pawnDialog.getyGrid());
			} else if(source.equals(pawnDialog.buttons[3])) {
				dataBoard.set(new Rook(pawnDialog.getTeam(), pawnDialog.getxGrid(), pawnDialog.getyGrid(), 1), 
						pawnDialog.getxGrid(), pawnDialog.getyGrid());
				
			}
			this.setEnabled(true);
			pawnDialog.setVisible(false);
			refreshButtons();
		}
	}

	@Override
	public void addActionListeners()
	{
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				panel.boardButtons[i][j].addActionListener(this);
			}
		}
	}

	@Override
	public void buildDefaultElements()
	{
		buildElement(panel, new StormConstraints(1, 1, 0, 0, 0, 0, 0, 0));
	}

	@Override
	public void configureElements()
	{
		panel = new AppInterface();
		dataBoard = new ChessBoard();
		playerTurn = GamePiece.TEAMWHITE;
	}
	private void refreshButtons()
	{
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				panel.boardButtons[i][j].setIcon(dataBoard.getPieceAt(i, j).getIcon());
			}
		}
	}
	private void setEnabledButtons(GamePiece piece)
	{
		int team = piece.getTeamNum();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(piece != ChessBoard.nullPiece) {
					panel.boardButtons[i][j].setEnabled(
							dataBoard.getPieceAt(i, j).getTeamNum() == team || piece.canMoveTo(i, j, dataBoard));
				} else {
					panel.boardButtons[i][j].setEnabled(!dataBoard.getPieceAt(i, j).equals(ChessBoard.nullPiece));
				}
			}
		}
	}
}

class CoOrdinate
{
	private int x;
	private int y;
	CoOrdinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
