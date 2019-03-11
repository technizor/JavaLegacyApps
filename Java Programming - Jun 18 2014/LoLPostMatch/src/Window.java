import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	private static final long serialVersionUID = -8255319694373975038L;
	private MatchPanel match;
	private TeamPanel blue;
	private TeamPanel purple;
	private JButton generate;

	public Window()
	{
		match = new MatchPanel();
		generate = new JButton("Generate Summary");
		generate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				generate();
			}
		});
		blue = new TeamPanel(true);
		purple = new TeamPanel(false);
		JPanel matchHolder = new JPanel();
		JPanel teamHolder = new JPanel();

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		matchHolder.setLayout(new BoxLayout(matchHolder, BoxLayout.X_AXIS));
		matchHolder.add(match);
		matchHolder.add(generate);
		teamHolder.setLayout(new BoxLayout(teamHolder, BoxLayout.X_AXIS));
		teamHolder.add(blue);
		teamHolder.add(purple);
		add(matchHolder);
		add(teamHolder);

		setResizable(false);
		pack();
		setTitle("Post-Match Summary Generator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void generate()
	{
		StringBuilder strb = new StringBuilder();
		strb.append(String.format("%s vs %s / Post-Match Discussion / %s%n",
				blue, purple, match));
		strb.append(String.format("%s WIN in %s%n", match.winner() == 1 ? blue
				: (match.winner() == 2 ? purple : "N/A"), match.time()));
		strb.append(String.format("%s%n%s%n%n", blue.teamInfo(),
				purple.teamInfo()));
		String[] b1 = blue.banList();
		String[] b2 = purple.banList();
		strb.append("BANS\n");
		for (int i = 0; i < 3; i++)
			strb.append(String.format("%10s | %10s%n", b1[i], b2[i]));
		strb.append(String.format("%n%s%n%s%n", blue.statsTable(),
				purple.statsTable()));
		StringSelection output = new StringSelection(strb.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
	}

	public static void main(final String[] args)
	{
		new Window();
	}

	public String toString()
	{
		return String.format("");
	}
}
