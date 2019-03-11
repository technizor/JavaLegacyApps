package ubg.com;
import java.util.Random;

import ubg.data.AbilityPower;
import ubg.data.AttackDamage;
import ubg.data.AttackSpeed;
import ubg.data.Boots;
import ubg.data.Champion;
import ubg.data.Cooldown;
import ubg.data.Critical;
import ubg.data.Health;
import ubg.data.Item;
import ubg.data.ItemCategory;
import ubg.data.Mana;
import ubg.data.Resist;
import ubg.data.SummonerSpell;
import ubg.data.Viktor;
import ubg.preferences.ChampionPref;
import ubg.preferences.SummonerPref;


public class UltimateBraveryGen
{
	public final ItemCategory boots = new Boots();
	public final ItemCategory viktor = new Viktor();
	public final ItemCategory[] itemCategoryList = {
			new AbilityPower(), 
			new AttackDamage(), 
			new AttackSpeed(), 
			new Cooldown(), 
			new Critical(), 
			new Health(), 
			new Mana(), 
			new Resist()
	};
	public final int categoryTotal = 8;
	public final int classic = 0;
	public final int dominion = 1;
	public final int aram =  2;

	private String itemsObtained;
	private int itemNum;
	private String spellsObtained;
	private int spellNum;
	
	public Random rand;
	public int summonerLevel;
	public ChampionPref ownedChampions;
	public SummonerPref options;
	public int gamemode;
	public int primarySkill;
	public Champion champion;
	public String championName;
	public Item[] itemBuild = new Item[6];
	public SummonerSpell[] summonerSpells = new SummonerSpell[2];
	private String copyString;
	
	public UltimateBraveryGen()
	{
		//Initialize
		rand = new Random();
		ownedChampions = new ChampionPref();
		options = new SummonerPref();
		//Champions
		ultimateBravery();
		return;
	}
	
	public void ultimateBravery()
	{
		copyString = "";
		this.summonerLevel = options.getValue(0);
		this.gamemode = options.getValue(1);
		itemsObtained = "";
		itemNum = 0;
		spellsObtained = "";
		spellNum = 0;
		champion = getRandChampion();

		championName = champion.getName();
		//Boots
		itemBuild[0] = selectItem(boots);
		//Primary Skill
		primarySkill = rand.nextInt(3);
		//Remaining Items
		while(itemNum < 6) {
			selectItem(itemCategoryList[rand.nextInt(categoryTotal)]);
		}
		while(spellNum < 2) {
			selectSpell();
		}
		//Output
		String output = "";
		System.out.println("Summoner Level: " + (int)(summonerLevel+1));
		if(gamemode%3 == 0) {
			System.out.println("Classic Build - " + championName);
			output += "Classic Build for " + championName + ". ";
		} else if(gamemode%3 == 1) {
			System.out.println("Dominion Build - " + championName);
			output += "Dominion Build for " + championName + ". ";
		} else {
			System.out.println("Proving Grounds Build - " + championName);
			output += "Proving Grounds Build for " + championName + ". ";
		}
		output += "Using " + summonerSpells[0].getName() + " and " + summonerSpells[1].getName()
				+ ", and building " + itemBuild[0].getName() + ", " + itemBuild[1].getName()
				+ ", " + itemBuild[2].getName() + ", " + itemBuild[3].getName()
				 + ", " + itemBuild[4].getName() + " and " + itemBuild[5].getName() + ". ";
		System.out.println("Items: " + itemBuild[0].getName() + ", " + itemBuild[1].getName()
				+ ", " + itemBuild[2].getName() + ", " + itemBuild[3].getName()
				 + ", " + itemBuild[4].getName() + ", " + itemBuild[5].getName());
		System.out.println("Summoner Spells: " + summonerSpells[0].getName() + " and " + summonerSpells[1].getName());
		System.out.println("Primary Skill: " + (primarySkill == 0 ? "Q" : primarySkill == 1 ? "W" : "E"));
		output += "Max " + (primarySkill == 0 ? "Q" : primarySkill == 1 ? "W" : "E") + ".";
		copyString = output;
	}
	
	private SummonerSpell selectSpell()
	{
		int maxN = SummonerSpell.spellList.length;
		int n = -1;
		while(n == -1)
		{
			n = rand.nextInt(maxN);
			SummonerSpell spell = SummonerSpell.spellList[n];
			if(spell.isMode(gamemode)) {
				String name1 = "(" + spell.getName() + ")";
				if(spellsObtained.indexOf(name1) == -1 && spell.getSummonerLevel() <= summonerLevel) {
					spellsObtained += name1 + "\n";
				} else {
					n = -1;
				}
			} else {
				n = -1;
			}
		}
		summonerSpells[spellNum] = SummonerSpell.spellList[n];
		spellNum += 1;
		return SummonerSpell.spellList[n];
	}
	private Champion getRandChampion()
	{
		if(ownedChampions.getTotal() == 0) return Champion.random;
		int championN = rand.nextInt(ownedChampions.getTotal());
		int i = 0;
		for(int pos = 0; pos < 256; pos++) {
			if(ownedChampions.getValues()[pos]) {
				i += 1;
				if(i == championN) return Champion.champions[pos]; 
			}
		}
		return Champion.random;
	}
	public Item selectItem(ItemCategory category)
	{
		if(championName == "Viktor" && itemNum == 5) {
			itemBuild[itemNum] = viktor.getItem(primarySkill);
			itemNum += 1;
			return viktor.getItem(primarySkill);
		} else {
			int maxN = category.getSize();
			int n = -1;
			while(n == -1)
			{
				n = rand.nextInt(maxN);
				Item item = category.getItem(n);
				if(item.isMode(gamemode)) {
					String name1 = "(" + item.getName() + ")";
					if(itemsObtained.indexOf(name1) == -1) {
						itemsObtained += name1 + "\n";
					} else {
						n = -1;
					}
				} else {
					n = -1;
				}
			}
			itemBuild[itemNum] = category.getItem(n);
			itemNum += 1;
			return category.getItem(n);
		}
	}
	public Champion getChampion()
	{
		return champion;
	}

	public String getString()
	{
		return copyString;
	}
}
