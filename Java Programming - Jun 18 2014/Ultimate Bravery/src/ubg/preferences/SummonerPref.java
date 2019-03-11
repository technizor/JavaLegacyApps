package ubg.preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SummonerPref
{
	public static final String fileName = "summoner.pref";
	public static final String[] prefs = {
		"level", "gamemode"
	};
	public static final int[] prefDefault = {
		29, 0
	};
	private int[] values = new int[prefs.length];
	public String getTag(String preference)
	{
		try {
			String str = preference.substring(0, preference.indexOf(":"));
			return str;
		} catch (Exception e) {
			return null;
		}
	}
	public int getValue(String preference)
	{
		try {
			String str = preference.substring(preference.indexOf(":")+1);
			return Integer.parseInt(str.trim());
		} catch (Exception e) {
			return 0;
		}
	}
	public SummonerPref()
	{
		values = loadPref();
		savePref();
	}
	public void setDefaultPref()
	{
		defaultPref();
		savePref();
	}
	private int[] loadPref()
	{
		int[] values = new int[prefs.length];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			for(int i = 0; i < prefs.length; i++)
			{
				line = reader.readLine();
				try {
					if(line.indexOf(":") != -1) {
						String pref = line.substring(line.indexOf(":")+1);
						values[i] = Integer.parseInt(pref);
					}
				} catch (Exception e) {}
				
			}
		} catch (IOException ioe) {
			return defaultPref();
		}
		return values;
	}
	private int[] defaultPref()
	{
		for(int i = 0; i < prefs.length; i++) {
			values[i] = prefDefault[i];
		}
		return values;
	}
	public void savePref()
	{
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(fileName)));
			for(int i = 0; i < prefs.length; i++)
			{
				writer.println(prefs[i] + ":" + values[i]);
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getValue(int n)
	{
		return values[n%values.length];
	}
	public void setValues(int[] values)
	{
		if(values.length == this.values.length)
		{
			this.values = values;
		}
	}
}
