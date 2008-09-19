package gridedit.controller;

import gridedit.view.Gridedit;
import gridedit.view.params.LevelMetadataParamsDialog;
import gridedit.view.params.LevelParamsDialog;
import gridedit.view.params.LevelSizeParamsDialog;
import gridedit.view.params.SkinParamsDialog;
import gridedit.view.params.TargetSetListDialog;
import gridmove.controller.Controller;
import gridmove.controller.Direction;
import gridmove.model.GridCoord;
import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.Floor;
import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.NodeTarget;
import gridmove.model.tiles.Tile;
import gridmove.model.tiles.Wall;

import java.awt.Point;

public class Editor extends Controller {
	private Gridedit myFrame;

	public Editor(Gridedit frame) {
		super(frame);
		myFrame = frame;
		Model.getModelFor(myFrame).setCurrentSkin(Skin.CLASSIC_EDITOR);
	}

	public String info() {
		Level currentLevel = Model.getModelFor(myFrame).getCurrentLevel();
		GridCoord coords = currentLevel.getAvatar().getGridLocation();
		Tile t = currentLevel.getRoomNumber(coords.room).getTileAt(coords.x, coords.y);
		if(t == null)
			return "Blank(" + coords.room + ", " + coords.y + ", " + coords.x + ")";
		else
			return t.toString();
	}

	public void move(Direction direction) {
		Level currentLevel = Model.getModelFor(myFrame).getCurrentLevel();
		Avatar avatar = currentLevel.getAvatar();
		
		int xAhead = avatar.getGridX() + direction.dx();
		int yAhead = avatar.getGridY() + direction.dy();
		int roomAhead = avatar.getParentRoomID();

		int height = currentLevel.ROOM_HEIGHT;
		int width = currentLevel.ROOM_WIDTH;

		int levelWidth = currentLevel.LEVEL_WIDTH;
		
		if(xAhead < 0 || xAhead >= width) {
			if(xAhead < 0) {
				if(mod(roomAhead, levelWidth) == 0)
					return;
				
				roomAhead += -1;
			} else {
				if(mod(roomAhead, levelWidth) == levelWidth - 1)
					return;
				
				roomAhead += 1;
			}
			
			xAhead = mod(xAhead + width, width);
		}
		
		if(yAhead < 0 || yAhead >= height) {
			if(yAhead < 0) {
				roomAhead += -levelWidth;
			} else {
				roomAhead += levelWidth;
			}
			
			yAhead = mod(yAhead + height, height);
		}
		
		Point locationAhead = getPixelLocationFor(new GridCoord(roomAhead, yAhead, xAhead));
		
		if(locationAhead.x < 0 || locationAhead.x >= myFrame.getScreen().getWidth()
				|| locationAhead.y < 0 || locationAhead.y >= myFrame.getScreen().getHeight())
			return;
		
		avatar.setGridLocation(xAhead, yAhead);
		avatar.setParentRoom(roomAhead);
//		avatar.setLocation(avatar.getX() + Model.getModelFor(myFrame).getCurrentSkin().getTileSize() * direction.dx(),
//				avatar.getY() + Model.getModelFor(myFrame).getCurrentSkin().getTileSize() * direction.dy());		
		avatar.setLocation(locationAhead);
		avatar.repaint();

		myFrame.updateLabel();
	}

	public void createNewLevel(boolean firstTime) {
		LevelSizeParamsDialog dialog = new LevelSizeParamsDialog(!firstTime);
		dialog.setVisible(true);
		Level newLevel = dialog.getNewLevel();

		if(newLevel == null)
			return;

		Model.getModelFor(myFrame).setCurrentLevel(newLevel);
		
		if(!firstTime) {
			myFrame.getScreen().updateScreen();
			myFrame.pack();
			myFrame.centerOnScreen();
		}
		
		new LevelMetadataParamsDialog(Model.getModelFor(myFrame).getCurrentLevel()).setVisible(true);
	}

