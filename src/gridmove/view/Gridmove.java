package gridmove.view;
import gridmove.model.LevelData;
import gridmove.model.Model;
import gridmove.model.Skin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Gridmove extends Gridscreen {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Gridmove() {
		super("Gridmove");
	}
	
	protected void initializeWith(LevelData data) {
		super.initializeWith(data);
		centerOnScreen();
		setVisible(true);
		myController.showLevelInfoDialog();
	}
	
	protected void createAndShowGUI() {
		super.createAndShowGUI();
		
		//create the menu bar and items
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.addKeyListener(myKeyRelay);
	
		JMenuItem loadItem = new JMenuItem("Load Level...");
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelData data = myController.loadLevelFromFile(false);
				
				if(data != null) {
					try {
						myController.setCurrentLevel(data, false);
						centerOnScreen();
						myController.showLevelInfoDialog();
					} catch (Exception ex) {
						showErrorDialog(ex);
					}
				}
			}
		});
	
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gridmove.this.processWindowEvent(new WindowEvent(Gridmove.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		fileMenu.add(loadItem);
		fileMenu.add(exitItem);
		
		JMenuItem settingsItem = new JMenuItem("Game Options...");
		settingsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.editGameSettings();
			}
		});
		editMenu.add(settingsItem);
		
		JMenu selectSkins = new JMenu("Skin");
		ButtonGroup skins = new ButtonGroup();
		
		JRadioButtonMenuItem classicItem = new JRadioButtonMenuItem("Gridmove Classic");
		classicItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.getActiveModel().setCurrentSkin(Skin.CLASSIC);
				Gridmove.this.getScreen().updateScreen();
				Gridmove.this.centerOnScreen();
			}
		});
		skins.add(classicItem);
	
	
		JRadioButtonMenuItem loadSkinItem = new JRadioButtonMenuItem("Load Skin...");
		loadSkinItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.loadSkinFromFile();
			}
		});
		skins.add(loadSkinItem);
		
		classicItem.setSelected(true);
		
		JMenuItem refreshItem = new JMenuItem("Refresh Screen");
		refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myScreen.updateScreen();
				myScreen.repaint();
				updateGemCount();
				updateKeyCount();
				pack();
				counterPanel.validate();
			}
		});
	
	
		selectSkins.add(classicItem);
		selectSkins.add(loadSkinItem);
	
		
		JMenuItem saveScreenshotItem = new JMenuItem("Save Screenshot...");
		saveScreenshotItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.saveScreenShotToFile();
			}
		});
	
		viewMenu.add(selectSkins);
		viewMenu.add(refreshItem);
		viewMenu.add(saveScreenshotItem);
		
		setJMenuBar(menuBar);
	}
	
	public static void main(String[] args) {
		//use the OS menu bar on the mac
		String osName = System.getProperty("os.name");
		if (osName.contains("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		
		new Gridmove();
	}
}
