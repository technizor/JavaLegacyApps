package pwrdf.enums;

import pwrdf.entity.TowerStats;

public enum TowerStatsEnum {
	basicTower(10, 50, 25, 100, 0.875, TowerStats.getStatScaling(35, 10, 12.5), 
			TowerStats.getStatScaling(2.5, 0.5, 3.25), TowerStats.getCostScaling(15, 35, 10),
			TowerStats.getCostScaling(5, 1, 10), TowerStats.getCostScaling(100, 150, 50), 2),
	fastTower(10, 50, 10, 125, 1.5, TowerStats.getStatScaling(15, 7.5, 20), 
			TowerStats.getStatScaling(3, 2, 5), TowerStats.getCostScaling(20, 40, 10),
			TowerStats.getCostScaling(10, 4, 10), TowerStats.getCostScaling(75, 125, 50), 2),
	slowTower(10, 50, 5, 175, 1.25, TowerStats.getStatScaling(10, 12.5, 15), 
			TowerStats.getStatScaling(1.5, 2.5, 3), TowerStats.getCostScaling(35, 25, 10),
			TowerStats.getCostScaling(5, 2, 10), TowerStats.getCostScaling(50, 100, 50), 2),
	splashTower(10, 50, 35, 125, 0.75, TowerStats.getStatScaling(35, 10, 5), 
			TowerStats.getStatScaling(3.5, 2.5, 1.5), TowerStats.getCostScaling(30, 45, 10),
			TowerStats.getCostScaling(15, 3, 10), TowerStats.getCostScaling(200, 175, 50), 2);
	
	private TowerStats stats;
	TowerStatsEnum(int maxRank, int maxLvl, double baseDmg, double baseRange, double baseSpeed,
			double[] rankScaling, double[] levelScaling, int[] rankCost, int[] powerCost, int[]lvlCost, int size)
	{
		
		this.stats = new TowerStats(maxRank, maxLvl, baseDmg, baseRange, baseSpeed,
				rankScaling, levelScaling, rankCost, powerCost, lvlCost, size);
	}
	public TowerStats getTowerStats()
	{
		return stats;
	}
}