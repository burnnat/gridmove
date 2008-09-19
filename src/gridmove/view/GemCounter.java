package gridmove.view;

import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.Gem;

import java.awt.Image;

public class GemCounter extends ItemCounter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalItems;
	private int itemCount = 0;

	@Override
	public void updateItemCount()  {
		totalItems = Model.getActiveModel().getCurrentLevel().getTotalGems();
		itemCount = Model.getActiveModel().getCurrentLevel().getGemsCollected();
		resizeToMinimum();
		repaint();
	}

	@Override
	public Image imageFromSkin(Skin s) {
		return s.getImageFor(new Gem());
	}
	
	@Override
	public String getText() {
		return itemCount + "/" + totalItems;
	}
}
