package world;

public final class FreeField extends AbstractWorldObject {

	FreeField(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WorldObjectRating rating() {
		return WorldObjectRating.GOOD;
	}

}
