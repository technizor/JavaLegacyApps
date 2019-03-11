package ubg.data;

import java.awt.Image;

import ubg.res.ImageRes;

public class Champion
{
	public final static Champion[] champions = {
		new Champion("Sion", 0, "Sion.png"),
		new Champion("Sivir", 1, "Sivir.png"),
		new Champion("Soraka", 2, "Soraka.png"),
		new Champion("Nunu", 3, "Nunu.png"),
		new Champion("Ryze", 4, "Ryze.png"),
		new Champion("Master Yi", 5, "Master Yi.png"),
		new Champion("Kayle", 6, "Kayle.png"),
		new Champion("Jax", 7, "Jax.png"),
		new Champion("Fiddlesticks", 8, "Fiddlesticks.png"),
		new Champion("Ashe", 9, "Ashe.png"),
		new Champion("Alistar", 10, "Alistar.png"),
		new Champion("Warwick", 11, "Warwick.png"),
		new Champion("Twisted Fate", 12, "Twisted Fate.png"),
		new Champion("Annie", 13, "Annie.png"),
		new Champion("Tristana", 14, "Tristana.png"),
		new Champion("Morgana", 15, "Morgana.png"),
		new Champion("Teemo", 16, "Teemo.png"),
		new Champion("Singed", 17, "Singed.png"),
		new Champion("Zilean", 18, "Zilean.png"),
		new Champion("Twitch", 19, "Twitch.png"),
		new Champion("Evelynn", 20, "Evelynn.png"),
		new Champion("Tryndamere", 21, "Tryndamere.png"),
		new Champion("Karthus", 22, "Karthus.png"),
		new Champion("Amumu", 23, "Amumu.png"),
		new Champion("Cho'Gath", 24, "Chogath.png"),
		new Champion("Rammus", 25, "Rammus.png"),
		new Champion("Anivia", 26, "Anivia.png"),
		new Champion("Veigar", 27, "Veigar.png"),
		new Champion("Kassadin",28, "Kassadin.png"),
		new Champion("Taric", 29, "Taric.png"),
		new Champion("Gangplank", 30, "Gangplank.png"),
		new Champion("Dr. Mundo", 31, "Dr Mundo.png"),
		new Champion("Blitzcrank", 32, "Blitzcrank.png"),
		new Champion("Janna", 33, "Janna.png"),
		new Champion("Malphite", 34, "Malphite.png"),
		new Champion("Corki", 35, "Corki.png"),
		new Champion("Katarina", 36, "Katarina.png"),
		new Champion("Nasus", 37, "Nasus.png"),
		new Champion("Shaco", 38, "Shaco.png"),
		new Champion("Heimerdinger", 39, "Heimerdinger.png"),
		new Champion("Udyr", 40, "Udyr.png"),
		new Champion("Nidalee", 41, "Nidalee.png"),
		new Champion("Poppy", 42, "Poppy.png"),
		new Champion("Gragas", 43, "Gragas.png"),
		new Champion("Pantheon", 44, "Pantheon.png"),
		new Champion("Mordekaiser", 45, "Mordekaiser.png"),
		new Champion("Ezreal", 46, "Ezreal.png"),
		new Champion("Shen", 47, "Shen.png"),
		new Champion("Kennen", 48, "Kennen.png"),
		new Champion("Garen", 49, "Garen.png"),
		new Champion("Akali", 50, "Akali.png"),
		new Champion("Malzahar", 51, "Malzahar.png"),
		new Champion("Olaf", 52, "Olaf.png"),
		new Champion("Kog'maw", 53, "Kog Maw.png"),
		new Champion("Xin Zhao", 54, "Xin Zhao.png"),
		new Champion("Vladimir", 55, "Vladimir.png"),
		new Champion("Galio", 56, "Galio.png"),
		new Champion("Urgot", 57, "Urgot.png"),
		new Champion("Miss Fortune", 58, "Miss Fortune.png"),
		new Champion("Sona", 59, "Sona.png"),
		new Champion("Swain", 60, "Swain.png"),
		new Champion("Lux", 61, "Lux.png"),
		new Champion("LeBlanc", 62, "LeBlanc.png"),
		new Champion("Irelia", 63, "Irelia.png"),
		new Champion("Trundle", 64, "Trundle.png"),
		new Champion("Cassiopeia", 65, "Cassiopeia.png"),
		new Champion("Caitlyn", 66, "Caitlyn.png"),
		new Champion("Renekton", 67, "Renekton.png"),
		new Champion("Karma", 68, "Karma.png"),
		new Champion("Maokai", 69, "Maokai.png"),
		new Champion("Jarvan IV", 70, "Jarvan IV.png"),
		new Champion("Nocturne", 71, "Nocturne.png"),
		new Champion("Lee Sin", 72, "Lee Sin.png"),
		new Champion("Brand", 73, "Brand.png"),
		new Champion("Rumble", 74, "Rumble.png"),
		new Champion("Vayne", 75, "Vayne.png"),
		new Champion("Orianna", 76, "Orianna.png"),
		new Champion("Yorick", 77, "Yorick.png"),
		new Champion("Leona", 78, "Leona.png"),
		new Champion("Wukong", 79, "Wukong.png"),
		new Champion("Skarner", 80, "Skarner.png"),
		new Champion("Talon", 81, "Talon.png"),
		new Champion("Riven", 82, "Riven.png"),
		new Champion("Xerath", 83, "Xerath.png"),
		new Champion("Graves", 84, "Graves.png"),
		new Champion("Shyvana", 85, "Shyvana.png"),
		new Champion("Fizz", 86, "Fizz.png"),
		new Champion("Volibear", 87, "Volibear.png"),
		new Champion("Ahri", 88, "Ahri.png"),
		new Champion("Viktor", 89, "Viktor.png"),
		new Champion("Sejuani",90, "Sejuani.png"),
		new Champion("Ziggs", 91, "Ziggs.png"),
		new Champion("Nautilus", 92, "Nautilus.png"),
		new Champion("Fiora", 93, "Fiora.png"),
		new Champion("Lulu", 94, "Lulu.png"),
		new Champion("Hecarim", 95, "Hecarim.png"),
		new Champion("Varus", 96, "Varus.png"),
		new Champion("Darius", 97, "Darius.png"),
		new Champion("Draven", 98, "Draven.png"),
		new Champion("Jayce", 99, "Jayce.png"),
		new Champion("Zyra", 100, "Zyra.png"),
		new Champion("Diana", 101, "Diana.png"),
		new Champion("Rengar", 102, "Rengar.png")
		//, new Champion("Syndra", 103, "Syndra.png")
	};
	public static final Champion random = new Champion("Random", -1, "Random.png");
	public static final int total = champions.length;
	private String fileName;
	private String name;
	private int id;
	private Image icon;
	public Image getIcon()
	{
		return icon;
	}
	public Champion (String name, int id, String fileName)
	{
		setName(name);
		setId(id);
		setFileName(fileName);
		icon = ImageRes.loadImage(fileName);
	}
	public String getName()
	{
		return name;
	}
	private void setName(String name)
	{
		this.name = name;
	}
	public int getId()
	{
		if(id < 0) return id+256;
		return id;
	}
	private void setId(int id)
	{
		if(id > 127) {
			this.id = id-256;
		} else {
			this.id = id;
		}
	}
	public String getFileName()
	{
		return fileName;
	}
	private void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
}
