import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TeamPanel extends JPanel
{
	private static final long serialVersionUID = -7372559336053972494L;
	private JLabel teamLabel;
	private JTextField nameField;
	private JTextField shortField;
	private PlayerPanel[] playerField;
	private BanPanel banField;
	private String name;
	private String acronym;

	public TeamPanel(boolean isBlue)
	{
		name = new String();
		acronym = new String();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel header = new JPanel();
		teamLabel = new JLabel("Purple Team Name: ");
		teamLabel.setMinimumSize(teamLabel.getPreferredSize());
		teamLabel.setMaximumSize(teamLabel.getPreferredSize());
		teamLabel = new JLabel((isBlue ? "Blue" : "Purple") + " Team Name: ");
		teamLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		teamLabel.setPreferredSize(teamLabel.getMinimumSize());
		nameField = new JTextField();
		nameField.setColumns(16);
		nameField.setMinimumSize(nameField.getPreferredSize());
		nameField.setMaximumSize(nameField.getPreferredSize());
		nameField.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0)
			{
				warn();
			}

			public void insertUpdate(DocumentEvent arg0)
			{
				warn();
			}

			public void removeUpdate(DocumentEvent arg0)
			{
				warn();
			}

			private void warn()
			{
				name = nameField.getText();
			}
		});
		shortField = new JTextField();
		shortField.setColumns(6);
		shortField.setMinimumSize(shortField.getPreferredSize());
		shortField.setMaximumSize(shortField.getPreferredSize());
		shortField.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent arg0)
			{
				warn();
			}

			public void insertUpdate(DocumentEvent arg0)
			{
				warn();
			}

			public void removeUpdate(DocumentEvent arg0)
			{
				warn();
			}

			private void warn()
			{
				acronym = shortField.getText();
			}
		});

		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		header.add(teamLabel);
		header.add(nameField);
		header.add(new JLabel("Team Acronym: "));
		header.add(shortField);
		add(header);
		playerField = new PlayerPanel[5];
		for (int i = 0; i < 5; i++) {
			playerField[i] = new PlayerPanel(i + 1);
			add(playerField[i]);
		}
		banField = new BanPanel();
		add(banField);
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}

	public String[] banList()
	{
		return banField.banList();
	}

	public String statsTable()
	{
		return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%n", acronym,
				playerField[0], playerField[1], playerField[2], playerField[3],
				playerField[4]);
	}

	public String toString()
	{
		return name;
	}

	public String teamInfo()
	{
		return String.format("%-3s | %s %s %s %s %s", acronym,
				playerField[0].name(), playerField[1].name(),
				playerField[2].name(), playerField[3].name(),
				playerField[4].name());
	}
}
