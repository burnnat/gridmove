package gridmove.model.tiles;

import gridmove.controller.Direction;
import gridmove.model.Skin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Avatar extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int imageOffsetX;
	private int imageOffsetY;
	private Direction facingDirection = Direction.DOWN;
	private Map<Direction, BufferedImage> sprites = new HashMap<Direction, BufferedImage>();

	public void loadFromSkin(Skin s) {
		if(s == null)
			return;

		setImage(s.getImageFor(this));

		repaint();
	}

	public void setImage(BufferedImage image) {
		if(image == null) {
			sprites.clear();
			updateCurrentSprite();
			return;
		}
		
		int width = image.getWidth();
		int height = image.getHeight();
		int colorScheme = BufferedImage.TYPE_INT_ARGB;
		
		BufferedImage downImage = image;
		BufferedImage leftImage = new BufferedImage(width, height, colorScheme);
		BufferedImage upImage = new BufferedImage(width, height, colorScheme);
		BufferedImage rightImage = new BufferedImage(width, height, colorScheme);
		
		double centerX = (image.getRaster().getWidth() - image.getRaster().getMinX()) / 2.0;
		double centerY = (image.getRaster().getHeight() - image.getRaster().getMinY()) / 2.0;
		AffineTransformOp rotation = new AffineTransformOp(AffineTransform.getRotateInstance(Math.toRadians(90), centerX, centerY), AffineTransformOp.TYPE_BILINEAR);
		
		leftImage = rotation.filter(downImage, null);
		upImage = rotation.filter(leftImage, null);
		rightImage = rotation.filter(upImage, null);

		sprites.put(Direction.UP, upImage);
		sprites.put(Direction.RIGHT, rightImage);
		sprites.put(Direction.DOWN, downImage);
		sprites.put(Direction.LEFT, leftImage);

		updateCurrentSprite();
	}

	public void setFacingDirection(Direction d) {
		facingDirection = d;
		updateCurrentSprite();
	}

	private void updateCurrentSprite() {
		mySprite = sprites.get(facingDirection);
	}

	@Override
	protected synchronized void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(new Color(0, 0, 255, 150));
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, imageOffsetX, imageOffsetY, getWidth(), getHeight(), this);
		}
	}

	public synchronized void setImageOffset(int x, int y) {
		imageOffsetX = x;
		imageOffsetY = y;
	}

	@Override
	public Tile copy() {
		return this;
	}

	@Override
	public Integer getTileLayer() {
		//if it's the gridedit cursor, put it on top
		if(mySprite == null)
			return new Integer(20);
		else
			return new Integer(7);
	}
}
