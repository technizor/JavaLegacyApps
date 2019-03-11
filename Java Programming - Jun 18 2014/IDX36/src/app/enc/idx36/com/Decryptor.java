package app.enc.idx36.com;

import java.util.ArrayList;
import java.util.Scanner;

public class Decryptor extends Cryptor
{
	private ArrayList<String[]> input; //Each element is an array rep a word. Elements of these are the characters.
	
	public Decryptor()
	{
		super();
		String in = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("Index Decryptor Program. Please enter a phrase to decrypt.\n> ");
		in = scanner.nextLine();
		input = new ArrayList<String[]>();
		String[] temp1 = in.split("" + SPACE);
		for(String str : temp1) {
			input.add(str.split("" + LETTERSPACE));
		}
		String output = decodeText();
		System.out.print("Decryption Complete. Outputted message is below.\n> ");
		System.out.println(output);
	}
	public Decryptor(final int runNum)
	{
		super();
		for(int i = 0; i < runNum; i++) {
			String in = "";
			Scanner scanner = new Scanner(System.in);
			System.out.print("Index Decryptor Program. Please enter a phrase to decrypt.\n> ");
			in = scanner.nextLine();
			input = new ArrayList<String[]>();
			String[] temp1 = in.split("" + SPACE);
			for(String str : temp1) {
				input.add(str.split("" + LETTERSPACE));
			}
			String output = decodeText();
			System.out.print("Decryption Complete. Outputted message is below.\n> ");
			System.out.println(output);
		}
	}
	public String decodeText()
	{
		String output = "";
		for(int i = 0; i < input.size(); i++) {
			String[] temp1 = input.get(i);
			for(String str : temp1) {
				if(str.lastIndexOf(".") == -1) {
					output += b36Tob10(str);
				} else {
					int testdex = str.indexOf(46);	//46 = char value of .
					String[] temp2 = {str.substring(0, testdex), str.substring(testdex+1)};
					int index = -1;
					index = Encryptor.b36Tob10(temp2[0]);
					if(temp2[0].charAt(0) == AUXIDENTIFIER) index = -1;//Auxillary char or a string of numbers
					if(index != -1) {
						String keyword = keyName.get(index);
						char[] vals = temp2[1].toCharArray();
						for(int ch = 0; ch < vals.length; ch++) {
							try {
								output += keyword.charAt(Encryptor.b36Tob10("" + vals[ch])-1);
							} catch(Exception e) {
								return "Could not understand!";
							}
						}
					} else {
						if(temp2[0].charAt(0) == AUXIDENTIFIER) {//Aux char
							int temp3 = -1;
							try {
								temp3 = Integer.parseInt(temp2[0].substring(1));
							} catch(NumberFormatException nfe) {}
							if(temp3 != -1) {
								String chars = auxChar.get(temp3+1);
								for(char ch : temp2[1].toCharArray()) {
									try {
										output += chars.charAt(Encryptor.b36Tob10("" + ch));
									} catch(Exception e)
									{
										e.printStackTrace();
										System.out.println(output);
									}
								}
							}
						}
					}
				}
			}
			output += " ";
		}
		return output.toLowerCase();
	}
}
