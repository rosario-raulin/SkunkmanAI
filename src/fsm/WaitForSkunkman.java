package fsm;

import java.awt.Point;

public final class WaitForSkunkman extends AbstractSkunkmanState {

	private final Point position; 
	
	public WaitForSkunkman(final Point comeFrom) {
		super();
		position = comeFrom;
	}

	@Override
	public void enter(SkunkmanFSM fsm) {
	}

	@Override
	public void execute(SkunkmanFSM fsm) {
		if (!fsm.getMap().containsSkunkman(position)) {
			fsm.changeState(new Run());
		}
	}

	@Override
	public void exit(SkunkmanFSM fsm) {
		// TODO Auto-generated method stub

	}

}
