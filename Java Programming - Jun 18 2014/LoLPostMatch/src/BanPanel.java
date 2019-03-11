import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BanPanel extends JPanel
{
	private static final long serialVersionUID = 2352405290877967393L;
	private JTextField[] banField;
	private String[] banList;

	public BanPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Bans: "));
		banField = new JTextField[3];
		banList = new String[3];
		for (int i = 0; i < 3; i++) {
			banList[i] = new String();
			banField[i] = new JTextField();
			banField[i].setColumns(6);
			banField[i].setMinimumSize(banField[i].getPreferredSize());
			banField[i].setMaximumSize(banField[i].getPreferredSize());
			add(banField[i]);
			banField[i].getDocument().addDocumentListener(
					new DocumentListener()
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
							for (int i = 0; i < 3; i++)
								banList[i] = banField[i].getText();
						}
					});
		}
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}

	public String[] banList()
	{
		return banList;
	}
}
