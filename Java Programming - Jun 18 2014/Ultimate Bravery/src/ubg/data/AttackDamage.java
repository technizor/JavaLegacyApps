package ubg.data;

public class AttackDamage extends ItemCategory
{
	public AttackDamage()
	{
		setName("Attack Damage and Armor Penetration");
		setList(itemList);
	}
	private Item[] itemList = {
			//ItemEnum.doransBlade.getItem(),
			//ItemEnum.prospectorsBlade.getItem(),
			ItemEnum.atmasImpaler.getItem(),
			ItemEnum.theBlackCleaver.getItem(),
			ItemEnum.infinityEdge.getItem(),
			ItemEnum.lastWhisper.getItem(),
			ItemEnum.theBloodthirster.getItem(),
			ItemEnum.swordOfTheOccult.getItem(),
			ItemEnum.sanguineBlade.getItem(),
			ItemEnum.frozenMallet.getItem(),
			ItemEnum.hextechGunblade.getItem(),
			ItemEnum.manamune.getItem(),
			ItemEnum.mawOfMalmortius.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.youmuusGhostblade.getItem(),
			ItemEnum.kitaesBloodrazor.getItem(),
			ItemEnum.madredsBloodrazor.getItem(),
			ItemEnum.wrigglesLantern.getItem(),
			ItemEnum.entropy.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
