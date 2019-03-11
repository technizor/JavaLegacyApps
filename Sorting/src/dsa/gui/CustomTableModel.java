package dsa.gui;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel
{
	private final static long serialVersionUID = 1L;
	String[] defaultColumnNames;
	String[] columnNames;
	Object[][] rowData;
	public String getColumnName(int col) {
		try {
			String a = columnNames[col].toString();
			return a;
		} catch(NullPointerException npe) {
			return "";
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			return "";
		}
	}
	public int getRowCount()
	{
		return rowData.length;
	}
	public int getColumnCount()
	{
		return columnNames.length;
	}
	public Object getValueAt(final int row, final int col)
	{
		try {
			final Object a = rowData[row][col];
			return a;
		}  catch(NullPointerException npe) {
			return "";
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			return "";
		}
	}
	public boolean isCellEditable(final int row, int col)
	{
		return false;
	}
	public void setValueAt(final Object value, final int row, final int col)
	{
		try {
			rowData[row][col] = value;
			fireTableCellUpdated(row, col);
		} catch(NullPointerException npe) {
			return;
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			return;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Class getColumnClass(final int c) {
		try {
			boolean doubleVals = false;
			for(int i = 0; i < this.getRowCount(); i++) {
				final String a =  getValueAt(i, c).toString().trim();
				try {
					final Double temp1 = Double.parseDouble(a);
					final Long temp2  = Long.parseLong(a);
					if(temp1.compareTo(temp2.doubleValue()) != 0) doubleVals = true;
				} catch(NumberFormatException nfe) {
					if("FALSE".equals(a.toUpperCase())||"TRUE".equals(a.toUpperCase())) return Boolean.class;
					return String.class;
				}
			}
			if(doubleVals) return Double.class;
			else return Long.class;
		} catch (NullPointerException npe) {
			return String.class;
		}
    }
	
	public CustomTableModel(final Object[][] a, final String[] b)
	{
		rowData = a;
		columnNames = b;
		defaultColumnNames = new String[256];
		for(int i = 1; i < 257; i++) defaultColumnNames[i-1] = "Column " + i;
		return;
	}
	public void setData(final Object[][] a, final String[] b)
	{
		rowData = a;
		columnNames = b;
		fireTableStructureChanged();
		return;
	}
}