package ubg.data;

public class Boots extends ItemCategory
{
	public Boots()
	{
		setName("Boots");
		setList(itemList);
	}
	private Item[] itemList = {
			ItemEnum.berserkersGreaves.getItem(),
			ItemEnum.bootsOfMobility.getItem(),
			ItemEnum.bootsOfSwiftness.getItem(),
			ItemEnum.ionianBootsOfLucidity.getItem(),
			ItemEnum.mercurysTreads.getItem(),
			ItemEnum.ninjaTabi.getItem(),
			ItemEnum.sorcerersShoes.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
