package gridmove.model.tiles;

import gridmove.model.RedBlueSet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;


public class RedBlueNode extends Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ACTIVE = "Active";
	public static final String INACTIVE = "Inactive";

	private static Map<String, NodeFunctor> map = new HashMap<String, NodeFunctor>();
	static {
		map.put(RedBlueSet.RED_BLUE_SET, NodeFunctor.TOGGLE_NODE);
	}
	
	private boolean activated = false;

	public RedBlueNode() {
		super(new NodeType(NodeType.STANDARD_NODE, false), map);
	}

//	public String getTargetSetName() {
//	return myTargetSet;
//	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean isActivated) {
		activated = isActivated;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(mySprite == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else {
			g.drawImage(mySprite, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Tile copy() {
		return Tile.adjustCopy(this, new RedBlueNode());
	}

	@Override
	public String toString() {
		return getName() + "(" + getParentRoomID() + ", " + getGridY() + ", " + getGridX() + ")";
	}

	@Override
	public String getState() {
		if(activated)
			return ACTIVE;
		else
			return INACTIVE;
	}
}
