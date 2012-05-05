package world;

final class Stone extends AbstractWO {

	Stone(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
	
	@Override
	public WORating rating() {
		return WORating.AVOID;
	}

	@Override
	public boolean isBlocked() {
		return true;
	}
}
