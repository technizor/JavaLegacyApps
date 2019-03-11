package tea.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TextFileFilter extends FileFilter {
	public final String description = "Text Files (*.txt)";
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileUtils.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileUtils.txt)) {
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