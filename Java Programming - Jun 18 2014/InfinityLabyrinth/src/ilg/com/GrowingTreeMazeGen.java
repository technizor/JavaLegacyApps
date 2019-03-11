package ilg.com;

import java.util.ArrayList;
import java.util.Random;

public class GrowingTreeMazeGen extends MazeGen
{
	public static final int NEWEST = 1337;
	public static final int OLDEST = 9001;
	public static final int MIDDLE = 7777;
	public static final int RANDOM = 5737;
	public static final int NEWRAND5050 = 35315;
	public static final int NEWRAND2575 = 4626;
	public static final int NEWRAND7525 = 44366;
	public static final int NEWOLD5050 = 3151;
	public static final int OLDRAND5050 = 531;
	
	private int genMode;
	public GrowingTreeMazeGen(int mode) {
		super("Growing Tree Algorithm");
	}
	
	public void generateMaze(Maze maze)
	{
		boolean[][] init = new boolean[maze.height()][maze.width()];
		Random rand = new Random();
		Random gridRand = new Random(rand.nextInt());
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		int x = gridRand.nextInt() % maze.height();
		int y = gridRand.nextInt() % maze.width();
		if(x < 0) x *= -1;
		if(y < 0) y *= -1;
		Integer[] n = {x, y};
		maze.setCell(x, y, new MazeCell(false, false, false, false));
		init[x][y] = true;
		list.add(n);
		for(int i = 1; i < maze.height() * maze.width() && list.size() > 0;) {
			int randElement = rand.nextInt() % list.size();
			if(randElement < 0) randElement *= -1;
			switch(genMode) {
			case NEWEST:
				n = list.get(list.size() - 1);
				break;
			case OLDEST:
				n = list.get(0);
				break;
			case MIDDLE:
				n = list.get(list.size() / 2);
				break;
			case NEWRAND5050:
				if(rand.nextInt() % 2 == 0) {
					n = list.get(list.size() - 1);
				} else {
					n = list.get(randElement);
				}
				break;
			case NEWRAND2575:
				if(rand.nextInt() % 4 == 0) {
					n = list.get(list.size() - 1);
				} else {
					n = list.get(randElement);
				}
				break;
			case NEWRAND7525:
				if(rand.nextInt() % 4 == 0) {
					n = list.get(randElement);
				} else {
					n = list.get(list.size() - 1);
				}
				break;
			case NEWOLD5050:
				if(rand.nextInt() % 2 == 0) {
					n = list.get(0);
				} else {
					n = list.get(list.size() - 1);
				}
				break;
			case OLDRAND5050:
				if(rand.nextInt() % 2 == 0) {
					n = list.get(0);
				} else {
					n = list.get(randElement);
				}
				break;
			default://RANDOM
				n = list.get(randElement);
				break;
			}
			Integer[] temp = null;
			ArrayList<Integer> directions = new ArrayList<Integer>();
			for(int d = 0; d < 4; d++) directions.add(d);
			for(int direction = gridRand.nextInt(directions.size()), size = directions.size(); size > 0; size--, direction = gridRand.nextInt(size)) {
				//0 = UP, 1 = DOWN, 2 = LEFT, 3 = RIGHT
				boolean able = false;
				if(direction < 0) direction *= -1;
				switch(directions.get(direction)) {
				case 0:
					if(n[0] > 0) {
						if(!init[n[0] - 1][n[1]] && 
								maze.getCell(n[0], n[1]).hasTopWall()) {
						able = true;
						maze.getCell(n[0], n[1]).setTop(false);
						maze.getCell(n[0] - 1, n[1]).setBot(false);
						temp = new Integer[2];
						temp[0] = n[0] - 1;
						temp[1] = n[1];
						list.add(temp);
						init[temp[0]][temp[1]] = true;
					}
					} 
					break;
				case 1:
					if(n[0] < maze.height() - 1) {
						if(!init[n[0] + 1][n[1]] && 
								maze.getCell(n[0], n[1]).hasBotWall()) {
							able = true;
							maze.getCell(n[0], n[1]).setBot(false);
							maze.getCell(n[0] + 1, n[1]).setTop(false);
							temp = new Integer[2];
							temp[0] = n[0] + 1;
							temp[1] = n[1];
							list.add(temp);
							init[temp[0]][temp[1]] = true;
						}
					}
 					break;
				case 2:
					if(n[1] > 0) {
						if(!init[n[0]][n[1] - 1] && 
								maze.getCell(n[0], n[1]).hasLeftWall()) {
							able = true;
							maze.getCell(n[0], n[1]).setLeft(false);
							maze.getCell(n[0], n[1] - 1).setRight(false);
							temp = new Integer[2];
							temp[0] = n[0];
							temp[1] = n[1] - 1;
							list.add(temp);
							init[temp[0]][temp[1]] = true;
						}
					}
					break;
				case 3:
					if(n[1] < maze.width() - 1) {
						if(!init[n[0]][n[1] + 1] && 
								maze.getCell(n[0], n[1]).hasRightWall()) {
							able = true;
							maze.getCell(n[0], n[1]).setRight(false);
							maze.getCell(n[0], n[1] + 1).setLeft(false);
							temp = new Integer[2];
							temp[0] = n[0];
							temp[1] = n[1] + 1;
							list.add(temp);
							init[temp[0]][temp[1]] = true;
						}
					}
					break;
				}
				if(able) {
					i++;
					break;
				}
				if(size != 1) directions.remove(direction);
				else break;
			}
			if(temp == null && (n[0] == 0 || init[n[0] - 1][n[1]]) && 
					(n[0] == maze.height() - 1 || init[n[0] + 1][n[1]]) && 
					(n[1] == maze.width() - 1 || init[n[0]][n[1] + 1]) && 
					(n[1] == 0 || init[n[0]][n[1] - 1])) {
				list.remove(list.indexOf(n));
			}
		}
	}

}
