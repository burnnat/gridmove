package gridmove.controller;

import gridedit.view.params.GameOptionsDialog;
import gridmove.model.LevelData;
import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.SkinData;
import gridmove.view.GridFrame;
import gridmove.view.LevelInfoDialog;
import gridmove.view.Screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 * The Controller class takes inputs from the keyboard (via the KeyRelay adapter), then modifies the model and queues animations accordingly.
 * 
 */
public class Controller {
	private Animator myAnimator;
	private GridFrame myFrame;

	private JFileChooser fc;
	private JFileChooser skinChooser;
	private JFileChooser screenShotChooser;

	private static final String EXTENSION = "lvl";
	private static FileFilter GRIDMOVE_FILTER = new FileFilter() {

		@Override
		public boolean accept(File f) {
			String ext = "";
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 &&  i < s.length() - 1) {
				ext = s.substring(i+1).toLowerCase();
			}

			return ext.equals(EXTENSION) || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "*." + EXTENSION;
		}
	};

	private static FileFilter SKIN_FILTER = new FileFilter() {

		@Override
		public boolean accept(File f) {
			String ext = "";
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 &&  i < s.length() - 1) {
				ext = s.substring(i+1).toLowerCase();
			}

			return ext.equals("skn") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "*.skn";
		}
	};

	private static FileFilter GIF_FILTER = new FileFilter() {

		@Override
		public boolean accept(File f) {
			String ext = "";
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 &&  i < s.length() - 1) {
				ext = s.substring(i+1).toLowerCase();
			}

			return ext.equals("gif") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "*.gif";
		}
	};

	private static FileFilter PNG_FILTER = new FileFilter() {

		@Override
		public boolean accept(File f) {
			String ext = "";
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 &&  i < s.length() - 1) {
				ext = s.substring(i+1).toLowerCase();
			}

			return ext.equals("png") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "*.png";
		}
	};

	private static FileFilter JPG_FILTER = new FileFilter() {

		@Override
		public boolean accept(File f) {
			String ext = "";
			String s = f.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 &&  i < s.length() - 1) {
				ext = s.substring(i+1).toLowerCase();
			}

			return ext.equals("jpg") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "*.jpg";
		}
	};

	public Controller(GridFrame gf) {
		myFrame = gf;
		myAnimator = new Animator();
		myAnimator.start();
		Notifier.configureForFrame(myFrame);
		fc = new JFileChooser();
		fc.addChoosableFileFilter(GRIDMOVE_FILTER);
		skinChooser = new JFileChooser();
		skinChooser.addChoosableFileFilter(SKIN_FILTER);
		screenShotChooser = new JFileChooser();
		screenShotChooser.addChoosableFileFilter(GIF_FILTER);
		screenShotChooser.addChoosableFileFilter(PNG_FILTER);
		screenShotChooser.addChoosableFileFilter(JPG_FILTER);
		screenShotChooser.setFileFilter(GIF_FILTER);
	}

	protected GridFrame getFrame() {
		return myFrame;
	}

	public void move(Action a) {
		myAnimator.queueAction(a);
	}
	
	public void showLevelInfoDialog() {
		new LevelInfoDialog(Model.getModelFor(myFrame).getCurrentLevel(), myFrame).setVisible(true);
	}

	public void setCurrentSkin(Skin skin) {
		Model.getModelFor(myFrame).setCurrentSkin(skin);
		myFrame.getScreen().updateScreen();
		myFrame.centerOnScreen();
	}
	
	public void setCurrentLevel(LevelData data, boolean firstTime) throws Exception {
		Model.getModelFor(myFrame).getCurrentLevel().setLevelData(data);

		if(!firstTime) {
			myFrame.getScreen().updateScreen();
			myFrame.updateGemCount();
			myFrame.centerOnScreen();
		}
	}

	public LevelData loadLevelFromFile(boolean firstTime) {
		int returnVal;
		do {
			returnVal = fc.showOpenDialog((JFrame)myFrame);

			if(firstTime && returnVal == JFileChooser.CANCEL_OPTION) {
				Object[] options = {"OK", "Quit"};
				int n = JOptionPane.showOptionDialog((JFrame)myFrame,
						"You must select a file to open.",
						"Error",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,
						options,
						options[0]);
				if(n == JOptionPane.NO_OPTION)
					System.exit(0);
			}
		} while (firstTime && returnVal == JFileChooser.CANCEL_OPTION);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				return loadFromFile(fc.getSelectedFile());	
			} catch (Exception e) {
				myFrame.showErrorDialog(e);
				if(firstTime)
					return loadLevelFromFile(firstTime);
			}
		}
		
		return null;
	}

	public void saveLevelToFile() {		
		int returnVal = fc.showSaveDialog((JFrame)myFrame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			String ext = "";
			String name = file.getPath();
			int i = name.lastIndexOf('.');

			if (i > 0 &&  i < name.length() - 1)
				ext = name.substring(i+1).toLowerCase();

			if(!ext.equals(EXTENSION))
				file = new File(name + '.' + EXTENSION);

			if (file.exists()) {
				int response = JOptionPane.showConfirmDialog (null,
						"Overwrite existing file?","Confirm Overwrite",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.CANCEL_OPTION) return;
			}
			try {
				saveToFile(file, Model.getModelFor(myFrame).getCurrentLevel().getLevelData());
			} catch (Exception e) {
				myFrame.showErrorDialog(e);
			}
		}
	}

	public void loadSkinFromFile() {
		int returnVal;

		returnVal = skinChooser.showOpenDialog((JFrame)myFrame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				SkinData data;
				FileInputStream fis = new FileInputStream(skinChooser.getSelectedFile());
				ObjectInputStream in = new ObjectInputStream(fis);
				data = (SkinData)in.readObject();
				in.close();
				setCurrentSkin(Skin.getSkinFrom(data));
			} catch (Exception e) {
				myFrame.showErrorDialog(e);
			}
		}
	}

	public void saveSkinToFile(Skin s) {		
		int returnVal = skinChooser.showSaveDialog((JFrame)myFrame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = skinChooser.getSelectedFile();

			String ext = "";
			String name = file.getPath();
			int i = name.lastIndexOf('.');

			if (i > 0 &&  i < name.length() - 1)
				ext = name.substring(i+1).toLowerCase();

			if(!ext.equals("skn"))
				file = new File(name + '.' + "skn");

			if (file.exists()) {
				int response = JOptionPane.showConfirmDialog (null,
						"Overwrite existing file?","Confirm Overwrite",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.CANCEL_OPTION) return;
			}
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(s.getSkinData());
				out.close();
			} catch (Exception e) {
				myFrame.showErrorDialog(e);
			}
		}
	}

	public void saveScreenShotToFile() {
		Screen screen = myFrame.getScreen();
		BufferedImage bi = new BufferedImage(screen.getWidth(), screen.getHeight(), BufferedImage.TYPE_INT_ARGB);
		myFrame.getScreen().paintAll(bi.getGraphics());		

		int returnVal = screenShotChooser.showSaveDialog((JFrame)myFrame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = screenShotChooser.getSelectedFile();

			String ext = "";
			String name = file.getPath();
			int i = name.lastIndexOf('.');

			if (i > 0 &&  i < name.length() - 1)
				ext = name.substring(i+1).toLowerCase();

			FileFilter ff = screenShotChooser.getFileFilter();
			String chosenExt = "";

			//these are static vars, so no need for Object.equals()
			if(ff == GIF_FILTER)
				chosenExt = "gif";
			else if(ff == PNG_FILTER)
				chosenExt = "png";
			else if(ff == JPG_FILTER)
				chosenExt = "jpg";

			if(!ext.equals(chosenExt))
				file = new File(name + '.' + chosenExt);

			if (file.exists()) {
				int response = JOptionPane.showConfirmDialog (null,
						"Overwrite existing file?","Confirm Overwrite",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.CANCEL_OPTION) return;
			}
			try {
				ImageIO.write(bi, chosenExt, file);
			} catch (Exception e) {
				myFrame.showErrorDialog(e);
			}
		}
	}

	public static LevelData loadFromFile(File file) throws IOException, ClassNotFoundException {
		LevelData data = null;

		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);
		data = (LevelData)in.readObject();
//		System.out.println(in.readObject());
		in.close();

		return data;
	}

	public static void saveToFile(File file, LevelData data) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(data);
		out.close();
	}

	public void editGameSettings() {
		new GameOptionsDialog(myFrame).setVisible(true);
	}

	public Animator getAnimator() {
		return myAnimator;
	}
}
