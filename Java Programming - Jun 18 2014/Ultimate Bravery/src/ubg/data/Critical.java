package ubg.data;

public class Critical extends ItemCategory
{
	public Critical()
	{
		setName("Critcal Strike");
		setList(itemList);
	}
	private Item[] itemList = {
			ItemEnum.atmasImpaler.getItem(),
			ItemEnum.cloakAndDagger.getItem(),
			ItemEnum.executionersCalling.getItem(),
			ItemEnum.infinityEdge.getItem(),
			ItemEnum.phantomDancer.getItem(),
			ItemEnum.trinityForce.getItem(),
			ItemEnum.youmuusGhostblade.getItem()
	};
	public void setItems()
	{
		this.setList(itemList);
	}

}
