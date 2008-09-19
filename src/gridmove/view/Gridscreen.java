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
		
		myScreen = new Screen();
		counterPanel = new JPanel();
		myGemCounter = new GemCounter();
		myKeyCounter = new KeyCounter();
		myKeyRelay = new KeyRelay(myController);

		createAndShowGUI();
		
		pack();
	}

	protected void createAndShowGUI() {			
		JPanel backing = new JPanel();
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
		
		backing.add(counterPanel);
	
		backing.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		add(backing);
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