package ccg.com;

import ccg.gui.AppWindow;

public class MainClass
{
	@SuppressWarnings("unused")
	private static ChessBoard board = new ChessBoard();
	public static void main(final String[] args)
	{
		AppWindow window = new AppWindow();
		window.setVisible(true);
	}
}
