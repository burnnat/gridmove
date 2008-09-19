package gridedit.view;

import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.Wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CustomTileMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFileChooser fc = new JFileChooser();
	private Gridedit myParent;
	private Map<String, String> filepaths = new HashMap<String, String>();
	
	public CustomTileMenu(String title, Gridedit parent) {
		super(title);
		myParent = parent;
		
		JMenuItem addCustomItem = new JMenuItem("Load Custom Tile...");
		add(addCustomItem);
		addCustomItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fc.showOpenDialog(CustomTileMenu.this) == JFileChooser.APPROVE_OPTION) {
					String name = fc.getSelectedFile().getName();
					Model.getActiveModel().getCurrentSkin().add(Wall.class, name, fc.getSelectedFile().getPath());
					filepaths.put("Wall." + name, fc.getSelectedFile().getPath());
					Wall myTile = new Wall(name);
					CustomTileMenuItem customTile = new CustomTileMenuItem(name, myTile, myParent);
					add(customTile, 0);
					myParent.getScreen().updateScreen();
					myParent.centerOnScreen();
				}
			}
		});
	}
	
	public void addToSkin(Skin s) {
		if(filepaths.size() > 0) {
			for (String key : filepaths.keySet()) {
				s.add(key, filepaths.get(key));
			}
		}
	}
}
