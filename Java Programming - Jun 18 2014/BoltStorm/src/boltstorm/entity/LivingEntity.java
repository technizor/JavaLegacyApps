package boltstorm.entity;

import java.awt.geom.Point2D;

public class LivingEntity extends Entity
{
	private int health;
	private int maxHealth;
	private EntityOffense offense;
	private EntityDefense defense;
	
	public LivingEntity(Point2D.Double entityPos, int entityId)
	{
		super(entityPos, entityId);
		setHealth(100);
		setMaxHealth(100);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(Point2D.Double entityPos, double entityAngle, int entityId)
	{
		super(entityPos, entityAngle, entityId);
		setHealth(100);
		setMaxHealth(100);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(int entityX, int entityY, int entityId)
	{
		super(new Point2D.Double(entityX, entityY), entityId);
		setHealth(100);
		setMaxHealth(100);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(int entityX, int entityY, double entityAngle, int entityId)
	{
		super(new Point2D.Double(entityX, entityY), entityAngle, entityId);
		setHealth(100);
		setMaxHealth(100);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(Point2D.Double entityPos, int health, int maxHealth, int entityId)
	{
		super(entityPos, entityId);
		setHealth(health);
		setMaxHealth(maxHealth);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(Point2D.Double entityPos, double entityAngle, int health, int maxHealth, int entityId)
	{
		super(entityPos, entityAngle, entityId);
		setHealth(health);
		setMaxHealth(maxHealth);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(int entityX, int entityY, int health, int maxHealth, int entityId)
	{
		super(new Point2D.Double(entityX, entityY), entityId);
		setHealth(health);
		setMaxHealth(maxHealth);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	public LivingEntity(int entityX, int entityY, double entityAngle, int health, int maxHealth, int entityId)
	{
		super(new Point2D.Double(entityX, entityY), entityAngle, entityId);
		setHealth(health);
		setMaxHealth(maxHealth);
		setOffense(new EntityOffense());
		setDefense(new EntityDefense());
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public EntityDefense getDefense()
	{
		return defense;
	}
	public void setDefense(EntityDefense defense)
	{
		this.defense = defense;
	}
	public EntityOffense getOffense()
	{
		return offense;
	}
	public void setOffense(EntityOffense offense)
	{
		this.offense = offense;
	}

}
