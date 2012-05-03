package world;

enum WORating {
	
	GOOD (5),
	OKAY (10),
	BAD (50),
	AVOID (100);
	
	private int value;
	
	private WORating(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
