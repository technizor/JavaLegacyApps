package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HangmanGuiRun {
	public static final String listFile = "HangmanList.TXT";
	public static ArrayList<String> loadWords(String fileName)
	{
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			return null;
		}
		if(reader != null) {
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					words.add(line.trim());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return words;
	}
	public static final char BLANKCHAR = '-';
	private String keyword;
	private ArrayList<String> words;
	private boolean[] lettersGuessed = new boolean[26];
	private int turns;
	private int maxTurns;
	public boolean finished;
	public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public HangmanGuiRun(ArrayList<String> words)
	{
		this.words = words;
		this.maxTurns = 8;
		reset();
	}
	public String getProgress()
	{
		String revealed = "Word: ";
		String alreadyGuessed = "Letters: ";
		for(int i = 0; i < keyword.length(); i++) {
			if(Character.isLetter(keyword.charAt(i))) {
				if(lettersGuessed[keyword.toUpperCase().charAt(i)-65]) {
					revealed += keyword.charAt(i);
				} else {
					revealed += BLANKCHAR;
				}
			} else {
				revealed += keyword.charAt(i);
			}
		}
		if(revealed.indexOf(BLANKCHAR) == -1) {
			String out = "You have guessed the word! \n" + keyword + "\n_________________________";
			reset();
			return out;
		}
		for(int i = 0; i < 26; i++) {
			if(lettersGuessed[i]) {
				int ch2 = i+65;
				char ch1 = (char)ch2;
				alreadyGuessed += ch1;
			}
		}
		return revealed + "\n" + alreadyGuessed + "\nTurns Remaining: " + (maxTurns-turns);
	}
	public String play(String input)
	{
		finished = false;
		String guess = input.trim().toUpperCase();
		if(guess.length() == 0) return null;
		if(guess.equalsIgnoreCase(keyword)) {
			String out = "You have guessed the word! \n" + keyword;
			reset();
			return out;
		}
		char ch = guess.charAt(0);
		if(Character.isLetter(ch)) {
			if(lettersGuessed[ch-65] != true) {
				if(keyword.indexOf(ch) == -1) {
					turns++;
				}
				lettersGuessed[ch-65] = true;
			} else {
				return "Already Guessed! \n\n" + getProgress();
			}
		} else {
			return "Not a Letter! \n\n" + getProgress();
		}
		if(maxTurns != turns) {
			return getProgress();
		}
		String out = "You have failed to guess the word! \nThe word was \"" + keyword + "\"!\n_________________________";
		reset();
		return out;
	}
	private void reset() {
		finished = true;
		keyword = words.get(new Random().nextInt(words.size()));
		lettersGuessed = new boolean[26];
		maxTurns = (int)(keyword.length() < 8 ? 16- keyword.length() : (keyword.length() < 12 ? 20 - keyword.length() : 8)); 
		turns = 0;
	}
}
