package bot;

import java.util.Stack;

import graph.AStarAlgorithm;
import graph.IGraph;
import graph.NoPathFoundException;
import graph.IPathFinder;
import world.Map;
import world.AbstractWO;

import apoSkunkman.ai.ApoSkunkmanAI;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class BotTestAI extends ApoSkunkmanAI {

	private Bot bot = null;
	private Stack<AbstractWO> path = null;
	
	@Override
	public String getPlayerName() {
		return "LisperAI";
	}

	@Override
	public String getAuthor() {
		return "Rosario Raulin";
	}

	@Override
	public void think(ApoSkunkmanAILevel level, ApoSkunkmanAIPlayer player) {
		if (bot == null) {
			bot = new Bot(player);
		}
		if (path == null || path.isEmpty()) {
			Map map = new Map(level);
			IGraph<AbstractWO> graph = map.asGraph();
			IPathFinder<AbstractWO> pf = new AStarAlgorithm<AbstractWO>(graph);
			
			AbstractWO from = map.getField(bot.getPosition());
			AbstractWO to = map.getField(level.getGoalXPoint());
			
			try {
				path = pf.findPath(from, to);
			} catch (NoPathFoundException e) {
				System.err.println(e.toString());
				return;
			}
		}
		try {
			bot.moveTo(path);
		} catch (WayIsBlockedException e) {
			System.err.println("Error: way is blocked!");
		} catch (ImpossibleMovementException e) {
			bot = null;
			path = null;
		}
	}
}
