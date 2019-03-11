package ubg.com;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ubg.data.Champion;
import ubg.preferences.ChampionPref;

public class ChampionList extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 7302583110235927716L;
	private static final Dimension size = new Dimension(320, 450);
	ChampionPref ownedChampions;
	JScrollPane scroll;
	JPanel listPane;
	JCheckBox[] boxes;
	JPanel options;
	JButton save;
	JButton close;
	JButton revert;
	AppWindow parent;

	public ChampionList(AppWindow parent, ChampionPref list)
	{
		ownedChampions = list;
		this.parent = parent;
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setSize(size);
		this.setLayout(new BorderLayout());
		options = new JPanel();
		options.setLayout(new GridLayout(1, 3));
		save = new JButton("Save");
		revert = new JButton("Revert");
		close = new JButton("Close");
		options.add(save);
		options.add(revert);
		options.add(close);
		this.add(options, BorderLayout.SOUTH);
		boxes = new JCheckBox[Champion.total];
		listPane = new JPanel();
		listPane.setLayout(new GridLayout(Champion.total, 1));
		for(int i = 0; i < Champion.total; i++)
		{
			boxes[i] = new JCheckBox(Champion.champions[i].getName(), ownedChampions.getValues()[i]);
			listPane.add(boxes[i]);
		}
		scroll = new JScrollPane(listPane);
		this.add(scroll, BorderLayout.CENTER);
		save.addActionListener(this);
		revert.addActionListener(this);
		close.addActionListener(this);
	}

	public void actionPerformed(ActionEvent paramActionEvent)
	{
		Object obj = paramActionEvent.getSource();
		if(obj.equals(save)) {
			boolean[] values = new boolean[256];
			for(int i = 0; i < Champion.total; i++)
			{
				values[i] = boxes[i].isSelected();
			}
			ownedChampions.setValues(values);
			ownedChampions.savePref();
			CardLayout cl = (CardLayout) parent.window.getLayout();
			cl.show(parent.window, parent.cardNames[0]);
			parent.selectedDisplay = 0;
			parent.display.draw = true;
		} else if(obj.equals(revert)){
			for(int i = 0; i < Champion.total; i++)
			{
				boxes[i].getModel().setSelected(ownedChampions.getValues()[i]);
			}
		} else if(obj.equals(close)) {
			CardLayout cl = (CardLayout) parent.window.getLayout();
			cl.show(parent.window, parent.cardNames[0]);
			parent.selectedDisplay = 0;
			parent.display.draw = true;
		}
	}
	
}
