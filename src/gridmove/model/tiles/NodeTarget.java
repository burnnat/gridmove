package gridmove.model.tiles;


import gridmove.model.GridCoord;
import gridmove.model.TileData;

import java.io.Serializable;
import java.util.Comparator;

public class NodeTarget implements Comparator<NodeTarget>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GridCoord myCoords;
	TileData myTarget;
		
	public NodeTarget(GridCoord coords, TileData replacement) {
		myCoords = coords;
		myTarget = replacement;
	}
	
	public GridCoord getTargetGridLocation() {
		return myCoords;
	}
	
	public TileData getTargetTile() {
		return myTarget;
	}

	public int compare(NodeTarget target1, NodeTarget target2) {
		if(target1.equals(target2))
			return 0;
		
		GridCoord coord1 = target1.getTargetGridLocation();
		GridCoord coord2 = target2.getTargetGridLocation();
		
		if(coord1.room > coord2.room)
			return 1;
		if(coord1.room < coord2.room)
			return -1;
		
		if(coord1.y > coord2.y)
			return 1;
		if(coord1.y < coord2.y)
			return -1;
		
		if(coord1.x > coord2.x)
			return 1;
		if(coord1.x < coord2.x)
			return -1;
		
		return getName(target1.getTargetTile()).compareTo(getName(target2.getTargetTile()));
	}
	
	private static String getName(TileData td) {
		if(td == null)
			return "Blank";
		else
			return td.myClassName.substring(td.myClassName.lastIndexOf('.') + 1);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof NodeTarget))
			return false;
		NodeTarget other = (NodeTarget) o;
		if(!myCoords.equals(other.getTargetGridLocation()))
			return false;
		if(myTarget == null) {
			if(other.getTargetTile() != null)
				return false;
		}
		else if(!myTarget.equals(other.getTargetTile())) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		return toString(this);
	}
	
	public static String toString(NodeTarget nt) {		
		return getName(nt.myTarget) + "(" + nt.getTargetGridLocation().room + ", " + nt.getTargetGridLocation().y + ", " + nt.getTargetGridLocation().x + ")";
	}
}
