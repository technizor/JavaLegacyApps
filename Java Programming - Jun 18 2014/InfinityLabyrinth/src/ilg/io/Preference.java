package ilg.io;

public class Preference
{
	private String preferenceName;
	private String preferenceValue;
	
	public Preference(String preference, String value)
	{
		preferenceName = preference;
		preferenceValue = value;
	}
	public String getName()
	{
		return preferenceName;
	}
	public String getValue()
	{
		return preferenceValue;
	}
}
