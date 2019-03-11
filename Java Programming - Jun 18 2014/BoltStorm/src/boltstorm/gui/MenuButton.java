package boltstorm.gui;

import javax.swing.ImageIcon;

import boltstorm.resource.Resource;


public class MenuButton
{
	private ImageIcon buttonIcon;
	private String buttonName;
	
	
	public MenuButton(String name)
	{
		setButtonName(name);
		setButtonIcon(null);
	}
	public MenuButton(String name, String iconFile)
	{
		setButtonName(name);
		setButtonIcon(Resource.getImageIcon(iconFile));
	}

	public ImageIcon getButtonIcon()
	{
		return buttonIcon;
	}

	public void setButtonIcon(ImageIcon buttonIcon)
	{
		this.buttonIcon = buttonIcon;
	}

	public String getButtonName()
	{
		return buttonName;
	}

	public void setButtonName(String buttonName)
	{
		this.buttonName = buttonName;
	}
}
