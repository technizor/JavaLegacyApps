package ubg.data;

public class Health extends ItemCategory
{
	public Health()
	{
		setName("Health and Health Regen");
		setList(itemList);
	}
	private Item[] itemList = {
			//ItemEnum.doransShield.getItem(),
			//ItemEnum.doransBlade.getItem(),
			//ItemEnum.doransRing.getItem(),
			//ItemEnum.prospectorsBlade.getItem(),
			//ItemEnum.prospectorsRing.getItem(),
			ItemEnum.aegisOfTheLegion.getItem(),
			ItemEnum.forceOfNature.getItem(),
			ItemEnum.ionicSpark.getItem(),
			ItemEnum.rylaisCrystalScepter.getItem(),
			ItemEnum.sunfireCape.getItem(),
			ItemEnum.tiamat.getItem(),
			ItemEnum.leviathan.getItem(),
			ItemEnum.warmogsArmor.getItem(),
			ItemEnum.eleisasMiracle.getItem(),
			ItemEnum.frozenMallet.getItem(),
			ItemEnum.locketOfTheIronSolari.getItem(),
			ItemEnum.randuinsOmen.getItem(),
			ItemEnum.rodOfAges.getItem(),
			ItemEnum.shurelyasReverie.getItem(),
			ItemEnum.spiritVisage.getItem(),
			ItemEnum.soulShroud.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.zekesHerald.getItem(),
			ItemEnum.bansheesVeil.getItem(),
			ItemEnum.entropy.getItem(),
			ItemEnum.hextechSweeper.getItem(),
			ItemEnum.odynsVeil.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
