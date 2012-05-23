

final class Goodie extends AbstractWO {

	Goodie(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public WORating rating() {
		return WORating.OKAY;
	}

	@Override
	public boolean isBlocked() {
		return false;
	}

}
