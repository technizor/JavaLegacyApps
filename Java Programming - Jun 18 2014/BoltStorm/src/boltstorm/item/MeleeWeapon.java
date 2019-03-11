package boltstorm.item;

public class MeleeWeapon extends WeaponItem
{
	public MeleeWeapon(String itemName) {
		super(itemName, WeaponItem.MELEE, 1, 100, 1, 1);
	}
	public MeleeWeapon(String itemName, int weaponDmg, int weaponPow) {
		super(itemName, WeaponItem.MELEE, weaponDmg, weaponPow, 1, 1);
	}
	public MeleeWeapon(String itemName, int weaponDmg, int weaponPow, int weaponDur, int weaponCrit) {
		super(itemName, WeaponItem.MELEE, weaponDmg, weaponPow, weaponDur, weaponCrit);
	}

}
