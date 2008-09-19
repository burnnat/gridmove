package gridmove.view;

import gridmove.model.Model;
import gridmove.model.tiles.Tile;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class TileLayout implements LayoutManager {

	public void layoutContainer(Container parent) {
		if(!(parent instanceof Screen))
			return;
		
		int tileSize = Model.getActiveModel().getCurrentSkin().getTileSize();
		
		for (Component compy : parent.getComponents()) {
			if (compy instanceof Tile) {
				Tile tile = (Tile) compy;
				tile.setSize(tileSize, tileSize);
				tile.setLocation(tile.getGridX() * tileSize, tile.getGridY() * tileSize);
			}
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentSkin().getTileSize(), Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentSkin().getTileSize());
	}

	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentSkin().getTileSize(), Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentSkin().getTileSize());
	}

	public void removeLayoutComponent(Component comp) {
		//nothing here
	}

	public void addLayoutComponent(String name, Component comp) {
		//nothing here either
	}

}
