package ubg.data;

public class Cooldown extends ItemCategory
{
	public Cooldown()
	{
		setName("Cooldown Reduction");
		setList(itemList);
	}
	private Item[] itemList = {
			ItemEnum.mejaisSoulstealer.getItem(),
			ItemEnum.athenesUnholyGrail.getItem(),
			ItemEnum.deathfireGrasp.getItem(),
			ItemEnum.frozenHeart.getItem(),
			ItemEnum.locketOfTheIronSolari.getItem(),
			ItemEnum.morellosEvilTome.getItem(),
			ItemEnum.nashorsTooth.getItem(),
			ItemEnum.randuinsOmen.getItem(),
			ItemEnum.shurelyasReverie.getItem(),
			ItemEnum.soulShroud.getItem(),
			ItemEnum.spiritVisage.getItem(),
			ItemEnum.youmuusGhostblade.getItem(),
			ItemEnum.zekesHerald.getItem(),
			ItemEnum.hextechSweeper.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
