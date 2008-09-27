package gridmove.model.tiles;

import gridmove.model.Skin;

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
	
	private static final float TEXT_SIZE_RATIO = .6f;
	
	private int numberGems;
	private boolean isPassable;
	private Font myFont = new Font("Dialog", Font.BOLD, 14);

	/**
	 * This is necessary so we can properly serialize the class - we need a constructor with no args
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
				g.setFont(myFont);
				FontMetrics fm = g.getFontMetrics();

				Rectangle2D bounds = getBounds();
				Rectangle2D textBounds = fm.getStringBounds(text, g);

				g.drawString(text, (int)((bounds.getWidth() - textBounds.getWidth()) / 2), (int)((bounds.getHeight() - textBounds.getHeight()) / 2 + fm.getAscent()));
			}
		}
	}
	
	@Override
	public void loadFromSkin(Skin s) {
		super.loadFromSkin(s);
		
		int textSize = (int) (s.getTileSize() * TEXT_SIZE_RATIO);
		
		Graphics g = getGraphics();
		
		if(g != null) {
			g.setFont(myFont);
			FontMetrics fm = g.getFontMetrics();
			
			while(fm.getAscent() > textSize + 2 || fm.getAscent() < textSize) {
				//use the proportion to get a closer font size
				float closerSize = myFont.getSize() * textSize / fm.getAscent();
				
				if(closerSize == myFont.getSize())
					break;
				
				myFont = myFont.deriveFont(closerSize);

				g.setFont(myFont);
				fm = g.getFontMetrics();
			}
			
			System.out.println("Font size: " + myFont.getSize());
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
