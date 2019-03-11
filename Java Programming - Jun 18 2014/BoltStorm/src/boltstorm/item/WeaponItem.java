package boltstorm.item;

public class WeaponItem extends Item
{
	public static final int MELEE = 1337;
	public static final int RANGE = 360;
	public static final int MAGIC = 9001;
	
	private int weaponType;
	private int weaponDmg;
	private int weaponPow;
	private int weaponDur;
	private int weaponCrit;
	
	public WeaponItem(String itemName, int weaponType) {
		super(itemName);
		setWeaponType(weaponType);
		setWeaponDmg(1);
		setWeaponPow(100);
		setWeaponDur(1);
		setWeaponCrit(1);
	}
	public WeaponItem(String itemName, int weaponType, int weaponDmg, int weaponPow) {
		super(itemName);
		setWeaponType(weaponType);
		setWeaponDmg(weaponDmg);
		setWeaponPow(weaponPow);
		setWeaponDur(1);
		setWeaponCrit(1);
	}
	public WeaponItem(String itemName, int weaponType, int weaponDmg, int weaponPow, int weaponDur, int weaponCrit) {
		super(itemName);
		setWeaponType(weaponType);
		setWeaponDmg(weaponDmg);
		setWeaponPow(weaponPow);
		setWeaponDur(weaponDur);
		setWeaponCrit(weaponCrit);
	}

	public int getWeaponType()
	{
		return weaponType;
	}

	public void setWeaponType(int weaponType)
	{
		this.weaponType = weaponType;
	}

	public int getWeaponPow()
	{
		return weaponPow;
	}

	public void setWeaponPow(int weaponPow)
	{
		this.weaponPow = weaponPow;
	}

	public int getWeaponDmg()
	{
		return weaponDmg;
	}

	public void setWeaponDmg(int weaponDmg)
	{
		this.weaponDmg = weaponDmg;
	}

	public int getWeaponCrit()
	{
		return weaponCrit;
	}

	public void setWeaponCrit(int weaponCrit)
	{
		this.weaponCrit = weaponCrit;
	}

	public int getWeaponDur()
	{
		return weaponDur;
	}

	public void setWeaponDur(int weaponDur)
	{
		this.weaponDur = weaponDur;
	}
	
}
