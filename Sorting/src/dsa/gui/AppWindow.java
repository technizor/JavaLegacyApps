package dsa.gui;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dsa.com.BasicSorts;
import dsa.com.Constants;
import dsa.com.Global;
import dsa.enums.SortInfo;
import dsa.io.FileUtils;


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
*	A file chooser used to load and save text files.
**/	private JFileChooser fc;

/**
*	Used to calculate drag distance.
**/	private Point initialClick;

/**
*	An AppInterface JPanel.
**/	private AppInterface panel = new AppInterface();
	private BasicSorts bs = new BasicSorts();

/**
*	Constructor of this object. Calls methods to create and
*	configure the interface object.
**/	public AppWindow()
	{
		configureDefaultElements();
		configureThis();
		fc = new JFileChooser();
		try {
			final File f = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(f);
		} catch (IOException e) {
		}
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new dsa.io.CSVFileFilter());
		fc.setAcceptAllFileFilterUsed(false);
		resetTableData();
	}

/**
*	This is the action listener that handles all events from
*	every single component in the application.
*
*	@param e - the event.
**/	public void actionPerformed(final ActionEvent e)
	{
		final Object source = e.getSource();
	//Get source of event, then execute method
		if(source == panel.windowButtons.close) {
			closeApp();
		} else if(source == panel.windowButtons.minimize) {
			this.setExtendedState(Frame.ICONIFIED);
		} else if(source == panel.tabPane.ioPane.outButton) {
			final String a = loadFile();
			if(a != null) panel.tabPane.ioPane.setFilePaths(2, a);
		} else if(source == panel.tabPane.ioPane.inButton) {
			final String a = loadFile();
			if(a != null) panel.tabPane.ioPane.setFilePaths(1, a);
		} else if(source == panel.tabPane.ioPane.submit) {
			panel.tabPane.setSelectedIndex(2);
			int a = 0;
			if(getInput()) {
				appendLog(1);
				bs = new BasicSorts();
				if((a = bs.initiate(0)) == 0) {
					appendLog(2);
					if(Global.loadTable) setTableData();
					else resetTableData();
				} else appendLog(a-3);
			}
		} else if(source == panel.tabPane.ioPane.rank) {
			panel.tabPane.setSelectedIndex(2);
			int a = 0;
			if(getInput()) {
				appendLog(3);
				bs = new BasicSorts();
				if((a = bs.initiate(1)) == 0) {
					appendLog(4);
					if(Global.loadTable) setTableData();
					else resetTableData();
				} else appendLog(a-3);
			}
		} else if(source == panel.tabPane.optionPane.sortSelect) {
			final int a = panel.tabPane.optionPane.sortSelect.getSelectedIndex();
			setInfo(a);
		}
	}
	private boolean getInput()
	{
		final String[] strVals = panel.tabPane.ioPane.getFilePaths();
		final int[] intVals = panel.tabPane.optionPane.getDataSettings();
		if(("".equals(strVals[0]) || "".equals(strVals[1]))) {
			appendLog(-1);
			return false;
		}
		Global.setGlobalValues(strVals, intVals);
		return true;
	}
	
	public void appendLog(final int responseNum)
	{
		if(responseNum < 0) {//Error
			switch(responseNum) {
			case -1:
				panel.tabPane.logPane.appendLog("Error. Invalid file(s) selected.\n");
				break;
			case -2:
				panel.tabPane.logPane.appendLog("An error occurred. Please check your sort settings and verify that your data is correct." +
				"\nIf problems persist that do not pertain to your settings/data, please contact the programmer.\n");
				break;
			case -3:
				panel.tabPane.logPane.appendLog("Attention! You cannot sort your data by a column that is not part of the sort!\n");
				break;
			case -4:
				panel.tabPane.logPane.appendLog("Attention! Could not save file due to its usage by another program.\n");
				break;
			case -5:
				panel.tabPane.logPane.appendLog("Attention! This file has headers!\n");
				break;
			case -6:
				panel.tabPane.logPane.appendLog("Attention! This file cannot be sorted with this algorithm!\n");
				break;
			case -7:
				panel.tabPane.logPane.appendLog("Could not find input file.\n");
				break;
			case -8:
				panel.tabPane.logPane.appendLog("CSV Loading failed.\n");
				break;
			case -9:
				panel.tabPane.logPane.appendLog("Could not load/display table data.\n");
				break;
			case -10:
				panel.tabPane.logPane.appendLog("Out of memory. Could not complete the request.\n");
				break;
			}
		} else if(responseNum > 0) {//Success message
			switch(responseNum) {
			case 1:
				panel.tabPane.logPane.appendLog("Initializing " + panel.tabPane.optionPane.sortSelect.getItemAt(panel.tabPane.optionPane.sortSelect.getSelectedIndex()).toString() + "...\n");
				break;
			case 2:
				panel.tabPane.logPane.appendLog(Global.performanceLog + "Finished sorting data...\n");
				break;
			case 3:
				panel.tabPane.logPane.appendLog("Initializing " + panel.tabPane.optionPane.sortSelect.getItemAt(panel.tabPane.optionPane.sortSelect.getSelectedIndex()).toString() + " data ranking...\n");
				break;
			case 4:
				panel.tabPane.logPane.appendLog(Global.performanceLog + "Finished ranking data...\n");
				break;
			case 5:
				panel.tabPane.logPane.appendLog("Open command cancelled by user.\n");
				break;
			}
			Global.performanceLog = "";
		} else if(responseNum == 0) panel.tabPane.logPane.appendLog("Initiating application...\n");
	}
	public void appendLog(final String a)
	{
		panel.tabPane.logPane.appendLog(a);
	}
	private void setInfo(final int a)
	{
		switch(a) {
		case 0:
			panel.tabPane.optionPane.sort.setText(SortInfo.Bubble.getInfo());
			break;
		case 1:
			panel.tabPane.optionPane.sort.setText(SortInfo.Shaker.getInfo());
			break;
		case 2:
			panel.tabPane.optionPane.sort.setText(SortInfo.Merge.getInfo());
			break;
		case 3:
			panel.tabPane.optionPane.sort.setText(SortInfo.MinMax.getInfo());
			break;
		case 4:
			panel.tabPane.optionPane.sort.setText(SortInfo.LSDRadix.getInfo());
			break;
		}
	}
	private void resetTableData()
	{
		final int length = 8;
		final int width = 1;
		String[] sortLabel = new String[width];
		Object[][] sortObj = new Object[length][width];
		for(int i = 0; i < width; i++) sortLabel[i] = new String("Column " + (i+1));
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) sortObj[i][j] = new String("");
		}
		panel.tabPane.dataPane.tableModel.setData(sortObj, sortLabel);
		panel.tabPane.dataPane.packColumns();
	}
	@SuppressWarnings("unchecked")
	private void setTableData()
	{
		int b = 0;
		int c = 0;
		int j = 0;
		ArrayList<ArrayList<Object>> sort = bs.getSorted();
		if(sort == null) {
			appendLog(-9);
			return;
		}
		for(final ArrayList<Object> d : sort) {
			if(d.size() != 0) b++;
			c = (c < d.size()) ? d.size() : c;
		}
		Object[][] sortObj = new Object[b][];
		for(final ArrayList<Object> d : sort) {
			if(d.size() != 0 && j < b) {
				sortObj[j] = new Object[c];
				final Object[] temp = d.toArray();
				int e = 0;
				for(Object f : temp) {
					if(f == null) break;
					try {
						final Double tempDouble = Double.parseDouble(f.toString().trim());
						final Long tempLong = Long.parseLong(f.toString().trim());
						sortObj[j][e] = (tempDouble - tempLong == 0) ? tempLong : tempDouble;
					} catch(NumberFormatException nfe) {
						if("FALSE".equals(f.toString().toUpperCase().trim()) || "TRUE".equals(f.toString().toUpperCase().trim()))
							sortObj[j][e] = ("TRUE".equals(f.toString().toUpperCase().trim())) ? new Boolean(true) : new Boolean(false);
						else sortObj[j][e] = f.toString();
					}
					e++;
				}
				j++;
			}
		}
		panel.tabPane.dataPane.tableModel.setData(sortObj, Global.headers);
		panel.tabPane.dataPane.packColumns();
	}
	
	
