package problem;

import components.State;

public class Problem {

	private State initialState;
	private SpaceState spaceState = new SpaceState();
	
	public Problem(State state) {
		this.initialState=state;
	}

	public State getInitialState() {
		return initialState;
	} 
	
	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	
	public void setSpaceState(SpaceState spaceState) {
		this.spaceState = spaceState;
	}
	
	public SpaceState getSpaceState() {
		return spaceState;
	}
	
}
