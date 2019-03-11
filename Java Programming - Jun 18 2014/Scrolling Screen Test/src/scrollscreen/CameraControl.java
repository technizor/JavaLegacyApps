package scrollscreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class CameraControl extends JFrame implements KeyListener, Runnable
{
	public static void main(final String[] args)
	{
		new CameraControl();
	}
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean camleft;
	private boolean camright;
	private boolean camup;
	private boolean camdown;
	private static final long serialVersionUID = -2251889706300804629L;
	public boolean running;
	private CameraRoom room;
	private CameraScreen screen;
	private final int roomW = 1215;
	private final int roomH = 717;
	private final int viewW = 200;
	private final int viewH = 200;
	private final int delay = 20;
	private final int speed = 2;
	
	public CameraControl()
	{
		room = new CameraRoom(roomW, roomH, roomW/2, roomH/2, viewW, viewH);
		screen = new CameraScreen(viewW, viewH);
		this.add(screen);
		this.setResizable(false);
		this.pack();
		this.addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		run();
	}
	
	public void run()
	{
		running = true;
		while(running)
		{
			cameraMove();
			screen.paint(screen.getGraphics(), room);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void cameraMove()
	{
		int xOff = 0;
		int yOff = 0;
		if(right) {
			xOff += speed;
		}
		if(left) {
			xOff -= speed;
		}
		if(down) {
			yOff += speed;
		}
		if(up) {
			yOff -= speed;
		}
		if(xOff != 0 || yOff != 0) room.move(xOff, yOff);
		int xOff1 = 0;
		int yOff1 = 0;
		if(camright) {
			xOff1 += speed;
		}
		if(camleft) {
			xOff1 -= speed;
		}
		if(camdown) {
			yOff1 += speed;
		}
		if(camup) {
			yOff1 -= speed;
		}
		if(xOff1 != 0 || yOff1 != 0) room.moveCamera(xOff1, yOff1);
	}
	public void keyPressed(KeyEvent arg0)
	{
		int keyId = arg0.getKeyCode();
		if(keyId == KeyEvent.VK_RIGHT) {
			camright = true;
		}
		if(keyId == KeyEvent.VK_LEFT) {
			camleft = true;
		}
		if(keyId == KeyEvent.VK_DOWN) {
			camdown = true;
		}
		if(keyId == KeyEvent.VK_UP) {
			camup = true;
		}
		if(keyId == KeyEvent.VK_D) {
			right = true;
		}
		if(keyId == KeyEvent.VK_A) {
			left = true;
		}
		if(keyId == KeyEvent.VK_S) {
			down = true;
		}
		if(keyId == KeyEvent.VK_W) {
			up = true;
		}
	}
	
	public void keyReleased(KeyEvent arg0)
	{
		int keyId = arg0.getKeyCode();
		if(keyId == KeyEvent.VK_RIGHT) {
			camright = false;
		}
		if(keyId == KeyEvent.VK_LEFT) {
			camleft = false;
		}
		if(keyId == KeyEvent.VK_DOWN) {
			camdown = false;
		}
		if(keyId == KeyEvent.VK_UP) {
			camup = false;
		}
		if(keyId == KeyEvent.VK_D) {
			right = false;
		}
		if(keyId == KeyEvent.VK_A) {
			left = false;
		}
		if(keyId == KeyEvent.VK_S) {
			down = false;
		}
		if(keyId == KeyEvent.VK_W) {
			up = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {}

}
