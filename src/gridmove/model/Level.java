package gridmove.model;

import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.NodeTarget;
import gridmove.model.tiles.NodeType;
import gridmove.model.tiles.RedBlueNode;
import gridmove.model.tiles.Tile;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;


/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Level {

	//all of the rooms in this level
	private Room[] rooms;

	//the ID of the room currently displayed
	private int currentRoom = 0;

	//the start location of the Avatar.
	private GridCoord avatarStartCoord;

	//the avatar itself
	private Avatar theOne;
	
	private int collectedGems = 0;
	private int collectedKeys = 0;

	private int totalGems;

	private String levelTitle;
	private String levelAuthor;
	private String levelNumber;

	private Map<String, ToggleSet> toggleSets = new HashMap<String, ToggleSet>();
	private ToggleSet redBlueSet;
	private TargetSet[] targetSets;
	private TargetSet redBlueTargetSet;

	/*
	 * Instead of using constants like this, the metrics of the level should be loaded at the same time we load the level data
	 */
	public int LEVEL_WIDTH;   //the number of screens wide the level is
	public int LEVEL_HEIGHT;  //the number of screens tall the level is 

	public int ROOM_HEIGHT; //the tile dimensions of this level's rooms

	public int ROOM_WIDTH;

	public Level(LevelData data) {
		try {
			theOne = new Avatar();
			loadData(data);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void collectGem() {
		collectedGems++;
	}

	public void collectKey() {
		collectedKeys++;
	}

	public void useKey() {
		collectedKeys--;
	}
	
	public Avatar getAvatar() {
		return theOne;
	}

	public GridCoord getAvatarStartCoord() {
		return avatarStartCoord;
	}
	
//	public int getAvatarStartRoom() {
//		return avatarStartRoom;
//	}
//
//	public int getAvatarStartX() {
//		return avatarStartX;
//	}
//
//	public int getAvatarStartY() {
//		return avatarStartY;
//	}

	public Room getCurrentRoom() {
		return getRoomNumber(currentRoom);
	}

	public int getCurrentRoomID() {
		return currentRoom;
	}

	public int getGemsCollected() {
		return collectedGems;
	}

	public int getKeysCollected() {
		return collectedKeys;
	}

	public String getLevelAuthor() {
		return levelAuthor;
	}
	
	public String getLevelNumber() {
		return levelNumber;
	}

	public LevelData getLevelData() throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		LevelData data = new LevelData(levelTitle, levelAuthor, levelNumber, avatarStartCoord.room, avatarStartCoord.y, avatarStartCoord.x, totalGems, LEVEL_HEIGHT, LEVEL_WIDTH, ROOM_HEIGHT, ROOM_WIDTH, redBlueTargetSet, targetSets);

		for (int r = 0; r < rooms.length; r++) {
			for (int i = 0; i < ROOM_HEIGHT; i++) {
				for (int j = 0; j < ROOM_WIDTH; j++) {
					Point location = Utils.getLevelLocationFor(r, i, j);
					Tile t = rooms[r].getTileAt(j, i);
					TileData td = null;
					if(t != null) {
						td = getTileDataFor(t);
					}
					data.setTileDataAt(location.x, location.y, td);
				}
			}
		}

		return data;
	}

	public String getLevelTitle() {
		return levelTitle;
	}

	public Room getRoomNumber(int id) {
		if(id < rooms.length && id >= 0)
			return rooms[id];
		else
			return null;
	}

	public int getTotalGems() {
		return totalGems;
	}

	private void loadData(LevelData data) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
		if(data == null)
			return;

		levelTitle = data.getLevelTitle();
		levelAuthor = data.getLevelAuthor();
		levelNumber = data.getLevelNumber();
		totalGems = data.getTotalGems();

		avatarStartCoord = new GridCoord(data.getStartRoom(), data.getStartY(), data.getStartX());
		collectedGems = 0;

		LEVEL_HEIGHT = data.LEVEL_HEIGHT;
		LEVEL_WIDTH = data.LEVEL_WIDTH;
		ROOM_HEIGHT = data.ROOM_HEIGHT;
		ROOM_WIDTH = data.ROOM_WIDTH;

		rooms = new Room[LEVEL_WIDTH * LEVEL_HEIGHT];

		int yOffset = 0;
		int xOffset = 0;
		ArrayList<String> classErrors = new ArrayList<String>();

		for (int r = 0; r < rooms.length; r++) {
			Tile[][] tiles = new Tile[ROOM_HEIGHT][ROOM_WIDTH];
			for (int i = 0; i < ROOM_HEIGHT; i++) {
				for (int j = 0; j < ROOM_WIDTH; j++) {
					Point levelLocation = new Point((r % LEVEL_WIDTH * ROOM_WIDTH + j),	(r / LEVEL_WIDTH * ROOM_HEIGHT + i));
					if(data.getTileDataAt(levelLocation.x, levelLocation.y) != null) {
						try {
							tiles[i][j] = createTileFrom(data.getTileDataAt(levelLocation.x, levelLocation.y));
							tiles[i][j].loadFromSkin(Model.getActiveModel().getCurrentSkin());
						} catch (ClassNotFoundException e) { //we're loading a block that no longer exists in this gridmove version
							tiles[i][j] = null;
							classErrors.add(e.getMessage());
						} catch (NoSuchFieldException e) {
							tiles[i][j] = null;
							TileData offendor = data.getTileDataAt(levelLocation.x, levelLocation.y);
							classErrors.add(offendor.myClassName.substring(offendor.myClassName.lastIndexOf('.') + 1));
						}
					}
					else {
						tiles[i][j] = null;
					}
				}
			}

			rooms[r] = new Room(r, tiles);

			if((r + 1) % LEVEL_WIDTH == 0) { //we're moving to the next "row" of rooms
				xOffset = 0;
				yOffset += ROOM_HEIGHT;
			} else { //we're just moving laterally to the next room in this row
				xOffset += ROOM_WIDTH;
			}
		}

		redBlueTargetSet = data.getRedBlueSet();
		targetSets = data.getTargetSets();
		initializeTileSets();

		if(classErrors.size() > 0) {
			String message = "A level was loaded containing a tile that was out-of-date with this version of Gridmove. The offending tile(s) were:";

			Map<String, Integer> occurances = new HashMap<String, Integer>();
			
			for (String name : classErrors) {
				int total = 0;
				if(occurances.containsKey(name))
					total = occurances.get(name);
				occurances.put(name, total + 1);
			}
			
			for (String name : occurances.keySet()) {
				message = message + '\n' + name + " (" + occurances.get(name) + " occurances)";
			}

			JOptionPane.showMessageDialog(null, message, "Problem Loading Level", JOptionPane.WARNING_MESSAGE);
		}

		//set the Avatar's initial position
		setCurrentRoomID(avatarStartCoord.room);
		getAvatar().setParentRoom(avatarStartCoord.room);
		getAvatar().setGridLocation(avatarStartCoord.x, avatarStartCoord.y);
		getAvatar().loadFromSkin(Model.getActiveModel().getCurrentSkin());
	}

	private void initializeTileSets() throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		List<RedBlueNode> nodes = new ArrayList<RedBlueNode>();
		for (Room r : rooms) {
			for (Tile[] ts : r.tiles) {
				for (Tile t : ts) {
					if(t instanceof RedBlueNode) {
						RedBlueNode node = (RedBlueNode)t;
						nodes.add(node);
					}
				}
			}
		}

		if(redBlueTargetSet == null)
			redBlueTargetSet = new TargetSet(RedBlueSet.RED_BLUE_SET, new ArrayList<NodeTarget>());

		redBlueSet = new RedBlueSet(redBlueTargetSet, nodes.toArray(new RedBlueNode[0]));

//		Map<String, List<Node>> nodeSets = new HashMap<String, List<Node>>();
//		for (Room r : rooms) {
//		for (Tile[] ts : r.tiles) {
//		for (Tile t : ts) {
//		if(t instanceof Node) {
//		Node node = (Node)t;
//		if(nodeSets.get(node.getTargetSetName()) == null)
//		nodeSets.put(node.getTargetSetName(), new ArrayList<Node>());
//		nodeSets.get(node.getTargetSetName()).add(node);
//		}
//		}
//		}
//		}

		for (TargetSet targetSet : targetSets) {
			toggleSets.put(targetSet.getName(), new ToggleSet(targetSet));	
		}
	}

	public void setAvatarStartRoom(int avatarStartRoom) {
		avatarStartCoord.setRoom(avatarStartRoom);
	}

	public void setAvatarStartX(int avatarStartX) {
		avatarStartCoord.x = avatarStartX;
	}

	public void setAvatarStartY(int avatarStartY) {
		avatarStartCoord.y = avatarStartY;
	}

	public void setCurrentRoomID(int roomID) {
		currentRoom = roomID;
	}

	public void setLevelAuthor(String levelAuthor) {
		this.levelAuthor = levelAuthor;
	}

	public void setLevelData(LevelData data) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
		loadData(data);
	}
	
	public void setLevelNumber(String levelNumber) {
		this.levelNumber = levelNumber;
	}

	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}

	public static Tile createTileFrom(TileData td) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		if(td == null)
			return null;

		Class<?> tileClass = Class.forName(td.myClassName);
		Constructor<?> c = tileClass.getDeclaredConstructor(new Class<?>[]{});
		c.setAccessible(true);
		Tile t = (Tile)c.newInstance(new Object[]{});

		Class<?> currentClass = tileClass;
		for (Map<String, Object> fields : td.myFieldGroups) {
			for (String key : fields.keySet()) {
				Field f = currentClass.getDeclaredField(key);
				f.setAccessible(true);

				Object datum = fields.get(key);
				if(f.getType().equals(TileData.class))
					datum = createTileFrom((TileData)datum);

				f.set(t, datum);
			}
			currentClass = currentClass.getSuperclass();
		}

		return t;
	}

	public static TileData getTileDataFor(Tile t) throws IllegalArgumentException, IllegalAccessException {
		if(t == null)
			return null;

		Class<?> tileClass;
		tileClass = t.getClass();

		List<Map<String, Object>> fieldGroups = new ArrayList<Map<String, Object>>();

		Class<?> currentLevel = tileClass;
		do {
			Map<String, Object> fields = new HashMap<String, Object>();
			//get the instance-specific (ScreenWarp target ID, for instance)
			for (Field f : currentLevel.getDeclaredFields()) {
				f.setAccessible(true);
				if(!Modifier.isStatic(f.getModifiers()) && !Modifier.isTransient(f.getModifiers())) {
					Object datum = f.get(t);

					//don't write any Tile objects!!!
					if(f.getType().getSuperclass() != null && datum != null) {
						Class<?> tester = f.getType();
						boolean isTileInstance = false;
						while(!Object.class.equals(tester)) {
							if(Tile.class.equals(tester))
								isTileInstance = true;
							tester = tester.getSuperclass();
						}
						if(isTileInstance)
							datum = getTileDataFor((Tile)datum);
					}

					fields.put(f.getName(), datum);
				}
			}
			fieldGroups.add(fields);

			//stop once we've copied all the tile data (we don't want to mess with the swing stuff)
			if(Tile.class.equals(currentLevel))
				break;

			currentLevel = currentLevel.getSuperclass();
		} while(!Object.class.equals(currentLevel)); //safety: stop when we hit the Object class

		return new TileData(tileClass.getName(), fieldGroups);
	}

	public void reloadImageData() {
		if(rooms == null)
			return;

		for (int r = 0; r < rooms.length; r++) {
			for (int i = 0; i < ROOM_HEIGHT; i++) {
				for (int j = 0; j < ROOM_WIDTH; j++) {
					Tile t = rooms[r].getTileAt(j, i);
					if(t != null) {
						t.loadFromSkin(Model.getActiveModel().getCurrentSkin());
					}
				}
			}
		}
		
		getAvatar().loadFromSkin(Model.getActiveModel().getCurrentSkin());
	}

	public void setTotalGems(int totalGems) {
		this.totalGems = totalGems;
	}

	public ToggleSet getToggleSet(String name) {
		if(name.equals(RedBlueSet.RED_BLUE_SET))
			return redBlueSet;
		else
			return toggleSets.get(name);
	}

	public TargetSet[] getAllTargetSets() {
		return targetSets;
	}

	public TargetSet getRedBlueTargetSet() {
		return redBlueTargetSet;
	}

	public void setTargetSets(TargetSet[] sets) {
		targetSets = sets;

		for (Room r : rooms) {
			for (int i = 0; i < ROOM_HEIGHT; i++) {
				for (int j = 0; j < ROOM_WIDTH; j++) {
					if (r.getTileAt(j, i) instanceof Node) {
						Node node = (Node) r.getTileAt(j, i);
						
						List<String> toRemove = new ArrayList<String>();
						
						for (String setName : node.getTargetSets()) {

							boolean isValid = false;
							for (TargetSet set : targetSets) {
								if(setName.equals(set.getName()) || setName.equals(RedBlueSet.RED_BLUE_SET)) {
									isValid = true;
									break;
								}
							}
							
							if(!isValid)
								toRemove.add(setName);
						}
						
						for (String set : toRemove) {
							node.removeTargetSet(set);
						}
						
						if(node.getTargetSets().size() == 0 && !node.getType().equals(NodeType.BLOCK_RECEPTOR)) 
							r.setTileAt(j, i, null);
					}
				}
			}
		}

		try {
			initializeTileSets();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
