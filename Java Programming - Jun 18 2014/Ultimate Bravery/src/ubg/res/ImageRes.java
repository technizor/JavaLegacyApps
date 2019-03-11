package ubg.res;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ubg.data.Champion;
import ubg.data.SummonerSpell;

public class ImageRes
{
	public static Image loadImage(String fileName)
	{
		try {
			Image img = Toolkit.getDefaultToolkit().getImage(ImageRes.class.getResource(fileName));
			Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, null);
			return img;
		} catch (Exception e) {
			System.out.println("Failed to load " + fileName + "!");
			return null;
		}
	}
	public static BufferedImage getImage(String fileName)
	{
		BufferedImage buf = null;
		try {
			buf = ImageIO.read(ImageRes.class.getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf;
	}
	public static Image getChampionIcon(Champion champ)
	{
		return loadImage(champ.getFileName());
	}
	public static Image getSummonerSpellIcon(SummonerSpell spell)
	{
		return loadImage(spell.getFileName());
	}
}