	public void selectTab(int tab) {
		myFrame.getPalette().selectTab(tab);
	}

	public void selectMasterTab(int tab) {
		myFrame.getPalette().selectMasterTab(tab);
	}

	public void doClick(int i) {
		myFrame.getPalette().doClick(i);		
	}

	public void placeTile(Tile t) {
		Level currentLevel = Model.getModelFor(myFrame).getCurrentLevel();
		Avatar avatar = currentLevel.getAvatar();
		
		GridCoord coords = avatar.getGridLocation();
		int room = coords.room;
		int gridX = coords.x;
		int gridY = coords.y;

		if(t != null) {
			t.setParentRoom(room);
			t.setGridLocation(gridX, gridY);

			if(t instanceof MovableBlock)
				((MovableBlock)t).setStartLocation(gridX, gridY);				

			t.loadFromSkin(Model.getModelFor(myFrame).getCurrentSkin());

			//if we need to, update the red/blue block sets
			if(t.getType().equals(Wall.BLUE_BLOCK)) {
				try {
					currentLevel.getRedBlueTargetSet().addTarget(new NodeTarget(t.getGridLocation(), Level.getTileDataFor(new Floor(Floor.BLUE_BLOCK_DOWN))));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			else if(t.getType().equals(Floor.RED_BLOCK_DOWN)) {
				try {

					currentLevel.getRedBlueTargetSet().addTarget(new NodeTarget(t.getGridLocation(), Level.getTileDataFor(new Wall(Wall.RED_BLOCK))));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Tile erased = currentLevel.getRoomNumber(room).getTileAt(gridX, gridY);
		if(erased != null) {
			String type = currentLevel.getRoomNumber(room).getTileAt(gridX, gridY).getType();
			if(type.equals(Wall.BLUE_BLOCK) || type.equals(Floor.RED_BLOCK_DOWN)) {
				currentLevel.getRedBlueTargetSet().removeTargetAt(coords);
			}
		}

		currentLevel.getRoomNumber(room).setTileAt(gridX, gridY, t);

		myFrame.getScreen().updateScreen();
		myFrame.updateLabel();
	}

	public static Point getPixelLocationFor(GridCoord coords) {
		Level currentLevel = Model.getActiveModel().getCurrentLevel();
		int tileSize = Model.getActiveModel().getCurrentSkin().getTileSize();
		return new Point((coords.room % currentLevel.LEVEL_WIDTH * currentLevel.ROOM_WIDTH + coords.x) * tileSize,
				(coords.room / currentLevel.LEVEL_WIDTH * currentLevel.ROOM_HEIGHT + coords.y) * tileSize);
	}

	public static GridCoord getGridLocationForPixels(int xpos, int ypos) {
		int gridX = xpos / Model.getActiveModel().getCurrentSkin().getTileSize();
		int gridY = ypos / Model.getActiveModel().getCurrentSkin().getTileSize();
		return new GridCoord((int)Math.floor(gridX / Model.getActiveModel().getCurrentLevel().ROOM_WIDTH) + (int)Math.floor(gridY / Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT) * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH,
				gridY % Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT,
				gridX % Model.getActiveModel().getCurrentLevel().ROOM_WIDTH);
	}

	public void setCurrentSkin(Skin skin) {
		Model.getActiveModel().setCurrentSkin(skin);
		myFrame.getPalette().reloadImages();
		myFrame.getScreen().updateScreen();
		myFrame.centerOnScreen();
	}

	public void editLevelSettings() {
		new LevelParamsDialog(Model.getActiveModel().getCurrentLevel()).setVisible(true);
	}

	public void editCurrentSkin() {
		new SkinParamsDialog(Model.getActiveModel().getCurrentSkin(), this).setVisible(true);
	}

	public void editTargetSets() {
		new TargetSetListDialog(myFrame).setVisible(true);
	}
	
	private static int mod(int o1, int o2) {
		while(o1 < 0) {
			o1 += o2;
		}
			
		return o1 % o2;
	}
}
