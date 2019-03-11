package dsa.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dsa.com.Constants;

public class LogPanel extends JPanel
{
	private final static long serialVersionUID = 1L;
	JTextArea log = new JTextArea();
	private JScrollPane logPane = new JScrollPane(log);

	private GridBagLayout gridbag = new GridBagLayout();
	public LogPanel()
	{
		buildDefaultElements();
		log.setEditable(false);
		logPane.setPreferredSize(new Dimension(400, 165));
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	private void buildDefaultElements()
	{
		buildElement(logPane, 0, 0, 1, 1, 0, 10, 0, 0);
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
	public void appendLog(final String str)
	{
		log.append(str);
		log.setCaretPosition(log.getDocument().getLength());
	}
}
