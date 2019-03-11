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
	public static void main(final String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"recording.in")));
		int n = Integer.parseInt(reader.readLine());
		ArrayList<Program> programList = new ArrayList<Program>(n);
		for (int program = 0; program < n; program++) {
			Program prog = new Program(reader.readLine());
			for (int p = 0; p < programList.size(); p++)
				prog.addConflicts(programList.get(p));
			programList.add(prog);
		}

		reader.close();
		Collections.sort(programList);

		ArrayList<Program> recordedList = new ArrayList<Program>();
		while (!programList.isEmpty()) {
			// Find the best program to add, by min. recorded conflicts, then by total conflicts
			int minConflicts = n;
			int minPos = 0;
			for (int p = 0; p < programList.size(); p++) {
				int conflicts = 0;
				for (int q = 0; q < recordedList.size(); q++) {
					Program current = programList.get(p);
					Program other = recordedList.get(q);
					if (other.conflictsWith(current)) {
						conflicts++;

						// ensure that you cannot add one that makes 3 conflicts
						// at the same time
						ArrayList<Program> otherConflicts = new ArrayList<Program>(
								other.getConflicts());
						otherConflicts.retainAll(recordedList);

						// failed test, 3 conflict at once. add n to # of
						// conflicts because n or more conflicts is impossible
						if (!otherConflicts.isEmpty())
							conflicts += n;
					}
				}
				if (conflicts < minConflicts) {
					minConflicts = conflicts;
					minPos = p;
				}
			}
			if (minConflicts < n) // passed at least once
				recordedList.add(programList.remove(minPos));
			else
				programList.clear();
		}

		//System.out.println(recordedList);

		PrintWriter writer = new PrintWriter(new FileWriter(new File(
				"recording.out")));
		writer.println(recordedList.size());
		writer.close();

	}

}

class Program implements Comparable<Program>
{
	private ArrayList<Program> conflicts;
	private int start;
	private int end;

	public boolean equals(Object other)
	{
		if (other instanceof Program) {
			Program otherProgram = (Program) other;
			return (otherProgram.start == start && otherProgram.end == end);
		}
		return false;
	}

	Program(String line)
	{
		String[] split = line.split(" ");
		start = Integer.parseInt(split[0]);
		end = Integer.parseInt(split[1]);
		conflicts = new ArrayList<Program>();
	}

	public int compareTo(Program other)
	{
		return conflicts.size() - other.conflicts.size();
	}

	public String toString()
	{
		return String.format("{%d,%d}", start, end);
	}

	public boolean conflictsWith(Program other)
	{
		if (start >= other.start && start < other.end)
			return true;
		if (end > other.start && end <= other.end)
			return true;
		return false;
	}

	public void addConflicts(Program other)
	{
		if ((start >= other.start && start < other.end)
				|| (end > other.start && end <= other.end)) {
			conflicts.add(other);
			other.conflicts.add(this);
		}
	}

	public ArrayList<Program> getConflicts()
	{
		return conflicts;
	}
}