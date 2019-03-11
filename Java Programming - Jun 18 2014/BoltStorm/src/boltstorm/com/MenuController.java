package boltstorm.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import boltstorm.gui.Game;


public class MenuController implements KeyListener {
	public List<Key> keys = new ArrayList<Key>();

	public Key left = new Key(keys);
	public Key right = new Key(keys);
	public Key up = new Key(keys);
	public Key down = new Key(keys);
	public Key confirm = new Key(keys);
	public Key deconfirm = new Key(keys);
	public Key pause = new Key(keys);

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public MenuController(Game game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	private void toggle(KeyEvent ke, boolean pressed) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			up.toggle(pressed);
			break;
		case KeyEvent.VK_DOWN:
			down.toggle(pressed);
			break;
		case KeyEvent.VK_LEFT:
			left.toggle(pressed);
			break;
		case KeyEvent.VK_RIGHT:
			right.toggle(pressed);
			break;
		case KeyEvent.VK_X:
			confirm.toggle(pressed);
			break;
		case KeyEvent.VK_C:
			deconfirm.toggle(pressed);
			break;
		case KeyEvent.VK_ESCAPE:
			pause.toggle(pressed);
			break;
		}
	}

	public void keyTyped(KeyEvent ke) {
	}
}
