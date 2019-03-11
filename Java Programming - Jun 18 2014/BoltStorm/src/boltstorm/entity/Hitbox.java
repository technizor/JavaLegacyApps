package boltstorm.entity;

import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Hitbox extends Polygon
{
	private static final long serialVersionUID = -851609615407217939L;
	public Hitbox(int[] xPoints, int[] yPoints, int n)
	{
		super(xPoints, yPoints, n);
	}
	public Hitbox(Point2D entityPos, int width, int height) {
		int xPos = (int) entityPos.getX();
		int yPos = (int) entityPos.getY();
		int[] x = {xPos-width/2, xPos+width/2, xPos+width/2, xPos-width/2};
		int[] y = {yPos-height/2, yPos-height/2, yPos+height/2, yPos+height/2};
		for(int i = 0; i < 4; i++)
		{
			this.addPoint(x[i], y[i]);
		}
	}
}
