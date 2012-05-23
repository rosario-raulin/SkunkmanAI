

import java.awt.Point;
import java.util.Stack;

import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class Bot {

	private final ApoSkunkmanAIPlayer ai;

	public Bot(final ApoSkunkmanAIPlayer ai) {
		this.ai = ai;
	}
	
	public void moveTo(final Point p) throws ImpossibleMovementException {
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
				throw new ImpossibleMovementException();
			} else {
				ai.movePlayerDown();
			}
		}
	}
	
	public void moveTo(AbstractWO obj)
			throws WayIsBlockedException, ImpossibleMovementException {
		if (obj.isBlocked()) {
			throw new WayIsBlockedException();
		} else {
			moveTo(obj.getPosition());
		}
	}
	
	public void moveTo(final Stack<AbstractWO> path)
			throws WayIsBlockedException, ImpossibleMovementException {
		moveTo(path.pop());
	}
	
	public Point getPosition() {
		return new Point(ai.getPlayerX(), ai.getPlayerY());
	}
	
	public void laySkunkman() {
		ai.laySkunkman();
	}
	
	public int getSkunkmanRange() {
		return ai.getSkunkWidth();
	}
}
