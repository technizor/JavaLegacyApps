package ubg.data;

public class Resist extends ItemCategory
{
	public Resist()
	{
		setName("Armor, Magic Resist and Tenacity");
		setList(itemList);
	}
	private Item[] itemList = {
			//ItemEnum.doransShield.getItem(),
			ItemEnum.aegisOfTheLegion.getItem(),
			ItemEnum.atmasImpaler.getItem(),
			ItemEnum.cloakAndDagger.getItem(),
			ItemEnum.forceOfNature.getItem(),
			ItemEnum.guardianAngel.getItem(),
			ItemEnum.moonflairSpellblade.getItem(),
			ItemEnum.quicksilverSash.getItem(),
			ItemEnum.sunfireCape.getItem(),
			ItemEnum.thornmail.getItem(),
			ItemEnum.witsEnd.getItem(),
			ItemEnum.zhonyasHourglass.getItem(),
			ItemEnum.leviathan.getItem(),
			ItemEnum.warmogsArmor.getItem(),
			ItemEnum.eleisasMiracle.getItem(),
			ItemEnum.frozenHeart.getItem(),
			ItemEnum.lichBane.getItem(),
			ItemEnum.locketOfTheIronSolari.getItem(),
			ItemEnum.mawOfMalmortius.getItem(),
			ItemEnum.randuinsOmen.getItem(),
			ItemEnum.bansheesVeil.getItem(),
			ItemEnum.wrigglesLantern.getItem(),
			ItemEnum.odynsVeil.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
