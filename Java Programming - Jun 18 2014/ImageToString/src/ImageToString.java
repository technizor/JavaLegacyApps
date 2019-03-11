import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ImageToString
{
	private static final String[] EXT = { "PNG", "JPG", "JPEG", "BMP", "TGA",
			"GIF", "JPE", "JFIF", "TIF", "TIFF" ,"TXT"};

	public static String convertImage(BufferedImage img) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "PNG", baos);
		baos.flush();
		byte[] ba = baos.toByteArray();
		String str = Base64.encode(ba);
		return str;
	}

	public static BufferedImage convertString(String str) throws IOException
	{
		byte[] ba = Base64.decode(str);
		InputStream in = new ByteArrayInputStream(ba);
		BufferedImage img = ImageIO.read(in);
		return img;
	}

	public static void openFile(String fileName) throws IOException
	{
		File f = new File(fileName);
		String ext = f.getAbsolutePath()
				.substring(f.getAbsolutePath().lastIndexOf('.') + 1)
				.toUpperCase();
		boolean v = false;
		for (int x = 0; !v && x < EXT.length; x++) {
			if (EXT[x].equals(ext))
				v = true;
		}
		if (v && !ext.equals("TXT")) {
			BufferedImage img = ImageIO.read(f);
			String out = convertImage(img);
			String op = f.getAbsolutePath().substring(
					f.getAbsolutePath().lastIndexOf('\\') + 1,
					f.getAbsolutePath().lastIndexOf('.'));
			PrintWriter prw = new PrintWriter(new FileWriter(op + ".OUT.TXT"));
			prw.println(out);
			prw.close();
		} else
		{
			BufferedReader rdr = new BufferedReader(new FileReader(f));
			StringBuilder strb = new StringBuilder();
			String ln = rdr.readLine();
			while(ln != null)
			{
				strb.append(ln);
				strb.append('\n');
				ln = rdr.readLine();
			}
			rdr.close();
			BufferedImage img = convertString(strb.toString());
			String op = f.getAbsolutePath().substring(
					f.getAbsolutePath().lastIndexOf('\\') + 1,
					f.getAbsolutePath().lastIndexOf('.'));
			ImageIO.write(img, "PNG", new File(op + ".OUT.PNG"));
		}
	}

	public static void openFile() throws IOException
	{
		FileDialog ch = new FileDialog((java.awt.Frame) null);
		ch.setVisible(true);
		File[] fq = ch.getFiles();
		for(File f : fq)
		{
			openFile(f.getAbsolutePath());
		}
	}

	public static void main(final String[] args) throws IOException
	{
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				openFile(args[i]);
			}
		} else {
			openFile();
		}
	}
}
