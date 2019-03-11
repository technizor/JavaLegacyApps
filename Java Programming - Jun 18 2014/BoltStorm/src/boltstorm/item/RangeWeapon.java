package boltstorm.item;

public class RangeWeapon extends WeaponItem
{
	public RangeWeapon(String itemName) {
		super(itemName, WeaponItem.RANGE, 1, 100, 1, 1);
	}
	public RangeWeapon(String itemName, int weaponDmg, int weaponPow) {
		super(itemName, WeaponItem.RANGE, weaponDmg, weaponPow, 1, 1);
	}
	public RangeWeapon(String itemName, int weaponDmg, int weaponPow, int weaponDur, int weaponCrit) {
		super(itemName, WeaponItem.RANGE, weaponDmg, weaponPow, weaponDur, weaponCrit);
	}

}
