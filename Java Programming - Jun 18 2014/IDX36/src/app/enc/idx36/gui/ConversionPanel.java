package app.enc.idx36.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import app.enc.idx36.com.Cryptor;
import app.enc.idx36.com.Decryptor;
import app.enc.idx36.com.Encryptor;

public class ConversionPanel extends JPanel
{
	private static final long serialVersionUID = -5510461536135185461L;
	private JTextArea text;
	private JScrollPane scroll;
	private Cryptor cryptor;
	
	public ConversionPanel(boolean encryptor)
	{
		if(encryptor) {
			setCryptor(new Encryptor(1));
		} else {
			setCryptor(new Decryptor(1));
		}
		text = new JTextArea();
		scroll = new JScrollPane();
		scroll.add(text);
		add(scroll);
	}

	public Cryptor getCryptor()
	{
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor)
	{
		this.cryptor = cryptor;
	}
}
