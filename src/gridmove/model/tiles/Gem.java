package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class Gem extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This no-args constructor fufills the serialization
	 */
	public Gem() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Gem());
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(4);
	}
}
