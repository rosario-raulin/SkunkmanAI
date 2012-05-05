package world;

final class Bush extends AbstractWO {

	Bush(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WORating rating() {
		return WORating.BAD;
	}
}
