package fsm;

import java.awt.Point;
import java.util.Stack;

import bot.Bot;
import bot.ImpossibleMovementException;
import bot.WayIsBlockedException;

import world.AbstractWO;
import world.EMapMode;
import world.Map;
import graph.AStarAlgorithm;
import graph.IPathFinder;
import graph.NoPathFoundException;

public final class FindSaferPlace extends AbstractSkunkmanState {

	private Stack<AbstractWO> saferPlace;
	private Point comeFrom;
	
	@Override
	public void enter(SkunkmanFSM fsm) {
		comeFrom = fsm.getBot().getPosition();
		final Map map = fsm.getMap();
		final Bot bot = fsm.getBot();
		final AbstractWO from = map.getField(fsm.getBot().getPosition());
		final IPathFinder<AbstractWO> pf =
				new AStarAlgorithm<AbstractWO>(map.asGraph(EMapMode.STRICT));
		
		Stack<AbstractWO> candidate = null;
		
		for (AbstractWO field : fsm.getMap()) {
			if (isSafe(field, bot.getPosition(), bot.getSkunkmanRange())) {
				try {
					final Stack<AbstractWO> alt = pf.findPath(from, field);
					
					if (candidate == null || alt.size() < candidate.size()) {
						candidate = alt;
					}
					
				} catch (NoPathFoundException e) {
					// We can safely ignore this.
				}
			}
		}
		
		saferPlace = candidate;
	}

	private boolean isSafe(AbstractWO field, Point currentPos, int skunkRange) {
		if (field.getPosition().equals(currentPos)) {
			return false;
		}

		if (field.getX() == currentPos.x) {
			final int y = field.getY();
			final int yMax = currentPos.y + skunkRange;
			final int yMin = currentPos.y - skunkRange;
			
			if (y > currentPos.y && y <= yMax) {
				return false;
			}
			
			if (y < currentPos.y && y >= yMin) {
				return false;
			}
		} else if (field.getY() == currentPos.y) {
			final int x = field.getX();
			final int xMax = currentPos.x + skunkRange;
			final int xMin = currentPos.x - skunkRange;
			
			if (x > currentPos.x && x <= xMax) {
				return false;
			}
			
			if (x < currentPos.x && x >= xMin) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void execute(SkunkmanFSM fsm) {
		if (saferPlace != null) {
			if (saferPlace.isEmpty()) {
				fsm.changeState(new WaitForSkunkman(comeFrom));
			} else {
				try {
					fsm.getBot().moveTo(saferPlace);
				} catch (ImpossibleMovementException e) {
					e.printStackTrace();
				} catch (WayIsBlockedException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("No safer place found! I'll try again.");
			fsm.changeState(new FindSaferPlace());
		}
	}

	@Override
	public void exit(SkunkmanFSM fsm) {
	}

}
