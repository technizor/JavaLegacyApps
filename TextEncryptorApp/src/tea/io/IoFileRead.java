package tea.io;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class IoFileRead
{
	public String getInputFile(String fileName)
	{
		String buffer = null, line = "";
		try {
			FileInputStream fis = new FileInputStream(fileName + ".txt");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			while((buffer = br.readLine()) != null) {
				line += buffer;
			}
		} catch (Exception e) {
			line = "";
		}
		try {
			FileInputStream fis = new FileInputStream(fileName + ".encryp");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			while((buffer = br.readLine()) != null) {
				line += buffer;
			}
		} catch (Exception e) {
			line = "";
		}
		return line;
	}
	public void getOutputFile(String fileName, String c)
	{
		try {
		      PrintStream out = new PrintStream(new FileOutputStream(
		          fileName + ".encryp"));
		        out.println(c);

		      out.close();

		    } catch (FileNotFoundException z) {
		      z.printStackTrace();
		    }
	}
}
