package problem;

import components.State;

public class Problem {

	State initialState;
	
	public Problem(State state) {
		this.initialState=state;
	}

	public State getInitialState() {
		return initialState;
	} 
	
}
