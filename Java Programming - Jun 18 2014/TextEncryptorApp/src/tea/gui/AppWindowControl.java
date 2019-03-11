package tea.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import tea.com.TextEncryptorEnum;

/**
*	AppWindowControl Class extends JPanel
*	This class is a simple JPanel containing all the window
*	control components, a.k.a. the minimize and close button.
*	The maximize/restore button does not appear due to the
*	disabling of sizing this application.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0715
**/

public class AppWindowControl extends JPanel
{
	final private static long serialVersionUID = 1L;	
	
/**
*	This button closes the window.
**/	protected JButton close = new JButton();

/**
*	This is the grid layout that holds the two buttons.
**/	private GridLayout grid = new GridLayout(1, 2);

/**
*	This button minimizes the window, and is to the left of the close button.
**/	protected JButton minimize = new JButton();
	
/**
*	The object constructor, this creates the JPanel containing
*	the window control buttons, sets their properties, and is
*	then displayed by the main application class.
**/	public AppWindowControl()
	{
		this.setLayout(grid);
		close.putClientProperty(TextEncryptorEnum.sizeVari, TextEncryptorEnum.controlSize);
		minimize.putClientProperty(TextEncryptorEnum.sizeVari, TextEncryptorEnum.controlSize);
		close.setToolTipText(TextEncryptorEnum.buttonProperties.close.getToolTip());
		minimize.setToolTipText(TextEncryptorEnum.buttonProperties.minimize.getToolTip());
		close.setText(TextEncryptorEnum.buttonProperties.close.getLabel());
		minimize.setText(TextEncryptorEnum.buttonProperties.minimize.getLabel());
		this.add(minimize);
		this.add(close);
	}
}
