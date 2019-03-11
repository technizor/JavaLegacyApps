package dwiteC2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Q2 {
	public static void main(String[] args) {
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader("c2/DATA2.TXT"));
			writer = new PrintWriter(new FileWriter("c2/OUT2.TXT"));
			for(int i = 0; i < 5; i++) {
				Scanner scan = new Scanner(reader.readLine().trim());
				int x = scan.nextInt();
				int y = scan.nextInt();
				String grid = "";
				String phrase = "";
				char[][] output = new char[x][y];
				for(int n = 0; n < x; n++) {
					for(int m = 0; m < y; m++) {
						output[n][m] = '#';
					}
				}
				for(int j = 0; j < x; j++) {
					grid += reader.readLine().trim();
				}
				phrase = reader.readLine().trim();
				int phrasePos = 0;
				for(int pos = 0; pos < grid.length() && phrasePos < phrase.length(); pos++) {
					if(grid.charAt(pos) == phrase.charAt(phrasePos)) {
						phrasePos++;
						output[pos / y][pos % y] = '.';
					}
				}
				if(phrasePos != phrase.length()) {
					for(int n = 0; n < x; n++) {
						for(int m = 0; m < y; m++) {
							output[n][m] = 'X';
						}
					}
				}
				for(int n = 0; n < x; n++) {
					String temp = "";
					for(int m = 0; m < y; m++) {
						temp += output[n][m];
					}
					writer.println(temp);
				}
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
