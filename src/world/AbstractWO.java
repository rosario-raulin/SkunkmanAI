package world;

import java.awt.Point;

public abstract class AbstractWO {
	
	protected final Point position;
	
	AbstractWO(int x, int y) {
		this.position = new Point(x, y);
	}
	
	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}
	
	public Point getPosition() {
		return new Point(position);
	}
	
	public abstract WORating rating();
	public abstract boolean isWalkable();
}
