import java.io.*;
import java.util.*;

/** A simple program to test the LetterGrid class with Boogle
  * @author Ridout
  * @version March 2013
  */
public class BoggleTest
{
    public static void main (String[] args) throws IOException
    {
	// Read in the list of words and put them into upper case
	Scanner file = new Scanner (new File ("wordlist.txt"));
	ArrayList <String> wordList = new ArrayList <String> ();
	while (file.hasNext ())
	    wordList.add (file.nextLine ().toUpperCase ());
	file.close();
	
	// Load up the boggle grids and give them a score
	String bgFileName = "boggle.txt";
	System.out.println ("Boggle Grid: " + bgFileName + "\n");
	LetterGrid grid = new LetterGrid (bgFileName);
	System.out.println (grid);
	
	// A few test cases for this file only
	System.out.println (grid.boggleSearch("SAERSTIPLSERSENG"));
	System.out.println (grid.boggleSearch("GNESRESLPITSREAS"));
	System.out.println (grid.boggleSearch("SAERSTA"));
	System.out.println (grid.boggleSearch("SITSRALPESR"));

	long startTime = System.nanoTime ();
	System.out.println ("Score: " + grid.boggleScore (wordList));
	long stopTime = System.nanoTime ();
	System.out.printf ("Time: %.1f ms%n", (stopTime - startTime) / 1000000.0);
	System.out.println ("Done");
    }
}
