package gridmove.model;

import java.awt.Point;

public class Utils {
	
	public static Point getLevelLocationFor(int roomID, int gridY, int gridX) {
		return new Point((roomID % Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH + gridX),
				(roomID / Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT + gridY));
	}
	
	public static int getRoomIDForLevelLocation(int levelX, int levelY) {
		double gridX = levelX;
		double gridY = levelY;
		return (int)Math.floor(gridX / Model.getActiveModel().getCurrentLevel().ROOM_WIDTH) + (int)Math.floor(gridY / Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT) * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH;
	}
}
