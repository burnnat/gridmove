package gridmove.model.tiles;

import java.awt.Graphics;

public class Floor extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String BLUE_BLOCK_DOWN = "Blue Block Down";
	public static final String RED_BLOCK_DOWN = "Red Block Down";

	/**
	 * This is necessary so we can properly serialize the class - we need a constructor with no args
	 */
	@SuppressWarnings("unused")
	private Floor() {
		super();
	}
	
	public Floor(String type) {
		super(type);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite != null) {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Floor(getType()));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(1);
	}
}
