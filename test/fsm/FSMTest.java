package fsm;

import apoSkunkman.ai.ApoSkunkmanAI;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public class FSMTest extends ApoSkunkmanAI {

	private SkunkmanFSM fsm;
	
	@Override
	public String getPlayerName() {
		return "FSMTestBot";
	}

	@Override
	public String getAuthor() {
		return "Rosario Raulin";
	}

	@Override
	public void think(ApoSkunkmanAILevel level, ApoSkunkmanAIPlayer player) {
		if (fsm == null) {
			fsm = new SkunkmanFSM(player, level, new Run());
		}
		fsm.run(level);
	}

}
