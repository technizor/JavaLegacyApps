package boltstorm.entity;

import java.awt.geom.Point2D;

import boltstorm.map.Map;

public class ProjectileEntity extends Entity
{
	private int projectileDmg;
	private int projectileType;
	private EntityOffense offense;
	private double speedDecay;
	private int duration;
	private int bounceCount;
	private int targetPen;
	private int moveCount;
	
	public ProjectileEntity(Point2D.Double startPos, int entityId, int projectileDmg, int projectileType, EntityOffense offense, 
			double speed, double speedDecay, int duration, int maxBounce, int maxTargetPen, double entityAngle)
	{
		super(startPos, entityAngle, entityId);
		setProjectileDmg(projectileDmg);
		setProjectileType(projectileType);
		setOffense(offense);
		setSpeed(speed);
		setSpeedDecay(speedDecay);
		setDuration(duration);
		setBounceCount(maxBounce);
		setTargetPen(maxTargetPen);
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public double getSpeedDecay()
	{
		return speedDecay;
	}

	public void setSpeedDecay(double speedDecay)
	{
		this.speedDecay = speedDecay;
	}

	public EntityOffense getOffense()
	{
		return offense;
	}

	public void setOffense(EntityOffense offense)
	{
		this.offense = offense;
	}

	public int getProjectileDmg()
	{
		return projectileDmg;
	}

	public void setProjectileDmg(int projectileDmg)
	{
		this.projectileDmg = projectileDmg;
	}

	public int getProjectileType()
	{
		return projectileType;
	}

	public void setProjectileType(int projectileType)
	{
		this.projectileType = projectileType;
	}

	public int getBounceCount()
	{
		return bounceCount;
	}

	public void setBounceCount(int bounceCount)
	{
		this.bounceCount = bounceCount;
	}

	public int getTargetPen()
	{
		return targetPen;
	}

	public void setTargetPen(int targetPen)
	{
		this.targetPen = targetPen;
	}
	public void tick(Map map)
	{
		super.tick(map);
		moveCount++;
		if(moveCount % 5 == 0) this.setSpeed(getSpeed()/getSpeedDecay());
	}

	public void bounce()
	{
		if(bounceCount < 1) {
			setSpeed(0);
			return;
		}
		double radAngle = Math.toRadians(getEntityAngle());
		double xComp = Math.cos(radAngle);
		double yComp = Math.sin(radAngle);
		setEntityAngle(Math.toDegrees(Math.atan2(yComp, -xComp)));
		setBounceCount(getBounceCount()-1);
		double decay = getSpeedDecay();
		setSpeed(getSpeed()/(getSpeedDecay()*2));
		setSpeedDecay(getSpeedDecay()+decay-1);
		System.out.println("Bounce!");
	}
}