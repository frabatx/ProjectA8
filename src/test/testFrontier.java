package test;

import java.util.ArrayList;

import algorithm.UninformedSearchStrategy;
import components.State;
import problem.Problem;
import problem.Strategy;
import tree.Node;

public class testFrontier {
	public static final int DEPTH_MAX = 4;
	public static final int INC_DEPTH = 1;

	public static void main(String[] args) throws Exception {
		State initialState = new State("Test.0.txt");
		Problem prob = new Problem(initialState);
		UninformedSearchStrategy uniformedAlgorithm = new UninformedSearchStrategy();
		for (Strategy s : Strategy.values()) {
			ArrayList<Node> nodeSolution = uniformedAlgorithm.search(prob, s, DEPTH_MAX, INC_DEPTH); 
			int i = 0;
			System.out.println("////////////////////////////////////");
			System.out.println(s);
			for (Node node : nodeSolution) {
				System.out.println(i + node.toString());
				i++;
			}
			System.out.println("////////////////////////////////////");  
		}  

	}

}
