package r13.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class MainClass extends JFrame
{
	private static final long serialVersionUID = 1548233817403693158L;
	public static void main(final String[] args)
	{
		@SuppressWarnings("unused")
		MainClass main = null;
		main = new MainClass();
	}
	public MainClass()
	{
		JFileChooser fc = null;
		try {
			fc = new JFileChooser();
			File textInput = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(textInput);
		} catch (Exception e) {
			System.out.println("File Input Error!");
		}
		try {
			String buffer = "";
			fc.showOpenDialog(this);
			File file = fc.getSelectedFile();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile())));
			/*fc.showSaveDialog(this);
			File out = fc.getSelectedFile();
			PrintWriter printer = new PrintWriter(new FileWriter(out.getAbsoluteFile()));
			String line = "";
			while ((line = reader.readLine()) != null) {
		    	System.out.println(line.trim());
		    }*/
			String line = "";
			while ((line = reader.readLine()) != null) {
		    	buffer += line + "\n";
		    }
		    buffer = buffer.substring(0, buffer.length() - 1);
		    String keyText = "", cipherText = "", plainText = "";
			try {
				keyText = buffer.substring(0, buffer.indexOf("\n"));
				cipherText = buffer.substring(buffer.indexOf("\n") + 1);
			} catch(Exception e) {
				cipherText = buffer;
			}
			char[] letterArray = cipherText.toCharArray();
			if(keyText.length() != 0) {
				char[] keyChar = keyText.toCharArray();
				int[] keyArray = new int[keyText.length()];
				for(int i = 0; i < keyText.length(); i++) {
					keyArray[i] = (keyChar[i] > 64 && keyChar[i] < 91) ? keyChar[i] - 64 : keyChar[i] - 96;
				}
				int j = 0;
				for(int i = 0; i < cipherText.length(); i++) {
					if((int) letterArray[i] <= 122 && (int)letterArray[i] >= 97) {
						int temp = (int)letterArray[i] - (keyArray[j % keyText.length()]);
						if(temp < 97) temp += 26;
						else if(temp > 122) temp -= 26;
						plainText += (char)temp;
						j++;
					} else if((int) letterArray[i] <= 90 && (int)letterArray[i] >= 65){
						int temp = (int)letterArray[i] - (keyArray[j % keyText.length()]);
						if(temp < 65) temp += 26;
						else if(temp > 90) temp -= 26;
						plainText += (char)temp;
						j++;
					} else {
						plainText += letterArray[i];
					}
				}
			} else {
				for(int i = 0; i < cipherText.length(); i++) {
					if((int) letterArray[i] <= 122 && (int)letterArray[i] >= 97) {
						plainText += (char)((int) ((letterArray[i] > 109) ? letterArray[i]  - 13 : letterArray[i]  + 13));
					} else if((int) letterArray[i] <= 90 && (int)letterArray[i] >= 65){
						plainText += (char)((int) ((letterArray[i] > 109) ? letterArray[i]  - 13 : letterArray[i]  + 13));
					} else {
						plainText += letterArray[i];
					}
				}
			}
			System.out.println(keyText + "\n" + cipherText + "\n" + plainText);
		} catch (IOException ioe){
			System.out.println("File Error!");
		}
	}
}
