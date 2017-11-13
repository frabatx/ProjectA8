package tree;

import java.util.ArrayList;

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
	public static boolean isGoal(State state)
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
	public ArrayList<State> successor(State state) throws CloneNotSupportedException {
		ArrayList<State> successorList = new ArrayList<>();
		
		for(Action a: state.generateActions()) {
			successorList.add(state.newState(state, a));
		}
		
		return successorList;
	}
	/**
	 * Alternative version of successor with treeTuple
	 * @param state
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public ArrayList<TreeTuple<Action, State, Integer>> successorT(State state) throws CloneNotSupportedException{
		ArrayList<TreeTuple<Action, State, Integer>> successorList = new ArrayList<>();
		for (Action a: state.generateActions()) {
			TreeTuple<Action, State, Integer> tuple = new TreeTuple<Action, State, Integer>(a, state.newState(state, a), a.getCost());
			successorList.add(tuple);
		}
		return successorList;
	}
	
	
	protected class TreeTuple<A, S, C> {
		private A first;
		private S second;
		private C third;

		public TreeTuple(A a, S s, C c) {
			first = a;
			second = s;
			third = c;
		}

		public A getFirst() {
			return first;
		}

		public S getSecond() {
			return second;
		}

		public C getThird() {
			return third;
		}

	}
}
