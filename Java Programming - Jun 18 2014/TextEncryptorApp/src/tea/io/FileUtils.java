package tea.io;

import java.io.File;

public class FileUtils {
	public final static String encryp = "encryp";
	public final static String txt = "txt";
    
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
}