package fsm;

public abstract class AbstractSkunkmanState implements IState {

	@Override
	public void enter(FSM fsm) {
		if (fsm instanceof SkunkmanFSM) {
			enter((SkunkmanFSM)fsm);
		} else {
			throw new IllegalArgumentException("FSM not of type SkunkmanFSM");
		}
	}

	@Override
	public void execute(FSM fsm) {
		if (fsm instanceof SkunkmanFSM) {
			execute((SkunkmanFSM)fsm);
		} else {
			throw new IllegalArgumentException("FSM not of type SkunkmanFSM");
		}
	}

	@Override
	public void exit(FSM fsm) {
		if (fsm instanceof SkunkmanFSM) {
			exit((SkunkmanFSM)fsm);
		} else {
			throw new IllegalArgumentException("FSM not of type SkunkmanFSM");
		}
	}

	abstract public void enter(SkunkmanFSM fsm);
	abstract public void execute(SkunkmanFSM fsm);
	abstract public void exit(SkunkmanFSM fsm);
}
