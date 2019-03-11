package pwrdf.entity;

public class TowerStats
{
	/** Tower Max Rank (Upgrades)*/
	private int maxRank;
	/** Tower Max Level*/
	private int maxLvl;

	/** Tower Upgrade Level*/
	private int currentRank;
	/** Tower Experience Level*/
	private int currentLvl;
	/** Tower Experience Value (Per damage dealt, plus total max health if last hit unit)*/
	private int currentExp;

	/** Tower Cost per upgrade (0 = initial price)*/
	private int[] rankCost;
	/** Tower Power usage per upgrade (0 = initial power cost)*/
	private int[] powerCost;
	/** Tower Experience per level (0 to reach level 0)*/
	private int[] lvlCost;
	
	/** Tower Base Stats at Level 0: Damage*/
	private double baseDmg;
	/** Tower Base Stats at Level 0: Range*/
	private double baseRange;
	/** Tower Base Stats at Level 0: Attack Speed*/
	private double baseSpeed;

	/** Tower Stat scaling (0 = AD, 1 = Range, 2 = AS) per upgrade*/
	private double[] rankScaling;
	/** Tower Stat scaling (0 = AD, 1 = Range, 2 = AS) per level*/
	private double[] levelScaling;
	/** Tower size - Tower is size*size tiles*/
	private int size;
	
	public TowerStats(int maxRank, int maxLvl, double baseDmg, double baseRange, double baseSpeed,
			double[] rankScaling, double[] levelScaling, int[] rankCost, int[] powerCost, int[] lvlCost, int towerSize)
	{
		setMaxRank(maxRank);
		setMaxLvl(maxLvl);
		setBaseDmg(baseDmg);
		setBaseRange(baseRange);
		setBaseSpeed(baseSpeed);
		setRankScaling(rankScaling);
		setLevelScaling(levelScaling);
		setLvlCost(lvlCost);
		setPowerCost(powerCost);
		setRankCost(rankCost);
		setCurrentExp(0);
		setCurrentLvl(0);
		setCurrentRank(0);
		setSize(towerSize);
	}

	public double getLevelScaling(int i)
	{
		return levelScaling[i];
	}

	private void setLevelScaling(double[] levelScaling)
	{
		this.levelScaling = levelScaling;
	}

	public double getRankScaling(int i)
	{
		return rankScaling[i];
	}

	private void setRankScaling(double[] rankScaling)
	{
		this.rankScaling = rankScaling;
	}

	public double getBaseSpeed()
	{
		return baseSpeed;
	}

	private void setBaseSpeed(double baseSpeed)
	{
		this.baseSpeed = baseSpeed;
	}

	public double getBaseRange()
	{
		return baseRange;
	}

	private void setBaseRange(double baseRange)
	{
		this.baseRange = baseRange;
	}

	public double getBaseDmg()
	{
		return baseDmg;
	}

	private void setBaseDmg(double baseDmg)
	{
		this.baseDmg = baseDmg;
	}

	public int getMaxLvl()
	{
		return maxLvl;
	}

	private void setMaxLvl(int maxLvl)
	{
		this.maxLvl = maxLvl;
	}

	public int getMaxRank()
	{
		return maxRank;
	}

	private void setMaxRank(int maxRank)
	{
		this.maxRank = maxRank;
	}
/** Returns Current Level +1 for display purposes*/
	public int getCurrentLvl()
	{
		return currentLvl+1;
	}

	private void setCurrentLvl(int currentLvl)
	{
		this.currentLvl = currentLvl;
	}

/** Returns Current Rank +1 for display purposes*/
	public int getCurrentRank()
	{
		return currentRank+1;
	}

	private void setCurrentRank(int currentRank)
	{
		this.currentRank = currentRank;
	}

	public int getCurrentExp()
	{
		return currentExp;
	}

	private void setCurrentExp(int currentExp)
	{
		this.currentExp = currentExp;
	}

	public int getRankCost(int i)
	{
		return rankCost[i];
	}

	private void setRankCost(int[] rankCost)
	{
		this.rankCost = rankCost;
	}

	public int getPowerCost(int i)
	{
		return powerCost[i];
	}

	private void setPowerCost(int[] powerCost)
	{
		this.powerCost = powerCost;
	}

	public int getLvlCost(int i)
	{
		return lvlCost[i];
	}

	private void setLvlCost(int[] lvlCost)
	{
		this.lvlCost = lvlCost;
	}
	
	public void towerDamaged(int damage)
	{
		setCurrentExp(getCurrentExp() + damage);
	}
	public void towerKilled(int creepMaxHp)
	{
		setCurrentExp(getCurrentExp() + creepMaxHp);
		if(getCurrentExp() >= getLvlCost(currentLvl+1)) {
			towerLeveled();
		}
	}
	public void towerUpgrade()
	{
		if(currentRank+1 < getMaxRank())
			setCurrentRank(currentRank+1);
	}
	
	private void towerLeveled()
	{
		if(currentLvl+1 < getMaxLvl())
			setCurrentLvl(currentLvl+1);
	}
	public double getDamage()
	{
		return getBaseDmg()+currentLvl*getLevelScaling(0)+currentRank*getRankScaling(0);
	}
	public double getRange()
	{
		return getBaseRange()+currentLvl*getLevelScaling(1)+currentRank*getRankScaling(1);
	}
	public double getSpeed()
	{
		return getBaseSpeed()*(currentLvl*getLevelScaling(2)+currentRank*getRankScaling(2))/100;
	}
	public int getPowerCost()
	{
		return getPowerCost(currentRank);
	}
	public int getNextLevelExp()
	{
		if(currentLvl+1 < getMaxLvl())
			return getLvlCost(currentLvl+1);
		return -1;
	}
	public int getUpgradeCost()
	{
		if(currentRank+1 < getMaxRank())
			return getRankCost(currentRank+1);
		return -1;
	}
	public int getSellValue()
	{
		double val = 0;
		for(int i = 0; i <= currentRank; i++)
		{
			val += getRankCost(i);
		}
		return (int)val;
	}
	
	public static double[] getStatScaling(double ... stats)
	{
		double[] scale = new double[3];
		scale[0] = stats[0];
		scale[1] = stats[1];
		scale[2] = stats[2];
		return scale;
	}
	public static int[] getCostScaling(int base, int scale, int maxTimes)
	{
		int[] cost = new int[maxTimes];
		cost[0] = base;
		for(int i = 1; i < maxTimes; i++) {
			cost[i] = cost[i-1]+scale;
		}
		return cost;
	}

	public int getSize()
	{
		return size;
	}

	private void setSize(int size)
	{
		this.size = size;
	}
}
