package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class MovableBlock extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FREELY_MOVABLE_BLOCK = "Freely Movable Block";
	public static final String LOCKED_BLOCK = "Locked Block";
	
	private Tile base;
	
	private int startX;
	private int startY;

	public MovableBlock() {
		super(FREELY_MOVABLE_BLOCK);
	}

	public boolean isMovable() {
		return getType().equals(FREELY_MOVABLE_BLOCK);
	}
	
	public Tile getBaseTile() {
		return base;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
	
	public void setBaseTile(Tile t) {
		base = t;
	}

	public void setMovable(boolean isMovable) {
		if(isMovable)
			setType(FREELY_MOVABLE_BLOCK);
		else
			setType(LOCKED_BLOCK);
	}

	public void setStartLocation(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
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
		MovableBlock mb = new MovableBlock();
		mb.setStartLocation(startX, startY);
		return Tile.adjustCopy(this, mb);
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(6);
	}
}
