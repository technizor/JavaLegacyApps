package imageview;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CameraControl extends JFrame implements KeyListener, Runnable
{
	public static void main(final String[] args)
	{
		new CameraControl();
	}
	private boolean camleft;
	private boolean camright;
	private boolean camup;
	private boolean camdown;
	private static final String[] EXT = {
		"PNG","JPG","JPEG","BMP","TGA","GIF","JPE","JFIF","TIF","TIFF"
	};
	private static final long serialVersionUID = -2251889706300804629L;
	public boolean running;
	private CameraRoom room;
	private CameraScreen screen;
	private final int delay = 10;
	private final int speed = 2;
	private File currentDir;
	private JFileChooser fc;
	private File loadedFile;
	private BufferedImage displayImage;
	private int curWid;
	private int curHei;
	private int curMode;
	private Point curLoc;
	
	public CameraControl()
	{
		try {
			currentDir = new File(new File(".").getCanonicalPath());
			fc = new JFileChooser();
			fc.setCurrentDirectory(currentDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadFile();
		room = new CameraRoom(displayImage.getWidth(), displayImage.getHeight(), displayImage.getWidth()/2, displayImage.getHeight()/2, Math.min(1280, displayImage.getWidth()),  Math.min(720, displayImage.getHeight()));
		screen = new CameraScreen(Math.min(1280, displayImage.getWidth()), Math.min(720, displayImage.getHeight()));
		screen.setImage(displayImage);
		this.add(screen);
		this.setResizable(true);
		this.pack();
		this.addKeyListener(this);
		this.setMinimumSize(new Dimension(300, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		curWid = this.getWidth();
		curHei = this.getHeight();
		curMode = this.getExtendedState();
		curLoc = this.getLocationOnScreen();
		run();
	}
	public void loadFile()
	{
		fc.showOpenDialog(this);
		loadedFile = fc.getSelectedFile();
		System.out.println("Opened file successfully! Reading data.");
		if(loadedFile != null) {
			try {
				processImage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void processImage() throws IOException
	{
		BufferedImage image = ImageIO.read(loadedFile);
		String extension = loadedFile.getAbsolutePath().substring(loadedFile.getAbsolutePath().lastIndexOf('.') + 1).toUpperCase();
		boolean validFile = false;
		for(String ext : EXT) {
			if(ext.toString().equals(extension.toString())) {
				validFile = true;
				break;
			}
		}
		if(validFile) {
			displayImage = image;
			this.setTitle(loadedFile.getAbsolutePath());
		} else {
			System.out.println("Invalid file");
		}
	}
	public void run()
	{
		running = true;
		while(running)
		{
			if(this.getWidth() != curWid || this.getHeight() != curHei || this.getExtendedState() != curMode
					|| this.getLocationOnScreen() != curLoc)
			{
				int xOff = this.getWidth() - curWid;
				int yOff = this.getHeight() - curHei;
				curMode = this.getExtendedState();
				curLoc = this.getLocationOnScreen();
				curWid = this.getWidth();
				curHei = this.getHeight();
				room.setViewW(room.getViewW() + xOff);
				room.setViewH(room.getViewH() + yOff);
				screen.resizeScreen(screen.getWidth() + xOff, screen.getHeight() + yOff);
			}
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
		if(camright) {
			xOff += speed;
		}
		if(camleft) {
			xOff -= speed;
		}
		if(camdown) {
			yOff += speed;
		}
		if(camup) {
			yOff -= speed;
		}
		if(xOff != 0 || yOff != 0/* && room.getViewX() + xOff >= 0 && room.getViewY() + yOff >= 0
				 && room.getViewX() + xOff < room.getWidth() && room.getViewY() + yOff < room.getHeight()*/) room.moveCamera(xOff, yOff);
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
	}

	public void keyTyped(KeyEvent arg0) {}

}