/**
*	This method is used by the action listener to load files into the text boxes.
**/	private String loadFile()
	{
		String buffer = "";
		final int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = fc.getSelectedFile();
			appendLog("Opening: " + file.getName() + ".\n");
			final String ext = dsa.io.FileUtils.getExtension(file);
			if(!(ext.equals(FileUtils.csv))) appendLog("Could not open: " + file.getName() + ". Incorrect file type.\n");
			else buffer = file.getAbsolutePath();
		} else {
			buffer = null;
			appendLog(5);
		}
		return buffer;
	}
	
/**
*	One of the builder methods call by configureDefaultElements(),
*	this adds action listeners to the components.
**/	private void addDefaultActionListener()
	{
		panel.windowButtons.close.addActionListener(this);
		panel.windowButtons.minimize.addActionListener(this);
		panel.tabPane.ioPane.inButton.addActionListener(this);
		panel.tabPane.ioPane.outButton.addActionListener(this);
		panel.tabPane.ioPane.submit.addActionListener(this);
		panel.tabPane.ioPane.rank.addActionListener(this);
		panel.tabPane.optionPane.sortSelect.addActionListener(this);
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
		this.setTitle("Data Sorting Application");
		this.setSize(Constants.panelSize);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		setAlwaysOnTop(true);
		container.add(panel);
		container.setBorder(BorderFactory.createRaisedBevelBorder());
		appendLog(0);
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
				final int thisX = getLocation().x;
				final int thisY = getLocation().y;
	// Determine how much the mouse moved since the initial click
				final int xMoved = e.getX() - initialClick.x;
				final int yMoved = e.getY()-initialClick.y;
	// Move window to this position
				final int X = thisX + xMoved;
				final int Y = thisY + yMoved;
				setLocation( X, Y );
			}
		});
	}
}