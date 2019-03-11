package boltstorm.entity;

import java.awt.geom.Point2D;

import boltstorm.item.Inventory;


public class PlayerEntity extends LivingEntity
{
	private int playerId;
	private Inventory playerInv;
	public PlayerEntity(Point2D.Double playerPos, int playerId)
	{
		super(playerPos, playerId);
		setPlayerId(playerId);
		playerInv = new Inventory();
	}
	public PlayerEntity(int playerX, int playerY, int playerId)
	{
		super(playerX, playerY, playerId);
		setPlayerId(playerId);
		playerInv = new Inventory();
	}

	public int getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(int playerId)
	{
		this.playerId = playerId;
	}
	public Inventory getPlayerInv()
	{
		return playerInv;
	}
	public void setPlayerInv(Inventory playerInv)
	{
		this.playerInv = playerInv;
	}
}
