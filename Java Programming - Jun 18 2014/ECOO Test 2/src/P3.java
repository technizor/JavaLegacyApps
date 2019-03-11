import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class P3
{
	public Bulb[][] circuitBoard;
	public int height;
	public int width;
	public static void main (final String[] args) throws IOException
	{
		new P3();
	}
	public P3() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("p3.in")));
		for(int iter = 0; iter < 5; iter++) {
			Scanner getSizeBoard = new Scanner(reader.readLine());
			height = getSizeBoard.nextInt();
			width = getSizeBoard.nextInt();
			circuitBoard = new Bulb[height][width];
			int totalConnections = 0;
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					circuitBoard[i][j] = new Bulb(i,j,0);
				}
			}
			for(int row = 0; row < height; row++) {
				char[] rowLine = reader.readLine().toCharArray();
				for(int i = 0; i < rowLine.length; i++) {
					circuitBoard[row][i].newBulb(Integer.parseInt("" + rowLine[i]), circuitBoard);
					totalConnections += Integer.parseInt("" + rowLine[i]);
				}
			}
			if(totalConnections % 2 == 1) {
				System.out.println("-1");
				continue;
			}
			boolean works = true;
			printBoard();
			System.out.println(((works) ? totalConnections/2 : "-1"));
		}
	}

	private void printBoard()
	{
		for(int i = 0; i < height; i++) {
			String line = "";
			for(int j = 0; j < width; j++) {
				line += circuitBoard[i][j].connections;
				if(circuitBoard[i][j].getRight()) {
					line += "-";
				} else {
					line += " ";
				}
			}
			String line2 = "";
			for(int j = 0; j < width; j++) {
				if(circuitBoard[i][j].getBot()) {
					line2 += "|";
				} else {
					line2 += " ";
				}
				line2 += " ";
			}	
			System.out.println(line);
			System.out.println(line2);
		}
	}
	public void setAllConnected(int i, int j)
	{
		if(i > 0)circuitBoard[i-1][j].setBot(height);
		if(i < height-1) circuitBoard[i+1][j].setTop();
		if(j > 0)circuitBoard[i][j-1].setBot(height);
		if(j < width-1) circuitBoard[i][j+1].setTop();
		
	}
}
class Bulb {
	public int xPos;
	public int yPos;
	public int connections;
	private boolean[] sides;
	public void newBulb(int connections, Bulb[][] circuit) {
		this.connections = connections;
		sides = new boolean[4];
		if(connections == 4) {
			sides[0] = sides[1] = sides[2] = sides[3] = true;
			if(xPos > 0) {
				circuit[xPos-1][yPos].setBot(circuit.length);
			}
			if(yPos > 0) {
				circuit[xPos][yPos-1].setRight(circuit[0].length);
			}
			if(xPos < circuit.length-1) {
				circuit[xPos+1][yPos].setTop();
			}
			if(yPos > circuit[0].length-1) {
				circuit[xPos][yPos+1].setLeft();
			}
		}
		if((xPos == 0 || yPos == 0 || xPos == circuit.length-1 ||
				yPos == circuit[0].length-1) && connections == 3) {
			if(xPos > 0) {
				sides[0] = true;
				circuit[xPos-1][yPos].setBot(circuit.length);
			}
			if(yPos > 0) {
				sides[2] = true;
				circuit[xPos][yPos-1].setRight(circuit[0].length);
			}
			if(xPos < circuit.length-1) {
				sides[1] = true;
				circuit[xPos+1][yPos].setTop();
			}
			if(yPos > circuit[0].length-1) {
				sides[3] = true;
				circuit[xPos][yPos+1].setLeft();
			}
		}
		if(connections == 2 && ((xPos == 0 && yPos == 0) ||
				(xPos == 0 && yPos == circuit[0].length-1)||
				(xPos == circuit.length-1 && yPos == 0) ||
				(xPos == circuit.length-1 && yPos == circuit[0].length-1))) {
			if(xPos > 0) {
				sides[0] = true;
				circuit[xPos-1][yPos].setBot(circuit.length);
			}
			if(yPos > 0) {
				sides[2] = true;
				circuit[xPos][yPos-1].setRight(circuit[0].length);
			}
			if(xPos < circuit.length-1) {
				sides[1] = true;
				circuit[xPos+1][yPos].setTop();
			}
			if(yPos > circuit[0].length-1) {
				sides[3] = true;
				circuit[xPos][yPos+1].setLeft();
			}
		}
	}
	Bulb(int x, int y, int connections) {
		xPos = x;
		yPos = y;
		this.connections = connections;
		sides = new boolean[4];
		if(connections == 4) {
			sides[0] = sides[1] = sides[2] = sides[3] = true;
		}
	}
	public boolean getTop()
	{
		return sides[0];
	}
	public boolean getBot()
	{
		return sides[1];
	}
	public boolean getLeft()
	{
		return sides[2];
	}
	public boolean getRight()
	{
		return sides[3];
	}
	public int getConnected()
	{
		int n = 0;
		for(boolean b : sides) {
			if(b) n++;
		}
		return n;
	}
	public void setTop()
	{
		if(connections <= getConnected()) return;
		if(xPos != 0) sides[0] = true;
	}
	public void setBot(int height)
	{
		if(connections <= getConnected()) return;
		if(xPos < height-1) sides[1] = true;
	}
	public void setLeft()
	{
		if(connections <= getConnected()) return;
		if(yPos > 0) sides[2] = true;
	}
	public void setRight(int width)
	{
		if(connections <= getConnected()) return;
		if(yPos < width-1) sides[3] = true;
	}
}