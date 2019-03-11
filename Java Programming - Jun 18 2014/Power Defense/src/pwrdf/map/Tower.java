package pwrdf.map;

import pwrdf.entity.Enemy;
import pwrdf.entity.Projectile;
import pwrdf.entity.TowerStats;

public class Tower extends Tile
{
	private int towerId;
	private TowerStats stats;
	private Projectile towerMissile;
	private Enemy target;
	
	public Tower(int towerId, TowerStats towerStats)
	{
		setTowerId(towerId);
		this.stats = towerStats;
		target = null;
	}

	public void setTarget(Enemy target)
	{
		this.target = target;
	}
	
	public Enemy getTarget()
	{
		return target;
	}
	
	public int getTowerId()
	{
		return towerId;
	}

	public void setTowerId(int towerId)
	{
		this.towerId = towerId;
	}

	public Projectile getTowerMissile()
	{
		return towerMissile;
	}

	public void setTowerMissile(Projectile towerMissile)
	{
		this.towerMissile = towerMissile;
	}
	public void towerUpgrade()
	{
		stats.towerUpgrade();
	}
	public void towerDamaged(int damage)
	{
		stats.towerDamaged(damage);
	}
	public void towerKilled(int creepMaxHp)
	{
		stats.towerKilled(creepMaxHp);
	}
	public int towerSell(int x, int y, Map map)
	{
		map.getMapTiles()[x][y] = Tile.emptyTile;
		return stats.getSellValue();
	}

	public TowerStats getStats()
	{
		return stats;
	}

	public void setStats(TowerStats stats)
	{
		this.stats = stats;
	}
	public void target()
	{
		
	}
}
