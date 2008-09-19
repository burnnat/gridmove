package gridmove.model;

import java.io.Serializable;

/**
 * 
 * LevelData is the serializable instance of the level class, basically just wrapping all of its members without dealing with tile objects.
 * 
 * @author Nat
 */
public class LevelData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String levelTitle;
	private String levelAuthor;
	private String levelNumber;

	private int avatarStartX;
	private int avatarStartY;
	private int avatarStartRoom;
	private int totalGems;

	public int LEVEL_WIDTH; //the room dimensions of this level
	public int LEVEL_HEIGHT;

	public int ROOM_HEIGHT; //the tile dimensions of this level's rooms
	public int ROOM_WIDTH;

	private TileData[][] data;
	private TargetSet[] targetSets;
	private TargetSet redBlueSet;

	public LevelData(String levelTitle, String levelAuthor, String levelNumber,
			int avatarStartRoomID, int avatarStartY, int avatarStartX,
			int totalGems,
			int levelHeight, int levelWidth,
			int roomHeight, int roomWidth,
			TargetSet redBlueSet,
			TargetSet[] targetSets) {
		this.levelTitle = levelTitle;
		this.levelAuthor = levelAuthor;
		this.levelNumber = levelNumber;
		avatarStartRoom = avatarStartRoomID;
		this.avatarStartX = avatarStartX;
		this.avatarStartY = avatarStartY;
		this.totalGems = totalGems;
		LEVEL_WIDTH = levelWidth;
		LEVEL_HEIGHT = levelHeight;
		ROOM_HEIGHT = roomHeight;
		ROOM_WIDTH = roomWidth;
		data = new TileData[LEVEL_HEIGHT * ROOM_HEIGHT][LEVEL_WIDTH * ROOM_WIDTH];
		this.redBlueSet = redBlueSet; 
		this.targetSets = targetSets;
	}

	public String getLevelTitle() {
		return levelTitle;
	}

	public String getLevelAuthor() {
		return levelAuthor;
	}

	public String getLevelNumber() {
		return levelNumber;
	}

	public int getStartRoom() {
		return avatarStartRoom;
	}

	public int getStartX() {
		return avatarStartX;
	}

	public int getStartY() {
		return avatarStartY;
	}
	
	public int getTotalGems() {
		return totalGems;
	}

	public TileData getTileDataAt(int x, int y) {
		return data[y][x];
	}

	public void setTileDataAt(int x, int y, TileData t) {
			data[y][x] = t;
	}
	
	public TargetSet getRedBlueSet() {
		return redBlueSet;
	}

	public TargetSet[] getTargetSets() {
		if(targetSets == null)
			return new TargetSet[0];
		else
			return targetSets;
	}
}
