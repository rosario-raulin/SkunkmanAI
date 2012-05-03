package world;

public final class Skunkman extends AbstractWO {

	Skunkman(int x, int y) {
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
}
