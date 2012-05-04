package bot;

import graph.DijkstraAlgorithm;
import graph.IPathFinder;
import world.Map;
import world.AbstractWO;

import apoSkunkman.ai.ApoSkunkmanAI;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public final class BotTestAI extends ApoSkunkmanAI {

	private Bot bot = null;
	
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
			Map map = new Map(level);
			IPathFinder<AbstractWO> pathfinder
				= new DijkstraAlgorithm<AbstractWO>(map.asGraph());
			bot = new Bot(player, map, pathfinder);
		}
		
		try {
			bot.moveTo(level.getGoalXPoint());
		} catch (RuntimeException e) {
			System.err.println("You should fix: " + e);
			bot = null;
		}
	}

}
