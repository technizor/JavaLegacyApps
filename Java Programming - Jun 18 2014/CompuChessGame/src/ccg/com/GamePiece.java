package ccg.com;

import javax.swing.ImageIcon;

import ccg.com.chess.Rook;

public abstract class GamePiece
{
	public static final int TEAMBLACK = 1337;
	public static final int TEAMWHITE = 9001;
	public static final int CHESSMODE = 7777;
	public static final int CHECKERSMODE = 4341;
	public static final int UP = 135;
	public static final int DOWN = 136;
	public static final int LEFT = 137;
	public static final int RIGHT = 138;
	public static final int UPLEFT = 139;
	public static final int UPRIGHT = 140;
	public static final int DOWNLEFT = 141;
	public static final int DOWNRIGHT = 142;
	
	private ImageIcon icon;
	private int gameMode;
	private int teamNum;
	private int pieceType;
	private int value;
	private int xPos;
	private int yPos;
	private boolean isNull;
	private boolean canBePassant;
	private boolean isPassing;
	private boolean canCastle;
	private boolean isCastling;
	
	public GamePiece()
	{
		teamNum = 0;
		pieceType = 0;
		xPos = 0;
		yPos = 0;
		isNull = true;
		canBePassant = false;
		isPassing = false;
		canCastle = false;
		isCastling = false;
	}
	
	public GamePiece(int team, int piece, int x, int y, String iconPath)
	{
		teamNum = team;
		pieceType = piece;
		xPos = x;
		yPos = y;
		canBePassant = false;
		isPassing = false;
		canCastle = false;
		isCastling = false;
		setIcon(iconPath);
	}
	
	public abstract boolean canMoveTo(int x, int y, ChessBoard board);
	public abstract void move(int x, int y, ChessBoard board);
	public abstract boolean check(ChessBoard board);
	
	public boolean isHorizontalOf(int x, int y)
	{
		if(x == this.xPos) return true;
		return false;
	}
	public boolean isVerticalOf(int x, int y)
	{
		if(y == this.yPos) return true;
		return false;
	}
	public boolean isHorizontalVerticalOf(int x, int y)
	{
		if(isVerticalOf(x, y) || isHorizontalOf(x, y)) return true;
		return false;
	}
	public boolean isDiagonalOf(int x, int y)
	{
		int relativeX = this.xPos - x;
		int relativeY = this.yPos - y;
		if(relativeX == relativeY || relativeX * -1 == relativeY) return true;
		return false;
	}
	
	public int getyPos()
	{
		return yPos;
	}
	protected void setyPos(int yPos)
	{
		this.yPos = yPos;
	}
	public int getxPos()
	{
		return xPos;
	}
	protected void setxPos(int xPos)
	{
		this.xPos = xPos;
	}
	public int getPieceType()
	{
		return pieceType;
	}
	public int getTeamNum()
	{
		return teamNum;
	}
	public int getGameMode()
	{
		return gameMode;
	}
	protected void setGameMode(int mode)
	{
		gameMode = mode;
	}
	public boolean isNull()
	{
		return isNull;
	}
	public int getValue()
	{
		return value;
	}
	protected void setValue(int value)
	{
		this.value = value;
	}
	public boolean canBeEnPassant()
	{
		return canBePassant;
	}
	protected void setEnPassant(boolean enPassant)
	{
		canBePassant = enPassant;
	}
	public boolean isPassing()
	{
		return isPassing;
	}
	protected void setPassing(boolean isPassing)
	{
		this.isPassing = isPassing;
	}
	public boolean isCanCastle()
	{
		return canCastle;
	}
	protected void setCanCastle(boolean canCastle)
	{
		this.canCastle = canCastle;
	}

	public boolean isCastling()
	{
		return isCastling;
	}

	public void setCastling(boolean isCastling)
	{
		this.isCastling = isCastling;
	}
	public void castle(boolean castleLeft)
	{
		if(this.getClass() == Rook.class) {
			if(isCanCastle()) {
				this.yPos = castleLeft ? yPos + 3 : yPos - 2;
			}
		}
	}

	public ImageIcon getIcon()
	{
		return icon;
	}

	private void setIcon(String path)
	{
		java.net.URL imageURL = this.getClass().getResource(path);
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
	}
}
