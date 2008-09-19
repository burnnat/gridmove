package gridmove.model.tiles;

import java.io.Serializable;

public class NodeType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String myType;
	private boolean singleUse;
	
	public static final String STANDARD_NODE = "Standard";
	public static final String BLOCK_RECEPTOR = "Block Receptor";
	
	public NodeType(String type, boolean isSingleUse) {
		myType = type;
		singleUse = isSingleUse;
	}
	
	public String getType() {
		return myType;
	}
	
	public boolean isSingleUse() {
		if(myType.equals(BLOCK_RECEPTOR))
			return false;
		else
			return singleUse;
	}
}
