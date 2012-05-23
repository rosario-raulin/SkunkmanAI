import java.awt.Point;
import java.util.SortedSet;
import java.util.TreeSet;

import apoSkunkman.ai.ApoSkunkmanAIEnemy;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class SkunkmanFSM extends FSM {
	
	private final Bot bot;
	private Map map;
	private SortedSet<AbstractGoal> goals;
	
	public SkunkmanFSM(ApoSkunkmanAIPlayer player, ApoSkunkmanAILevel level,
			AbstractSkunkmanState initial) {
		bot = new Bot(player);
		map = new Map(level);
		goals = new TreeSet<AbstractGoal>();
		
		updateGoals(level);		
		changeState(initial);
	}
	
	private void updateGoals(ApoSkunkmanAILevel level) {
		filterGoals();
		
		final Point goalXPoint = level.getGoalXPoint();
		if (goalXPoint.x != -1) {
			goals.add(new PointX(goalXPoint, EGoalPriority.HIGH));
		}
		else {
			for (final ApoSkunkmanAIEnemy e : level.getEnemies()) {
				if (e.isVisible()) {
					Point pos = new Point((int)e.getX(), (int) e.getY());
					goals.add(new Enemy(pos, EGoalPriority.HIGH));
				}
			}
		}
	}

	private void filterGoals() {
		if (!goals.isEmpty()) {
				final SortedSet<AbstractGoal> newGoals =
						new TreeSet<AbstractGoal>();
				for (final AbstractGoal g : goals) {
					if (g.getPriority().equals(EGoalPriority.HIGHEST) &&
							!g.isReached(bot)) {
						newGoals.add(g);
					}
				}
				goals = newGoals;
		}
	}

	public void run(ApoSkunkmanAILevel level) {
		map = new Map(level);
		updateGoals(level);
		run();
	}

	public Map getMap() {
		return map;
	}
	
	public Bot getBot() {
		return bot;
	}

	public AbstractGoal getGoal() {
		return goals.last();
	}
	
	public SortedSet<AbstractGoal> getGoals() {
		return goals;
	}
	
	public void clearGoals() {
		goals.clear();
	}
	
	public void addGoal(AbstractGoal goal) {
		goals.add(goal);
	}
}
