package boltstorm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boltstorm.com.MenuController;
import boltstorm.gui.screen.MainMenu;
import boltstorm.gui.screen.Splash;
import boltstorm.resource.Resource;


public class Game extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static JFrame window;
	private MenuController menuControls;
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            menuControls.keyPressed(e);
            return true;
        }
    }
	public static void main(final String[] args)
	{
		window = new JFrame("BoltStorm: Next Generation Combat");
		Game game = new Game();
		window.add(game);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(game, BorderLayout.CENTER);
		window.getContentPane().setBackground(java.awt.Color.BLACK);
		window.pack();
		window.setMinimumSize(new Dimension(Game.X, Game.Y));
		window.setPreferredSize(new Dimension(Game.X, Game.Y));
		window.setMaximumSize(new Dimension(Game.X, Game.Y));
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setIconImage(Resource.getImage(Resource.windowIcon));
		game.start();
	}
	public static final int X = 1280;
	public static final int Y = 720;
	public int xOffset = 0;
	public int yOffset = 0;
	private boolean running = false;
	private Screen screen;
	private Thread runner;
	Image icon;
	public void start()
	{
		running = true;
		menuControls = new MenuController(this);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
		this.setLayout(new BorderLayout());
		icon = Resource.getImage(Resource.boltstormLogo);
		this.setName("BoltStorm");
		this.setScreen(new Splash(0, 0, this));
		if(runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
		int showSplash = 2500;
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
			}
			repaint();
			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
			if(showSplash > 0)
			{
				showSplash -= 2;
				if(showSplash == 0)
				this.setScreen(new Menu(0, 0, this, new MainMenu(this)));
			}
		}
	}

	public void tick() {
		if(screen instanceof Menu) {
			menuControls.tick();
			((Menu) screen).getMenu().tick();
		}
	}
	public MenuController getMenuControls()
	{
		return this.menuControls;
	}
	public Screen getScreen()
	{
		return screen;
	}
	public void setScreen(Screen screen)
	{
		if(this.screen != null) {
			this.remove(this.screen);
		}
		this.screen = screen;
		this.add(screen, BorderLayout.CENTER);
		this.validate();
	}
}
