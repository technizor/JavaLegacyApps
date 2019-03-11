import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

public class MatchPanel extends JPanel
{
	private static final long serialVersionUID = 3492763061706856464L;
	private static final String[] WINNERS = { "N/A", "BLUE", "PURPLE" };
	private JTextField matchField;
	private JFormattedTextField[] timeField;
	private JComboBox<String> winnerField;

	private String matchName;
	private int[] time;
	private int winner;

	private MaskFormatter createFormatter(String s)
	{
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	public MatchPanel()
	{
		matchName = new String();
		time = new int[3];
		winner = 0;
		matchField = new JTextField();
		matchField.setColumns(16);
		matchField.setMinimumSize(matchField.getPreferredSize());
		matchField.setMaximumSize(matchField.getPreferredSize());
		matchField.getDocument().addDocumentListener(new DocumentListener()
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
				matchName = matchField.getText();
			}
		});
		MaskFormatter timeFormat = createFormatter("##");
		timeFormat.setPlaceholderCharacter('0');
		timeFormat.setOverwriteMode(true);
		timeFormat.setValueContainsLiteralCharacters(false);
		timeField = new JFormattedTextField[3];
		for (int i = 0; i < 3; i++) {
			timeField[i] = new JFormattedTextField(timeFormat);
			timeField[i].setColumns(2);
			timeField[i].setMinimumSize(timeField[i].getPreferredSize());
			timeField[i].setMaximumSize(timeField[i].getPreferredSize());
			timeField[i].addPropertyChangeListener(new PropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent evt)
				{
					Object src = evt.getSource();
					for (int i = 0; i < 3; i++) {
						if (timeField[i].equals(src))
							time[i] = Integer.parseInt(timeField[i].getText());
					}
				}
			});
		}
		winnerField = new JComboBox<String>(WINNERS);
		winnerField.setMinimumSize(winnerField.getPreferredSize());
		winnerField.setMaximumSize(winnerField.getPreferredSize());
		winnerField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				winner = winnerField.getSelectedIndex();
			}
		});
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Game: "));
		add(matchField);
		add(new JLabel("Time: "));
		add(timeField[0]);
		add(new JLabel(":"));
		add(timeField[1]);
		add(new JLabel(":"));
		add(timeField[2]);
		add(new JLabel("Winning Team: "));
		add(winnerField);

		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}

	public int winner()
	{
		return winner;
	}

	public String time()
	{
		return String.format("%02d:%02d:%02d", time[0], time[1], time[2]);
	}

	public String toString()
	{
		return matchName;
	}
}
