package dsa.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import dsa.com.Constants;
import dsa.com.Global;

public class TablePanel extends JPanel
{
	private final static long serialVersionUID = 1L;
	private String[] columnNames;
	@SuppressWarnings("unused")
	private Object[][] rowData;
	CustomTableModel tableModel;
	JTable table = new JTable(tableModel);
	private JScrollPane tablePane = new JScrollPane(table);

	private GridBagLayout gridbag = new GridBagLayout();
	public TablePanel()
	{
		buildDefaultElements();
		tablePane.setPreferredSize(new Dimension(441, 159));
		tablePane.setMaximumSize(new Dimension(441, 159));
		tablePane.setMinimumSize(new Dimension(441, 159));
		Dimension size = table.getPreferredScrollableViewportSize();
		table.setPreferredScrollableViewportSize(new Dimension(Math.min(getPreferredSize().width, size.width), size.height));
		table.setFillsViewportHeight(true);
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	ToolTipManager.sharedInstance().unregisterComponent(table);
		columnNames = new String[256];
		rowData = new Object[65536][256];
		for(int i = 1; i < 257; i++) columnNames[i-1] = "Column " + i;
		String[] a = {columnNames[0]};
		Object[][] b = {{""},{""},{""},{""},{""},{""},{""},{""}};
		tableModel = new CustomTableModel(b, a);
		table.setModel(tableModel);
		packColumns();
		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	private void buildDefaultElements()
	{
		buildElement(tablePane, 0, 0, 1, 1, 0, 10, 0, 0);
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

//	Sets the preferred width of the visible column specified by vColIndex. The column
//	will be just wide enough to show the column head and the widest cell in the column.
//	margin pixels are added to the left and right
//	(resulting in an additional width of 2*margin pixels).
	public void packColumns()
	{
		for (int c = 0; c < table.getColumnCount(); c++) packColumn(c);
	}
	public void packColumn(final int colIndex)
	{
		DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
		TableColumn col = colModel.getColumn(colIndex);
		int width = 0;
		int minWidth = 0;
		int maxWidth = 0;
//	Get width of column header
		TableCellRenderer renderer = col.getHeaderRenderer();
		if (renderer == null) renderer = table.getTableHeader().getDefaultRenderer();
		Component comp = renderer.getTableCellRendererComponent(
				table, col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width;
//	Get maximum width of column data
		for (int r = 0; r < Global.listHeight; r++) {
			renderer = table.getCellRenderer(r, colIndex);
			try {
				comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, colIndex), false, false, r, colIndex);
				width = Math.max(width, comp.getPreferredSize().width);
			} catch(Exception e) {
				width = Math.max(width, 100);
			}
		}
		tableModel.fireTableDataChanged();
		col.sizeWidthToFit();
		minWidth = (((table.getRowCount() > 8) ? 426 : 441)/table.getColumnCount()) > width ? ((table.getRowCount() > 8) ? 426 : 441)/table.getColumnCount() : width;
		maxWidth = (minWidth > width) ? minWidth : width;
//	Fixes the min, max, and preferred width to the greater of either the table
//	view width divided by columns, or the minimum width required to display 
//	every table element in that column
		col.setMinWidth(minWidth);
		col.setMaxWidth(maxWidth);
		if((maxWidth >= width) && (width >= minWidth)) col.setPreferredWidth(width);
		else col.setPreferredWidth(minWidth);
		if(Constants.systemOut) System.out.println(col.getMaxWidth() + "\t" + col.getPreferredWidth() + "\t" + col.getMinWidth() + "\t" + table.getAutoResizeMode() +
				"\t" + Global.listHeight + "x" + Global.listWidth);
	}
}
