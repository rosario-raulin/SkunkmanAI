package goal;

import java.awt.Point;

import bot.Bot;

import world.AbstractWO;
import world.Map;

public abstract class AbstractGoal implements Comparable<AbstractGoal> {

	private final EGoalPriority priority;
	private final Point point;
	
	public AbstractGoal(final Point point, final EGoalPriority priority) {
		this.priority = priority;
		this.point = point;
	}
	
	public AbstractWO getWO(final Map map) {
		return map.getField(point);
	}
	
	public boolean isReached(final Bot bot) {
		return point.equals(bot.getPosition());
	}
	
	private int getPriorityValue() {
		return priority.getValue();
	}
	
	public Point getPosition() {
		return point;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		
		final AbstractGoal otherGoal = (AbstractGoal)other;
		return point.equals(otherGoal.point);
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 23;
		return point.hashCode() * PRIME;
	}
	
	@Override
	public int compareTo(final AbstractGoal goal) {
		return this.getPriorityValue() - goal.getPriorityValue();
	}
	
	public EGoalPriority getPriority() {
		return priority;
	}
}
