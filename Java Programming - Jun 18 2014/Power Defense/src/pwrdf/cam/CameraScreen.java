package pwrdf.cam;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import pwrdf.map.Map;

public class CameraScreen extends JPanel
{
	private static final long serialVersionUID = -424885338402465798L;
	public CameraScreen(int i, int j) {
		this.setMinimumSize(new Dimension(i, j));
		this.setMaximumSize(new Dimension(i, j));
		this.setPreferredSize(new Dimension(i, j));
		this.setSize(200, 200);
	}
	public void paint(Graphics g, CameraRoom room, Map map, boolean showCursor)
	{
		Image image = createImage(room.getViewW(), room.getViewH());
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.drawImage(map.render(room.getViewX(), room.getViewY(), room.getViewW(), room.getViewH(), room.getSelectX(), room.getSelectY(), showCursor), 0, 0, null);
		g.drawImage(image, 0, 0, this);
	}
}
