package pwrdf.entity;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import pwrdf.map.Tower;

public class Projectile extends Entity
{
	private Tower parentTower;
	private Enemy targetEnemy;
	private double speed;
	private int range;
	private Point2D.Double targetLocation;
	public Projectile(double posX, double posY) {
		super(posX, posY);
	}
	public void setTarget(Enemy targetEnemy) {
		this.targetEnemy = targetEnemy;
	}
	@Override
	public void move()
	{
		if(targetEnemy != null) targetLocation = targetEnemy.getPosition();
		double x1 = getPosition().getX(); 
		double x2 = targetEnemy.getPosition().getX();
		double y1 = getPosition().getY(); 
		double y2 = targetEnemy.getPosition().getY();
		double angle = Math.atan2(y2-y1, x2-x1);
		double hypot = Math.hypot(x1-x2, y1-y2);
		if(speed > 0) {
			if(speed < hypot) {
				x1 += Math.cos(angle)*speed;
				y2 += Math.sin(angle)*speed;
			} else {
				x1 = x2;
				y1 = y2;
			}
			setPosition(x1, x2);
		}
		if(getPosition().equals(targetLocation)) {
			damageUnits(targetLocation);
		}
	}
	private void setPosition(double x, double y)
	{
		setPosition(new Point2D.Double(x, y));
	}
	private void damageUnits(Double targetLocation2)
	{
		if(targetEnemy != null) {
			targetEnemy.damageEnemy((int) parentTower.getStats().getDamage());
		}
		if(range > 0) {
			
		}
	}

}
