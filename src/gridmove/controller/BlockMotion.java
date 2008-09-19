package gridmove.controller;

import gridmove.model.Model;
import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.MovableBlock;

public class BlockMotion extends Action {

	private Direction myDirection;
	private boolean becomesLocked;
	
	public BlockMotion(MovableBlock block, boolean becomesLocked, Direction direction) {
		super(block);
		myDirection = direction;
		this.becomesLocked = becomesLocked;
	}
	
	public void run() {
		Avatar avatar = Model.getActiveModel().getCurrentLevel().getAvatar();
		
		for (int i = 0; i < myTarget.getWidth() / Motion.PIXEL_SKIP; i++) {
			myTarget.setLocation(myTarget.getX() + Motion.PIXEL_SKIP * myDirection.dx(), myTarget.getY() + Motion.PIXEL_SKIP * myDirection.dy());
			avatar.setLocation(avatar.getX() + Motion.PIXEL_SKIP * myDirection.dx(), avatar.getY() + Motion.PIXEL_SKIP * myDirection.dy());
			myTarget.repaint();
			avatar.repaint();
			try {
				Thread.sleep(Motion.WAIT_TIME);
			} catch (InterruptedException e) {
				
			}
		}

		final int xAhead = myTarget.getGridX() + myDirection.dx();
		final int yAhead = myTarget.getGridY() + myDirection.dy();
		
		myTarget.setGridLocation(xAhead, yAhead);
		myTarget.setLocation(myTarget.getGridX() * myTarget.getWidth(), myTarget.getGridY() * myTarget.getWidth());
		avatar.setGridLocation(avatar.getGridX() + myDirection.dx(), avatar.getGridY() + myDirection.dy());
		avatar.setLocation(avatar.getGridX() * avatar.getWidth(), avatar.getGridY() * avatar.getWidth());
		
		if(becomesLocked) {
			((MovableBlock)myTarget).setMovable(false);
			myTarget.loadFromSkin(Model.getActiveModel().getCurrentSkin());
			Model.getActiveModel().getCurrentLevel().getCurrentRoom().setTileAt(xAhead, yAhead, myTarget);
		}
	}
}
