package world;

import apoSkunkman.ai.ApoSkunkmanAIConstants;

public final class WOFactory {

	private WOFactory() {}
	
	public static AbstractWO createWorldObject(int type, int x,
			int y) {
		switch (type) {
		case ApoSkunkmanAIConstants.LEVEL_FREE:
			return new FreeField(x, y);
		case ApoSkunkmanAIConstants.LEVEL_BUSH:
			return new Bush(x, y);
		case ApoSkunkmanAIConstants.LEVEL_GOODIE:
			return new Goodie(x, y);
		case ApoSkunkmanAIConstants.LEVEL_SKUNKMAN:
			return new Skunkman(x, y);
		case ApoSkunkmanAIConstants.LEVEL_STONE:
			return new Stone(x, y);
		}
		throw new IllegalArgumentException("Not a valid AWO type: " + type);
	}
}
