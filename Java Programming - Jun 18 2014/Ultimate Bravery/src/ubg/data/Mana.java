package ubg.data;

public class Mana extends ItemCategory
{
	public Mana()
	{
		setName("Mana and Mana Regen");
		setList(itemList);
	}
	private Item[] itemList = {
			//ItemEnum.doransRing.getItem(),
			//ItemEnum.prospectorsRing.getItem(),
			ItemEnum.athenesUnholyGrail.getItem(),
			ItemEnum.eleisasMiracle.getItem(),
			ItemEnum.frozenHeart.getItem(),
			ItemEnum.lichBane.getItem(),
			ItemEnum.manamune.getItem(),
			ItemEnum.morellosEvilTome.getItem(),
			ItemEnum.rodOfAges.getItem(),
			ItemEnum.shurelyasReverie.getItem(),
			ItemEnum.soulShroud.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.bansheesVeil.getItem(),
			ItemEnum.odynsVeil.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
