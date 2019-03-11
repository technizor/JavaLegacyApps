import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class P1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("p1.in")));
		for(int i = 0; i < 5; i++) {
			int changes = Integer.parseInt(reader.readLine());
			LetterChange[] letters = new LetterChange[changes];
			for(int j = changes-1; j >= 0; j--) {
				String line = reader.readLine().toLowerCase();
				letters[j] = new LetterChange(line.toCharArray()[0], line.toCharArray()[2]);
			}
			char[] words = reader.readLine().toLowerCase().toCharArray();
			for(int j = 0; j < words.length; j++) {
				for(LetterChange letter : letters) {
					if(letter.originalChar == words[j]) {
						words[j] = letter.newChar;
						break;
					}
				}
			}
			System.out.println(new String(words));
		}
	}
}
class LetterChange {
	public char originalChar;
	public char newChar;
	LetterChange(char a, char b) {
		originalChar = a;
		newChar = b;
	}
}