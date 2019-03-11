package pwrdf.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pwrdf.map.Map;

public class GameScreen extends JPanel implements Runnable
{
	private static final long serialVersionUID = 515819521677473685L;
	private Map map;
	public boolean running;
	public static void main(final String[] args)
	{
		JFrame window = new JFrame("Test");
		window.add(new GameScreen());
		window.setVisible(true);
	}
	public GameScreen()
	{
		map = new Map("Test", 30, 30, Map.NONLINEAR);
		run();
	}
	@Override
	public void run()
	{
		running = true;
		while(running) {
			paint(getGraphics());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void paint(Graphics g)
	{
		Image img = createImage(getWidth(), getHeight());
		Graphics2D g2D = (Graphics2D) g;
	}
}
