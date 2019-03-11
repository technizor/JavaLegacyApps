package boltstorm.item;

public enum WeaponEnum {
	P90("P90", WeaponItem.RANGE, 20, 20, 1, 5);
	private WeaponItem weapon;
	WeaponEnum(String itemName, int weaponType, int weaponDmg, int weaponPow, int weaponDur, int weaponCrit)
	{
		weapon = new WeaponItem(itemName, weaponType, weaponDmg, weaponPow, weaponDur, weaponCrit);
	}
	public WeaponItem getWeapon()
	{
		return weapon;
	}
}
