package world;

public final class Goodie extends AbstractWO {

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

}
