package gridmove.model;

import gridmove.model.tiles.NodeTarget;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * A ToggleSet controls the toggling of all node targets.  It is is not serializable - instead, it is an active class that tracks all target tiles' whereabouts (for movable blocks).
 * 
 * @author Nat
 */
public class ToggleSet {
	private String myName;
	
//	private GridCoord[] myTargetCoords;
//	private Tile[] myReplacements;
//	private Tile[] myCache;
	
	private ToggleTile[] tiles;

	public ToggleSet(TargetSet data) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		
		myName = data.getName();
//		myReplacements = new Tile[data.getSize()];
//		myTargetCoords = new GridCoord[data.getSize()];
//		
//		for (int i = 0; i < data.getTargets().length; i++) {
//			myReplacements[i] = Level.createTileFrom(data.getTargets()[i].getTargetTile());
//			myTargetCoords[i] = data.getTargets()[i].getTargetGridLocation();
//			if(myReplacements[i] != null)
//				myReplacements[i].setGridLocation(myTargetCoords[i].x, myTargetCoords[i].y);
//		}
//		
//		myCache = new Tile[data.getSize()];
		
		List<ToggleTile> initTiles = new ArrayList<ToggleTile>();
		
		for (int i = 0; i < data.getTargets().size(); i++) {
			NodeTarget nt = data.getTargets().get(i);
			try {
				initTiles.add(new ToggleTile(nt));
			}
			catch (ClassNotFoundException e) {} //we're loading a block that is out-of-date with this gridmove version
			catch (NoSuchFieldException e) {}
		}
		
		tiles = initTiles.toArray(new ToggleTile[0]);
	}
	
	public String getName() {
		return myName;
	}

	public void toggle() {
//		//if the node is turning on, save a cache of what was there before
//		if(!activated) {
//			for (int i = 0; i < myCache.length; i++) {
//				GridCoord coords = myTargetCoords[i];
//				myCache[i] = Model.singleton().getCurrentLevel().getRoomNumber(coords.room).getTileAt(coords.x, coords.y);
//			}
//		} else { //if the node is turning off, delete the placed blocks, wherever they are
////			if(myCache != null) {
//				for (int i = 0; i < myReplacements.length; i++) {
//					Tile target = myReplacements[i];
//					if(target != null) { //we don't need to delete something if we already did.  We can't delete nothing... that's for the cache.
//						GridCoord coords;
//						coords = target.getGridLocation();
//						
//						//update the coords, so blocks reappear in the position they left.
//						myTargetCoords[i] = coords;
//						
//						Model.singleton().getCurrentLevel().getRoomNumber(coords.room).setTileAt(coords.x, coords.y, null);
//					}
//				}
////			}
//		}
//
//		Tile[] replacements;
//		if(activated)
//			replacements = myCache;
//		else
//			replacements = myReplacements;
//
//		for (int i = 0; i < replacements.length; i++) {
//			Tile target = replacements[i];
//			
//			GridCoord coords = myTargetCoords[i];
//			final int room = coords.room;
//			final int gridX = coords.x;
//			final int gridY = coords.y;
//
//			if(target != null) {
//				target.setParentRoom(room);
//				target.setGridLocation(gridX, gridY);
//				target.loadFromSkin(Model.singleton().getCurrentSkin());
//			}
//
//			Model.singleton().getCurrentLevel().getRoomNumber(room).setTileAt(gridX, gridY, target);
//		}
//		
//		activated = !activated;
		
		for (ToggleTile tt : tiles) {
			tt.toggle();
		}
		
//		for (Node node : switches) {
//			node.setActivated(activated);
//			node.loadFromSkin(Model.singleton().getCurrentSkin());	
//		}
	}
	
	public void setActivated(boolean activating) {
		for (ToggleTile tt : tiles) {
			tt.setActivated(activating);
		}
	}
}
