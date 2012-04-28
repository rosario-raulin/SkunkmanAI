package world;

import java.awt.Point;

public abstract class AbstractWorldObject {
	
	protected final Point position;
	
	AbstractWorldObject(int x, int y) {
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
	
	public abstract WorldObjectRating rating();
	public abstract boolean isWalkable();
}
