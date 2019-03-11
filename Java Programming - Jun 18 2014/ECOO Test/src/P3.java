import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class P3
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("p3.in")));
		for(int i = 0; i < 5; i++) {
			int people = Integer.parseInt(reader.readLine());
			int[] skills = new int[people];
			int totalSkill = 0;
			for(int j = 0; j < people; j++) {
				skills[j] = Integer.parseInt(reader.readLine());
				totalSkill += skills[j];
			}
			int averageSkill = totalSkill/2;
			int[][] aim = new int[people+1][averageSkill+1];
			for(int x = 0; x < people; x++) {
				for(int p = 0; p < aim[0].length; p++) {
					if(p-skills[x] >= 0) {
						aim[x+1][p] = Math.max(aim[x][p], aim[x][p-skills[x]]+skills[x]);
					} else {
						aim[x+1][p] = aim[x][p];
					}
				}
			}
			int closest = aim[people][averageSkill];
			int diff = totalSkill-closest*2;
			System.out.println(diff);
		}
	}
}