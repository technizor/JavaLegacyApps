import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
 
public class TrigTest extends JPanel {
	private static final long serialVersionUID = -8678788995650687614L;
	Point2D.Double center = new Point2D.Double(200, 175);
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.blue);
        g2.fill(new Ellipse2D.Double(center.x-2, center.y-2, 4, 4));
        g2.setPaint(Color.green.darker());
        Point2D.Double p = getPoint(-45.0, 200.0);
        g2.fill(new Ellipse2D.Double(p.x-2, p.y-2, 4, 4));
        g2.setPaint(Color.red);
        p = getPoint(60.0, 150.0);
        g2.fill(new Ellipse2D.Double(p.x-2, p.y-2, 4, 4));
    }
 
    private Point2D.Double getPoint(double angle, double distance) {
        // Angles in java are measured clockwise from 3 o'clock.
        double theta = Math.toRadians(angle);
        Point2D.Double p = new Point2D.Double();
        p.x = center.x + distance*Math.cos(theta);
        p.y = center.y + distance*Math.sin(theta);
        return p;
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TrigTest());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}