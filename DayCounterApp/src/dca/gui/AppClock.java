package dca.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.UIManager;

/**
*	AppClock Class Extends JPanel Implements Runnable
*	This class is a JPanel with a constant redrawing of
*	the current time.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/
public class AppClock extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
/**
*	This is the main thread for the redraw and recalculation of the date.
**/	Thread runner;

/**
*	This variable stores the date as a String and can be returned.
**/	private String time = null;

/**
*	The constructor method starts the redraw thread.
**/	AppClock()
	{
		if(runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}

/**
*	This method runs the redraw method once every second.
**/	public void run()
	{
		while(true) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Interrupted Thread.");
			}
		}
	}

/**
*	This method paints the time text and is called by the
*	running method every second.
**/	public void paintComponent(Graphics comp)
	{
		Graphics2D comp2D = (Graphics2D) comp;
		Font type = UIManager.getDefaults().getFont("Label.font");
		comp2D.setFont(type);
		comp2D.setColor(getBackground());
		comp2D.fillRect(0, 0, getSize().width, getSize().height);
		GregorianCalendar day = new GregorianCalendar();
		time = day.getTime().toString();
		comp2D.setColor(Color.black);
		comp2D.drawString(time, 54, 19);
	}
	
/**
*	This method can return the current time as a String.
*	@return String time - returns the current time.
**/	public String getTime()
	{
		return time;
	}
}