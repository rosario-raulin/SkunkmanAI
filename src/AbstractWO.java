

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
	
	@Override
	public String toString() {
		return position.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AbstractWO otherWO = (AbstractWO)obj;
		return position.equals(otherWO.getPosition());
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 23;
		return PRIME * position.hashCode();
	}
	
	public abstract WORating rating();
	public abstract boolean isWalkable();
	public abstract boolean isBlocked();
}
