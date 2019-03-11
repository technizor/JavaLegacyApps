package app.enc.idx36.gui;

public class MainWindow extends org.jetstorm.stormFrame.StormFrame
{
	private static final long serialVersionUID = -61170723881056717L;
	private MainPanel pane;
	
	public MainWindow() {
		super("Index Coder 36", false, false);
	}

	@Override
	public void actionHandler(Object source)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionListeners()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildDefaultElements()
	{
		this.add(pane);
	}

	@Override
	public void configureElements()
	{
		pane = new MainPanel();
	}

}
