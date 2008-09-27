package gridmove.view;

import gridmove.controller.Controller;
import gridmove.controller.KeyRelay;
import gridmove.controller.Notifier;
import gridmove.model.LevelData;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Gridscreen extends GridFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Controller myController;
	protected Screen myScreen;
	protected JPanel counterPanel;
	protected GemCounter myGemCounter;
	protected KeyCounter myKeyCounter;
	protected KeyRelay myKeyRelay;
	
	public Gridscreen(String title) {
		super(title);
		
		myController = new Controller(this);
		
		LevelData levelData = null;
		
		while(levelData == null) {
			levelData = myController.loadLevelFromFile(true);
		}
		
		initializeWith(levelData);
	}
	
	public Gridscreen(String title, LevelData levelData) {
		super(title);
		
		myController = new Controller(this);
		
		initializeWith(levelData);
	}
	
	protected void initializeWith(LevelData data) {		
		try {
			myController.setCurrentLevel(data, true);
		} catch (Exception e) {
			showErrorDialog(e);
		}

		createAndShowGUI();
		
		pack();
	}

	protected void createAndShowGUI() {
		myScreen = new Screen();
		counterPanel = new JPanel();
		myGemCounter = new GemCounter();
		myKeyCounter = new KeyCounter();
		myKeyRelay = new KeyRelay(myController);
		
		JPanel backing = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public Dimension getMinimumSize() {
                return getPreferredSize();
            }
			
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
		backing.setLayout(new BoxLayout(backing, BoxLayout.Y_AXIS));
		
		myScreen.addKeyListener(myKeyRelay);
		backing.addKeyListener(myKeyRelay);
		
		backing.add(myScreen);
		backing.add(Box.createVerticalStrut(5));
		
		counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.X_AXIS));
		
		counterPanel.add(Box.createHorizontalGlue());
		counterPanel.add(myGemCounter);
		counterPanel.add(Box.createHorizontalGlue());
		counterPanel.add(myKeyCounter);
		counterPanel.add(Box.createHorizontalGlue());
		
		myGemCounter.resizeToMinimum();
		myKeyCounter.resizeToMinimum();		
		
		backing.add(counterPanel);	
		backing.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		add(Box.createHorizontalGlue());
		add(backing);
		add(Box.createHorizontalGlue());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(myKeyRelay);
	}

	public void centerOnScreen() {
		Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setLocation((int)((scrSize.getWidth() - getWidth()) / 2), (int)((scrSize.getHeight() - getHeight()) / 2));
	}

	public Screen getScreen() {
		return myScreen;
	}

	public void updateGemCount() {
		myGemCounter.updateItemCount();
		counterPanel.revalidate();
	}

	public void updateKeyCount() {
		myKeyCounter.updateItemCount();
		counterPanel.revalidate();
	}

	public void showErrorDialog(Exception e) {
		//write to log
		e.printStackTrace();
		
		//then display a dialog so the user knows what's going on
		Notifier.notifyError(e);
	}

	public void showFatalErrorDialog(Exception e) {
		showErrorDialog(e);
		System.exit(0);
	}

}