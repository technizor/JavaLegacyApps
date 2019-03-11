import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Q1
{
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"slowdown.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<Integer> distEvents = new ArrayList<Integer>();
		ArrayList<Integer> timeEvents = new ArrayList<Integer>();
		for (int line = 0; line < n; line++) {
			String[] str = reader.readLine().split(" ");
			if (str[0].equals("D"))
				distEvents.add(Integer.parseInt(str[1]));
			else
				timeEvents.add(Integer.parseInt(str[1]));
		}
		reader.close();
		// Finishes the run
		distEvents.add(1000);
		timeEvents.add(10000001);

		Collections.sort(distEvents, Collections.reverseOrder());
		Collections.sort(timeEvents, Collections.reverseOrder());

		double currentDist = 0;
		double currentTime = 0;
		int inverseSpeed = 1;
		while (!distEvents.isEmpty() && !timeEvents.isEmpty()
				&& currentDist < 1000) {
			double nextDist = (distEvents.get(distEvents.size() - 1) - currentDist);
			double nextTime = (timeEvents.get(timeEvents.size() - 1) - currentTime);
			if (nextDist * inverseSpeed <= nextTime) {
				if (distEvents.get(distEvents.size() - 1) == 1000) {
					double remaining = 1000 - currentDist;
					currentTime += remaining * inverseSpeed;
					currentDist = 1000;
				} else {
					distEvents.remove(distEvents.size() - 1);
					currentTime += nextDist * inverseSpeed;
					currentDist += nextDist;
					inverseSpeed++;
				}
			} else if (nextTime < nextDist) {
				timeEvents.remove(timeEvents.size() - 1);
				currentTime += nextTime;
				currentDist += nextTime / inverseSpeed;
				inverseSpeed++;
			}
		}
		int finishTime = (int) Math.ceil(currentTime);
		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"slowdown.out")));
		writer.println(finishTime);
		writer.close();

	}

}