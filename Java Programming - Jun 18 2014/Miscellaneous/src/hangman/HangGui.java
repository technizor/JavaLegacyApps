package hangman;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class HangGui extends JFrame implements ActionListener, KeyListener
{
	public static void main(final String[] args)
	{
		new HangGui();
	}
	private static final long serialVersionUID = 9138606139203692934L;
	private JPanel contentPane;
	private JPanel infoPane;
	private JTextArea output;
	private JLabel turnLabel;
	private JLabel wordLabel;
	private JLabel letterLabel;
	private JTextField input;
	private JButton enter;
	private JLabel inputLabel;
	private JPanel inputPane;
	private JPanel labelPane;
	private JPanel boxPane;
	private JTextField wordInfo;
	private JTextField letterInfo;
	private JTextField turnInfo;
	private HangmanGuiRun game;
	public HangGui()
	{
		this.setTitle("Hangman GUI");
		init();
	}
	public void refreshWindow()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.pack();
	}
	private void init()
	{
		contentPane = new JPanel();
		refreshWindow();
		game = new HangmanGuiRun(HangmanGuiRun.loadWords(HangmanGuiRun.listFile));
		contentPane.setLayout(new BorderLayout());
		infoPane = new JPanel();
		infoPane.setLayout(new BorderLayout());
		boxPane = new JPanel();
		boxPane.setLayout(new BorderLayout());
		labelPane = new JPanel();
		labelPane.setLayout(new BorderLayout());
		wordLabel = new JLabel("Word Guessed: ");
		letterLabel = new JLabel("Letters Used: ");
		turnLabel = new JLabel("Turns Left: ");
		labelPane.add(wordLabel, BorderLayout.NORTH);
		labelPane.add(letterLabel, BorderLayout.CENTER);
		labelPane.add(turnLabel, BorderLayout.SOUTH);
		boxPane.add(wordLabel, BorderLayout.NORTH);
		boxPane.add(letterLabel, BorderLayout.CENTER);
		boxPane.add(turnLabel, BorderLayout.SOUTH);
		output = new JTextArea();
		output.setEditable(false);
		output.setPreferredSize(new Dimension(400, 200));
		contentPane.add(output, BorderLayout.NORTH);
		inputPane = new JPanel();
		inputPane.setLayout(new BorderLayout());
		inputLabel = new JLabel("Enter a letter or the word: ");
		input = new JTextField();
		enter = new JButton("Enter");
		contentPane.add(new JPanel(), BorderLayout.CENTER);
		inputPane.add(inputLabel, BorderLayout.WEST);
		inputPane.add(input, BorderLayout.CENTER);
		inputPane.add(enter, BorderLayout.EAST);
		inputPane.setPreferredSize(new Dimension(400, 25));
		contentPane.add(inputPane, BorderLayout.SOUTH);
		enter.addActionListener(this);
		output.setText(game.getProgress());
		String fontName = output.getFont().getName();
		int fontSize = output.getFont().getSize();
		output.setFont(new Font(fontName, 0, (int)(fontSize*1.5)));
		this.addKeyListener(this);
		enter.setMnemonic(KeyEvent.VK_ENTER);
		this.setContentPane(contentPane);
		this.setResizable(false);
		refreshWindow();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		if(source.equals(enter)) {
			inputted();
		}
	}
	private void inputted()
	{
		String outputText = game.play(input.getText());
		if(outputText != null) output.setText(outputText);
		if(game.finished) {
			output.setText(outputText + "\n" + game.getProgress());
		}
		input.setText("");
	}
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getID() == KeyEvent.VK_ENTER) {
			inputted();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
