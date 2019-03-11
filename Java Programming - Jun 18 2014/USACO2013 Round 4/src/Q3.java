import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q3
{
	static ArrayList<String> wordData;
	static ArrayList<Integer> totalData;

	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"scode.in")));
		String initialWord = reader.readLine();
		reader.close();

		wordData = new ArrayList<String>();
		totalData = new ArrayList<Integer>();

		int total = decode(initialWord);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"scode.out")));
		writer.println(total);
		writer.close();

	}

	public static int decode(String word)
	{
		int index = wordData.indexOf(word);
		if (index != -1)
			return totalData.get(index);

		int length = word.length();
		int minLength = length / 2 + 1;
		int total = 0;
		for (int newLength = minLength; newLength < length; newLength++) {
			String first = word.substring(0, newLength);
			String firstSub = word.substring(newLength);
			if (first.indexOf(firstSub) == 0) {
				total += 1 + decode(first);
			}
			if (first.lastIndexOf(firstSub) == first.length()
					- firstSub.length()) {
				total += 1 + decode(first);
			}
			String last = word.substring(length - newLength);
			String lastSub = word.substring(0, length - newLength);
			if (last.indexOf(lastSub) == 0) {
				total += 1 + decode(last);
			}
			if (last.lastIndexOf(lastSub) == last.length() - lastSub.length()) {
				total += 1 + decode(last);
			}
		}
		wordData.add(word);
		totalData.add(total);
		return total;
	}
}