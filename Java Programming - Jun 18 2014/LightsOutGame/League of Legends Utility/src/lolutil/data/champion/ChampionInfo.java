package lolutil.data.champion;


public class ChampionInfo
{
	private String name;
	private String title;
	private Date releaseDate;
	private int ipCost;
	private int rpCost;
	public ChampionInfo(String name, String title, String release, int ip, int rp)
	{
		this.name = name;
		this.title = title;
		releaseDate = new Date(release);
		ipCost = ip;
		rpCost = rp;
	}
	public int getIpCost()
	{
		return ipCost;
	}
	public int getRpCost()
	{
		return rpCost;
	}
	public String getReleaseDate()
	{
		return releaseDate.getDate();
	}
	public String getName()
	{
		return name;
	}
	public String getTitle()
	{
		return title;
	}
}
