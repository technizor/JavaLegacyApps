/**
 * Finds and replaces every occurrence of a substring.
 * 
 * @author Sherman Ying
 * @version February 12, 2013
 */

public class SearchAndReplace 
{
	/**
	 * Replaces all occurrences of the search string with the specified
	 * replacement string.
	 * 
	 * @param originalStr
	 *            The string you are searching in
	 * @param searchStr
	 *            The string you are searching for
	 * @param replaceStr
	 *            The string you want to replace the searchStr with
	 * @return The new string with all of the replacements made
	 */
	static String searchAndReplace(String originalStr, String searchStr,
			String replaceStr) 
	{
		// Create a StringBuilder with the maximum required capacity
		int newLength = originalStr.length() / searchStr.length()
				* replaceStr.length()+1;
		StringBuilder strBuilder = new StringBuilder(newLength);

		// Store the next index of the search string and the first index of the
		// original string that is unchecked
		int nextIndex = originalStr.indexOf(searchStr);
		int lastIndex = 0;

		// Replace all occurrences of the search string
		while (nextIndex != -1)
		{
			// Add the next non-search string segment and the replacement
			strBuilder.append(originalStr.substring(lastIndex, nextIndex)).append(replaceStr);

			// Update the index values
			lastIndex = nextIndex + searchStr.length();
			nextIndex = originalStr.indexOf(searchStr, lastIndex);
		}
		// Add any remaining characters
		strBuilder.append(originalStr.substring(lastIndex));
		return strBuilder.toString();
	}

	public static void main(String[] args) 
	{
		System.out.println("Checking Search and Replace");

		// First test that the code works
		System.out.println("First test that the code works");
		String newStr = searchAndReplace("Simple simple simple", "simple",
				"easy");
		System.out.println(newStr.equals("Simple easy easy"));
		newStr = searchAndReplace("cat cat cat", "cat", "a big cat");
		System.out.println(newStr.equals("a big cat a big cat a big cat"));
		newStr = searchAndReplace("nothing to replace", "something", "replace");
		System.out.println(newStr.equals("nothing to replace"));
		newStr = searchAndReplace("aaaaa", "a", "aaa");
		System.out.println(newStr.equals("aaaaaaaaaaaaaaa"));
		newStr = searchAndReplace("bbbbb", "bb", "b");
		System.out.println(newStr.equals("bbb"));

		// Now time how fast it is to do lots of search and replaces
		System.out.println("Then time how long it takes");

		String sentence = "This is a test: is is is is is complete";
		String newSentence = "";

		long startTime = System.nanoTime();
		for (int times = 1; times <= 100000; times++)
			newSentence = searchAndReplace(sentence, "is", "was");
		long endTime = System.nanoTime();

		System.out.println(newSentence);
		System.out.printf("Total Time: %.0f ms%n",
				(endTime - startTime) / 1000000.0);
		System.out.println("Program is Complete");
	} // main method
} // SearchAndReplace class

