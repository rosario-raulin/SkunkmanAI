package bot;

import java.awt.Point;
import java.util.Stack;

import world.AbstractWO;

import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class Bot {

	private final ApoSkunkmanAIPlayer ai;

	public Bot(final ApoSkunkmanAIPlayer ai) {
		this.ai = ai;
	}
	
	public void moveTo(final Point p) {
		final int xDiff = ai.getPlayerX() - p.x;
		final int yDiff = ai.getPlayerY() - p.y;
	
		if (xDiff == -1) {
			ai.movePlayerRight();
		} else if (xDiff == 1) {
			ai.movePlayerLeft();
		} else if (yDiff == 1) {
			ai.movePlayerUp();
		} else {
			if (yDiff != -1) {
				throw new IllegalArgumentException("Cannot move directly: " + p);
			} else {
				ai.movePlayerDown();
			}
		}
	}
	
	public void moveTo(AbstractWO obj) {
		moveTo(obj.getPosition());
	}
	
	public void moveTo(final Stack<AbstractWO> path) {
		moveTo(path.pop());
	}
	
	public Point getPosition() {
		return new Point(ai.getPlayerX(), ai.getPlayerY());
	}
}
