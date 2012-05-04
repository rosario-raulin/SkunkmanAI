package bot;

import java.awt.Point;
import java.util.Stack;

import world.AbstractWO;
import world.Map;
import graph.IPathFinder;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class Bot {

	private ApoSkunkmanAIPlayer ai;
	private IPathFinder<AbstractWO> pathfinder;
	private Map map;
	private Stack<AbstractWO> path;
	private Point lastTarget;
	
	
	public Bot(ApoSkunkmanAIPlayer ai, Map map,
			IPathFinder<AbstractWO> pathfinder) {
		this.ai = ai;
		this.pathfinder = pathfinder;
		this.map = map;
	}
	
	public void reset(ApoSkunkmanAIPlayer ai, Map map,
			IPathFinder<AbstractWO> pathfinder) {
		path = null;
		lastTarget = null;
		this.ai = ai;
		this.pathfinder = pathfinder;
		this.map = map;
	}
	
	public void moveTo(ApoSkunkmanAILevel level) {
		Point target = level.getGoalXPoint();
		if (target.x == -1) {
			throw new IllegalArgumentException("Not in GoalX mode!");
		} else {
			moveTo(level.getGoalXPoint());
		}
	}
	
	public void moveTo(int x, int y) {
		moveTo(new Point(x, y));
	}
	
	public void moveTo(Point p) {
		if (lastTarget == null || path == null || !p.equals(lastTarget)
				|| path.isEmpty()) {
			lastTarget = p;
			path = calcPath(p);
			
			if (path.isEmpty()) {
				throw new RuntimeException("No path to target found: " + p);
			}
		}
		
		AbstractWO next = path.pop();
		System.out.println("Next step is: " + next.getPosition());
		int xDifference = ai.getPlayerX() - next.getX();
		int yDifference = ai.getPlayerY() - next.getY();
		
		if (xDifference > 0) {
			ai.movePlayerLeft();
		} else if (xDifference < 0) {
			ai.movePlayerRight();
		} else if (yDifference > 0) {
			ai.movePlayerUp();
		} else {
			assert(yDifference < 0);
			ai.movePlayerDown();
		}
	}
	
	private Stack<AbstractWO> calcPath(Point p) {
		AbstractWO from = map.getField(ai.getPlayerX(), ai.getPlayerY());
		AbstractWO to = map.getField(p);
		
		return pathfinder.findPath(from, to);
	}
}
