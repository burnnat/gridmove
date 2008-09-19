package gridmove.controller;

import gridmove.model.Model;
import gridmove.view.Screen;

public class ScreenChange extends Action {
	private int roomID;
	private ScreenUpdate update;
	
	public ScreenChange(int targetRoomID, Screen screen) {
		super(null);
		roomID = targetRoomID;
		update = new ScreenUpdate(screen);
	}


	public void run() {
		Model.getActiveModel().getCurrentLevel().getCurrentRoom().cleanup();
		Model.getActiveModel().getCurrentLevel().setCurrentRoomID(roomID);
		update.run();
	}
}
