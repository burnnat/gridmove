package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class CaveWarp extends Tile implements Warp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int roomID;
	private int x;
	private int y;

	/**
	 * This is necessary so we can properly serialize the class - we need a consructor with no args
	 */
	@SuppressWarnings("unused")
	private CaveWarp() {
		super();
	}
	
	public CaveWarp(int targetRoomID, int targetY, int targetX) {
		super();
		roomID = targetRoomID;
		y = targetY;
		x = targetX;
	}

	public int getTargetID() {
		return roomID;
	}

	public int getTargetX() {
		return x;
	}

	public int getTargetY() {
		return y;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " to " + "(" + roomID + ", " + y + ", " + x + ")";
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new CaveWarp(roomID, y, x));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(8);
	}
}
