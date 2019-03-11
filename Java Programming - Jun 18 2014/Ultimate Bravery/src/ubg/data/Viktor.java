package ubg.data;

public class Viktor extends ItemCategory
{
	public Viktor()
	{
		setName("Ability Power and Magic Penetration");
		setList(itemList);
	}
	private Item[] itemList = {
			ItemEnum.augmentPower.getItem(),
			ItemEnum.augmentGravity.getItem(),
			ItemEnum.augmentDeath.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
