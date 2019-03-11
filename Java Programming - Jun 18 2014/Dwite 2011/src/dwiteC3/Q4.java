package dwiteC3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Q4
{
	public static void main(String[] args)
	{
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader(new File("c3/DATA4.TXT")));
			writer = new PrintWriter(new FileWriter(new File("c3/OUT4.TXT")));
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				String temp1 = scan.nextLine();
				String[] temp = temp1.split(" ");
				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);
				char[][] maze = new char[x][y];
				for(int j = 0; j < x; j++) {
					maze[j] = scan.nextLine().toCharArray();
				}
				int ax = -1, ay = -1, bx = -1, by = -1, cx = -1, cy = -1;
				for(int j = 0; j < x; j++) {
					for(int k = 0; k < y && (ax == -1 || bx == -1 || cx == -1); k++) {
						if(maze[j][k] == 'A') {
							ax = j;
							ay = k;
						}
						if(maze[j][k] == 'B') {
							bx = j;
							by = k;
						}
						if(maze[j][k] == 'C') {
							cx = j;
							cy = k;
						}
					}
				}
				int moves = getMoves(ax, ay, bx, by, maze) + getMoves(bx, by, cx, cy, maze) + getMoves(cx, cy, ax, ay, maze);
				writer.println(moves);
				writer.flush();
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
	public static int getMoves(int posX1, int posY1, int posX2, int posY2, char[][] maze) {
		int moves = 0;
		for(; !(posX1 == posX2 && posY1 == posY2) && moves < 1000; moves++) {
			boolean up = false, down = false, left = false, right = false;
			if(posX1 > posX2) {
				up = true;
			} else if(posX1 < posX2) {
				down = true;
			}
			if(posY1 > posY2) {
				left = true;
			} else if(posY1 < posY2) {
				right = true;
			}
			if(down && maze[posX1+1][posY1] != '#') {
				posX1++;
				continue;
			}
			if(up && maze[posX1-1][posY1] != '#') {
				posX1--;
				continue;
			}
			if(right && maze[posX1][posY1+1] != '#') {
				posY1++;
				continue;
			}
			if(left && maze[posX1][posY1-1] != '#') {
				posY1--;
				continue;
			}
		}
		return moves;
	}
}
