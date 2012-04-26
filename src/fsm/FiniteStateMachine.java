package fsm;

public class FiniteStateMachine {
	
	private IState currentState;
	private IState previousState;

	public FiniteStateMachine(IState initialState) {
		changeState(initialState);
	}
	
	public void changeState(IState to) {
		if (currentState != null) {
			currentState.exit();
			previousState = currentState;
		}
		currentState = to;
		currentState.enter();
	}
	
	public void revertToPreviousState() {
		if (previousState == null) {
			throw new RuntimeException("There is no previous state");
		} else {
			changeState(previousState);
		}
	}
	
	public void run() {
		currentState.execute(this);
	}
}
