package gridmove.model.tiles;

import gridmove.model.GridCoord;
import gridmove.model.Skin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public abstract class Tile extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String DEFAULT = "DEFAULT";
	public static final Tile[][][] TILE_GROUPS = new Tile[][][] {{
		{
			new Wall(Wall.SHRUBBERY_CORNER_TOP_LEFT),
			new Wall(Wall.SHRUBBERY_EDGE_TOP),
			new Wall(Wall.SHRUBBERY_CORNER_TOP_RIGHT),
			new Wall(Wall.SHRUBBERY_EDGE_LEFT),
			new Wall(Wall.SHRUBBERY),
			new Wall(Wall.SHRUBBERY_EDGE_RIGHT),
			new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_LEFT),
			new Wall(Wall.SHRUBBERY_EDGE_BOTTOM),
			new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_RIGHT),
		},
		{
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_LEFT_UP),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_TOP_LEFT),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_TOP_RIGHT),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_LEFT_DOWN),
			null,
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_UP),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_LEFT),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_RIGHT),
			new Wall(Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_DOWN),
		},
		{
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_UP),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_TOP_LEFT),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_TOP_RIGHT),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_DOWN),
			null,
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_UP),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_LEFT),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_RIGHT),
			new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_DOWN),
		},
		{
			new Wall(Wall.SHRUBBERY_TAPER_CORNER_TOP_LEFT),
			new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_UP),
			new Wall(Wall.SHRUBBERY_TAPER_CORNER_TOP_RIGHT),
			new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_LEFT),
			null,
			new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_RIGHT),
			new Wall(Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_LEFT),
			new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_DOWN),
			new Wall(Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_RIGHT),
		},
		{
			new Wall(Wall.SHRUBBERY_CORNER_SHARP_TOP_LEFT),
			new Wall(Wall.SHRUBBERY_PENINSULA_TOP),
			new Wall(Wall.SHRUBBERY_CORNER_SHARP_TOP_RIGHT),
			new Wall(Wall.SHRUBBERY_PENINSULA_RIGHT),
			new Wall(Wall.SHRUBBERY_DOUBLE_STYLIZED_HORIZONTAL),
			new Wall(Wall.SHRUBBERY_PENINSULA_LEFT),
			new Wall(Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_LEFT),
			new Wall(Wall.SHRUBBERY_PENINSULA_BOTTOM),
			new Wall(Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_RIGHT),
		},
		{
			null,
			new Wall(Wall.SHRUBBERY_COVE_STYLIZED_TOP),
			null,
			new Wall(Wall.SHRUBBERY_COVE_STYLIZED_LEFT),
			new Wall(Wall.SHRUBBERY_DOUBLE_STYLIZED_VERTICAL),
			new Wall(Wall.SHRUBBERY_COVE_STYLIZED_RIGHT),
			null,
			new Wall(Wall.SHRUBBERY_COVE_STYLIZED_BOTTOM),
			null,
		},
		{
			null,
			new Wall(Wall.SHRUBBERY_COVE_SQUARE_LEFT),
			null,
			new Wall(Wall.SHRUBBERY_COVE_SQUARE_RIGHT),
			null,
			new Wall(Wall.SHRUBBERY_COVE_SQUARE_TOP),
			null,
			new Wall(Wall.SHRUBBERY_COVE_SQUARE_BOTTOM),
			null,
		}},
		{{
			new Wall(Wall.INSIDE_BRICK_TOP_LEFT),
			new Wall(Wall.INSIDE_BRICK_EDGE_TOP),
			new Wall(Wall.INSIDE_BRICK_TOP_RIGHT),
			new Wall(Wall.INSIDE_BRICK_EDGE_LEFT),
			new Wall(Wall.SOLID_BLOCK),
			new Wall(Wall.INSIDE_BRICK_EDGE_RIGHT),
			new Wall(Wall.INSIDE_BRICK_BOTTOM_LEFT),
			new Wall(Wall.INSIDE_BRICK_EDGE_BOTTOM),
			new Wall(Wall.INSIDE_BRICK_BOTTOM_RIGHT),
		},
		{
			new Wall(Wall.OUTSIDE_BRICK_TOP_LEFT),
			new Wall(Wall.OUTSIDE_BRICK_EDGE_TOP),
			new Wall(Wall.OUTSIDE_BRICK_TOP_RIGHT),
			new Wall(Wall.OUTSIDE_BRICK_EDGE_LEFT),
			new Wall(Wall.SOLID_BLOCK),
			new Wall(Wall.OUTSIDE_BRICK_EDGE_RIGHT),
			new Wall(Wall.OUTSIDE_BRICK_BOTTOM_LEFT),
			new Wall(Wall.OUTSIDE_BRICK_EDGE_BOTTOM),
			new Wall(Wall.OUTSIDE_BRICK_BOTTOM_RIGHT),
		}},
		{{
			new Wall(Wall.DARK_BUSH),
			new Wall(Wall.CRAB_BLOCK),
			new Wall(Wall.LIGHT_BUSH),
			new Wall(Wall.BLUE_BLOCK),
			new Wall(Wall.VOLCANO_BLOCK),
			new Floor(Floor.RED_BLOCK_DOWN),
			null,
			new Placard("", ""),
			null,
		},
		{
			new RedBlueNode(),
			new CaveWarp(0, 0, 0),
			new Node(new NodeType(NodeType.STANDARD_NODE, false), null),
			new ScreenWarp(0),
			new MovableBlock(),
			new GemBlock(0),
			new Door(),
			new Gem(),
			new Key(),
		}}};

	public static final Tile[] ALL_TILES = new Tile[] {		
		new Wall(Wall.SHRUBBERY),
		new Wall(Wall.SHRUBBERY_CORNER_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_TOP_RIGHT),
		new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_EDGE_TOP),
		new Wall(Wall.SHRUBBERY_EDGE_LEFT),
		new Wall(Wall.SHRUBBERY_EDGE_RIGHT),
		new Wall(Wall.SHRUBBERY_EDGE_BOTTOM),

		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_TOP_RIGHT),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_LEFT_UP),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_LEFT_DOWN),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_UP),
		new Wall(Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_DOWN),

		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_TOP_RIGHT),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_UP),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_DOWN),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_UP),
		new Wall(Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_DOWN),

		new Wall(Wall.SHRUBBERY_TAPER_CORNER_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_CORNER_TOP_RIGHT),
		new Wall(Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_UP),
		new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_DOWN),
		new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_LEFT),
		new Wall(Wall.SHRUBBERY_DOUBLE_TAPER_RIGHT),

		new Wall(Wall.SHRUBBERY_CORNER_SHARP_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_SHARP_TOP_RIGHT),
		new Wall(Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_PENINSULA_TOP),
		new Wall(Wall.SHRUBBERY_PENINSULA_BOTTOM),
		new Wall(Wall.SHRUBBERY_PENINSULA_RIGHT),
		new Wall(Wall.SHRUBBERY_PENINSULA_LEFT),
		
		new Wall(Wall.SHRUBBERY_DOUBLE_STYLIZED_HORIZONTAL),
		new Wall(Wall.SHRUBBERY_DOUBLE_STYLIZED_VERTICAL),

		new Wall(Wall.SHRUBBERY_COVE_STYLIZED_TOP),
		new Wall(Wall.SHRUBBERY_COVE_STYLIZED_BOTTOM),
		new Wall(Wall.SHRUBBERY_COVE_STYLIZED_LEFT),
		new Wall(Wall.SHRUBBERY_COVE_STYLIZED_RIGHT),

		new Wall(Wall.SHRUBBERY_COVE_SQUARE_TOP),
		new Wall(Wall.SHRUBBERY_COVE_SQUARE_BOTTOM),
		new Wall(Wall.SHRUBBERY_COVE_SQUARE_LEFT),
		new Wall(Wall.SHRUBBERY_COVE_SQUARE_RIGHT),

		new Wall(Wall.INSIDE_BRICK_TOP_LEFT),
		new Wall(Wall.INSIDE_BRICK_TOP_RIGHT),
		new Wall(Wall.INSIDE_BRICK_BOTTOM_LEFT),
		new Wall(Wall.INSIDE_BRICK_BOTTOM_RIGHT),
		new Wall(Wall.INSIDE_BRICK_EDGE_TOP),
		new Wall(Wall.INSIDE_BRICK_EDGE_BOTTOM),
		new Wall(Wall.INSIDE_BRICK_EDGE_LEFT),
		new Wall(Wall.INSIDE_BRICK_EDGE_RIGHT),

		new Wall(Wall.OUTSIDE_BRICK_TOP_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_TOP_RIGHT),
		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_RIGHT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_TOP),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_BOTTOM),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_RIGHT),

		new Wall(Wall.SOLID_BLOCK),
		new Wall(Wall.DARK_BUSH),
		new Wall(Wall.CRAB_BLOCK),
		new Wall(Wall.LIGHT_BUSH),
		new Wall(Wall.VOLCANO_BLOCK),

		new RedBlueNode(),
		new CaveWarp(0, 0, 0),
		new Node(new NodeType(NodeType.STANDARD_NODE, false), null),
		new ScreenWarp(0),
		new MovableBlock(),
		new GemBlock(0),
		new Door(),
		new Gem(),
		new Key()
	};

	private GridCoord myLocation = new GridCoord(0, 0, 0);
	private String myType = DEFAULT;
	transient BufferedImage mySprite;

	public Tile() {
		Dimension size = new Dimension(24, 24);
		setSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}

	public Tile(String type) {
		this();
		myType = type;
	}

	public abstract Tile copy();

	protected static Tile adjustCopy(Tile original, Tile copy) {
		copy.setGridLocation(original.getGridLocation());
		return copy;
	}

	public void setSize(Dimension size) {
		//check to see that it is square
		if(size.height != size.width)
			throw new IllegalArgumentException("Tiles can only be set to sizes that are square.");

		super.setSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}

	public void setType(String type) {
		myType = type;
	}

	public synchronized void setParentRoom(int roomID) {
		myLocation.setRoom(roomID);
	}

	public synchronized void setGridLocation(int x, int y) {
		myLocation.setLocation(x, y);
	}

	public synchronized void setGridLocation(GridCoord coords) {
		myLocation = coords.copy();
	}

	/**
	 * Returns the name of this tile's function, as in "Wall"
	 */
	public String getName() {
		String name = getClass().getName();
		name = name.substring(name.lastIndexOf('.') + 1);
		return name;
	}

	public String getState() {
		return "";
	}

	public String getType() {
		return myType;
	}

	public synchronized int getParentRoomID() {
		return myLocation.room;
	}

	public synchronized int getGridX() {
		return myLocation.x;
	}

	public synchronized int getGridY() {
		return myLocation.y;
	}

	public synchronized GridCoord getGridLocation() {
		return myLocation;
	}
	
	public abstract Integer getTileLayer();

	protected abstract void paintComponent(Graphics g);

	public void loadFromSkin(Skin s) {
		if(s == null)
			return;

		mySprite = s.getImageFor(this);
		repaint();
	}

	public String toString() {
		return getName() + myLocation.toString();
	}
}
