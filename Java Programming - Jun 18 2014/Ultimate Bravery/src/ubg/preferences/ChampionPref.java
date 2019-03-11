package ubg.preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ubg.data.Champion;

public class ChampionPref
{
	public static final String fileName = "champions.pref";
	private boolean[] ownedChampions;
	private int totalOwned;
	public ChampionPref()
	{
		ownedChampions = loadPref();
	}
	public void ownAll()
	{
		totalOwned = Champion.total;
		for(int i = 0; i < Champion.total; i++) {
			ownedChampions[i] = true;
		}
		savePref();
	}
	public void ownNone()
	{
		totalOwned = 0;
		for(int i = 0; i < Champion.total; i++) {
			ownedChampions[i] = false;
		}
		savePref();
	}
	public void toggleChampion(int ...ids)
	{
		for(int id : ids) {
			if(ownedChampions[id%256]) {
				totalOwned -= 1;
			} else {
				totalOwned += 1;
			}
			ownedChampions[id%256] = !ownedChampions[id%256];
		}
		savePref();
	}
	public boolean[] loadPref()
	{
		boolean[] champions = new boolean[256];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line = reader.readLine();
			for(int pos = 0; pos < 16; pos++)
			{
				try {
					boolean[] values = fromChar(line.charAt(pos));
					for(int i = 0; i < 8; i++) {
						champions[pos*8+i] = values[i];
						if(values[i]) totalOwned += 1;
					}
				} catch (NullPointerException nfe) {}
				
			}
		} catch (IOException ioe) {
			return new boolean[256];
		}
		return champions;
	}
	public void savePref()
	{
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(new File(fileName)));
			String output = "";
			totalOwned = 0;
			for(int pos = 0; pos < 16; pos++)
			{
				output += toChar(pos);
			}
			writer.print(output);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private char toChar(int pos)
	{
		int champPos = pos*8;
		int val = 0;
		for(int i = 0, p = 128; i < 8; i++, p /= 2) {
			val += getVal(champPos+i)*p;
			if(getVal(champPos+i) == 1) totalOwned += 1;
		}
		return (char) val;
	}
	private byte getVal(int champPos)
	{
		if(ownedChampions[champPos]) return 1;
		return 0;
	}
	private boolean[] fromChar(char ch)
	{
		int val = (int) ch;
		boolean[] values = new boolean[8];
		for(int i = 0, p = 128; i < 8; i++, p /= 2) {
			values[i] = val/p > 0;
			val = val % p;
		}
		return values;
	}
	public int getTotal()
	{
		return totalOwned;
	}
	public boolean[] getValues()
	{
		return ownedChampions;
	}
	public void setValues(boolean[] values)
	{
		if(values.length == ownedChampions.length) {
			ownedChampions = values;
		}
	}
}
