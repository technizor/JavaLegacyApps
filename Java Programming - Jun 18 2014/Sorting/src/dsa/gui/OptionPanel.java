package dsa.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;

import dsa.com.Constants;
import dsa.com.Global;
import dsa.enums.ElementProperties;
import dsa.enums.SortInfo;

public class OptionPanel extends JPanel implements ItemListener, ChangeListener
{
	private final static long serialVersionUID = 1L;
	private GridBagLayout gridbag = new GridBagLayout();
	private JLabel sortLabel = new JLabel();
	private JLabel rowLabel = new JLabel();
	private JLabel columnLabel = new JLabel();
	private JLabel sortByLabel = new JLabel();
	private JSpinner sortColumn = new JSpinner();
	private JSpinner columnSize = new JSpinner();
	private JSpinner rowSize = new JSpinner();
	private SpinnerModel rowModel;
	private SpinnerModel columnModel;
	private SpinnerModel sortColumnModel;
	private JCheckBox headerCheck = new JCheckBox();
	private JCheckBox addHeaders = new JCheckBox();
	private JCheckBox enableTable = new JCheckBox();
	JComboBox sortSelect = new JComboBox();
	JTextArea sort = new JTextArea();
	private JScrollPane sortPane = new JScrollPane(sort);
	
	
	public OptionPanel()
	{
		buildElements();
		setDefaultLabelToolTip();
		configureDefaults();
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
/**
*	This method call a builder method to generate settings
*	for each component.
**/	private void buildElements()
	{
		buildElement(sortLabel,		0, 0,	1, 1,	0, 0,	0, 0);
		buildElement(sortSelect,	1, 0,	1, 1,	0, 0,	0, 0);
		buildElement(sortPane,		2, 0,	1, 6,	0, 0,	0, 0);
		buildElement(columnLabel,	0, 1,	1, 1,	0, 0,	0, 0);
		buildElement(columnSize,	1, 1,	1, 1,	0, 0,	0, 0);
		buildElement(rowLabel,		0, 2,	1, 1,	0, 0,	0, 0);
		buildElement(rowSize,		1, 2,	1, 1,	0, 0,	0, 0);
		buildElement(sortByLabel,	0, 3,	1, 1,	0, 0,	0, 0);
		buildElement(sortColumn,	1, 3,	1, 1,	0, 0,	0, 0);
		buildElement(headerCheck,	0, 4,	1, 1,	0, 0,	0, 0);
		buildElement(addHeaders,	1, 4,	1, 1,	0, 0,	0, 0);
		buildElement(enableTable,	0, 5,	2, 1,	0, 0,	0, 0);
	}

	private void setDefaultLabelToolTip()
	{
		sortSelect.setToolTipText(ElementProperties.sortSelect.getToolTip());
		headerCheck.setText(ElementProperties.headerCheck.getLabel());
		headerCheck.setToolTipText(ElementProperties.headerCheck.getToolTip());
		addHeaders.setText(ElementProperties.addHeaders.getLabel());
		addHeaders.setToolTipText(ElementProperties.addHeaders.getToolTip());
		enableTable.setText(ElementProperties.enableTable.getLabel());
		enableTable.setToolTipText(ElementProperties.enableTable.getToolTip());
		sortLabel.setText(ElementProperties.sortLabel.getLabel());
		sortLabel.setToolTipText(ElementProperties.sortLabel.getToolTip());
		columnLabel.setText(ElementProperties.columnLabel.getLabel());
		columnLabel.setToolTipText(ElementProperties.columnLabel.getToolTip());
		rowLabel.setText(ElementProperties.rowLabel.getLabel());
		rowLabel.setToolTipText(ElementProperties.rowLabel.getToolTip());
		columnSize.setToolTipText(ElementProperties.columnSize.getToolTip());
		rowSize.setToolTipText(ElementProperties.rowSize.getToolTip());
		sortColumn.setToolTipText(ElementProperties.sortColumn.getToolTip());
		sortByLabel.setText(ElementProperties.sortByLabel.getLabel());
		sortByLabel.setToolTipText(ElementProperties.sortByLabel.getToolTip());
	}

	private void configureDefaults()
	{
		for(final SortInfo a : SortInfo.values()) sortSelect.addItem(a.getName());
		sortSelect.setSelectedIndex(4);
		rowModel = new SpinnerNumberModel(256, 1, 65536, 1);
		columnModel = new SpinnerNumberModel(16, 1, 256, 1);
		sortColumnModel = new SpinnerNumberModel(1, 1, 16, 1);
		rowSize.setModel(rowModel);
		columnSize.setModel(columnModel);
		columnSize.addChangeListener(this);
		headerCheck.addItemListener(this);
		addHeaders.addItemListener(this);
		enableTable.addItemListener(this);
		sortColumn.setModel(sortColumnModel);
		sortPane.setPreferredSize(new Dimension(150, 165));
		sort.setEditable(false);
		sort.setText(SortInfo.LSDRadix.getInfo());
		addHeaders.setSelected(true);
		headerCheck.setSelected(true);
		enableTable.setSelected(true);
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
	public int[] getDataSettings()
	{
		int[] vals = new int[4];
		vals[0] = Integer.parseInt(rowSize.getValue().toString());
		vals[1] = Integer.parseInt(columnSize.getValue().toString());
		vals[2] = Integer.parseInt(sortColumn.getValue().toString())-1;
		vals[3] = sortSelect.getSelectedIndex()+1;
		return vals;
	}
	public void stateChanged(final ChangeEvent ce)
	{
		int a = Integer.parseInt(sortColumn.getValue().toString());
		final int b = Integer.parseInt(columnModel.getValue().toString());
		a = (a > b) ? b : a;
		sortColumnModel = new SpinnerNumberModel(a, 1, b, 1);
		sortColumn.setModel(sortColumnModel);
	}

	public void itemStateChanged(final ItemEvent e)
	{
		final Object source = e.getSource();
		if(source == headerCheck) {
			Global.inHeader = (e.getStateChange() == ItemEvent.DESELECTED) ? false : true;
		} else if (source == addHeaders) {
			Global.outHeader = (e.getStateChange() == ItemEvent.DESELECTED) ? false : true;
		} else if (source == enableTable) {
			Global.loadTable = (e.getStateChange() == ItemEvent.DESELECTED) ? false : true;
		}
	}
}
