package lolutil.data.item;

public class ItemCategory
{
	public static final ItemCategory magicItem = new ItemCategory("Magic");
	public static final ItemCategory defenseItem = new ItemCategory("Defense");
	public static final ItemCategory attackItem = new ItemCategory("Attack");
	public static final ItemCategory movementItem = new ItemCategory("Movement");
	public static final ItemCategory consumableItem = new ItemCategory("Consumables");
	public static final ItemCategory abilityPowerItem = new ItemCategory("Ability Power", magicItem);
	public static final ItemCategory manaRegenItem = new ItemCategory("Mana Regen", magicItem);
	public static final ItemCategory cooldownReductionItem = new ItemCategory("Cooldown Reduction", magicItem);
	public static final ItemCategory manaItem = new ItemCategory("Mana", magicItem);
	public static final ItemCategory damageItem = new ItemCategory("Damage", attackItem);
	public static final ItemCategory lifeStealItem = new ItemCategory("Life Steal", attackItem);
	public static final ItemCategory attackSpeedItem = new ItemCategory("Attack Speed", attackItem);
	public static final ItemCategory criticalStrikeItem = new ItemCategory("Critical Strike", attackItem);
	public static final ItemCategory armorItem = new ItemCategory("Armor", defenseItem);
	public static final ItemCategory magicResistItem = new ItemCategory("Magic Resist", defenseItem);
	public static final ItemCategory healthItem = new ItemCategory("Health", defenseItem);
	public static final ItemCategory healthRegenItem = new ItemCategory("Health Regen", defenseItem);
	private String name;
	private ItemCategory parent;
	public ItemCategory(String name)
	{
		this.name = name;
	}
	public ItemCategory(String name, ItemCategory parent)
	{
		this.name = name;
		this.parent = parent;
	}
	public String toString()
	{
		if(parent != null) {
			return parent.toString() + ">" + name;
		}
		return name;
	}
}
