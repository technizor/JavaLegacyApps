package app.enc.idx36.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends JPanel
{
	private static final long serialVersionUID = 5004377217406100888L;
	private JTextArea log;
	private JScrollPane scrollLog;
	public LogPanel()
	{
		log = new JTextArea();
		scrollLog = new JScrollPane(log);
		scrollLog.setPreferredSize(new Dimension(400, 165));
		log.setEditable(false);
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	}
	public void appendLog(String message)
	{
		log.append("\n" + message);
	}
}
