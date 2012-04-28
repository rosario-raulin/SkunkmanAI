package world;

enum WorldObjectRating {
	
	GOOD (5),
	OKAY (10),
	BAD (50),
	AVOID (100);
	
	private int value;
	
	private WorldObjectRating(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
