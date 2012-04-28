package world;

public final class Skunkman extends AbstractWorldObject {

	Skunkman(int x, int y) {
		super(x, y);
	}
		
	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public WorldObjectRating rating() {
		return WorldObjectRating.AVOID;
	}
}
