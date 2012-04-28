package world;

public final class Stone extends AbstractWorldObject {

	Stone(int x, int y) {
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
