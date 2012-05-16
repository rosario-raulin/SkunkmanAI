package fsm;

public final class LaySkunkman extends AbstractSkunkmanState {

	@Override
	public void enter(SkunkmanFSM fsm) {
		fsm.getBot().laySkunkman();
	}

	@Override
	public void execute(SkunkmanFSM fsm) {
		fsm.changeState(new FindSaferPlace());
	}

	@Override
	public void exit(SkunkmanFSM fsm) {
	}
}
