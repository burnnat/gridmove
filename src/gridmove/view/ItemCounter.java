package gridmove.view;

import gridmove.model.Model;
import gridmove.model.Skin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class ItemCounter extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int SPRITE_SIZE = 12;

	private Font myFont;

	private transient Image mySprite;
	private transient Image myImageCache;

	public ItemCounter() {
		super();
		setImage(imageFromSkin(Model.getActiveModel().getCurrentSkin()));
		updateFontSize();
		updateItemCount();
		repaint();
	}

	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	/**
	 *  Updates the number of gems/keys from the model. Should call repaint().
	 */
	public abstract void updateItemCount();

	public abstract Image imageFromSkin(Skin s);

	public void setImage(Image i) {
		if(i == null)
			return;
		mySprite = i;
		updateImage();
	}

	public void updateImage() {
		myImageCache = mySprite.getScaledInstance(SPRITE_SIZE, SPRITE_SIZE, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(myImageCache));
		repaint();	
	}

	public void updateFontSize() {
		Graphics g = getGraphics();
		if(g != null) {
			FontMetrics fm = g.getFontMetrics();
			
			while(fm.getAscent() > SPRITE_SIZE + 2 || fm.getAscent() < SPRITE_SIZE) {
				//use the proportion to get a closer font size
//				Font oldFont = new Font("Arial", g.getFont().getStyle(), g.getFont().getSize());
				Font oldFont = g.getFont();
				float closerSize = (float)(oldFont.getSize() * SPRITE_SIZE / fm.getAscent());

				myFont = oldFont.deriveFont(closerSize);
				g.setFont(myFont);

				fm = g.getFontMetrics();
			}
			
			setFont(myFont);
		}
	}

	protected void paintComponent(Graphics g) {
		setText(getText());
		super.paintComponent(g);
	}

	public abstract String getText();

	protected void resizeToMinimum() {
		updateImage();
		updateFontSize();
		if(getHeight() < getMinimumSize().getHeight() || getWidth() < getMinimumSize().getWidth())
			setSize(getMinimumSize());
	}
}
