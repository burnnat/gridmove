package gridmove.controller;

import gridmove.model.Model;
import gridmove.view.Screen;

public class ScreenMotion extends Action {
	private ScreenChange screenChange;
	private Direction myDirection;
	private OffMotion offMotion;
	private OffMotion onMotion;
	
	public ScreenMotion(int targetRoomID, Direction direction, Screen screen) {
		super(Model.getActiveModel().getCurrentLevel().getAvatar());
		screenChange = new ScreenChange(targetRoomID, screen);
		myDirection = direction;
		offMotion = new OffMotion(direction, false);
		onMotion = new OffMotion(direction, true);
	}

	public void run() {
		int x = myTarget.getGridX() + myDirection.dx();
		int y = myTarget.getGridY() + myDirection.dy();
		
		if(myDirection == Direction.UP)
			y = Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT - 1;
		if(myDirection == Direction.DOWN)
			y = 0;
		if(myDirection == Direction.LEFT)
			x = Model.getActiveModel().getCurrentLevel().ROOM_WIDTH - 1;
		if(myDirection == Direction.RIGHT)
			x = 0;
				
		offMotion.run();

		myTarget.setGridLocation(x, y);
		
		screenChange.run();		
		
		onMotion.run();
		
//		myTarget.setGridLocation(x + myDirection.dx(), y + myDirection.dy());
	}
}