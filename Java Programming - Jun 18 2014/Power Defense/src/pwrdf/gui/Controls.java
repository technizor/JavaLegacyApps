package pwrdf.gui;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controls
{
	private int upKey = KeyEvent.VK_W;
	private int downKey = KeyEvent.VK_S;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int cameraKey = KeyEvent.VK_SHIFT;
	private int confirmKey = KeyEvent.VK_SPACE;
	public Controls(){}
	public static Controls loadControls()
	{
		Controls controls = new Controls();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("controls.ini")));
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] info = line.split("=");
				int n = toInt(info[1]);
				if(info[0] == "up") {
					controls.setUpKey(n);
				} else if(info[0] == "dn") {
					controls.setDownKey(n);
				} else if(info[0] == "lf") {
					controls.setLeftKey(n);
				} else if(info[0] == "rg") {
					controls.setRightKey(n);
				} else if(info[0] == "cf") {
					controls.setConfirmKey(n);
				} else if(info[0] == "mv") {
					controls.setCameraKey(n);
				}
			}
			System.out.println("Loaded controls successfully");
		} catch (IOException ioe) {}
		return controls;
	}
	private static int toInt(String number)
	{
		try {
			int n = Integer.parseInt(number);
			return n;
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
	public int getUpKey()
	{
		return upKey;
	}
	public void setUpKey(int upKey)
	{
		this.upKey = upKey;
	}
	public int getDownKey()
	{
		return downKey;
	}
	public void setDownKey(int downKey)
	{
		this.downKey = downKey;
	}
	public int getLeftKey()
	{
		return leftKey;
	}
	public void setLeftKey(int leftKey)
	{
		this.leftKey = leftKey;
	}
	public int getRightKey()
	{
		return rightKey;
	}
	public void setRightKey(int rightKey)
	{
		this.rightKey = rightKey;
	}
	public int getCameraKey()
	{
		return cameraKey;
	}
	public void setCameraKey(int cameraKey)
	{
		this.cameraKey = cameraKey;
	}
	public int getConfirmKey()
	{
		return confirmKey;
	}
	public void setConfirmKey(int confirmKey)
	{
		this.confirmKey = confirmKey;
	}
}
