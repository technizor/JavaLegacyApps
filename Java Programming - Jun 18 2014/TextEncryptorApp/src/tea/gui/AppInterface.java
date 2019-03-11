package tea.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import tea.com.TextEncryptorEnum;


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
	
	MaskFormatter formatter;
	
/**
*	This is the grid bag layout used by this JPanel.
**/	private GridBagLayout gridbag = new GridBagLayout();

	InPanel in = new InPanel();
	JTextField key = new JTextField();
	JLabel keyLabel = new JLabel();
	JTextArea log = new JTextArea();
	JScrollPane logPane = new JScrollPane(log);
	JFormattedTextField num;
	JLabel numLabel = new JLabel();
	OutPanel out = new OutPanel();
	MaskFormatter pass;
	/**
	*	This is a label that shows the name of the program.
	**/	JLabel title = new JLabel();
	/**
	*	This is an instance of the JPanel holding the window control components.
	*	It will appear in the top right corner of the application.
	**/	AppWindowControl windowButtons = new AppWindowControl();

/**
*	This constructor calls other methods to initialize components.
**/	public AppInterface()
	{
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		key.setDocument(new JTextFieldLimit(16));
		log.setEditable(false);
		format();
		logPane.setPreferredSize(new Dimension(400, 45));
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
			obj.putClientProperty(TextEncryptorEnum.sizeVari, TextEncryptorEnum.compSize);
			this.add(obj);
		}
	
/**
*	This method call a builder method to generate settings
*	for each component.
**/	private void buildElements()
	{
		buildElement(title, 		0, 0, 	7, 1, 	0, 0, 	1, 0);
		buildElement(windowButtons,	7, 0,	1, 1,	0, 0,	0, 0);
		buildElement(keyLabel, 		0, 1, 	1, 1,	0, 0, 	0, 0);
		buildElement(key, 			1, 1, 	3, 1, 	0, 0, 	0, 0);
		buildElement(numLabel, 		4, 1, 	1, 1,	0, 0, 	0, 0);
		buildElement(num, 			5, 1, 	3, 1, 	0, 0, 	0, 0);
		buildElement(in,			0, 2,	4, 1,	0, 0,	0, 0);
		buildElement(out,			4, 2,	4, 1,	0, 0,	0, 0);
		buildElement(logPane,		0, 3,	8, 1,	0, 0,	0, 0);
	}
	
private void format()
{
	try {
		formatter = new MaskFormatter(TextEncryptorEnum.PINMASK);
		formatter.setAllowsInvalid(false);
		formatter.setPlaceholderCharacter(TextEncryptorEnum.PINSPACER);
		num = new JFormattedTextField(formatter);
	} catch (Exception e) {
		
	}
}
	
/**
*	This method sets up the tool tips and labels of the  
*	components based on information from the DayCounterEnum
*	class.
**/	private void setDefaultLabelToolTip()
	{
		title.setText(TextEncryptorEnum.elementProperties.title.getLabel());
		title.setToolTipText(TextEncryptorEnum.elementProperties.title.getToolTip());
		numLabel.setText(TextEncryptorEnum.elementProperties.numLabel.getLabel());
		numLabel.setToolTipText(TextEncryptorEnum.elementProperties.numLabel.getToolTip());
		keyLabel.setText(TextEncryptorEnum.elementProperties.keyLabel.getLabel());
		keyLabel.setToolTipText(TextEncryptorEnum.elementProperties.keyLabel.getToolTip());
		num.setToolTipText(TextEncryptorEnum.elementProperties.num.getToolTip());
		key.setToolTipText(TextEncryptorEnum.elementProperties.key.getToolTip());
	}
}
