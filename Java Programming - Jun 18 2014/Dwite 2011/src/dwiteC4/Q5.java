package dwiteC4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q5
{
	static int getDistance (Dust a, Dust b)
	{
		return (int)(Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z));
	}
	
	public static void main(final String[] args) throws Exception
	{
		long time = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader(new File("c4/DATA5.TXT")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("c4/OUT5.TXT")));
		Scanner scan = new Scanner(reader);
		ArrayList<Dust> dusts = new ArrayList<Dust>();
		for(int i = 0; i < 5; i++)
		{
			int numlines = scan.nextInt();
			for (int line = 0; line < numlines; line++)
			{
				int A, B, C, D, E, F, G, H, I, U, V;
				A = scan.nextInt();
				B = scan.nextInt();
				C = scan.nextInt();
				D = scan.nextInt();
				E = scan.nextInt();
				F = scan.nextInt();
				G = scan.nextInt();
				H = scan.nextInt();
				I = scan.nextInt();
				U = scan.nextInt();
				V = scan.nextInt();
				
				for (int start = U; start <= V; start++)
				{
					dusts.add(new Dust(A*start*start+B*start+C, D*start*start+E*start+F, G*start*start+H*start+I));
				}
			}
			int highest = 0;
			for (int outer = 0; outer < dusts.size(); outer++)
			{
				for (int inner = outer + 1; inner < dusts.size(); inner++)
				{
					int dist = getDistance(dusts.get(outer), dusts.get(inner));
					if (dist > highest)
						highest = dist;
				}
			}
			writer.println((int)highest);
			writer.flush();
			dusts.clear();
		}
		long timeDur = System.currentTimeMillis()-time;
		System.out.println(timeDur);
		System.exit((int) timeDur);
	}
}
class Dust
{
	public int x, y, z;
	public Dust (int _x, int _y, int _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
}
