package dwiteC2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Q1 {
	public static void main(String[] args) {
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader("c2/DATA1.TXT"));
			writer = new PrintWriter(new FileWriter("c2/OUT1.TXT"));
			for(int i = 0; i < 5; i++) {
				int n = 0;
				int moves = 0;
				String line = reader.readLine();
				n = Integer.parseInt(line);
				int xCor = 0;
				int yCor = 0;
				boolean down = true;
				for(int x = 1; (moves+x) < n; moves += x, x++) {
					if(down) {
						xCor += x;
					} else {
						yCor -= x;
					}
					down = !down;
				}
				if(moves < n) {
					if(down) {
						xCor += n - moves;
					} else {
						yCor -= n - moves;
					}
				}
				writer.println(xCor + " " + yCor);
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
