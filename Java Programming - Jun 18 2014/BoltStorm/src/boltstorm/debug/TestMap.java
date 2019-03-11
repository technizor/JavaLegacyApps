package boltstorm.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boltstorm.entity.BulletEntity;
import boltstorm.gui.Game;
import boltstorm.map.Map;
import boltstorm.map.Tile;

public class TestMap extends JPanel implements Runnable
{
	private static final long serialVersionUID = -7772310693320144303L;
	Map map;
	Thread runner;
	boolean shot;
	int gunType = 0;
	public static final int GAMEX = 1024;
	public static final int GAMEY = 768;
	public static final int GUNX = 129;
	//0 = shotgun, 1 = assault rifle
	public static void main(String[] args)
	{
        JFrame f = new JFrame("Physics Engine Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TestMap());
        f.setSize(GAMEX,GAMEY);
        f.setLocation(200,0);
        f.setVisible(true);
	}
	public TestMap()
	{
		map = new TestingMap(GAMEX, GAMEY);
		if(runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2D.setColor(Color.black);
		g2D.fillRect(0, 0, getWidth(), getHeight());
		Tile[][] tiles = map.getTileset();
		g2D.setColor(Color.cyan);
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				if(tiles[i][j].isSolid()) {
					g2D.fillRect(i*Map.TILESCALE, j*Map.TILESCALE, Map.TILESCALE, Map.TILESCALE);
				}
			}
		}
		g2D.setColor(Color.lightGray);
		for(int i = 0; i < map.getEntityList().size(); i++)
		{
			g2D.fillRect((int)(map.getEntityList().get(i).getEntityX()-2), (int)(map.getEntityList().get(i).getEntityY()-1), 4, 2);
		}
		g2D.setColor(Color.darkGray);
		g2D.fillRect(GUNX-22, Game.Y/2-2, 23, 4);
		g2D.fillRect(GUNX-22, Game.Y/2-2, 3, 8);
		if(shot) {
			g2D.setColor(Color.orange);
			int[] temp1 = {GUNX+1,GUNX+6, GUNX+4, GUNX+10, GUNX+4, GUNX+6, GUNX+1};
			int[] temp2 = {Game.Y/2-2, Game.Y/2-5, Game.Y/2-2, Game.Y/2, Game.Y/2+2, Game.Y/2+5, Game.Y/2+2};
			g2D.fillPolygon(temp1, temp2, 7);
		}
	}
	
	public void run()
	{
		boolean running = true;
		int tickCount = 0;
		while(running)
		{
			shot = false;
			if(gunType == 0) {
				if(tickCount % 15 == 0) {
					Random rand = new Random();
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount, 0, 0, 
							null, 5, (1.0 + rand.nextInt(15)/100), 1, (rand.nextInt(15)/2-15)));
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount+1, 0, 0, 
							null, 5, (1.0 + rand.nextInt(15)/100), 1, (rand.nextInt(15)/2-7.5)));
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount+2, 0, 0, 
							null, 5, (1.0 + rand.nextInt(15)/100), 1, (rand.nextInt(15)/2)));
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount+3, 0, 0, 
							null, 5, (1.0 + rand.nextInt(15)/100), 1, (rand.nextInt(15)/2+7.5)));
					shot = true;
				}
			} else if(gunType == 1)
			{
				if(tickCount % 10 == 0 || tickCount % 10 == 1 || tickCount % 10 == 2) {
					Random rand = new Random();
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount, 0, 0, 
							null, 15, (1.1 + rand.nextInt(5)/100), 1, (rand.nextInt(15)/2-3.75)));
					shot = true;
				}
			} else if(gunType == 2)
			{
				if(tickCount % 25 == 0) {
					Random rand = new Random();
					map.addEntity(new BulletEntity(new Point2D.Double(GUNX, Game.Y/2), tickCount, 0, 0, 
							null, 50, (1 + rand.nextInt(2)/100), 1, (rand.nextInt(3)/2-0.75)));
					shot = true;
				}
			}
			if(tickCount % 150 == 149) {
				gunType = (gunType+1) %  3;
				System.out.println("Switching gun!");
			}
			map.tick();
			tickCount++;
			this.repaint();
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				running  = false;
				e.printStackTrace();
			}
		}
	}
}
