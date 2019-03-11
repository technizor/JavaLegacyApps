package app.enc.idx36.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

public class MainPanel extends JTabbedPane
{
	private static final long serialVersionUID = -8201969845331527028L;
	private ControlPanel controls;
	private LogPanel log;
	private IOPanel io;
	private ConversionPanel encryp;
	private ConversionPanel decryp;
	
	public MainPanel()
	{
		encryp = new ConversionPanel(true);
		decryp = new ConversionPanel(false);
		controls = new ControlPanel();
		this.addTab("Plain Text", decryp);
		this.setMnemonicAt(0, KeyEvent.VK_P);
		this.addTab("Cipher Text", encryp);
		this.setMnemonicAt(1, KeyEvent.VK_C);
		this.addTab("Options", controls);
		this.setMnemonicAt(2, KeyEvent.VK_O);
		this.addTab("Log", log);
		this.setMnemonicAt(3, KeyEvent.VK_L);
		this.addTab("I/O", io);
		this.setMnemonicAt(4, KeyEvent.VK_I);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	}
}
