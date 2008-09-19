package gridmove.model.tiles;

import java.awt.Graphics;

public class ScreenWarp extends Tile implements Warp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int targetID;

	/**
	 * This is necessary so we can properly serialize the class - we need a consructor with no args
	 */
	@SuppressWarnings("unused")
	private ScreenWarp() {
		super();
	}
	
	/**
	 * Constructs a ScreenWarp that points to another room to be loaded onto the screen
	 * @param targetRoomID - the roomID of the target room
	 */
	public ScreenWarp(int targetRoomID) {
		targetID = targetRoomID;
	}
	
	public int getTargetID() {
		return targetID;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite != null) {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	public String toString() {
		return super.toString() + " to " + targetID; 
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new ScreenWarp(targetID));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(0);
	}
}
