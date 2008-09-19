package gridmove.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TileData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String myClassName;
	protected List<Map<String, Object>> myFieldGroups;
	
	public TileData(String tileClassName, List<Map<String, Object>> fields) {
		myClassName = tileClassName;
		myFieldGroups = fields;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof TileData))
			return false;
		TileData other = (TileData) o;		
		if(!myClassName.equals(other.myClassName))
			return false;
		if(!myFieldGroups.equals(other.myFieldGroups))
			return false;
		return true;
	}
}
