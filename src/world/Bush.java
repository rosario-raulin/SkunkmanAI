package world;

public final class Bush extends AbstractWorldObject {

	Bush(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WorldObjectRating rating() {
		return WorldObjectRating.BAD;
	}
}
