package app.enc.idx36.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import app.enc.idx36.com.PresetKeyList;

public class ControlPanel extends JPanel
{
	private static final long serialVersionUID = 3781445632641197430L;
	private JButton encrypt;
	private JButton decrypt;
	private JComboBox<String> key;
	
	public ControlPanel()
	{
		key = new JComboBox<String>();
		for(String str : PresetKeyList.presetNames) {
			key.addItem(str);
		}
		key.addItem("Use Keyword File");
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	}
	public JButton getEncryptButton()
	{
		return encrypt;
	}
	public JButton getDecryptButton()
	{
		return decrypt;
	}
}
