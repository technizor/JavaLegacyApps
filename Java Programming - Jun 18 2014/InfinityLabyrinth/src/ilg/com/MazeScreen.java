package ilg.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class MazeScreen extends JPanel
{

	private static final long serialVersionUID = 1L;
	private Maze maze;
	private int charX;
	private int charY;
	private int endX;
	private int endY;
	private int direction;
	private int viewX;
	private int viewY;
	public final int viewH = 5;
	public final int viewW = 5;
	private final int viewOffset = 15;
	private final int SCALE = 20;
	
	public MazeScreen(Maze maze)
	{
		this.maze = maze;
		Random rand = new Random();
		setCharX(rand.nextInt(maze.width()));
		setCharY(rand.nextInt(maze.height()));
		endX = rand.nextInt(maze.width());
		endY = rand.nextInt(maze.height());
		direction = -1;
	}
	public void paintComponent(Graphics comp)
	{
		Graphics2D comp2D = (Graphics2D) comp;
		comp2D.setColor(Color.darkGray);
		comp2D.fillRect(0, 0, viewW*SCALE+viewOffset*2, viewH*SCALE+viewOffset*2);
		comp2D.setColor(Color.BLACK);
		comp2D.fillRect(viewOffset, viewOffset, viewW*SCALE, viewH*SCALE);
		for(int i = viewX; i < viewX+viewW; i++) {
			for(int j = viewY; j < viewY+viewH; j++) {
				comp2D.setColor(Color.GREEN);
				MazeCell cell = maze.getCell(i, j);
				if(cell.hasTopWall()) comp2D.fillRect((i-viewX)*SCALE+viewOffset, (j-viewY)*SCALE+viewOffset, 1, SCALE);
				if(cell.hasBotWall()) comp2D.fillRect((i-viewX+1)*SCALE - 1+viewOffset, (j-viewY)*SCALE+viewOffset, 1, SCALE);
				if(cell.hasLeftWall()) comp2D.fillRect((i-viewX)*SCALE+viewOffset, (j-viewY)*SCALE+viewOffset, SCALE, 1);
				if(cell.hasRightWall()) comp2D.fillRect((i-viewX)*SCALE+viewOffset, (j-viewY+1)*SCALE - 1+viewOffset, SCALE, 1);
				if(i == endX && j == endY) {
					comp2D.setColor(Color.BLUE);
					comp2D.fillRect((i-viewX) * SCALE + 1+viewOffset, (j-viewY) * SCALE + 1+viewOffset, SCALE - 2, SCALE - 2);
				}
				if(i == charX && j == charY) {
					comp2D.setColor(Color.RED);
					comp2D.fillOval((i-viewX) * SCALE + 1+viewOffset, (j-viewY) * SCALE + 1+viewOffset, SCALE - 2, SCALE - 2);
				}
			}
		}
		int direct = 0;
		//0 = there, 1 = north, 2 = east, 3 = south, 4 = west, 5 = northwest, 6 = northeast, 7 = southeast, 8 = southwest
		if(charX > endX) {
			if(charY > endY) {
				direct = 5;
			} else if(charY < endY) {
				direct = 8;
			} else {
				direct = 4;
			}
		} else if(charX < endX) {
			if(charY > endY) {
				direct = 6;
			} else if(charY < endY) {
				direct = 7;
			} else {
				direct = 2;
			}
		} else {
			if(charY > endY) {
				direct = 1;
			} else if(charY < endY) {
				direct = 3;
			} else {
				direct = 0;
			}
 		}
		int[] cardinalCenter = {65, 58, 72};
		int[] cardinalEdge1 = {128, 121, 121};
		int[] cardinalEdge2 = {2, 9, 9};
		int[] ordinalLeft = {2, 11, 2};
		int[] ordinalRight = {128, 119, 128};
		int[] ordinalTop = {2, 2, 11};
		int[] ordinalBot = {119, 128, 128};
		comp2D.setColor(Color.lightGray);
		switch(direct)
		{
		case 1:
			comp2D.fillPolygon(cardinalCenter, cardinalEdge2, 3);
			break;
		case 2:
			comp2D.fillPolygon(cardinalEdge1, cardinalCenter, 3);
			break;
		case 3:
			comp2D.fillPolygon(cardinalCenter, cardinalEdge1, 3);
			break;
		case 4:
			comp2D.fillPolygon(cardinalEdge2, cardinalCenter, 3);
			break;
		case 5:
			comp2D.fillPolygon(ordinalLeft, ordinalTop, 3);
			break;
		case 6:
			comp2D.fillPolygon(ordinalRight, ordinalTop, 3);
			break;
		case 7:
			comp2D.fillPolygon(ordinalRight, ordinalBot, 3);
			break;
		case 8:
			comp2D.fillPolygon(ordinalLeft, ordinalBot, 3);
			break;
		default:
			break;
		}
	}
	public int getCharX()
	{
		return charX;
	}
	public int getCharY()
	{
		return charY;
	}
	public int getEndX()
	{
		return endX;
	}
	public int getEndY()
	{
		return endY;
	}
	public void setCharX(int x)
	{
		charX = x;
		viewX = Math.min(maze.width()-5,Math.max(0, x-2));
	}
	public void setCharY(int y)
	{
		charY = y;
		viewY = Math.min(maze.height()-5,Math.max(0, y-2));
	}
	public void setEndX(int x)
	{
		endX = x;
	}
	public void setEndY(int y)
	{
		endY = y;
	}
	public int getDirection()
	{
		return direction;
	}
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	public boolean getRunning()
	{
		return getRunning();
	}
	public void setMaze(Maze maze)
	{
		this.maze = maze;
		Random rand = new Random();
		setCharX(rand.nextInt(maze.width()));
		setCharY(rand.nextInt(maze.height()));
		endX = rand.nextInt(maze.width());
		endY = rand.nextInt(maze.height());
		direction = -1;
	}
}
