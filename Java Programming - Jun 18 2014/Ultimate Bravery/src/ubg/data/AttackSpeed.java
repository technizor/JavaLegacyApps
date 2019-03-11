package ubg.data;

public class AttackSpeed extends ItemCategory
{
	public AttackSpeed()
	{
		setName("Attack Speed");
		setList(itemList);
	}
	private Item[] itemList = {
			ItemEnum.theBlackCleaver.getItem(),
			ItemEnum.guinsoosRageblade.getItem(),
			ItemEnum.ionicSpark.getItem(),
			ItemEnum.malady.getItem(),
			ItemEnum.witsEnd.getItem(),
			ItemEnum.kitaesBloodrazor.getItem(),
			ItemEnum.theLightbringer.getItem(),
			ItemEnum.nashorsTooth.getItem(),
			ItemEnum.phantomDancer.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.youmuusGhostblade.getItem(),
			ItemEnum.madredsBloodrazor.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
