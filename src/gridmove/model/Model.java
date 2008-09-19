package gridmove.model;

import gridmove.view.GridFrame;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Model {
	private static Map<GridFrame, Model> models = new HashMap<GridFrame, Model>();
	private static Model activeModel;
	private Level currentLevel;
	private Skin currentSkin;
	
	private Model() {
		currentLevel = new Level(null);
		currentSkin = Skin.CLASSIC;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}

	public Skin getCurrentSkin() {
		return currentSkin;
	}
	
	public void setCurrentLevel(Level l) {
		currentLevel = l;
	}

	public void setCurrentSkin(Skin currentSkin) {
		this.currentSkin = currentSkin;
		currentLevel.reloadImageData();
	}
	
	/**
	 * Creates a model instance to be paired with the frame.
	 * 
	 * @param frame
	 */
	public static void register(GridFrame frame) {
		if(!models.containsKey(frame)) {
			models.put(frame, new Model());
		}
	}
	
	/**
	 * Gets the model associated with whichever frame is currently active.
	 * 
	 * @return the active model
	 */
	public static Model getActiveModel() {
		if(activeModel != null) {
			return activeModel;
		} else {
//			for (GridFrame frame : models.keySet()) {
//				if(frame.isActive())
//					return getModelFor(frame);
//			}
			
			return null;
		}
	}
	
	public static void setActiveModel(Model m) {
		activeModel = m;
	}
	
	public static Model getModelFor(GridFrame frame) {
		return models.get(frame);
	}
}
