package gridmove.controller;

import gridmove.model.tiles.Tile;

/**
 * @author Nat
 *
 * Version: 0.1
 * 
 * Abstract class for any in-game animation we'll need.
 *
 */
public abstract class Action implements Runnable {
	Tile myTarget;
	
	public Action(Tile target) {
		myTarget = target;
	}
	
	public Tile getTarget() {
		return myTarget;
	}
}
