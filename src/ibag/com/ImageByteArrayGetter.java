package ibag.com;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImageByteArrayGetter extends JFrame
{
	private static final long serialVersionUID = 376686597222570615L;
	
	public static void main (final String[] args)
	{
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				new ImageByteArrayGetter(args[i]);
			}
		} else {
			new ImageByteArrayGetter();
		}
	}
	private File currentDirectory;
	private File imageInput;
	private static final String[] EXT = {
		"PNG","JPG","JPEG","BMP","TGA","GIF","JPE","JFIF","TIF","TIFF"
	};
	public ImageByteArrayGetter(String imagePath)
	{
		imageInput = new File(imagePath);
		try {
			processImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ImageByteArrayGetter()
	{
		JFileChooser fc = null;
		try {
			fc = new JFileChooser();
			currentDirectory = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(currentDirectory);
		} catch (Exception e) {
			System.out.println("File Input Error!");
		}
		fc.showOpenDialog(this);
		imageInput = fc.getSelectedFile();
		System.out.println("Opened file successfully! Reading data.");
		try {
			processImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(-1);
	}
	private void processImage() throws IOException
	{
		BufferedImage image = ImageIO.read(imageInput);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String extension = imageInput.getAbsolutePath().substring(imageInput.getAbsolutePath().lastIndexOf('.') + 1).toUpperCase();
		boolean validFile = false;
		for(String ext : EXT) {
			if(ext.toString().equals(extension.toString())) {
				validFile = true;
				break;
			}
		}
		if(validFile) {
			ImageIO.write(image, extension, baos );
			baos.flush();
			byte[] byteArray = baos.toByteArray();
			System.out.println("Finished Reading File. Outputting data to text.");
			String fileName = imageInput.getAbsolutePath().substring(imageInput.getAbsolutePath().lastIndexOf('\\') + 1,
					imageInput.getAbsolutePath().lastIndexOf('.'));
			String output = fileName + ": {";
			for(int i = 0; i < byteArray.length; i++) {
				output += byteArray[i];
				if(i < byteArray.length-1) {
					output += ", ";
				}
				if(i % 8 == 0 && i != 0) {
					output += "\n";
				}
			}
			output += "};";
			PrintWriter writer = new PrintWriter(new FileWriter(fileName + "_byte.TXT"));
			writer.println(output);
			writer.flush();
			writer.close();
		} else {
			System.out.println("Invalid file");
		}
	}
}
