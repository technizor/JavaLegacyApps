package boltstorm.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import boltstorm.entity.PlayerEntity;
import boltstorm.gui.Game;


public class PlayerController implements KeyListener {
	public List<Key> keys = new ArrayList<Key>();

	private int playerId;
	public Key up = new Key(keys);
	public Key down = new Key(keys);
	public Key left = new Key(keys);
	public Key right = new Key(keys);
	public Key counter = new Key(keys);
	public Key clock = new Key(keys);
	public Key attack = new Key(keys);
	public Key ranged = new Key(keys);
	public Key melee = new Key(keys);
	public Key magic = new Key(keys);
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

	public PlayerController(PlayerEntity player, Game game) {
		this.playerId = player.getPlayerId();
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	private void toggle(KeyEvent ke, boolean pressed) {
		switch(playerId)
		{
		case 0:
			if (ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_F) counter.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_G) clock.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_H) attack.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_R) melee.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_T) ranged.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_Y) magic.toggle(pressed);
			break;
		case 1:
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD8) up.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD5) down.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_DECIMAL) counter.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_ENTER) clock.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD0) attack.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD1) melee.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD2) ranged.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_NUMPAD3) magic.toggle(pressed);
			break;
		case 2:
			if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_COMMA) counter.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_PERIOD) clock.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_SLASH) attack.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_L) melee.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_SEMICOLON) ranged.toggle(pressed);
			if (ke.getKeyCode() == KeyEvent.VK_QUOTE) magic.toggle(pressed);
			break;
		}
		if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) pause.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) pause.toggle(pressed);
	}

	public void keyTyped(KeyEvent ke) {
	}
}
