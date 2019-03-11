package boltstorm.entity;

import java.awt.geom.Point2D;

public class BulletEntity extends ProjectileEntity
{

	public BulletEntity(Point2D.Double startPos, int entityId, int projectileDmg, int projectileType,
			EntityOffense offense, double speed, double speedDecay, int duration, double entityAngle) {
		super(startPos, entityId, projectileDmg, projectileType, offense, speed, speedDecay, duration, 2, 1, entityAngle);
	}

}
