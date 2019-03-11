package mazer.map;

public class Tile
{
	final String[] TILE_IDS = {
		"Normal", "Wall", "Void", "Brake",
		"Checkpoint", "Start", "Finish", "Teleporter",
		"Ice", "Booster", "Jumper", "Rotator Booster", 
		"Rotator Jumper", "Rotator Trigger"
	};
	private int tileId;
	private int metaData;
	// Holds (rotator) booster/jumper orientation and direction of trigger
	private int triggerId;
	// Holds event ids for linking event targets
	
	public Tile (int tileId, int metaData, int triggerId)
	{
		setTileId(tileId);
		setMetaData(metaData);
		setTriggerId(triggerId);
	}

	public int getTileId()
	{
		return tileId;
	}

	private void setTileId(int tileId)
	{
		this.tileId = tileId;
	}

	public int getMetaData()
	{
		return metaData;
	}

	public void setMetaData(int metaData)
	{
		this.metaData = metaData;
	}

	public int getTriggerId()
	{
		return triggerId;
	}

	public void setTriggerId(int triggerId)
	{
		this.triggerId = triggerId;
	}
}
