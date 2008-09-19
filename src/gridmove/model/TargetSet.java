package gridmove.model;

import gridmove.model.tiles.NodeTarget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A TargetSet is basically just a souped-up array of NodeTargets wrapped up nice and neatly.
 * 
 * @author Nat
 */
public class TargetSet implements Comparator<TargetSet>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int maxTargetsToDisplay = 4;
	
	private String myName;
	private List<NodeTarget> myTargets;
	
	public TargetSet(String name, List<NodeTarget> targets) {
		myName = name;
		myTargets = targets;
	}
	
	public String getName() {
		return myName;
	}
	
	public int getSize() {
		return myTargets.size();
	}
	
	public List<NodeTarget> getTargets() {
		return myTargets;
	}
	
	public String toString() {
		String targetString = myName;
		
		if(myTargets.size() > 0)
			targetString += ": adds ";

		int i;
		for (i = 0; i < Math.min(myTargets.size(), maxTargetsToDisplay); i++) {
			if(!targetString.equals(myName + ": adds ")) {
				targetString += ", ";
			}
			targetString += NodeTarget.toString(myTargets.get(i));
		}
		
		if(i >= maxTargetsToDisplay)
			targetString += "...";
		
		return targetString;
	}

	public int compare(TargetSet ts1, TargetSet ts2) {
		return ts1.getName().compareTo(ts2.getName());
	}

	public void setName(String myName) {
		this.myName = myName;
	}

	public void setTargets(List<NodeTarget> myTargets) {
		this.myTargets = myTargets;
	}
	
	public void addTarget(NodeTarget target) {
		removeTargetAt(target.getTargetGridLocation());
		myTargets.add(target);
	}
	
	public void removeTargetAt(GridCoord coords) {
		for (NodeTarget nt : new ArrayList<NodeTarget>(myTargets)) {
			if(nt.getTargetGridLocation().equals(coords))
				myTargets.remove(nt);
		}
	}
}
