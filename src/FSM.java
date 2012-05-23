

public class FSM {

	private IState currentState;
	private IState previousState;
	
	protected FSM() {}
	
	public void changeState(IState to) {
		if (currentState != null) {
			currentState.exit(this);
			previousState = currentState;
		}
		currentState = to;
		currentState.enter(this);
	}
	
	public FSM(IState initial) {
		changeState(initial);
	}
	
	public void revertToPrevious() {
		changeState(previousState);
	}
	
	public void run() {
		currentState.execute(this);
	}
}
