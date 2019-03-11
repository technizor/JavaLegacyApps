package dwiteC3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Q5
{
 public static void main(String[] args) {
	BufferedReader reader;
	PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader(new File("c3/DATA5.TXT")));
			writer = new PrintWriter(new FileWriter(new File("c3/OUT5.TXT")));
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				Random rand = new Random();
				String output = "";
				for(int j = 0; j < 3; j++) {
					output += (rand.nextBoolean() ? "Y" : "N");
				}
				writer.println(output);
				writer.flush();
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
 }
