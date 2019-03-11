package lolutil.data;

import lolutil.data.champion.ChampionStats;

public class Passive
{
	boolean unique;
	int target;		//0 = self, 1 = friendly aura, 2 = enemy, 3 = enemey aura
	ChampionStats stats;
}
