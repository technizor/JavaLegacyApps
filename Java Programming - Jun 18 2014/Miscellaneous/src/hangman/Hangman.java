package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
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
	
	public static void main(final String[] args)
	{
		new Hangman(loadWords("list.txt"), 8, 5);
	}
	
	
	public static final char BLANKCHAR = '-';
	private ArrayList<String> words;
	private int maxTurns;
	public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public Hangman(ArrayList<String> words, int maxTurns, int playNum)
	{
		this.setWords(words);
		this.maxTurns = maxTurns;
		System.out.println("Hangman Game");
		for(int i = 0; i < playNum; i++) {
			play(words.get(new java.util.Random().nextInt(words.size())));
		}
	}
	private void play(String keyword)
	{
		boolean[] lettersGuessed = new boolean[26];
		for(int iter = 0; iter < maxTurns; iter++) {
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
				System.out.println("You have guessed the word! " + keyword + "\n_________________________");
				return;
			}
			for(int i = 0; i < 26; i++) {
				if(lettersGuessed[i]) {
					int ch = i+65;
					char ch1 = (char)ch;
					alreadyGuessed += ch1;
				}
			}
			System.out.println(revealed + "\n" + alreadyGuessed + "\nTurns Remaining: " + (maxTurns-iter));
			boolean looping = true;
			while(looping) {
				System.out.print("Enter a Letter or the word: ");
				Scanner scan = new Scanner(System.in);
				String guess = scan.nextLine().trim().toUpperCase();
				if(guess.equalsIgnoreCase(keyword)) {
					System.out.println("You have guessed the word! " + keyword);
					return;
				}
				char ch = guess.charAt(0);
				if(Character.isLetter(ch)) {
					if(lettersGuessed[ch-65] != true) {
						lettersGuessed[ch-65] = true;
						looping = false;
						if(keyword.toUpperCase().indexOf(ch) != -1) {
							iter--;
						}
					} else {
						System.out.print("Already Guessed! ");
					}
				} else {
					System.out.print("Not a Letter! ");
				}
			}
		}
		System.out.println("You have failed to guess the word! The word it " + keyword + "\n_________________________");
	}
	public ArrayList<String> getWords()
	{
		return words;
	}
	private void setWords(ArrayList<String> words)
	{
		this.words = words;
	}
}
