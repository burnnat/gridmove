package gridedit.view;

import gridedit.controller.Editor;
import gridmove.model.GridCoord;
import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.tiles.Tile;
import gridmove.view.Screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class EditorScreen extends Screen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean showGrid = false;

	public EditorScreen() {		
		super();
		setLayout(new FullTileLayout());
		updateScreen();
	}

	public void showGrid(boolean showGrid) {
		this.showGrid = showGrid;
		repaint();
	}

	protected void paintChildren(Graphics g) {
		super.paintChildren(g);

		if(showGrid) {
			g.setColor(Color.YELLOW.darker());

			for (int i = 0; i < Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH; i++) {
				for (int j = 1; j < Model.getActiveModel().getCurrentLevel().ROOM_WIDTH; j++) {
					Point start = Editor.getPixelLocationFor(new GridCoord(i, 0, j));
					Point end = Editor.getPixelLocationFor(new GridCoord(i + Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * (Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT - 1), Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT, j));

					g.drawLine(start.x, start.y, end.x, end.y);
				}
			}

			for (int i = 0; i < Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT; i++) {
				for (int j = 1; j < Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT; j++) {
					Point start = Editor.getPixelLocationFor(new GridCoord(i * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH, j, 0));
					Point end = Editor.getPixelLocationFor(new GridCoord((i + 1) * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH - 1, j, Model.getActiveModel().getCurrentLevel().ROOM_WIDTH));

					g.drawLine(start.x, start.y, end.x, end.y);	
				}
			}
		}

		g.setColor(Color.RED);

		for (int i = 0; i < Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT; i++) {
			Point start = Editor.getPixelLocationFor(new GridCoord(i * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH, 0, 0));
			Point end = Editor.getPixelLocationFor(new GridCoord((i + 1) * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH - 1, 0, Model.getActiveModel().getCurrentLevel().ROOM_WIDTH));

			g.drawLine(start.x, start.y, end.x, end.y);
		}

		for (int i = 0; i < Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH; i++) {
			Point start = Editor.getPixelLocationFor(new GridCoord(i, 0, 0));
			Point end = Editor.getPixelLocationFor(new GridCoord(i + Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH * (Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT - 1), Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT, 0));

			g.drawLine(start.x, start.y, end.x, end.y);
		}

		g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
		g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);

	}

//	public void paint(Graphics g) {
//	//is this a double-double buffering? Oh well, it's only the level editor.
//	Image offscreen = createImage(getWidth(), getHeight());
//	Graphics bufferGraphics = offscreen.getGraphics();

//	super.paint(bufferGraphics);

//	if(showGrid) {
//	bufferGraphics.setColor(Color.YELLOW.darker());

//	for (int i = 0; i < Model.singleton().getCurrentLevel().LEVEL_WIDTH; i++) {
//	for (int j = 1; j < Model.singleton().getCurrentLevel().ROOM_WIDTH; j++) {
//	Point start = Editor.getPixelLocationFor(new GridCoord(i, 0, j));
//	Point end = Editor.getPixelLocationFor(new GridCoord(i + Model.singleton().getCurrentLevel().LEVEL_WIDTH * (Model.singleton().getCurrentLevel().LEVEL_HEIGHT - 1), Model.singleton().getCurrentLevel().ROOM_HEIGHT, j));

//	bufferGraphics.drawLine(start.x, start.y, end.x, end.y);
//	}			
//	}

//	for (int i = 0; i < Model.singleton().getCurrentLevel().LEVEL_HEIGHT; i++) {
//	for (int j = 1; j < Model.singleton().getCurrentLevel().ROOM_HEIGHT; j++) {
//	Point start = Editor.getPixelLocationFor(new GridCoord(i * Model.singleton().getCurrentLevel().LEVEL_WIDTH, j, 0));
//	Point end = Editor.getPixelLocationFor(new GridCoord((i + 1) * Model.singleton().getCurrentLevel().LEVEL_WIDTH - 1, j, Model.singleton().getCurrentLevel().ROOM_WIDTH));

//	bufferGraphics.drawLine(start.x, start.y, end.x, end.y);	
//	}
//	}
//	}

//	bufferGraphics.setColor(Color.RED);

//	for (int i = 0; i < Model.singleton().getCurrentLevel().LEVEL_HEIGHT; i++) {
//	Point start = Editor.getPixelLocationFor(new GridCoord(i * Model.singleton().getCurrentLevel().LEVEL_WIDTH, 0, 0));
//	Point end = Editor.getPixelLocationFor(new GridCoord((i + 1) * Model.singleton().getCurrentLevel().LEVEL_WIDTH - 1, 0, Model.singleton().getCurrentLevel().ROOM_WIDTH));

//	bufferGraphics.drawLine(start.x, start.y, end.x, end.y);
//	}

//	for (int i = 0; i < Model.singleton().getCurrentLevel().LEVEL_WIDTH; i++) {
//	Point start = Editor.getPixelLocationFor(new GridCoord(i, 0, 0));
//	Point end = Editor.getPixelLocationFor(new GridCoord(i + Model.singleton().getCurrentLevel().LEVEL_WIDTH * (Model.singleton().getCurrentLevel().LEVEL_HEIGHT - 1), Model.singleton().getCurrentLevel().ROOM_HEIGHT, 0));

//	bufferGraphics.drawLine(start.x, start.y, end.x, end.y);
//	}

//	bufferGraphics.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
//	bufferGraphics.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);

//	g.drawImage(offscreen, 0, 0, this);
//	}

	/**
	 * Clears all tiles off the screen and adds current ones from the current room.
	 * Should be called when moving from room to room.
	 */
	public void updateScreen() {		
		removeAll(); //clears away all old tiles
		
		Level currentLevel = Model.getActiveModel().getCurrentLevel();

		//add the new tiles
		for (int r = 0; r < currentLevel.LEVEL_HEIGHT * currentLevel.LEVEL_WIDTH; r++) {
			for (int i = 0; i < currentLevel.ROOM_WIDTH; i++) {
				for (int j = 0; j < currentLevel.ROOM_HEIGHT; j++) {
					Tile t = currentLevel.getRoomNumber(r).getTileAt(i, j);
					if(t != null)
						addTile(t);
				}
			}
		}
		
		//and the Avatar
		currentLevel.getAvatar().setImage(null);
		addTile(currentLevel.getAvatar());

		Dimension size = new Dimension(Model.getActiveModel().getCurrentSkin().getTileSize() * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH * Model.getActiveModel().getCurrentLevel().LEVEL_WIDTH,
				Model.getActiveModel().getCurrentSkin().getTileSize() * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT * Model.getActiveModel().getCurrentLevel().LEVEL_HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		revalidate();

		//then redraw all components
//		repaint(getVisibleRect());
		repaint();
	}

}
