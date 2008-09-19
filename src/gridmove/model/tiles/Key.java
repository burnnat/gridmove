package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class Key extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Key() {
		super();
	}
	
	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Key());
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.YELLOW.darker());
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(4);
	}
}
