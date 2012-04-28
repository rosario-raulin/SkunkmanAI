package world;

public final class Goodie extends AbstractWorldObject {

	Goodie(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WorldObjectRating rating() {
		return WorldObjectRating.OKAY;
	}

}
