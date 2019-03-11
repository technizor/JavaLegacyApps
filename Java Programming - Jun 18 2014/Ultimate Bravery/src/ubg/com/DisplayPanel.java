package ubg.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import ubg.res.ImageRes;

public class DisplayPanel extends JPanel implements MouseMotionListener, Runnable
{
	private static final Dimension size = new Dimension(320, 450);
	private static final long serialVersionUID = -1875018572937660033L;
	UltimateBraveryGen generator;
	Image layout;
	Image maxQ;
	Image maxW;
	Image maxE;
	Image dominion;
	Image classic;
	Image provingGrounds;
	Image randomChamp;
	
	private final int champIconX = 20;
	private final int champIconY = 50;
	private final int champIconS = 120;
	private final int summonerIconX = 143;
	private final int summonerIconY1 = 49;
	private final int summonerIconY2 = 111;
	private final int summonerIconS = 60;
	private final int itemIconY = 174;
	private final int itemIconX2 = 216;
	private final int itemIconY2 = 68;
	private final int itemIconS = 64;
	
	private int mouseX;
	private int mouseY;
	public boolean draw;
	public DisplayPanel(UltimateBraveryGen ultimateBravery)
	{
		generator = ultimateBravery;
		draw = true;
		layout = ImageRes.loadImage("layout.png");
		maxQ = ImageRes.loadImage("maxQ.png");
		maxW = ImageRes.loadImage("maxW.png");
		maxE = ImageRes.loadImage("maxE.png");
		dominion = ImageRes.loadImage("dominion.png");
		classic = ImageRes.loadImage("classic.png");
		provingGrounds = ImageRes.loadImage("proving grounds.png");
		randomChamp = ImageRes.loadImage("Random.png");
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setSize(size);
		this.addMouseMotionListener(this);
	}
	public void paint(Graphics g)
	{
		Image screen = createImage(size.width, size.height);
		if(screen == null) return;
		Graphics2D g2D = (Graphics2D) screen.getGraphics();
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, size.width, size.height);
		Image champIcon = generator.getChampion().getIcon();
		Image summonerIcon1 = generator.summonerSpells[0].getIcon();
		Image summonerIcon2 = generator.summonerSpells[1].getIcon();
		Font font = g2D.getFont();
		Font font2 = new Font(font.getName(), 30, 20);
		g2D.setFont(font2);
		g2D.drawImage(champIcon, champIconX, champIconY, null);
		g2D.drawImage(summonerIcon1, summonerIconX, summonerIconY1, null);
		g2D.drawImage(summonerIcon2, summonerIconX, summonerIconY2, null);
		for(int i = 0; i < 6; i++) {
			g2D.drawImage(generator.itemBuild[i].getIcon(), champIconX+((i%2)*itemIconX2), 
					itemIconY+((i/2)*itemIconY2), null);
		}
		g2D.setColor(Color.WHITE);
		if(mouseX >= champIconX && mouseY >= champIconY && mouseX < champIconX+champIconS 
				&& mouseY < champIconY+champIconS) {
			Rectangle2D rect2D = g2D.getFontMetrics().getStringBounds(generator.championName, g2D);
			g2D.drawString(generator.championName, (int)(160-rect2D.getWidth()/2), (int)(50-rect2D.getHeight()/2));
			//this.setToolTipText(generator.championName);
		} else if(mouseX >= summonerIconX && mouseY >= summonerIconY1 && mouseX < summonerIconX+summonerIconS
				&& mouseY < summonerIconY1+summonerIconS) {
			Rectangle2D rect2D = g2D.getFontMetrics().getStringBounds(generator.summonerSpells[0].getName(), g2D);
			g2D.drawString(generator.summonerSpells[0].getName(), (int)(160-rect2D.getWidth()/2), (int)(50-rect2D.getHeight()/2));
			//this.setToolTipText(generator.summonerSpells[0].getName());
		} else if(mouseX >= summonerIconX && mouseY >= summonerIconY2 && mouseX < summonerIconX+summonerIconS
				&& mouseY < summonerIconY2+summonerIconS) {
			Rectangle2D rect2D = g2D.getFontMetrics().getStringBounds(generator.summonerSpells[1].getName(), g2D);
			g2D.drawString(generator.summonerSpells[1].getName(), (int)(160-rect2D.getWidth()/2), (int)(50-rect2D.getHeight()/2));
			//this.setToolTipText(generator.summonerSpells[1].getName());
		} else {
			for(int i = 0; i < 6; i++)
			{
				if(mouseX >= champIconX + ((i%2) *itemIconX2) && 
						mouseY >= itemIconY + ((i/2) * itemIconY2)
						&& mouseX < champIconX + ((i%2) *itemIconX2) + itemIconS 
						&& mouseY < itemIconY + ((i/2) * itemIconY2) + itemIconS) {
					Rectangle2D rect2D = g2D.getFontMetrics().getStringBounds(generator.itemBuild[i].getName(), g2D);
					g2D.drawString(generator.itemBuild[i].getName(), (int)(160-rect2D.getWidth()/2), (int)(50-rect2D.getHeight()/2));
					//this.setToolTipText(generator.itemBuild[i].getName());
					break;
				}
			}
		}
		g2D.drawImage(layout, 0, 0, null);
		if(generator.gamemode%3 == 0) {
			g2D.drawImage(classic, 207, 51, null);
		} else if(generator.gamemode%3 == 1) {
			g2D.drawImage(dominion, 207, 51, null);
		} else if(generator.gamemode%3 == 2) {
			g2D.drawImage(provingGrounds, 207, 51, null);
		}
		if(generator.primarySkill%3 == 0) {
			g2D.drawImage(maxQ, 207, 113, null);
		} else if(generator.primarySkill%3 == 1) {
			g2D.drawImage(maxW, 207, 113, null);
		} else if(generator.primarySkill%3 == 2) {
			g2D.drawImage(maxE, 207, 113, null);
		}
		//207,51  207,113
		g.drawImage(screen, 0, 0, null);
	}
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}
	@Override
	public void run()
	{
		boolean running = true;
		while(running)
		{
			if(draw) {
				this.paint(this.getGraphics());
				this.update(this.getGraphics());
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				running = false;
				e.printStackTrace();
			}
		}
	}
}
