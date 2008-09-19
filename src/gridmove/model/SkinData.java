package gridmove.model;

import java.io.Serializable;
import java.util.Map;

public class SkinData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String skinName;
	private Map<String, String> skinData;
	private int skinTileSize;
	
	public SkinData(String name, Map<String, String> data, int tileSize) {
		skinName = name;
		skinData = data;
		skinTileSize = tileSize;
	}
	
	public String getName() {
		return skinName;
	}
	
	public Map<String, String> getMap() {
		return skinData;
	}
	
	public int getTileSize() {
		return skinTileSize;
	}
}
