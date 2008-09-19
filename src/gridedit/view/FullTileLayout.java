package gridedit.view;

import gridedit.controller.Editor;
import gridmove.model.Model;
import gridmove.model.tiles.Tile;
import gridmove.view.Screen;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;

public class FullTileLayout implements LayoutManager2 {

	public void layoutContainer(Container parent) {
		if(!(parent instanceof Screen))
			return;

		int tileSize = Model.getActiveModel().getCurrentSkin().getTileSize();
		
		for (Component compy : parent.getComponents()) {
			if (compy instanceof Tile) {
				Tile tile = (Tile) compy;
				tile.setSize(tileSize, tileSize);
				tile.setLocation(Editor.getPixelLocationFor(tile.getGridLocation()));	
			}
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentSkin().getTileSize(),
				Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentSkin().getTileSize());
	}

	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentSkin().getTileSize(),
				Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentSkin().getTileSize());
	}

	public void removeLayoutComponent(Component comp) {
		//nothing here
	}

	public void addLayoutComponent(String name, Component comp) {
		//nothing here either
	}

	public void addLayoutComponent(Component comp, Object constraints) {
		// or here		
	}

	public float getLayoutAlignmentX(Container target) {
		// we want it centered
		return (float) 0.5;
	}

	public float getLayoutAlignmentY(Container target) {
		// centered here too
		return (float) 0.5;
	}

	public void invalidateLayout(Container target) {
		// nothing here
	}

	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentSkin().getTileSize(),
				Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentSkin().getTileSize());
	}
}
