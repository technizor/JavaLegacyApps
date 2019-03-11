package app.enc.idx36.com;

import java.util.ArrayList;
import java.util.Scanner;

public class Encryptor extends Cryptor
{
	public Encryptor()
	{
		super();
		String input = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("Index Encryptor Program. Please enter a phrase to encrypt.\n> ");
		input = scanner.nextLine();
		String output = encodeText(input, 0, "");
		System.out.print("Encryption Complete. Outputted message is below.\n> ");
		System.out.println(output);
	}
	public Encryptor(final int runNum)
	{
		super();
		for(int i = 0; i < runNum; i++) {
			String input = "";
			Scanner scanner = new Scanner(System.in);
			System.out.print("Index Encryptor Program. Please enter a phrase to encrypt.\n> ");
			input = scanner.nextLine();
			String output = encodeText(input.trim(), 0, "");
			System.out.print("Encryption Complete. Outputted message is below.\n> ");
			System.out.println(output);
		}
	}
	public String encodeText(final String input, int position, String output)
	{
		final int startInd = (int)((Math.random() * keyName.size()) % keyName.size());
		for(int i = startInd; position < input.length(); i++) {
			if(i >= keyName.size()) i -= keyName.size();
			if(!isAlpha(input.charAt(position))) {
				if(input.charAt(position) == ' ')
					return(encodeText(input, position+1, output + SPACE));
				if(isNumChar(input.charAt(position))) {
					String temp0 = input.substring(position);
					Scanner temp = new Scanner(temp0);
					int temp1 = temp.nextInt();
					return(encodeText(input, position + (temp1 + "").length(), 
								output + b10Tob36(temp1)));
				}
				for(int c = 0; c < 2; c++) {
					String tmp = auxChar.get(c+2);
					for(int k = 0; k < tmp.length(); k++) {
						ArrayList<Integer> charPs = new ArrayList<Integer>();
						int ps = 0;
						while (position < input.length()) {
							ps = tmp.toLowerCase().indexOf(input.toLowerCase().charAt(position));
							if(ps == -1) break;
							charPs.add(ps);
							position++;
						}
						if(charPs.size() != 0) {
							if(output.length() > 0) {
								if(output.charAt(output.length()-1) != SPACE) output += LETTERSPACE;
								output += auxChar.get(c) + ".";
								for(int n : charPs) {
									output += b10Tob36(n);
									if(n == 36) n = 0;
									else n++;
								}
								if(position < input.length()) return(encodeText(input, position, output));
								else return output;
							} else {
								output += auxChar.get(c) + ".";
								for(int n : charPs) {
									output += b10Tob36(n);
									if(n == 36) n = 0;
									else n++;
								}
								if(position < input.length()) return(encodeText(input, position, output));
								else return output;
							}
						}
					}
				}
				return(encodeText(input, position+1, output + input.charAt(position)));
			}
			String temp = "";
			try {
				temp = keyName.get(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArrayList<Integer> charPos = new ArrayList<Integer>();
			int pos = 0;
			while (position < input.length()) {
				pos = temp.toLowerCase().indexOf(input.toLowerCase().charAt(position));
				if(pos == -1) break;
				charPos.add(pos);
				position++;
			}
			if(charPos.size() != 0) {
				if(output.length() > 0) {
					if(output.charAt(output.length()-1) != SPACE) output += LETTERSPACE;
					output += b10Tob36(i) + ".";
					for(int n : charPos) {
						if(n == 36) n = 0;
						else n++;
						output += b10Tob36(n);
					}
					break;
				} else {
					output += b10Tob36(i) + ".";
					for(int n : charPos) {
						if(n == 9) n = 0;
						else n++;
						output += b10Tob36(n);
					}
					break;
				}
			}
			i++;
		}
		if(position < input.length()) return(encodeText(input, position, output));
		else return output;
	}
}
