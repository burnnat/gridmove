package gridedit.view;
import gridedit.controller.Editor;
import gridedit.controller.EditorKeyRelay;
import gridedit.view.params.LevelPreviewParamsDialog;
import gridmove.controller.KeyRelay;
import gridmove.controller.Notifier;
import gridmove.logging.LoggingOutputStream;
import gridmove.logging.StdOutErrLevel;
import gridmove.model.Level;
import gridmove.model.LevelData;
import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.view.GridFrame;
import gridmove.view.Gridscreen;
import gridmove.view.Screen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Gridedit extends GridFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel infoLabel = new JLabel();

	private Editor myController;
	private Screen myScreen;
	private Palette myPalette;

	public Gridedit() {
		super("GRIDEDIT");

		myController = new Editor(this);

		Model.register(this);

		//Custom button text
		Object[] options = {"Open a Level", "Create a New Level"};
		int n = JOptionPane.showOptionDialog(this,
				"What would you like to do?",
				"Gridedit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if(n == JOptionPane.YES_OPTION) {
			LevelData data = myController.loadLevelFromFile(true);
			if(data != null) {
				try {
					myController.setCurrentLevel(data, true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			myController.createNewLevel(true);
		}

		myScreen = new EditorScreen();

		KeyRelay relay = new EditorKeyRelay(myController);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.addKeyListener(relay);

		JMenuItem newItem = new JMenuItem("New Level...");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.createNewLevel(false);
			}
		});

		JMenuItem saveItem = new JMenuItem("Save Level As...");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.saveLevelToFile();
			}
		});

		JMenuItem loadItem = new JMenuItem("Load Level...");
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelData data = myController.loadLevelFromFile(false);

				if(data != null) {
					try {
						myController.setCurrentLevel(data, false);
						centerOnScreen();
					} catch (Exception ex) {
						showErrorDialog(ex);
					}
				}
			}
		});

		JMenuItem previewItem = new JMenuItem("Preview in Gridmove...");
		previewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Level currentLevel = Model.getModelFor(Gridedit.this).getCurrentLevel();
					Gridscreen previewFrame = new Gridscreen("Preview - " + currentLevel.getLevelTitle(), currentLevel.getLevelData());
					previewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					new LevelPreviewParamsDialog(Model.getModelFor(previewFrame).getCurrentLevel(), currentLevel.getAvatar().getGridLocation()).setVisible(true);
					previewFrame.getScreen().updateScreen();
					previewFrame.updateGemCount();
					previewFrame.updateKeyCount();
					previewFrame.centerOnScreen();
					previewFrame.setVisible(true);
				} catch (Exception ex) {
					showErrorDialog(ex);
				}
			}
		});

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(previewItem);
		fileMenu.add(exitItem);

		//		myCustomTiles = new CustomTileMenu("Insert Custom Tile", this);

		JMenuItem targetSetsItem = new JMenuItem("Target Sets...");
		targetSetsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.editTargetSets();
			}
		});

		JMenuItem settingsItem = new JMenuItem("Level Settings...");
		settingsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.editLevelSettings();
			}
		});

		//		editMenu.add(myCustomTiles);
		editMenu.add(targetSetsItem);
		editMenu.add(settingsItem);

		JMenu selectSkins = new JMenu("Skin");
		ButtonGroup skins = new ButtonGroup();

		JRadioButtonMenuItem classicItem = new JRadioButtonMenuItem("Gridmove Classic");
		classicItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.setCurrentSkin(Skin.CLASSIC_EDITOR);
			}
		});
		skins.add(classicItem);

		//		JRadioButtonMenuItem modernItem = new JRadioButtonMenuItem("Futura (Kenton)");
		//		modernItem.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				myController.setCurrentSkin(Skin.FUTURA);
		//			}
		//		});
		//		skins.add(modernItem);

		JRadioButtonMenuItem loadSkinItem = new JRadioButtonMenuItem("Load Skin...");
		loadSkinItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.loadSkinFromFile();
			}
		});
		skins.add(loadSkinItem);

		JMenuItem saveSkinItem = new JMenuItem("Save Skin As...");
		saveSkinItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.saveSkinToFile(Model.getActiveModel().getCurrentSkin());
			}
		});

		JMenuItem editSkinItem = new JMenuItem("Edit Current Skin...");
		editSkinItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.editCurrentSkin();
			}
		});

		JMenuItem saveScreenshotItem = new JMenuItem("Save Screenshot...");
		saveScreenshotItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.saveScreenShotToFile();
			}
		});

		JCheckBoxMenuItem gridItem = new JCheckBoxMenuItem("Show Grid");
		gridItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				((EditorScreen) myScreen).showGrid(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		classicItem.setSelected(true);

		selectSkins.add(classicItem);
		//		selectSkins.add(modernItem);
		selectSkins.add(loadSkinItem);
		selectSkins.add(editSkinItem);
		selectSkins.add(saveSkinItem);

		viewMenu.add(selectSkins);
		viewMenu.add(saveScreenshotItem);
		viewMenu.add(gridItem);

		setJMenuBar(menuBar);

		JPanel backing = new JPanel();

		myScreen.addKeyListener(relay);
		backing.addKeyListener(relay);

		infoLabel.setAlignmentX(Box.CENTER_ALIGNMENT);

		backing.setLayout(new BoxLayout(backing, BoxLayout.Y_AXIS));

		backing.add(Box.createVerticalGlue());
		backing.add(myScreen);
		backing.add(Box.createVerticalStrut(5));
		backing.add(infoLabel);
		backing.add(Box.createVerticalGlue());

		backing.setBorder(new EmptyBorder(5, 5, 5, 5));

		add(backing);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(relay);

		updateLabel();
		pack();

		myPalette = new Palette(this, relay);

		centerOnScreen();
	}

	public void centerOnScreen() {		
		Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setLocation((int)((scrSize.getWidth() - getWidth()) / 2), (int)((scrSize.getHeight() - getHeight()) / 2));
		myPalette.setLocation(getBounds().x + getBounds().width, (int)((scrSize.getHeight() - myPalette.getHeight()) / 2));
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

		// initialize logging to go to rolling log file
		LogManager logManager = LogManager.getLogManager();
		logManager.reset();

		// log file max size 10K, 3 rolling files, append-on-open
		try {
			File logDir = new File("logs");
			logDir.mkdir();
			Handler fileHandler = new FileHandler(logDir.getPath() + "/log", 10000, 3, true);
			fileHandler.setFormatter(new SimpleFormatter());
			Logger.getLogger("").addHandler(fileHandler);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// rebind stdout/stderr to logger                                  
		Logger logger;                                                         
		LoggingOutputStream los;

		logger = Logger.getLogger("stdout");
		los = new LoggingOutputStream(logger, System.out, StdOutErrLevel.STDOUT);          
		System.setOut(new PrintStream(los, true));                             

		logger = Logger.getLogger("stderr");                                   
		los= new LoggingOutputStream(logger, System.err, StdOutErrLevel.STDERR);           
		System.setErr(new PrintStream(los, true));                              

		System.out.println("Systems Streams Rebound to Logger");
		System.out.println("--- Session Started ---");

		Gridedit g = null;

		try {
			g = new Gridedit();
			g.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void setVisible(boolean isVisible) {
		super.setVisible(isVisible);
		myPalette.setVisible(isVisible);
	}

	public void updateLabel() {
		infoLabel.setText(myController.info());
	}

	public Palette getPalette() {
		return myPalette;
	}

	public Editor getEditor() {
		return myController;
	}

	public Screen getScreen() {
		return myScreen;
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

	public void updateGemCount() {}

	public void updateKeyCount() {}
}
