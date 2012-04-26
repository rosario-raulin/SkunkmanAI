package fsm;

public final class Eating implements IState {

	@Override
	public void enter() {
		throw new RuntimeException("Enter Eating");
	}

	@Override
	public void execute(FiniteStateMachine fsm) {
		throw new RuntimeException("Eating!");
	}

	@Override
	public void exit() {
		throw new RuntimeException("Exit Eating");
	}

}
