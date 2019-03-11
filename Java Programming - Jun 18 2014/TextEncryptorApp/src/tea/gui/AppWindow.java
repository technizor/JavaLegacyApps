package tea.gui;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import tea.com.EncryptionKey;
import tea.com.TextEncryptorEnum;
import tea.io.FileUtils;

/**
*	AppWindow Class Extends JFrame Implements ActionListener
*	This class is the main window of the application. This class
*	is created by the AppLaunch class and is then used to handle
*	events created by the interface.

*	@author Sherman Ying <idiotioc@gmail.com>
*	@version 1.6
*	@since 2011.0715
**/

public class AppWindow extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

/**
*	A panel that contains the AppInterface.
**/	private JPanel container = new JPanel();
	
/**
*	An instance of the EncryptionKey object, which handles the complex encryption algorithm.
**/	private EncryptionKey encrypt;
	private String lastFileName = "*.encryp";

/**
*	A file chooser used to load and save text files.
**/	private JFileChooser fc;

/**
*	Used to calculate drag distance.
**/	private Point initialClick;

/**
*	An AppInterface JPanel.
**/	private AppInterface panel = new AppInterface();

/**
*	Constructor of this object. Calls methods to create and
*	configure the interface object.
**/	public AppWindow()
	{
		configureDefaultElements();
		configureThis();
		fc = new JFileChooser();
		try {
			File f = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(f);
		} catch (IOException e) {
		}
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new tea.io.EncryptionFileFilter());
		fc.addChoosableFileFilter(new tea.io.TextFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		}

/**
*	This is the action listener that handles all events from
*	every single component in the application.
*
*	@param e - the event.
**/	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	//Get source of event, then execute method
		if(source == panel.windowButtons.close) {
			closeApp();
		} else if(source == panel.windowButtons.minimize) {
			this.setExtendedState(Frame.ICONIFIED);
		} else if(source == panel.in.inFile) {
			String a = loadFile();
			if(a != null) {
				panel.in.inText.setText(a);
			}
		} else if(source == panel.in.outFile) {
			saveFile(panel.in.inText.getText());
		} else if(source == panel.out.inFile) {
			String a = loadFile();
			if(a != null) {
				panel.out.outText.setText(a);
			};
		} else if(source == panel.out.outFile) {
			saveFile(panel.out.outText.getText());
		} else if(source == panel.in.encryptIt) {
			encryptText(1);
		} else if(source == panel.out.decryptIt) {
			encryptText(2);
		}
	}

/**
*	This method is used by the action listener to load files into the text boxes.
**/	private String loadFile()
	{
		String buffer = "";
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			panel.log.append("Opening: " + file.getName() + ".\n");
			String ext = tea.io.FileUtils.getExtension(file);
			if(!(ext.equals(FileUtils.encryp) || ext.equals(FileUtils.txt))) {
				panel.log.append("Could not open: " + file.getName() + ". Incorrect file type.\n");
			} else {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "UTF-16BE"));
				    String line = null;
				    while ((line = reader.readLine()) != null) {
				    	buffer += line + "\n";
				    }
				    buffer = buffer.substring(0, buffer.length() - 1);
				} catch (IOException x) {
					buffer = null;
					System.err.format("IOException: %s%n", x);
				}
			}
		} else {
			buffer = null;
			panel.log.append("Open command cancelled by user.\n");
		}
		panel.log.setCaretPosition(panel.log.getDocument().getLength());
		return buffer;
	}
	
/**
*	This method is used to save to a file.
*
*	@param a - the text to be saved.
**/	private void saveFile(String a)
	{
		fc.setSelectedFile(new File(lastFileName));
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String desc = fc.getFileFilter().getDescription();
			String extension = "";
			if(desc == "Encryption Files (*.encryp)") {
				extension = "encryp";
			} else if(desc == "Text Files (*.txt)") {
				extension = "txt";
			} else {
				extension = "encryp";
			}
			String ext = "encryp";
			ext = tea.io.FileUtils.getExtension(file);
			try {
				if(ext.equals(extension) == false) {
					file = new File(file.getAbsolutePath() + "." + extension);
				}
			} catch (NullPointerException f) {
				file = new File(file.getAbsolutePath() + "." + extension);
			}
			panel.log.append("Saving: " + file.getName() + "\n");
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "UTF-16BE"));
				writer.write(a);
			    writer.close();
			} catch (FileNotFoundException z) {
				z.printStackTrace();
			} catch(UnsupportedEncodingException ue){
				System.out.println("Not supported : " + ue);
			} catch(IOException ufe){
				System.out.println("IO Exception : " + ufe);
			}
		} else {
			panel.log.append("Save command cancelled by user.\n");
		}
		panel.log.setCaretPosition(panel.log.getDocument().getLength());
	}
	
