package boltstorm.entity;

public class EntityDefense
{
	private int magicFlat;
	private int magicPercent;
	private int meleeFlat;
	private int meleePercent;
	private int rangeFlat;
	private int rangePercent;
	
	public EntityDefense()
	{
		setRangePercent(0);
		setMeleePercent(0);
		setMagicPercent(0);
		setRangeFlat(0);
		setMeleeFlat(0);
		setMagicFlat(0);
	}
	
	public int getMagicDmg(int damage, int magicPenFlat, int magicPenPercent)
	{
		return (damage/(100 + (magicFlat-magicPenFlat)* magicPenPercent/100))*(100-magicPercent)/100;
	}
	public int getMeleeDmg(int damage, int meleePenFlat, int meleePenPercent)
	{
		return (damage/(100 + (meleeFlat-meleePenFlat)* meleePenPercent/100))*(100-meleePercent)/100;
	}
	public int getRangeDmg(int damage, int rangePenFlat, int rangePenPercent)
	{
		return (damage/(100 + (rangeFlat-rangePenFlat)* rangePenPercent/100))*(100-rangePercent)/100;
	}
	
	public int getRangePercent()
	{
		return rangePercent;
	}
	public void setRangePercent(int rangePercent)
	{
		this.rangePercent = rangePercent;
	}
	public int getMeleePercent()
	{
		return meleePercent;
	}
	public void setMeleePercent(int meleePercent)
	{
		this.meleePercent = meleePercent;
	}
	public int getMagicPercent()
	{
		return magicPercent;
	}
	public void setMagicPercent(int magicPercent)
	{
		this.magicPercent = magicPercent;
	}
	public int getMagicFlat()
	{
		return magicFlat;
	}
	public void setMagicFlat(int magicFlat)
	{
		this.magicFlat = magicFlat;
	}
	public int getMeleeFlat()
	{
		return meleeFlat;
	}
	public void setMeleeFlat(int meleeFlat)
	{
		this.meleeFlat = meleeFlat;
	}
	public int getRangeFlat()
	{
		return rangeFlat;
	}
	public void setRangeFlat(int rangeFlat)
	{
		this.rangeFlat = rangeFlat;
	}
}
