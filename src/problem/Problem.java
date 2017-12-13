package problem;

import components.State;

/**
 * This class rapresents the problem. 
 * Initial State and Space State are the basis of the rappresentation of problems
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class Problem {

	private State initialState;
	private SpaceState spaceState = new SpaceState();
	
	//COSTRUCTOR
	/**
	 * The problem is built from an initial state
	 * @param state
	 * initial state
	 */
	public Problem(State state) {
		this.initialState=state;
	}
	//GETTER
	/**
	 * This method returns the initial state
	 * @return
	 * initial state
	 */
	public State getInitialState() {
		return initialState;
	} 
	/**
	 * This method returns the space state
	 * @return
	 * space state
	 */
	public SpaceState getSpaceState() {
		return spaceState;
	}
	//SETTER
	/**
	 * This method set initial state
	 * @param initialState
	 */
	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	/**
	 * This method set a new Space State into Problem
	 * @param spaceState
	 */
	public void setSpaceState(SpaceState spaceState) {
		this.spaceState = spaceState;
	}
	
	
	
}
