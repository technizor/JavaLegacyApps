package dca.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dca.com.DayCounterEnum;


/**
*	AppInterface Class extends JPanel
*	This class contains all visual components of the GUI of
*	this application. The DayCounterApp class handles all
*	events caused by the components within this JPanel.
*
*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0711
**/

public class AppInterface extends JPanel
{
	private final static long serialVersionUID = 1L;
	
/**
*	This is the grid bag layout used by this JPanel.
**/	private GridBagLayout gridbag = new GridBagLayout();

/**
*	This is an instance of the JPanel containing the redrawn clock.
*	This shares its location with the result bar in the bottom right.
**/	AppClock currentTime = new AppClock();

/**
*	This is a button used to trigger a submission event that causes
*	the DayCounter to calculate the difference of days.
*	It appears on the very right.
**/	JButton submit = new JButton();

/**
*	This is a button labeled for the second date, and will display the second
*	date as text.
*	It appears below the fromLabel button on the left.
**/	JButton toLabel = new JButton();

/**
*	This is a button labeled for the first date, and will display the first
*	date as text.
*	It appears above the toLabel button on the left.
**/	JButton fromLabel = new JButton();

/**
*	This is a button labeled as "Current Time", and triggers the
*	display of the clock component.
*	This component is near the top on the left column.
**/	JButton currentTimeLabel = new JButton();

/**
*	A button that displays the current year.
**/	JButton yearLabel = new JButton();

/**
*	A button that displays the current month.
**/	JButton monthLabel = new JButton();

/**
*	A button that displays the current day and day of the week.
**/	JButton dayLabel = new JButton();

/**
*	This is a label that shows the name of the program.
**/	JLabel title = new JLabel();

/**
*	A button that clears the result display.
**/	JButton resultLabel = new JButton();

/**
*	A JTextField that displays the results.
**/	JTextField result = new JTextField();

/**
*	This is a JCombobox for the first date.
**/	JComboBox<String> monthBox1 = new JComboBox<String>();

/**
*	This is a JCombobox for the second date.
**/	JComboBox<String> monthBox2 = new JComboBox<String>();

/**
*	This is a JCombobox for the first date.
**/	JComboBox<String> yearBox1 = new JComboBox<String>();

/**
*	This is a JCombobox for the second date.
**/	JComboBox<String> yearBox2 = new JComboBox<String>();

/**
*	This is a JCombobox for the first date.
**/	JComboBox<String> dayBox1 = new JComboBox<String>();

/**
*	This is a JCombobox for the second date.
**/	JComboBox<String> dayBox2 = new JComboBox<String>();
	
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
*	This method call a builder method to generate settings
*	for each component.
**/	private void buildElements()
	{
		buildElement(currentTimeLabel, 	0, 0, 	1, 1, 	0, 15, 	3, 3);
		buildElement(monthLabel, 		1, 0, 	1, 1, 	0, 0, 	0, 0);
		buildElement(dayLabel, 			2, 0, 	1, 1, 	0, 0, 	0, 0);
		buildElement(yearLabel, 		3, 0, 	1, 1, 	0, 0, 	0, 0);
		buildElement(fromLabel, 		0, 1, 	1, 1, 	20, 25, 0, 0);
		buildElement(monthBox1, 		1, 1, 	1, 1, 	20, 0, 	0, 0);
		buildElement(dayBox1, 			2, 1, 	1, 1, 	30, 0, 	0, 0);
		buildElement(yearBox1, 			3, 1, 	1, 1, 	30, 0, 	0, 0);
		buildElement(submit, 			4, 0, 	1, 3, 	0, 25, 	0, 0);
		buildElement(toLabel, 			0, 2, 	1, 1, 	0, 25, 	0, 0);
		buildElement(monthBox2, 		1, 2, 	1, 1, 	0, 0, 	0, 0);
		buildElement(dayBox2, 			2, 2, 	1, 1, 	0, 0, 	0, 0);
		buildElement(yearBox2, 			3, 2, 	1, 1, 	0, 0, 	0, 0);
		buildElement(resultLabel, 		0, 3, 	1, 1, 	0, 0, 	0, 0);
		buildElement(result, 			1, 3, 	4, 1, 	0, 0, 	0, 0);
		buildElement(currentTime,		1, 3, 	4, 1, 	0, 0, 	0, 0);
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
**/	private void buildElement(JComponent obj, int gx, int gy, int gw, int gh, int wx, int wy,  int fill, int alignment)
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
		obj.putClientProperty(DayCounterEnum.sizeVari, DayCounterEnum.compSize);
		this.add(obj);
	}
	
/**
*	This method sets up the tool tips and labels of the  
*	components based on information from the DayCounterEnum
*	class.
**/	private void setDefaultLabelToolTip()
	{
		title.setText(DayCounterEnum.elementProperties.title.getLabel());
		title.setToolTipText(DayCounterEnum.elementProperties.title.getToolTip());
		currentTime.setToolTipText(DayCounterEnum.elementProperties.currentTime.getToolTip());
		submit.setText(DayCounterEnum.elementProperties.submit.getLabel());
		submit.setToolTipText(DayCounterEnum.elementProperties.submit.getToolTip());
		result.setToolTipText(DayCounterEnum.elementProperties.result.getToolTip());
		currentTimeLabel.setText(DayCounterEnum.elementProperties.currentTimeLabel.getLabel());
		currentTimeLabel.setToolTipText(DayCounterEnum.elementProperties.currentTimeLabel.getToolTip());
		fromLabel.setText(DayCounterEnum.elementProperties.fromLabel.getLabel());
		fromLabel.setToolTipText(DayCounterEnum.elementProperties.fromLabel.getToolTip());
		toLabel.setText(DayCounterEnum.elementProperties.toLabel.getLabel());
		toLabel.setToolTipText(DayCounterEnum.elementProperties.toLabel.getToolTip());
		resultLabel.setText(DayCounterEnum.elementProperties.resultLabel.getLabel());
		resultLabel.setToolTipText(DayCounterEnum.elementProperties.resultLabel.getToolTip());
		monthBox1.setToolTipText(DayCounterEnum.elementProperties.monthBox1.getToolTip());
		monthBox2.setToolTipText(DayCounterEnum.elementProperties.monthBox2.getToolTip());
		yearBox1.setToolTipText(DayCounterEnum.elementProperties.yearBox1.getToolTip());
		yearBox2.setToolTipText(DayCounterEnum.elementProperties.yearBox2.getToolTip());
		dayBox1.setToolTipText(DayCounterEnum.elementProperties.dayBox1.getToolTip());
		dayBox2.setToolTipText(DayCounterEnum.elementProperties.dayBox2.getToolTip());
		yearLabel.setText(DayCounterEnum.elementProperties.yearLabel.getLabel());
		yearLabel.setToolTipText(DayCounterEnum.elementProperties.yearLabel.getToolTip());
		monthLabel.setText(DayCounterEnum.elementProperties.monthLabel.getLabel());
		monthLabel.setToolTipText(DayCounterEnum.elementProperties.monthLabel.getToolTip());
		dayLabel.setText(DayCounterEnum.elementProperties.dayLabel.getLabel());
		dayLabel.setToolTipText(DayCounterEnum.elementProperties.dayLabel.getToolTip());
	}
}
