package test;

import components.State;
import problem.SpaceState;

public class Test_stateSpace {

	public static void main(String[] args) {
		
		State state = new State("Test_isgoal.txt");
		
		if(SpaceState.isGoal(state)) {
			System.out.println("U r the best!!");
		}else {
			System.out.println("F*ck!!");
		}

	}
	
	

}
