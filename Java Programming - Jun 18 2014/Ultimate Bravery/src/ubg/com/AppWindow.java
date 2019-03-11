package ubg.com;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AppWindow extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -1513412058075984826L;
	public static final String title = "Roll with It";
	public UltimateBraveryGen generator;
	public JPanel content;
	public DisplayPanel display;
	public JMenuBar menu;
	public JMenuItem roll;
	public JMenuItem options;
	public JMenuItem champions;
	public TextTransfer clipboard;
	public JMenuItem getText;
	public ChampionList champList;
	public Options optionList;
	public JPanel window;
	public int selectedDisplay = 0;
	public String[] cardNames = {
			"Display", "Champions", "Options"
	};
	public boolean isDrawingDisplay()
	{
		if(selectedDisplay == 0) return true;
		return false;
	}
	public static void main(final String[] args)
	{
		AppWindow window = new AppWindow();
		window.setVisible(true);
		window.display.paint(window.display.getGraphics());
	}
	public AppWindow()
	{
		clipboard = new TextTransfer();
		selectedDisplay = 0;
		generator = new UltimateBraveryGen();
		content = new JPanel();
		window = new JPanel();
		content.setLayout(new BorderLayout());
		window.setLayout(new CardLayout());
		menu = new JMenuBar();
		roll = new JMenuItem("Run");
		getText = new JMenuItem("Copy to clipboard");
		champions = new JMenuItem(cardNames[1]);
		options = new JMenuItem(cardNames[2]);
		champList = new ChampionList(this, generator.ownedChampions);
		optionList = new Options(this, generator.options);
		setMenuSizes();
		menu.add(roll);
		menu.add(getText);
		menu.add(champions);
		menu.add(options);
		roll.addActionListener(this);
		getText.addActionListener(this);
		champions.addActionListener(this);
		options.addActionListener(this);
		display = new DisplayPanel(generator);
		content.add(menu, BorderLayout.NORTH);
		content.add(window, BorderLayout.CENTER);
		window.add(display, cardNames[0]);
		window.add(champList, cardNames[1]);
		window.add(optionList, cardNames[2]);
		this.setContentPane(content);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setName(title);
		this.setTitle(title);
		refreshWindow();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		display.run();
	}
	
	private void setMenuSizes()
	{
		int height = champions.getPreferredSize().height;
		Dimension a = new Dimension(80, height);
		Dimension b = new Dimension(40, height);
		Dimension c = new Dimension(110, height);
		Dimension d = new Dimension(70, height);
		getText.setMinimumSize(c);
		getText.setMaximumSize(c);
		getText.setPreferredSize(c);
		getText.setSize(c);
		champions.setMinimumSize(a);
		champions.setMaximumSize(a);
		champions.setPreferredSize(a);
		champions.setSize(a);
		options.setMinimumSize(d);
		options.setMaximumSize(d);
		options.setPreferredSize(d);
		options.setSize(d);
		roll.setMinimumSize(b);
		roll.setMaximumSize(b);
		roll.setPreferredSize(b);
		roll.setSize(b);
	}
	public void refreshWindow()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.pack();
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source.equals(getText)) {
			clipboard.setClipboardContents(generator.getString());
			CardLayout cards = (CardLayout)(window.getLayout());
			cards.show(window, cardNames[0]);
			selectedDisplay = 0;
			display.draw = true;
		} else if(source.equals(champions)) {
			CardLayout cards = (CardLayout)(window.getLayout());
			if(selectedDisplay != 1) {
				cards.show(window, cardNames[1]);
				selectedDisplay = 1;
				display.draw = false;
			} else {
				cards.show(window, cardNames[0]);
				selectedDisplay = 0;
				display.draw = true;
			}
		} else if(source.equals(options)) {
			CardLayout cards = (CardLayout)(window.getLayout());
			if(selectedDisplay != 2) {
				cards.show(window, cardNames[2]);
				selectedDisplay = 2;
				display.draw = false;
			} else {
				cards.show(window, cardNames[0]);
				selectedDisplay = 0;
				display.draw = true;
			}
		} else if(source.equals(roll)) {
			CardLayout cards = (CardLayout)(window.getLayout());
			cards.show(window, cardNames[0]);
			selectedDisplay = 0;
			display.draw = true;
			generator.ultimateBravery();
			display.paint(display.getGraphics());
		}
	}

}
