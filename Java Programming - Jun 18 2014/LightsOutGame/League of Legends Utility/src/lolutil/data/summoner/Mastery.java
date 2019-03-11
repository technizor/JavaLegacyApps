package lolutil.data.summoner;

import lolutil.data.champion.ChampionStats;

public enum Mastery
{
	summonersWrath("Summoner's Wrath", 0, 0, 0, 1),
	bruteForce("Brute Force", 0, 0, 1, 4),
	mentalForce("Mental Force", 0, 0, 2, 4),
	butcher("Butcher", 0, 0, 3, 2),
	alacrity("Alacrity", 0, 1, 1, 4),
	sorcery("Sorcery", 0, 1, 2, 4),
	demolistionist("Demolitionist", 0, 1, 3, 1),
	deadliness("Deadliness", 0, 2, 0, 4),
	weaponExpertise("Weapons Expertise", 0, 2, 1, 1, true),
	arcaneKnowledge("Arcane Knowledge", 0, 2, 2, 1, true),
	havoc("Havoc", 0, 2, 3, 3),
	lethality("Lethality", 0, 3, 0, 1, true),
	vampirism("Vampirism", 0, 3, 1, 3),
	blast("Blast", 0, 3, 2, 4),
	sunder("Sunder", 0, 4, 2, 3),
	archmage("Archmage", 0, 4, 3, 4),
	executioner("Executioner", 0, 5, 1, 1),
	summonersResolve("Summoner's Resolve", 1, 0, 0, 1),
	resistance("Resistance", 1, 0, 1, 3),
	hardiness("Hardiness", 1, 0, 2, 3),
	toughSkin("Tough Skin", 1, 0, 3, 2),
	durability("Durability", 1, 1, 1, 4),
	vigor("Vigor", 1, 1, 2, 3),
	indomitable("Indomitable", 1, 2, 0, 2),
	veteransScars("Veteran's Scars", 1, 2, 1, 1, true),
	evasion("Evasion", 1, 2, 2, 3),
	bladedArmor("Bladed Armor", 1, 2, 3, 1, true),
	siegeCommander("Siege Commander", 1, 3, 0, 1),
	initiator("Initiator", 1, 3, 1, 3),
	enlightenment("Enlightenment",1, 3, 2, 3),
	honorGuard("Honor Guard", 1, 4, 1, 3),
	mercenary("Mercenary", 1, 4, 2, 3),
	juggernaut("Juggernaut", 1, 5, 1, 1),
	summonersInsight("Summoner's Insight", 2, 0, 0, 1),
	goodHands("Good Hands", 2, 0, 1, 3),
	expandedMind("Expanded Mind", 2, 0, 2, 3),
	improvedRecall("Improved Recall", 2, 0, 3, 1),
	swiftness("Swiftness", 2, 1, 1, 4),
	meditation("Meditation", 2, 1, 2, 3, true),
	scout("Scout", 2, 1, 3, 1),
	greed("Greed", 2, 2, 1, 4),
	transmutation("Transmutation", 2, 2, 2, 3),
	runicAffinity("Runic Affinity", 2, 2, 3, 1),
	wealth("Wealth", 2, 3, 1, 2, true),
	awareness("Awareness", 2, 3, 2, 4),
	sage("Sage", 2, 3, 3, 1),
	strengthOfSpirit("Strength of Spirit", 2, 4, 1, 3),
	intelligence("Intelligence", 2, 4, 2, 3),
	mastermind("Mastermind", 2, 5, 2, 1);
	
	
	private int tree;	//0 = offense, 1 = defense, 2 = utility
	private int tier;	//0-5
	private int spot;	//0-3
	private int maxPoints;
	private int points;
	private boolean dependant;
	private String name;
	private String description;
	private ChampionStats stats;
	Mastery(String name, int tree, int tier, int spot, int maxPoints)
	{
		this.name = name;
		this.tier = tier;
		this.tree = tree;
		this.spot = spot;
		this.maxPoints = maxPoints;
		this.points = 0;
		this.dependant = false;
	}
	Mastery(String name, int tree, int tier, int spot, int maxPoints, boolean dependant)
	{
		this.name = name;
		this.tier = tier;
		this.tree = tree;
		this.spot = spot;
		this.maxPoints = maxPoints;
		this.points = 0;
		this.dependant = true;
	}
}
