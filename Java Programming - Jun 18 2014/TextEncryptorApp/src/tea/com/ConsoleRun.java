package tea.com;

import java.io.Console;

import tea.io.IoFileRead;

public class ConsoleRun {
	public static void main(final String[] arguments)
	{
	for(;;){
		String a = null, b = null, c = null, d = null, e = null, f = null, g = null, k = null;
		int h, i, j;
		Console console = System.console();
		System.out.println("Encryptor Program.");
		a = console.readLine("\nPlease Enter Encryption Key: ");
		d = (console.readLine("Read an input file? \n[Y = 1/N = 2] - ")).trim();
		try {
			h = Integer.parseInt(d);
		} catch (Exception y) {
			h = 2;
		}
		switch(h) {
		case 1:
			e = console.readLine("\nPlease Enter the input text file name - ");
			IoFileRead in = new IoFileRead();
			b = in.getInputFile(e);
			break;
		default:
			b = console.readLine("\nPlease Enter Phrase - ");
			break;
		}
		System.out.println("\nNow encrypting...");
		EncryptionKey z = new EncryptionKey(a, b, 1337);
		z.encryptIt();
		c = z.getEncrypted();
		System.out.println("\nEncrypted Phrase is:\n" + c + "\nWould you like to save this as a text file?");
		f = (console.readLine("\n[Y = 1/N = 2] - ")).trim();
		try {
			i = Integer.parseInt(f);
		} catch (Exception y) {
			i = 2;
		}
		switch(i) {
		case 1:
			g = console.readLine("\nPlease Enter the output text file name.");
			IoFileRead out = new IoFileRead();
			out.getOutputFile(g, c);
			break;
		default:
			break;
		}
		k = (console.readLine("\nDo you wish to exit? \n[Y = 1/N = 2] - ")).trim();
		try {
			j = Integer.parseInt(k);
		} catch (Exception y) {
			j = 2;
		}
		switch(j) {
		case 1:
			System.exit(-1);
			break;
		default:
			break;
		}
	}
	}
}
