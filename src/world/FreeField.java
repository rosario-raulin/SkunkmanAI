package world;

public final class FreeField extends AbstractWO {

	FreeField(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WORating rating() {
		return WORating.GOOD;
	}

}
