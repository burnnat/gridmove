package gridmove.controller;

import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.Tile;

/**
 * @author Nat
 *
 * Version: 0.1
 * 
 * The Action representing the motion of any particular Tile - currently only implemented for the Avatar.
 * WAIT_TIME controls the speed of motion.  Obviously, the smaller the WAIT_TIME, the faster it moves.
 * It's currently set to 6 milliseconds.
 *
 */
public class Motion extends Action {
	
	public static long WAIT_TIME = 4;
	public static int PIXEL_SKIP = 1;

	private Direction myDirection;
	
	public Motion(Tile target, Direction direction) {
		super(target);
		myDirection = direction;
	}

	public void run() {
		if(myTarget instanceof Avatar)
			((Avatar) myTarget).setFacingDirection(myDirection);
		
		for (int i = 0; i < myTarget.getWidth() / PIXEL_SKIP; i++) {
			myTarget.setLocation(myTarget.getX() + PIXEL_SKIP * myDirection.dx(), myTarget.getY() + PIXEL_SKIP * myDirection.dy());
			myTarget.repaint();
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				
			}
		}
		
		myTarget.setGridLocation(myTarget.getGridX() + myDirection.dx(), myTarget.getGridY() + myDirection.dy());
		myTarget.setLocation(myTarget.getGridX() * myTarget.getWidth(), myTarget.getGridY() * myTarget.getWidth());
	}
}
