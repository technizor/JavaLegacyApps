package ccg.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AppInterface extends JPanel
{
	private static final long serialVersionUID = 5105865985877158835L;
	private GridLayout gridLayout;
	JButton[][] boardButtons;
	public AppInterface()
	{
		gridLayout = new GridLayout(8, 8);
		setLayout(gridLayout);
		boardButtons = new JButton[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				boardButtons[i][j] = new JButton();
				add(boardButtons[i][j]);
			}
		}
	}
}
