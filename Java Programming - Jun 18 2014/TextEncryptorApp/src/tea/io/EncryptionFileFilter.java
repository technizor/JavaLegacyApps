package tea.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class EncryptionFileFilter extends FileFilter {
	public final String description = "Encryption Files (*.encryp)";
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileUtils.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileUtils.encryp)) {
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