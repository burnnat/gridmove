package gridedit.view;

import gridmove.model.Model;
import gridmove.model.tiles.Tile;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class CustomTileMenuItem extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tile myTile;
	public Gridedit myParent;
	
	public CustomTileMenuItem(String text, Tile tile, Gridedit parent) {
		super(text, new ImageIcon(Model.getActiveModel().getCurrentSkin().getImageFor(tile).getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		myTile = tile;
		myParent = parent;
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myParent.getEditor().placeTile(myTile.copy());
				myParent.getScreen().updateScreen();
			}
		});
	}
}
