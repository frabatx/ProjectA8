package problem;

import java.util.HashMap;

import components.State;
import movements.Action;

/**
 * Space state is rappresent all possibility combination ov action,successor by rootstate and cost
 * @author kekko
 *
 */
public class SpaceState {
	
	public SpaceState() {
	}
	
	/**
	 * It is the method that decides when the final result is reached
	 * 
	 * @param state 
	 * @return
	 */
	public boolean isGoal(State state)
	{
		boolean isgoal=true;
		int [][] matrix = state.getMatrix();
		
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++) {
				if(matrix[i][j]!=state.getK()) {
					isgoal=false;
					break;
				}
			}
		}
		return isgoal;
	}
	
	
	/**
	 * We don't understand if method have to return threeTuple (action,state,cost) or only states genereted by all action
	 * the second version implement a threeTuple
	 * @param state
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public HashMap<Action,State> successor(State state) throws CloneNotSupportedException {
		HashMap<Action,State> successorList = new HashMap<>();
		
		for(Action a: state.generateActions()) {
			successorList.put(a,state.newState(state, a));
		}
		
		return successorList;
	}

}
