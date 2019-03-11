package pwrdf.entity;

public class Enemy extends Entity
{
	private int slowDuration;
	private double baseSpeed;
	private double moveSpeed;
	private double atkDamage;
	private long health;
	private long maxHealth;
	public Enemy(double posX, double posY, long maxHealth, double moveSpeed, double damage) {
		super(posX, posY);
		this.maxHealth = this.health = maxHealth;
		this.setMoveSpeed(moveSpeed);
		this.setDamage(damage);
		baseSpeed = moveSpeed;
	}
	public void slow(double percentage, int duration) {
		moveSpeed *= percentage/100;
		slowDuration = Math.max(duration, slowDuration);
	}
	public void resetSpeed()
	{
		moveSpeed = baseSpeed;
	}
	public void tick()
	{
		if(slowDuration > 0) {
			slowDuration--;
		} else {
			resetSpeed();
		}
		move();
	}
	public void move() {}
	
	public long getHealth()
	{
		return health;
	}

	public void setHealth(long health)
	{
		this.health = health;
	}

	public long getMaxHealth()
	{
		return maxHealth;
	}

	public void setMaxHealth(long maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	public long damageEnemy(long damage)
	{
		this.health -= damage;
		return health;
	}
	public long healEnemy(long heal)
	{
		this.health += heal;
		return health;
	}

	public double getMoveSpeed()
	{
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed)
	{
		this.moveSpeed = moveSpeed;
	}
	public double getDamage()
	{
		return atkDamage;
	}

	public void setDamage(double damage)
	{
		this.atkDamage = damage;
	}

}
