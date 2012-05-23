import apoSkunkman.ai.ApoSkunkmanAI;
import apoSkunkman.ai.ApoSkunkmanAILevel;
import apoSkunkman.ai.ApoSkunkmanAIPlayer;

public class Fnord extends ApoSkunkmanAI {

	private SkunkmanFSM fsm;
	
	@Override
	public String getPlayerName() {
		return "Fnord";
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
