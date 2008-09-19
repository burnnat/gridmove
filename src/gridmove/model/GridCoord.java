package gridmove.model;

import java.io.Serializable;

public class GridCoord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int room;
	public int x;
	public int y;
	
	/**
	 * A wrapper class for a grid location: the room number, x position, and y position. 
	 */
	public GridCoord(int roomID, int gridY, int gridX) {
		room = roomID;
		x = gridX;
		y = gridY;
	}

//	public int getRoomID() {
//		return room;
//	}
	
	public void setRoom(int roomID) {
		room = roomID;
	}

//	public int getGridX() {
//		return x;
//	}
//
//	public int getGridy() {
//		return y;
//	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object compareTo) {
		if(compareTo instanceof GridCoord) {
			GridCoord other = (GridCoord)compareTo;
			return x == other.x && y == other.y && room == other.room;
		}
		else
			return false;
	}
	
	public GridCoord copy() {
		return new GridCoord(room, y, x);
	}
	
	public String toString() {
		return "(" + room + ", " + y + ", " + x + ")";
	}
}
