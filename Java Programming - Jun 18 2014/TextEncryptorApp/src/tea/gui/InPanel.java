package tea.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tea.com.TextEncryptorEnum;

public class InPanel extends JPanel
{
	private final static long serialVersionUID = 1L;
	
	JButton encryptIt = new JButton();
	GridBagLayout gridbag = new GridBagLayout();
	JButton inFile = new JButton();
	JButton outFile = new JButton();
	JTextArea inText = new JTextArea();
	JLabel inTextLabel = new JLabel();
	JScrollPane scrollPane = new JScrollPane(inText);
	
	public InPanel()
	{
		this.setLayout(gridbag);
		scrollPane.setPreferredSize(new Dimension(250,150));
		buildDefaultElements();
		setDefaultLabelToolTip();
	}
	
	private void buildDefaultElements()
	{
		buildElement(inTextLabel, 	0, 1, 2, 1, 0, 0, 1, 0);
		buildElement(scrollPane, 	0, 2, 2, 1, 0, 0, 0, 0);
		buildElement(outFile,		0, 3, 2, 1, 0, 0, 0, 0);
		buildElement(inFile,		0, 4, 2, 1, 0, 0, 0, 0);
		buildElement(encryptIt, 	0, 5, 2, 1, 0, 0, 0, 0);
	}
	
	private void buildElement(JComponent obj, int gx, int gy, int gw, int gh, int wx, int wy,  int fill, int alignment)
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
		this.add(obj);
	}
	private void setDefaultLabelToolTip()
	{
		inTextLabel.setText(TextEncryptorEnum.elementProperties.inTextLabel.getLabel());
		inTextLabel.setToolTipText(TextEncryptorEnum.elementProperties.inTextLabel.getToolTip());
		inFile.setText(TextEncryptorEnum.elementProperties.inFile.getLabel());
		inFile.setToolTipText(TextEncryptorEnum.elementProperties.inFile.getToolTip());
		outFile.setText(TextEncryptorEnum.elementProperties.outFile.getLabel());
		outFile.setToolTipText(TextEncryptorEnum.elementProperties.outFile.getToolTip());
		encryptIt.setText(TextEncryptorEnum.elementProperties.encryptIt.getLabel());
		encryptIt.setToolTipText(TextEncryptorEnum.elementProperties.encryptIt.getToolTip());
	}
}