/**
*	This method call the encryption key's encryption methods.
*
*	@param a - the selector (1 for encryption, 2 for decryption).
**/	private void encryptText(int a)
	{

		String pass = "" + panel.key.getText().toString();
		switch(a) {
		case 1:
			panel.log.append("Encrypting...\n");
			try {
				encrypt = new EncryptionKey(pass, panel.in.inText.getText(), Integer.parseInt(panel.num.getText()));
			} catch (Exception z) {
				encrypt = new EncryptionKey(pass, panel.in.inText.getText(), -1);
			}
			encrypt.encryptIt();
			panel.in.inText.setText(encrypt.trimString(panel.in.inText.getText()));
			panel.out.outText.setText(encrypt.trimString(encrypt.getEncrypted()));
			break;
		case 2:
			panel.log.append("Decrypting...\n");
			try {
				encrypt = new EncryptionKey(pass, panel.out.outText.getText(), Integer.parseInt(panel.num.getText()));
			} catch (Exception z) {
				encrypt = new EncryptionKey(pass, panel.out.outText.getText(), -1);
			}
			encrypt.encryptIt();
			panel.out.outText.setText(encrypt.trimString(panel.out.outText.getText()));
			panel.in.inText.setText(encrypt.trimString(encrypt.getEncrypted()));
			break;
		}
	}
	
/**
*	One of the builder methods call by configureDefaultElements(),
*	this adds action listeners to the components.
**/	private void addDefaultActionListener()
	{
		panel.windowButtons.close.addActionListener(this);
		panel.windowButtons.minimize.addActionListener(this);
		panel.in.encryptIt.addActionListener(this);
		panel.out.decryptIt.addActionListener(this);
		panel.in.inFile.addActionListener(this);
		panel.out.outFile.addActionListener(this);
		panel.out.inFile.addActionListener(this);
		panel.in.outFile.addActionListener(this);
	}
	
/**
*	This method exits the program.
**/	private void closeApp()
	{
		this.setVisible(false);
		System.exit(-1);
	}
	
/**
*	One of two configuration methods, this method's operation
*	is more complex than the first and so must call other methods
*	to configure the components of the GUI.
**/	private void configureDefaultElements()
	{
		addDefaultActionListener();
		installMouseListeners();
	}
	
/**
*	One of two configuration methods, this configures the global
*	window settings.
**/	private void configureThis()
	{
	//Configure window elements and layout manager
		setContentPane(container);
	//Configure element defaults
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Encryptor Application");
		this.setSize(TextEncryptorEnum.panelSize);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		setAlwaysOnTop(true);
		container.add(panel);
		container.setBorder(BorderFactory.createRaisedBevelBorder());
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}
		SwingUtilities.updateComponentTreeUI(this);
		this.pack();
	}
	
/**
*	One of the action methods, this will create a mouse listener
*	that allows users to click anywhere (excluding JComponents)
*	and drag to move the main window.
**/	private void installMouseListeners()
	{
	// Get point of initial mouse click
		addMouseListener( new MouseAdapter()
		{
			public void mousePressed( MouseEvent e )
			{
				initialClick = e.getPoint();
				getComponentAt(initialClick);
			}
		});
	// Move window when mouse is dragged
		addMouseMotionListener( new MouseMotionAdapter()
		{
			public void mouseDragged( MouseEvent e )
			{
	// get location of Window
				int thisX = getLocation().x;
				int thisY = getLocation().y;
	// Determine how much the mouse moved since the initial click
				int xMoved = e.getX() - initialClick.x;
				int yMoved = e.getY()-initialClick.y;
	// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				setLocation( X, Y );
			}
		});
	}
}