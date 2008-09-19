package gridmove.controller;

import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.view.GridFrame;

public class LevelCompletion extends Notification {
	
	private GridFrame myFrame;
	private Direction direction;
	
	public LevelCompletion(GridFrame frame, Direction d) {
		super("Level Complete!",
				"You have completed the level!\n\nLevel Title: "
				+ Model.getActiveModel().getCurrentLevel().getLevelTitle()
				+ "\nLevel Author: "
				+ Model.getActiveModel().getCurrentLevel().getLevelAuthor());
		
		myFrame = frame;
		direction = d;
	}

	public void run() {
		Level currentLevel = Model.getActiveModel().getCurrentLevel();
		new Motion(currentLevel.getAvatar(), direction).run();
		new OffMotion(direction, false).run();
		
		super.run();
		
		currentLevel.setCurrentRoomID(currentLevel.getAvatarStartCoord().room);
		currentLevel.getAvatar().setGridLocation(currentLevel.getAvatarStartCoord());
		new ScreenUpdate(myFrame.getScreen()).run();
	}
}
