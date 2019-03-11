package boltstorm.item;

public class MagicWeapon extends WeaponItem
{
	public MagicWeapon(String itemName) {
		super(itemName, WeaponItem.MAGIC, 1, 100, 1, 1);
	}
	public MagicWeapon(String itemName, int weaponDmg, int weaponPow) {
		super(itemName, WeaponItem.MAGIC, weaponDmg, weaponPow, 1, 1);
	}
	public MagicWeapon(String itemName, int weaponDmg, int weaponPow, int weaponDur, int weaponCrit) {
		super(itemName, WeaponItem.MAGIC, weaponDmg, weaponPow, weaponDur, weaponCrit);
	}

}
