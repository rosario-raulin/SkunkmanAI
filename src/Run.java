import java.awt.Point;
import java.util.Stack;

public final class Run extends AbstractSkunkmanState {

	private Stack<AbstractWO> path;
	
	@Override
	public void enter(SkunkmanFSM fsm) {
		if (!fsm.getGoals().isEmpty()) {
			Map map = fsm.getMap();
			Bot bot = fsm.getBot();
			AbstractWO from = map.getField(bot.getPosition());
			AbstractWO to = fsm.getGoal().getWO(map);
			IPathFinder<AbstractWO> pf =
					new DijkstraAlgorithm<AbstractWO>(map.asGraph());
			try {
				path = pf.findPath(from, to);
			} catch (NoPathFoundException e) {
			}
		}
	}

	@Override
	public void execute(SkunkmanFSM fsm) {
		if (enemyIsClose(fsm)) {
			fsm.changeState(new LaySkunkman());
		} else if (path == null || path.isEmpty()) {
				fsm.changeState(new Run());
		} else {
			try {
				fsm.getBot().moveTo(path);
			} catch (WayIsBlockedException e) {
				fsm.changeState(new LaySkunkman());
			} catch (ImpossibleMovementException e) {
				path = null;
				fsm.clearGoals();
				System.out.println("Cannot move this way");
			}
		}
	}

	private boolean enemyIsClose(SkunkmanFSM fsm) {
		for (AbstractGoal g : fsm.getGoals()) {
			if (g instanceof Enemy) {
				final Point player = fsm.getBot().getPosition();
				final Point enemy = g.getPosition();
			
				if (Math.abs(player.x - enemy.x) <= 0 &&
						Math.abs(player.y - enemy.y) <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void exit(SkunkmanFSM fsm) {
	}
}