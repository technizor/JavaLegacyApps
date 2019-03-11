package pwrdf.cam;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import pwrdf.gui.Controls;
import pwrdf.map.Map;
import pwrdf.map.Tile;

public class CameraControl extends JFrame implements KeyListener, Runnable
{
	public static void main(final String[] args)
	{
		new CameraControl();
	}
	public Map map;
	public Controls controls;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean cameraControlled;
	private static final long serialVersionUID = -2251889706300804629L;
	public boolean running;
	private CameraRoom room;
	private CameraScreen screen;
	private final int windowW = 1280;
	private final int windowH = 720;
	private final int viewW = 640;
	private final int viewH = 448;
	private final int delay = 20;
	private final int speed = 8;
	private final int camSpeed = 8;
	
	public CameraControl()
	{
		screen = new CameraScreen(viewW, viewH);
		controls = Controls.loadControls();
		map = Map.loadMap("testmap.PRDM");
		room = new CameraRoom(
				map.getMapWidth()*Tile.TILESIZE, map.getMapHeight()*Tile.TILESIZE, 
				map.getMapWidth()*Tile.TILESIZE/2,map.getMapHeight()*Tile.TILESIZE/2, 
				viewW, viewH);
		this.add(screen);
		this.setTitle("Power Defense - " + map.getName());
		this.setName("Power Defense");
		this.setResizable(false);
		this.pack();
		//this.setSize(windowW, windowH);
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
			screen.paint(screen.getGraphics(), room, map, !cameraControlled);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void cameraMove()
	{
		if(!cameraControlled) {
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
			if(xOff != 0 || yOff != 0) room.moveSelect(xOff, yOff);
		} else {
			int xOff1 = 0;
			int yOff1 = 0;
			if(right) {
				xOff1 += camSpeed;
			}
			if(left) {
				xOff1 -= camSpeed;
			}
			if(down) {
				yOff1 += camSpeed;
			}
			if(up) {
				yOff1 -= camSpeed;
			}
			if(xOff1 != 0 || yOff1 != 0) {
				room.moveCamera(xOff1, yOff1);
			}
		}
	}
	public void keyPressed(KeyEvent arg0)
	{
		int keyId = arg0.getKeyCode();
		if(keyId == controls.getCameraKey())
		{
			cameraControlled = true;
		}
		if(keyId == controls.getRightKey()) {
			right = true;
		}
		if(keyId == controls.getLeftKey()) {
			left = true;
		}
		if(keyId == controls.getDownKey()) {
			down = true;
		}
		if(keyId == controls.getUpKey()) {
			up = true;
		}
	}
	
	public void keyReleased(KeyEvent arg0)
	{
		int keyId = arg0.getKeyCode();
		if(keyId == controls.getCameraKey()) {
			cameraControlled = false;
		}
		if(keyId == controls.getRightKey()) {
			right = false;
		}
		if(keyId == controls.getLeftKey()) {
			left = false;
		}
		if(keyId == controls.getDownKey()) {
			down = false;
		}
		if(keyId == controls.getUpKey()) {
			up = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {}

}
