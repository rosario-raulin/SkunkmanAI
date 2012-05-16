package fsm;

public interface IState {
	void enter(FSM fsm);
	void execute(FSM fsm);
	void exit(FSM fsm);
}
