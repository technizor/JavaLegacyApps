package pwrdf.entity;

import java.awt.geom.Point2D;

public abstract class Entity
{
	private Point2D.Double position;
	public Entity(double posX, double posY) {
		setPosition(new Point2D.Double(posX, posY));
	}
	public abstract void move();
	public Point2D.Double getPosition()
	{
		return position;
	}
	public void setPosition(Point2D.Double position)
	{
		this.position = position;
	}
}
