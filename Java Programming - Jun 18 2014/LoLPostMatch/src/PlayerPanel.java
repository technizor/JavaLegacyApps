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
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

public class PlayerPanel extends JPanel
{
	private static final long serialVersionUID = -4613857574496170285L;
	private static final String[] ORDER = {" ", "\u2081", "\u2082", "\u2083"};
	private static final String[] ROLES = {" ", "Top Laner", "Jungler",
			"Mid Laner", "AD Carry", "Support" };
	private static final Integer[] ORDERS = { 1, 2, 3 };
	private JLabel roleLabel;
	private JFormattedTextField[] kdaField;
	private JTextField nameField;
	private JTextField pickField;
	private JComboBox<Integer> orderField;
	private int order;
	private String pick;
	private String name;
	private int role;
	private int[] kda;

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

	public PlayerPanel(int roleN)
	{
		role = roleN;
		kda = new int[3];
		name = new String();
		pick = new String();
		order = 1;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		roleLabel = new JLabel(ROLES[1] + ": ");
		roleLabel.setMinimumSize(roleLabel.getPreferredSize());
		roleLabel.setMaximumSize(roleLabel.getPreferredSize());
		roleLabel.setText(ROLES[roleN] + ": ");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setPreferredSize(roleLabel.getMinimumSize());
		nameField = new JTextField();
		nameField.setColumns(10);
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
		orderField = new JComboBox<Integer>(ORDERS);
		orderField.setMinimumSize(orderField.getPreferredSize());
		orderField.setMaximumSize(orderField.getPreferredSize());
		orderField.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						order = orderField.getSelectedIndex() + 1;
					}
				});
		pickField = new JTextField();
		pickField.setColumns(6);
		pickField.setMinimumSize(pickField.getPreferredSize());
		pickField.setMaximumSize(pickField.getPreferredSize());
		pickField.getDocument().addDocumentListener(new DocumentListener()
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
				pick = pickField.getText();
			}
		});
		kdaField = new JFormattedTextField[3];
		MaskFormatter kdaFormat = createFormatter("###");
		kdaFormat.setPlaceholderCharacter('0');
		kdaFormat.setOverwriteMode(true);
		kdaFormat.setValueContainsLiteralCharacters(false);
		for (int i = 0; i < 3; i++) {
			kdaField[i] = new JFormattedTextField(kdaFormat);
			kdaField[i].setColumns(3);
			kdaField[i].setMinimumSize(kdaField[i].getPreferredSize());
			kdaField[i].setMaximumSize(kdaField[i].getPreferredSize());
			kdaField[i].addPropertyChangeListener(new PropertyChangeListener()
					{
						public void propertyChange(PropertyChangeEvent evt)
						{
							Object src = evt.getSource();
							for (int i = 0; i < 3; i++) {
								if (kdaField[i].equals(src))
									kda[i] = Integer.parseInt(kdaField[i].getText());
							}
						}
					});
		}
		add(roleLabel);
		add(nameField);
		add(new JLabel("Pick: "));
		add(pickField);
		add(new JLabel("Order: "));
		add(orderField);
		add(new JLabel("K: "));
		add(kdaField[0]);
		add(new JLabel("D: "));
		add(kdaField[1]);
		add(new JLabel("A: "));
		add(kdaField[2]);
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}
	public int[] kda()
	{
		return kda;
	}
	public String name()
	{
		return name;
	}
	public String pick()
	{
		return pick;
	}
	public int order()
	{
		return order;
	}
	public int role()
	{
		return role;
	}
	public String toString()
	{
		return String.format("%-16s | %10s%s | %d-%d-%d", name, pick, orderScript(), kda[0],kda[1],kda[2]);
	}

	private String orderScript()
	{
		return ORDER[order];
	}
}
