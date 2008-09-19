package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GemBlock extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberGems;
	private boolean isPassable;

	/**
	 * This is necessary so we can properly serialize the class - we need a consructor with no args
	 */
	@SuppressWarnings("unused")
	private GemBlock() {
		super();
	}
	
	public GemBlock(int numberOfGems) {
		numberGems = numberOfGems;
		isPassable = false;
	}
	
	public int getNumberOfGems() {
		return numberGems;
	}
	
	public boolean isPassable() {
		return isPassable;
	}
	
	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);

			if(!isPassable) {
				g.setColor(Color.BLACK);

				String text = "" + numberGems;
				Font font = new Font("Dialog", Font.BOLD, 14);
				g.setFont(font);
				FontMetrics fm = g.getFontMetrics();

				Rectangle2D bounds = getBounds();
				Rectangle2D textBounds = fm.getStringBounds(text, g);

				g.drawString(text, (int)((bounds.getWidth() - textBounds.getWidth()) / 2), (int)((bounds.getHeight() - textBounds.getHeight()) / 2 + fm.getAscent()));
			}
		}
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new GemBlock(numberGems));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(2);
	}
}
