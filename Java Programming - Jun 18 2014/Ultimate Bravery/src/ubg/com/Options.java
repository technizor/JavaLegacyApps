package ubg.com;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ubg.preferences.SummonerPref;

public class Options extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 7302583110235927716L;
	private static final Dimension size = new Dimension(320, 450);
	SummonerPref options;
	JScrollPane scroll;
	JPanel listPane;
	JComboBox<String>[] boxes;
	JPanel buttonOptions;
	JButton save;
	JButton close;
	AppWindow parent;
	@SuppressWarnings("unchecked")
	public Options(AppWindow parent, SummonerPref list)
	{
		options = list;
		this.parent = parent;
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setLayout(new BorderLayout());
		buttonOptions = new JPanel();
		buttonOptions.setLayout(new GridLayout(1, 2));
		save = new JButton("Save");
		close = new JButton("Close");
		buttonOptions.add(save);
		buttonOptions.add(close);
		this.add(buttonOptions, BorderLayout.SOUTH);
		boxes = new JComboBox[2];
		boxes[0] = new JComboBox<String>();
		for(int i = 1; i <= 30; i++)
		{
			boxes[0].addItem("Lvl " + i);
		}
		boxes[1] = new JComboBox<String>();
		boxes[1].addItem("Classic");
		boxes[1].addItem("Dominion");
		boxes[1].addItem("Proving Grounds");
		boxes[0].setSelectedIndex(options.getValue(0)%30);
		boxes[1].setSelectedIndex(options.getValue(1)%3);
		
		listPane = new JPanel();
		listPane.setLayout(new GridLayout(2, 2));
		listPane.add(new JLabel("Summoner Level: "));
		listPane.add(boxes[0]);
		listPane.add(new JLabel("Gamemode: "));
		listPane.add(boxes[1]);
		scroll = new JScrollPane(listPane);
		this.add(scroll, BorderLayout.CENTER);
		save.addActionListener(this);
		close.addActionListener(this);
	}

	public void actionPerformed(ActionEvent paramActionEvent)
	{
		Object obj = paramActionEvent.getSource();
		if(obj.equals(save)) {
			int[] values = new int[2];
			values[0] = boxes[0].getSelectedIndex();
			values[1] = boxes[1].getSelectedIndex();
			options.setValues(values);
			options.savePref();
			CardLayout cl = (CardLayout) parent.window.getLayout();
			cl.show(parent.window, parent.cardNames[0]);
			parent.selectedDisplay = 0;
			parent.display.draw = true;
		} else if(obj.equals(close)) {
			CardLayout cl = (CardLayout) parent.window.getLayout();
			cl.show(parent.window, parent.cardNames[0]);
			parent.selectedDisplay = 0;
			parent.display.draw = true;
		}
	}
	
}
