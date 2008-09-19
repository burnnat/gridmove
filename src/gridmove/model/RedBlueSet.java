package gridmove.model;

import gridmove.model.tiles.RedBlueNode;

import java.lang.reflect.InvocationTargetException;

public class RedBlueSet extends ToggleSet {
	
	//the set name reserved for the level-wide red/blue set
	public static final String RED_BLUE_SET = "Red/Blue Set";
	
	private boolean activated = false;
	private RedBlueNode[] switches;
	
	public RedBlueSet(TargetSet data, RedBlueNode[] nodes) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		super(data);
		switches = nodes;
	}

	public String getName() {
		return RED_BLUE_SET;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void toggle() {
		super.toggle();
		activated = !activated;
		updateNodes();
	}
	
	public void setActivated(boolean activating) {
		super.setActivated(activating);
		activated = activating;
		updateNodes();
	}
	
	private void updateNodes() {
		for (RedBlueNode rbn : switches) {
			rbn.setActivated(activated);
		}
	}
}
