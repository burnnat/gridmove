package gridedit.view;

import gridmove.model.Model;
import gridmove.model.tiles.Tile;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tile myTile;
	
	public TileButton(ImageIcon icon, Tile t) {
		super(icon);
		setFocusable(false);
		if(icon != null) {
			setMinimumSize(new Dimension(icon.getIconWidth() + 6, icon.getIconHeight() + 6));
			setPreferredSize(new Dimension(icon.getIconWidth() + 27, icon.getIconHeight() + 27));
		}
		myTile = t;
		if(t != null) {
			String name = t.getType();
			if(name.equals(Tile.DEFAULT))
				name = t.getName();
			setToolTipText(name);
		}
	}
	
	public Tile getTile() {
		return myTile;
	}
	
	public void reloadImage() {
		ImageIcon icon;
		
		if(Model.getActiveModel().getCurrentSkin().getImageFor(myTile) != null)
			icon = new ImageIcon(Model.getActiveModel().getCurrentSkin().getImageFor(myTile).getScaledInstance(24, 24, Image.SCALE_SMOOTH));
		else
			icon = null;
		
		setIcon(icon);
	}
}
