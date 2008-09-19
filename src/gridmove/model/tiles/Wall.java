package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Nat
 *
 * Version: 0.1
 * 
 * Yeah, it's a wall.
 *
 */
public class Wall extends Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SHRUBBERY = "Shrubbery";
	
	public static final String SHRUBBERY_CORNER_TOP_LEFT = "Shrubbery-Corner-Top Left";
	public static final String SHRUBBERY_CORNER_TOP_RIGHT = "Shrubbery-Corner-Top Right";
	public static final String SHRUBBERY_CORNER_BOTTOM_LEFT = "Shrubbery-Corner-Bottom Left";
	public static final String SHRUBBERY_CORNER_BOTTOM_RIGHT = "Shrubbery-Corner-Bottom Right";
	
	public static final String SHRUBBERY_CORNER_SHARP_TOP_LEFT = "Shrubbery-Corner-Sharp-Top Left";
	public static final String SHRUBBERY_CORNER_SHARP_TOP_RIGHT = "Shrubbery-Corner-Sharp-Top Right";
	public static final String SHRUBBERY_CORNER_SHARP_BOTTOM_LEFT = "Shrubbery-Corner-Sharp-Bottom Left";
	public static final String SHRUBBERY_CORNER_SHARP_BOTTOM_RIGHT = "Shrubbery-Corner-Sharp-Bottom Right";
	
	public static final String SHRUBBERY_EDGE_LEFT = "Shrubbery-Edge-Left";
	public static final String SHRUBBERY_EDGE_TOP = "Shrubbery-Edge-Top";
	public static final String SHRUBBERY_EDGE_RIGHT = "Shrubbery-Edge-Right";
	public static final String SHRUBBERY_EDGE_BOTTOM = "Shrubbery-Edge-Bottom";
	
	public static final String SHRUBBERY_TAPER_SQUARE_BOTTOM_LEFT = "Shrubbery-Taper-Square-Bottom Left";
	public static final String SHRUBBERY_TAPER_SQUARE_BOTTOM_RIGHT = "Shrubbery-Taper-Square-Bottom Right";
	public static final String SHRUBBERY_TAPER_SQUARE_TOP_LEFT = "Shrubbery-Taper-Square-Top Left";
	public static final String SHRUBBERY_TAPER_SQUARE_TOP_RIGHT = "Shrubbery-Taper-Square-Top Right";
	public static final String SHRUBBERY_TAPER_SQUARE_LEFT_DOWN = "Shrubbery-Taper-Square-Left Down";
	public static final String SHRUBBERY_TAPER_SQUARE_LEFT_UP = "Shrubbery-Taper-Square-Left Up";
	public static final String SHRUBBERY_TAPER_SQUARE_RIGHT_DOWN = "Shrubbery-Taper-Square-Right Down";
	public static final String SHRUBBERY_TAPER_SQUARE_RIGHT_UP = "Shrubbery-Taper-Square-Right Up";

	public static final String SHRUBBERY_TAPER_STYLIZED_BOTTOM_LEFT = "Shrubbery-Taper-Stylized-Bottom Left";
	public static final String SHRUBBERY_TAPER_STYLIZED_BOTTOM_RIGHT = "Shrubbery-Taper-Stylized-Bottom Right";
	public static final String SHRUBBERY_TAPER_STYLIZED_TOP_LEFT = "Shrubbery-Taper-Stylized-Top Left";
	public static final String SHRUBBERY_TAPER_STYLIZED_TOP_RIGHT = "Shrubbery-Taper-Stylized-Top Right";
	public static final String SHRUBBERY_TAPER_STYLIZED_LEFT_DOWN = "Shrubbery-Taper-Stylized-Left Down";
	public static final String SHRUBBERY_TAPER_STYLIZED_LEFT_UP = "Shrubbery-Taper-Stylized-Left Up";
	public static final String SHRUBBERY_TAPER_STYLIZED_RIGHT_DOWN = "Shrubbery-Taper-Stylized-Right Down";
	public static final String SHRUBBERY_TAPER_STYLIZED_RIGHT_UP = "Shrubbery-Taper-Stylized-Right Up";
	
	public static final String SHRUBBERY_DOUBLE_STYLIZED_HORIZONTAL = "Shrubbery-Double Stylized-Horizontal";
	public static final String SHRUBBERY_DOUBLE_STYLIZED_VERTICAL = "Shrubbery-Double Stylized-Vertical";
	
	public static final String SHRUBBERY_DOUBLE_TAPER_LEFT = "Shrubbery-Double Taper-Left";
	public static final String SHRUBBERY_DOUBLE_TAPER_RIGHT = "Shrubbery-Double Taper-Right";
	public static final String SHRUBBERY_DOUBLE_TAPER_UP = "Shrubbery-Double Taper-Up";
	public static final String SHRUBBERY_DOUBLE_TAPER_DOWN = "Shrubbery-Double Taper-Down";
	
	public static final String SHRUBBERY_PENINSULA_LEFT = "Shrubbery-Peninsula-Left";
	public static final String SHRUBBERY_PENINSULA_RIGHT = "Shrubbery-Peninsula-Right";
	public static final String SHRUBBERY_PENINSULA_TOP = "Shrubbery-Peninsula-Top";
	public static final String SHRUBBERY_PENINSULA_BOTTOM = "Shrubbery-Peninsula-Bottom";
	
	public static final String SHRUBBERY_COVE_STYLIZED_LEFT = "Shrubbery-Cove-Stylized-Left";
	public static final String SHRUBBERY_COVE_STYLIZED_RIGHT = "Shrubbery-Cove-Stylized-Right";
	public static final String SHRUBBERY_COVE_STYLIZED_TOP = "Shrubbery-Cove-Stylized-Top";
	public static final String SHRUBBERY_COVE_STYLIZED_BOTTOM = "Shrubbery-Cove-Stylized-Bottom";
	
	public static final String SHRUBBERY_COVE_SQUARE_LEFT = "Shrubbery-Cove-Square-Left";
	public static final String SHRUBBERY_COVE_SQUARE_RIGHT = "Shrubbery-Cove-Square-Right";
	public static final String SHRUBBERY_COVE_SQUARE_TOP = "Shrubbery-Cove-Square-Top";
	public static final String SHRUBBERY_COVE_SQUARE_BOTTOM = "Shrubbery-Cove-Square-Bottom";

	public static final String SHRUBBERY_TAPER_CORNER_TOP_LEFT = "Shrubbery-Taper-Corner-Top Left";
	public static final String SHRUBBERY_TAPER_CORNER_TOP_RIGHT = "Shrubbery-Taper-Corner-Top Right";
	public static final String SHRUBBERY_TAPER_CORNER_BOTTOM_LEFT = "Shrubbery-Taper-Corner-Bottom Left";
	public static final String SHRUBBERY_TAPER_CORNER_BOTTOM_RIGHT = "Shrubbery-Taper-Corner-Bottom Right";
	
	public static final String INSIDE_BRICK_TOP_LEFT = "Brick-Inside-Top Left";
	public static final String INSIDE_BRICK_EDGE_TOP = "Brick-Inside-Edge Top";
	public static final String INSIDE_BRICK_TOP_RIGHT = "Brick-Inside-Top Right";
	public static final String INSIDE_BRICK_EDGE_RIGHT = "Brick-Inside-Edge Right";
	public static final String INSIDE_BRICK_BOTTOM_LEFT = "Brick-Inside-Bottom Left";
	public static final String INSIDE_BRICK_EDGE_LEFT = "Brick-Inside-Edge Left";
	public static final String INSIDE_BRICK_BOTTOM_RIGHT = "Brick-Inside-Bottom Right";
	public static final String INSIDE_BRICK_EDGE_BOTTOM = "Brick-Inside-Edge Bottom";
	
	public static final String OUTSIDE_BRICK_TOP_LEFT = "Brick-Outside-Top Left";
	public static final String OUTSIDE_BRICK_EDGE_TOP = "Brick-Outside-Edge Top";
	public static final String OUTSIDE_BRICK_TOP_RIGHT = "Brick-Outside-Top Right";
	public static final String OUTSIDE_BRICK_EDGE_RIGHT = "Brick-Outside-Edge Right";
	public static final String OUTSIDE_BRICK_BOTTOM_LEFT = "Brick-Outside-Bottom Left";
	public static final String OUTSIDE_BRICK_EDGE_LEFT = "Brick-Outside-Edge Left";
	public static final String OUTSIDE_BRICK_BOTTOM_RIGHT = "Brick-Outside-Bottom Right";
	public static final String OUTSIDE_BRICK_EDGE_BOTTOM = "Brick-Outside-Edge Bottom";
	
	public static final String CRAB_BLOCK = "Crab Block";
	public static final String SOLID_BLOCK = "Solid Block";
	public static final String VOLCANO_BLOCK = "Volcano Block";
	public static final String DARK_BUSH = "Dark Bush";
	public static final String LIGHT_BUSH = "Light Bush";
	public static final String DOOR = "Door";

	public static final String BLUE_BLOCK = "Blue Block Up";
	public static final String RED_BLOCK = "Red Block Up";

	/**
	 * This is necessary so we can properly serialize the class - we need a constructor with no args
	 */
	@SuppressWarnings("unused")
	private Wall() {
		super();
	}
	
	public Wall(String type) {
		super(type);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Wall(getType()));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(5);
	}
}
