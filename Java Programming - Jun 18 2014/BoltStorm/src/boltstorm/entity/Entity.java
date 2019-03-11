package boltstorm.entity;

import java.awt.geom.Point2D;

import boltstorm.map.Map;

public class Entity
{
	private Point2D.Double entityPos;
	private int entityId;
	private double entityAngle;
	private double speed;
	private int width;
	private int height;
	private Hitbox hitbox;
	private boolean isSolid = false;
	
	public Entity(Point2D.Double entityPos, int entityId)
	{
		setEntityPos(entityPos);
		setEntityAngle(0);
		setEntityId(entityId);
		setHitbox(new Hitbox(entityPos, 8, 8));
	}
	public Entity(Point2D.Double entityPos, double entityAngle, int entityId)
	{
		setEntityPos(entityPos);
		setEntityAngle(entityAngle);
		setEntityId(entityId);
		setHitbox(new Hitbox(entityPos, 8, 8));
	}
	public Entity(Point2D.Double entityPos, int width, int height, int entityId)
	{
		setEntityPos(entityPos);
		setEntityAngle(entityAngle);
		setEntityId(entityId);
		setHitbox(new Hitbox(entityPos, width, height));
	}
	public double getEntityAngle()
	{
		return entityAngle;
	}
	public void setEntityAngle(double entityAngle)
	{
		this.entityAngle = entityAngle;
	}
	public Point2D.Double getEntityPos()
	{
		return entityPos;
	}
	public double getEntityX()
	{
		return entityPos.getX();
	}
	public double getEntityY()
	{
		return entityPos.getY();
	}
	public void setEntityPos(Point2D.Double entityPos)
	{
		this.entityPos = entityPos;
	}
	public void setEntityPos(double posX, double posY)
	{
		this.entityPos.setLocation(posX, posY);
	}
	public void move(double xOffset, double yOffset)
	{
		this.entityPos.setLocation(this.entityPos.getX() + xOffset, this.entityPos.getY() + yOffset);
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public Hitbox getHitbox()
	{
		return hitbox;
	}
	public void setHitbox(Hitbox hitbox)
	{
		this.hitbox = hitbox;
	}
	public void move(double distance, Map map)
	{
		Point2D.Double point = getPoint(distance);
		Point2D.Double collisionPt = canMove(distance, map);
		if(collisionPt == null) 
			setEntityPos(point);
		else if(this instanceof ProjectileEntity) {
			ProjectileEntity proj = (ProjectileEntity) this;
			if(proj.getBounceCount() > 0) {
				proj.bounce();
			} else {
				proj.setSpeed(0);
			}
			Point2D.Double bouncePoint = getPoint(distance);
			setEntityPos(bouncePoint);
		}
	}
	public Point2D.Double getPoint(double distance)
	{
		Point2D.Double point = getEntityPos();
		double theta = Math.toRadians(entityAngle);
		point.x = entityPos.x + distance * Math.cos(theta);
		point.y = entityPos.y + distance * Math.sin(theta);
		// (distance = (pointx-entityposx)/mathcostheta
		return point;
	}
	public double getDistance(Point2D.Double point)
	{
		double theta = Math.toRadians(entityAngle);
		double distance = (point.x - entityPos.x)/Math.cos(theta);
		return distance;
	}

	public double getIntersectY(double posX)
	{
		double theta = Math.toRadians(entityAngle);
		double distance = (posX- entityPos.x) / Math.cos(theta);
		double posY = entityPos.y + distance * Math.sin(theta);
		return posY;
	}
	public double getIntersectX(double posY)
	{
		double theta = Math.toRadians(entityAngle);
		double distance = (posY- entityPos.y);
		double dist = distance/ Math.sin(theta);
		double posX = entityPos.x + dist * Math.cos(theta);
		return posX;
	}
	public Point2D.Double canMove(double distance, Map map)
	{
		for(double i = (Map.TILESCALE > distance ? distance/10 : Map.TILESCALE/10); i < distance; i += (Map.TILESCALE > distance ? distance/10 : Map.TILESCALE/10))
		{
			Point2D.Double point = getPoint(i);
			if(map.getTileAt(point.getX(),
					point.getY()) != null) {
				if(map.getTileAt(point.getX(),
						point.getY()).isSolid()) {
					return point;
				}
			}
		}
		Point2D.Double point2 = getPoint(distance);
		if(map.getTileAt(point2.getX(),	point2.getY()) != null) {
			if(map.getTileAt(point2.getX(),	point2.getY()).isSolid()) {
				return point2;
			}
		}
		return null;
	}
	public double getDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1));
	}
	public boolean isSolid()
	{
		return isSolid;
	}
	public void setSolid(boolean isSolid)
	{
		this.isSolid = isSolid;
	}
	public int getEntityId()
	{
		return entityId;
	}
	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}
	public void tick(Map map)
	{
		this.move(getSpeed(), map);
	}
	public double getSpeed()
	{
		return speed;
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
}
