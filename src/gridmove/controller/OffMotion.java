package gridmove.controller;

import gridmove.model.Model;
import gridmove.model.tiles.Avatar;

public class OffMotion extends Action {

	private Direction myDirection;
	private boolean entering;

	public OffMotion(Direction direction, boolean isEntering) {
		super(Model.getActiveModel().getCurrentLevel().getAvatar());

		entering = isEntering;

		myDirection = direction;
	}

	public void run() {
		((Avatar) myTarget).setFacingDirection(myDirection);
		
		int offsetX = 0;
		int offsetY = 0;

		if(entering) {
			offsetX = -myDirection.dx() * myTarget.getWidth();
			offsetY = -myDirection.dy() * myTarget.getHeight();
		}

		for (int i = 0; i < myTarget.getWidth() / Motion.PIXEL_SKIP; i++) {
			offsetX += Motion.PIXEL_SKIP * myDirection.dx();
			offsetY += Motion.PIXEL_SKIP * myDirection.dy();
			((Avatar) myTarget).setImageOffset(offsetX, offsetY);
			myTarget.repaint();
			try {
				Thread.sleep(Motion.WAIT_TIME);
			} catch (InterruptedException e) {

			}
		}
		
		//in case the pixel skip is not an even divisor of the tile size
		if(entering)
			((Avatar) myTarget).setImageOffset(0, 0);
	}
}
