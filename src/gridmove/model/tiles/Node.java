package gridmove.model.tiles;

import gridmove.model.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Node extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NodeType myType;
	private Map<String, NodeFunctor> myTargetSets;

	/**
	 * This is necessary so we can properly serialize the class - we need a constructor with no args
	 */
	@SuppressWarnings("unused")
	private Node() {
		super();
	}

//	public Node(String nodeType, GridCoord[] targetCoords, Tile[] replacements) {
//	super(nodeType);

//	if((targetCoords != null) ^ (replacements != null)) {
//	if(targetCoords.length != replacements.length)
//	throw new IllegalArgumentException("Tile and Coordinate lengths are unequal.");

//	NodeTarget[] targets = new NodeTarget[replacements.length];

//	for (int i = 0; i < replacements.length; i++) {
//	try {
//	targets[i] = new NodeTarget(targetCoords[i], Level.getTileDataFor(replacements[i]));
//	} catch (IllegalArgumentException e) {
//	e.printStackTrace();
//	} catch (IllegalAccessException e) {
//	e.printStackTrace();
//	}	
//	}
//	}
//	}

	public Node(NodeType nodeType, Map<String, NodeFunctor> targetSets) {
		super(nodeType.getType());
		myType = nodeType;

		if(targetSets != null)
			myTargetSets = targetSets;
		else
			myTargetSets = new HashMap<String, NodeFunctor>();
	}

	public void doNodeAction() {
		for (String name : myTargetSets.keySet()) {
			myTargetSets.get(name).doNodeAction(Model.getActiveModel().getCurrentLevel().getToggleSet(name));
		}
	}

	public boolean isSingleUseNode() {
		return myType.isSingleUse();
	}

	public List<String> getTargetSets() {
		return new ArrayList<String>(myTargetSets.keySet());
	}
	
	public void addTargetSet(String name, NodeFunctor function) {
		myTargetSets.put(name, function);
	}
	
	public void removeTargetSet(String name) {
		myTargetSets.remove(name);
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
		return Tile.adjustCopy(this, new Node(myType, myTargetSets));
	}

	@Override
	public String toString() {
		String sets;
		if(myTargetSets.size() > 0)
			sets = " - ";
		else
			sets = "";

		for (Iterator<String> it = myTargetSets.keySet().iterator(); it.hasNext();) {
			String name = (String) it.next();

			if(it.hasNext())
				name += ", ";

			sets += name;
		}

		return getType() + " " + super.toString() + sets;
	}

	@Override
	public Integer getTileLayer() {
		return new Integer(4);
	}
}
