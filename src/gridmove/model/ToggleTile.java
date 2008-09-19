package gridmove.model;

import java.lang.reflect.InvocationTargetException;

import gridmove.model.tiles.NodeTarget;
import gridmove.model.tiles.Tile;

public class ToggleTile {
	private GridCoord myTargetCoords;
	private Tile myReplacement;
	private Tile myCache;
	private boolean activated = false;

	public ToggleTile(NodeTarget nt) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
		myTargetCoords = nt.getTargetGridLocation();
		myReplacement = Level.createTileFrom(nt.getTargetTile());

		if(myReplacement != null)
			myReplacement.setGridLocation(nt.getTargetGridLocation());
	}

	public void toggle() {
		setActivated(!activated);
	}

	public void setActivated(boolean activating) {

		//check to see if the state is changing
		if(activated == activating)
			return;
		
		//if the node is turning on, save a cache of what was there before
		if(activating) {
			GridCoord coords = myTargetCoords;
			myCache = Model.getActiveModel().getCurrentLevel().getRoomNumber(coords.room).getTileAt(coords.x, coords.y);
		} else { //if the node is turning off, delete the placed blocks, wherever they are
			Tile target = myReplacement;
			if(target != null) { //we don't need to delete something if we already did.  We can't delete nothing... that's for the cache.
				GridCoord coords;
				coords = target.getGridLocation();

				//update the coords, so blocks reappear in the position they left.
				myTargetCoords = coords;

				Model.getActiveModel().getCurrentLevel().getRoomNumber(coords.room).setTileAt(coords.x, coords.y, null);
			}
		}

		Tile replacement;
		if(activating)
			replacement = myReplacement;
		else
			replacement = myCache;

		Tile target = replacement;

		GridCoord coords = myTargetCoords;
		final int room = coords.room;
		final int gridX = coords.x;
		final int gridY = coords.y;

		if(target != null) {
			target.setGridLocation(myTargetCoords);
			target.loadFromSkin(Model.getActiveModel().getCurrentSkin());
		}

		Model.getActiveModel().getCurrentLevel().getRoomNumber(room).setTileAt(gridX, gridY, target);

		activated = activating;
	}
}