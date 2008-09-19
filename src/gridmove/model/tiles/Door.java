package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String OPEN = "Open";
	public static final String CLOSED = "Closed";
	
	private boolean open = false;

	public Door() {
		super();
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean isOpen) {
		open = isOpen;
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Door());
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null && open == false) {
			g.setColor(new Color(139, 69, 19));
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public String getState() {
		String descriptor;

		if(open)
			descriptor = OPEN;
		else
			descriptor = CLOSED;

		return descriptor;
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(3);
	}
}
