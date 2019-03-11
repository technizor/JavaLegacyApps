package dca.com;

import dca.gui.AppWindow;

/**
*	AppLaunch Class
*	As suggested by it name, this is the class that launches the
*	entire DayCounter Application, creating a DayCounterApp object
*	that acts as the main window for the application. All the GUI
*	building and formatting is handled by the object itself.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

public class AppLaunch
{
/**
*	A simple method, this generates an invisible window object
*	then displays it.
*
*	@param arguments - not used, but this maybe used in the future to set look and feel.
**/	public static void main(final String[] arguments)
	{
		AppWindow mainFrame = new AppWindow();
		mainFrame.setVisible(true);
	}
}