package pwrdf.enums;

import pwrdf.entity.Projectile;
import pwrdf.entity.TowerStats;

public enum TowerEnum {
	basicTower(TowerStatsEnum.basicTower.getTowerStats(), new Projectile(0, 0)), 
	fastTower(TowerStatsEnum.fastTower.getTowerStats(), new Projectile(0, 0)),
	slowTower(TowerStatsEnum.slowTower.getTowerStats(), new Projectile(0, 0)),
	splashTower(TowerStatsEnum.splashTower.getTowerStats(), new Projectile(0, 0));
	
	private TowerStats stats;
	private Projectile shot;
	TowerEnum(TowerStats towerStats, Projectile towerShot) {
		stats = towerStats;
		shot = towerShot;
	}
	public TowerStats getStats()
	{
		return stats;
	}
	public Projectile getShot()
	{
		return shot;
	}
}
