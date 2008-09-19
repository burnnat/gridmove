package gridmove.view;

import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.Key;

import java.awt.Image;

public class KeyCounter extends ItemCounter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int keyCount = 0;

	@Override
	public void updateItemCount()  {
		keyCount = Model.getActiveModel().getCurrentLevel().getKeysCollected();
		resizeToMinimum();
		repaint();
	}

	@Override
	public Image imageFromSkin(Skin s) {
		return s.getImageFor(new Key());
	}

	@Override
	public String getText() {
		return "" + keyCount;
	}
}
