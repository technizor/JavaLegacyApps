package ccg.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import ccg.com.chess.Bishop;
import ccg.com.chess.Knight;
import ccg.com.chess.Queen;
import ccg.com.chess.Rook;

public class PawnToPiece extends JFrame
{
	private static final long serialVersionUID = 4089525406078065711L;
	protected JButton[] buttons;
	private GridLayout grid;
	private int team;
	private int xGrid;
	private int yGrid;
	
	public PawnToPiece(int TEAMNUM, AppWindow parent, int x, int y) {
		setTeam(TEAMNUM);
		buttons = new JButton[4];
		grid = new GridLayout(4, 1);
		setxGrid(x);
		setyGrid(y);
		this.setLayout(grid);
		for(int i = 0; i < 4; i++) {
			buttons[i] = new JButton();
			this.add(buttons[i]);
			buttons[i].addActionListener(parent);
		}
		buttons[0].setIcon(new Queen(TEAMNUM, 0, 0).getIcon());
		buttons[1].setIcon(new Bishop(TEAMNUM, 0, 0).getIcon());
		buttons[2].setIcon(new Knight(TEAMNUM, 0, 0).getIcon());
		buttons[3].setIcon(new Rook(TEAMNUM, 0, 0).getIcon());
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	public int getxGrid()
	{
		return xGrid;
	}

	public void setxGrid(int xGrid)
	{
		this.xGrid = xGrid;
	}

	public int getyGrid()
	{
		return yGrid;
	}

	public void setyGrid(int yGrid)
	{
		this.yGrid = yGrid;
	}

	public int getTeam()
	{
		return team;
	}

	public void setTeam(int team)
	{
		this.team = team;
	}
}
