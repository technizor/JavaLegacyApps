package boltstorm.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends Screen
{
	private static final long serialVersionUID = 6880745820928638499L;
	private JPanel controlPane;
	protected MenuScreen contentPane;
	public static final String menuControlText = "X to Confirm, C to Deconfirm, Arrow Keys to Select";
	
	public Menu(int xSize, int ySize, Game game, MenuScreen menu)
	{
		super(xSize, ySize, game);
		init(game, menu);
	}
	private void init(Game game, MenuScreen menu)
	{
		contentPane = menu;
		this.setLayout(new BorderLayout());
		controlPane = new JPanel();
		controlPane.setBorder(BorderFactory.createRaisedBevelBorder());
		controlPane.setLayout(new BorderLayout());
		JLabel label = new JLabel(menuControlText);
		controlPane.add(label, BorderLayout.CENTER);
		contentPane.setBackground(Color.BLACK);
		this.add(contentPane, BorderLayout.CENTER);
		this.add(controlPane, BorderLayout.SOUTH);
		controls = game.getMenuControls();
		label.setHorizontalTextPosition(JLabel.CENTER);
		controlPane.addKeyListener(game.getMenuControls());
		contentPane.addKeyListener(game.getMenuControls());
	}
	public int getSelected()
	{
		return contentPane.getSelected();
	}
	public void setSelected(int selected)
	{
		contentPane.setSelected(selected);
	}
	public void selectedPlus()
	{
		contentPane.setSelected((contentPane.getButtonSize()+contentPane.getSelected()+1) % contentPane.getButtonSize());
	}
	public void selectedMinus()
	{
		contentPane.setSelected((contentPane.getButtonSize()+contentPane.getSelected()-1) % contentPane.getButtonSize());
	}
	public MenuScreen getNextMenu()
	{
		return contentPane.getNextMenu();
	}
	public MenuScreen getMenu()
	{
		return contentPane;
	}
	public void setMenu(MenuScreen menu)
	{
		this.remove(contentPane);
		this.contentPane = menu;
		this.add(contentPane, BorderLayout.CENTER);
		this.validate();
	}
}
