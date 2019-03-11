package ubg.data;

public class AbilityPower extends ItemCategory
{
	public AbilityPower()
	{
		setName("Ability Power and Magic Penetration");
		setList(itemList);
	}
	private Item[] itemList = {
			//ItemEnum.doransRing.getItem(),
			//ItemEnum.prospectorsRing.getItem(),
			ItemEnum.abyssalScepter.getItem(),
			ItemEnum.guinsoosRageblade.getItem(),
			ItemEnum.hauntingGuise.getItem(),
			ItemEnum.malady.getItem(),
			ItemEnum.moonflairSpellblade.getItem(),
			ItemEnum.rabadonsDeathcap.getItem(),
			ItemEnum.rylaisCrystalScepter.getItem(),
			ItemEnum.voidStaff.getItem(),
			ItemEnum.zhonyasHourglass.getItem(),
			ItemEnum.archangelsStaff.getItem(),
			ItemEnum.athenesUnholyGrail.getItem(),
			ItemEnum.deathfireGrasp.getItem(),
			ItemEnum.hextechGunblade.getItem(),
			ItemEnum.morellosEvilTome.getItem(),
			ItemEnum.nashorsTooth.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.willOfTheAncients.getItem(),
			ItemEnum.hextechSweeper.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
