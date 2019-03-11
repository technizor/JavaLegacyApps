package ilg.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PreferenceFile
{
	public static final char REGEX = '=';
	private ArrayList<Preference> preferences;
	public PreferenceFile(File f)
	{
		preferences = new ArrayList<Preference>();
		try {
			Scanner scan = new Scanner(f);
			for(String line = null; (line = scan.nextLine()) != null; line  = null) {
				int pos = -1;
				if((pos = line.indexOf(REGEX)) != -1) {
					String pref = line.substring(0, pos).trim();
					String val = line.substring(pos).trim();
					preferences.add(new Preference(pref, val));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveToFile(File f)
	{
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(writer != null) {
			for(Preference pref : preferences) {
				writer.println(pref.getName() + REGEX + pref.getValue());
			}
			writer.flush();
		} else {
			System.out.println("Failed to save file.");
		}
	}
}
