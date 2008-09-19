package gridmove.controller;

public class Direction {
	public static final Direction RIGHT = new Direction(1, 0);
	public static final Direction LEFT = new Direction(-1, 0);
	public static final Direction UP = new Direction(0, -1);
	public static final Direction DOWN = new Direction(0, 1);
	public static final Direction NOWHERE = new Direction(0, 0);
	
	private final int dx;
	private final int dy;
	
	public Direction(int xMotion, int yMotion) {
		dx = xMotion;
		dy = yMotion;
	}
	
	public Direction opposite() {
		return new Direction(-dx, -dy);
	}
	
	public int dx() {
		return dx;
	}
	
	public int dy() {
		return dy;
	}
}
