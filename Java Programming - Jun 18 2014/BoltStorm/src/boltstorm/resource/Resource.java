package boltstorm.resource;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Resource
{
	public static final String boltstormLogo = "boltstorm.png";
	public static final String optionIcon = "option.png";
	public static final String helpIcon = "help.png";
	public static final String exitIcon = "exit.png";
	public static final String singleIcon = "single.png";
	public static final String editorIcon = "editor.png";
	public static final String multiIcon = "multi.png";
	public static final String videoIcon = "video.png";
	public static final String soundIcon = "sound.png";
	public static final String profileIcon = "profile.png";
	public static final String backIcon = "back.png";
	public static final String controlsIcon = "controls.png";
	public static final String splashScreen = "splash.png";
	public static final String windowIcon = "windowIcon.png";
	
	public static Image getImage(String fileName)
	{
		java.net.URL url = Resource.class.getResource(fileName);
		if(url != null) return new ImageIcon(url).getImage();
		return null;
	}
	public static ImageIcon getImageIcon(String fileName)
	{
		java.net.URL url = Resource.class.getResource(fileName);
		if(url != null) return new ImageIcon(url);
		return null;
	}
	public static Font getFont(String fileName)
	{
		java.net.URL url = Resource.class.getResource(fileName);
		if(url != null) return new Font(fileName, 0, 0);
		return null;
	}
}
