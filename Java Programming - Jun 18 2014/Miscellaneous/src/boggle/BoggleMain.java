package boggle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoggleMain
{
	public static char[][] board;
	public static ArrayList<String> wordList;
	public static void main (final String[] args)
	{
		board = loadBoardFile("board.txt");
		wordList = loadWordFile("list.txt");
		BoggleBoard boggle = new BoggleBoard(board);
		for(int i = 0; i < wordList.size(); i++) {
			boggle.boggleSearch(wordList.get(i));
		}
	}
	public static char[][] loadBoardFile(String fileName)
	{
		char[][] board;
		ArrayList<String> input = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				input.add(line.toUpperCase().trim());
			}
			board = new char[input.get(0).length()][input.size()];
			for(int i = 0; i < input.size(); i++) {
				board[i] = input.get(i).toCharArray();
			}
			return board;
		} catch(FileNotFoundException fnfe) {
			System.out.println("File: " + fileName + " was not found!");
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	public static ArrayList<String> loadWordFile(String fileName)
	{
		ArrayList<String> wordList = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while((line = reader.readLine()) != null) {
				wordList.add(line.toUpperCase().trim());
			}
			return wordList;
		} catch(FileNotFoundException fnfe) {
			System.out.println("File: " + fileName + " was not found!");
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
}
