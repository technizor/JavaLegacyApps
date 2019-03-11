package connectFour;

import javax.swing.JFrame;

public class MainClass
{
	public static void main(final String[] args)
	{
		@SuppressWarnings("unused")
		ConnectFourGame game = new ConnectFourGame();
		JFrame temp = new JFrame("Test");
		temp.setVisible(true);
	}
}
