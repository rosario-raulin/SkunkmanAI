package fsm;

import goal.AbstractGoal;
import goal.Enemy;
import graph.AStarAlgorithm;
import graph.IPathFinder;
import graph.NoPathFoundException;

import java.awt.Point;
import java.util.Stack;

import bot.Bot;
import bot.ImpossibleMovementException;
import bot.WayIsBlockedException;

import world.AbstractWO;
import world.Map;

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
					new AStarAlgorithm<AbstractWO>(map.asGraph());
			try {
				path = pf.findPath(from, to);
			} catch (NoPathFoundException e) {
				fsm.changeState(new Run());
			}
		}
	}

	@Override
	public void execute(SkunkmanFSM fsm) {
		if (path == null || path.isEmpty()) {
			fsm.changeState(new Run());
		} else if (enemyIsClose(fsm)) {
			fsm.changeState(new LaySkunkman());
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
			
				if (Math.abs(player.x - enemy.x) <= 1 &&
						Math.abs(player.y - enemy.y) <= 1) {
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
