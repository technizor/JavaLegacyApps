package dsa.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dsa.com.Constants;
import dsa.enums.ElementProperties;



/**
*	AppInterface Class extends JPanel
*	This class contains all visual components of the GUI of
*	this application. The DayCounterApp class handles all
*	events caused by the components within this JPanel.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0715
**/

public class AppInterface extends JPanel
{
	private final static long serialVersionUID = 1L;
/**
*	This is the grid bag layout used by this JPanel.
**/	private GridBagLayout gridbag = new GridBagLayout();

/**
*	This is a label that shows the name of the program.
**/	JLabel title = new JLabel();
/**
*	This is an instance of the JPanel holding the window control components.
*	It will appear in the top right corner of the application.
**/	AppWindowControl windowButtons = new AppWindowControl();
	TabPanel tabPane = new TabPanel();

/**
*	This constructor calls other methods to initialize components.
**/	public AppInterface()
	{
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		buildElements();
		setDefaultLabelToolTip();
	}


/**
*	This method is the builder method that creates the components.
*
*	@param obj - represents the component that it is setting up
*	@param gx - Grid Position X
*	@param gy - Grid Position Y
*	@param gw - Grid spaces taken X
*	@param gh - Grid spaces taken Y
*	@param wx - Grid weight X
*	@param wy - Grid weight Y
*	@param fill - 0: Both - 1: None - 2: Horizontal - 3: Vertical
*	@param alignment - 0: Center - 1: North - 2: NE - 3: East - 4: SE - 5: South - 6: SW - 7: West - 8: NW
**/	private void buildElement(final JComponent obj, final int gx, final int gy,
		final int gw, final int gh, final int wx, final int wy,  final int fill, 
		final int alignment)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weightx = wy;
		switch(fill){
			case 0:
				gbc.fill = GridBagConstraints.BOTH;
				break;
			case 1:
				gbc.fill = GridBagConstraints.NONE;
				break;
			case 2:
				gbc.fill = GridBagConstraints.HORIZONTAL;
				break;
			case 3:
				gbc.fill = GridBagConstraints.VERTICAL;
				break;
		}
		switch(alignment){
		case 0:
			gbc.anchor = GridBagConstraints.CENTER;
			break;
		case 1:
			gbc.anchor = GridBagConstraints.NORTH;
			break;
		case 2:
			gbc.anchor = GridBagConstraints.NORTHEAST;
			break;
		case 3:
			gbc.anchor = GridBagConstraints.EAST;
			break;
		case 4:
			gbc.anchor = GridBagConstraints.SOUTHEAST;
			break;
		case 5:
			gbc.anchor = GridBagConstraints.SOUTH;
			break;
		case 6:
			gbc.anchor = GridBagConstraints.SOUTHWEST;
			break;
		case 7:
			gbc.anchor = GridBagConstraints.WEST;
			break;
		case 8:
			gbc.anchor = GridBagConstraints.NORTHWEST;
			break;
		}
		gridbag.setConstraints(obj, gbc);
		obj.putClientProperty(Constants.sizeVari, Constants.compSize);
		this.add(obj);
	}
	
/**
*	This method call a builder method to generate settings
*	for each component.
**/	private void buildElements()
	{
		buildElement(title, 		0, 0, 	2, 1, 	0, 0, 	1, 0);
		buildElement(windowButtons,	3, 0,	1, 1,	0, 0,	1, 3);
		buildElement(tabPane,		0, 1,	4, 1,	0, 0,	1, 0);
	}
	
/**
*	This method sets up the tool tips and labels of the  
*	components based on information from the DayCounterEnum
*	class.
**/	private void setDefaultLabelToolTip()
	{
		title.setText(ElementProperties.title.getLabel());
		title.setToolTipText(ElementProperties.title.getToolTip());
	}
}
