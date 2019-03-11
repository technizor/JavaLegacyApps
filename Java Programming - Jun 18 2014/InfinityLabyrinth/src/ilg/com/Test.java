package ilg.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Test extends JFrame implements KeyListener, Runnable
{
	private static final long serialVersionUID = 2671188080379215610L;
	Thread runner;
	private Maze maze;
	private int generationMode = GrowingTreeMazeGen.RANDOM;
	//private JScrollPane scrollPane;
	private MazeScreen pane;
	public int X = 5;
	public int Y = 5;
	public static void main(String[] args)
	{
		new Test();
	}
	public Test()
	{
		maze = new Maze(X, Y, new GrowingTreeMazeGen(generationMode));
		pane = new MazeScreen(maze);
		//scrollPane = new JScrollPane(pane);
		if(runner == null) {
			runner = new Thread(this);
			runner.start();
		}
		this.setContentPane(pane);
		this.setSize(120 + 16, 120 + 38);
		//pane.setPreferredSize(new Dimension(Math.min(this.getSize().width, 300), Math.min(this.getSize().height, 300)));
		//if(this.getSize().height < this.getSize().width) {
		//	this.setSize(new Dimension(this.getSize().width, this.getSize().width));
		//}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_W) {
			pane.setDirection(2);
		} else if(ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S) {
			pane.setDirection(3);
		} else if(ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) {
			pane.setDirection(0);
		} else if(ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) {
			pane.setDirection(1);
		}
	}
	public void keyReleased(KeyEvent ke)
	{
		if((ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_W) &&
				pane.getDirection() == 2) {
			pane.setDirection(-1);/*
			if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasLeftWall()) {
				pane.setCharY(pane.getCharY() - 1);
			}*/
		} else if((ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_S) &&
				pane.getDirection() == 3) {
			pane.setDirection(-1);/*
			if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasRightWall()) {
				pane.setCharY(pane.getCharY() + 1);
			}*/
		} else if((ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) &&
				pane.getDirection() == 0) {
			pane.setDirection(-1);/*
			if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasTopWall()) {
				pane.setCharX(pane.getCharX() - 1);
			}*/
		} else if((ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) &&
				pane.getDirection() == 1) {
			pane.setDirection(-1);/*
			if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasBotWall()) {
				pane.setCharX(pane.getCharX() + 1);
			}*/
		}
	}
	public void keyTyped(KeyEvent ke) {}
	public void run()
	{
		while(true) {
			if(pane.getCharX() == pane.getEndX() && pane.getCharY() == pane.getEndY()) {
				X = Y = (int) (X * 1.25 + 2);
				maze = new Maze(X, Y, new GrowingTreeMazeGen(generationMode));
				pane.setMaze(maze);
				this.setLocationRelativeTo(null);
				repaint();
			}
			if(pane.getDirection() == 0) {
				if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasTopWall()) {
					pane.setCharX(pane.getCharX() - 1);
				}
			} else if(pane.getDirection() == 1) {
				if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasBotWall()) {
					pane.setCharX(pane.getCharX() + 1);
				}
			} else if(pane.getDirection() == 2) {
				if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasLeftWall()) {
					pane.setCharY(pane.getCharY() - 1);
				}
			} else if(pane.getDirection() == 3) {
				if(!maze.getCell(pane.getCharX(), pane.getCharY()).hasRightWall()) {
					pane.setCharY(pane.getCharY() + 1);
				}
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupted Thread.");
			}
		}
	}
}
