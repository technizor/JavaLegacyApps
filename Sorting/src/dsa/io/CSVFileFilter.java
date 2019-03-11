package dsa.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CSVFileFilter extends FileFilter {
	public final String description = "Comma Separated Value Files (*.csv)";
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileUtils.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileUtils.csv)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return description;
    }
}