package gridmove.model;

import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;

public class Room {
	protected Tile[][] tiles;
	private int myID;
	
	public Room(int roomID, Tile[][] tileData) {
		myID = roomID;
		tiles = tileData;
	}
	
	public void cleanup() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j] instanceof MovableBlock) {
					MovableBlock mb = (MovableBlock)tiles[i][j];
					if(mb.isMovable()) {
						mb.setGridLocation(mb.getStartX(), mb.getStartY());
						tiles[i][j] = null;
						tiles[mb.getStartY()][mb.getStartX()] = mb;
					}
				}
			}
		}
	}
	 
	public Tile getTileAt(int x, int y) {
		int width = Model.getActiveModel().getCurrentLevel().ROOM_WIDTH;
		int height = Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT;
		
		if(x < width && x >= 0 && y < height && y >= 0) {
			return tiles[y][x];
		}
			
		//If we're still here, the tile must be out of bounds.  If it is, we link to the next screen.
		if(x >= width) {
			Tile local = tiles[y][width - 1];
			if(local instanceof ScreenWarp)
				return local;
			else
				return new ScreenWarp(myID + 1);
		} else if(x < 0) {
			Tile local = tiles[y][0];
			if(local instanceof ScreenWarp)
				return local;
			else
				return new ScreenWarp(myID - 1);
		} else if(y >= height) {
			Tile local = tiles[height - 1][x];
			if(local instanceof ScreenWarp)
				return local;
			else
				return new ScreenWarp(myID + Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH);			
		} else { // if(y < 0).  We need to format this as an else statement to keep the compiler happy. :)
			Tile local = tiles[0][x];
			if(local instanceof ScreenWarp)
				return local;
			else
				return new ScreenWarp(myID - Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH);
		}
	}
	
	public void setTileAt(int x, int y, Tile t) {
		tiles[y][x] = t;
	}
}
