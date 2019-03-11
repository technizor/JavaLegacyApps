package dsa.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dsa.com.Constants;
import dsa.enums.ElementProperties;

public class IOPanel extends JPanel
{
	private final static long serialVersionUID = 1L;
	
	private GridBagLayout gridbag = new GridBagLayout();
	private JTextField inField = new JTextField(25);
	private JTextField outField = new JTextField(25);
	JButton inButton = new JButton();
	JButton outButton = new JButton();
	private JLabel inLabel = new JLabel();
	private JLabel outLabel = new JLabel();
	JButton submit = new JButton();
	JButton rank = new JButton();
	
	public IOPanel()
	{
		buildDefaultElements();
		setDefaultLabelToolTip();
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	private void buildDefaultElements()
	{
		buildElement(inLabel, 0, 0, 1, 1, 0, 10, 0, 0);
		buildElement(inField, 1, 0, 1, 1, 0, 0, 0, 0);
		buildElement(inButton, 2, 0, 1, 1, 0, 0, 0, 0);
		buildElement(outLabel, 0, 1, 1, 1, 0, 10, 0, 0);
		buildElement(outField, 1, 1, 1, 1, 0, 0, 0, 0);
		buildElement(outButton, 2, 1, 1, 1, 0, 0, 0, 0);
		buildElement(rank, 0, 2,	3, 1,	0, 0,	0, 0);
		buildElement(submit, 0, 3,	3, 1,	0, 0,	0, 0);
	}
	private void setDefaultLabelToolTip()
	{
		inLabel.setText(ElementProperties.inLabel.getLabel());
		inLabel.setToolTipText(ElementProperties.inLabel.getToolTip());
		inField.setText(ElementProperties.inField.getLabel());
		inField.setToolTipText(ElementProperties.inField.getToolTip());
		inButton.setText(ElementProperties.inButton.getLabel());
		inButton.setToolTipText(ElementProperties.inButton.getToolTip());
		outLabel.setText(ElementProperties.outLabel.getLabel());
		outLabel.setToolTipText(ElementProperties.outLabel.getToolTip());
		outField.setText(ElementProperties.outField.getLabel());
		outField.setToolTipText(ElementProperties.outField.getToolTip());
		outButton.setText(ElementProperties.outButton.getLabel());
		outButton.setToolTipText(ElementProperties.outButton.getToolTip());
		submit.setText(ElementProperties.submit.getLabel());
		submit.setToolTipText(ElementProperties.submit.getToolTip());
		rank.setText(ElementProperties.rank.getLabel());
		rank.setToolTipText(ElementProperties.rank.getToolTip());
	}
	
	
	private void buildElement(final JComponent obj, final int gx, final int gy,
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
	public String[] getFilePaths()
	{
		String[] vals = new String[2];
		vals[0] = inField.getText().trim();
		vals[1] = outField.getText().trim();
		return vals;
	}
	public void setFilePaths(final int a, final String b)
	{
		final String c = b.trim();
		switch(a) {
		case 1:
			inField.setText(c);
			outField.setText(c.substring(0, c.length()-4) + ".out.csv");
			break;
		case 2:
			outField.setText(c);
			break;
		}
	}
}
