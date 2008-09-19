package gridmove.model.tiles;

import java.awt.Color;
import java.awt.Graphics;

public class Placard extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String myTitle;
	private String myText;
	
	/**
	 * This is necessary so we can properly serialize the class - we need a consructor with no args
	 */
	@SuppressWarnings("unused")
	private Placard() {
		super();
	}
	
	public Placard(String title, String text) {
		myTitle = title;
		myText = text;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	public String getText() {
		return myText;
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new Placard(myTitle, myText));
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(5);
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
	
	public String toString() {
		return super.toString() + " - \"" + myTitle + "\" - \"" + myText + "\"";
	}
}
