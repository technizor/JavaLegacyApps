package pwrdf.map;

import java.util.ArrayList;

public class TowerTiles
{
	private ArrayList<Tower> towerList;
	private boolean[] towerExist;
	private int[][] towerMask;
	private int width;
	private int height;
	public TowerTiles(int width, int height)
	{
		towerMask = new int[width][height];
		this.width = width;
		this.height = height;
		towerList = new ArrayList<Tower>();
	}
	public boolean canPlace(Tower tower, int xPos, int yPos)
	{
		int size = tower.getStats().getSize();
		if(xPos >= 0 && yPos > 0 && xPos+size <= width && yPos+size <= height) {
			for(int i = xPos; i < xPos+size; i++) {
				for(int j = yPos; j < yPos+size; j++) {
					if(towerMask[i][j] != 0) return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean placeTower(Tower tower, int xPos, int yPos)
	{
		boolean canPlace = canPlace(tower, xPos, yPos);
		if(!canPlace) {
			return false;
		}
		int towerId = getFirstEmpty();
		int size = tower.getStats().getSize();
		for(int i = xPos; i < xPos+size; i++) {
			for(int j = yPos; j < yPos+size; j++) {
				if(i == xPos && j == yPos) {
					towerMask[i][j] = towerId;
				} else {
					towerMask[i][j] = -1*towerId;
				}
			}
		}
		if(towerId > towerList.size()) {
			towerList.add(tower);
		} else {
			towerList.set(towerId-1, tower);
		}
		return true;
	}
	private int getFirstEmpty()
	{
		for(int index = 0; index < towerList.size(); index++) {
			if(!towerExist[index]) return index+1;
 		}
		return towerList.size()+1;
	}
	public boolean removeTower(int xPos, int yPos)
	{
		if(towerMask[xPos][yPos] == 0) return false;
		int towerId = Math.abs(towerMask[xPos][yPos]);
		Tower tower = towerList.get(towerId-1);
		int size = tower.getStats().getSize();
		for(int i = xPos+1-size; i < xPos+size; i++) {
			for(int j = yPos+1-size; j < yPos+size; j++) {
				if(Math.abs(towerMask[i][j]) == towerId) {
					towerMask[i][j] = 0;
				}
			}
		}
		towerExist[towerId-1] = false;
		return true;
	}
}
