package dsa.gui;

import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

public class TabPanel extends JTabbedPane
{
private final static long serialVersionUID = 1L;

	IOPanel ioPane = new IOPanel();
	OptionPanel optionPane = new OptionPanel();
	LogPanel logPane = new LogPanel();
	TablePanel dataPane = new TablePanel();
	public TabPanel()
	{
		this.addTab("IO Tab", ioPane);
	    this.setMnemonicAt(0, KeyEvent.VK_I);
		this.addTab("Option Tab", optionPane);
	    this.setMnemonicAt(1, KeyEvent.VK_O);
		this.addTab("InfoLog Tab", logPane);
	    this.setMnemonicAt(2, KeyEvent.VK_L);
		this.addTab("DataTable Tab", dataPane);
	    this.setMnemonicAt(3, KeyEvent.VK_D);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}
}
