package fsm;

public interface IState {
	void enter();
	void execute(FiniteStateMachine fsm);
	void exit();
}
