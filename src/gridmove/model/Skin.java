package gridmove.model;

import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.Door;
import gridmove.model.tiles.Floor;
import gridmove.model.tiles.Gem;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.Key;
import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.NodeType;
import gridmove.model.tiles.Placard;
import gridmove.model.tiles.RedBlueNode;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;
import gridmove.model.tiles.Wall;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Skin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String FILLED_GEMCOUNTER = "GemCounter.Filled";
	private static final String UNFILLED_GEMCOUNTER = "GemCounter.Unfilled";
	public static final String BLANK_IMAGE = "NULL";

	public static final Skin CLASSIC = new Skin("Gridmove Classic");
	static {
		CLASSIC.setTileSize(0);
		CLASSIC.add(Wall.class, Wall.SHRUBBERY, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_BOTTOM_LEFT, "images/classic/2.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_BOTTOM_RIGHT, "images/classic/3.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_TOP_LEFT, "images/classic/4.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_TOP_RIGHT, "images/classic/5.gif");		

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_LEFT, "images/classic/2.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_SHARP_BOTTOM_RIGHT, "images/classic/3.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_SHARP_TOP_LEFT, "images/classic/4.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_CORNER_SHARP_TOP_RIGHT, "images/classic/5.gif");		

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_EDGE_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_EDGE_TOP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_EDGE_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_EDGE_BOTTOM, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_BOTTOM_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_TOP_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_TOP_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_LEFT_DOWN, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_LEFT_UP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_DOWN, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_SQUARE_RIGHT_UP, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_BOTTOM_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_TOP_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_TOP_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_DOWN, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_LEFT_UP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_DOWN, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_STYLIZED_RIGHT_UP, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_STYLIZED_HORIZONTAL, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_STYLIZED_VERTICAL, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_TAPER_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_TAPER_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_TAPER_UP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_DOUBLE_TAPER_DOWN, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_PENINSULA_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_PENINSULA_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_PENINSULA_TOP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_PENINSULA_BOTTOM, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_STYLIZED_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_STYLIZED_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_STYLIZED_TOP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_STYLIZED_BOTTOM, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_SQUARE_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_SQUARE_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_SQUARE_TOP, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_COVE_SQUARE_BOTTOM, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_CORNER_TOP_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_CORNER_TOP_RIGHT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_LEFT, "images/classic/1.gif");
		CLASSIC.add(Wall.class, Wall.SHRUBBERY_TAPER_CORNER_BOTTOM_RIGHT, "images/classic/1.gif");

		CLASSIC.add(Wall.class, Wall.CRAB_BLOCK, "images/classic/6.gif");
		CLASSIC.add(Wall.class, Wall.SOLID_BLOCK, "images/classic/7.gif");
		CLASSIC.add(Wall.class, Wall.VOLCANO_BLOCK, "images/classic/9.gif");
		CLASSIC.add(Wall.class, Wall.DARK_BUSH, "images/classic/10.gif");
		CLASSIC.add(Wall.class, Wall.LIGHT_BUSH, "images/classic/11.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_EDGE_BOTTOM, "images/classic/8.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_TOP, "images/classic/8.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_EDGE_TOP, "images/classic/12.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_BOTTOM, "images/classic/12.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_EDGE_LEFT, "images/classic/13.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_RIGHT, "images/classic/13.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_EDGE_RIGHT, "images/classic/14.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_LEFT, "images/classic/14.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_TOP_LEFT, "images/classic/15.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_TOP_RIGHT, "images/classic/16.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_BOTTOM_RIGHT, "images/classic/17.gif");
		CLASSIC.add(Wall.class, Wall.INSIDE_BRICK_BOTTOM_LEFT, "images/classic/18.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_TOP_LEFT, "images/classic/19.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_TOP_RIGHT, "images/classic/20.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_BOTTOM_RIGHT, "images/classic/21.gif");
		CLASSIC.add(Wall.class, Wall.OUTSIDE_BRICK_BOTTOM_LEFT, "images/classic/22.gif");
		//		CLASSIC.add(Wall.class, Wall.DOOR, "images/classic/23.gif");
		CLASSIC.add(Wall.class, Wall.BLUE_BLOCK, "images/classic/Blue Up.gif");
		CLASSIC.add(Wall.class, Wall.RED_BLOCK, "images/classic/Red Up.gif");
		CLASSIC.add(Floor.class, Floor.BLUE_BLOCK_DOWN, "images/classic/Blue Down.gif");
		CLASSIC.add(Floor.class, Floor.RED_BLOCK_DOWN, "images/classic/Red Down.gif");
		CLASSIC.add(Avatar.class, Tile.DEFAULT, "images/classic/34.gif");
		CLASSIC.add(Gem.class, Tile.DEFAULT, "images/classic/68.gif");
		CLASSIC.add(CaveWarp.class, Tile.DEFAULT, "images/classic/136.gif");
		CLASSIC.add(MovableBlock.class, MovableBlock.FREELY_MOVABLE_BLOCK, "images/classic/170.gif");
		CLASSIC.add(MovableBlock.class, MovableBlock.LOCKED_BLOCK, "images/classic/6.gif");
		CLASSIC.add(Node.class, Tile.DEFAULT, "images/classic/204.gif");
		CLASSIC.add(Node.class, NodeType.STANDARD_NODE, "images/classic/204.gif");
		CLASSIC.add(Node.class, NodeType.BLOCK_RECEPTOR, "images/classic/BR.gif");
		CLASSIC.add(RedBlueNode.class, NodeType.STANDARD_NODE + " - " + RedBlueNode.ACTIVE, "images/classic/Toggle1.gif");
		CLASSIC.add(RedBlueNode.class, NodeType.STANDARD_NODE + " - " + RedBlueNode.INACTIVE, "images/classic/Toggle2.gif");
		CLASSIC.add(GemBlock.class, Tile.DEFAULT, "images/classic/374.gif");
		CLASSIC.add(Door.class, Tile.DEFAULT + " - " + Door.CLOSED, "images/classic/23.gif");
		CLASSIC.add(Door.class, Tile.DEFAULT + " - " + Door.OPEN, BLANK_IMAGE);
		CLASSIC.add(Key.class, Tile.DEFAULT, "images/classic/Key.gif");
		CLASSIC.add(ScreenWarp.class, Tile.DEFAULT, BLANK_IMAGE);
		CLASSIC.add(Placard.class, Tile.DEFAULT, "images/classic/Sign.gif");
		//		CLASSIC.add(FILLED_GEMCOUNTER, "images/classic/FilledGemCounter.gif");
		//		CLASSIC.add(UNFILLED_GEMCOUNTER, "images/classic/EmptyGemCounter.gif");
	}

	public static final Skin CLASSIC_EDITOR = CLASSIC.copy();
	static {
		CLASSIC_EDITOR.setName("Classic Editor");
		CLASSIC_EDITOR.add(ScreenWarp.class, Tile.DEFAULT, "images/classic/ScreenWarp.gif");
	}

	//	public static final Skin FUTURA = new Skin("Futura");
	//	static {
	//	FUTURA.add(Wall.class, Wall.SHRUBBERY, "images/futura/shrubbery.gif");
	//	FUTURA.add(Wall.class, Wall.SHRUBBERY_BOTTOM_LEFT, "images/futura/shrubbery-ll.gif");
	//	FUTURA.add(Wall.class, Wall.SHRUBBERY_BOTTOM_RIGHT, "images/futura/shrubbery-lr.gif");
	//	FUTURA.add(Wall.class, Wall.SHRUBBERY_TOP_LEFT, "images/futura/shrubbery-ul.gif");
	//	FUTURA.add(Wall.class, Wall.SHRUBBERY_TOP_RIGHT, "images/futura/shrubbery-ur.gif");
	//	FUTURA.add(Wall.class, Wall.CRAB_BLOCK, "images/futura/fountain.gif");
	//	FUTURA.add(Wall.class, Wall.SOLID_BLOCK, "images/futura/7.gif");
	//	FUTURA.add(Wall.class, Wall.VOLCANO_BLOCK, "images/futura/9.gif");
	//	FUTURA.add(Wall.class, Wall.DARK_BUSH, "images/futura/10.gif");
	//	FUTURA.add(Wall.class, Wall.LIGHT_BUSH, "images/futura/11.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_EDGE_BOTTOM, "images/futura/8.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_TOP, "images/futura/8.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_EDGE_TOP, "images/futura/12.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_BOTTOM, "images/futura/12.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_EDGE_LEFT, "images/futura/13.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_RIGHT, "images/futura/13.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_EDGE_RIGHT, "images/futura/14.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_EDGE_LEFT, "images/futura/14.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_TOP_LEFT, "images/futura/15.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_TOP_RIGHT, "images/futura/16.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_BOTTOM_RIGHT, "images/futura/17.gif");
	//	FUTURA.add(Wall.class, Wall.INSIDE_BRICK_BOTTOM_LEFT, "images/futura/18.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_TOP_LEFT, "images/futura/19.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_TOP_RIGHT, "images/futura/20.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_BOTTOM_RIGHT, "images/futura/21.gif");
	//	FUTURA.add(Wall.class, Wall.OUTSIDE_BRICK_BOTTOM_LEFT, "images/futura/22.gif");
	//	FUTURA.add(Wall.class, Wall.DOOR, "images/futura/23.gif");
	//	FUTURA.add("Avatar." + Tile.DEFAULT, "images/futura/34.gif");
	//	FUTURA.add("Gem." + Tile.DEFAULT, "images/futura/gem.gif");
	//	FUTURA.add("CaveWarp." + Tile.DEFAULT, "images/futura/136.gif");
	//	FUTURA.add("MovableBlock." + Tile.DEFAULT, "images/futura/crate.gif");
	//	FUTURA.add("Node." + Tile.DEFAULT, "images/futura/204.gif");
	//	FUTURA.add("Node." + Node.TOGGLE_NODE, "images/futura/204.gif");
	//	FUTURA.add("Node." + Node.SINGLE_USE_NODE, "images/futura/204.gif");
	//	FUTURA.add("Node." + Node.BLOCK_ACTIVATED_NODE, "images/futura/204.gif");
	//	FUTURA.add("GemBlock." + Tile.DEFAULT, "images/futura/374.gif");
	//	FUTURA.add(FILLED_GEMCOUNTER, "images/futura/FilledGemCounter.gif");
	//	FUTURA.add(UNFILLED_GEMCOUNTER, "images/futura/EmptyGemCounter.gif");
	//	}

	private Map<String, String> filepaths;
	private int tileSize = 0;
	private int largestTileSize = 1;

	//this stores the loaded data of all the images. The key is the tile class/type, and the value is the image data
	private Map<String, BufferedImage> rawImages = new HashMap<String, BufferedImage>();
	//this stores the scaled versions of the images. The key is the tile class/type, and the value is the scaled data
	private Map<String, BufferedImage> scaledImages = new HashMap<String, BufferedImage>();

	private String myName;

	//the "question mark" icon for tiles not included in the skin
	private static BufferedImage DEFAULT_IMAGE;

	public Skin() {
		this("");
	}

	public Skin(String name) {
		initializeFields();
		myName = name;
		filepaths = new HashMap<String, String>();
	}

	public Skin(String name, Map<String, String> filenames) {
		initializeFields();
		myName = name;
		filepaths = filenames;
		reloadImages();
	}

	private void initializeFields() {
		this.tileSize = 0;
		if(DEFAULT_IMAGE == null) {
			try {
				DEFAULT_IMAGE = ImageIO.read(new File("images/default.gif"));
			} catch (IOException e) {
				DEFAULT_IMAGE = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
				e.printStackTrace();
			}
		}
	}

	public void reloadImages() {
		for (String tileType : filepaths.keySet()) {
			reloadImage(tileType, false);
		}
	}

	private void reloadImage(String tileType, boolean resizeToFit) {
		BufferedImage image;
		try {
			String filename = filepaths.get(tileType);
			if(filename != null)
				if(filename.equals(BLANK_IMAGE)) {
					image = new BufferedImage(getTileSize(), getTileSize(), BufferedImage.TYPE_INT_ARGB);
				} else {
					image = ImageIO.read(new File(filepaths.get(tileType)));
				}
			else 
				image = DEFAULT_IMAGE;

			if(image.getWidth(null) > largestTileSize)
				largestTileSize = image.getWidth(null);

		} catch (IOException e) {
			image = DEFAULT_IMAGE;
		}

		rawImages.put(tileType, image);
	}

	private BufferedImage resizeToFit(BufferedImage tileImage) {
		if(tileImage == null)
			return null;

		double scaleX = (double) getTileSize() / (double) tileImage.getWidth();
		double scaleY = (double) getTileSize() / (double) tileImage.getHeight();

		AffineTransformOp scaling = new AffineTransformOp(AffineTransform.getScaleInstance(scaleX, scaleY), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return scaling.filter(tileImage, null);
	}

	public BufferedImage getImageFor(Tile t) {		
		if(t == null) {
			return null;
		} else {
			String tileType = t.getName() + '.' + t.getType() + (t.getState().equals("") ? "" : " - " + t.getState());

			if(rawImages.containsKey(tileType)) {
				BufferedImage i = scaledImages.get(tileType);

				if(i != null) {
					return i;
				} else {
					BufferedImage scaled = resizeToFit(rawImages.get(tileType));
					scaledImages.put(tileType, scaled);
					return scaled;
				}
			} else {
				return DEFAULT_IMAGE;
			}
		}
	}

	/**
	 * Fetches this skin's image for the GemCounter.
	 * 
	 * @param filled - whether the image requested is the 'filled' image.
	 * @return The requested image if available, null if not.
	 */
	public Image getGemCounterImage(boolean filled) {
		String key = UNFILLED_GEMCOUNTER;
		if(filled)
			key = FILLED_GEMCOUNTER;
		return rawImages.get(key);
	}

	/**
	 * Gets the filepaths for each tiletype stored in this skin.
	 * 
	 * @return A copy of this skin's tiletype-to-filepath map.
	 */
	public final Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();

		for (String key : filepaths.keySet()) {
			map.put(key, filepaths.get(key));
		}

		return map;
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}

	/**
	 * Adds an image file to this skin
	 * @param tileClass - the class of the tile, such as {@link Wall.class}.
	 * @param tileType - a string tileType constant, as in {@link Wall.SHRUBBERY}.
	 * @param filename
	 */
	public void add(Class<? extends Tile> tileClass, String tileType, String filename) {
		add(tileClass.getSimpleName() + '.' + tileType, filename);
	}

	/**
	 * Adds an image file to this skin
	 * @param tileType - must be in the form "TileName.TILE_TYPE", where TILE_TYPE is a string tileType constant, as in Wall.SHRUBBERY.
	 * @param filename
	 */
	public void add(String tileKey, String filename) {
		filepaths.put(tileKey, filename);
		reloadImage(tileKey, true);
	}

	public int getTileSize() {
		if(tileSize > 0)
			return tileSize;
		else
			return largestTileSize;
	}

	//	public int getMaxTileSize() {
	//		return maxTileSize;
	//	}

	public void setTileSize(int tileSize) {
		if(this.tileSize != tileSize)
			scaledImages.clear();

		this.tileSize = tileSize;
		reloadImages();
	}

	public Skin copy() {
		return new Skin(myName, filepaths);
	}

	public static Skin getSkinFrom(SkinData data) {
		Skin s = new Skin(data.getName(), data.getMap());
		s.setTileSize(data.getTileSize());
		return s;
	}

	public SkinData getSkinData() {
		return new SkinData(myName, filepaths, tileSize);
	}
}