package goal;

public enum EGoalPriority {
	
	HIGHEST (1000),
	HIGH (100),
	NORMAL (10),
	LOW (1);
	
	private int value;
	
	private EGoalPriority(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
